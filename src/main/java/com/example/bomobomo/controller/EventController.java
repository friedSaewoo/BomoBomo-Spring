package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.EventDetailDto;
import com.example.bomobomo.domain.dto.EventDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.EventVo;
import com.example.bomobomo.domain.vo.PageVo;
import com.example.bomobomo.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/event/*")
public class EventController {

    private final EventService eventService;

//    이벤트 메인페이지 리스트
    @GetMapping("/list")
    public String showEventListPage(Model model){

       List<EventVo> eventDtoList = eventService.findAll();
       model.addAttribute("list", eventDtoList);
        return "event/event";
    }

//   이벤트 상세페이지 이동
    @GetMapping(value = "/detail")
    public String showEventDetailPage(Long eventNumber, Model model) {

        EventVo eventVo = eventService.find(eventNumber);
        model.addAttribute("detail", eventVo);
        return "event/eventdetail";
    }
    //이벤트 컨트롤러 (신청페이지 이동)

//   이벤트 신청서페이지 이동
    @GetMapping("/payment")
    public String ShowEventApplicationPage(Long eventNumber, Model model) {

        EventVo eventVo = eventService.find(eventNumber);
        model.addAttribute("payment", eventVo);
        return "event/eventPayment";
    }


    //    이벤트 조회 이미지
    @Value("${file.eventImg}")
    private String fileEventImg;

    @GetMapping("/eventimg")
    public byte[] getEventImg(String fileFullPath) throws IOException {
        return FileCopyUtils.copyToByteArray(new File(fileEventImg, fileFullPath));
    }

//   직원 목록 전체 조회
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
    //    인재 채용 페이지 이동
    @GetMapping("/rec")
    public String showRecruitmentPage(){
        return "event/recruiment";
    }
}



