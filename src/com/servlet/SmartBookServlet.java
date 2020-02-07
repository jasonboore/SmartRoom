package com.servlet;
/**
 * 智能预定
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
		System.out.println("进入     SmartBookServlet");
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
	        System.out.println(json1);//得到的是JSON格式
	        JSONObject jsonObject = JSONObject.fromObject(json1.toString());
            String Hours=jsonObject.getString("Hours");//获取预计开会时长
            //int hours=Integer.parseInt(Hours);
	        String Size1 = jsonObject.getString("Size");//获取参会人数
	        String Functions = jsonObject.getString("Functions");//获取功能
	        String Token = jsonObject.getString("Token");//获取Token
	        if(CompareToken.selectToken(Token)) {
	        	//Token存在
	        	  String istoken=TokenUtil.verificationToken(Token);
	         if(istoken.equals("成功")){
	        	 //Token符合条件
	        	 //对人数进行数字异常检测
	        	 FilterManage check1 = new FilterManage();
	             check1.addChecker(new intChecker());
	             if(check1.doChain_check(Size1)) {
	            	 //不存在异常
	            	 int Size = Integer.parseInt(jsonObject.getString("Size"));
	        	 if(Functions.equals("")&&Size1.length()!=0&&Hours.equals(""))
	        	 {
	        		 //输入人数限制，返回满足容量房间信息
	        	 List<RoomMessage> data=SmartBook.selectSizeRoomInfo(Size);
	        
	           if(data!=null) {
	        	  JSONArray jsonArray=JSONArray.fromObject(data);
	        	String data1=jsonArray.toString();
	        	System.out.println("这里的data1    "+data1.toString());
	        	
	        	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

	            if(json.size()>0){
	                for(int i=0;i<json.size();i++){
	                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
	                    net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//	                    System.out.println("这里的解析的    "+jsonObj);
	                }}
	            System.out.println("第一行    "+json.getJSONObject(1));
	            response.setContentType("text/html;charset=utf-8");
	            PrintWriter out = response.getWriter();
	            out.write(data1.toString());
	            out.flush();
	            out.close();
	            }
	           
	           else {
                       //普通失败，返回数组-1
	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
	            	  if(statusdata!=null) {
	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
	                	String data1=jsonArray.toString();
	                	System.out.println("这里的data1    "+data1.toString());
	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
	                    if(json.size()>0){
	                        for(int i=0;i<json.size();i++){
	                            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//	                            System.out.println("这里的解析的    "+jsonObj);
	                        }
	                        }
	                    System.out.println("第一行    "+json.getJSONObject(1));
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
	        	 System.out.println("时长容量为空");
	        		 //输入功能要求，返回满足功能房间信息
	        	 List<RoomMessage> data=SmartBook.selectFunctionsRoomInfo(Functions);
	  	           if(data!=null) {
	  	        	  JSONArray jsonArray=JSONArray.fromObject(data);
	  	        	String data1=jsonArray.toString();
	  	        	System.out.println("这里的data1    "+data1.toString()); 	
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
	  	           
	  	           else {
                         //普通失败，返回数组-1
	  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
	  	            	  if(statusdata!=null) {
	  	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
	  	                	String data1=jsonArray.toString();
	  	                	System.out.println("这里的data1    "+data1.toString());
	  	                	
	  	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

	  	                    if(json.size()>0){
	  	                        for(int i=0;i<json.size();i++){
	  	                            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
	  	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//	  	                            System.out.println("这里的解析的    "+jsonObj);
	  	                        }
	  	                        }
	  	                    System.out.println("第一行    "+json.getJSONObject(1));
	  	                    response.setContentType("text/html;charset=utf-8");
	  	                    PrintWriter out = response.getWriter();
	  	                    out.write(data1.toString());
	  	                    out.flush();
	  	                    out.close();
	  	                    }
	        	 }
	        	 }
	        	else if(Hours.length()!=0&&Size1.equals("")&&Functions.equals("")) {
	        		 //输入功能要求和人数要求，返回满足两个条件的信息
	        		System.out.println("容量功能为空");
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
		  	        	System.out.println("这里的data1    "+data1.toString());
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
		  	           
		  	           else {
                          //普通失败，返回数组-1
		  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
		  	            	  if(statusdata!=null) {
		  	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
		  	                	String data1=jsonArray.toString();
		  	                	System.out.println("这里的data1    "+data1.toString());
		  	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	                    if(json.size()>0){
		  	                        for(int i=0;i<json.size();i++){
		  	                            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
		  	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                        }
		  	                        }
		  	                    System.out.println("第一行    "+json.getJSONObject(1));
		  	                    response.setContentType("text/html;charset=utf-8");
		  	                    PrintWriter out = response.getWriter();
		  	                    out.write(data1.toString());
		  	                    out.flush();
		  	                    out.close();
		  	                    }
		        	 }
	        	 }
	        	 else if(Size1.length()!=0&&Functions.length()!=0&&Hours.equals("")) {
	        		 //输入功能要求和人数要求，返回满足两个条件的信息
	        		 System.out.println("时长为空");
	        		 List<RoomMessage> data=SmartBook.selectDoubleRoomInfo(Functions, Size);
		  	           if(data!=null) {
		  	        	  JSONArray jsonArray=JSONArray.fromObject(data);
		  	        	String data1=jsonArray.toString();
		  	        	System.out.println("这里的data1    "+data1.toString());
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
		  	           
		  	           else {
                          //普通失败，返回数组-1
		  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
		  	            	  if(statusdata!=null) {
		  	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
		  	                	String data1=jsonArray.toString();
		  	                	System.out.println("这里的data1    "+data1.toString());
		  	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	                    if(json.size()>0){
		  	                        for(int i=0;i<json.size();i++){
		  	                            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
		  	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                        }
		  	                        }
		  	                    System.out.println("第一行    "+json.getJSONObject(1));
		  	                    response.setContentType("text/html;charset=utf-8");
		  	                    PrintWriter out = response.getWriter();
		  	                    out.write(data1.toString());
		  	                    out.flush();
		  	                    out.close();
		  	                    }
		        	 }
	        	 }
	        	 else if(Size1.equals("")&&Functions.length()!=0&&Hours.length()!=0) {
	        		 //输入功能要求和人数要求，返回满足两个条件的信息
	        		 System.out.println("容量为空");
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
		  	        	System.out.println("这里的data1    "+data1.toString());
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
		  	           
		  	           else {
                          //普通失败，返回数组-1
		  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
		  	            	  if(statusdata!=null) {
		  	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
		  	                	String data1=jsonArray.toString();
		  	                	System.out.println("这里的data1    "+data1.toString());
		  	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	                    if(json.size()>0){
		  	                        for(int i=0;i<json.size();i++){
		  	                            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
		  	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                        }
		  	                        }
		  	                    System.out.println("第一行    "+json.getJSONObject(1));
		  	                    response.setContentType("text/html;charset=utf-8");
		  	                    PrintWriter out = response.getWriter();
		  	                    out.write(data1.toString());
		  	                    out.flush();
		  	                    out.close();
		  	                    }
		        	 }
	        	 }
	        	 else if(Size1.length()!=0&&Functions.equals("")&&Hours.length()!=0) {
	        		 //输入功能要求和人数要求，返回满足两个条件的信息
	        		 System.out.println("功能为空");
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
		  	        	System.out.println("这里的data1    "+data1.toString());
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
		  	           
		  	           else {
                          //普通失败，返回数组-1
		  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
		  	            	  if(statusdata!=null) {
		  	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
		  	                	String data1=jsonArray.toString();
		  	                	System.out.println("这里的data1    "+data1.toString());
		  	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	                    if(json.size()>0){
		  	                        for(int i=0;i<json.size();i++){
		  	                            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
		  	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                        }
		  	                        }
		  	                    System.out.println("第一行    "+json.getJSONObject(1));
		  	                    response.setContentType("text/html;charset=utf-8");
		  	                    PrintWriter out = response.getWriter();
		  	                    out.write(data1.toString());
		  	                    out.flush();
		  	                    out.close();
		  	                    }
		        	 }
	        	 }
	        	 else if(Size1.length()!=0&&Functions.length()!=0&&Hours.length()!=0) {
	        		 //输入功能要求，人数要求，时长要求，返回满足三个条件的信息
	        		 System.out.println("均不为空");
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
		  	        	System.out.println("这里的data1    "+data1.toString());
		  	        	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	            if(json.size()>0){
		  	                for(int i=0;i<json.size();i++){
		  	                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
		  	                    net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                }
		  	            
		  	            response.setContentType("text/html;charset=utf-8");
		  	            PrintWriter out = response.getWriter();
		  	            out.write(data1.toString());
		  	            out.flush();
		  	            out.close();
		  	            }
		  	          else {
                          //普通失败，返回数组-1
		  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
		  	            	  if(statusdata!=null) {
		  	                	  JSONArray jsonArray1=JSONArray.fromObject(statusdata);
		  	                	String data11=jsonArray1.toString();
		  	                	System.out.println("这里的data11    "+data11.toString());
		  	                	net.sf.json.JSONArray json11=net.sf.json.JSONArray.fromObject(data11.toString());
		  	                    if(json11.size()>0){
		  	                        for(int i=0;i<json11.size();i++){
		  	                            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
		  	                            net.sf.json.JSONObject jsonObj = json11.getJSONObject(i);
		  	                        }
		  	                        }
		  	                    System.out.println("第一行    "+json11.getJSONObject(1));
		  	                    response.setContentType("text/html;charset=utf-8");
		  	                    PrintWriter out = response.getWriter();
		  	                    out.write(data11.toString());
		  	                    out.flush();
		  	                    out.close();
		  	                    }
		  	          }
		  	           }
		  	           
		  	           else {
                          //普通失败，返回数组-1
		  	            	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus1();
		  	            	  if(statusdata!=null) {
		  	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
		  	                	String data1=jsonArray.toString();
		  	                	System.out.println("这里的data1    "+data1.toString());
		  	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
		  	                    if(json.size()>0){
		  	                        for(int i=0;i<json.size();i++){
		  	                            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
		  	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
		  	                        }
		  	                        }
		  	                    System.out.println("第一行    "+json.getJSONObject(1));
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
	            	 //输入房间信息不合法
	            	 List<RoomMessage> statusdata=SmartBook.selectConditionStatus2();
 	            	  if(statusdata!=null) {
 	                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
 	                	String data1=jsonArray.toString();
 	                	System.out.println("这里的data1    "+data1.toString());
 	                	
 	                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

 	                    if(json.size()>0){
 	                        for(int i=0;i<json.size();i++){
 	                            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
 	                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
// 	                            System.out.println("这里的解析的    "+jsonObj);
 	                        }
 	                        }
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
                  //Token失效，放回数组-2
	       	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus3();
	       	  if(statusdata!=null) {
	           	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
	           	String data1=jsonArray.toString();
	           	System.out.println("这里的data1    "+data1.toString());
	           	
	           	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

	               if(json.size()>0){
	                   for(int i=0;i<json.size();i++){
	                       // 遍历 jsonarray 数组，把每一个对象转成 json 对象
	                       net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//	                       System.out.println("这里的解析的    "+jsonObj);
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
           //    Token 为空，返回数组-3
	        	  List<RoomMessage> statusdata=SmartBook.selectConditionStatus3();
	        	  if(statusdata!=null) {
	            	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
	            	String data1=jsonArray.toString();
	            	System.out.println("这里的data1    "+data1.toString());
	            	
	            	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());

	                if(json.size()>0){
	                    for(int i=0;i<json.size();i++){
	                        // 遍历 jsonarray 数组，把每一个对象转成 json 对象
	                        net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//	                        System.out.println("这里的解析的    "+jsonObj);
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
