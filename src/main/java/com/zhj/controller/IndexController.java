package com.zhj.controller;

import com.zhj.entity.Salary;
import com.zhj.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by zhj on 2018/3/23.
 */

@Controller
public class IndexController {
    @Autowired
    public SimpleDateFormat simpleDateFormat ;

    @RequestMapping(value = "/")
    public  String index(HttpServletRequest request , HttpServletResponse response,Map<String,Object> map){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
        User user = (User) ac.getBean("user");
        request.setAttribute("time", simpleDateFormat.format(new Date()));
        request.setAttribute("user", user);
        System.out.println("---------hello world----------------");

        map.put("salary", new Salary());
        return "index";
    }


    @RequestMapping("hello")
    @ResponseBody
    public  String hello(){
        System.out.println(simpleDateFormat.format(new Date()));
        return  "hello";
    }

    private static final double ShebaoJishu = 23118;
    private static final double GongjijinJishu = 23118;
    @RequestMapping(value = "tax",method = RequestMethod.POST)
    public String tax(@Valid Salary salary , Errors  errors , Map<String,Object> map ){
        if(errors.hasErrors()) {
            return "index";
        }

        double shouru =  salary.getShouru();
        double sbjs = shouru;
        double gjjjs = shouru ;
        if(sbjs > ShebaoJishu){
            sbjs = ShebaoJishu ;
        }
        if(gjjjs > GongjijinJishu){
            gjjjs = GongjijinJishu ;
        }

        double yanglao = 0.08 * sbjs ;
        double yiliao = 0.02 * sbjs;
        double shiye = 0.002 * sbjs;
        double gongjijin = 0.12 * gjjjs;
        double xiaoji = yanglao + yiliao + shiye +  gongjijin ;
        double jiaoshuijine = shouru - xiaoji ;

        double geshui = 0 ;
        if(jiaoshuijine > 3500){
           if(jiaoshuijine - 3500 <= 1500 ){
               geshui =  (jiaoshuijine - 3500) * 0.03 ;
           }else if(jiaoshuijine - 3500 > 1500  &&  jiaoshuijine - 3500 < 4500 ){
               geshui =  (jiaoshuijine - 3500) * 0.1 - 105  ;
           }else if (jiaoshuijine - 3500 > 4500  &&  jiaoshuijine - 3500 < 9000 ){
               geshui =  (jiaoshuijine - 3500) * 0.2 - 555  ;
           }else if (jiaoshuijine - 3500 > 9000  &&  jiaoshuijine - 3500 < 35000 ){
               geshui =  (jiaoshuijine - 3500) * 0.25 - 1005  ;
           }else if (jiaoshuijine - 3500 > 35000  &&  jiaoshuijine - 3500 < 55000 ){
               geshui =  (jiaoshuijine - 3500) * 0.30 - 2755  ;
           }else if (jiaoshuijine - 3500 > 55000  &&  jiaoshuijine - 3500 < 80000 ){
               geshui =  (jiaoshuijine - 3500) * 0.35 - 5505  ;
           }else if (jiaoshuijine - 3500 > 80000 ){
               geshui =  (jiaoshuijine - 3500) * 0.45 - 13505  ;
           }
        }
        double shuihou = jiaoshuijine - geshui ;
        salary.setGeshui(geshui);
        salary.setGongjijin(gongjijin);
        salary.setShiye(shiye);
        salary.setShuihou(shuihou);
        salary.setYanglao(yanglao);
        salary.setYiliao(yiliao);
        salary.setXiaoji(xiaoji);
        
        System.out.println(salary);

        map.put("salary",salary);
        return "tax";
    }

}