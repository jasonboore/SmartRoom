package com.tools;
/*
 * �û��һ�����ӿ�
 * */
public interface UserRepasswoed {  

    User findUserByNameOrEmail(String nameOrEmail);  
    boolean UpdateByName(String userName,String newPassword);
}  
