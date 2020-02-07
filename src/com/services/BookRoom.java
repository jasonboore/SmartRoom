package com.services;
/**
 * BookRoom()  Ԥ��ָ������
 * selectMeetingStatus()  �жϷ���״̬
 * UpdateRoomInfoIsMeeting()  ���÷���ռ��
 * UpdateRoomInfoIsMeeting0() �ָ�ʹ��
 * deleteReserveRoom() ɾ��Ԥ����Ϣ
 * selectMeetingRoomLevel() ��ѯ�����ҵȼ�
 * 
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.db.ConnDb;
import com.tools.getUUID;

public class BookRoom {
	public static boolean BookRoom(String Time,String Name,String RoomNumber,String BuildingNumber,String days,String EmployeeNumber){
        String uuid = getUUID.getUUID32();
        String sql = ("insert into reserveroom values ('"+getUUID.getUUID32()+"','"+System.currentTimeMillis() +"','"+Time+"','"
        +Name+"','"+RoomNumber+"','"+BuildingNumber+"','"+days+"','"+EmployeeNumber+"')");
        
        System.out.println(sql);
        //��ѯ���˻���Ϣ
        ConnDb connDb = new ConnDb();
        try {
//            ִ��SQL���
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            int rs =  ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ERROR");
            return false;
        }
      
    }
	 public static String selectMeetingStatus(String Time,String BuildingNumber,String RoomNumber){
	        String NotFound = null;
	        String Number = null;
	        String Password = null;
	        String sql = "select * from roominfo  where Time = '"+Time+"'and BuildingNumber = '"+BuildingNumber+"'"
	        		+ "and RoomNumber ='"+RoomNumber+"'";
	        //��ѯ���˻���Ϣ
	        System.out.println(sql);
	        ConnDb connDb = new ConnDb();
	        try {
//	            ִ��SQL���
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            if (rs.next()) {
	            	if(rs.getString("IsMeeting").equals("0"))
	            	{
	            		ps.close();
	                return  "0";
	            	}
	            	else if(rs.getString("IsMeeting").equals("1")) 
	            	{
	            		ps.close();
	            		return "1";
	            	}
	            	else if(rs.getString("IsMeeting").equals("2"))
	            			{
	            		ps.close();
	            		return "2";
	            			}
	            	else {
	            		ps.close();
	            		return "-1";
	            	}
	            }else {
	            	ps.close();
	                return "-1";//û�д�����Ϣ����ʾδע��
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("ִ��SQL������");
	            return "-1";
	        }
	        
	    }
	 public static boolean UpdateRoomInfoIsMeeting(String Time,String BuildingNumber,String RoomNumber,String days){
	        //�������ְλ�жϵȼ�
	      
	            ConnDb connDb = new ConnDb();
	            String sql = "update roominfo set IsMeeting = '1'"+" where Time = '"+Time+"' and BuildingNumber='"
   	       				+BuildingNumber+"' and RoomNumber = '"+RoomNumber+"' and Days = '"+days+"'";

	               System.out.println(sql);
	               try {
	               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	                    int rs =  ps.executeUpdate();
	                    ps.close();
	               }
	               catch (SQLException e) {
	                   e.printStackTrace();
	                   System.out.println("ERROR");
	                   return false;
	               }             
	               return true;
	               }  
	 public static boolean UpdateRoomInfoIsMeeting0(String Time,String BuildingNumber,String RoomNumber,String days){
	        //�������ְλ�жϵȼ�
	      
	            ConnDb connDb = new ConnDb();
	            String sql = "update roominfo set IsMeeting = '0'"+" where Time = '"+Time+"' and BuildingNumber='"
	       				+BuildingNumber+"' and RoomNumber = '"+RoomNumber+"' and Days = '"+days+"'";

	               System.out.println(sql);
	               try {
	               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	                    int rs =  ps.executeUpdate();
	                    ps.close();
	               }
	               catch (SQLException e) {
	                   e.printStackTrace();
	                   System.out.println("ERROR");
	                   return false;
	               }             
	               return true;
	               }   
	 public static boolean deleteReserveRoom(String Time,String BuildingNumber,String RoomNumber,String days){
	        //�������ְλ�жϵȼ�
	      
	            ConnDb connDb = new ConnDb();
	            String sql = "delete from  reserveroom where Time = '"+Time+"' and BuildingNumber='"
	       				+BuildingNumber+"' and RoomNumber = '"+RoomNumber+"' and Days = '"+days+"'";

	               System.out.println(sql);
	               try {
	               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	                    int rs =  ps.executeUpdate();
	                    ps.close();
	               }
	               catch (SQLException e) {
	                   e.printStackTrace();
	                   System.out.println("ERROR");
	                   return false;
	               }             
	               return true;
	               }   
	 public static String selectMeetingRoomLevel(String Time,String BuildingNumber,String RoomNumber,String days){
	        String MeetingRoomLevel = null;
	        String sql = "select * from roominfo  where  Time= '"+Time+"' and BuildingNumber = '"+BuildingNumber
	        		+"' and RoomNumber='"+RoomNumber+"' and Days = '"+days+"'";
	        //��ѯ���˻���Ϣ
	        System.out.println(sql);
	        ConnDb connDb = new ConnDb();
	        try {
//	            ִ��SQL���
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            if (rs.next()) {
	            	MeetingRoomLevel=rs.getString("MeetingRoomLevel");
	            	ps.close();
	                return  MeetingRoomLevel;
	            }else {
	            	ps.close();
	                return "ERROR";//û�д�����Ϣ����ʾδע��
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            System.out.println("ִ��SQL������");
	            return "ERROR";
	        }
	        
	    }
}
