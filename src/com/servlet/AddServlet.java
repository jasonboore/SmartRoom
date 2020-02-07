package com.servlet;
/*
 * ���������ҵ�servlet
 * �ͻ��˴���Ĳ��� Token Time BuildNumber RoomNumber Size Function MeetingRoomLevel 
 * ��ϸ����������Ͳ��չ����ʼ�
 * TokenUtil.verificationToken(Token);	 ����token���ҵ���com.tools �¸÷����жϴ���token�Ƿ���Ч
 * ���� ) ����unicode����
 * 
 * ���ؿͻ��˲��� "{\"status\":\"-1\"}" / "{\"status\":\"0\"}"
 * 
 * */
import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.services.Add;
import com.services.Login;
import com.tools.CompareToken;
import com.tools.Dateadd;
import com.tools.FilterManage;
import com.tools.SqlInjChecker;
import com.tools.TokenUtil;
import com.tools.dateToString;
import com.tools.intChecker;
import com.tools.tounicode;
import net.sf.json.JSONObject;

public class AddServlet extends HttpServlet {
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
	        JSONObject jsonObject = JSONObject.fromObject(json.toString());
	        /*ͨ��������ֵ*/
	        String Token = jsonObject.getString("Token");//��ȡToken
	       
	        String BuildingNumber = jsonObject.getString("BuildingNumber");//unicode ����¥��
	        String RoomNumber = jsonObject.getString("RoomNumber");//��ȡ�����
	        String Function =  jsonObject.getString("Function");//�����ȡ����
	        String MeetingRoomLevel = jsonObject.getString("MeetingRoomLevel");	////�����ȡ�����ҵȼ�       
	        String istoken=TokenUtil.verificationToken(Token);//token������	    
	        if(CompareToken.selectToken(Token)) {
	        	//������ݿ��д���Token��ִ�в���
	        if(istoken.equals("�ɹ�"))
	        {
	        	/**
	        	 * ��ȡ����״̬,��¥�ţ�����ţ�ʱ��Σ���������ע����
	        	 * ����������ֻ��������
	        	 */
	        	/*ͨ��Token��ѯԱ����*/
            	String EmployeeNumber=CompareToken.selectEmployeeNumberByToken(Token);
            	
            	
            	/*ͨ��Ա���Ų�ѯԱ���ȼ�*/
            	String AdminPower=Login.selectadminpower(EmployeeNumber);
            	if(Integer.parseInt(AdminPower)>=2)
            	{
	        	 FilterManage check1 = new FilterManage();
	             check1.addChecker(new SqlInjChecker());
	             FilterManage check2 = new FilterManage();
	             check2.addChecker(new intChecker());
	     		if((check1.doChain_check(BuildingNumber))&&(check1.doChain_check(RoomNumber))
	     				&&(check2.doChain_check(jsonObject.getString("Size"))))
	        	{
	     			//����Ϸ�
	     			System.out.println("������Ϣ�Ϸ�");
	     			int Size = Integer.parseInt(jsonObject.getString("Size"));
	     			
	     			if(!Add.CompareRoom(BuildingNumber,RoomNumber))
	     			{
	     				//���û�д˷�����Ϣ���������
	     			String Days0=null,Days1=null,Days2=null;
					try {
						/**
						 * ��ȡ���죬���죬���������
						 */
						Days0 = Dateadd.mydays(dateToString.nowdateToString(), 0);
						Days1 = Dateadd.mydays(dateToString.nowdateToString(), 1);
						Days2 = Dateadd.mydays(dateToString.nowdateToString(), 2);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/**
					 * �������������ʱ��ķ�����Ϣ
					 */
					
	     			boolean IsSuccess0 = Add.InsertRoomInfo ("08:00-08:45", BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days0);
	     			boolean IsSuccess1 = Add.InsertRoomInfo ("09:00-09:45", BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days0);
	     			boolean IsSuccess2 = Add.InsertRoomInfo ("10:00-10:45", BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days0);
	     			boolean IsSuccess3 = Add.InsertRoomInfo ("11:00-11:45", BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days0);
	     			boolean IsSuccess4 = Add.InsertRoomInfo ("13:00-13:45", BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days0);
	     			boolean IsSuccess5 = Add.InsertRoomInfo ("14:00-14:45", BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days0);
	     			boolean IsSuccess6 = Add.InsertRoomInfo ("15:00-15:45", BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days0);
	     			boolean IsSuccess7 = Add.InsertRoomInfo ("16:00-16:45", BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days0);

	     			boolean IsSuccess8 = Add.InsertRoomInfo ("08:00-08:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days1);
	     			boolean IsSuccess9 = Add.InsertRoomInfo ("09:00-09:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days1);
	     			boolean IsSuccess10 = Add.InsertRoomInfo ("10:00-10:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days1);
	     			boolean IsSuccess11 = Add.InsertRoomInfo ("11:00-11:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days1);
	     			boolean IsSuccess12 = Add.InsertRoomInfo ("13:00-13:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days1);
	     			boolean IsSuccess13 = Add.InsertRoomInfo ("14:00-14:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days1);
	     			boolean IsSuccess14 = Add.InsertRoomInfo ("15:00-15:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days1);
	     			boolean IsSuccess15 = Add.InsertRoomInfo ("16:00-16:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days1);
	     			
	     			boolean IsSuccess16 = Add.InsertRoomInfo ("08:00-08:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days2);
	     			boolean IsSuccess17 = Add.InsertRoomInfo ("09:00-09:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days2);
	     			boolean IsSuccess18 = Add.InsertRoomInfo ("10:00-10:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days2);
	     			boolean IsSuccess19 = Add.InsertRoomInfo ("11:00-11:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days2);
	     			boolean IsSuccess20 = Add.InsertRoomInfo ("13:00-13:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days2);
	     			boolean IsSuccess21 = Add.InsertRoomInfo ("14:00-14:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days2);
	     			boolean IsSuccess22 = Add.InsertRoomInfo ("15:00-15:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days2);
	     			boolean IsSuccess23 = Add.InsertRoomInfo ("16:00-16:45",BuildingNumber,RoomNumber,Size,Function,MeetingRoomLevel,Days2);
	     			
	            if(IsSuccess0&&IsSuccess1&&IsSuccess2&&IsSuccess3&&IsSuccess4
	            	&&IsSuccess5&&IsSuccess6&&IsSuccess7&&IsSuccess8
	            	&&IsSuccess9&&IsSuccess10&&IsSuccess11&&IsSuccess12
	            	&&IsSuccess13&&IsSuccess14&&IsSuccess15&&IsSuccess16&&IsSuccess17
	            	&&IsSuccess18&&IsSuccess19&&IsSuccess20&&IsSuccess21&&IsSuccess22
	            	&&IsSuccess23)
	            {
	            	//���ӷ�����Ϣ�ɹ�
	            	System.out.println("��������ɹ�");
	            response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
	            String  info_json  = "{\"status\":\"0\"}";//�ɹ�����״̬0
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
	            }
	            
	            else
	            {
	            	System.out.println("��������ʧ��");
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
	     				System.out.println("�����ظ�");
		            	response.setContentType("text/html;charset=utf-8");
			        	PrintWriter out = response.getWriter();  
			            String  info_json  = "{\"status\":\"-4\"}";//�����ظ�����״̬-4
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
		            String  info_json  = "{\"status\":\"-2\"}";//������Ϣ���Ϸ������˻�-2
		            System.out.println(info_json);
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
	        	
	        	//��Ҫǿ��ע��
            	System.out.println("tokenʧЧ");
	        	response.setContentType("text/html;charset=utf-8");
		        PrintWriter out = response.getWriter();
		        String  info_json  = "{\"status\":\"-3\"}";//TokenΪ�ջ�TokenʧЧ�� ���µ�¼
		        System.out.println(info_json);
		        out.write(info_json);
		        out.flush();
		        out.close();
	        }
	        }
	        else {
	        	
	        	//��Ҫǿ��ע��
            	System.out.println("tokenʧЧ");
	        	response.setContentType("text/html;charset=utf-8");
		        PrintWriter out = response.getWriter();
		        String  info_json  = "{\"status\":\"-3\"}";//TokenΪ�ջ�TokenʧЧ�� ���µ�¼
		        System.out.println(info_json);
		        out.write(info_json);
		        out.flush();
		        out.close();
	        }
	     }
	    @Override
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	 

	        response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();
	        out.flush();
	        out.close();
	    }
	}
