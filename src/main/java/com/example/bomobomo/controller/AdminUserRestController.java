package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.NoticeDto;
import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.PageVo;
import com.example.bomobomo.domain.vo.SearchVo;
import com.example.bomobomo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/user/*")
public class AdminUserRestController {
    private final AdminService adminService;

    @GetMapping("/list/{page}")
    public Map<String, Object> selectAllUsers(@PathVariable("page")int page, SearchVo searchVo) {

        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setAmount(10);
        PageVo pageVo = new PageVo(adminService.getTotalUsers(searchVo), criteria);
        List<UserDto> adminUserList = adminService.selectAllUsers(criteria, searchVo);

        Map<String, Object> adminUserMap = new HashMap<>();
        adminUserMap.put("pageVo", pageVo);
        adminUserMap.put("adminUserList", adminUserList);


        return adminUserMap;
    }
}
