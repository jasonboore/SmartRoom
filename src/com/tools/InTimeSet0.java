package com.tools;
/*
 * ���������Ϊ 0 �Ķ�ʱ�߳�
 * */
import java.text.ParseException;

import com.services.BookRoom;

public class InTimeSet0 implements Runnable{
	
	
	
	String End=null;
	String EndTime;
	String Days;
	private String Time;
	private String RoomNumber;
	private String BuildingNumber;
	Long CurrentTime=System.currentTimeMillis();
	public void SetDays(String Days)
	{
		this.Days=Days;
	}
	public void SetEndTime(String EndTime)
	{
		this.EndTime=EndTime;
	}
	public void setTime(String time) {
		this.Time = time;
	}
	
	public void setRoomNumber(String roomNumber) {
		this.RoomNumber = roomNumber;
	}
	
	public void setBuildingNumber(String buildingNumber) {
		this.BuildingNumber = buildingNumber;
	}

	
	
	@Override
	public void run() {
		
	try {
		
		End=Timer.dateToStamp(Days+" "+EndTime);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	long part=Long.parseLong(End)-CurrentTime;
	System.out.println(EndTime+"   EndTime�������");
	System.out.println(Days+"   Days�������");
	System.out.println(part+"   ��ʱ");
	try {
		Thread.sleep(part);
		System.out.println("׼ʱִ��");
		BookRoom.UpdateRoomInfoIsMeeting0(Time, BuildingNumber,RoomNumber,Days);
		System.out.println("�����Ѿ���Ϊ0");
		BookRoom.deleteReserveRoom(Time, BuildingNumber, RoomNumber,Days);
		System.out.println("Ԥ��������Ϣ�����");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
	}
	
	
}
