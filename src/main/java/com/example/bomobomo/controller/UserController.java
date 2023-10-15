package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.AddressDto;
import com.example.bomobomo.domain.dto.UserDto;

import com.example.bomobomo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
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
    public String join(UserDto userDto, AddressDto addressDto, HttpServletRequest req) {

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


        try {
            userDto = userService.find(userId, userPassword);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("isLogin", "0");
            return new RedirectView("/user/login");
        }

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

    //회원 아이디 확인 메서드
    @PostMapping("/idFindOk")
    public String idFindOk(String userName, String userEmail, Model model, UserDto userDto) {

        userDto = userService.idFindOk(userName, userEmail);

        model.addAttribute("user", userDto);

        System.out.println("컨트롤러 진입.");
        System.out.println("가입일 : " + userDto.getRegisterDate());
        System.out.println("아이디 : " + userDto.getUserId());

        return "user/idFindOk";
    }


    //중복 아이디 확인 메서드
    @PostMapping("/idCheck")
    @ResponseBody
    public int userCheck(String userId) {

        int idCk = userService.idCheck(userId);

        return idCk;
    }

    //등록된 회원 이름 확인 메서드
    @PostMapping("/nameCheck")
    @ResponseBody
    public int nameCheck(String userName) {

        int nameCk = userService.nameCheck(userName);
        System.out.println("컨트롤러확인");
        return nameCk;
    }

    //등록된 회원 이메일 확인 메서드
    @PostMapping("/emailCheck")
    @ResponseBody
    public int emailCheck(String userEmail, String userName) {
        int emailCk = userService.emailCheck(userEmail, userName);
        return emailCk;
    }

    //등록된 회원 정보 확인 및 패스워드 메서드
    @PostMapping("/pwEmailCheck")
    @ResponseBody
    public int pwEmailCheck(String userEmail, String userName, String userId) {
        int emailCk = userService.pwEmailCheck(userEmail, userName, userId);
        return emailCk;
    }


    // 비밀번호 찾기 폼
//   	@RequestMapping(value = "/find_pw_form.do")
//   	public String find_pw_form() throws Exception{
//
//        System.out.println("비밀번호 찾기 매서드 진입");
//   		return "/member/find_pw_form";
//   	}


}
