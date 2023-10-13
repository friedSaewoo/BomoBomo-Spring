package com.example.bomobomo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//약관 페이지 이동 컨트롤러
@Controller
@RequestMapping("/policy/*")
public class PolicyController {


    @GetMapping("/privacyA")
    public String privacyA(){ return "policy/privacyA"; }

    @GetMapping("/privacyB")
    public String privacyB(){ return "policy/privacyB"; }

    @GetMapping("/service")
    public String servicePolicy(){ return "policy/service"; }

    @GetMapping("/personalInfo")
    public String personalInfoPolicy(){ return "policy/personalInfo"; }

}
