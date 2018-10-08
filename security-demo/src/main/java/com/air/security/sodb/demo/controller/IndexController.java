package com.air.security.sodb.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/hello")
    public String helloPage() {
        System.out.println("打开了hello,但渲染的是index.jsp");
        return "index";
    }
}
