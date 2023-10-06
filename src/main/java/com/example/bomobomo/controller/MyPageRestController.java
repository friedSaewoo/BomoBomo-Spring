package com.example.bomobomo.controller;

import com.example.bomobomo.domain.vo.*;
import com.example.bomobomo.service.EventBoardService;
import com.example.bomobomo.service.MyPageService;
import com.example.bomobomo.service.SitterBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/myPages")
@RequiredArgsConstructor
public class MyPageRestController {
    private final MyPageService myPageService;
    private final SitterBoardService sitterBoardService;
    private final EventBoardService eventBoardService;
//     파일경로 확인 , 시터 결제내역 이미지
//    @Value("${file.dir}")
//    private String fileDir;
//
//    @GetMapping("/img")
//    public byte[] getPfpImg(String fileFullPath) throws IOException {
//        return FileCopyUtils.copyToByteArray(new File(fileDir, fileFullPath));
//    }
// 이벤트 결제 내역 이미지
//    @Value("${file.dir}")
//    private String fileDir;
//
//    @GetMapping("/eventimg")
//    public byte[] getEventImg(String fileFullPath) throws IOException {
//        return FileCopyUtils.copyToByteArray(new File(fileDir, fileFullPath));
//    }
    // 마이페이지 시터 이용후기 사용자가 작성한 내용만 출력
    @GetMapping("/sitterReviewList")
    public Map<String, Object> showEventReviewList(HttpServletRequest req, Criteria criteria){
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        PageVo pageVo = new PageVo(sitterBoardService.findSitterReviewTotal(userNumber), criteria);
        List<SitterBoardVo> sitterBoardVoList = sitterBoardService.findAll(criteria,userNumber);

        Map<String, Object> map = new HashMap<>();
        map.put("pageVo", pageVo);
        map.put("list", sitterBoardVoList);
        return map;
    }

    // 마이페이지 이벤트 이용후기 사용자가 작성한 내용만 출력
    @GetMapping("/eventReviewList")
    public Map<String, Object> showSitterReviewList(HttpServletRequest req, Criteria criteria){
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        PageVo pageVO = new PageVo(eventBoardService.findEventReviewTotal(userNumber), criteria);
        List<EventBoardVo> eventBoardVoList = eventBoardService.findEventAll(criteria,userNumber);

        Map<String, Object> map = new HashMap<>();
        map.put("pageVO", pageVO);
        map.put("eventlist", eventBoardVoList);
        return map;
    }
    //유저가 매칭을 한 직원의 정보를 출력
    @PostMapping("/matchEmpInfo/{userNumber}")
    public Map<String , Object> findMatchEmpInfo(HttpServletRequest req, @PathVariable("userNumber") Long userNumber){
//        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        System.out.println("로그인 번호 : "+userNumber);
        List<MatchEmpInfoVo> matchEmpInfoVoList = myPageService.findMachEmpInfoList(userNumber);
        MatchEmpRatingAvgVo matchEmpRatingAvgVo = myPageService.findMatchEmpAvg(userNumber);

        Map<String,Object> map =new HashMap<>();
        // 직원의 활동내용을 하나씩 뽑아야함
        map.put("infoList",matchEmpInfoVoList);
        map.put("avg",matchEmpRatingAvgVo);

        return map;
    }




}









