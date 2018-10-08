package com.yellowcong.auth.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yellowcong.auth.code.CaptchaCodeUtils;
import com.yellowcong.auth.code.CaptchaCodeUtils.CaptchaCode;
import com.yellowcong.auth.constants.Constants;

@Controller
public class CaptchaController {
	
	/**
	  * 创建日期:2018/02/07<br/>
	  * 创建时间:8:36:28<br/>
	  * 创建用户:yellowcong<br/>
	  * 机能概要: 写数据到客户端
	  * @param request
	  * @param response
	  * @throws Exception
	 */
	@GetMapping(value = Constants.REQUEST_MAPPING, produces = "image/png")
    public void handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OutputStream out = null;
		try {
			//设置response头信息
			//禁止缓存
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("image/png");
			//存储验证码到session
			CaptchaCode code = CaptchaCodeUtils.getInstance().getCode();
			
			
			//获取验证码code
			String codeTxt = code.getText();
			request.getSession().setAttribute(Constants.STORE_CODE, codeTxt);
			//写文件到客户端
			out = response.getOutputStream();
			byte[] imgs = code.getData();
			out.write(imgs, 0, imgs.length);
			out.flush();
		} finally {
			if(out != null) {
				out.close();
			}
		}
    }
	
	/**
	 * 创建日期:2018年2月7日<br/>
	 * 创建时间:下午10:09:29<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:验证码比对
	 * @param code
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value="/chkCode",method=RequestMethod.POST)
	public void checkJSON(String code,HttpServletRequest req,HttpServletResponse resp) {
		
		//获取session中的验证码
		String storeCode = (String)req.getSession().getAttribute(Constants.STORE_CODE);
		code = code.trim();
		//返回值
		Map<String,Object> map = new HashMap<String, Object>();
		//验证是否对，不管大小写
		if(!StringUtils.isEmpty(storeCode) && code.equalsIgnoreCase(storeCode)) {
			map.put("error", false);
			map.put("msg", "验证成功");
		}else if (StringUtils.isEmpty(code)){
			map.put("error", true);
			map.put("msg", "验证码不能为空");
		}else {
			map.put("error", true);
			map.put("msg", "验证码错误");
		}
		this.writeJSON(resp, map);
	}
	
	/**
     * 在SpringMvc中获取到Session
     * @return
     */
    public void writeJSON(HttpServletResponse response,Object object){
        try {
            //设定编码 
            response.setCharacterEncoding("UTF-8");
            //表示是json类型的数据
            response.setContentType("application/json");
            //获取PrintWriter 往浏览器端写数据
            PrintWriter writer = response.getWriter();

            ObjectMapper mapper = new ObjectMapper(); //转换器
            //获取到转化后的JSON 数据
            String json = mapper.writeValueAsString(object);
            //写数据到浏览器
            writer.write(json);
            //刷新，表示全部写完，把缓存数据都刷出去
            writer.flush();

            //关闭writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
