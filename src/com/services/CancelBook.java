package com.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConnDb;

public class CancelBook {
	 public static boolean selectReserveRoom(String Time,String BuildingNumber,String RoomNumber,String Days){
	        
	        String sql = "select * from reserveroom   where Time = '"+Time+"' and BuildingNumber='" + BuildingNumber+"' and RoomNumber = '"+RoomNumber+"' and Days = '"+Days+"'";
	        //查询该账户信息
	        ConnDb connDb = new ConnDb();
	        try {
//	            执行SQL语句
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            if (rs.next()) {
	            	ps.close();
	                return true;//返回正确密码
	            }else {
	            	ps.close();
	                return false;//没有此人信息。提示未注册
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("执行SQL语句出错！");
	        }
	        return false;
	    }
}
