package com.tools;

/** 
 * 生成帐户激活、重新设置密码的链接 
 */  
public class GenerateRepasswordLink {  


    /** 
     * 生成重设密码的链接 
     */  
    public static String generateResetPwdLink(User user) {  
        return "http://localhost:8080/SmartRoom/resetPassword.jsp";
    }  


}  
