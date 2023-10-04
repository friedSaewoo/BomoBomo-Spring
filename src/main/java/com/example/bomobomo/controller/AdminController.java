package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.AdminDto;
import com.example.bomobomo.domain.dto.NoticeDto;
import com.example.bomobomo.domain.vo.UserDetailVo;
import com.example.bomobomo.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

//    관리자 로그인 페이지 GET 매핑
    @GetMapping("/login")
    public String login(){
        return "admin/adminLogin";
    }
//    관리자 로그인 POST 매핑
    @PostMapping("/login")
    public RedirectView login(String adminId, String adminPassword, HttpServletRequest req, RedirectAttributes redirectAttributes){
        AdminDto adminDto = null;

        try {
            adminDto = adminService.adminLogin(adminId, adminPassword);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("isLogin","0");
            return new RedirectView("/admin/login");
        }

        req.getSession().setAttribute("adminId", adminDto.getAdminId());
        req.getSession().setAttribute("adminNumber",adminDto.getAdminNumber());
        return new RedirectView("/admin/main");
    }
//    관리자 로그아웃
    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest req){
        req.getSession().invalidate();
        return new RedirectView("/common/index");
    }

//    회원 상세정보
    @GetMapping("/user/detail")
    public String selectUserDetail(@RequestParam(name = "userNumber") Long userNumber, Model model) {
        UserDetailVo userDetail = adminService.selectUserDetail(userNumber);
        model.addAttribute("userDetail", userDetail);
        return "admin/adminUserDetail";
    }
//    공지사항 상세정보
    @GetMapping("/notice/detail")
    public String selectNoticeDetail(@RequestParam(name = "noticeNumber")Long noticeNumber, Model model){
        NoticeDto noticeDetail = adminService.selectNoticeDetail(noticeNumber);
        model.addAttribute("noticeDetail",noticeDetail);
        return "admin/adminNoticeDetail";
    }

    @GetMapping("/main")
    public String Main(){
        return "admin/adminMain";
    }
    @GetMapping("/user")
    public String User(){
        return "admin/adminUser";
    }
    @GetMapping("/emp")
    public String Emp(){
        return "admin/adminEmp";
    }
    @GetMapping("/match")
    public String Match(){
        return "admin/adminMatch";
    }
    @GetMapping("/event")
    public String Event(){
        return "admin/adminEvent";
    }
    @GetMapping("/notice")
    public String Notice(){
        return "admin/adminNotice";
    }

    @GetMapping("/redirect/notice")
    public RedirectView redirectNotice(HttpServletRequest req){
        req.getSession().invalidate();
        return new RedirectView("/admin/notice");
    }
    @GetMapping("/noticeRegist")
    public String noticeRegist(){
        return "admin/adminNoticeWrite";
    }

    @PostMapping("/noticeRegist")
    public RedirectView noticeRegist(NoticeDto noticeDto){
        adminService.noticeRegist(noticeDto);
        return new RedirectView("/admin/notice");
    }
}
