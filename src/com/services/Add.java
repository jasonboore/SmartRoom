package com.services;
/*
 * ���ӻ�����
 * ���������ݿ���н��� 
 * InsertReserveRoom()  ����в�������������Ϣ
 * CompareRoom()   �жϸ÷����Ƿ��Ѵ���
 * */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.db.ConnDb;
import com.tools.getUUID;


public class Add {

	public static boolean InsertRoomInfo(String Time,String BuildingNumber,String RoomNumber,int Size,String Function,String MeetingRoomLevel,String Days){
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

            System.out.println("insert into roominfo values ('"+getUUID.getUUID32()+"','"
           			+Time +"','"+BuildingNumber+"','"+RoomNumber+"',"+Size+",'"+Function
           			+"',"+level+",'"+0+"')");
            //�������ķ�����Ϣ
            String sql = "insert into roominfo values ('"+getUUID.getUUID32()+"','"
            			+Time +"','"+BuildingNumber+"','"+RoomNumber+"',"+Size+",'"+Function
            			+"','"+level+"','"+0+"','"+Days+"')";
              
               try {
               	 PreparedStatement ps =  connDb.conn().prepareStatement(sql);
                    int rs =  ps.executeUpdate();
                    if(rs==1) {
                    	ps.close();
                    return true;
                    }
                    else
                    {
                    	ps.close();
                    	return false;
                    }
                    
               }
               catch (SQLException e) {
                   e.printStackTrace();
                   System.out.println("ERROR");
                   return false;
               }             
               
               }

    public static boolean CompareRoom(String BuildingNumber,String RoomNumber){

        String sql = "select * from roominfo  where  "+" BuildingNumber = '"
        		      +BuildingNumber+"' and RoomNumber = '"+RoomNumber+"'";
    
        ConnDb connDb = new ConnDb();
        try {
//            ִ��SQL���
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
            	ps.close();
                return true;
            }else {
            	ps.close();
                return false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ִ��SQL������");
            return false;
        }
        
    }
    
 
        }




