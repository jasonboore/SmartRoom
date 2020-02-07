package com.services;
/*
 * 增加会议室
 * 此类与数据库进行交互 
 * InsertReserveRoom()  向表中插入新增房间信息
 * CompareRoom()   判断该房间是否已存在
 * */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.db.ConnDb;
import com.tools.getUUID;


public class Add {

	public static boolean InsertRoomInfo(String Time,String BuildingNumber,String RoomNumber,int Size,String Function,String MeetingRoomLevel,String Days){
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

            System.out.println("insert into roominfo values ('"+getUUID.getUUID32()+"','"
           			+Time +"','"+BuildingNumber+"','"+RoomNumber+"',"+Size+",'"+Function
           			+"',"+level+",'"+0+"')");
            //添加增添的房间信息
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
//            执行SQL语句
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
            System.out.println("执行SQL语句出错！");
            return false;
        }
        
    }
    
 
        }




