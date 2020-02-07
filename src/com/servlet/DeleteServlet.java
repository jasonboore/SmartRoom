package com.servlet;
/**
 * 删除会议室
 */
import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.services.Delete;
import com.services.Login;
import com.tools.CompareToken;
import com.tools.Dateadd;
import com.tools.TokenUtil;
import com.tools.dateToString;
import com.tools.tounicode;

import net.sf.json.JSONObject;

public class DeleteServlet extends HttpServlet {
	

	
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
        String Token = jsonObject.getString("Token");//获取Token
      //  String Time = jsonObject.getString("Time");//解码获取时间
        String BuildingNumber = jsonObject.getString("BuildingNumber");//解码获取楼号
        String RoomNumber = jsonObject.getString("RoomNumber");//获取房间号
       // String Days = jsonObject.getString("Days");//解码获取日期
//        System.out.println("传入的日期是：  "+Days);
        String istoken=TokenUtil.verificationToken(Token);//token判断
        if(CompareToken.selectToken(Token)) {
 		   //如果数据库存在Token
        if(istoken.equals("成功"))
        {
        	//如果Token合法，执行操作
        	/**
        	 * 删除房间信息
        	 */
        	/*通过Token查询员工号*/
        	String EmployeeNumber=CompareToken.selectEmployeeNumberByToken(Token);
        	
        	
        	/*通过员工号查询员工等级*/
        	String AdminPower=Login.selectadminpower(EmployeeNumber);
        	if(Integer.parseInt(AdminPower)>=2)
        	{
        	boolean RoomExist= Delete.RoomExist(BuildingNumber, RoomNumber);
        	if(RoomExist) //房间存在
        	{
        	boolean meeting= Delete.IsMeeting(BuildingNumber, RoomNumber);
        	if(meeting)//房间空闲
        	{
        	String IsSuccess = Delete.DeleteReserveRoom(BuildingNumber, RoomNumber);
            System.out.println(IsSuccess);
            if(IsSuccess.equals("success")) 
            {
            	//插入成功
            response.setContentType("text/html;charset=utf-8");
        	PrintWriter out = response.getWriter();
//          封装成JSON格式发送回客户端       
            String  info_json  = "{\"status\":\"0\"}";//成功返回0
            System.out.println(info_json);
            out.write(info_json);
            out.flush();
            out.close(); 
            }
            else
            {
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
        		response.setContentType("text/html;charset=utf-8");      	   
        	        PrintWriter out = response.getWriter();     
        	        String  info_json  = "{\"status\":\"-8\"}";//房间占用
        	        System.out.println(info_json);
        	        System.out.println("房间占用 ");
        	        out.write(info_json);
        	        out.flush();
        	        out.close();	
        	} 
        	}
        	else
        	{
        		response.setContentType("text/html;charset=utf-8");      	   
        	        PrintWriter out = response.getWriter();     
        	        String  info_json  = "{\"status\":\"-9\"}";//房间不存在
        	        System.out.println(info_json);
        	        System.out.println("房间不存在 ");
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
        	response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();
	        String  info_json  = "{\"status\":\"-3\"}";//Token失效， 重新登录
	        System.out.println(info_json);
	        System.out.println("数据库中token不合法 ");
	        out.write(info_json);
	        out.flush();
	        out.close();
        }
        }
        
        else {
        	//如果本地数据库token不存在
  		   //需要做强制注销 重新登陆   		   
  		    response.setContentType("text/html;charset=utf-8");      	   
 	        PrintWriter out = response.getWriter();     
 	        String  info_json  = "{\"status\":\"-3\"}";//Token为空， 重新登录
 	        System.out.println(info_json);
 	        System.out.println("数据库中token不存在 ");
 	        out.write(info_json);
 	        out.flush();
 	        out.close();		   
        }
        }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
}
}
