package com.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConnDb;

public class CancelBook {
	 public static boolean selectReserveRoom(String Time,String BuildingNumber,String RoomNumber,String Days){
	        
	        String sql = "select * from reserveroom   where Time = '"+Time+"' and BuildingNumber='" + BuildingNumber+"' and RoomNumber = '"+RoomNumber+"' and Days = '"+Days+"'";
	        //��ѯ���˻���Ϣ
	        ConnDb connDb = new ConnDb();
	        try {
//	            ִ��SQL���
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            if (rs.next()) {
	            	ps.close();
	                return true;//������ȷ����
	            }else {
	            	ps.close();
	                return false;//û�д�����Ϣ����ʾδע��
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("ִ��SQL������");
	        }
	        return false;
	    }
}
