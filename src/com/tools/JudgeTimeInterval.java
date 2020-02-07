package com.tools;
/*
 * 筛选当前时间段的空闲房间
 * 
 * */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.services.MainInterface;

public class JudgeTimeInterval {
   public static List<RoomMessage> toapply() throws ParseException{
	   SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	   Date nowTime=df.parse(df.format(new Date()));
	   /*df.format():将给定的Date格式化为日期/时间字符串，并将结果添加到给定的StringBuffer
	    *df.parse():解析字符串的文本，生成Date
	    * */
	   
	   System.out.println(nowTime);
	   String Days= Dateadd.mydays(dateToString.nowdateToString(), 0);
	   
	   Date beginTime1=df.parse(Days+" 08:00:00");
	   Date endTime1=df.parse(Days+" 09:00:00");
	   
	   Date beginTime2=df.parse(Days+" 09:00:00");
	   Date endTime2=df.parse(Days+" 10:00:00");
	   
	   Date beginTime3=df.parse(Days+" 10:00:00");
	   Date endTime3=df.parse(Days+" 11:00:00");
	   
	   Date beginTime4=df.parse(Days+" 11:00:00");
	   Date endTime4=df.parse(Days+" 12:00:00");
	   
	   Date beginTime5=df.parse(Days+" 13:00:00");
	   Date endTime5=df.parse(Days+" 14:00:00");
	   
	   Date beginTime6=df.parse(Days+" 14:00:00");
	   Date endTime6=df.parse(Days+" 15:00:00");
	   
	   Date beginTime7=df.parse(Days+" 15:00:00");
	   Date endTime7=df.parse(Days+" 16:00:00");
	   
	   Date beginTime8=df.parse(Days+" 16:00:00");
	   Date endTime8=df.parse(Days+" 17:00:00");
	   if(TimePartDelay.belongCalender(nowTime, beginTime1, endTime1))
	   {
		   List<RoomMessage> data1= MainInterface.selectRoomInfoTimePart(Days, "08:00-08:45");
		   return data1;
	   }
	   else if(TimePartDelay.belongCalender(nowTime, beginTime2, endTime2))
	   {
		   List<RoomMessage> data2= MainInterface.selectRoomInfoTimePart(Days, "09:00-09:45");
		   return data2;
	   }
	   else if(TimePartDelay.belongCalender(nowTime, beginTime3, endTime3))
	   {
		   List<RoomMessage> data3= MainInterface.selectRoomInfoTimePart(Days, "10:00-10:45");
		   return data3;
	   }
	   else if(TimePartDelay.belongCalender(nowTime, beginTime4, endTime4))
	   {
		   List<RoomMessage> data4= MainInterface.selectRoomInfoTimePart(Days, "11:00-11:45");
		   return data4;
	   }
	   else if(TimePartDelay.belongCalender(nowTime, beginTime5, endTime5))
	   {
		   List<RoomMessage> data5= MainInterface.selectRoomInfoTimePart(Days, "13:00-13:45");
		   return data5;
	   }
	   else if(TimePartDelay.belongCalender(nowTime, beginTime6, endTime6))
	   {
		   List<RoomMessage> data6= MainInterface.selectRoomInfoTimePart(Days, "14:00-14:45");
		   return data6;
	   }
	   else if(TimePartDelay.belongCalender(nowTime, beginTime7, endTime7))
	   {
		   List<RoomMessage> data7= MainInterface.selectRoomInfoTimePart(Days, "15:00-15:45");
		   return data7;
	   }
	   else if(TimePartDelay.belongCalender(nowTime, beginTime8, endTime8))
	   {
		   List<RoomMessage> data8= MainInterface.selectRoomInfoTimePart(Days, "16:00-16:45");
		   return data8;
	   }
	   else
	   {
		   return null;
	   }
   }
}
