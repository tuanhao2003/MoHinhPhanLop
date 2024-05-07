package com.example.springweb.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mapping {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/members_management")
    public String membersManagement(){
        return "memberManage";
    }
}
