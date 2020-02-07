package com.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConnDb;
/*
 * DeleteReserveRoom()  删除房间
 * Boolean RoomExist()  判断房间是否存在
 * Boolean IsMeeting()  判断会议室状态
 * */
public class Delete {
	public static String DeleteReserveRoom(String BuildingNumber,String RoomNumber){
        ConnDb connDb = new ConnDb();

            //添加增添的房间信息
            String sql = "delete from roominfo where "+" BuildingNumber='"
            				+BuildingNumber+"' and RoomNumber='"+RoomNumber+"'";
               System.out.println(sql);
               try {
               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
                    int rs =  ps.executeUpdate();
                    System.out.println("rs"+rs);
                    if(rs>0) {
                    	ps.close();
                    	return "success";
               }
                    else 
                    	{
                    	ps.close();
                    	return "ERROR";
                    	}
               }
               catch (SQLException e) {
                   e.printStackTrace();
                   System.out.println("ERROR");
                   return "ERROR";
               }             
               
	}     
	
	
	public static Boolean IsMeeting(String BuildingNumber,String RoomNumber){
		String meeting;
		boolean yn=false;
		String sql = "select * from roominfo where "+" BuildingNumber='"+BuildingNumber
				+"' and RoomNumber='"+RoomNumber+"'";
		System.out.println(sql);
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
              
            	meeting =rs.getString("IsMeeting");
            	System.out.println("会议室状态:"+meeting);
                if(meeting.trim().equals("1"))//占用
                {
                	ps.close();
                	yn=false;
                	break;
                	
                	
                }
                else if(meeting.trim().equals("2"))//维修
                {
                	ps.close();
                	yn=false;
                	break;
                	
                }
                else 
                	{
                	ps.close();
                	yn=true;//空闲
                	}
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
            
        }
		return yn;       
		
		
	}
	
	public static Boolean RoomExist(String BuildingNumber,String RoomNumber){
		String sql = "select * from roominfo where "+" BuildingNumber='"+BuildingNumber
				+"' and RoomNumber='"+RoomNumber+"'";
		System.out.println(sql);
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
            		ps.close();
            		return true;
            }
            else 
            	{
            	ps.close();
            	return false;
            	}
        } catch (SQLException e) {
            
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
            return false;
        }
	      
		
		
	}
        }

