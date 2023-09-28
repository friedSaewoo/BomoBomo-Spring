package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.EventDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.PageVo;
import com.example.bomobomo.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/event/*")
public class EventController {

    private final EventService eventService;

    //화면 임시
    @GetMapping("/list")
    public String showEventListPage(Model model){

       List<EventDto> eventDtoList = eventService.findAll();
       model.addAttribute("list", eventDtoList);
        return "event/event";
    }



    @GetMapping("/empIntro")
    public String showEmpList(Criteria criteria, Model model){
        log.info("> request : {}", criteria);
        criteria.setPage(criteria.getPage());
        criteria.setAmount(3);

        List<EmpDto> empDtoList = eventService.findEmpAll(criteria);
        log.info("> List : {}", empDtoList);
        model.addAttribute("empIntro", empDtoList);
        model.addAttribute("pageInfo", new PageVo(eventService.getTotal(), criteria));
        return "event/employeeIntro" ;
    }



}
