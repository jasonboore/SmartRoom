package com.services;

/*
 * UpdateReserveRoom() �޸Ļ����� �����ݿ���н���
 * UpdateReserveRoomIsMeeting() �޸ķ���״̬
 * 
 * */
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.ConnDb;

public class Change {

	public static String UpdateReserveRoom(String BuildingNumber,String RoomNumber,int Size,String Function,String MeetingRoomLevel){
        String level=null;
        //�������ְλ�жϵȼ�
        switch (MeetingRoomLevel)
        {
        case "���³�" :
        	level ="4";
        break;
        
        case "�ܾ���":
        	level ="3";
        break;
        case "���ž���":
        	level ="2";
        break;
        }
            ConnDb connDb = new ConnDb();

            //�������ķ�����Ϣ
            String sql =  "update roominfo set Size = "+Size+", Functions = '"+Function
    				+"', MeetingRoomLevel = '"+level+"'"+"  where "
    				+" BuildingNumber='"+BuildingNumber+"' and RoomNumber = '"+RoomNumber+"'";
            
               try {
               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
                    int rs =  ps.executeUpdate();
                    if(rs>0) {
                    	ps.close();
                    	return "success";//�޸ĳɹ�
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
        //�������ְλ�жϵȼ�
        switch (MeetingRoomLevel)
        {
        case "���³�" :
        	level ="4";
        break;
        
        case "�ܾ���":
        	level ="3";
        break;
        case "���ž���":
        	level ="2";
        break;
        }
            ConnDb connDb = new ConnDb();

            //�������ķ�����Ϣ
            String sql =  "update roominfo set Size = "+Size+", Functions = '"+Function
    				+"', MeetingRoomLevel = '"+level+"' , IsMeeting = '"+IsMeeting+"'  where "
    				+"  BuildingNumber='"+BuildingNumber+"' and RoomNumber = '"+RoomNumber+"'";
            
               try {
               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
                    int rs =  ps.executeUpdate();
                    if(rs>0) {
                    	ps.close();
                    	return "success";//�޸ĳɹ�
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

