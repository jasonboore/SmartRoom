package com.tools;
/*
 * 用户找回密码接口
 * */
public interface UserRepasswoed {  

    User findUserByNameOrEmail(String nameOrEmail);  
    boolean UpdateByName(String userName,String newPassword);
}  
