package com.tools; 
/*
 * ��ָ�����䷢���ʼ�  
 * ������  18182737073@163.com  ʶ����  ������ ��password.dat�� 
 * sendResetPasswordEmail(User user ,String userEmail)  ������Ҫ���������Ա���ź�����
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
     * ���������������ӵ��ʼ� 
     */  
    public static void sendResetPasswordEmail(User user ,String userEmail) {  
        Session session = getSession();  
        MimeMessage message = new MimeMessage(session);  
        try {  
            message.setSubject("����GoV");  
            message.setSentDate(new Date());  
            message.setFrom(new InternetAddress(FROM));  
            message.setRecipient(RecipientType.TO, new InternetAddress(userEmail));  
            //���ȼ�
            message.addHeader("X-Priority","1");

            message.setContent("GoV:���ܻ����� Ա���������:<br/><a href='" + GenerateRepasswordLink.generateResetPwdLink(user) +"'>����������в���</a>","text/html;charset=utf-8");  
            // �����ʼ�  
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
