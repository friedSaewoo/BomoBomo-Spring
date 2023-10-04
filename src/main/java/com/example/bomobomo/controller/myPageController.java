package com.example.bomobomo.controller;


import com.example.bomobomo.domain.dto.*;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.MyPageSitterVo;
import com.example.bomobomo.domain.vo.PageVo;
import com.example.bomobomo.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private final SitterBoardService sitterBoardService;
    private final MyPageService myPageService;


    //mypage 메인 으로 이동
    @GetMapping("/main")
    public String showMainPage(HttpServletRequest req){
        Long userNumber=(Long)req.getSession().getAttribute("userNumber");

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

    // 결제 내역 페이지로 이동 (매핑방법 확인)
    @GetMapping("/buy")
    public String showBuyPage(Model model, HttpServletRequest req, Criteria criteria){
        criteria.setAmount(5);
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        PageVo pageVo = new PageVo(myPageService.getTotal(userNumber), criteria);
//        Criteria criteria = new Criteria();

        //myPageSitterVo의 값을 모델 객체에 담아서 뿌려준다

        model.addAttribute("buyList",myPageService.findSitterList(criteria, userNumber));
        model.addAttribute("pageVo", pageVo);
        return "mypage/buyPage";
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


    // buy페이지에서 시터 리뷰로 이동하는 컨트롤러
    @GetMapping("/siterreview")
    public String showsiterreviewPage(HttpServletRequest req,
                                      @ModelAttribute("matchNumber") Long matchNumber,
                                      @ModelAttribute("empNumber") Long empNumber ,
                                      Model model){

        model.addAttribute("title", sitterBoardService.findTitle(matchNumber));
        return "myPage/siterReview";
    }
    //게시물 작성후 이동하는 컨트롤러
    @PostMapping("/siterreview")
    public RedirectView siterreviewWriter(SitterBoardDto sitterBoardDto, HttpServletRequest req){
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        sitterBoardDto.setUserNumber(userNumber);
        sitterBoardService.register(sitterBoardDto);
        // main에서 userNumber를 받고 있기때문에 따로 req하지 않음
        return new RedirectView("/mypage/main");
    }

}

