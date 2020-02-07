package com.servlet;
/**
 * ��������ʾ����Ԥ�����
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
import com.services.MainInterface1;
import com.tools.CompareToken;
import com.tools.Dateadd;
import com.tools.RoomMessage;
import com.tools.TokenUtil;
import com.tools.dateToString;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class MainInterface1Servlet extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	       
	        String Token = jsonObject.getString("Token");//��ȡToken
	        System.out.println(Token);
	          
	          if(CompareToken.selectToken(Token)) {
	        	  //Token����
	        	  String istoken=TokenUtil.verificationToken(Token);
	         if(istoken.equals("�ɹ�")){
	        	 //Token��������
	        	 String Days=null;
				try {
					//��ȡ��ǰ����
					Days = Dateadd.mydays(dateToString.nowdateToString(), 0);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//��ѯ���շ���
	        	 List<RoomMessage> data=MainInterface1.selectRoomInfoToday(Days);
	        	 if(!(null == data || data.size() ==0 )) {
	        		 //���������Ϣ��Ϊ��
	        	  JSONArray jsonArray=JSONArray.fromObject(data);//��������json��ʽ
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
                 //ʧ�ܷ�������-1
			       	  List<RoomMessage> statusdata=MainInterface1.selectRoomInfoToday1();
			       	  if(statusdata!=null) {
			           	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
			           	String data1=jsonArray.toString();
			           	System.out.println("�����data1    "+data1.toString());
			           	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
			               if(json.size()>0){
			                   for(int i=0;i<json.size();i++){
			                       // ���� jsonarray ���飬��ÿһ������ת�� json ����
			                       net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//			                       System.out.println("����Ľ�����    "+jsonObj);
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
//             Token��������������������-3
	       	  List<RoomMessage> statusdata=MainInterface1.selectRoomInfoToday3();
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
               //TokenΪ�գ���������-3
	        	  List<RoomMessage> statusdata=MainInterface1.selectRoomInfoToday3();
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
