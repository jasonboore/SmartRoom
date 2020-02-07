package com.tools;
/*
 * date.after(begin):date��ʾ��ʱ���Ƿ���begin�����ʱ��֮���Ƿ���true,���򷵻�false
 * date.after(end):date��ʾ��ʱ���Ƿ���end�����ʱ��֮���Ƿ���true,���򷵻�false
 * */
import java.util.Calendar;
import java.util.Date;

public class TimePartDelay {
 public static boolean belongCalender(Date nowTime,Date beginTime,Date endTime)
 {
	 /* ʹ��Ĭ��ʱ�������Ի������һ������*/
	 Calendar date=Calendar.getInstance();
	 
	 /* ʹ�ø�����Date ���ô�Calendar��ʱ��*/
	 date.setTime(nowTime);
	 
	 /* ʹ��Ĭ��ʱ�������Ի������һ������*/
	 Calendar begin=Calendar.getInstance();
	 
	 /* ʹ�ø�����Date ���ô�Calendar��ʱ��*/
	 begin.setTime(beginTime);
	 
	 
	 /* ʹ��Ĭ��ʱ�������Ի������һ������*/
	 Calendar end=Calendar.getInstance();
	 
	 /* ʹ�ø�����Date ���ô�Calendar��ʱ��*/
	 end.setTime(endTime);
	 if(date.after(begin)&&date.before(end))
	 {
		 /*date.after(begin):date��ʾ��ʱ���Ƿ���begin�����ʱ��֮���Ƿ���true,���򷵻�false
		  * date.after(end):date��ʾ��ʱ���Ƿ���end�����ʱ��֮���Ƿ���true,���򷵻�false
		  * */
		 return true;
	 }
	 else
	 {
		 return false;
	 }
	 
 }
 public static boolean beforeCalender(Date nowTime,Date beginTime)
 {
	 /* ʹ��Ĭ��ʱ�������Ի������һ������*/
	 Calendar date=Calendar.getInstance();
	 
	 /* ʹ�ø�����Date ���ô�Calendar��ʱ��*/
	 date.setTime(nowTime);
	 
	 /* ʹ��Ĭ��ʱ�������Ի������һ������*/
	 Calendar begin=Calendar.getInstance();
	 
	 /* ʹ�ø�����Date ���ô�Calendar��ʱ��*/
	 begin.setTime(beginTime);
	 
	 
	 /* ʹ��Ĭ��ʱ�������Ի������һ������*/
	 Calendar end=Calendar.getInstance();
	 
	 /* ʹ�ø�����Date ���ô�Calendar��ʱ��*/
//	 end.setTime(endTime);
	 if(date.before(begin))
	 {
		 /*date.after(begin):date��ʾ��ʱ���Ƿ���begin�����ʱ��֮���Ƿ���true,���򷵻�false
		  * 
		  * */
		 return true;
	 }
	 else
	 {
		 return false;
	 }
	 
 }
 public static boolean afterCalender(Date nowTime,Date endTime)
 {
	 /* ʹ��Ĭ��ʱ�������Ի������һ������*/
	 Calendar date=Calendar.getInstance();
	 
	 /* ʹ�ø�����Date ���ô�Calendar��ʱ��*/
	 date.setTime(nowTime);
	 
	 /* ʹ��Ĭ��ʱ�������Ի������һ������*/
	 Calendar begin=Calendar.getInstance();
	 
	 /* ʹ�ø�����Date ���ô�Calendar��ʱ��*/
//	 begin.setTime(beginTime);
	 
	 
	 /* ʹ��Ĭ��ʱ�������Ի������һ������*/
	 Calendar end=Calendar.getInstance();
	 
	 /* ʹ�ø�����Date ���ô�Calendar��ʱ��*/
//	 end.setTime(endTime);
	 if(date.after(end))
	 {
		 /*
		  * date.after(end):date��ʾ��ʱ���Ƿ���end�����ʱ��֮���Ƿ���true,���򷵻�false
		  * */
		 return true;
	 }
	 else
	 {
		 return false;
	 }
	 
 }
}
