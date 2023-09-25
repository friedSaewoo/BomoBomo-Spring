package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.AdminDto;
import com.example.bomobomo.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

    @Transactional
    public AdminDto adminLogin(String adminId, String adminPassword){
        return Optional.ofNullable(adminMapper.login(adminId, adminPassword))
                .orElseThrow( () -> {throw new IllegalArgumentException("조회 결과 없음"); });
    }
}
