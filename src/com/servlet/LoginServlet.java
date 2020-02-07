package com.servlet;

import java.io.BufferedReader;



import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.services.Login;
import com.tools.CompareToken;
import com.tools.EmployeeNumberChecker;
import com.tools.FilterManage;
import com.tools.SearchZHandToken;
import com.tools.TokenUtil;
import com.tools.tomd5;
import net.sf.json.JSONObject;
/*
 * ��¼ servlet
 * 
 * */
public class LoginServlet extends HttpServlet {
	private String none=null;
	private String Token=null;
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
        response.setContentType("text/html;charset=utf-8");
        /*�����ַ���Ϊ'UTF-8'*/
        request.setCharacterEncoding("utf-8");
        System.out.println("���ӳɹ�����");// �����Ƿ�ɹ�����
        StringBuffer json1 = new StringBuffer();// �ַ���
        String line = null;
        BufferedReader reader = request.getReader();// ��ȡ��
        while ((line = reader.readLine()) != null) {
            json1.append(line);// ���ܵ���JSON��ʽ
        }

        System.out.println("�����"+json1);//�õ�����JSON��ʽ
        JSONObject jsonObject = JSONObject.fromObject(json1.toString());
        String zhanghu = jsonObject.getString("zhanghu");//��ȡJson��ֵ��
        String mima = tomd5.tomd5(jsonObject.getString("mima"));//���������MD5����
//        String Token=jsonObject.getString("Token");//��ȡToken
       
        System.out.println("������˻� "+zhanghu);//����˻������롢Token������鿴
        System.out.println("��������� "+mima);
//        System.out.println("�����token "+Token);
        
        
        /*���˻���������ע���쳣���*/
        FilterManage check1 = new FilterManage();
        check1.addChecker(new EmployeeNumberChecker());
		if((check1.doChain_check(zhanghu)))
		{					       

        	boolean in=Login.selectNumberin(zhanghu);
        	if(in) {
        String Password = Login.selectNumber(zhanghu);//����login�෽����ѯ�û�����
        System.out.println("ѭ������������ "+Password);
        System.out.println("ѭ������������tomd5 "+tomd5.tomd5(Password));  
      
       if(Password.equals(mima))   
        {
    	 //���������ȷ
    	    Token=TokenUtil.genToken();
    	   while(!TokenUtil.verificationToken(Token).equals("�ɹ�"))
    	   {
    		   Token=TokenUtil.genToken();
    	   }
    	   
    	  if(!SearchZHandToken.searchZH(zhanghu))
    	  {  
    		//������˻���Ӧ��tokenΪ��  ����
       	   Login.InsertToken(zhanghu,Token);
       	   System.out.println("����token�ɹ� ");
    	  }
    	  else {
    		  Login.UpdateToken(zhanghu,Token);//ÿ��½�ɹ�����token
    	  }
    	  /*ͨ��Ա���Ų�ѯ����*/
      	String Name=CompareToken.selectNameByEmployeeNumber(zhanghu);
    	/*ͨ��Ա���Ų�ѯԱ���ȼ�*/
    	String AdminPower=Login.selectadminpower(zhanghu);
        System.out.println("ѭ���ڲ�������� "+Password);
        System.out.println("ѭ���ڲ��������tomd5 "+tomd5.tomd5(Password));
        System.out.println("����token ");
        PrintWriter out = response.getWriter();
        String  info_json  = "{\"status\":\"0\",\"Name\":\""+Name+"\",\"Token\":\""+Token+"\",\"AdminPower\":\""+AdminPower+"\"}";//��½�ɹ�����״̬0
        System.out.println(info_json);
        out.write(info_json);
        out.flush();
        out.close();
     }
    	         	   
       
       else {
    	 //����������
    	   response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();     
	        String  info_json  =  "{\"status\":\"-1\",\"Name\":\""+none+"\",\"Token\":\""+none+"\",\"AdminPower\":\""+none+"\"}";//��ͨ����
	        System.out.println(info_json);
	        out.write(info_json);
	        out.flush();
	        out.close();
 
       }
    	   
		}
        	else {
    			//�˻�������
    			response.setContentType("text/html;charset=utf-8");
    	        PrintWriter out = response.getWriter();     
    	        String  info_json  =  "{\"status\":\"-5\",\"Name\":\""+none+"\",\"Token\":\""+none+"\",\"AdminPower\":\""+none+"\"}";//�˻�������
    	        System.out.println(info_json);
    	        out.write(info_json);
    	        out.flush();
    	        out.close();
        	}
		}
		else {
			//�˻����Ƿ�
			response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();     
	        String  info_json  =  "{\"status\":\"-2\",\"Name\":\""+none+"\",\"Token\":\""+none+"\",\"AdminPower\":\""+none+"\"}";//�˻����Ƿ�����-2
	        System.out.println(info_json);
	        out.write(info_json);
	        out.flush();
	        out.close();
		}
    }
  
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        doPost(request, response);
    }
}