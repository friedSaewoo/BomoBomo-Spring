package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.OrderDto;
import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.PageVo;
import com.example.bomobomo.service.OrderService;
import com.example.bomobomo.service.SitterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/sitter/*")
@RequiredArgsConstructor
public class SitterController {

    private final SitterService sitterService;
    private final OrderService orderService;

//    @LoggingPointcut
    @GetMapping("/sitterFind")
    public String sitterPage(Model model, Criteria criteria) {

        System.out.println("시터 컨트롤러 확인");
        int sitterTotal = sitterService.sitterTotalPeople();
        model.addAttribute("sitterTotal", sitterTotal);

        criteria.setAmount(6);
        System.out.println("현재 페이지 : " + criteria.getPage());
        System.out.println("한 페이지 당 게시물 수 : " + criteria.getAmount());

        model.addAttribute("sitterList", sitterService.sitterSelect(criteria));
        model.addAttribute("pageInfo", new PageVo(sitterService.getTotal(), criteria));


        return "sitter/sitterFind";
    }

    @GetMapping("/sitterInfo")
    public void sitterInfo(Long empNumber, Model model) {
        EmpDto empDto = sitterService.sitterInfo(empNumber);

        String gender = empDto.getEmpGender();

        if(gender == "F") {
            System.out.println("성별이 여자");
            empDto.setEmpGender("여");
        } else {
            System.out.println("성별이 남자");
            empDto.setEmpGender("남");
        }

        empDto.getEmpContent().replaceAll("\r\n", "<br>");

        System.out.println("시터 자기소개 내용 : " +  empDto.getEmpContent());
        System.out.println("이름 : " + empDto.getEmpName() + "  ,  성별 : " + empDto.getEmpGender());
        model.addAttribute("emp", empDto);

    }


    @GetMapping("/sitterS")
    public String sitterS(HttpServletRequest req) {

        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        System.out.println("로그인된 유저 번호 : " + userNumber);
        if(userNumber == null) {
            return "/user/login";
        }

        return "/sitter/sitterS";
    }

    //신청서 데이터 저장후 이동
    @PostMapping("/sitterApplication")
    public RedirectView applicationRegister(OrderDto orderDto, HttpServletRequest req, UserDto userDto){
        // 저장 서비스 실행
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");

        System.out.println("로그인된 유저 번호 : " + userNumber);

        orderDto.setUserNumber(userNumber);
//        orderService.register(orderDto);


        log.info(orderDto.getUserNumber().toString());
        // 신청서 내용 입력 후 이동
        return new RedirectView("/common/index");
    }
}
