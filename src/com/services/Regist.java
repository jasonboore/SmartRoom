package com.services;
/*  进行员工注册
 *  selectPosition(String number)   通过员工号从allinfo表中查询对应职位
 *  Regist()  将合理注册信息插入数据库 admininfo表
 *  searchZH1(String number)  判断通过传入的某员工号是否可以查询到该员工
 *  
 * */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.db.ConnDb;

public class Regist {

	public static String selectPosition(String number){
        String Position = null;//职位
        String sql = "select * from allinfo  where  EmployeeNumber = '"+number+"'";
        ConnDb connDb = new ConnDb();
        try {
//            执行SQL语句
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
              
            	Position = rs.getString("Position");//职位
            	ps.close();
                //获取职位
                return Position;//返回职位
            }else {
            	ps.close();
                return "查无此人";//没有此人信息。提示无法注册
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("执行SQL语句出错！");
            return "执行SQL语句出错！";
        }
   
    }
	
	//注册信息方法
	public static String Regist(String EmployeeNumber,String Password, String Name, String Sex,
			String PhoneNumber,String Email, String Ministry,String Position) {
        String NotFound = null;
        String Sucess=null;
        int level=0; //权限
        switch (Position)
        {
        case "董事长" :
        	level =4;
        break;
        
        case "总经理":
        	level =3;
        break;
        case "部门经理":
        	level =2;
        break;
        case "员工":
        	level =1;
        break;
        }
        String insql4 = "insert into admininfo values ("+EmployeeNumber
   			 +",'"+Password+"','"+Name+"',"+Sex+",'"+PhoneNumber
   			 +"','"+Email+"','"+Ministry+"',"+null+","+null+","+null+
   			 ","+level+","+System.currentTimeMillis()+")";
        
        System.out.println("insert into admininfo values ("+EmployeeNumber
      			 +",'"+Password+"','"+Name+"',"+Sex+",'"+PhoneNumber
       			 +"','"+Email+"','"+Ministry+"',"+0+","+null+","+0+
       			 ","+level+","+System.currentTimeMillis()+")");

        ConnDb connDb1 = new ConnDb();
        try {
        	if(!searchZH1(EmployeeNumber))
        	{
        	
        	 PreparedStatement ps1 =  connDb1.conn().prepareStatement(insql4);
             int rs1 =  ps1.executeUpdate();
             if(rs1==1)
             {
            	 System.out.println("插入注册信息成功！");
            	 ps1.close();
            	 return "注册成功";
             }
             else
             {   
            	 ps1.close();
            	 System.out.println("插入注册信息失败！");
            	 return "该员工号已注册！";
             }
        }
        	else
            {   
        	 System.out.println("该员工号已注册！");
           	 return "该员工号已注册！";
            }
        }
        catch (SQLException e) {
           
            e.printStackTrace();
            System.out.println("该员工号已注册！  catch抛出");
            return NotFound="该员工号已注册！";
        }

        } 
		        
	public static boolean searchZH1 (String Number){
		
		
		String sql = "select * from admininfo where EmployeeNumber ='"+Number+"'";
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


	

