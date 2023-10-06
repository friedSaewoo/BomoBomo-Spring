package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.AdminDto;
import com.example.bomobomo.domain.dto.EventDetailDto;
import com.example.bomobomo.domain.dto.EventDto;
import com.example.bomobomo.domain.dto.NoticeDto;
import com.example.bomobomo.domain.vo.UserDetailVo;
import com.example.bomobomo.service.AdminService;
import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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
    @GetMapping("/redirect/event")
    public RedirectView redirectEvent(){
        return new RedirectView("/admin/event");
    }
    @GetMapping("/eventRegist")
    public String eventRegist(){
        return "admin/adminEventRegist";
    }

    @PostMapping("/eventRegist")
    public RedirectView eventRegist( EventDto eventDto, @RequestParam("eventImgFile")List<MultipartFile> eventImg,
                         EventDetailDto eventDetailDto, @RequestParam("eventDetailFile")List<MultipartFile> detailImg){
        adminService.eventRegist(eventDto);
        try {
            adminService.eventImgRegistAndSave(eventImg,eventDto.getEventNumber());
            adminService.eventDetailRegistAndSave(detailImg,eventDto.getEventNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RedirectView("/admin/event");
    }


    @GetMapping("/notice")
    public String Notice(){
        return "admin/adminNotice";
    }
    @GetMapping("/redirect/notice")
    public RedirectView redirectNotice(){
        return new RedirectView("/admin/notice");
    }
    @GetMapping("/noticeRegist")
    public String noticeRegist(){
        return "admin/adminNoticeWrite";
    }
//      공지사항 등록
    @PostMapping("/noticeRegist")
    public RedirectView noticeRegist(NoticeDto noticeDto){
        adminService.noticeRegist(noticeDto);
        return new RedirectView("/admin/notice");
    }
//    공지사항 수정
    @PostMapping("/noticeConfig")
    public RedirectView noticeUpdate(NoticeDto noticeDto){
        adminService.noticeUpdate(noticeDto);
        return  new RedirectView("/admin/notice");
    }
//    공지사항 상세정보
    @GetMapping(value={"/adminNoticeDetail","/adminNoticeConfig"})
    public void selectNoticeDetail(@RequestParam(name = "noticeNumber")Long noticeNumber, Model model){
        NoticeDto noticeDetail = adminService.selectNoticeDetail(noticeNumber);
        model.addAttribute("noticeDetail",noticeDetail);
    }
//    공지사항 삭제
    @GetMapping("/adminNoticeDelete")
    public RedirectView noticeDelete(Long noticeNumber){
        adminService.noticeDelete(noticeNumber);
        return new RedirectView("/admin/notice");
    }
}
