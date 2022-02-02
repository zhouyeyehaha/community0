package com.jc.community.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OneController {

    @RequestMapping("/one")
    @ResponseBody
    public String One(){
        return "hello";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
    }
    //GET请求的两种传参方式

    // /student?name=haha&limit=20
    @RequestMapping("/student")
    @ResponseBody
    public String student(@RequestParam(name = "name",required = false,defaultValue = "123") int name,
                          @RequestParam(name = "limit",required = false,defaultValue = "333") int limit){
        System.out.println(name);
        System.out.println(limit);
        return "students";
    }

    // /student/123
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String path(@PathVariable("id") int id){ //@PathVariable 路径变量
        System.out.println(id);
        return "path";
    }

    //POST请求

    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String savestudents(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "ok";
    }

    // 响应HTML数据

    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView teacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","张三");
        modelAndView.addObject("age","13");
        modelAndView.setViewName("/demo/view");
        return modelAndView;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)  //返回的是加了@ResponseBody注解的结果  模板没解析  很奇怪
    public String school(Model model){
        model.addAttribute("name","学校");
        model.addAttribute("age",20);
        return "/demo/view";
    }

    //响应JSON数据（异步请求）
    //java对象 --> json字符串 --> js对象
    //自动封装成josn
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> emp(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age","23");
        return map;
    }

    @RequestMapping(path = "/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> emps(){
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age","23");
        list.add(map);
        return list;
    }
}
