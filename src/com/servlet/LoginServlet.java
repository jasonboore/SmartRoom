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
 * 登录 servlet
 * 
 * */
public class LoginServlet extends HttpServlet {
	private String none=null;
	private String Token=null;
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
        response.setContentType("text/html;charset=utf-8");
        /*设置字符集为'UTF-8'*/
        request.setCharacterEncoding("utf-8");
        System.out.println("连接成功反馈");// 测试是否成功连接
        StringBuffer json1 = new StringBuffer();// 字符流
        String line = null;
        BufferedReader reader = request.getReader();// 读取流
        while ((line = reader.readLine()) != null) {
            json1.append(line);// 接受的是JSON格式
        }

        System.out.println("传入的"+json1);//得到的是JSON格式
        JSONObject jsonObject = JSONObject.fromObject(json1.toString());
        String zhanghu = jsonObject.getString("zhanghu");//获取Json键值对
        String mima = tomd5.tomd5(jsonObject.getString("mima"));//对密码就行MD5加密
//        String Token=jsonObject.getString("Token");//获取Token
       
        System.out.println("传入的账户 "+zhanghu);//输出账户、密码、Token，方便查看
        System.out.println("传入的密码 "+mima);
//        System.out.println("传入的token "+Token);
        
        
        /*对账户进行命令注入异常检测*/
        FilterManage check1 = new FilterManage();
        check1.addChecker(new EmployeeNumberChecker());
		if((check1.doChain_check(zhanghu)))
		{					       

        	boolean in=Login.selectNumberin(zhanghu);
        	if(in) {
        String Password = Login.selectNumber(zhanghu);//调用login类方法查询用户密码
        System.out.println("循环外查出的密码 "+Password);
        System.out.println("循环外查出的密码tomd5 "+tomd5.tomd5(Password));  
      
       if(Password.equals(mima))   
        {
    	 //如果密码正确
    	    Token=TokenUtil.genToken();
    	   while(!TokenUtil.verificationToken(Token).equals("成功"))
    	   {
    		   Token=TokenUtil.genToken();
    	   }
    	   
    	  if(!SearchZHandToken.searchZH(zhanghu))
    	  {  
    		//如果该账户对应的token为空  插入
       	   Login.InsertToken(zhanghu,Token);
       	   System.out.println("插入token成功 ");
    	  }
    	  else {
    		  Login.UpdateToken(zhanghu,Token);//每登陆成功更新token
    	  }
    	  /*通过员工号查询姓名*/
      	String Name=CompareToken.selectNameByEmployeeNumber(zhanghu);
    	/*通过员工号查询员工等级*/
    	String AdminPower=Login.selectadminpower(zhanghu);
        System.out.println("循环内查出的密码 "+Password);
        System.out.println("循环内查出的密码tomd5 "+tomd5.tomd5(Password));
        System.out.println("更新token ");
        PrintWriter out = response.getWriter();
        String  info_json  = "{\"status\":\"0\",\"Name\":\""+Name+"\",\"Token\":\""+Token+"\",\"AdminPower\":\""+AdminPower+"\"}";//登陆成功返回状态0
        System.out.println(info_json);
        out.write(info_json);
        out.flush();
        out.close();
     }
    	         	   
       
       else {
    	 //如果密码错误
    	   response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();     
	        String  info_json  =  "{\"status\":\"-1\",\"Name\":\""+none+"\",\"Token\":\""+none+"\",\"AdminPower\":\""+none+"\"}";//普通错误
	        System.out.println(info_json);
	        out.write(info_json);
	        out.flush();
	        out.close();
 
       }
    	   
		}
        	else {
    			//账户不存在
    			response.setContentType("text/html;charset=utf-8");
    	        PrintWriter out = response.getWriter();     
    	        String  info_json  =  "{\"status\":\"-5\",\"Name\":\""+none+"\",\"Token\":\""+none+"\",\"AdminPower\":\""+none+"\"}";//账户不存在
    	        System.out.println(info_json);
    	        out.write(info_json);
    	        out.flush();
    	        out.close();
        	}
		}
		else {
			//账户名非法
			response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();     
	        String  info_json  =  "{\"status\":\"-2\",\"Name\":\""+none+"\",\"Token\":\""+none+"\",\"AdminPower\":\""+none+"\"}";//账户名非法返回-2
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