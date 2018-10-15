package com.zhj.bean;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created by zhj on 2018/10/15.
 */


@Component
public class HelloView implements  View {

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        response.getWriter().print("Welcome to View:" + new Date());
    }
}