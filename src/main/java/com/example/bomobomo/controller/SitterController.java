package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.OrderDto;
import com.example.bomobomo.domain.dto.SitterBoardDto;
import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.PageVo;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import com.example.bomobomo.service.OrderService;
import com.example.bomobomo.service.ReviewService;
import com.example.bomobomo.service.SitterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/sitter/*")
@RequiredArgsConstructor
public class SitterController {

    private final SitterService sitterService;
    private final OrderService orderService;
    private final ReviewService reviewService;

    //    @LoggingPointcut
    @GetMapping("/sitterFind")
    public String sitterPage(Model model, Criteria criteria) {
        int sitterTotal = sitterService.sitterTotalPeople();

        criteria.setAmount(6);

        model.addAttribute("sitterTotal", sitterTotal);
        model.addAttribute("sitterList", sitterService.sitterSelect(criteria));
        model.addAttribute("pageInfo", new PageVo(sitterService.getTotal(), criteria));

//        System.out.println("크리테리아 겟 페이지 : " + criteria.getPage());
//        System.out.println("크리테리아 : " + criteria);

        return "sitter/sitterFind";
    }

    @GetMapping("/sitterInfo")
    public void sitterInfo(Long empNumber, Model model) {
        EmpDto empDto = sitterService.sitterInfo(empNumber);
        List<SitterBoardDto> sitterBoardList = sitterService.selectSitterBoardList(empNumber);

        ArrayList<Double> sitterReview = sitterService.sitterReview(empNumber);
        System.out.println("각 평점 : " + sitterReview);

        if(sitterReview.size() == 0) {
            System.out.println("널인지 확인");
            sitterReview.add(0.0);
        }
        double sum = 0;
        for (double num : sitterReview) {
            sum += num;
        }

        double sitterTotalReview = sum / sitterReview.size();
        System.out.println(sitterTotalReview);

        String gender = empDto.getEmpGender();
        System.out.println("시터 성별 확인 : " + empDto.getEmpGender());
        if (empDto.getEmpGender() == "F") {
            System.out.println("성별이 여자");
            empDto.setEmpGender("여");
        } else if(empDto.getEmpGender() == "M") {
            System.out.println("성별이 남자");
            empDto.setEmpGender("남");
        }

        System.out.println("선택 시터 성별 : " + empDto.getEmpGender());
//        empDto.getEmpContent().replaceAll("\r\n", "<br>");

//        System.out.println("시터 자기소개 내용 : " + empDto.getEmpContent());
//        System.out.println("이름 : " + empDto.getEmpName() + "  ,  성별 : " + empDto.getEmpGender());
//        System.out.println("시터 번호 : " + empDto.getEmpNumber());
//        System.out.println("시터 게시판 번호 : " + sitterBoardDto.);

        model.addAttribute("emp", empDto);
        model.addAttribute("sitterBoardList", sitterBoardList);
        model.addAttribute("sitterTotalReview", sitterTotalReview);



    }


    @GetMapping("/sitterRegister")
    public String sitterRegister(HttpServletRequest req) {

        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        if (userNumber == null) {
            return "/user/login";
        }
        return "/sitter/sitterRegister";
    }

    @PostMapping("/sitterRegister")
    public String sitterRegister(HttpServletRequest req, Long sitterNumber,
                                 @RequestParam("sitterNum") Long empNumber) {

//        req.getParameter("sitterNum");
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        System.out.println("로그인된 유저 번호 : " + userNumber);
        System.out.println(" --> : " + empNumber);

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

        System.out.println("로그인된 유저 번호 : " + userNumber);

        orderDto.setUserNumber(userNumber);
//        orderService.register(orderDto);


        log.info(orderDto.getUserNumber().toString());
        // 신청서 내용 입력 후 이동
        return new RedirectView("/common/index");
    }

    @PostMapping("/addrCheck")
    public String addrCheck(String city, String cityAddr, Model model, Criteria criteria) {

        System.out.println("시도 : " + city + ", 구군 : " + cityAddr);
        int sitterAddrTotal = sitterService.sitterAddrTotal();
        model.addAttribute("sitterTotal", sitterAddrTotal);

        criteria.setAmount(6);

        System.out.println("여기 오나 확인");

        model.addAttribute("sitterList", sitterService.addrCheck(criteria));
        model.addAttribute("pageInfo", new PageVo(sitterService.getTotal(), criteria));

        return "sitter/sitterFind";
    }

}
