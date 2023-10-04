package com.example.bomobomo.controller;

import com.example.bomobomo.service.MailService;
import com.example.bomobomo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mail/*")
public class MailController {

    private final MailService mailService;
    private final UserService userService;

    @GetMapping("/userMail")
    public String MailPage(){
        return "user/mailTest";
    }

    @ResponseBody
    @PostMapping("/mail")
    public String MailSend(String mail){
        System.out.println("메일 컨트롤러 타는지 확인");
       int number = mailService.sendMail(mail);

       String num = "" + number;

       return num;
    }


    @ResponseBody
    @PostMapping("/pwFindOk")
    public String pwFindOk(String userEmail, String userId){
        System.out.println("메일 컨트롤러 타는지 확인");
       int number = mailService.reworkPwMail(userEmail, userId);

        System.out.println("메일컨트롤러 유저 : " + userId);

       String num = "" + number;


       return num;
    }

}