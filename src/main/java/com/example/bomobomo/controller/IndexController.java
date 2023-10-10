package com.example.bomobomo.controller;

import com.example.bomobomo.domain.vo.EventBoardVo;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import com.example.bomobomo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.Service;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/common/*")
public class IndexController {


    private final ReviewService reviewService;

//    화면 볼려고 만든 임시 컨트롤러
    @GetMapping("/index")
    public String showMainPage(Model model){

        List<SitterBoardVo> sitterBoardVo = reviewService.findTopCount();
        List<EventBoardVo> eventBoardVo = reviewService.findEventTopCount();


        model.addAttribute("reviewTop", sitterBoardVo);
        model.addAttribute("eventReviewTop", eventBoardVo);

        log.info(sitterBoardVo.toString());
        log.info(eventBoardVo.toString());

        return "common/index";
    }






}
