package com.tools;

/*
 * nowdateToString() ��ָ���ĵ�������������ת�����ַ���������
 * 
 * */


import java.text.SimpleDateFormat;
import java.util.Date;

public class dateToString {


        public static String nowdateToString() {
            Date date = new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String reStr = sdf.format(date);
            System.out.println(reStr);
            return reStr;
        }

}
