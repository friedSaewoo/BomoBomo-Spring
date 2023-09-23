package com.example.bomobomo.controller;

import com.example.bomobomo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//임시 컨트롤러
@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {


    private final NoticeService noticeService;

    @GetMapping("/faq")
    public String showFaqPage(){
        return "board/boardFaq";
    }


    @GetMapping("/notice")
    public String showNoticePage(){
        return "board/boardNotice";
    }


    @GetMapping("/detail")
    public void showNoticeDetailPage(Long noticeNumber, Model model){

        model.addAttribute("noticeDetail",  noticeService.selectOne(noticeNumber));

    }


    @GetMapping("/serviceReview")
    public String showServiceReviewPage(){
        return "board/boardServiceReview";
    }
}
