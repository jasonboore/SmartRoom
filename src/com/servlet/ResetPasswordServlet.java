package com.servlet;  

import java.io.IOException;  
import java.util.HashMap;  
import java.util.Map;  

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.services.rePassward;
import com.tools.User;
import com.tools.UserRepasswoed;
import com.tools.tomd5;  

/** 
 * ������������ 
 */  
public class ResetPasswordServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
    	doPost(request, response);
        }  



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        doGet(request, response);  
        /*�����ַ���Ϊ'UTF-8'*/
        request.setCharacterEncoding("utf-8");
        String userName = request.getParameter("userName");  
        String newPassword = request.getParameter("newPassword");  
        String newPassword2 = request.getParameter("newPassword2");  
        Map<String,String> errors = new HashMap<String, String>();  
        if (newPassword == null || "".equals(newPassword)) {  
            errors.put("newPassword", "�����벻��Ϊ�գ�");  
        }  

        if (newPassword2 == null || "".equals(newPassword2)) {  
            errors.put("newPassword2", "ȷ�������벻��Ϊ�գ�");  
        }  

        if (!newPassword.equals(newPassword2)) {  
            errors.put("passwordError", "������������벻һ�£�");  
        }  

        if (!errors.isEmpty()) {  
            request.setAttribute("errors", errors);  
            request.getRequestDispatcher("/resetPassword?userName=" + userName).forward(request, response);  
            return;  
        }  

        UserRepasswoed userRepasswoed = rePassward.getInstance();  
         boolean flag = userRepasswoed.UpdateByName(userName,tomd5.tomd5(newPassword));  


        request.getRequestDispatcher("/resetPasswordSuccess.jsp").forward(request, response);
    }  
} 
