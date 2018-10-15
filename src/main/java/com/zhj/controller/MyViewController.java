package com.zhj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhj on 2018/10/15.
 */

@RequestMapping(value="/springmvc")
@Controller
public class MyViewController {

    @RequestMapping("testMyView")
    public String testView(){
        System.out.println("testView");
		return "helloView";
    }

    @RequestMapping("testJspView")
    public ModelAndView testJspView(){
        System.out.println("testJspView");
        ModelAndView mv = new ModelAndView("jspView");
        mv.addObject("name","zhuhaijun");
        return mv;
    }


}