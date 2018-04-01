package com.khh.wechat.util;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.khh.base.exception.ErrorCode;
import com.khh.wechat.vo.AccessToken;
import com.khh.wechat.vo.MassMessageVO;
import com.khh.wechat.vo.button.Button;
import com.khh.wechat.vo.button.CommonButton;
import com.khh.wechat.vo.button.ComplexButton;
import com.khh.wechat.vo.button.Menu;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 微信验证帮助类
 * @author kuanghaohua
 *
 */
public class WeiXinUtil {

	private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);

	//与结构配置信息中的Token要一致
	private static String TOKEN = "aaaa";

	//第三方用户唯一凭证
	private static String APPID = "wx248222285cbb149c";

	//第三方用户唯一凭证密钥
	private static String APPSECRET = "d4624c36b6795d1d99dcf0547af5443d";

	// 获取 access_token 的接口地址（GET） 限 200（次/天）
	private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";


	//创建菜单的接口地址
	private static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 初始化菜单
	 * @throws Exception
	 */
	public static void initMenu() throws Exception{
		try {
			AccessToken accessToken = getAccessToken();

			if (accessToken != null) {
				String requestURL = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken.getToken());
				String jsonMenu = getMenuJsonStr();
				JSONObject jsonObject = sendHttpsRequest(requestURL, HttpPost.METHOD_NAME, jsonMenu);
				if (jsonObject == null || !jsonObject.getInteger("errcode").equals(ErrorCode.SUCCESS)) {
					log.error("创建菜单失败");
				}else{
                    log.debug("创建菜单成功");
                }

			} else {
				log.error("获取token失败");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 *  获取凭证密钥
	 * @return
	 * @throws Exception
	 */
	public static AccessToken getAccessToken()throws Exception{
		AccessToken accessToken = null;

		String requestUrl = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObject = sendHttpsRequest(requestUrl, HttpGet.METHOD_NAME, null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取 token 失败
				log.error("获取 token 失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}

	/**
	 * 发起https请求并获取结果
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	private static JSONObject sendHttpsRequest(String requestUrl, String requestMethod, String outputStr) throws Exception{

		return HttpsUtil.httpRequest(requestUrl, requestMethod, outputStr);
	}


	/**
	 * 调用群发接口
	 * @throws Exception
	 */
	private static String MASS_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
	public static void sendRequestToEveryOne(MassMessageVO messageVO) throws Exception{
		AccessToken accessToken = getAccessToken();
		if (accessToken != null) {
			String requestURL = MASS_URL.replace("ACCESS_TOKEN", accessToken.getToken());

			String message = JSONObject.toJSONString(messageVO);
			sendHttpsRequest(requestURL, HttpPost.METHOD_NAME, message);
			log.info("每天群发推送...");
		} else {
			log.error("获取token失败");
		}
	}


	
	/**
	 * 验证签名
	 * 
	 */
	public static boolean checkSignature(String signature,String timestamp,
			String nonce){
		String[] arr = new String[]{TOKEN,timestamp,nonce};
		//将token,timestamp,nonce三个参数进行字典排序
		try{
			Arrays.sort(arr);
			
		}catch(Exception e){
			
		}
		StringBuilder content = new StringBuilder();
		
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
			
		}
		MessageDigest md = null;
		String tmpStr = null;
		
		try {
			md = MessageDigest.getInstance("SHA-1");
			//将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		content = null;
		//将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		
		return tmpStr!=null ?tmpStr.equals(signature.toUpperCase()):false;
		
	}
	
	/**
	 * 将字节数组转转换为十六进制字符串
	
	 */
	private static String byteToStr(byte[] byteArray){
		String strDigest = "";
		for(int i = 0;i<byteArray.length;i++){
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}
	/**
	 * 将字节转换为十六进制字符串
	 */
	private static String byteToHexStr(byte mByte){
		char[] Digit = {'0','1','2','3','4','5','6','7','8','9',
				'A','B','C','D','E','F'};
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte>>>4)&0X0F];
		tempArr[1] = Digit[mByte&0X0F];
		
		String s = new String(tempArr);
		return s;
	}

	public static String button_a1_key = "a1";
	public static String button_a2_key = "a2";
	public static String button_a3_1_key = "a3_1";
	public static String button_a3_2_key = "a3_2";

	private static String getMenuJsonStr() {

		Menu menu = new Menu();

		CommonButton a1 = new CommonButton();
		a1.setName("今日行情");
		a1.setType(Button.BUTTON_TYPE_CLICK);
		a1.setKey(button_a1_key);

		CommonButton a2 = new CommonButton();
		a2.setName("昨日数据");
		a2.setType(Button.BUTTON_TYPE_CLICK);
		a2.setKey(button_a2_key);

		ComplexButton a3 = new ComplexButton();
        a3.setName("我");

        CommonButton a3_1 = new CommonButton();
        a3_1.setName("绑定注册");
        a3_1.setType(Button.BUTTON_TYPE_CLICK);
        a3_1.setKey(button_a3_1_key);

        CommonButton a3_2 = new CommonButton();
        a3_2.setName("我的信息");
        a3_2.setType(Button.BUTTON_TYPE_CLICK);
        a3_2.setKey(button_a3_2_key);

        List<Button> a3List = new ArrayList<>();
        a3List.add(a3_1);
        a3List.add(a3_2);

        a3.setSub_button(a3List);

        List<Button> menu_list = new ArrayList<>();
        menu_list.add(a1);
        menu_list.add(a2);
        menu_list.add(a3);

        menu.setButton(menu_list);

		return JSONObject.toJSONString(menu);
	}

	public static void main(String[] args) {
		System.out.println(getMenuJsonStr());
	}
}
