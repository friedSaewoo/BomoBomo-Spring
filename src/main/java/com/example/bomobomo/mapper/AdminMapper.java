package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.AdminDto;
import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.SearchVo;
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

//    유저 리스트
    public List<UserDto> selectAllUsers(@Param("criteria") Criteria criteria, @Param("searchVo")SearchVo searchVo);
//    검색별 유저 수
    public int getTotalUsers(SearchVo searchVo);

//    직원 리스트
    public List<EmpDto> selectAllEmp(@Param("criteria")Criteria criteria, @Param("searchVo")SearchVo searchVo);
//    검색별 직원 수
    public int getTotalEmp(SearchVo searchVo);

}
