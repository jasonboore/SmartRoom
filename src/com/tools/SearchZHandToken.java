package com.tools;
/*
 * SearchNumber(String Token) ͨ��token��Ա����
 * Token (String Number)  ͨ��Ա���Ų�token
 * searchZH (String Number)  ͨ��Ա���Ų��˻�
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
		ConnDb connDb = new ConnDb();	//�������ݿ�
		try {
		//      ִ��SQL���
		      PreparedStatement ps =  connDb.conn().prepareStatement(sql);
		      ResultSet rs =  ps.executeQuery();
		      if (rs.next()) {
		         EmployeeNumber=rs.getString("EmployeeNumber").trim();
		         ps.close();
		        	return EmployeeNumber;
		        
		      }else {
		    	  ps.close();
		          return "���޴���";
		      }
		  } catch (SQLException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		      System.out.println("ִ��SQL������");
		      return "���޴���";
		  }
		
	}

	//ͨ��Ա���Ų�token
	
	public static String Token (String Number){
		
		String Token = null;
		String sql = "select * from token where EmployeeNumber ='"+Number+"'";
		ConnDb connDb = new ConnDb();	//�������ݿ�
		try {
		//      ִ��SQL���
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
		      System.out.println("ִ��SQL������");
		      return Token="null";
		  }
		
		
	}
	
	public static boolean searchZH (String Number){
		
		
		String sql = "select * from token where EmployeeNumber ='"+Number+"'";
		ConnDb connDb = new ConnDb();	//�������ݿ�
		try {
		//      ִ��SQL���
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
