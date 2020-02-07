package com.servlet;
/**
 * ɾ��������
 */
import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.services.Delete;
import com.services.Login;
import com.tools.CompareToken;
import com.tools.Dateadd;
import com.tools.TokenUtil;
import com.tools.dateToString;
import com.tools.tounicode;

import net.sf.json.JSONObject;

public class DeleteServlet extends HttpServlet {
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain;charset=utf-8");
		/*�����ַ���Ϊ'UTF-8'*/
        request.setCharacterEncoding("utf-8");
      	System.out.println("���ӳɹ�����");// �����Ƿ�ɹ�����
        StringBuffer json = new StringBuffer();// �ַ���
        String line = null;
        BufferedReader reader = request.getReader();// ��ȡ��
        while ((line = reader.readLine()) != null) {
            json.append(line);// ���ܵ���JSON��ʽ
        }
        System.out.println(json);//�õ�����JSON��ʽ
        JSONObject jsonObject = JSONObject.fromObject(json.toString());
        String Token = jsonObject.getString("Token");//��ȡToken
      //  String Time = jsonObject.getString("Time");//�����ȡʱ��
        String BuildingNumber = jsonObject.getString("BuildingNumber");//�����ȡ¥��
        String RoomNumber = jsonObject.getString("RoomNumber");//��ȡ�����
       // String Days = jsonObject.getString("Days");//�����ȡ����
//        System.out.println("����������ǣ�  "+Days);
        String istoken=TokenUtil.verificationToken(Token);//token�ж�
        if(CompareToken.selectToken(Token)) {
 		   //������ݿ����Token
        if(istoken.equals("�ɹ�"))
        {
        	//���Token�Ϸ���ִ�в���
        	/**
        	 * ɾ��������Ϣ
        	 */
        	/*ͨ��Token��ѯԱ����*/
        	String EmployeeNumber=CompareToken.selectEmployeeNumberByToken(Token);
        	
        	
        	/*ͨ��Ա���Ų�ѯԱ���ȼ�*/
        	String AdminPower=Login.selectadminpower(EmployeeNumber);
        	if(Integer.parseInt(AdminPower)>=2)
        	{
        	boolean RoomExist= Delete.RoomExist(BuildingNumber, RoomNumber);
        	if(RoomExist) //�������
        	{
        	boolean meeting= Delete.IsMeeting(BuildingNumber, RoomNumber);
        	if(meeting)//�������
        	{
        	String IsSuccess = Delete.DeleteReserveRoom(BuildingNumber, RoomNumber);
            System.out.println(IsSuccess);
            if(IsSuccess.equals("success")) 
            {
            	//����ɹ�
            response.setContentType("text/html;charset=utf-8");
        	PrintWriter out = response.getWriter();
//          ��װ��JSON��ʽ���ͻؿͻ���       
            String  info_json  = "{\"status\":\"0\"}";//�ɹ�����0
            System.out.println(info_json);
            out.write(info_json);
            out.flush();
            out.close(); 
            }
            else
            {
            	response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();       
	            String  info_json  = "{\"status\":\"-1\"}";//��ͨʧ��
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
        	        String  info_json  = "{\"status\":\"-8\"}";//����ռ��
        	        System.out.println(info_json);
        	        System.out.println("����ռ�� ");
        	        out.write(info_json);
        	        out.flush();
        	        out.close();	
        	} 
        	}
        	else
        	{
        		response.setContentType("text/html;charset=utf-8");      	   
        	        PrintWriter out = response.getWriter();     
        	        String  info_json  = "{\"status\":\"-9\"}";//���䲻����
        	        System.out.println(info_json);
        	        System.out.println("���䲻���� ");
        	        out.write(info_json);
        	        out.flush();
        	        out.close();	
        	} 
        	}
        	else {
        		response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();  
	            String  info_json  = "{\"status\":\"-5\"}";//��������ӷ����Ȩ��
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
        	}
        }
        
        else {
        	response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();
	        String  info_json  = "{\"status\":\"-3\"}";//TokenʧЧ�� ���µ�¼
	        System.out.println(info_json);
	        System.out.println("���ݿ���token���Ϸ� ");
	        out.write(info_json);
	        out.flush();
	        out.close();
        }
        }
        
        else {
        	//����������ݿ�token������
  		   //��Ҫ��ǿ��ע�� ���µ�½   		   
  		    response.setContentType("text/html;charset=utf-8");      	   
 	        PrintWriter out = response.getWriter();     
 	        String  info_json  = "{\"status\":\"-3\"}";//TokenΪ�գ� ���µ�¼
 	        System.out.println(info_json);
 	        System.out.println("���ݿ���token������ ");
 	        out.write(info_json);
 	        out.flush();
 	        out.close();		   
        }
        }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
}
}
