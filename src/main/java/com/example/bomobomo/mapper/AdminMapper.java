package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.AdminDto;
import com.example.bomobomo.domain.vo.WeeklyRegisterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
//    관리자 로그인
    public AdminDto login(@Param("adminId")String adminId, @Param("adminPassword")String adminPassword);
//    주간 가입수
    public List<WeeklyRegisterVo> weeklyRegisterSelect();
}
