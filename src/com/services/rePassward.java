package com.services;  

/*
 * 通过邮件修改密码
 * findUserByNameOrEmail(String Email) 通过邮件查对应员工号是否存在
 * UpdateByName(String EmployeeNumber,String newPassword)  更新员工号和用户密码
 * 
 * */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;  
import java.util.Map;  
import com.tools.User;
import com.tools.UserRepasswoed;
import com.db.ConnDb;  



public class rePassward implements UserRepasswoed {  

    private static rePassward instance = new rePassward();  

    private rePassward() {}  

    public static rePassward getInstance() {  
        return instance;  
    }  

    Map<Integer,User> users = new HashMap<Integer, User>();  

    int nextId = 1;  
    @Override  
    public User findUserByNameOrEmail(String Email) {  
        User user=new User();
        ConnDb connDb = new ConnDb();

        String sql="select * from admininfo where Email = '"+Email+"'";
        System.out.println(sql);
       
        try{
        	
        	PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
                     
            if(rs.next()){

                user.setUserName(rs.getString("EmployeeNumber"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("Email"));
            }   
            ps.close();
            return user;
        }catch(SQLException e)
        {
            e.printStackTrace();
            return null;
        } catch (Exception e) {         
            e.printStackTrace();
            return null;
        }
       

    }  
    public boolean UpdateByName(String EmployeeNumber,String newPassword){
        boolean flag=false;
        ConnDb connDb = new ConnDb();

        String sql="update admininfo set Password= '"+newPassword+"' where EmployeeNumber= '"+EmployeeNumber+"'";
       
        try{
        	PreparedStatement ps =  connDb.conn().prepareStatement(sql);
          
            flag=ps.executeUpdate()>0;  
            ps.close();
        }catch(SQLException e)
        {
            e.printStackTrace();
        } catch (Exception e) {
            
            e.printStackTrace();
        }

        return flag;

    }

}  
