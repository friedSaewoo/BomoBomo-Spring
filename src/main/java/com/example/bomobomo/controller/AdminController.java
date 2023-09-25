package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.AdminDto;
import com.example.bomobomo.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/login")
    public String login(){
        return "admin/adminLogin";
    }

    @GetMapping("/Main")
    public String Main(){
        return "admin/adminMain";
    }
    @GetMapping("User")
    public String User(){
        return "admin/adminUser";
    }
    @GetMapping("/Emp")
    public String Emp(){
        return "admin/adminEmp";
    }
    @GetMapping("/Match")
    public String Match(){
        return "admin/adminMatch";
    }
    @GetMapping("/Event")
    public String Event(){
        return "admin/adminEvent";
    }
    @GetMapping("/Notice")
    public String Notice(){
        return "admin/adminNotice";
    }

    @PostMapping("/login")
    public RedirectView login(String adminId, String adminPassword, HttpServletRequest req){
        AdminDto adminDto = adminService.adminLogin(adminId, adminPassword);
        req.getSession().setAttribute("adminId", adminDto.getAdminId());
        req.getSession().setAttribute("adminNumber",adminDto.getAdminNumber());
        return new RedirectView("/admin/Main");
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest req){
        req.getSession().invalidate();
        return new RedirectView("/common/index");
    }
}
