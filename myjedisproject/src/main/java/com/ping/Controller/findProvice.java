package com.ping.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ping.Service.Impl.ProvinceServiceImpl;
import com.ping.Service.ProvinceService;
import com.ping.domain.Province;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findProvice")
public class findProvice extends HttpServlet {
    private ProvinceService provinceService=new ProvinceServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
       String json=provinceService.findAllJson();
       System.out.println(json);
      response.setContentType("application/json,charset=utf-8");
      response.getWriter().write(json);




    }

}
