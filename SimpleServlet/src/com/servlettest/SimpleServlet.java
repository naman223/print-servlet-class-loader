package com.servlettest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SimpleServlet extends HttpServlet {

  static int counter4 = 2;

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");

    response.getWriter().println("App4 Counter:" + counter4);


    Properties  properties = new Properties();
    if(counter4%2==0) {
      response.getWriter().println("Server ClassLoader: " + getClass().getClassLoader());
      properties.put("classloader1",getClass().getClassLoader());
      System.setProperties(properties);
    } else {
      Properties properties1 = System.getProperties();
      ClassLoader classLoader = (ClassLoader)properties1.get("classloader1");

      response.getWriter().println("Server ClassLoader...."+classLoader);
      response.getWriter().println("VH ClassLoader...."+getClass().getClassLoader());

      if(classLoader.equals(getClass().getClassLoader())) {
        response.getWriter().println("Same....");
      } else {
        response.getWriter().println("Different....");
      }
    }
    counter4++;

    try {
      Thread.sleep(30);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
