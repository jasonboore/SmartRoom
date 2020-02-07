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
 * ���������������������
 * ��������
 */
public class ForgotPwdServlet extends HttpServlet {


	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
	response.setContentType("text/html;charset=utf-8");
	/*�����ַ���Ϊ'UTF-8'*/
    request.setCharacterEncoding("utf-8");
    System.out.println("���ӳɹ�����");// �����Ƿ�ɹ�����
    StringBuffer json1 = new StringBuffer();// �ַ���
    String line = null;
    BufferedReader reader = request.getReader();// ��ȡ��
    while ((line = reader.readLine()) != null) {
        json1.append(line);// ���ܵ���JSON��ʽ
    }

    System.out.println("�����"+json1);//�õ�����JSON��ʽ
    
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
	        String  info_json  = "{\"status\":\"-1\"}";//���䲻����
	        System.out.println(info_json);
	        out.write(info_json);
	        out.flush();
	        out.close();
					
  }
		else {
			// ���������������������
	        sendEmail.sendResetPasswordEmail(user,Email);
	        response.setContentType("text/html;charset=utf-8");   
	        PrintWriter out = response.getWriter();     
	        String  info_json  = "{\"status\":\"0\"}";//���ҳɹ�
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
        String  info_json  = "{\"status\":\"-2\"}";//����Ƿ�
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