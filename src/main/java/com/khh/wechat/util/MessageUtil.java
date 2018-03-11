package com.khh.wechat.util;


import com.khh.wechat.vo.message.request.*;
import com.khh.wechat.vo.message.response.*;
import com.khh.wechat.vo.message.response.TextMessage;
import com.khh.wechat.vo.message.response.VoiceMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 消息工具类
 * 
 * 
 */
public class MessageUtil {


	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	
	/*
	 * 返回消息类型:图片
	 */
	public static final String RESP_MESSAGE_TYPE_IMAG = "image";
	
	/*
	 * 返回消息类型是:音频
	 */
	public static final String RESP_MESSAGE_TYPE_VOICE ="voice";

	/*
	 * 返回消息类型:视频
	 */
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	

	
	
	
	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/*
	 * 请求消息类型:视频
	 */
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	
	/*
	 * 请求消息类型:小视频
	 */
	public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
	
	
	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
	
	
	
	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	/**
	 * 事件类型：scancode_waitmsg(扫码带提示)
	 */
	public static final String EVENT_TYPE_SCANCODE_WAITMSG = "scancode_waitmsg";

	/*
	 * 事件类型为：scancode_push(扫码推事件)
	 */
	public static final String EVENT_TYPE_SCANCODE_PUSH = "scancode_push";
	
	
	/*
	 * 事件类型为:pic_sysphoto(系统拍照发图)
	 */
	public static final String EVENT_TYPE_PIC_SYSPHOTO = "pic_sysphoto";
	
	
	//文本提交方式
	public static final int SUBMIT_TYPE_TEXT = 1;
	//图文提交方式
	public static final int SUBMIT_TYPE_NEWS = 2;
	//音乐提交方式
	public static final int SUBMIT_TYPE_MUSIC = 3;
	//图片提交方式
	public static final int SUBMIT_TYPE_IMAGE = 4;
	//音频提交方式
	public static final int SUBMIT_TYPE_VOICE = 5;
    //视频提交方式
	public static final int SUBMIT_TYPE_VIDEO = 6;
	

	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	// @SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request)
			throws Exception {
		// 将解析结果存储在 HashMap 中
		Map<String, String> map = new HashMap<String, String>();

		// 从 request 中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到 xml 根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}

	public static BaseRequestMessage parseRequest(HttpServletRequest request) throws Exception {
		Map<String, Object> map = parseXml2(request);

		// 公众账号
		String toUserName = (String) map.get("ToUserName");
		// 发送方账号(open_id)
		String fromUserName = (String) map.get("FromUserName");
		// 消息类型
		String msgType = (String) map.get("MsgType");

		// 获取消息id(当发送的是消息类型的时候才存在msgId，事件类型没有msgId)
		String msgId = (String) map.get("MsgId");

		String createTime = (String)map.get("CreateTime");


		BaseRequestMessage message = new BaseRequestMessage();
		message.setToUserName(toUserName);
		message.setFromUserName(fromUserName);
		message.setMsgType(msgType);
		message.setMsgId(msgId);
		message.setCreateTime(createTime);

		message.setParamMap(map);

		return message;
	}

	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	// @SuppressWarnings("unchecked")
	public static Map<String,Object> parseXml2(HttpServletRequest request) throws Exception {
		// 将解析结果存储在 HashMap 中
		Map<String, Object> map = new HashMap<String, Object>();

		// 从 request 中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到 xml 根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList){
			List<Element> list = e.elements();
			if(list==null || list.size()==0 ){
				map.put(e.getName(), e.getText());
				
			}else{
				Map<String,String> m = new HashMap<String, String >();
				for (Element element : list) {
					m.put(element.getName(), element.getText());
				}
				map.put(e.getName(), m);
			}
			
			
		}

		// 释放资源
		inputStream.close();

		return map;
	}
 	/**
	 *  * 文本消息对象转换成 xml  * 
	 *   * @param textMessage 文本消息对象
	 *   * @return
	 * xml 131.
	 */
	public static String textMessageToXml(TextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * 音乐消息对象转换成 xml
	 * 
	 * @param musicMessage
	 *            音乐消息对象
	 * @return xml
	 */
	public static String musicMessageToXml(MusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/**
	 * 图文消息对象转换成 xml
	 * 
	 * @param newsMessage
	 *            图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
	
	
	/*
	 * 图片消息对象转成xml
	 * @param imagMessage
	 *            图片消息对象
	 * @return xml
	 */
	public static String imagMessageToXml(ImagMessage imagMessage) {
		xstream.alias("xml", imagMessage.getClass());		
		return xstream.toXML(imagMessage);
	}

	
	/*
	 * 语音消息对象转成xml
	 * @param voiceMessage
	 *            语音消息对象
	 * @return xml
	 */
	public static String voiceMessageToXml(VoiceMessage voiceMessage) {
		xstream.alias("xml", voiceMessage.getClass());		
		return xstream.toXML(voiceMessage);
	}
	
	
	/*
	 * 视频消息对象转成xml
	 * @param voiceMessage
	 *            语音消息对象
	 * @return xml
	 */
	public static String videoMessageToXml(VideoMessage videoMessage) {
		xstream.alias("xml", videoMessage.getClass());		
		return xstream.toXML(videoMessage);
	}

	/**
	 * 基础信息转换为xml
	 * @param message
	 * @return
	 */
	public static String baseMessageToXml(BaseMessage message){
		String result = null;
		if(message instanceof TextMessage){
			result = textMessageToXml((TextMessage)message);
		}else if(message instanceof MusicMessage){
			result  = musicMessageToXml((MusicMessage)message);
		}else if(message instanceof NewsMessage) {
			result = newsMessageToXml((NewsMessage) message);
		}else if(message instanceof ImagMessage) {
			result = imagMessageToXml((ImagMessage) message);
		}else if(message instanceof VoiceMessage) {
			result = voiceMessageToXml((VoiceMessage) message);
		}else if(message instanceof VideoMessage) {
			result = videoMessageToXml((VideoMessage) message);
		}

		return result;
	}

	
	
	
	/**
	 * 扩展 xstream，使其支持 CDATA 块
	 * 
	 * 
	 */
	
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有 xml 节点的转换都增加 CDATA 标记
				boolean cdata = true;

				//@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

}