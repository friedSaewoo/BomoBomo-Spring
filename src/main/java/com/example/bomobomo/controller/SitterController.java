package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.*;
import com.example.bomobomo.domain.vo.*;
import com.example.bomobomo.service.AdminService;
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
    private final AdminService adminService;

    //    @LoggingPointcut
    // 전체 시터 리스트 페이지 컨트롤러
    @GetMapping("/sitterFind")
    public String sitterPage(Model model, Criteria criteria) {

        List<ActDto> actList = adminService.selectAct();
               model.addAttribute("actList",actList);
               log.info("========================================={}",actList);

        return "sitter/sitterFind";
    }

    // 선택한 시터 정보 컨트롤러
    @GetMapping("/sitterInfo")
    public String sitterInfo(Long empNumber, Model model, HttpServletRequest req) {
        System.out.println("시터 번호 : " + empNumber);
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        System.out.println("유저 번호 : " + userNumber);

        EmpVo empVo = sitterService.sitterInfo(empNumber);
        List<SitterBoardVo> sitterBoardList = sitterService.selectSitterBoardList(empNumber);
        if(userNumber != null) {
            Long cnt = sitterService.userMatchCheck(userNumber);
            model.addAttribute("cnt", cnt);
        }

        Double sitterReview = sitterService.sitterReview(empNumber);
        List<ActVo> actVo = sitterService.sitterPossibleList(empNumber);
        System.out.println("머인지 : " + empNumber);
        EmpImgDto empImgDto = sitterService.sitterImg(empNumber);

        String empImg = empImgDto.getEmpImgUploadPath() + "/" + empImgDto.getEmpImgUuid() + "_" + empImgDto.getEmpImgName();
        System.out.println("empImg 확인 : " + empImg);
        System.out.println("VO 확인 : " + actVo);


        model.addAttribute("actVoList", actVo);

        if(sitterReview == null) {
            sitterReview = 0.0;
        }

        double sitterTotalReview = sitterReview;
        System.out.println("시터 평균 : " + sitterTotalReview);
        String gender = empVo.getEmpGender();
        if (empVo.getEmpGender().equals('F')) {
            empVo.setEmpGender("여");
        } else if(empVo.getEmpGender().equals('M')) {
            empVo.setEmpGender("남");
        }

//        empVo.setEmpContent(empVo.getEmpContent().replace("\r\n","<br>"));
        System.out.println("자기소개 : " + empVo.getEmpContent());

        String empDate = empVo.getEmpDate().substring(0, 10);
        empVo.setEmpDate(empDate);

        model.addAttribute("empImg", empImg);
        model.addAttribute("empImgDto", empImgDto);
        model.addAttribute("userNumber", userNumber);
        model.addAttribute("emp", empVo);
        model.addAttribute("sitterBoardList", sitterBoardList);
        model.addAttribute("sitterTotalReview", sitterTotalReview);

        return "/sitter/sitterInfo";
    }

//    선택한 시터 신청 컨트롤러(현재 로그인 상태 확인)
    @GetMapping("/sitterRegister")
    public String sitterRegister(HttpServletRequest req) {

        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        if (userNumber == null) {
            return "/user/login";
        }
        return "/mypage/application";
    }

//    선택한 시터 신청 컨트롤러
    @PostMapping("/sitterRegister")
    public String sitterRegister(HttpServletRequest req, Model model,
                                 @RequestParam("empName") String empName,
                                 @RequestParam("empNumber") Long empNumber) {

        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        OrderDto orderDto = null;
        if (userNumber == null) {
                  return "/user/login";
              }

        try {
            orderDto = orderService.findOrder(userNumber);
            System.out.println("========={}"+orderDto);

        }catch (NullPointerException e) {
            return "/mypage/applicationPage";
        }

        model.addAttribute("order",orderDto);

        model.addAttribute("empName",empName);
        model.addAttribute("empNumber",empNumber);


        return "/sitter/sitterRegister";
    }


    //신청서 데이터 저장후 이동
    @PostMapping("/sitterApplication")
    public RedirectView applicationRegister(SubmitOrderDto submitOrderDto, MatchDto matchDto, HttpServletRequest req,
                                            @RequestParam("empNumber")Long empNumber, Model model) {
        // 저장 서비스 실행
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        System.out.println("시터 번호" + empNumber);
        System.out.println("유저번호 : " + userNumber);

        System.out.println("서브밋오더 : " + submitOrderDto);
        matchDto.setUserNumber(userNumber);
        matchDto.setEmpNumber(empNumber);
        System.out.println("매치 : " + matchDto);

        sitterService.register(submitOrderDto);
        sitterService.sitterMatching(matchDto);


        // 신청서 내용 입력 후 이동
        return new RedirectView("/common/index");
    }

//    @PostMapping("/join")
//    public String join(UserDto userDto, AddressDto addressDto, HttpServletRequest req) {
//
//        String detail = req.getParameter("addressDetails");
//        String addressDetail = addressDto.getAddressDetail() +" "+ detail;
//
//        addressDto.setAddressDetail(addressDetail);
//
//        userService.register(userDto, addressDto);
//
//        return "user/login";
//    }
}
