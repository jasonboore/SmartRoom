package com.tools;
/*
 * ������������Ƿ�Ϊ����
 * 
 * */
public class intChecker implements BaseFilter_Albert_Frank_Check{
	public boolean doCheck(String sin) {
	if(!intTest.intTest(sin))
	{	
		System.out.println(getClass().getName()+"  error");
		System.out.println(sin+"   �����Ƿ�������������");
		return false;
	}
	// TODO Auto-generated method stub
	System.out.println(sin+"    �����Ϸ�");
	return true;
}

}
