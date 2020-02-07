package com.servlet;
/**
 * 注销Servlet
 */
import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.Logout;
import com.tools.CompareToken;

import net.sf.json.JSONObject;


public class LogoutServlet extends HttpServlet {
	 @Override
	 protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        // TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		/*设置字符集为'UTF-8'*/
        request.setCharacterEncoding("utf-8");
        System.out.println("连接成功反馈");// 测试是否成功连接
        StringBuffer json1 = new StringBuffer();// 字符流
        String line = null;
        BufferedReader reader = request.getReader();// 读取流
        while ((line = reader.readLine()) != null) {
            json1.append(line);// 接受的是JSON格式
        }

        System.out.println("传入的"+json1);//得到的是JSON格式
        JSONObject jsonObject = JSONObject.fromObject(json1.toString());
        String Token = jsonObject.getString("Token");//获取Token
        if(CompareToken.selectToken(Token)) {
        	//判断数据库中是否有Token
        	 boolean IsSuccess=Logout.DeleteToken(Token);
        if(IsSuccess)
        {
        	//Token满足未过期等条件
        	 response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
	            String  info_json  = "{\"status\":\"quit\"}";//删除数据库中token
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
        }
        else
        {
        	//如果过期或其他条件不符合
        	 response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
	            String  info_json  = "{\"status\":\"-3\"}";//返回重新登录界面
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
        }
        }
        else
        {
        	//如果数据库中没有Token
        	 response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
	            String  info_json  = "{\"status\":\"-3\"}";//返回重新登录界面
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
        }
        
	}

	 @Override
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	 

		 doPost(request, response);
	    }

}
