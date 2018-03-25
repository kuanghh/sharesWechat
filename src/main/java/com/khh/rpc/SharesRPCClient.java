package com.khh.rpc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

/**
 * Created by 951087952@qq.com on 2018/3/25.
 *
 */
public class SharesRPCClient {

    private Connection connection;
    private Channel channel;

    private String replyQueueName;

    public SharesRPCClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RPCUtil.HOST);


        connection = factory.newConnection();
        channel = connection.createChannel();

        //Actively declare a server-named exclusive, autodelete, non-durable queue.
        // The name of the new queue is held in the "queue" field of the AMQP.Queue.DeclareOk result.
        replyQueueName = channel.queueDeclare().getQueue();//创建一条匿名的回应队列，用来存放服务端响应的数据
    }

    public String call(String message) throws Exception{
        //配置 corrID，来作为关联的唯一凭证
        String corrId = UUID.randomUUID().toString();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", RPCUtil.RPC_QUEUE_NAME, props, message.getBytes("UTF-8"));

        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);


        channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                if (properties.getCorrelationId().equals(corrId)) {
                    response.offer(new String(body, "UTF-8"));
                }
            }
        });

        return response.take();
    }

    public void close() throws IOException {
        connection.close();
    }

    public void run(String message) throws Exception{
        String response = "";
        try{
            System.out.println(" client send request");
            response = this.call(message);
            System.out.println(" client get Response : " + response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
