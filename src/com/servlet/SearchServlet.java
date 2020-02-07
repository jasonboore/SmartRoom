package com.servlet;
/**
 * 查询所有房间信息
 * 
 */
import java.io.BufferedReader;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.Search;
import com.tools.CompareToken;
import com.tools.FilterManage;
import com.tools.JudgeSearch;
import com.tools.RoomMessage;
import com.tools.TokenUtil;
import com.tools.tojson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    	 System.out.println("进入    SearchServlet");// 测试是否成功连接
        response.setContentType("text/html;charset=utf-8");
        /*设置字符集为'UTF-8'*/
        request.setCharacterEncoding("utf-8");
        System.out.println("连接成功反馈");// 测试是否成功连接
        StringBuffer json1 = new StringBuffer();// 字符流
        String line = null;
        BufferedReader reader = request.getReader();// 读取流
        while ((line = reader.readLine()) != null) {
            json1.append(line);// 接受的是JSON格式
        }

        System.out.println(json1);//得到的是JSON格式
        
        JSONObject jsonObject = JSONObject.fromObject(json1.toString());

        String Token = jsonObject.getString("Token");//获取Token
        System.out.println(Token);

          
          if(CompareToken.selectToken(Token)) {
        	  //Token存在
        	  String istoken=TokenUtil.verificationToken(Token);//Token解码
         if(istoken.equals("成功")){
        	 //Token符合条件
        	 List<RoomMessage> data=null;
			try {
				data = JudgeSearch.toapplySearch();
				System.out.println("判断时间");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//返货所有房间信息
           if(data!=null) {
        	   //数组不为空
        	  JSONArray jsonArray=JSONArray.fromObject(data);
        	String data1=jsonArray.toString();
        	System.out.println("这里的data1    "+data1.toString());
        	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
            if(json.size()>0){
                for(int i=0;i<json.size();i++){
                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
                }}
            System.out.println("第一行    "+json.getJSONObject(1));
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(data1.toString());
            out.flush();
            out.close();
            }
           
           else {
                 //普通失败返回数组-1
            	  List<RoomMessage> statusdata=Search.selectStatus1();
            	  if(statusdata!=null) {
                	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
                	String data1=jsonArray.toString();
                	System.out.println("这里的data1    "+data1.toString());
                	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
                    if(json.size()>0){
                        for(int i=0;i<json.size();i++){
                            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                            net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//                            System.out.println("这里的解析的    "+jsonObj);
                        }}
                    System.out.println("第一行    "+json.getJSONObject(1));
                    response.setContentType("text/html;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(data1.toString());
                    out.flush();
                    out.close();
                    }

              }

        }
         
         else {
              //Token失效，返回数组-3，重新登录
       	  List<RoomMessage> statusdata=Search.selectStatus3();
       	  if(statusdata!=null) {
           	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
           	String data1=jsonArray.toString();
           	System.out.println("这里的data1    "+data1.toString());
           	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
               if(json.size()>0){
                   for(int i=0;i<json.size();i++){
                       // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                       net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//                       System.out.println("这里的解析的    "+jsonObj);
                   }}
               System.out.println("第一行    "+json.getJSONObject(1));
               response.setContentType("text/html;charset=utf-8");
               PrintWriter out = response.getWriter();
               out.write(data1.toString());
               out.flush();
               out.close();
               }

         }
       
    }
          
          else {
              //Token为空返回数组-3
        	  List<RoomMessage> statusdata=Search.selectStatus3();
        	  if(statusdata!=null) {
            	  JSONArray jsonArray=JSONArray.fromObject(statusdata);
            	String data1=jsonArray.toString();
            	System.out.println("这里的data1    "+data1.toString());           	
            	net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(data1.toString());
                if(json.size()>0){
                    for(int i=0;i<json.size();i++){
                        // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                        net.sf.json.JSONObject jsonObj = json.getJSONObject(i);
//                        System.out.println("这里的解析的    "+jsonObj);
                    }}
                System.out.println("第一行    "+json.getJSONObject(1));
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(data1.toString());
                out.flush();
                out.close();
                }

          }
          
    }
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 

    	doPost(request, response);
    }
}