package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.SitterBoardDto;
import com.example.bomobomo.domain.vo.*;
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
    public Map<String, Object> sitterList(String cityName, String countryName, Integer actNumber, Model model, Criteria criteria) {
//        List<SitterBoardVo> sitterBoardList = sitterService.selectSitterAvg();

        System.out.println("주소 정보 : " + cityName + " --> " + countryName);
            //현재 출력된 시터 수
            int sitterTotal = sitterService.sitterTotalPeople(cityName, countryName, actNumber);
            criteria.setAmount(9);

//            List<EmpListVo> sitterActImgList =sitterService.sitterActImg();
//        System.out.println("내용물 화긴 : " + sitterActImgList);
//            for(int i = 0; i < sitterActImgList.size(); i++) {
//                List<ActVo> actVo = sitterService.sitterPossibleList(sitterActImgList.get(i).getEmpNumber());
//
//                System.out.println("화긴 : " + actVo);
//                Map<String, Object> sitterFindMap = new HashMap<>();
//                sitterFindMap.put("actVoList", actVo);
//
//            }



//        System.out.println("테스트임 : " + sitterService.sitterActImg());
//            for(int i = 0; i < sitterActImgList.size(); i++) {
////                System.out.println("UUID : " + sitterActImgList.get(i).getActImgList().get(i).getActImgUuid());
////                System.out.println("ImgName : " + sitterActImgList.get(i).getActImgList().get(i).getActImgName());
////                System.out.println("ActName : " + sitterActImgList.get(i).getActImgList().get(i).getActName());
//
//            }

            Map<String, Object> sitterFindMap = new HashMap<>();
            sitterFindMap.put("sitterTotal", sitterTotal);
            //시터 정보 리스트
            sitterFindMap.put("sitterList", sitterService.sitterSelect(criteria, cityName, countryName, actNumber));

            //페이지
            sitterFindMap.put("pageInfo", new PageVo(sitterService.sitterTotalPeople(cityName, countryName, actNumber), criteria));

//        if(city != null && cityAddr != null) {
//
//            System.out.println("주소 정보가 있음 : " + city + " --> " + cityAddr);
//            //현재 출력된 시터 수
//            int sitterAddrTotal = sitterService.sitterAddrTotal();
//            criteria.setAmount(6);
//            Map<String, Object> sitterAddrFindMap = new HashMap<>();
////            model.addAttribute("sitterList", sitterService.addrCheck(criteria));
////            model.addAttribute("pageInfo", new PageVo(sitterService.sitterTotalPeople(), criteria));
//            sitterAddrFindMap.put("sitterTotal", sitterAddrTotal);
//            //시터 정보 리스트
//            sitterAddrFindMap.put("sitterList", sitterService.addrCheck(criteria));
//            //페이지
//            sitterAddrFindMap.put("pageInfo", new PageVo(sitterService.sitterAddrTotal(), criteria));
//            return sitterAddrFindMap;
//        }

            return sitterFindMap;
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
