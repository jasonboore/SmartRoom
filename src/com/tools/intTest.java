package com.tools;

/*
 * ����ַ����Ƿ�Ϊ������
 * */
public class intTest {

	public static boolean intTest(String str)
	{
		boolean isDigit1 = false;//����һ��booleanֵ��������ʾ�Ƿ��������
    for(int i=0 ; i<str.length() ; i++ ){ //ѭ�������ַ���   
        if(Character.isDigit(str.charAt(i))){     //��char��װ���е��ж����ֵķ����ж�ÿһ���ַ�
        	System.out.println("�������ֻ���ĸ");
            isDigit1 = true;   
        }
        else
        {
        	isDigit1 = false;
        	break;       	
        }
              
    }
    return isDigit1;
    
	}
}
