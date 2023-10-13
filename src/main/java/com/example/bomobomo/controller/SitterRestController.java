package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.SitterBoardDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.PageVo;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import com.example.bomobomo.service.SitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sitters")
public class SitterRestController {
    private final SitterService sitterService;

    @GetMapping("/sitterFind")
    public Map<String, Object> sitterList(String city, String cityAddr, Model model, Criteria criteria) {
        //현재 출력된 시터 수
        List<SitterBoardVo> sitterBoardList = sitterService.selectSitterAvg();

        if(city == null) {
            System.out.println("주소 정보가 없음 : " + city);
            int sitterTotal = sitterService.sitterTotalPeople();
            criteria.setAmount(6);
            Map<String, Object> sitterFindMap = new HashMap<>();
            sitterFindMap.put("sitterTotal", sitterTotal);
            //시터 정보 리스트
            sitterFindMap.put("sitterList", sitterService.sitterSelect(criteria));

            //페이지
            sitterFindMap.put("pageInfo", new PageVo(sitterService.sitterTotalPeople(), criteria));
            return sitterFindMap;

        } else {
            System.out.println("주소 정보가 있음 : " + city);
            //현재 출력된 시터 수
            int sitterAddrTotal = sitterService.sitterAddrTotal();
            criteria.setAmount(6);
            Map<String, Object> sitterFindMap = new HashMap<>();
//            model.addAttribute("sitterList", sitterService.addrCheck(criteria));
//            model.addAttribute("pageInfo", new PageVo(sitterService.sitterTotalPeople(), criteria));
            sitterFindMap.put("sitterTotal", sitterAddrTotal);
            //시터 정보 리스트
            sitterFindMap.put("sitterList", sitterService.addrCheck(criteria));
            //페이지
            sitterFindMap.put("pageInfo", new PageVo(sitterService.sitterAddrTotal(), criteria));
            return sitterFindMap;
        }


    }

    @GetMapping("/sitterAddrList")
    public Map<String, Object> sitterAddrList(String city, String cityAddr, Model model, Criteria criteria) {
        //주소로 출력된 시터 수
        int sitterAddrTotal = sitterService.sitterAddrTotal();
        criteria.setAmount(6);
        Map<String, Object> sitterFindMap = new HashMap<>();
//        model.addAttribute("sitterList", sitterService.addrCheck(criteria));
//        model.addAttribute("pageInfo", new PageVo(sitterService.sitterTotalPeople(), criteria));
        sitterFindMap.put("sitterTotal", sitterAddrTotal);
        //시터 정보 리스트
        sitterFindMap.put("sitterList", sitterService.addrCheck(criteria));
        //페이지
        sitterFindMap.put("pageInfo", new PageVo(sitterService.sitterAddrTotal(), criteria));
        return sitterFindMap;
    }

}
