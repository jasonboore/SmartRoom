package com.tools;


/*
 * 执行定时线程
 * 
 * */
public class Excute {
	public Excute() {
	InTimeSet1 I1=new InTimeSet1();
	System.out.println("set1即将已执行");
	Thread th1=new Thread(I1);
	System.out.println("set1已执行");
	th1.start();
	InTimeSet0 I0=new InTimeSet0();
	Thread th0=new Thread(I0);
	System.out.println("set0已执行");
	th0.start();
	}
}
