package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.AddressDto;
import com.example.bomobomo.domain.dto.UserDto;

import com.example.bomobomo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Controller
@Slf4j
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


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
    public String join(UserDto userDto, AddressDto addressDto, HttpServletRequest req, HashMap map) {

        String detail = req.getParameter("addressDetails");
        String addressDetail = addressDto.getAddressDetail() +" "+ detail;
        System.out.println("set설정 전 Dto주소 : " + addressDto.getAddressDetail());

        addressDto.setAddressDetail(addressDetail);

        System.out.println("set설정 후 Dto주소 : " + addressDto.getAddressDetail());
        System.out.println("유저정보 : " + userDto);
        System.out.println("주소정보 : " + addressDto);

        userService.register(userDto, addressDto);

        return "user/login";
    }

    @PostMapping("/login")
    public RedirectView login(String userId, String userPassword, HttpServletRequest req, RedirectAttributes redirectAttributes){
        UserDto userDto = null;

//        UserDto userDto = userService.find(userId, userPassword);
        req.getSession().setAttribute("userNumber", userDto.getUserNumber());
        req.getSession().setAttribute("userName",userDto.getUserName());
        req.getSession().setAttribute("userId", userDto.getUserId());

        System.out.println("로그인 컨트롤러 : " + userDto.getUserId());
        System.out.println("로그인 컨트롤러 : " + userDto.getUserName());
        System.out.println("로그인 컨트롤러 : " + userDto.getUserNumber());

        try {
            userDto = userService.find(userId, userPassword);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("isLogin", "0");
            return new RedirectView("/user/login");
        }

        return new RedirectView("/common/index");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req){
        req.getSession().invalidate();
        return "user/login";
    }



    @PostMapping("/idCheck")
    @ResponseBody
    public int userCheck(String userId) {

        int idCk = userService.idCheck(userId);

        return idCk;
    }


    @PostMapping("/nameCheck")
    @ResponseBody
    public int nameCheck(String userName) {

        int nameCk = userService.nameCheck(userName);

        return nameCk;
    }

}
