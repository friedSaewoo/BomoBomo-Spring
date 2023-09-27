package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.UserDto;

import com.example.bomobomo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@Slf4j
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
//    private final EmailService emailService;

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/join")
    public String join(){
        return "user/join";
    }

    @GetMapping("/idFind")
    public String selectId() {

        return "user/idFind";
    }

    @GetMapping("/pwFind")
    public String selectPw() {

        return "user/pwFind";
    }

    @PostMapping("/join")
    public String join(UserDto userDto, HttpServletRequest req) {

//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        userDto.setRegisterDate(formatter.format(date));

        userService.register(userDto);


        System.out.println("날짜는 : " + userDto.getRegisterDate());


        return "user/login";
    }

    @PostMapping("/login")
    public RedirectView login(String userId, String userPassword, HttpServletRequest req){

        UserDto userDto = userService.find(userId, userPassword);
        req.getSession().setAttribute("userNumber", userDto.getUserNumber());
        req.getSession().setAttribute("userName",userDto.getUserName());
        req.getSession().setAttribute("userId", userDto.getUserId());

        System.out.println("로그인 컨트롤러 : " + userDto.getUserId());
        System.out.println("로그인 컨트롤러 : " + userDto.getUserName());
        System.out.println("로그인 컨트롤러 : " + userDto.getUserNumber());

        return new RedirectView("/common/index");
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest req){
        req.getSession().invalidate();
        return "user/login";
    }


}
