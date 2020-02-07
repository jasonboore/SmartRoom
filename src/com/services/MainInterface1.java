package com.services;
/* ���շ�����Ϣ
 * selectRoomInfoToday()  ���ҷ���ʱ��
 * selectRoomInfoToday1()  �÷���״̬���Ϊ -1
 * selectRoomInfoToday3()   �÷���״̬���Ϊ -3
 * 
 * */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.ConnDb;
import com.tools.RoomMessage;
import com.tools.tounicode;

public class MainInterface1 {
	public static  List<RoomMessage> selectRoomInfoToday(String Days){
    	ArrayList<RoomMessage> data;
        data=new ArrayList<RoomMessage>();
        String sql = "select * from roominfo where Days= '"+Days+"'";;
        //��ѯ���˻���Ϣ
        ConnDb connDb = new ConnDb();
        try {
//            ִ��SQL���
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
            System.out.println("while���    "+data.toString());
            	System.out.println("data1   "+data.get(1).getBuildingNumber());
            	ps.close();
                return data;
              
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ִ��SQL������");
        }
        return null;
    }
	
	public static  List<RoomMessage> selectRoomInfoToday3(){
		ArrayList<RoomMessage> data;
        data=new ArrayList<RoomMessage>();
       int i=3;
            
            while(i>0) {
            	RoomMessage msg=new RoomMessage();
            	msg.setTime("-3");
            	msg.setBuildingNumber("-3");
            	msg.setRoomNumber("-3");
            	
            	String Size1="-3";
            	msg.setSize(Size1);
            	msg.setFunctions("-3");
            	msg.setIsMeeting( "-3");
            	msg.setDays("-3");
            	data.add(msg);  
            	i--;
            }
	 
    System.out.println("selectStatus() ����msg���    "+data.toString());
	System.out.println("data1   "+data.get(0).getBuildingNumber());
	return data;//
         
    }
	
	public static  List<RoomMessage> selectRoomInfoToday1(){
		ArrayList<RoomMessage> data;
        data=new ArrayList<RoomMessage>();
       int i=3;
            
            while(i>0) {
            	RoomMessage msg=new RoomMessage();
            	msg.setTime("-1");
            	msg.setBuildingNumber("-1");
            	msg.setRoomNumber("-1");
            	
            	String Size1="-1";
            	msg.setSize(Size1);
            	msg.setFunctions("-1");
            	msg.setIsMeeting( "-1");
            	msg.setDays("-1");
            	data.add(msg);  
            	i--;
            }
	 
    System.out.println("selectStatus() ����msg���    "+data.toString());
	System.out.println("data1   "+data.get(0).getBuildingNumber());
	return data;//
         
    }
	
}
