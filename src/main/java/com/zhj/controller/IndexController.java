package com.zhj.controller;

import com.zhj.entity.Salary;
import com.zhj.entity.TestBean;
import com.zhj.entity.TestIntroductionBean;
import com.zhj.entity.User;
import com.zhj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhj on 2018/3/23.
 */

@Controller
public class IndexController {
    @Autowired
    public SimpleDateFormat simpleDateFormat ;

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/")
    public  String index(HttpServletRequest request , HttpServletResponse response,@ModelAttribute("salary")Salary salary ,Map<String,Object> map ){
//        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
//        User user0 = (User) ac.getBean("user");

        return "index";
    }

    @RequestMapping("test")
    @ResponseBody
    public  String test(){
        System.out.println(simpleDateFormat.format(new Date()));
        User user = new User();
        user.setAge(26);
        user.setName("zhangsan");
        user.setSex("m");
        userService.save(user);

        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < 5; i++) {
            User obj = new User();
            obj.setAge( 1 + i * 3 );
            obj.setName( "name"+i );
            obj.setSex(i % 2 == 0 ? "m":"f");
            userList.add(obj);
        }
        userService.batchSave(userList);


        List<User> persons = userService.getUsers();
        System.out.println("-----------得到所有的user "+ persons.size());
        for (User u : persons) {
            System.out.println(u);
        }

        return  "hello";
    }

    @RequestMapping("hello")
    @ResponseBody
    public  String hello(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(simpleDateFormat.format(new Date()));

        TestBean testBean = SpringContextHolder.getBean("testBean");
        double money = testBean.test();
        System.out.println("hello money="+money);

        ((TestIntroductionBean)testBean).say();

        return  "hello";
    }



    private static final double ShebaoJishu = 25401;
    private static final double GongjijinJishu = 25401;
    private static final double ShebaoJishuChengdu = 15333;
    private static final double GongjijinJishuChengdu = 20972;

    @RequestMapping(value = "tax",method = RequestMethod.GET)
    public String tax(@Valid Salary salary , Errors  errors , String city , Map<String,Object> map ){
        if(errors.hasErrors()) {
            return "index";
        }

        boolean isBeijing = true;
        if("028".equals(city)){
            isBeijing = false ;
        }
        System.out.println("city="+city);


        double shouru =  salary.getShouru();
        double sbjs = shouru;
        double gjjjs = shouru ;

        if (isBeijing){
            if(sbjs > ShebaoJishu){
                sbjs = ShebaoJishu ;
            }
            if(gjjjs > GongjijinJishu){
                gjjjs = GongjijinJishu ;
            }
        }else{
            if(sbjs > ShebaoJishuChengdu){
                sbjs = ShebaoJishuChengdu ;
            }
            if(gjjjs > GongjijinJishuChengdu){
                gjjjs = GongjijinJishuChengdu ;
            }
        }

        double yanglao = isBeijing ? 0.08 * sbjs :0.08 * sbjs ;
        double yiliao = isBeijing ? 0.02 * sbjs :  0.02 * sbjs;
        double shiye = isBeijing ? 0.002 * sbjs : 0.004 * sbjs;
        double gongjijin = isBeijing ? 0.12  * sbjs :   0.06 * gjjjs;


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