package com.tools;

/*
 * 检查邮件合法性
 * 
 * */
import java.io.PrintWriter;

public class emailChecker implements BaseFilter_Albert_Frank_Check {

	@Override
	public boolean doCheck(String sin) {
		
		if(sin.contains("'")||sin.contains("\"")||sin.contains(" ")||sin.contains("(")||sin.contains(")")||sin.contains("*"))
		{
	
			System.out.println(getClass().getName()+"error");
			System.out.println(sin+"    邮箱非法输入，包含非法注入符号");
			return false;
		}
		// TODO Auto-generated method stub
		System.out.println(sin+"     输入合法");
		return true;
	}
	

}