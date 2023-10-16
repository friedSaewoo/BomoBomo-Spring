package com.example.bomobomo.controller;


import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.EventApplyDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.EmpVo;
import com.example.bomobomo.domain.vo.EventPayVo;
import com.example.bomobomo.domain.vo.PageVo;
import com.example.bomobomo.mapper.EventMapper;
import com.example.bomobomo.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
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

//   직원 조회 이미지
    @Value("${file.empImg}")
    private String fileEmpImg;

    @GetMapping("/empImg")
    public byte[] getEmpImg(String fileFullPath) throws IOException {
        return FileCopyUtils.copyToByteArray(new File(fileEmpImg, fileFullPath));
    }

//   직원 목록 전체 조회
    @GetMapping("/empIntro/{page}")
    public Map<String, Object> showEmpList(@PathVariable("page")int page, Criteria criteria){

        criteria.setPage(criteria.getPage());
        criteria.setAmount(6);
        criteria.setPage(page);

        List<EmpVo> empVoList = eventService.findEmpAll(criteria);

        Map<String,Object> eventMap = new HashMap<>();
        eventMap.put("empIntro",empVoList);
        eventMap.put("pageInfo",new PageVo(eventService.getTotal(), criteria));

        return eventMap;
    }
}






















