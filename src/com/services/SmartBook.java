package com.services;

/*
 * 智能查询房间信息
 * selectSizeRoomInfo(int size) 通过目标容量 筛选房间
 * selectFunctionsRoomInfo(String functions)  通过功能筛选房间
 * selectDoubleRoomInfo(String functions,int size)  通过容量和房间查询房间信息
 * selectConditionStatus1()  返回状态数组为 -1
 * selectConditionStatus2()  返回状态数组为 -2
 * selectConditionStatus3()  返回状态数组为 -3
 * 
 * */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.ConnDb;
import com.tools.RoomMessage;
import com.tools.SuitableRoomMsg;
import com.tools.tounicode;

public class SmartBook {
	 public static  List<RoomMessage> selectSizeRoomInfo(int size){
	    	ArrayList<RoomMessage> data;
	        data=new ArrayList<RoomMessage>();
	        String sql = "select * from roominfo where Size >= '"+size+"' and IsMeeting = '0'";
	        ConnDb connDb = new ConnDb();
	        try {
//	            执行SQL语句
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
	            System.out.println("while里的    "+data.toString());
	            ps.close();
	                return data;
	        } catch (SQLException e) {
	         
	            e.printStackTrace();
	            System.out.println("执行SQL语句出错！");
	        }
	        return null;
	    }
	    
	 public static  List<RoomMessage> selectFunctionsRoomInfo(String functions){
	    	ArrayList<RoomMessage> data;
	        data=new ArrayList<RoomMessage>();
	        String sql = "select * from roominfo where Functions = '"+functions+"' and IsMeeting = '0'";
	        ConnDb connDb = new ConnDb();
	        try {
//	            执行SQL语句
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
	            System.out.println("while里的    "+data.toString());
	            ps.close();
	                return data;
	        } catch (SQLException e) {
	         
	            e.printStackTrace();
	            System.out.println("执行SQL语句出错！");
	        }
	        return null;
	    }
	 public static  List<RoomMessage> selectDoubleRoomInfo(String functions,int size){
	    	ArrayList<RoomMessage> data;
	        data=new ArrayList<RoomMessage>();
	        String sql = "select * from roominfo where Functions = '"+functions+"' and IsMeeting = '0' and Size >= "+size;

	        ConnDb connDb = new ConnDb();
	        try {
//	            执行SQL语句
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
	            System.out.println("while里的    "+data.toString());

	                return data;
	        } catch (SQLException e) {
	         
	            e.printStackTrace();
	            System.out.println("执行SQL语句出错！");
	        }
	        return null;
	    }
public static  List<RoomMessage> selectConditionStatus1(){
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
		 
	    System.out.println("selectStatus() 生成msg里的    "+data.toString());
		System.out.println("data1   "+data.get(0).getBuildingNumber());
		return data;//
	         
	    }
	 public static  List<RoomMessage> selectConditionStatus2(){
			ArrayList<RoomMessage> data;
	        data=new ArrayList<RoomMessage>();
	       int i=3;
	            
	            while(i>0) {
	            	RoomMessage msg=new RoomMessage();
	            	msg.setTime("-2");
	            	msg.setBuildingNumber("-2");
	            	msg.setRoomNumber("-2");
	            	
	            	String Size1="-2";
	            	msg.setSize(Size1);
	            	msg.setFunctions("-2");
	            	msg.setIsMeeting( "-2");
	            	msg.setDays("-2");
	            	data.add(msg);  
	            	i--;
	            }
		 
	    System.out.println("selectStatus() 生成msg里的    "+data.toString());
		System.out.println("data1   "+data.get(0).getBuildingNumber());
		return data;//
	         
	    }
	    public static  List<RoomMessage> selectConditionStatus3(){
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
		 
	    System.out.println("selectStatus() 生成msg里的    "+data.toString());
		System.out.println("data1   "+data.get(0).getBuildingNumber());
		return data;//
	         
	    }
	    public static  List<SuitableRoomMsg> selectAllRoomInfo(){
	    	ArrayList<SuitableRoomMsg> data,data1;
	        data=new ArrayList<SuitableRoomMsg>();
	        String sql = "select * from roominfo";
	        ConnDb connDb = new ConnDb();
	        try {
//	            执行SQL语句
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            
	            while(rs.next()) {
	            	SuitableRoomMsg msg=new SuitableRoomMsg();
	            	msg.setRoomNumber(rs.getString("RoomNumber"));
	            	msg.setBuildingNumber(rs.getString("BuildingNumber"));
	            	data.add(msg);
	                
	            }
	            System.out.println("while里的    "+data.toString());
	            	
	            	  ps.close();
	                return data;
	        } catch (SQLException e) {
	         
	            e.printStackTrace();
	            System.out.println("执行SQL语句出错！");
	        }
	        return null;
}
public static  String selectMeeting(String BuildingNumber,String RoomNumber,String Days,String Time){
	    	ArrayList<SuitableRoomMsg> data,data1;
	        data=new ArrayList<SuitableRoomMsg>();
	        String sql = "select * from roominfo where RoomNumber = '"+RoomNumber+"' and Days= '"+Days+"' and Time= '"+Time+"' and BuildingNumber= '"+BuildingNumber+"'";
	        //System.out.println("select * from roominfo where RoomNumber = '"+RoomNumber+"' and Days= '"+Days+"' and Time= '"+Time+"' and BuildingNumber= '"+BuildingNumber+"'");
	        ConnDb connDb = new ConnDb();
	        try {
//	            执行SQL语句
	            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
	            ResultSet rs =  ps.executeQuery();
	            
	           if(rs.next()) {
	            	
	            	String Meeting=rs.getString("IsMeeting");
	            	System.out.println(Meeting);
	            	return Meeting;
	            	
	            	
	                
	            }
	            //System.out.println(data.get(1).getIsMeeting());
	            	
	            	  ps.close();
	               
	        } catch (SQLException e) {
	         
	            e.printStackTrace();
	            System.out.println("执行SQL语句出错！");
	        }
	        return null;
}
	public static  List<SuitableRoomMsg> selectSuitableRoom(String BuildingNumber,String RoomNumber,String Days,String Time){
	ArrayList<SuitableRoomMsg> data,data1;
    data=new ArrayList<SuitableRoomMsg>();
    String sql = "select * from roominfo where RoomNumber = '"+RoomNumber+"' and Days= '"+Days+"' and Time= '"+Time+"' and BuildingNumber= '"+BuildingNumber+"'";
    //System.out.println("select * from roominfo where RoomNumber = '"+RoomNumber+"' and Days= '"+Days+"' and Time= '"+Time+"' and BuildingNumber= '"+BuildingNumber+"'");
    ConnDb connDb = new ConnDb();
   
    try {
//        执行SQL语句
        PreparedStatement ps =  connDb.conn().prepareStatement(sql);
        ResultSet rs =  ps.executeQuery();
        
        while(rs.next()) {
        	SuitableRoomMsg msg=new SuitableRoomMsg();
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
        //System.out.println(data.get(1).getIsMeeting());
              
        	  ps.close();
            return data;
    } catch (SQLException e) {
     
        e.printStackTrace();
        System.out.println("执行SQL语句出错！");
    }

    return null;
}
	public static  List<SuitableRoomMsg> selectSizeRoom(int Size){
    	ArrayList<SuitableRoomMsg> data,data1;
        data=new ArrayList<SuitableRoomMsg>();
        String sql = "select * from roominfo where Size >= '"+Size+"'";
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            
            while(rs.next()) {
            	SuitableRoomMsg msg=new SuitableRoomMsg();
            	msg.setRoomNumber(rs.getString("RoomNumber"));
            	msg.setBuildingNumber(rs.getString("BuildingNumber"));
            	data.add(msg);
                
            }
            System.out.println("while里的    "+data.toString());
            	
            	  ps.close();
                return data;
        } catch (SQLException e) {
         
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
        }
        return null;
}
	public static  List<SuitableRoomMsg> selectFunctionsRoom(String functions){
    	ArrayList<SuitableRoomMsg> data,data1;
        data=new ArrayList<SuitableRoomMsg>();
        String sql = "select * from roominfo where Functions = '"+functions+"'";
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            
            while(rs.next()) {
            	SuitableRoomMsg msg=new SuitableRoomMsg();
            	msg.setRoomNumber(rs.getString("RoomNumber"));
            	msg.setBuildingNumber(rs.getString("BuildingNumber"));
            	data.add(msg);
                
            }
            System.out.println("while里的    "+data.toString());
            	
            	  ps.close();
                return data;
        } catch (SQLException e) {
         
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
        }
        return null;
}
	public static  List<SuitableRoomMsg> selectDoubleRoom(String functions,int size){
    	ArrayList<SuitableRoomMsg> data,data1;
        data=new ArrayList<SuitableRoomMsg>();
        String sql = "select * from roominfo where Functions = '"+functions+"'  and Size >= "+size;
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            
            while(rs.next()) {
            	SuitableRoomMsg msg=new SuitableRoomMsg();
            	msg.setRoomNumber(rs.getString("RoomNumber"));
            	msg.setBuildingNumber(rs.getString("BuildingNumber"));
            	data.add(msg);
                
            }
            System.out.println("while里的    "+data.toString());
            	
            	  ps.close();
                return data;
        } catch (SQLException e) {
         
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
        }
        return null;
}
}
