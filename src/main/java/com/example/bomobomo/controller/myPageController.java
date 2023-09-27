package com.example.bomobomo.controller;


import com.example.bomobomo.domain.dto.AddressDto;
import com.example.bomobomo.domain.dto.OrderDto;
import com.example.bomobomo.domain.dto.SubmitOrderDto;
import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.service.AdressService;
import com.example.bomobomo.service.OrderService;
import com.example.bomobomo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
public class myPageController {
    private final OrderService orderService;
    private final AdressService adressService;
    private final UserService userService;


    //mypage 메인 으로 이동
    @GetMapping("/main")
    public String showMainPage(HttpServletRequest req){
        Long userNumber=(Long)req.getSession().getAttribute("userNumber");
        System.out.println(userNumber);
        return "mypage/myPageMain";
    }
    // 신청서 페이지 이동
    @GetMapping("/application")
    public String showapplicationPage(HttpServletRequest req){
        Long userNumber=(Long)req.getSession().getAttribute("userNumber");
        System.out.println(userNumber);
        return "mypage/applicationPage";
    }

    //신청서 데이터 저장후 이동
    @PostMapping("/application")
    public RedirectView applicationRegister(OrderDto orderDto,HttpServletRequest req){
        // 저장 서비스 실행
        Long userNumber=(Long)req.getSession().getAttribute("userNumber");
        System.out.println(userNumber);
        orderDto.setUserNumber(userNumber);
        orderService.register(orderDto);


        log.info(orderDto.getUserNumber().toString());
        // 신청서 내용 입력 후 이동
        return new RedirectView("/mypage/main");
    }

    @GetMapping("/buy")
    public String showBuyPage(){
        return "myPage/buyPage";
    }

    //회원정보 수정 창으로 이동
    @GetMapping("/userManage")
    public String showuserManagePage(HttpServletRequest req){
        Long userNumber=(Long)req.getSession().getAttribute("userNumber");
        String userId= (String)req.getSession().getAttribute("userId");
        String userName=(String)req.getSession().getAttribute("userName");

        System.out.println(userNumber);
        System.out.println(userId);
        System.out.println(userName);

        return "myPage/userManageMentPage";
    }
    //회원정보 수정후 이동
    @PostMapping("/userManage")
    public RedirectView userManageModify(UserDto userDto, AddressDto addressDto,HttpServletRequest req){
        Long userNumber=(Long)req.getSession().getAttribute("userNumber");
        String userId= (String)req.getSession().getAttribute("userId");
        String userName=(String)req.getSession().getAttribute("userName");
        userDto.setUserNumber(userNumber);
        addressDto.setUserNumber(userNumber);

        System.out.println(userNumber);
        System.out.println(userId);
        System.out.println(userName);

        userService.modify(userDto);
        adressService.modify(addressDto);

        log.info(userDto.toString());
        log.info(addressDto.toString());


        return new RedirectView("/mypage/main");
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
