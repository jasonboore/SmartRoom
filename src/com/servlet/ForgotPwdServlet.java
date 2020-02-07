package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.rePassward;
import com.tools.CompareToken;
import com.tools.sendEmail;
import com.tools.EmployeeNumberChecker;
import com.tools.FilterManage;
import com.tools.User;
import com.tools.UserRepasswoed;
import com.tools.emailChecker;

import net.sf.json.JSONObject;

import com.services.Logout;

/**
 * 发送重设密码申请的链接
 * 忘记密码
 */
public class ForgotPwdServlet extends HttpServlet {


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
    
    String Email = jsonObject.getString("Email");
    FilterManage check1 = new FilterManage();
    check1.addChecker(new emailChecker());
	if((check1.doChain_check(Email))) {
   
		UserRepasswoed userRepasswoed = rePassward.getInstance();
		User user = userRepasswoed.findUserByNameOrEmail(Email);
		if (user == null) {
			response.setContentType("text/html;charset=utf-8");   
	        PrintWriter out = response.getWriter();     
	        String  info_json  = "{\"status\":\"-1\"}";//邮箱不存在
	        System.out.println(info_json);
	        out.write(info_json);
	        out.flush();
	        out.close();
					
  }
		else {
			// 发送重新设置密码的链接
	        sendEmail.sendResetPasswordEmail(user,Email);
	        response.setContentType("text/html;charset=utf-8");   
	        PrintWriter out = response.getWriter();     
	        String  info_json  = "{\"status\":\"0\"}";//查找成功
	        System.out.println(info_json);
	        out.write(info_json);
	        out.flush();
	        out.close();
			
		}
	} 
	
	else
	{
		response.setContentType("text/html;charset=utf-8");
 	   
        PrintWriter out = response.getWriter();     
        String  info_json  = "{\"status\":\"-2\"}";//邮箱非法
        System.out.println(info_json);
        out.write(info_json);
        out.flush();
        out.close();
	}
}

 @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 

	 doPost(request, response);
    }

}