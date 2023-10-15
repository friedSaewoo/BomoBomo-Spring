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
    public String sitterInfo(Long empNumber, Model model) {
        System.out.println("시터 번호 : " + empNumber);
        EmpVo empVo = sitterService.sitterInfo(empNumber);
        List<SitterBoardDto> sitterBoardList = sitterService.selectSitterBoardList(empNumber);

        Double sitterReview = sitterService.sitterReview(empNumber);
        List<ActVo> actVo = sitterService.sitterPossibleList(empNumber);
        System.out.println("VO 확인 : " + actVo);

//        for(int i=0; i<actVo.size(); i++) {
//            String imgUploadPath = actVo.get(i).getActImgUploadPath();
//            String imgUuid = actVo.get(i).getActImgUuid();
//            String imgName = actVo.get(i).getActImgName();
//            String actName = actVo.get(i).getActName();
//
//            String imgPath = imgUploadPath + '/' + imgUuid +'_' + imgName;
//            System.out.println("이미지 패스 --> : " + imgPath);
//            System.out.println("이미지 이름 : " + imgName);
//
//            model.addAttribute("imgPath", imgPath);
//            model.addAttribute("actName", actName);
//        }
        model.addAttribute("actVoList", actVo);

//        System.out.println("이미지 경로 : " + imgPath);

//        String imagePath = actImg.actImgUploadPath + '/' + actImg.actImgUuid +'_' + actImg.actImgName;

        if(sitterReview == null) {
            sitterReview = 0.0;
        }

//        double sum = 0;
//        for (double num : sitterReview) {
//            sum += num;
//        }

        double sitterTotalReview = sitterReview;
        System.out.println("시터 평균 : " + sitterTotalReview);
        String gender = empVo.getEmpGender();
        if (empVo.getEmpGender().equals('F')) {
            empVo.setEmpGender("여");
        } else if(empVo.getEmpGender().equals('M')) {
            empVo.setEmpGender("남");
        }

        String empDate = empVo.getEmpDate().substring(0, 10);
        empVo.setEmpDate(empDate);

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
        return "/sitter/sitterRegister";
    }

//    선택한 시터 신청 컨트롤러
    @PostMapping("/sitterRegister")
    public String sitterRegister(HttpServletRequest req, Long sitterNumber,
                                 @RequestParam("empNumber") Long empNumber) {

//        req.getParameter("empNumber");
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");

        System.out.println("시터번호" + empNumber);
        System.out.println("유저번호" + userNumber);
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

//        System.out.println("시터 번호" + empNumber);
        System.out.println("유저번호 : " + userNumber);

        orderDto.setUserNumber(userNumber);
        orderService.register(orderDto);

        log.info(orderDto.getUserNumber().toString());
        // 신청서 내용 입력 후 이동
        return new RedirectView("/common/index");
    }

}
