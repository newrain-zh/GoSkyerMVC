package com.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by zzqno on 2017-3-26.
 */
@WebServlet("/testServlet")
public class TestServlert extends HttpServlet{

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get:"+request.getParameter("xxx"));
        System.out.println("get:"+request.getParameter("yyy"));
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        System.out.println("request.getContentType()"+request.getContentType());
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] hobby = request.getParameterValues("hobby");

        System.out.println(username+","+password+","+ Arrays.toString(hobby));

        /*
         * 测试获取所有请求参数的名称
         */
        Enumeration<?> names2 = request.getParameterNames();
        while(names2.hasMoreElements()){
            System.out.println(names2.nextElement());
        }
//      Iterator<String> names = (Iterator<String>) request.getParameterNames();
//      while(names.hasNext()){
//          System.out.println(names.next());
//      }

        /*
         * 获取所有请求参数，封装到Map中
         */
        Map<String,String[]> map = (Map<String,String[]>)request.getParameterMap();
        for(String name:map.keySet()){
            String[] values = map.get(name);
            System.out.println(name+"="+Arrays.toString(values));
        }
    }

}
