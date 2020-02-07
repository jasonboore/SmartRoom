package com.servlet;
/**
 * ����Ԥ��
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.services.SmartBook;
import com.tools.CompareToken;
import com.tools.FilterManage;
import com.tools.JudgeSearch;
import com.tools.RoomMessage;
import com.tools.SuitableRoom;
import com.tools.SuitableRoomMsg;
import com.tools.TokenUtil;
import com.tools.intChecker;
import com.tools.tounicode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class SmartBookServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����     SmartBookServlet");
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
	        System.out.println(json1);//�õ�����JSON��ʽ
	        JSONObject jsonObject = JSONObject.fromObject(json1.toString());
            String Hours=jsonObject.getString("Hours");//��ȡԤ�ƿ���ʱ��
            //int hours=Integer.parseInt(Hours);
	        String Size1 = jsonObject.getString("Size");//��ȡ�λ�����
	        String Functions = jsonObject.getString("Functions");//��ȡ����
	        String Token = jsonObject.getString("Token");//��ȡToken
	        if(CompareToken.selectToken(Token)) {
	        	//Token����
	        	  String istoken=TokenUtil.verificationToken(Token);
	         if(istoken.equals("�ɹ�")){
	        	 //Token��������
	        	 //���������������쳣���
	        	 FilterManage check1 = new FilterManage();
	             check1.addChecker(new intChecker());
	             if(check1.doChain_check(Size1)) {
	            	 //�������쳣
	            	 int Size = Integer.parseInt(jsonObject.getString("Size"));
	        	 if(Functions.equals("")&&Size1.length()!=0&&Hours.equals(""))
	        	 {
	        		 //�����������ƣ�������������������Ϣ
	        	 List<RoomMessage> data=SmartBook.selectSizeRoomInfo(Size);
	        
	           if(data!=null) {
	        	  JSONArray jsonArray=JSONArray.fromObject(data);
	        	String data1=jsonArray.toString();
	        	System.out.println("�����data1    "+data1.toString());
	        	
	        	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

	            if(json.size()>0){
	                for(int i=0;i<json.size();i++){
	                    // ���� jsonarray ���飬��ÿһ������ת�� json ����
	                    net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//	                    System.out.println("����Ľ�����    "+jsonObj);
	                }}
	            System.out.println("��һ��    "+json.getJSONObject(1));
	            response.setContentType("text/html;charset=utf-8");
	            PrintWriter out = response.getWriter();
	            out.write(data1.toString());
	            out.flush();
	            out.close();
	            }
	           
	           else {
                       //��ͨʧ�ܣ���������-1
	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
	            	  if(statusdata!=null) {
	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
	                	String data1=jsonArray.toString();
	                	System.out.println("�����data1    "+data1.toString());
	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
	                    if(json.size()>0){
	                        for(int i=0;i<json.size();i++){
	                            // ���� jsonarray ���飬��ÿһ������ת�� json ����
	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//	                            System.out.println("����Ľ�����    "+jsonObj);
	                        }
	                        }
	                    System.out.println("��һ��    "+json.getJSONObject(1));
	                    response.setContentType("text/html;charset=utf-8");
	                    PrintWriter out = response.getWriter();
	                    out.write(data1.toString());
	                    out.flush();
	                    out.close();
	                    }

	              }

	        }
	        else if(Size1.equals("")&&Functions.length()!=0&&Hours.equals(""))
	        {
	        	 System.out.println("ʱ������Ϊ��");
	        		 //���빦��Ҫ�󣬷������㹦�ܷ�����Ϣ
	        	 List<RoomMessage> data=SmartBook.selectFunctionsRoomInfo(Functions);
	  	           if(data!=null) {
	  	        	  JSONArray jsonArray=JSONArray.fromObject(data);
	  	        	String data1=jsonArray.toString();
	  	        	System.out.println("�����data1    "+data1.toString()); 	
	  	        	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
	  	            if(json.size()>0){
	  	                for(int i=0;i<json.size();i++){
	  	                    // ���� jsonarray ���飬��ÿһ������ת�� json ����
	  	                    net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
	  	                }}
	  	            System.out.println("��һ��    "+json.getJSONObject(1));
	  	            response.setContentType("text/html;charset=utf-8");
	  	            PrintWriter out = response.getWriter();
	  	            out.write(data1.toString());
	  	            out.flush();
	  	            out.close();
	  	            }
	  	           
	  	           else {
                         //��ͨʧ�ܣ���������-1
	  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
	  	            	  if(statusdata!=null) {
	  	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
	  	                	String data1=jsonArray.toString();
	  	                	System.out.println("�����data1    "+data1.toString());
	  	                	
	  	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

	  	                    if(json.size()>0){
	  	                        for(int i=0;i<json.size();i++){
	  	                            // ���� jsonarray ���飬��ÿһ������ת�� json ����
	  	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//	  	                            System.out.println("����Ľ�����    "+jsonObj);
	  	                        }
	  	                        }
	  	                    System.out.println("��һ��    "+json.getJSONObject(1));
	  	                    response.setContentType("text/html;charset=utf-8");
	  	                    PrintWriter out = response.getWriter();
	  	                    out.write(data1.toString());
	  	                    out.flush();
	  	                    out.close();
	  	                    }
	        	 }
	        	 }
	        	else if(Hours.length()!=0&&Size1.equals("")&&Functions.equals("")) {
	        		 //���빦��Ҫ�������Ҫ�󣬷�������������������Ϣ
	        		System.out.println("��������Ϊ��");
	        		int hours=Integer.parseInt(Hours);
	        		 List<SuitableRoomMsg> data=null;
					try {
						data = SuitableRoom.SuitableRoom(hours,"","");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		  	           if(data!=null) {
		  	        	  JSONArray jsonArray=JSONArray.fromObject(data);
		  	        	String data1=jsonArray.toString();
		  	        	System.out.println("�����data1    "+data1.toString());
		  	        	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	            if(json.size()>0){
		  	                for(int i=0;i<json.size();i++){
		  	                    // ���� jsonarray ���飬��ÿһ������ת�� json ����
		  	                    net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                }}
		  	            System.out.println("��һ��    "+json.getJSONObject(1));
		  	            response.setContentType("text/html;charset=utf-8");
		  	            PrintWriter out = response.getWriter();
		  	            out.write(data1.toString());
		  	            out.flush();
		  	            out.close();
		  	            }
		  	           
		  	           else {
                          //��ͨʧ�ܣ���������-1
		  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
		  	            	  if(statusdata!=null) {
		  	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
		  	                	String data1=jsonArray.toString();
		  	                	System.out.println("�����data1    "+data1.toString());
		  	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	                    if(json.size()>0){
		  	                        for(int i=0;i<json.size();i++){
		  	                            // ���� jsonarray ���飬��ÿһ������ת�� json ����
		  	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                        }
		  	                        }
		  	                    System.out.println("��һ��    "+json.getJSONObject(1));
		  	                    response.setContentType("text/html;charset=utf-8");
		  	                    PrintWriter out = response.getWriter();
		  	                    out.write(data1.toString());
		  	                    out.flush();
		  	                    out.close();
		  	                    }
		        	 }
	        	 }
	        	 else if(Size1.length()!=0&&Functions.length()!=0&&Hours.equals("")) {
	        		 //���빦��Ҫ�������Ҫ�󣬷�������������������Ϣ
	        		 System.out.println("ʱ��Ϊ��");
	        		 List<RoomMessage> data=SmartBook.selectDoubleRoomInfo(Functions, Size);
		  	           if(data!=null) {
		  	        	  JSONArray jsonArray=JSONArray.fromObject(data);
		  	        	String data1=jsonArray.toString();
		  	        	System.out.println("�����data1    "+data1.toString());
		  	        	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	            if(json.size()>0){
		  	                for(int i=0;i<json.size();i++){
		  	                    // ���� jsonarray ���飬��ÿһ������ת�� json ����
		  	                    net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                }}
		  	            System.out.println("��һ��    "+json.getJSONObject(1));
		  	            response.setContentType("text/html;charset=utf-8");
		  	            PrintWriter out = response.getWriter();
		  	            out.write(data1.toString());
		  	            out.flush();
		  	            out.close();
		  	            }
		  	           
		  	           else {
                          //��ͨʧ�ܣ���������-1
		  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
		  	            	  if(statusdata!=null) {
		  	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
		  	                	String data1=jsonArray.toString();
		  	                	System.out.println("�����data1    "+data1.toString());
		  	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	                    if(json.size()>0){
		  	                        for(int i=0;i<json.size();i++){
		  	                            // ���� jsonarray ���飬��ÿһ������ת�� json ����
		  	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                        }
		  	                        }
		  	                    System.out.println("��һ��    "+json.getJSONObject(1));
		  	                    response.setContentType("text/html;charset=utf-8");
		  	                    PrintWriter out = response.getWriter();
		  	                    out.write(data1.toString());
		  	                    out.flush();
		  	                    out.close();
		  	                    }
		        	 }
	        	 }
	        	 else if(Size1.equals("")&&Functions.length()!=0&&Hours.length()!=0) {
	        		 //���빦��Ҫ�������Ҫ�󣬷�������������������Ϣ
	        		 System.out.println("����Ϊ��");
	        		 int hours=Integer.parseInt(Hours);
	        		 List<SuitableRoomMsg> data=null;
					try {
						data = SuitableRoom.SuitableRoom(hours, "", Functions);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		  	           if(data!=null) {
		  	        	  JSONArray jsonArray=JSONArray.fromObject(data);
		  	        	String data1=jsonArray.toString();
		  	        	System.out.println("�����data1    "+data1.toString());
		  	        	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	            if(json.size()>0){
		  	                for(int i=0;i<json.size();i++){
		  	                    // ���� jsonarray ���飬��ÿһ������ת�� json ����
		  	                    net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                }}
		  	            System.out.println("��һ��    "+json.getJSONObject(1));
		  	            response.setContentType("text/html;charset=utf-8");
		  	            PrintWriter out = response.getWriter();
		  	            out.write(data1.toString());
		  	            out.flush();
		  	            out.close();
		  	            }
		  	           
		  	           else {
                          //��ͨʧ�ܣ���������-1
		  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
		  	            	  if(statusdata!=null) {
		  	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
		  	                	String data1=jsonArray.toString();
		  	                	System.out.println("�����data1    "+data1.toString());
		  	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	                    if(json.size()>0){
		  	                        for(int i=0;i<json.size();i++){
		  	                            // ���� jsonarray ���飬��ÿһ������ת�� json ����
		  	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                        }
		  	                        }
		  	                    System.out.println("��һ��    "+json.getJSONObject(1));
		  	                    response.setContentType("text/html;charset=utf-8");
		  	                    PrintWriter out = response.getWriter();
		  	                    out.write(data1.toString());
		  	                    out.flush();
		  	                    out.close();
		  	                    }
		        	 }
	        	 }
	        	 else if(Size1.length()!=0&&Functions.equals("")&&Hours.length()!=0) {
	        		 //���빦��Ҫ�������Ҫ�󣬷�������������������Ϣ
	        		 System.out.println("����Ϊ��");
	        		 int hours=Integer.parseInt(Hours);
	        		 List<SuitableRoomMsg> data=null;
					try {
						data = SuitableRoom.SuitableRoom(hours, Size1, "");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		  	           if(data!=null) {
		  	        	  JSONArray jsonArray=JSONArray.fromObject(data);
		  	        	String data1=jsonArray.toString();
		  	        	System.out.println("�����data1    "+data1.toString());
		  	        	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	            if(json.size()>0){
		  	                for(int i=0;i<json.size();i++){
		  	                    // ���� jsonarray ���飬��ÿһ������ת�� json ����
		  	                    net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                }}
		  	            System.out.println("��һ��    "+json.getJSONObject(1));
		  	            response.setContentType("text/html;charset=utf-8");
		  	            PrintWriter out = response.getWriter();
		  	            out.write(data1.toString());
		  	            out.flush();
		  	            out.close();
		  	            }
		  	           
		  	           else {
                          //��ͨʧ�ܣ���������-1
		  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
		  	            	  if(statusdata!=null) {
		  	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
		  	                	String data1=jsonArray.toString();
		  	                	System.out.println("�����data1    "+data1.toString());
		  	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	                    if(json.size()>0){
		  	                        for(int i=0;i<json.size();i++){
		  	                            // ���� jsonarray ���飬��ÿһ������ת�� json ����
		  	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                        }
		  	                        }
		  	                    System.out.println("��һ��    "+json.getJSONObject(1));
		  	                    response.setContentType("text/html;charset=utf-8");
		  	                    PrintWriter out = response.getWriter();
		  	                    out.write(data1.toString());
		  	                    out.flush();
		  	                    out.close();
		  	                    }
		        	 }
	        	 }
	        	 else if(Size1.length()!=0&&Functions.length()!=0&&Hours.length()!=0) {
	        		 //���빦��Ҫ������Ҫ��ʱ��Ҫ�󣬷�������������������Ϣ
	        		 System.out.println("����Ϊ��");
	        		 int hours=Integer.parseInt(Hours);
	        		 List<SuitableRoomMsg> data=null;
					try {
						data = SuitableRoom.SuitableRoom(hours, Size1,Functions);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		  	           if(data!=null) {
		  	        	  JSONArray jsonArray=JSONArray.fromObject(data);
		  	        	String data1=jsonArray.toString();
		  	        	System.out.println("�����data1    "+data1.toString());
		  	        	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	            if(json.size()>0){
		  	                for(int i=0;i<json.size();i++){
		  	                    // ���� jsonarray ���飬��ÿһ������ת�� json ����
		  	                    net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                }
		  	            
		  	            response.setContentType("text/html;charset=utf-8");
		  	            PrintWriter out = response.getWriter();
		  	            out.write(data1.toString());
		  	            out.flush();
		  	            out.close();
		  	            }
		  	          else {
                          //��ͨʧ�ܣ���������-1
		  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
		  	            	  if(statusdata!=null) {
		  	                	  JSONArray jsonArray1=JSONArray.fromObject(statusdata);
		  	                	String data11=jsonArray1.toString();
		  	                	System.out.println("�����data11    "+data11.toString());
		  	                	net.sf.json.JSONArray json11=net.sf.json.JSONArray.fromObject(data11.toString());
		  	                    if(json11.size()>0){
		  	                        for(int i=0;i<json11.size();i++){
		  	                            // ���� jsonarray ���飬��ÿһ������ת�� json ����
		  	                            net.sf.json.JSONObject jsonObj = json11.getJSONObject(i);
		  	                        }
		  	                        }
		  	                    System.out.println("��һ��    "+json11.getJSONObject(1));
		  	                    response.setContentType("text/html;charset=utf-8");
		  	                    PrintWriter out = response.getWriter();
		  	                    out.write(data11.toString());
		  	                    out.flush();
		  	                    out.close();
		  	                    }
		  	          }
		  	           }
		  	           
		  	           else {
                          //��ͨʧ�ܣ���������-1
		  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
		  	            	  if(statusdata!=null) {
		  	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
		  	                	String data1=jsonArray.toString();
		  	                	System.out.println("�����data1    "+data1.toString());
		  	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	                    if(json.size()>0){
		  	                        for(int i=0;i<json.size();i++){
		  	                            // ���� jsonarray ���飬��ÿһ������ת�� json ����
		  	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                        }
		  	                        }
		  	                    System.out.println("��һ��    "+json.getJSONObject(1));
		  	                    response.setContentType("text/html;charset=utf-8");
		  	                    PrintWriter out = response.getWriter();
		  	                    out.write(data1.toString());
		  	                    out.flush();
		  	                    out.close();
		  	                    }
		        	 }
	        	 }
	         }
	             else {
	            	 //���뷿����Ϣ���Ϸ�
	            	 List<RoomMessage> statusdata=SmartBook.selectConditionStatus2();
 	            	  if(statusdata!=null) {
 	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
 	                	String data1=jsonArray.toString();
 	                	System.out.println("�����data1    "+data1.toString());
 	                	
 	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

 	                    if(json.size()>0){
 	                        for(int i=0;i<json.size();i++){
 	                            // ���� jsonarray ���飬��ÿһ������ת�� json ����
 	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
// 	                            System.out.println("����Ľ�����    "+jsonObj);
 	                        }
 	                        }
 	                    System.out.println("��һ��    "+json.getJSONObject(1));
 	                    response.setContentType("text/html;charset=utf-8");
 	                    PrintWriter out = response.getWriter();
 	                    out.write(data1.toString());
 	                    out.flush();
 	                    out.close();
	            	 }
	             }
	             
	         }
	         else {
                  //TokenʧЧ���Ż�����-2
	       	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus3();
	       	  if(statusdata!=null) {
	           	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
	           	String data1=jsonArray.toString();
	           	System.out.println("�����data1    "+data1.toString());
	           	
	           	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

	               if(json.size()>0){
	                   for(int i=0;i<json.size();i++){
	                       // ���� jsonarray ���飬��ÿһ������ת�� json ����
	                       net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//	                       System.out.println("����Ľ�����    "+jsonObj);
	                   }}
	               System.out.println("��һ��    "+json.getJSONObject(1));
	               response.setContentType("text/html;charset=utf-8");
	               PrintWriter out = response.getWriter();
	               out.write(data1.toString());
	               out.flush();
	               out.close();
	               }

	         }
	        }
	    
	          
	          else {
           //    Token Ϊ�գ���������-3
	        	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus3();
	        	  if(statusdata!=null) {
	            	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
	            	String data1=jsonArray.toString();
	            	System.out.println("�����data1    "+data1.toString());
	            	
	            	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

	                if(json.size()>0){
	                    for(int i=0;i<json.size();i++){
	                        // ���� jsonarray ���飬��ÿһ������ת�� json ����
	                        net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//	                        System.out.println("����Ľ�����    "+jsonObj);
	                    }}
	                System.out.println("��һ��    "+json.getJSONObject(1));
	                response.setContentType("text/html;charset=utf-8");
	                PrintWriter out = response.getWriter();
	                out.write(data1.toString());
	                out.flush();
	                out.close();
	                }

	          }
	        }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
