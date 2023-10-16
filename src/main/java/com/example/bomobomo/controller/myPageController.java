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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private final EventBoardService eventBoardService;


    //mypage 메인 으로 이동
    @GetMapping("/main")
    public String showMainPage(HttpServletRequest req,Model model){
        Long userNumber=(Long)req.getSession().getAttribute("userNumber");

        try {
            myPageService.findMatch(userNumber);
            model.addAttribute("match",myPageService.findMatch(userNumber));
            log.info("예외처리"+myPageService.findMatch(userNumber).toString());

        } catch (NullPointerException e) {
            return "mypage/myPageMain";
        }
        log.info("============================1번");
        myPageService.findEmpInfoImg(myPageService.findMatch(userNumber).getEmpNumber());
        model.addAttribute("empInfoImg",myPageService.findEmpInfoImg(myPageService.findMatch(userNumber).getEmpNumber()));
        log.info("============================1.5번");
        log.info(myPageService.findEmpInfoImg(myPageService.findMatch(userNumber).getEmpNumber()).toString());
        log.info("============================2번");
        myPageService.findEmpActItemImg(myPageService.findMatch(userNumber).getEmpNumber());
        model.addAttribute("empActItemImgList",myPageService.findEmpActItemImg(myPageService.findMatch(userNumber).getEmpNumber()));
        log.info("============================3번");
        myPageService.findMatchEmpRating(myPageService.findMatch(userNumber).getEmpNumber());
        model.addAttribute("rating",myPageService.findMatchEmpRating(myPageService.findMatch(userNumber).getEmpNumber()));
        return "mypage/myPageMain";
    }
    // 신청서 페이지 이동
    @GetMapping("/application")
    public String showapplicationPage(HttpServletRequest req,Model model){
        Long userNumber=(Long)req.getSession().getAttribute("userNumber");
        System.out.println(userNumber);
        // Order 조회하는 서비스
        //if
        // 수정 페이지
        //(model에 값을 넣어서 뿌려준다)
        try {
            orderService.findOrder(userNumber);
            System.out.println(orderService.findOrder(userNumber).toString());
            model.addAttribute("order",orderService.findOrder(userNumber));
        } catch (NullPointerException e) {
            return "mypage/applicationPage";
        }


        return "mypage/updateApplicationPage";
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
    @PostMapping("/updateApplication")
    public RedirectView updateApplication(OrderDto orderDto,HttpServletRequest req){
        Long userNumber =(Long)req.getSession().getAttribute("userNumber");
        log.info("=============================오더{}",orderDto);
        orderDto.setUserNumber(userNumber);
        orderService.orderUpdate(orderDto);
        log.info("=============================오더{}",orderDto);

        return new RedirectView("/mypage/main");
    }

    // 결제 내역 페이지로 이동 (매핑방법 확인)
    @GetMapping("/buy")
    public String showBuyPage(Model model, HttpServletRequest req, Criteria criteria){
        criteria.setAmount(5);
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        PageVo pageVo = new PageVo(myPageService.getTotal(userNumber), criteria);
//        Criteria criteria = new Criteria();


        model.addAttribute("buyList",myPageService.findSitterList(criteria, userNumber));
        model.addAttribute("pageVo", pageVo);

        return "mypage/buyPage";
    }
    @GetMapping("/buyEvent")
    public String showBuyEventPage(Model model, HttpServletRequest req, Criteria criteria){
        criteria.setAmount(5);
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");

//        Criteria criteria = new Criteria();

        PageVo pageVO=new PageVo(myPageService.findEventTotal(userNumber),criteria);
        model.addAttribute("eventList",myPageService.findEventList(criteria,userNumber));
        model.addAttribute("pageVO",pageVO);
        return "mypage/buyEventPage";
    }

    //회원정보 수정 창으로 이동
    @GetMapping("/userManage")
    public String showuserManagePage(HttpServletRequest req,Model model){
        Long userNumber=(Long)req.getSession().getAttribute("userNumber");

        myPageService.findUser(userNumber);
        model.addAttribute("user",myPageService.findUser(userNumber));
        myPageService.findUserAddress(userNumber);
        model.addAttribute("userAddress",myPageService.findUserAddress(userNumber));


        System.out.println(userNumber);


        return "mypage/userManageMentPage";
    }
    //회원정보 수정후 이동
    @PostMapping("/userManage")
    public RedirectView userManageModify(UserDto userDto, AddressDto addressDto,HttpServletRequest req){
        Long userNumber=(Long)req.getSession().getAttribute("userNumber");

        userDto.setUserNumber(userNumber);
        addressDto.setUserNumber(userNumber);

        System.out.println(userNumber);


        userService.modify(userDto);
        adressService.modify(addressDto);

        log.info(userDto.toString());
        log.info(addressDto.toString());


        return new RedirectView("/mypage/main");
    }




    @GetMapping("/reviewwrite")
    public String showreviewwritePage(HttpServletRequest req,
                                      @ModelAttribute("eventPayNumber") Long eventPayNumber,
                                      Model model){
        Long userNUmber =(Long)req.getSession().getAttribute("userNumber");
        System.out.println(eventPayNumber);
        model.addAttribute("eventNameNumber",eventBoardService.findEventTitle(eventPayNumber));


        return "mypage/reviewWrite";
    }

    @PostMapping("/reviewwrite")
    public RedirectView eventreviewwrite(EventBoardDto eventBoardDto, HttpServletRequest req,
                                         @RequestParam("eventBoardImg")MultipartFile file){
        Long userNumber=(Long) req.getSession().getAttribute("userNumber");
        eventBoardDto.setUserNumber(userNumber);

        System.out.println(eventBoardDto);
        eventBoardService.registerAndFileproc(eventBoardDto,file);

        return new RedirectView("/mypage/main");
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

        return new RedirectView("/mypage/main");
    }

}

