package com.db;
/*
 * �������ݿⳣ��
 * 
 * */
import java.sql.*;

public class ConnDb {
    private String driverName = "com.mysql.jdbc.Driver";
    private String username = "root";
    private String password = "1466";
    private String url = "jdbc:mysql://localhost:3306/mydb1?"
    		+ "useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    
    public Connection conn() {
        try {
            Class.forName(driverName);
            try {
                Connection conn = DriverManager.getConnection(url,username,password);
                return conn;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("�������ݿ�ʧ�ܣ�");
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("��������ʧ�ܣ�");
        }
        return null;
    }
}