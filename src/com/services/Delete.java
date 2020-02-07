package com.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConnDb;
/*
 * DeleteReserveRoom()  ɾ������
 * Boolean RoomExist()  �жϷ����Ƿ����
 * Boolean IsMeeting()  �жϻ�����״̬
 * */
public class Delete {
	public static String DeleteReserveRoom(String BuildingNumber,String RoomNumber){
        ConnDb connDb = new ConnDb();

            //�������ķ�����Ϣ
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
//            ִ��SQL���
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
              
            	meeting =rs.getString("IsMeeting");
            	System.out.println("������״̬:"+meeting);
                if(meeting.trim().equals("1"))//ռ��
                {
                	ps.close();
                	yn=false;
                	break;
                	
                	
                }
                else if(meeting.trim().equals("2"))//ά��
                {
                	ps.close();
                	yn=false;
                	break;
                	
                }
                else 
                	{
                	ps.close();
                	yn=true;//����
                	}
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
            System.out.println("ִ��SQL������");
            
        }
		return yn;       
		
		
	}
	
	public static Boolean RoomExist(String BuildingNumber,String RoomNumber){
		String sql = "select * from roominfo where "+" BuildingNumber='"+BuildingNumber
				+"' and RoomNumber='"+RoomNumber+"'";
		System.out.println(sql);
        ConnDb connDb = new ConnDb();
        try {
//            ִ��SQL���
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
            System.out.println("ִ��SQL������");
            return false;
        }
	      
		
		
	}
        }

