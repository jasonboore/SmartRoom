package com.tools;

import java.awt.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;

import com.services.Search;
import com.services.SmartBook;

public class SuitableRoom {

	@SuppressWarnings("unused")
	public static java.util.List<SuitableRoomMsg> SuitableRoom(int num,String size,String Functions) throws ParseException {

		
		java.util.List<SuitableRoomMsg> data = SmartBook.selectAllRoomInfo();
		java.util.List<SuitableRoomMsg> data1 = SmartBook.selectSizeRoom(Integer.parseInt(size));
	    java.util.List<SuitableRoomMsg> data2 = SmartBook.selectFunctionsRoom(Functions);
		java.util.List<SuitableRoomMsg> data3 = SmartBook.selectDoubleRoom(Functions,Integer.parseInt(size));
		ArrayList<String> Roomlist = new ArrayList<String>();
	    ArrayList<String> Buildinglist = new ArrayList<String>();
	    java.util.List<SuitableRoomMsg> Suitdata = new ArrayList<SuitableRoomMsg>();
	    java.util.List<SuitableRoomMsg> IsMeeting=new ArrayList<SuitableRoomMsg>();
	    if(size.equals("")&&Functions.equals("")) {
	        for (int i = 0; i < data.size(); i++) {
	            if (!Roomlist.contains(data.get(i).getRoomNumber()))
	            	Roomlist.add(data.get(i).getRoomNumber());
	        }
//	        for (int i = 0; i < Roomlist.size(); i++)
//	        {
//	         
//	        	System.out.println(Roomlist.get(i));
//	        }
      for (int j = 0; j < data.size(); j++) {
         if (!Buildinglist.contains(data.get(j).getBuildingNumber()))
        	 Buildinglist.add(data.get(j).getBuildingNumber());
     }
      
//      for (int i = 0; i < Buildinglist.size(); i++)
//      {
//         System.out.println(Buildinglist.get(i));
//      }
	    }
	    else if(size.length()!=0&&Functions.equals("")) {
	        for (int i = 0; i < data1.size(); i++) {
	            if (!Roomlist.contains(data1.get(i).getRoomNumber()))
	            	Roomlist.add(data1.get(i).getRoomNumber());
	        }
//	        for (int i = 0; i < Roomlist.size(); i++)
//	        {
//	         
//	        	System.out.println(Roomlist.get(i));
//	        }
      for (int j = 0; j < data1.size(); j++) {
         if (!Buildinglist.contains(data1.get(j).getBuildingNumber()))
        	 Buildinglist.add(data1.get(j).getBuildingNumber());
     }
      
//      for (int i = 0; i < Buildinglist.size(); i++)
//      {
//         System.out.println(Buildinglist.get(i));
//      }
	    }
	    else if(size.equals("")&&Functions.length()!=0) {
	        for (int i = 0; i < data2.size(); i++) {
	            if (!Roomlist.contains(data2.get(i).getRoomNumber()))
	            	Roomlist.add(data2.get(i).getRoomNumber());
	        }
//	        for (int i = 0; i < Roomlist.size(); i++)
//	        {
//	         
//	        	System.out.println(Roomlist.get(i));
//	        }
      for (int j = 0; j < data2.size(); j++) {
         if (!Buildinglist.contains(data2.get(j).getBuildingNumber()))
        	 Buildinglist.add(data2.get(j).getBuildingNumber());
     }
      
      for (int i = 0; i < Buildinglist.size(); i++)
      {
         System.out.println(Buildinglist.get(i));
      }
	    }
	    else if(size.length()!=0&&Functions.length()!=0) {
	        for (int i = 0; i < data3.size(); i++) {
	            if (!Roomlist.contains(data3.get(i).getRoomNumber()))
	            	Roomlist.add(data3.get(i).getRoomNumber());
	        }
//	        for (int i = 0; i < Roomlist.size(); i++)
//	        {
//	         
//	        	System.out.println(Roomlist.get(i));
//	        }
      for (int j = 0; j < data3.size(); j++) {
         if (!Buildinglist.contains(data3.get(j).getBuildingNumber()))
        	 Buildinglist.add(data3.get(j).getBuildingNumber());
     }
      
//      for (int i = 0; i < Buildinglist.size(); i++)
//      {
//         System.out.println(Buildinglist.get(i));
//      }
	    }
  	   String []Days=new String[3];
      try {
			//获取当前日期
			Days[0] = Dateadd.mydays(dateToString.nowdateToString(), 0);
			Days[1] = Dateadd.mydays(dateToString.nowdateToString(), 1);
			Days[2] = Dateadd.mydays(dateToString.nowdateToString(), 2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
      
      
      
      SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	   Date nowTime=df.parse(df.format(new Date()));
	   /*df.format():将给定的Date格式化为日期/时间字符串，并将结果添加到给定的StringBuffer
	    *df.parse():解析字符串的文本，生成Date
	    * */
	   
	   System.out.println(nowTime);
	   String days= Dateadd.mydays(dateToString.nowdateToString(), 0);
	   
	   
	   
	   Date beginTime1=df.parse(days+" 08:00:00");
	   Date endTime1=df.parse(days+" 09:00:00");
	   
	   Date beginTime2=df.parse(days+" 09:00:00");
	   Date endTime2=df.parse(days+" 10:00:00");
	   
	   Date beginTime3=df.parse(days+" 10:00:00");
	   Date endTime3=df.parse(days+" 11:00:00");
	   
	   Date beginTime4=df.parse(days+" 11:00:00");
	   Date endTime4=df.parse(days+" 13:00:00");
	   
	   Date beginTime5=df.parse(days+" 13:00:00");
	   Date endTime5=df.parse(days+" 14:00:00");
	   
	   Date beginTime6=df.parse(days+" 14:00:00");
	   Date endTime6=df.parse(days+" 15:00:00");
	   
	   Date beginTime7=df.parse(days+" 15:00:00");
	   Date endTime7=df.parse(days+" 16:00:00");
	   
	   Date beginTime8=df.parse(days+" 16:00:00");
	   Date endTime8=df.parse(days+" 17:00:00");

	   Date endTime9=df.parse(days+" 23:59:59");
	   StringBuffer Meeting;
  	for(int i=0;i<Buildinglist.size();i++)
  	{
  		for(int j = 0;j<Roomlist.size();j++)
  		{
  			Meeting=new StringBuffer();
  		
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[0], "08:00-08:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[0], "09:00-09:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[0], "10:00-10:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[0], "11:00-11:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[0], "13:00-13:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[0], "14:00-14:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[0], "15:00-15:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[0], "16:00-16:45"));
  			System.out.println(Meeting);
  		
 			int   index=0;
 		  
 		   if(TimePartDelay.belongCalender(nowTime, beginTime1, endTime1))
 		   {
 			 index=1;
 		   }
 		   else if(TimePartDelay.belongCalender(nowTime, beginTime2, endTime2))
 		   {
 			 index=2;
 		   }
 		   else if(TimePartDelay.belongCalender(nowTime, beginTime3, endTime3))
 		   {
 			 index=3;
 		   }
 		   else if(TimePartDelay.belongCalender(nowTime, beginTime4, endTime4))
 		   {
 			 index=4;
 		   }
 		   else if(TimePartDelay.belongCalender(nowTime, beginTime5, endTime5))
 		   {
 			 index=5;
 		   }
 		   else if(TimePartDelay.belongCalender(nowTime, beginTime6, endTime6))
 		   {
 			 index=6;
 		   }
 		   else if(TimePartDelay.belongCalender(nowTime, beginTime7, endTime7))
 		   {
 			 index=7;
 		   }
 		  else if(TimePartDelay.belongCalender(nowTime, endTime7, endTime9))
		   {
			 break;
		   }
  			switch (num) {
  			case 4:
  				System.out.println("4");
  			 if(Meeting.indexOf("0000", index)==0) {
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "08:00-08:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "09:00-09:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "10:00-10:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "11:00-11:45"));
  				index=4;
  			 }
  			  if(Meeting.indexOf("0000", index)== 1) { 
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "09:00-09:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "10:00-10:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "11:00-11:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "13:00-13:45"));
  				index=5;
  			 }
  			 if(Meeting.indexOf("0000", index)== 2) {
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "10:00-10:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "11:00-11:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "13:00-13:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "14:00-14:45"));
  				index=6;
  			}
  			 if(Meeting.indexOf("0000", index)== 3) {
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "11:00-11:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "13:00-13:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "14:00-14:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "15:00-15:45"));
  				index=7;
  			}
  			 if(Meeting.indexOf("0000", index)== 4) {
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "13:00-13:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "14:00-14:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "15:00-15:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "16:00-16:45"));
  				index=7;
  			}
  			 break;
  			case 3:
  				System.out.println("3");
  			 if(Meeting.indexOf("000", index)== 0) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "08:00-08:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "09:00-09:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "10:00-10:45"));
   				
   				index=3;
  			}
  			 if(Meeting.indexOf("000", index)== 1) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "09:00-09:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "10:00-10:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "11:00-11:45"));
   				
   				index=4;
  			}
  			 if(Meeting.indexOf("000", index)== 2) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "10:00-10:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "11:00-11:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "13:00-13:45"));
   				index=5;
  			}
  			 if(Meeting.indexOf("000", index)== 3) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "11:00-11:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "13:00-13:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "14:00-14:45"));
   				index=6;
  			}
  			 if(Meeting.indexOf("000", index)== 4) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "13:00-13:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "14:00-14:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "15:00-15:45"));
   				index=7;
  			}
  		 if(Meeting.indexOf("000", index)== 5) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "14:00-14:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "15:00-15:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "16:00-16:45"));
   				index=7;
  			}
  		 break;
  			case 2:
  				System.out.println("2");
  			 if(Meeting.indexOf("00", index)== 0) {
   			
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "08:00-08:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "09:00-09:45"));
   				
   				index=2;
  			 }
  			 if(Meeting.indexOf("00", index)== 1) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "09:00-09:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "10:00-10:45"));
   				index=3;
  			}
  			 if(Meeting.indexOf("00", index)== 2) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "10:00-10:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "11:00-11:45"));
   				index=4;
  			}
  			 if(Meeting.indexOf("00", index)== 3) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "11:00-11:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "13:00-13:45"));
   				index=5;
  			}
  			 if(Meeting.indexOf("00", index)== 4) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "13:00-13:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "14:00-14:45"));
   				index=6;
  			}
  			 if(Meeting.indexOf("00", index)== 5) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "14:00-14:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "15:00-15:45"));
   				index=7;
  			}
  			 if(Meeting.indexOf("00", index)==6) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "15:00-15:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "16:00-16:45"));
   				index=7;
  			}
  			 break;
  			case 1:
  				System.out.println("1");
  			if(Meeting.indexOf("0", index)== 0) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "08:00-08:45"));
   				
   				index=1;
  			}
  			 if(Meeting.indexOf("0", index)== 1) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "09:00-09:45"));
   				index=2;
  			}
  			 if(Meeting.indexOf("0", index)== 2) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "10:00-10:45"));
   				index=3;
  			 }	
   			 if(Meeting.indexOf("0", index)== 3) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "11:00-11:45"));
   				index=4;
   			 }
   			 if(Meeting.indexOf("0", index)== 4) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "13:00-13:45"));
   				index=5;
   			 }
   			 if(Meeting.indexOf("0", index)== 5) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "14:00-14:45"));
   				index=6;
   			 }
   			 if(Meeting.indexOf("0", index)== 6) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "15:00-15:45"));
   				index=7;
   			 }
   			 if(Meeting.indexOf("0", index)== 7) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[0], "16:00-16:45"));
   				
   			 }
   			 break;	
   			default :
   				break;
  		     
  			}
  		}
  		}
  	
  for(int k=1;k<3;k++) {
	for(int i=0;i<Buildinglist.size();i++)
  	{
  		for(int j = 0;j<Roomlist.size();j++)
  		{
 	   
  			Meeting=new StringBuffer();
  	  		System.out.println("这里的BuildingNumber:"+Buildinglist.get(i)+"这里的RoomNumber:"+Roomlist.get(i));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[k], "08:00-08:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[k], "09:00-09:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[k], "10:00-10:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[k], "11:00-11:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[k], "13:00-13:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[k], "14:00-14:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[k], "15:00-15:45"));
  			Meeting.append(SmartBook.selectMeeting(Buildinglist.get(i), Roomlist.get(j), Days[k], "16:00-16:45"));
  			System.out.println("这里的Meeting:"+Meeting);
  			int index=0;
 			
  			switch (num) {
  			case 4:
  				System.out.println("4");
  			 if(Meeting.indexOf("0000", index)==0) {
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "08:00-08:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "09:00-09:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "10:00-10:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "11:00-11:45"));
  				index=4;
  			 }
  			  if(Meeting.indexOf("0000", index)== 1) {
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "09:00-09:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "10:00-10:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "11:00-11:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "13:00-13:45"));
  				index=5;
  			 }
  			 if(Meeting.indexOf("0000", index)== 2) {
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "10:00-10:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "11:00-11:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "13:00-13:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "14:00-14:45"));
  				index=6;
  			}
  			 if(Meeting.indexOf("0000", index)== 3) {
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "11:00-11:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "13:00-13:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "14:00-14:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "15:00-15:45"));
  				index=7;
  			}
  			 if(Meeting.indexOf("0000", index)== 4) {
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "13:00-13:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "14:00-14:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "15:00-15:45"));
  				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "16:00-16:45"));
  				index=7;
  			}
  			 break;
  			case 3:
  				System.out.println("3");
  			 if(Meeting.indexOf("000", index)== 0) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "08:00-08:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "09:00-09:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "10:00-10:45"));
   				index=3;
  			}
  			 if(Meeting.indexOf("000", index)== 1) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "09:00-09:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "10:00-10:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "11:00-11:45"));
   				
   				index=4;
  			}
  			 if(Meeting.indexOf("000", index)== 2) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "10:00-10:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "11:00-11:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "13:00-13:45"));
   				index=5;
  			}
  			 if(Meeting.indexOf("000", index)== 3) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "11:00-11:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "13:00-13:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "14:00-14:45"));
   				index=6;
  			}
  			 if(Meeting.indexOf("000", index)== 4) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "13:00-13:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "14:00-14:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "15:00-15:45"));
   				index=7;
  			}
  		 if(Meeting.indexOf("000", index)== 5) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "14:00-14:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "15:00-15:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "16:00-16:45"));
   				index=7;
  			}
  		 break;
  			case 2:
  				System.out.println("2");
  			 if(Meeting.indexOf("00", index)== 0) {
   			
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "08:00-08:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "09:00-09:45"));
   				
   				index=2;
  			 }
  			 if(Meeting.indexOf("00", index)== 1) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "09:00-09:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "10:00-10:45"));
   				index=3;
  			}
  			 if(Meeting.indexOf("00", index)== 2) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "10:00-10:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "11:00-11:45"));
   				index=4;
  			}
  			 if(Meeting.indexOf("00", index)== 3) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "11:00-11:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "13:00-13:45"));
   				index=5;
  			}
  			 if(Meeting.indexOf("00", index)== 4) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "13:00-13:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "14:00-14:45"));
   				index=6;
  			}
  			 if(Meeting.indexOf("00", index)== 5) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "14:00-14:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "15:00-15:45"));
   				index=7;
  			}
  			 if(Meeting.indexOf("00", index)==6) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "15:00-15:45"));
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "16:00-16:45"));
   				index=7;
  			}
  			 break;
  			case 1:
  				System.out.println("1");
  			if(Meeting.indexOf("0", index)== 0) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "08:00-08:45"));
   				index=1;
   				System.out.println(index+"啊");
  			}
  			 if(Meeting.indexOf("0", index)== 1) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "09:00-09:45"));
   				index=2;
  			}
  			 if(Meeting.indexOf("0", index)== 2) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "10:00-10:45"));
   				index=3;
  			 }	
   			 if(Meeting.indexOf("0", index)== 3) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "11:00-11:45"));
   				index=4;
   			 }
   			 if(Meeting.indexOf("0", index)== 4) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "13:00-13:45"));
   				index=5;
   			 }
   			 if(Meeting.indexOf("0", index)== 5) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "14:00-14:45"));
   				index=6;
   			 }
   			 if(Meeting.indexOf("0", index)== 6) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "15:00-15:45"));
   				index=7;
   			 }
   			 if(Meeting.indexOf("0", index)== 7) {
   				Suitdata.addAll(SmartBook.selectSuitableRoom(Buildinglist.get(i), Roomlist.get(j), Days[k], "16:00-16:45"));
   				
   			 }
   			 break;	
   			default :
   				break;
  		     
  			}
  		}
  		}
  	}    
      
    	 
  	System.out.println(Suitdata.size());
  		return Suitdata;
  	}
   			 
   			 
  			 
  		
  	
  	
	
}


