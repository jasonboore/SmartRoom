package com.services;

/*
 * 查询房间信息
 * selectRoomInfo()  从roominfo表中查询房间信息
 * selectStatus1()  置返货房间状态数组为 -1
 * selectStatus3()  置返货房间状态数组为 -3
 * 
 * 
 * */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.spi.DirStateFactory.Result;
import com.db.ConnDb;
import com.tools.RoomMessage;
import com.tools.tounicode;

public class Search {

    public static  List<RoomMessage> selectRoomInfo(String Days,String Time){
    	ArrayList<RoomMessage> data,data1;
        data=new ArrayList<RoomMessage>();
        String sql=null;
        switch (Time) {
		case "08:00-08:45":
			  sql = "SELECT * FROM roominfo  where Time not in('08:00-08:45') or Days not in('"+Days+"')"+" ORDER BY BuildingNumber,RoomNumber,Days,Time";
			break;
		case "09:00-09:45":
			  sql = "SELECT * FROM roominfo  where Time not in('08:00-08:45','09:00-09:45') or Days not in('"+Days+"')"+" ORDER BY BuildingNumber,RoomNumber,Days,Time";
			break;
		case "10:00-10:45":
			  sql = "SELECT * FROM roominfo  where Time not in('08:00-08:45','09:00-09:45','10:00-10:45') or Days not in('"+Days+"')"+" ORDER BY BuildingNumber,RoomNumber,Days,Time";
			break;
		case "11:00-11:45":
			  sql = "SELECT * FROM roominfo  where Time not in('08:00-08:45','09:00-09:45','10:00-10:45','11:00-11:45') or Days not in('"+Days+"')"+" ORDER BY BuildingNumber,RoomNumber,Days,Time";
			break;
		case "13:00-13:45":
			sql = "SELECT * FROM roominfo  where Time not in('08:00-08:45','09:00-09:45','10:00-10:45','11:00-11:45','13:00-13:45') or Days not in('"+Days+"')"+" ORDER BY BuildingNumber,RoomNumber,Days,Time";
			break;
				
		case "14:00-14:45":
			sql = "SELECT * FROM roominfo  where Time not in('08:00-08:45','09:00-09:45','10:00-10:45','11:00-11:45','13:00-13:45','14:00-14:45') or Days not in('"+Days+"')"+" ORDER BY BuildingNumber,RoomNumber,Days,Time";			
			break;
		case "15:00-15:45":
			sql = "SELECT * FROM roominfo  where Time not in('08:00-08:45','09:00-09:45','10:00-10:45','11:00-11:45','13:00-13:45','14:00-14:45','15:00-15:45') or Days not in('"+Days+"')"+" ORDER BY BuildingNumber,RoomNumber,Days,Time";					
			break;
		
		}
       
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            
            while(rs.next()) {
            	RoomMessage msg=new RoomMessage();
            	
            	msg.setTime(rs.getString("Time"));
            	msg.setBuildingNumber(rs.getString("BuildingNumber"));
            	msg.setRoomNumber(rs.getString("RoomNumber"));
            	int Size = rs.getInt("Size");
            	String Size1=String.valueOf(Size);
            	msg.setSize(Size1);
            	msg.setFunctions(rs.getString("Functions"));
            	msg.setIsMeeting( rs.getString("IsMeeting"));
            	msg.setDays(rs.getString("Days"));
            	data.add(msg);
            	
                
            }
            System.out.println("while里的    "+data.toString());
            	System.out.println("data1   "+data.get(1).getBuildingNumber());
            	  ps.close();
                return data;
        } catch (SQLException e) {
         
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
        }
        return null;
    }
    public static  List<RoomMessage> selectRoomInfo0(){
    	ArrayList<RoomMessage> data,data1;
        data=new ArrayList<RoomMessage>();
       
			
			
        String sql = "SELECT * FROM roominfo ORDER BY BuildingNumber,RoomNumber,Days,Time";
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            
            while(rs.next()) {
            	RoomMessage msg=new RoomMessage();
            	
            	msg.setTime(rs.getString("Time"));
            	msg.setBuildingNumber(rs.getString("BuildingNumber"));
            	msg.setRoomNumber(rs.getString("RoomNumber"));
            	int Size = rs.getInt("Size");
            	String Size1=String.valueOf(Size);
            	msg.setSize(Size1);
            	msg.setFunctions(rs.getString("Functions"));
            	msg.setIsMeeting( rs.getString("IsMeeting"));
            	msg.setDays(rs.getString("Days"));
            	data.add(msg);
            	
                
            }
            System.out.println("while里的    "+data.toString());
            	System.out.println("data1   "+data.get(1).getBuildingNumber());
            	  ps.close();
                return data;
        } catch (SQLException e) {
         
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
        }
        return null;
    }
    public static  List<RoomMessage> selectRoomInfo9(String Days){
    	ArrayList<RoomMessage> data,data1;
        data=new ArrayList<RoomMessage>();
	
        String sql = "select * from roominfo where Days<>'"+Days+"'"+" ORDER BY BuildingNumber,RoomNumber,Days,Time";
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            
            while(rs.next()) {
            	RoomMessage msg=new RoomMessage();
            	
            	msg.setTime(rs.getString("Time"));
            	msg.setBuildingNumber(rs.getString("BuildingNumber"));
            	msg.setRoomNumber(rs.getString("RoomNumber"));
            	int Size = rs.getInt("Size");
            	String Size1=String.valueOf(Size);
            	msg.setSize(Size1);
            	msg.setFunctions(rs.getString("Functions"));
            	msg.setIsMeeting( rs.getString("IsMeeting"));
            	msg.setDays(rs.getString("Days"));
            	data.add(msg);
            	
                
            }
            System.out.println("while里的    "+data.toString());
            	System.out.println("data1   "+data.get(1).getBuildingNumber());
            	  ps.close();
                return data;
        } catch (SQLException e) {
         
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
        }
        return null;
    }
    public static  List<RoomMessage> selectStatus1(){
    	ArrayList<RoomMessage> data;
        data=new ArrayList<RoomMessage>();
        int i=3;
        while(i>1) {
            	RoomMessage msg=new RoomMessage();
            	msg.setTime("-1");
            	msg.setBuildingNumber("-1");
            	msg.setRoomNumber("-1");
            	msg.setSize("-1");
            	msg.setFunctions("-1");
            	msg.setIsMeeting("-1");
            	data.add(msg);
            	i--;
        }
                System.out.println("selectStatus() 生成msg里的    "+data.toString());
            	System.out.println("data1   "+data.get(0).getBuildingNumber());
                return data;
       
    }
    
    public static  List<RoomMessage> selectStatus3(){
    	ArrayList<RoomMessage> data;
        data=new ArrayList<RoomMessage>();
        int i=3;
        while(i>1) {
            	RoomMessage msg=new RoomMessage();
            	msg.setTime("-3");
            	msg.setBuildingNumber("-3");
            	msg.setRoomNumber("-3");
            	msg.setSize("-3");
            	msg.setFunctions("-3");
            	msg.setIsMeeting("-3");
            	data.add(msg);
            	i--;
        }
                System.out.println("selectStatus() 生成msg里的    "+data.toString());
            	System.out.println("data1   "+data.get(0).getBuildingNumber());
                return data;
       
    }
    public static  List<RoomMessage> selectAllRoomInfoBR(){
    	ArrayList<RoomMessage> data,data1;
        data=new ArrayList<RoomMessage>();
       
			
			
        String sql = "select * from roominfo ";
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            
            while(rs.next()) {
            	RoomMessage msg=new RoomMessage();
            	msg.setBuildingNumber(rs.getString("BuildingNumber"));
            	msg.setRoomNumber(rs.getString("RoomNumber"));
            	data.add(msg);
            	
                
            }
            System.out.println("while里的    "+data.toString());
            	System.out.println("data1   "+data.get(1).getBuildingNumber());
            	  ps.close();
                return data;
        } catch (SQLException e) {
         
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
        }
        return null;
    }
    
}