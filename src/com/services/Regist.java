package com.services;
/*  ����Ա��ע��
 *  selectPosition(String number)   ͨ��Ա���Ŵ�allinfo���в�ѯ��Ӧְλ
 *  Regist()  ������ע����Ϣ�������ݿ� admininfo��
 *  searchZH1(String number)  �ж�ͨ�������ĳԱ�����Ƿ���Բ�ѯ����Ա��
 *  
 * */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.db.ConnDb;

public class Regist {

	public static String selectPosition(String number){
        String Position = null;//ְλ
        String sql = "select * from allinfo  where  EmployeeNumber = '"+number+"'";
        ConnDb connDb = new ConnDb();
        try {
//            ִ��SQL���
            PreparedStatement ps =  connDb.conn().prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            if (rs.next()) {
              
            	Position = rs.getString("Position");//ְλ
            	ps.close();
                //��ȡְλ
                return Position;//����ְλ
            }else {
            	ps.close();
                return "���޴���";//û�д�����Ϣ����ʾ�޷�ע��
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ִ��SQL������");
            return "ִ��SQL������";
        }
   
    }
	
	//ע����Ϣ����
	public static String Regist(String EmployeeNumber,String Password, String Name, String Sex,
			String PhoneNumber,String Email, String Ministry,String Position) {
        String NotFound = null;
        String Sucess=null;
        int level=0; //Ȩ��
        switch (Position)
        {
        case "���³�" :
        	level =4;
        break;
        
        case "�ܾ���":
        	level =3;
        break;
        case "���ž���":
        	level =2;
        break;
        case "Ա��":
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
            	 System.out.println("����ע����Ϣ�ɹ���");
            	 ps1.close();
            	 return "ע��ɹ�";
             }
             else
             {   
            	 ps1.close();
            	 System.out.println("����ע����Ϣʧ�ܣ�");
            	 return "��Ա������ע�ᣡ";
             }
        }
        	else
            {   
        	 System.out.println("��Ա������ע�ᣡ");
           	 return "��Ա������ע�ᣡ";
            }
        }
        catch (SQLException e) {
           
            e.printStackTrace();
            System.out.println("��Ա������ע�ᣡ  catch�׳�");
            return NotFound="��Ա������ע�ᣡ";
        }

        } 
		        
	public static boolean searchZH1 (String Number){
		
		
		String sql = "select * from admininfo where EmployeeNumber ='"+Number+"'";
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


	

