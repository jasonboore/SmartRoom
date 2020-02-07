package com.servlet;
/*
 * �޸Ļ�����    �ͻ��� ������� 41-47 
 * ���ؿͻ��� 0/-1 ״̬
 * 
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.services.Change;
import com.services.Login;
import com.tools.CompareToken;
import com.tools.FilterManage;
import com.tools.TokenUtil;
import com.tools.intChecker;
import com.tools.tounicode;

import net.sf.json.JSONObject;


public class ChangeServlet extends HttpServlet {

	String isError;//isError �жϳ�����Ϣ
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
	        // System.out.println(json1.toString());//�õ������ַ���
	        // �ѵõ����ַ�����װΪJSON���ٻ�ȡ����Ĵ������û���
	        JSONObject jsonObject = JSONObject.fromObject(json.toString());
	       
	        String Token = jsonObject.getString("Token");//��ȡToken
	        //String Time = jsonObject.getString("Time");//�����ȡʱ���
	        String BuildingNumber = jsonObject.getString("BuildingNumber");//�����ȡ¥��
	        String RoomNumber = jsonObject.getString("RoomNumber");//�����ȡ�����
	        String Function =  jsonObject.getString("Function");//�����ȡ����
	        String MeetingRoomLevel = jsonObject.getString("MeetingRoomLevel");//�����ȡ�����ҵȼ�
	        String IsMeeting=jsonObject.getString("IsMeeting");//��ȡ����״̬
	        String istoken=TokenUtil.verificationToken(Token);//����token
	        if(CompareToken.selectToken(Token)) {
	       //���Token����
	        if(istoken.equals("�ɹ�"))
	        {
	        	//Token��������
	        	/*ͨ��Token��ѯԱ����*/
	        	String EmployeeNumber=CompareToken.selectEmployeeNumberByToken(Token);
	        	
	        	
	        	/*ͨ��Ա���Ų�ѯԱ���ȼ�*/
	        	String AdminPower=Login.selectadminpower(EmployeeNumber);
	        	if(Integer.parseInt(AdminPower)>=2)
	        	{
	        	//���������������쳣���
	        	 FilterManage check2 = new FilterManage();
	             check2.addChecker(new intChecker());
	     		if((check2.doChain_check(jsonObject.getString("Size"))))
	        	{
	     			//������������
	     			System.out.println("������Ϣ�Ϸ�");
	     			int Size = Integer.parseInt(jsonObject.getString("Size"));
	     			String IsSuccess;
	     			 if(IsMeeting.equals("2"))
	 	            {
	     				 //�޸Ļ�����ά�޻���������
	 	            	IsSuccess=Change.UpdateReserveRoomIsMeeting( BuildingNumber, RoomNumber, Size, Function, MeetingRoomLevel, IsMeeting);
	 	            }
	     			 else {
	     				 //�޸Ļ�������������
	        	 IsSuccess = Change.UpdateReserveRoom( BuildingNumber, RoomNumber, Size, Function, MeetingRoomLevel);
	     			 		}
	        	 System.out.println(IsSuccess);
	            if(IsSuccess.equals("success"))
	            {
	            	//�޸ĳɹ�
	            response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          ��װ��JSON��ʽ���ͻؿͻ���       
	            String  info_json  = "{\"status\":\"0\"}";//�ɹ�����״̬0
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
	            }
	            else {
	            	//TokenʧЧ�� ���µ�¼
	            	response.setContentType("text/html;charset=utf-8");
	    	        PrintWriter out = response.getWriter();
	    	        String  info_json  = "{\"status\":\"-1\"}";//��ͨʧ��
	    	        System.out.println(info_json);
	    	        System.out.println("���ݿ���token���Ϸ� ");
	    	        out.write(info_json);
	    	        out.flush();
	    	        out.close();
	            }
	            
	        	}
	     		else {
	     			//������������Ƿ�
	     		response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();  
	            String  info_json  = "{\"status\":\"-2\"}";//����״̬-2��������������Ƿ�
	            System.out.println(info_json);
	            System.out.println("SIZE�Ƿ�");
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
	        	//ʧ��
	        	response.setContentType("text/html;charset=utf-8");
		        PrintWriter out = response.getWriter();
//		      ��װ��JSON��ʽ���ͻؿͻ���       
		        String  info_json  = "{\"status\":\"-1\"}";//��ͨʧ��
		        System.out.println(info_json);
		        out.write(info_json);
		        out.flush();
		        out.close();	        
	        }
	        }
	        else{//����������ݿ�token������
	  		   //��Ҫ��ǿ��ע�� ���µ�½   		   
	  		    response.setContentType("text/html;charset=utf-8");      	   
	 	        PrintWriter out = response.getWriter();     
	 	        String  info_json  = "{\"status\":\"-3\"}";//Token�����ڣ����µ�¼
	 	        System.out.println(info_json);
	 	        System.out.println("���ݿ���token������ ");
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
