package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.AdminDto;
import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.SearchVo;
import com.example.bomobomo.domain.vo.WeeklyRegisterVo;
import com.example.bomobomo.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

    @Transactional
    public AdminDto adminLogin(String adminId, String adminPassword){
        return Optional.ofNullable(adminMapper.login(adminId, adminPassword))
                .orElseThrow( () -> {throw new IllegalArgumentException("로그인 실패!!"); });
    }

    public List<WeeklyRegisterVo> weeklyRegister(){
        return adminMapper.weeklyRegisterSelect();
    }

    public List<UserDto> selectAllUsers(Criteria criteria, SearchVo searchVo){
        return adminMapper.selectAllUsers(criteria,searchVo);
    }


    public int getTotalUsers(SearchVo searchVo){
        return adminMapper.getTotalUsers(searchVo);
    }
}
