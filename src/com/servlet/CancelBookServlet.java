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
		String fail="失败";

		response.setContentType("text/html;charset=utf-8");
		/*设置字符集为'UTF-8'*/
        request.setCharacterEncoding("utf-8");
        System.out.println("连接成功反馈");// 测试是否成功连接
        StringBuffer json11 = new StringBuffer();// 字符流
        String line = null;
        BufferedReader reader = request.getReader();// 读取流
        while ((line = reader.readLine()) != null) {
            json11.append(line);// 接受的是JSON格式
        }

        System.out.println(json11);//得到的是JSON格式
        JSONObject jsonObject = JSONObject.fromObject(json11.toString());
       
        String Token = jsonObject.getString("Token");//获取Token        
		String Time = jsonObject.getString("Time");//解码获取时间段
		String RoomNumber = jsonObject.getString("RoomNumber");//获取房间号
		String BuildingNumber = jsonObject.getString("BuildingNumber");//解码获取房间号
	    String Days = jsonObject.getString("Days");//解码获取日期
		System.out.println("传入的token值："+Token);
		System.out.println("传入的Days值："+Days);
      
        
        if(CompareToken.selectToken(Token)) {
        	//如果Token存在
        	  String istoken=TokenUtil.verificationToken(Token);//token解码检查
		if(istoken.equals("成功")){
			boolean CancelBook=com.services.CancelBook.selectReserveRoom(Time, BuildingNumber, RoomNumber, Days);
			if(CancelBook) {
			boolean Cancel=BookRoom.UpdateRoomInfoIsMeeting0(Time, BuildingNumber, RoomNumber, Days);
			BookRoom.deleteReserveRoom(Time, BuildingNumber, RoomNumber,Days);
			System.out.println("预定房间信息已清除");
			if(Cancel)
			{
				response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();  
	        	tojson<String, String> json=new tojson<>();
	           json.put("Status", "0");//成功返回状态0
	            System.out.println("0  "+json.toString());
	            out.write(json.toString());
	            out.flush();
	            out.close(); 
			}
			else {
				//失败
            	response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          封装成JSON格式发送回客户端       
	         //   String  info_json  = "{\"status\":\"-1\"}";//组装json格式的字符串来传送
	        	tojson<String, String> info_json=new tojson<>();
	        	info_json.put("Status", "-1");//普通失败
	            System.out.println("1  "+info_json.toString());
	            out.write(info_json.toString());
	            out.flush();
	            out.close(); 	
			}
			}
			else {
				//失败
            	response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          封装成JSON格式发送回客户端       
	         //   String  info_json  = "{\"status\":\"-1\"}";//组装json格式的字符串来传送
	        	tojson<String, String> info_json=new tojson<>();
	        	info_json.put("Status", "-4");//普通失败
	            System.out.println("1  "+info_json.toString());
	            out.write(info_json.toString());
	            out.flush();
	            out.close(); 
			}
			}
		else {
        	//Token失效
        	response.setContentType("text/html;charset=utf-8");
        	PrintWriter out = response.getWriter();
//          封装成JSON格式发送回客户端       
        	tojson<String, String> info_json=new tojson<>();
        	info_json.put("Status", "-3");//token失效，返回-3
        	info_json.put("EmployeeNumber", fail);
        	info_json.put("Name", fail);
            System.out.println("3  "+info_json.toString());
            out.write(info_json.toString());
            out.flush();
            out.close(); 
        }
		
		}
        else {
        	//Token为空
        	response.setContentType("text/html;charset=utf-8");
        	PrintWriter out = response.getWriter();
//          封装成JSON格式发送回客户端       
        	tojson<String, String> info_json=new tojson<>();
        	info_json.put("Status", "-3");//Token为空，返回状态-3
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
