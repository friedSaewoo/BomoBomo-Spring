package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.OrderDto;
import com.example.bomobomo.domain.dto.SitterBoardDto;
import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.domain.vo.*;
import com.example.bomobomo.service.OrderService;
import com.example.bomobomo.service.ReviewService;
import com.example.bomobomo.service.SitterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/sitter/*")
@RequiredArgsConstructor
public class SitterController {

    private final SitterService sitterService;
    private final OrderService orderService;
    private final ReviewService reviewService;

    //    @LoggingPointcut
    // 전체 시터 리스트 페이지 컨트롤러
    @GetMapping("/sitterFind")
    public String sitterPage(Model model, Criteria criteria) {
        return "sitter/sitterFind";
    }

    // 선택한 시터 정보 컨트롤러
    @GetMapping("/sitterInfo")
    public void sitterInfo(Long empNumber, Model model) {
        EmpDto empDto = sitterService.sitterInfo(empNumber);
        List<SitterBoardDto> sitterBoardList = sitterService.selectSitterBoardList(empNumber);

        ArrayList<Double> sitterReview = sitterService.sitterReview(empNumber);

        if(sitterReview.size() == 0) {
            sitterReview.add(0.0);
        }
        double sum = 0;
        for (double num : sitterReview) {
            sum += num;
        }

        double sitterTotalReview = sum / sitterReview.size();

        String gender = empDto.getEmpGender();
        if (empDto.getEmpGender().equals("F")) {
            empDto.setEmpGender("여");
        } else if(empDto.getEmpGender().equals("M")) {
            empDto.setEmpGender("남");
        }
        System.out.println("dtoContent : " + empDto.getEmpContent());
//        String empContent = empDto.getEmpContent().replaceAll("\r\n", "\n");
//        empDto.setEmpContent(empContent);
//        System.out.println("=================================");
//        System.out.println("empContent : " + empContent);
        String empDate = empDto.getEmpDate().substring(0, 10);
        empDto.setEmpDate(empDate);
        System.out.println("시작일 : " + empDto.getEmpDate());
        model.addAttribute("emp", empDto);
        model.addAttribute("sitterBoardList", sitterBoardList);
        model.addAttribute("sitterTotalReview", sitterTotalReview);

    }

//    선택한 시터 신청 컨트롤러(현재 로그인 상태 확인)
    @GetMapping("/sitterRegister")
    public String sitterRegister(HttpServletRequest req) {

        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        if (userNumber == null) {
            return "/user/login";
        }
        return "/sitter/sitterRegister";
    }

//    선택한 시터 신청 컨트롤러
    @PostMapping("/sitterRegister")
    public String sitterRegister(HttpServletRequest req, Long sitterNumber,
                                 @RequestParam("sitterNum") Long empNumber) {

//        req.getParameter("sitterNum");
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");

        if (userNumber == null) {
            return "/user/login";
        }

        return "/sitter/sitterRegister";
    }

    //신청서 데이터 저장후 이동
    @PostMapping("/sitterApplication")
    public RedirectView applicationRegister(OrderDto orderDto, HttpServletRequest req, UserDto userDto) {
        // 저장 서비스 실행
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");

        orderDto.setUserNumber(userNumber);
//        orderService.register(orderDto);

        log.info(orderDto.getUserNumber().toString());
        // 신청서 내용 입력 후 이동
        return new RedirectView("/common/index");
    }

}
