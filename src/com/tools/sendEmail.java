package com.tools; 
/*
 * 向指定邮箱发送邮件  
 * 发件人  18182737073@163.com  识别码  包含于 “password.dat” 
 * sendResetPasswordEmail(User user ,String userEmail)  传入需要重设密码的员工号和邮箱
 * 
 * */

import java.io.IOException;  
import java.io.InputStream;  
import java.util.Date;  
import java.util.Properties;  

import javax.mail.Authenticator;  
import javax.mail.Message.RecipientType;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  

public class sendEmail {  

    private static final String FROM = "18182737073@163.com";  


    /** 
     * 发送重设密码链接的邮件 
     */  
    public static void sendResetPasswordEmail(User user ,String userEmail) {  
        Session session = getSession();  
        MimeMessage message = new MimeMessage(session);  
        try {  
            message.setSubject("来自GoV");  
            message.setSentDate(new Date());  
            message.setFrom(new InternetAddress(FROM));  
            message.setRecipient(RecipientType.TO, new InternetAddress(userEmail));  
            //优先级
            message.addHeader("X-Priority","1");

            message.setContent("GoV:智能会议室 员工密码更改:<br/><a href='" + GenerateRepasswordLink.generateResetPwdLink(user) +"'>点击继续进行操作</a>","text/html;charset=utf-8");  
            // 发送邮件  
            Transport.send(message);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  

    public static Session getSession() {  
        Properties props = new Properties();  
        props.setProperty("mail.transport.protocol", "smtp");  
        props.setProperty("mail.smtp.host", "smtp.163.com");  
        props.setProperty("mail.smtp.port", "25");  
        props.setProperty("mail.smtp.auth", "true");  
        Session session = Session.getInstance(props, new Authenticator() {  
            @Override  
            protected PasswordAuthentication getPasswordAuthentication() {  
                String password = null;  
                InputStream is = sendEmail.class.getResourceAsStream("password.dat");  
                byte[] b = new byte[1024];  
                try {  
                    int len = is.read(b);  
                    password = new String(b,0,len);  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                return new PasswordAuthentication(FROM, password);  
            }  

        });  
        return session;  
    }  
}  
