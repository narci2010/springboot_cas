package com.yellowcong.cas.utils;


import javax.net.ssl.HttpsURLConnection;

import org.springframework.util.StringUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * 首先客户端提交用户名、密码、及Service三个参数，
 * 如果验证成功便返回用户的TGT(Ticket Granting Ticket)至客户端, 
 * 然后客户端再根据 TGT 获取用户的 ST(Service Ticket)来进行验证登录。 
 * 故名思意，TGT是用于生成一个新的Ticket(ST)的Ticket，
 * 而ST则是提供给客户端用于登录的Ticket，两者最大的区别在于，
 * TGT是用户名密码验证成功之后所生成的Ticket，并且会保存在Server中及Cookie中，
 * 而ST则必须是是根据TGT来生成，主要用于登录，并且当登录成功之后 ST 则会失效。
 * 创建日期:2018年2月8日
 * 创建时间:下午6:39:29
 * 创建者    :yellowcong
 * 机能概要:
 */
public class CasServerUtil {
	
	//登录服务器地址
	private static final String  CAS_SERVER_PATH = "https://yellowcong.com:8443";
	
	//登录地址的token 
	private static final String  GET_TOKEN_URL = CAS_SERVER_PATH + "/v1/tickets";
	
	//目标返回的服务器的url
	//反斜杠一定要注意了
	private static final String TAGET_URL = "http://yellowcong.com:8888/";
	
	private static CasServerUtil utils = null;
	
	private CasServerUtil(){}
	
	public static CasServerUtil getInstance(){
		if(utils == null){
			synchronized (CasServerUtil.class) {
				if(utils == null){
					utils = new CasServerUtil();
				}
			}
		}
		return utils;
	}
	/**
	  * 创建日期:2018/02/08<br/>
	  * 创建时间:15:35:16<br/>
	  * 创建用户:yellowcong<br/>
	  * 机能概要: 先通过用户名密码，
	  * 先生成tikect的 token，然后再通过token获取到id
	  * @param args
	  * @throws Exception
	 */
	public static void main(String [] args) throws Exception {
		
		
		String username ="yellowcong";
		String password ="yellowcong";
	
		
		CasServerUtil utils = CasServerUtil.getInstance();
		
		String st = utils.getSt(username, password);
		System.out.println(st);
	}
	/**
	 * 创建日期:2018年2月8日<br/>
	 * 创建时间:下午7:26:32<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:通过用户名和密码来获取service ticket,通过这个可以免密码登录
	 * @param username
	 * @param password
	 * @return
	 */
	public String getSt(String username,String password){
		//先获取到 token generate ticket
		String tgt = utils.getTGT(username, password);
		
		if(StringUtils.isEmpty(tgt)){
			return "";
		}
		
		return utils.getST(tgt);
	}
	/**
	 * 创建日期:2018年2月8日<br/>
	 * 创建时间:下午6:36:54<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:获取到 （Tokent generate tiker ,token生成票据）tgt
	 * @return
	 */
	private String getTGT(String username,String password){
		String tgt = "";
		OutputStreamWriter out = null;
		BufferedWriter wirter  = null;
		HttpsURLConnection conn = null;
		try {
			//第一步，获取到tgt
			conn = (HttpsURLConnection) openConn(GET_TOKEN_URL);
			String param ="username=" + URLEncoder.encode(username, "UTF-8");
			param += "&password" + "=" + URLEncoder.encode(password, "UTF-8");

			out = new OutputStreamWriter(conn.getOutputStream());
			wirter = new BufferedWriter(out);
			//添加参数到目标服务器
			wirter.write(param);
			wirter.flush();
			wirter.close();
			out.close(); 
			
			//获取token
			tgt = conn.getHeaderField("location");
			//获取返回值
			if (tgt != null && conn.getResponseCode() == 201) {
				  tgt = tgt.substring(tgt.lastIndexOf("/") + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn != null){
					conn.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tgt;
	}
	
	/**
	 * 创建日期:2018年2月8日<br/>
	 * 创建时间:下午7:15:16<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:根据票据生成工具，获取st
	 * @param tgt
	 * @return
	 */
	private String getST(String tgt){
		String serviceTicket = "";
		OutputStreamWriter out = null;
		BufferedWriter wirter  = null;
		HttpsURLConnection conn = null;
		try {
			 
			//第一步，获取到tgt
			conn = (HttpsURLConnection) openConn(GET_TOKEN_URL+"/"+tgt);
			
			//需要访问的目标网站
			String param ="service=" + URLEncoder.encode(TAGET_URL, "utf-8");

			out = new OutputStreamWriter(conn.getOutputStream());
			wirter = new BufferedWriter(out);
			//添加参数到目标服务器
			wirter.write(param);
			wirter.flush();
			wirter.close();
			out.close(); 
			
			//获取返回的ticket票据
		    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line ="";
	        while ((line = in.readLine()) != null) {
	        	serviceTicket = line;
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn != null){
					conn.disconnect();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return serviceTicket;
	}
	
	/**
	 * 创建日期:2018年2月8日<br/>
	 * 创建时间:下午7:28:36<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:通过post表单提交来获取连接
	 * @param urlk
	 * @return
	 * @throws Exception
	 */
    private URLConnection openConn(String urlk) throws Exception {
        URL url = new URL(urlk);
        HttpsURLConnection hsu = (HttpsURLConnection) url.openConnection();
        hsu.setDoInput(true);
        hsu.setDoOutput(true);
        hsu.setRequestMethod("POST");
        return hsu;
    }
}
