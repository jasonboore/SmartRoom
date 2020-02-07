package com.services;
import java.sql.PreparedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConnDb;
/*
 * selectNumber(String number) ͨ���˺Ų����ݿ�����
 * InsertToken(String zhanghu,String Token)  �����˻� token
 * UpdateToken(String zhanghu,String Token) ͨ���˻����� token
 * selectadminpower(String number) ͨ���˻���Ȩ��
 * selectNumberin(String number)  �ж��˻��Ƿ����
 * 
 * */
public class Login {

    public static String selectNumber(String number){
        String Number = null;
        String Password = null;
        String sql = "select * from admininfo  where  EmployeeNumber = '"+number+"'";
        //��ѯ���˻���Ϣ
        ConnDb connDb = new ConnDb();
        try {
//            ִ��SQL���
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
              
            	Number =rs.getString("EmployeeNumber");
            	Password = rs.getString("Password");
                //��ȡ�˺�����
            	ps.close();
                return Password;//������ȷ����
            }else {
            	ps.close();
                return null;//û�д�����Ϣ����ʾδע��
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ִ��SQL������");
        }
        return null;
    }
    
    public static boolean selectNumberin(String number){
     
        String sql = "select * from admininfo  where  EmployeeNumber = '"+number+"'";
        //��ѯ���˻���Ϣ
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
                return false;//û�д�����Ϣ����ʾδע��
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ִ��SQL������");
        }
        return false;
    }
    
    public static void InsertToken(String zhanghu,String Token)
    {
    	
    	
    	String sql="insert into token values ('"+zhanghu+"','"+Token+"')";
    	System.out.println("insert into admininfo values ('"+zhanghu+"','"+Token+"')");
    	ConnDb connDb = new ConnDb();
    	  try {
         	 PreparedStatement ps1 =  connDb.conn().prepareStatement(sql);
              int rs1 =  ps1.executeUpdate();
              ps1.close();
         }
         catch (SQLException e) {
            
             e.printStackTrace();
             
         }
    }
    public static void UpdateToken(String zhanghu,String Token)
    {
    	
    	
    	String sql="update token set token = '"+Token+"' where EmployeeNumber = '"+zhanghu+"'";
    	System.out.println("update token set token = '"+Token+"' where EmployeeNumber = '"+zhanghu+"'");
    	ConnDb connDb = new ConnDb();
    	  try {
         	 PreparedStatement ps1 =  connDb.conn().prepareStatement(sql);
              int rs1 =  ps1.executeUpdate();
              ps1.close();
         }
         catch (SQLException e) {
            
             e.printStackTrace();
             
         }
    }
    
    public static String selectadminpower(String number){
        String adminpower = null;
      
        String sql = "select * from admininfo  where  EmployeeNumber = '"+number+"'";
        //��ѯ���˻���Ϣ
        ConnDb connDb = new ConnDb();
        try {
//            ִ��SQL���
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
              
            	
            	adminpower = rs.getString("AdminPower");
                //��ȡ�˺�����
            	ps.close();
                return adminpower;//������ȷ����
            }else {
            	ps.close();
                return "���޴���";//û�д�����Ϣ����ʾδע��
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ִ��SQL������");
        }
        return null;
    }
    
    
}