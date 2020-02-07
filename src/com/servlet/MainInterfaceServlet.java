package com.servlet;
/**
 * 主界面显示当前时间段房间使用情况
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
import com.services.MainInterface;
import com.tools.CompareToken;
import com.tools.JudgeTimeInterval;
import com.tools.RoomMessage;
import com.tools.TokenUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class MainInterfaceServlet extends HttpServlet {
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	        
	        String Token = jsonObject.getString("Token");//获取Token
	        if(CompareToken.selectToken(Token)) {
	        	//Token存在
	        	String istoken=TokenUtil.verificationToken(Token);//token解码检查
	        if(istoken.equals("成功"))
	        {
	        	//Token符合条件
	        	List<RoomMessage> data=null;
				try {
					//判断时间在哪个时间段，不同时间返回不同时间段信息
					data = JudgeTimeInterval.toapply();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
			
	            if(!(null == data || data.size() ==0 )) {
	            	//如果数据不为空，返回数组
	         	  JSONArray jsonArray=JSONArray.fromObject(data);
	         	String data1=jsonArray.toString();
	         	System.out.println("这里的data1    "+data1.toString());
	         	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
	             if(json.size()>0){
	                 for(int i=0;i<json.size();i++){
	                     // 遍历 jsonarray 数组，把每一个对象转成 json 对象
	                     net.sf.json.JSONObject jsonObj = json.getJSONObject(i);

	                 }}
	             //System.out.println("第一行    "+json.getJSONObject(1));
	             response.setContentType("text/html;charset=utf-8");
	             PrintWriter out = response.getWriter();
	             out.write(data1.toString());
	             out.flush();
	             out.close();
	             }
	            
	            
	            else {

	            	  List<RoomMessage> statusdata=MainInterface.selectRoomInfoTimePart1();
	            	  if(statusdata!=null) {
	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
	                	String data1=jsonArray.toString();
	                	System.out.println("这里的data3    "+data1.toString());
	                	
	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

	                    if(json.size()>0){
	                        for(int i=0;i<json.size();i++){
	                            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//	                            System.out.println("这里的解析的    "+jsonObj);
	                        }}
	                    System.out.println("第一行    "+json.getJSONObject(1));
	                    response.setContentType("text/html;charset=utf-8");
	                    PrintWriter out = response.getWriter();
	                    out.write(data1.toString());
	                    out.flush();
	                    out.close();
	                    }

	              }

	        

	         }
	          
	          else {
                //Token不符合条件，返回数组-3
	        	  List<RoomMessage> statusdata=MainInterface.selectRoomInfoTimePart3();
	        	  if(statusdata!=null) {
	            	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
	            	String data1=jsonArray.toString();
	            	System.out.println("这里的data4    "+data1.toString());
	            	
	            	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

	                if(json.size()>0){
	                    for(int i=0;i<json.size();i++){
	                        // 遍历 jsonarray 数组，把每一个对象转成 json 对象
	                        net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
	                    }}
	                System.out.println("第一行    "+json.getJSONObject(1));
	                response.setContentType("text/html;charset=utf-8");
	                PrintWriter out = response.getWriter();
	                out.write(data1.toString());
	                out.flush();
	                out.close();
	                }

	          }
	        
	     }
	           
	           else {
	        	   //Token不存在，返回数组-3
	         	  List<RoomMessage> statusdata=MainInterface.selectRoomInfoTimePart3();
	         	  if(statusdata!=null) {
	             	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
	             	String data1=jsonArray.toString();
	             	System.out.println("这里的data2    "+data1.toString());
	             	
	             	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
	                 if(json.size()>0){
	                     for(int i=0;i<json.size();i++){
	                         // 遍历 jsonarray 数组，把每一个对象转成 json 对象
	                         net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//	                         System.out.println("这里的解析的    "+jsonObj);
	                     }}
	                 System.out.println("第一行    "+json.getJSONObject(1));
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
