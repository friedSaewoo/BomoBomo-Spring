package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.EmpDto;
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
@RequestMapping("/admin/emp/*")
public class AdminEmpRestController {
    private final AdminService adminService;

    @GetMapping("/list/{page}")
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
}
