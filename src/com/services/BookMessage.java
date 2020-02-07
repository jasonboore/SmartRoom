package com.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.ConnDb;
import com.tools.BookMsg;
import com.tools.RoomMessage;
import com.tools.Timer;

public class BookMessage {
	 public static  List<BookMsg> selectReserveRoom(String EmployeeNumber){
	    	ArrayList<BookMsg> data;
	        data=new ArrayList<BookMsg>();
	        String sql = "select * from reserveroom where EmployeeNumber = '"+EmployeeNumber+"'";
	        ConnDb connDb = new ConnDb();
	        try {
//	            执行SQL语句
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            
	            while(rs.next()) {
	            	BookMsg msg=new BookMsg();
	            	msg.setTime(rs.getString("Time"));
	            	msg.setBuildingNumber(rs.getString("BuildingNumber"));
	            	msg.setRoomNumber(rs.getString("RoomNumber"));
	            	String Now=rs.getString("ReserveTime");
	            	String nowTime=Timer.stampToDate(Now);
	            	msg.setNowTime(nowTime);
	            	msg.setDays(rs.getString("Days"));
	            	data.add(msg);

	            }
	            System.out.println("while里的    "+data.toString());
	            	//System.out.println("data1   "+data.get(0).getBuildingNumber());
	            	  ps.close();
	                return data;
	        } catch (SQLException e) {
	         
	            e.printStackTrace();
	            System.out.println("执行SQL语句出错！");
	        }
	        return null;
	    }
	 public static  List<BookMsg> selectStatus1(){
	    	ArrayList<BookMsg> data;
	        data=new ArrayList<BookMsg>();
	        int i=3;
	        while(i>1) {
	        	BookMsg msg=new BookMsg();
	            	msg.setTime("-1");
	            	msg.setBuildingNumber("-1");
	            	msg.setRoomNumber("-1");
	            	msg.setNowTime("-1");
	            	msg.setDays("-1");
	            	data.add(msg);
	            	i--;
	        }
	                System.out.println("selectStatus() 生成msg里的    "+data.toString());
	            	//System.out.println("data1   "+data.get(0).getBuildingNumber());
	                return data;
	       
	    }
	    
	 public static  List<BookMsg> selectStatus3(){
	    	ArrayList<BookMsg> data;
	        data=new ArrayList<BookMsg>();
	        int i=3;
	        while(i>1) {
	        	BookMsg msg=new BookMsg();
	            	msg.setTime("-3");
	            	msg.setBuildingNumber("-3");
	            	msg.setRoomNumber("-3");
	            	msg.setNowTime("-3");
	            	msg.setDays("-3");
	            	data.add(msg);
	            	i--;
	        }
	                System.out.println("selectStatus() 生成msg里的    "+data.toString());
	            	//System.out.println("data1   "+data.get(0).getBuildingNumber());
	                return data;
	       
	    }
}
