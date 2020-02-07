package com.servlet;
/*
 * 修改会议室    客户端 传入参数 41-47 
 * 返回客户端 0/-1 状态
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
	        // System.out.println(json1.toString());//得到的是字符串
	        // 把得到的字符串封装为JSON，再获取里面的传过来用户名
	        JSONObject jsonObject = JSONObject.fromObject(json.toString());
	       
	        String Token = jsonObject.getString("Token");//获取Token
	        //String Time = jsonObject.getString("Time");//解码获取时间段
	        String BuildingNumber = jsonObject.getString("BuildingNumber");//解码获取楼号
	        String RoomNumber = jsonObject.getString("RoomNumber");//解码获取房间号
	        String Function =  jsonObject.getString("Function");//解码获取功能
	        String MeetingRoomLevel = jsonObject.getString("MeetingRoomLevel");//解码获取会议室等级
	        String IsMeeting=jsonObject.getString("IsMeeting");//获取房间状态
	        String istoken=TokenUtil.verificationToken(Token);//解密token
	        if(CompareToken.selectToken(Token)) {
	       //如果Token存在
	        if(istoken.equals("成功"))
	        {
	        	//Token符合条件
	        	/*通过Token查询员工号*/
	        	String EmployeeNumber=CompareToken.selectEmployeeNumberByToken(Token);
	        	
	        	
	        	/*通过员工号查询员工等级*/
	        	String AdminPower=Login.selectadminpower(EmployeeNumber);
	        	if(Integer.parseInt(AdminPower)>=2)
	        	{
	        	//对容量进行数字异常检测
	        	 FilterManage check2 = new FilterManage();
	             check2.addChecker(new intChecker());
	     		if((check2.doChain_check(jsonObject.getString("Size"))))
	        	{
	     			//符合数字条件
	     			System.out.println("输入信息合法");
	     			int Size = Integer.parseInt(jsonObject.getString("Size"));
	     			String IsSuccess;
	     			 if(IsMeeting.equals("2"))
	 	            {
	     				 //修改会议室维修或容量或功能
	 	            	IsSuccess=Change.UpdateReserveRoomIsMeeting( BuildingNumber, RoomNumber, Size, Function, MeetingRoomLevel, IsMeeting);
	 	            }
	     			 else {
	     				 //修改会议室容量或功能
	        	 IsSuccess = Change.UpdateReserveRoom( BuildingNumber, RoomNumber, Size, Function, MeetingRoomLevel);
	     			 		}
	        	 System.out.println(IsSuccess);
	            if(IsSuccess.equals("success"))
	            {
	            	//修改成功
	            response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          封装成JSON格式发送回客户端       
	            String  info_json  = "{\"status\":\"0\"}";//成功返回状态0
	            System.out.println(info_json);
	            out.write(info_json);
	            out.flush();
	            out.close(); 
	            }
	            else {
	            	//Token失效， 重新登录
	            	response.setContentType("text/html;charset=utf-8");
	    	        PrintWriter out = response.getWriter();
	    	        String  info_json  = "{\"status\":\"-1\"}";//普通失败
	    	        System.out.println(info_json);
	    	        System.out.println("数据库中token不合法 ");
	    	        out.write(info_json);
	    	        out.flush();
	    	        out.close();
	            }
	            
	        	}
	     		else {
	     			//房间容量输入非法
	     		response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();  
	            String  info_json  = "{\"status\":\"-2\"}";//返回状态-2，房间容量输入非法
	            System.out.println(info_json);
	            System.out.println("SIZE非法");
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
	        	//失败
	        	response.setContentType("text/html;charset=utf-8");
		        PrintWriter out = response.getWriter();
//		      封装成JSON格式发送回客户端       
		        String  info_json  = "{\"status\":\"-1\"}";//普通失败
		        System.out.println(info_json);
		        out.write(info_json);
		        out.flush();
		        out.close();	        
	        }
	        }
	        else{//如果本地数据库token不存在
	  		   //需要做强制注销 重新登陆   		   
	  		    response.setContentType("text/html;charset=utf-8");      	   
	 	        PrintWriter out = response.getWriter();     
	 	        String  info_json  = "{\"status\":\"-3\"}";//Token不存在，重新登录
	 	        System.out.println(info_json);
	 	        System.out.println("数据库中token不存在 ");
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
