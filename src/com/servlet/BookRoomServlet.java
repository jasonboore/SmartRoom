package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.services.BookRoom;
import com.services.Login;
import com.tools.CompareToken;
import com.tools.InTimeSet0;
import com.tools.TokenUtil;
import com.tools.tojson;
import com.tools.tounicode;

import net.sf.json.JSONObject;

/**
 * Ԥ��������
 */

public class BookRoomServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			//���Token��������
			//��ѯ�������Ƿ�Ϊ��
			String MeetingStatus = BookRoom.selectMeetingStatus(Time, BuildingNumber, RoomNumber);
            if(MeetingStatus.equals("0"))
            {
            	//���������Ϊ����״̬
            	
            	/*ͨ��Token��ѯԱ����*/
            	String EmployeeNumber=CompareToken.selectEmployeeNumberByToken(Token);
            	/*ͨ��Ա���Ų�ѯ����*/
            	String Name=CompareToken.selectNameByEmployeeNumber(EmployeeNumber);
            	/*ͨ��Ա���Ų�ѯԱ���ȼ�*/
            	String AdminPower=Login.selectadminpower(EmployeeNumber);
            	/*��ѯ�����ҵȼ�*/
            	String MeetingRoomLevel=BookRoom.selectMeetingRoomLevel(Time, BuildingNumber, RoomNumber,Days);
            	
            	if(Integer.parseInt(AdminPower)>=Integer.parseInt(MeetingRoomLevel)) {
            		//���Ա���ȼ����ڻ����ҵȼ�������Ԥ���˻�����
            		boolean IsSuccess=BookRoom.BookRoom(Time, Name, RoomNumber, BuildingNumber,Days,EmployeeNumber);
                if(IsSuccess)
                {
                	//�޸Ļ�����״̬
                	BookRoom.UpdateRoomInfoIsMeeting(Time, BuildingNumber, RoomNumber,Days);
                    /**
                     * ���ö�ʱ�̣߳����������ʱ�������״̬�Զ���Ϊ1
                     */
                	InTimeSet0 I0=new InTimeSet0();
                    I0.SetDays(Days);
                    I0.SetEndTime(Time.substring(6)+":00");
                    I0.setRoomNumber(RoomNumber);
                    I0.setTime(Time);
                    I0.setBuildingNumber(BuildingNumber);
                	Thread th0=new Thread(I0);
                	System.out.println("set0��ִ��");
                	th0.start();
                    System.out.println("�������");
                	response.setContentType("text/html;charset=utf-8");
                	
    	        	PrintWriter out = response.getWriter();  
    	        	tojson<String, String> json=new tojson<>();
    	           json.put("Status", "0");//�ɹ�����״̬0
    	           json.put("EmployeeNumber", EmployeeNumber);//����Ա����
    	           json.put("Name", Name);//��������
    	            System.out.println("0  "+json.toString());
    	            out.write(json.toString());
    	            out.flush();
    	            out.close(); 
                }
                else
                {
                	//ʧ��
                	response.setContentType("text/html;charset=utf-8");
    	        	PrintWriter out = response.getWriter();
//    	          ��װ��JSON��ʽ���ͻؿͻ���       
    	         //   String  info_json  = "{\"status\":\"-1\"}";//��װjson��ʽ���ַ���������
    	        	tojson<String, String> info_json=new tojson<>();
    	        	info_json.put("Status", "-1");//��ͨʧ��
    	        	info_json.put("EmployeeNumber", fail);
    	        	info_json.put("Name", fail);
    	            System.out.println("1  "+info_json.toString());
    	            out.write(info_json.toString());
    	            out.flush();
    	            out.close(); 
                }
                
            	}
            	 else
                 {
            		 //Ȩ�޲���	
                 	response.setContentType("text/html;charset=utf-8");
     	        	PrintWriter out = response.getWriter();
//     	          ��װ��JSON��ʽ���ͻؿͻ���       
     	         //   String  info_json  = "{\"status\":\"-1\"}";//��װjson��ʽ���ַ���������
     	        	tojson<String, String> info_json=new tojson<>();
     	        	info_json.put("Status", "-5");
     	        	info_json.put("EmployeeNumber", fail);
     	        	info_json.put("Name", fail);
     	            System.out.println("1  "+info_json.toString());
     	            out.write(info_json.toString());
     	            out.flush();
     	            out.close(); 
                 }
            }
            else if(MeetingStatus.equals("1"))
            {
            	//����״̬Ϊ��ռ��
            	response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          ��װ��JSON��ʽ���ͻؿͻ���       
	        	tojson<String, String> info_json=new tojson<>();
	        	info_json.put("Status", "-6");//�÷����ѱ�Ԥ��������-6
	        	info_json.put("EmployeeNumber", fail).toString();
	        	info_json.put("Name", fail).toString();
	            System.out.println("2  "+info_json.toString());
	            out.write(info_json.toString());
	            out.flush();
	            out.close(); 
            }
            else if(MeetingStatus.equals("2"))
            {
               //����״̬Ϊά��
            	response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          ��װ��JSON��ʽ���ͻؿͻ���       
	        	tojson<String, String> info_json=new tojson<>();
	        	info_json.put("Status", "-7");//����ά�ޣ�����״̬-7
	        	info_json.put("EmployeeNumber", fail).toString();
	        	info_json.put("Name", fail).toString();
	            System.out.println("2  "+info_json.toString());
	            out.write(info_json.toString());
	            out.flush();
	            out.close(); 
            }
            else
            {
            	//ʧ��
            	response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          ��װ��JSON��ʽ���ͻؿͻ���       
	        	tojson<String, String> info_json=new tojson<>();
	        	info_json.put("Status", "-1");//��ͨʧ��
	        	info_json.put("EmployeeNumber", fail).toString();
	        	info_json.put("Name", fail).toString();
	            System.out.println("2  "+info_json.toString());
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
   
	@Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 

		doPost(request, response);
    }


}
