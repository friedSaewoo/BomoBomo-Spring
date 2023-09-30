package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.NoticeDto;
import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.MatchListVo;
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
@RequestMapping("/admin/rest/*")
public class AdminRestController {
    private final AdminService adminService;

    @GetMapping("/user/list/{page}")
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

    @GetMapping("/emp/list/{page}")
    public Map<String, Object> selectAllEmp(@PathVariable("page")int page, SearchVo searchVo) {
        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setAmount(10);
        PageVo pageVo = new PageVo(adminService.getTotalEmp(searchVo), criteria);
        List<EmpDto> adminEmpList = adminService.selectAllEmp(criteria, searchVo);

        Map<String, Object> adminEmpMap = new HashMap<>();
        adminEmpMap.put("pageVo", pageVo);
        adminEmpMap.put("adminEmpList", adminEmpList);
        return adminEmpMap;
    }

    @GetMapping("/notice/list/{page}")
    public Map<String, Object> selectAllNotice(@PathVariable("page")int page, SearchVo searchVo){
        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setAmount(10);
        PageVo pageVo = new PageVo(adminService.getTotalNotice(searchVo), criteria);
        List<NoticeDto> adminNoticeList = adminService.selectAllNotice(criteria, searchVo);

        Map<String, Object> adminNoticeMap = new HashMap<>();
        adminNoticeMap.put("pageVo", pageVo);
        adminNoticeMap.put("adminNoticeList",adminNoticeList);
        return adminNoticeMap;
    }

    @GetMapping("/match/list/{page}")
    public Map<String,Object> selectAllMatchs(@PathVariable("page")int page, SearchVo searchVo){
        Criteria criteria = new Criteria();
        criteria.setPage(page);
        criteria.setAmount(5);
        PageVo pageVo = new PageVo(adminService.getTotalMatchs(searchVo),criteria);
        List<MatchListVo> matchList = adminService.selectAllMatchs(criteria,searchVo);

        Map<String, Object> matchListMap = new HashMap<>();
        matchListMap.put("pageVo",pageVo);
        matchListMap.put("matchList",matchList);
        return matchListMap;
    }
}
