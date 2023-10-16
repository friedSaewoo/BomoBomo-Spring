package com.example.bomobomo.controller;


import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.EventApplyDto;
import com.example.bomobomo.domain.vo.EventPayVo;
import com.example.bomobomo.mapper.EventMapper;
import com.example.bomobomo.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/events/*")
@RequiredArgsConstructor
public class EventRestController {
    private final EventService eventService;

    private EventMapper eventMapper;

//  이벤트 결제 후 정보 넘기기
    @PostMapping(value = "complete")
    public String applyEvent(@RequestBody EventPayVo eventPayVo) throws Exception {

        System.out.println(eventPayVo.getUserNumber());
        System.out.println(eventPayVo.getEventNumber());

        eventService.saveEvent(eventPayVo);
        eventService.savePayment(eventPayVo);

       return "complete";
    }
}

