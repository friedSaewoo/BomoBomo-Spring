package com.example.bomobomo.controller;


import com.example.bomobomo.domain.dto.OrderDto;
import com.example.bomobomo.domain.dto.SubmitOrderDto;
import com.example.bomobomo.service.OrderService;
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
