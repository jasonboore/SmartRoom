package com.tools;
/*
 * 将房间号置为 0 的定时线程
 * */
import java.text.ParseException;

import com.services.BookRoom;

public class InTimeSet1 implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		SetDays days=new SetDays();
		String Days=days.getDays();
		String StartTime=days.getStartTime();
		String Union=Days+" "+StartTime;
		String Stamp=null;
		 long CurrentTime = System.currentTimeMillis();
		 
		try {
			 Stamp=Timer.dateToStamp(Union);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long part=Long.parseLong(Stamp)-CurrentTime;
		try {
			Thread.sleep(part);
			System.out.println("准时输出");
			BookRoom.UpdateRoomInfoIsMeeting(days.getTime(), days.getBuildingNumber(), days.getRoomNumber(),days.getDays());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
