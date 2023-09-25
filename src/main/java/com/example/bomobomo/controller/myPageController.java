package com.example.bomobomo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/mypage/*")
public class myPageController {

    @GetMapping("/main")
    public String showMainPage(){
        return "myPage/myPageMain";
    }

    @GetMapping("/application")
    public String showapplicationPage(){
        return "myPage/applicationPage";
    }

    @GetMapping("/buy")
    public String showBuyPage(){
        return "myPage/buyPage";
    }

    @GetMapping("/userManage")
    public String showuserManagePage(){
        return "myPage/userManageMentPage";
    }

    @GetMapping("/reviewwrite")
    public String showreviewwritePage(){
        return "myPage/reviewWrite";
    }

    @GetMapping("/siterreview")
    public String showsiterreviewPage(){
        return "myPage/siterReview";
    }


}
