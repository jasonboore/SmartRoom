package com.tools;

import java.text.ParseException;
import java.util.List;

public class aa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//   StringBuffer aa=new StringBuffer();
//   aa.append("00001000");
//   System.out.println(aa);
//    System.out.println(aa.indexOf("0",4));
		try {
			List<SuitableRoomMsg> aa=SuitableRoom.SuitableRoom(1, "30", "∆’Õ®∑øº‰");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SuitableRoom aaa=new SuitableRoom();
		System.out.println(aaa.toString());
	}

}
