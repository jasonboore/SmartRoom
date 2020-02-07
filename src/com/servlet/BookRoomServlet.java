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
 * 预定会议室
 */

public class BookRoomServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			//如果Token符合条件
			//查询会议室是否为空
			String MeetingStatus = BookRoom.selectMeetingStatus(Time, BuildingNumber, RoomNumber);
            if(MeetingStatus.equals("0"))
            {
            	//如果会议室为空闲状态
            	
            	/*通过Token查询员工号*/
            	String EmployeeNumber=CompareToken.selectEmployeeNumberByToken(Token);
            	/*通过员工号查询姓名*/
            	String Name=CompareToken.selectNameByEmployeeNumber(EmployeeNumber);
            	/*通过员工号查询员工等级*/
            	String AdminPower=Login.selectadminpower(EmployeeNumber);
            	/*查询会议室等级*/
            	String MeetingRoomLevel=BookRoom.selectMeetingRoomLevel(Time, BuildingNumber, RoomNumber,Days);
            	
            	if(Integer.parseInt(AdminPower)>=Integer.parseInt(MeetingRoomLevel)) {
            		//如果员工等级大于会议室等级，可以预订此会议室
            		boolean IsSuccess=BookRoom.BookRoom(Time, Name, RoomNumber, BuildingNumber,Days,EmployeeNumber);
                if(IsSuccess)
                {
                	//修改会议室状态
                	BookRoom.UpdateRoomInfoIsMeeting(Time, BuildingNumber, RoomNumber,Days);
                    /**
                     * 启用定时线程，到会议结束时间会议室状态自动置为1
                     */
                	InTimeSet0 I0=new InTimeSet0();
                    I0.SetDays(Days);
                    I0.SetEndTime(Time.substring(6)+":00");
                    I0.setRoomNumber(RoomNumber);
                    I0.setTime(Time);
                    I0.setBuildingNumber(BuildingNumber);
                	Thread th0=new Thread(I0);
                	System.out.println("set0已执行");
                	th0.start();
                    System.out.println("进入进程");
                	response.setContentType("text/html;charset=utf-8");
                	
    	        	PrintWriter out = response.getWriter();  
    	        	tojson<String, String> json=new tojson<>();
    	           json.put("Status", "0");//成功返回状态0
    	           json.put("EmployeeNumber", EmployeeNumber);//返回员工号
    	           json.put("Name", Name);//返回姓名
    	            System.out.println("0  "+json.toString());
    	            out.write(json.toString());
    	            out.flush();
    	            out.close(); 
                }
                else
                {
                	//失败
                	response.setContentType("text/html;charset=utf-8");
    	        	PrintWriter out = response.getWriter();
//    	          封装成JSON格式发送回客户端       
    	         //   String  info_json  = "{\"status\":\"-1\"}";//组装json格式的字符串来传送
    	        	tojson<String, String> info_json=new tojson<>();
    	        	info_json.put("Status", "-1");//普通失败
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
            		 //权限不足	
                 	response.setContentType("text/html;charset=utf-8");
     	        	PrintWriter out = response.getWriter();
//     	          封装成JSON格式发送回客户端       
     	         //   String  info_json  = "{\"status\":\"-1\"}";//组装json格式的字符串来传送
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
            	//房间状态为被占用
            	response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          封装成JSON格式发送回客户端       
	        	tojson<String, String> info_json=new tojson<>();
	        	info_json.put("Status", "-6");//该房间已被预订，返回-6
	        	info_json.put("EmployeeNumber", fail).toString();
	        	info_json.put("Name", fail).toString();
	            System.out.println("2  "+info_json.toString());
	            out.write(info_json.toString());
	            out.flush();
	            out.close(); 
            }
            else if(MeetingStatus.equals("2"))
            {
               //房间状态为维修
            	response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          封装成JSON格式发送回客户端       
	        	tojson<String, String> info_json=new tojson<>();
	        	info_json.put("Status", "-7");//房间维修，返回状态-7
	        	info_json.put("EmployeeNumber", fail).toString();
	        	info_json.put("Name", fail).toString();
	            System.out.println("2  "+info_json.toString());
	            out.write(info_json.toString());
	            out.flush();
	            out.close(); 
            }
            else
            {
            	//失败
            	response.setContentType("text/html;charset=utf-8");
	        	PrintWriter out = response.getWriter();
//	          封装成JSON格式发送回客户端       
	        	tojson<String, String> info_json=new tojson<>();
	        	info_json.put("Status", "-1");//普通失败
	        	info_json.put("EmployeeNumber", fail).toString();
	        	info_json.put("Name", fail).toString();
	            System.out.println("2  "+info_json.toString());
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
   
	@Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 

		doPost(request, response);
    }


}
