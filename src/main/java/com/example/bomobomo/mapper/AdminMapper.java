package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.AdminDto;
import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.NoticeDto;
import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.domain.vo.*;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
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
    public List<UserListVo> selectAllUsers(@Param("criteria") Criteria criteria, @Param("searchVo")SearchVo searchVo);
//    검색별 유저 수
    public int getTotalUsers(SearchVo searchVo);
//    회원 상세정보
    public UserDetailVo selectUserDetail(Long userNumber);

//    직원 리스트
    public List<EmpDto> selectAllEmp(@Param("criteria")Criteria criteria, @Param("searchVo")SearchVo searchVo);
//    검색별 직원 수
    public int getTotalEmp(SearchVo searchVo);

//    공지사항 리스트
    public List<NoticeDto> selectAllNotice(@Param("criteria")Criteria criteria, @Param("searchVo")SearchVo searchVo);
//    검색별 공지 수
    public int getTotalNotice(SearchVo searchVo);
//    공지사항 상세정보
    public NoticeDto selectNoticeDetail(Long noticeNumber);
//    공지 등록
    public void noticeRegist(NoticeDto noticeDto);

//    매칭 리스트
    public List<MatchListVo> selectAllMatchs(@Param("criteria")Criteria criteria, @Param("searchVo")SearchVo searchVo);
//    검색별 매칭수
    public int getTotalMatchs(SearchVo searchVo);
}
