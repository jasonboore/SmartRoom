package com.services;

/*
 * UpdateReserveRoom() 修改会议室 与数据库进行交互
 * UpdateReserveRoomIsMeeting() 修改房间状态
 * 
 * */
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.ConnDb;

public class Change {

	public static String UpdateReserveRoom(String BuildingNumber,String RoomNumber,int Size,String Function,String MeetingRoomLevel){
        String level=null;
        //根据最低职位判断等级
        switch (MeetingRoomLevel)
        {
        case "董事长" :
        	level ="4";
        break;
        
        case "总经理":
        	level ="3";
        break;
        case "部门经理":
        	level ="2";
        break;
        }
            ConnDb connDb = new ConnDb();

            //添加增添的房间信息
            String sql =  "update roominfo set Size = "+Size+", Functions = '"+Function
    				+"', MeetingRoomLevel = '"+level+"'"+"  where "
    				+" BuildingNumber='"+BuildingNumber+"' and RoomNumber = '"+RoomNumber+"'";
            
               try {
               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
                    int rs =  ps.executeUpdate();
                    if(rs>0) {
                    	ps.close();
                    	return "success";//修改成功
                    }
                    else
                    {ps.close();
                    	return "ERROR";}
               }
               catch (SQLException e) {
                   e.printStackTrace();
                   System.out.println("ERROR");
                   return "ERROR";
               }             
               
               }     
	public static String UpdateReserveRoomIsMeeting(String BuildingNumber,String RoomNumber,int Size,String Function,String MeetingRoomLevel,String IsMeeting){
        String level=null;
        //根据最低职位判断等级
        switch (MeetingRoomLevel)
        {
        case "董事长" :
        	level ="4";
        break;
        
        case "总经理":
        	level ="3";
        break;
        case "部门经理":
        	level ="2";
        break;
        }
            ConnDb connDb = new ConnDb();

            //添加增添的房间信息
            String sql =  "update roominfo set Size = "+Size+", Functions = '"+Function
    				+"', MeetingRoomLevel = '"+level+"' , IsMeeting = '"+IsMeeting+"'  where "
    				+"  BuildingNumber='"+BuildingNumber+"' and RoomNumber = '"+RoomNumber+"'";
            
               try {
               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
                    int rs =  ps.executeUpdate();
                    if(rs>0) {
                    	ps.close();
                    	return "success";//修改成功
                    }
                    else {ps.close();
                    	return "ERROR";}
               }
               catch (SQLException e) {
                   e.printStackTrace();
                   System.out.println("ERROR");
                   return "ERROR";
               }             
               
               }     
        }

