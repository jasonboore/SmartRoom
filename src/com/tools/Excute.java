package com.tools;


/*
 * ִ�ж�ʱ�߳�
 * 
 * */
public class Excute {
	public Excute() {
	InTimeSet1 I1=new InTimeSet1();
	System.out.println("set1������ִ��");
	Thread th1=new Thread(I1);
	System.out.println("set1��ִ��");
	th1.start();
	InTimeSet0 I0=new InTimeSet0();
	Thread th0=new Thread(I0);
	System.out.println("set0��ִ��");
	th0.start();
	}
}
