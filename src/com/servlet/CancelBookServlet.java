package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.BookRoom;
import com.tools.CompareToken;
import com.tools.TokenUtil;
import com.tools.tojson;

import net.sf.json.JSONObject;


public class CancelBookServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fail="ʧ��";

		response.setContentType("text/html;charset=utf-8");
		/*�����ַ���Ϊ'UTF-8'*/
        request.setCharacterEncoding("utf-8");
        System.out.println("���ӳɹ�����");// �����Ƿ�ɹ�����
        StringBuffer json11 = new StringBuffer();// �ַ���
        String line = null;
        BufferedReader reader = request.getReader();// ��ȡ��
        while ((line = reader.readLine()) != null) {
            json11.append(line);// ���ܵ���JSON��ʽ
        }

        System.out.println(json11);//�õ�����JSON��ʽ
        JSONObject jsonObject = JSONObject.fromObject(json11.toString());
       
        String Token = jsonObject.getString("Token");//��ȡToken        
		String Time = jsonObject.getString("Time");//�����ȡʱ���
		String RoomNumber = jsonObject.getString("RoomNumber");//��ȡ�����
		String BuildingNumber = jsonObject.getString("BuildingNumber");//�����ȡ�����
	    String Days = jsonObject.getString("Days");//�����ȡ����
		System.out.println("�����tokenֵ��"+Token);
		System.out.println("�����Daysֵ��"+Days);
      
        
        if(CompareToken.selectToken(Token)) {
        	//���Token����
        	  String istoken=TokenUtil.verificationToken(Token);//token������
		if(istoken.equals("�ɹ�")){
			boolean CancelBook=com.services.CancelBook.selectReserveRoom(Time, BuildingNumber, RoomNumber, Days);
			if(CancelBook) {
			boolean Cancel=BookRoom.UpdateRoomInfoIsMeeting0(Time, BuildingNumber, RoomNumber, Days);
			BookRoom.deleteReserveRoom(Time, BuildingNumber, RoomNumber,Days);
			System.out.println("Ԥ��������Ϣ�����");
			if(Cancel)
			{
				response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();  
	        	tojson<String, String> json=new tojson<>();
	           json.put("Status", "0");//�ɹ�����״̬0
	            System.out.println("0  "+json.toString());
	            out.write(json.toString());
	            out.flush();
	            out.close(); 
			}
			else {
				//ʧ��
            	response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          ��װ��JSON��ʽ���ͻؿͻ���       
	         //   String  info_json  = "{\"status\":\"-1\"}";//��װjson��ʽ���ַ���������
	        	tojson<String, String> info_json=new tojson<>();
	        	info_json.put("Status", "-1");//��ͨʧ��
	            System.out.println("1  "+info_json.toString());
	            out.write(info_json.toString());
	            out.flush();
	            out.close(); 	
			}
			}
			else {
				//ʧ��
            	response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          ��װ��JSON��ʽ���ͻؿͻ���       
	         //   String  info_json  = "{\"status\":\"-1\"}";//��װjson��ʽ���ַ���������
	        	tojson<String, String> info_json=new tojson<>();
	        	info_json.put("Status", "-4");//��ͨʧ��
	            System.out.println("1  "+info_json.toString());
	            out.write(info_json.toString());
	            out.flush();
	            out.close(); 
			}
			}
		else {
        	//TokenʧЧ
        	response.setContentType("text/html;charset=utf-8");
        	PrintWriter out = response.getWriter();
//          ��װ��JSON��ʽ���ͻؿͻ���       
        	tojson<String, String> info_json=new tojson<>();
        	info_json.put("Status", "-3");//tokenʧЧ������-3
        	info_json.put("EmployeeNumber", fail);
        	info_json.put("Name", fail);
            System.out.println("3  "+info_json.toString());
            out.write(info_json.toString());
            out.flush();
            out.close(); 
        }
		
		}
        else {
        	//TokenΪ��
        	response.setContentType("text/html;charset=utf-8");
        	PrintWriter out = response.getWriter();
//          ��װ��JSON��ʽ���ͻؿͻ���       
        	tojson<String, String> info_json=new tojson<>();
        	info_json.put("Status", "-3");//TokenΪ�գ�����״̬-3
        	info_json.put("EmployeeNumber", fail);
        	info_json.put("Name", fail);
            System.out.println("4  "+info_json.toString());
            out.write(info_json.toString());
            out.flush();
            out.close(); 
        }
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
