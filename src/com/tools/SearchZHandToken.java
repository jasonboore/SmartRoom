package com.tools;
/*
 * SearchNumber(String Token) 通过token查员工号
 * Token (String Number)  通过员工号查token
 * searchZH (String Number)  通过员工号查账户
 * 
 * */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConnDb;

public class SearchZHandToken {
	public static String  SearchNumber(String Token){
		String EmployeeNumber = null;
		String sql = "select * from token where token ='"+Token+"'";
		ConnDb connDb = new ConnDb();	//连接数据库
		try {
		//      执行SQL语句
		      PreparedStatement ps =  connDb.conn().prepareStatement(sql);
		      ResultSet rs =  ps.executeQuery();
		      if (rs.next()) {
		         EmployeeNumber=rs.getString("EmployeeNumber").trim();
		         ps.close();
		        	return EmployeeNumber;
		        
		      }else {
		    	  ps.close();
		          return "查无此人";
		      }
		  } catch (SQLException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		      System.out.println("执行SQL语句出错！");
		      return "查无此人";
		  }
		
	}

	//通过员工号查token
	
	public static String Token (String Number){
		
		String Token = null;
		String sql = "select * from token where EmployeeNumber ='"+Number+"'";
		ConnDb connDb = new ConnDb();	//连接数据库
		try {
		//      执行SQL语句
		      PreparedStatement ps =  connDb.conn().prepareStatement(sql);
		      ResultSet rs =  ps.executeQuery();
		      if (rs.next()) {
		    	  Token=rs.getString("token").trim();
		    	  ps.close();
		        	return Token;
		        
		      }else {
		    	  ps.close();
		          return Token="null";
		      }
		  } catch (SQLException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		      System.out.println("执行SQL语句出错！");
		      return Token="null";
		  }
		
		
	}
	
	public static boolean searchZH (String Number){
		
		
		String sql = "select * from token where EmployeeNumber ='"+Number+"'";
		ConnDb connDb = new ConnDb();	//连接数据库
		try {
		//      执行SQL语句
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
