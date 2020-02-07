package com.servlet;
/*
 * 新增会议室的servlet
 * 客户端传入的参数 Token Time BuildNumber RoomNumber Size Function MeetingRoomLevel 
 * 详细传入参数解释参照工作笔记
 * TokenUtil.verificationToken(Token);	 解密token并且调用com.tools 下该方法判断传入token是否有效
 * 调用 ) 进行unicode解码
 * 
 * 返回客户端参数 "{\"status\":\"-1\"}" / "{\"status\":\"0\"}"
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
	String isError;//isError 判断出错信息
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 	response.setContentType("text/plain;charset=utf-8"); 
		 	/*设置字符集为'UTF-8'*/
	        request.setCharacterEncoding("utf-8");
	      	System.out.println("连接成功反馈");// 测试是否成功连接
	        StringBuffer json = new StringBuffer();// 字符流
	        String line = null;
	        BufferedReader reader = request.getReader();// 读取流
	        while ((line = reader.readLine()) != null) {
	            json.append(line);// 接受的是JSON格式
	        }
	        System.out.println(json);//得到的是JSON格式
	        JSONObject jsonObject = JSONObject.fromObject(json.toString());
	        /*通过键接收值*/
	        String Token = jsonObject.getString("Token");//获取Token
	       
	        String BuildingNumber = jsonObject.getString("BuildingNumber");//unicode 解码楼号
	        String RoomNumber = jsonObject.getString("RoomNumber");//获取房间号
	        String Function =  jsonObject.getString("Function");//解码获取功能
	        String MeetingRoomLevel = jsonObject.getString("MeetingRoomLevel");	////解码获取会议室等级       
	        String istoken=TokenUtil.verificationToken(Token);//token解码检查	    
	        if(CompareToken.selectToken(Token)) {
	        	//如果数据库中存在Token，执行操作
	        if(istoken.equals("成功"))
	        {
	        	/**
	        	 * 获取插入状态,对楼号，房间号，时间段，进行命令注入检查
	        	 * 对容量限制只能是数字
	        	 */
	        	/*通过Token查询员工号*/
            	String EmployeeNumber=CompareToken.selectEmployeeNumberByToken(Token);
            	
            	
            	/*通过员工号查询员工等级*/
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
	     			//如果合法
	     			System.out.println("输入信息合法");
	     			int Size = Integer.parseInt(jsonObject.getString("Size"));
	     			
	     			if(!Add.CompareRoom(BuildingNumber,RoomNumber))
	     			{
	     				//如果没有此房间信息，允许插入
	     			String Days0=null,Days1=null,Days2=null;
					try {
						/**
						 * 获取当天，明天，后天的日期
						 */
						Days0 = Dateadd.mydays(dateToString.nowdateToString(), 0);
						Days1 = Dateadd.mydays(dateToString.nowdateToString(), 1);
						Days2 = Dateadd.mydays(dateToString.nowdateToString(), 2);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/**
					 * 插入今明后三天时间的房间信息
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
	            	//增加房间信息成功
	            	System.out.println("新增房间成功");
	            response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
	            String  info_json  = "{\"status\":\"0\"}";//成功返回状态0
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
	            }
	            
	            else
	            {
	            	System.out.println("新增房间失败");
	            	response.setContentType("text/html;charset=utf-8");
		        	PrintWriter out = response.getWriter();  
		            String  info_json  = "{\"status\":\"-1\"}";//普通失败
		            System.out.println(info_json);
		            out.write(info_json);
		            out.flush();
		            out.close(); 
	            }
	     			}
	     			else
	     			{
	     				System.out.println("房间重复");
		            	response.setContentType("text/html;charset=utf-8");
			        	PrintWriter out = response.getWriter();  
			            String  info_json  = "{\"status\":\"-4\"}";//房间重复返回状态-4
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
		            String  info_json  = "{\"status\":\"-2\"}";//增加信息不合法返回账户-2
		            System.out.println(info_json);
		            out.write(info_json);
		            out.flush();
		            out.close(); 
	     		}
            	}
            	else {
            		response.setContentType("text/html;charset=utf-8");
		        	PrintWriter out = response.getWriter();  
		            String  info_json  = "{\"status\":\"-5\"}";//不具有添加房间的权限
		            System.out.println(info_json);
		            out.write(info_json);
		            out.flush();
		            out.close(); 
            	}
	        }
	        else {
	        	
	        	//需要强制注销
            	System.out.println("token失效");
	        	response.setContentType("text/html;charset=utf-8");
		        PrintWriter out = response.getWriter();
		        String  info_json  = "{\"status\":\"-3\"}";//Token为空或Token失效， 重新登录
		        System.out.println(info_json);
		        out.write(info_json);
		        out.flush();
		        out.close();
	        }
	        }
	        else {
	        	
	        	//需要强制注销
            	System.out.println("token失效");
	        	response.setContentType("text/html;charset=utf-8");
		        PrintWriter out = response.getWriter();
		        String  info_json  = "{\"status\":\"-3\"}";//Token为空或Token失效， 重新登录
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
