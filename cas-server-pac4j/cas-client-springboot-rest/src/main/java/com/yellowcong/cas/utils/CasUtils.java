package com.yellowcong.cas.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.cookie.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 创建日期:2018年3月14日 <br/>
 * 创建用户:yellowcong <br/>
 * 功能描述:
 */
public class CasUtils {
	// url的前缀
	private static final String CAS_SERVER_URL_LOGIN = "https://yellowcong.com:8443/login";

	private static final String DEFALUT_ENCODE = "UTF-8";

	/**
	 * 创建日期:2018年3月14日<br/>
	 * 创建用户:yellowcong<br/>
	 * 功能描述:获取登录的cookie信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static Map<String, String> getTgcInfo(String username, String password) {
		// 获取表单的execution 字段
		String execution = getExecution();

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("username", "yellowcong");
		parameters.put("password", "yellowcong");
		parameters.put("execution", execution);
		parameters.put("_eventId", "submit");
		parameters.put("geolocation", "");
		// 登录后的界面，这个无所谓了，返回的结果
		String str = HttpClient.post(CAS_SERVER_URL_LOGIN, parameters);
		
		List<Cookie> cookies = HttpClient.getCookies();
		Map<String,String> result = cookies.size() ==0 ?null:new HashMap<String,String>();
		for (Cookie cookie : cookies) {
			result.put(cookie.getName(), cookie.getValue());
		}
		printCookies();
		return result;
	}
	
	/**
	 * 创建日期:2018年3月15日<br/>
	 * 创建用户:yellowcong<br/>
	 * 功能描述:打印cookie信息
	 */
	private static void printCookies(){
		List<Cookie> cookies = HttpClient.getCookies();
		for (Cookie cookie : cookies) {
			System.out.printf("%s:%s\r\n", cookie.getName(), cookie.getValue());
		}
	}

	public static void main(String[] args) {
		Map<String,String>  str = getTgcInfo("yellowcong","yellowcong");
	}

	/**
	 * 创建日期:2018年3月14日<br/>
	 * 创建用户:yellowcong<br/>
	 * 功能描述:获取 execution
	 * 
	 * @return
	 */
	private static String getExecution() {
		//清空cookie的信息
		HttpClient.clearCookie();
		
		//发送请求到服务器
		String str = HttpClient.get(CAS_SERVER_URL_LOGIN);
		printCookies();
		// 通过jsonp来解析
		Document doc = Jsoup.parse(str);
		// 获取execution的值
		Element element = doc.select("input[name=execution]").first();
		return element.val();
	}
}
