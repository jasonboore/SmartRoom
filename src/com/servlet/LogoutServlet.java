package com.servlet;
/**
 * ע��Servlet
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
        String Token = jsonObject.getString("Token");//��ȡToken
        if(CompareToken.selectToken(Token)) {
        	//�ж����ݿ����Ƿ���Token
        	 boolean IsSuccess=Logout.DeleteToken(Token);
        if(IsSuccess)
        {
        	//Token����δ���ڵ�����
        	 response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
	            String  info_json  = "{\"status\":\"quit\"}";//ɾ�����ݿ���token
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
        }
        else
        {
        	//������ڻ���������������
        	 response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
	            String  info_json  = "{\"status\":\"-3\"}";//�������µ�¼����
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
        }
        }
        else
        {
        	//������ݿ���û��Token
        	 response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
	            String  info_json  = "{\"status\":\"-3\"}";//�������µ�¼����
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
