package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.AdminDto;
import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.NoticeDto;
import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.domain.vo.*;
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

// 관리자 로그인
    @Transactional
    public AdminDto adminLogin(String adminId, String adminPassword){
        return Optional.ofNullable(adminMapper.login(adminId, adminPassword))
                .orElseThrow( () -> {throw new IllegalArgumentException("로그인 실패!!"); });
    }
    
// 주간 회원가입수
    public List<WeeklyRegisterVo> weeklyRegister(){
        return adminMapper.weeklyRegisterSelect();
    }

// 회원조회
    public List<UserListVo> selectAllUsers(Criteria criteria, SearchVo searchVo){
        return adminMapper.selectAllUsers(criteria,searchVo);
    }
// 회원 수
    public int getTotalUsers(SearchVo searchVo){
        return adminMapper.getTotalUsers(searchVo);
    }
// 회원 상세정보
    public UserDetailVo selectUserDetail(Long userNumber){
        return adminMapper.selectUserDetail(userNumber);
    }

// 직원조회
    public List<EmpDto> selectAllEmp(Criteria criteria, SearchVo searchVo){
        return adminMapper.selectAllEmp(criteria,searchVo);
    }
// 직원 수
    public int getTotalEmp(SearchVo searchVo){
        return adminMapper.getTotalEmp(searchVo);
    }

//    공지사항 조회
    public List<NoticeDto> selectAllNotice(Criteria criteria, SearchVo searchVo){
        return adminMapper.selectAllNotice(criteria,searchVo);
    }
//    공지사항 수
    public int getTotalNotice(SearchVo searchVo){
        return adminMapper.getTotalNotice(searchVo);
    }
//    공지사항 상세정보
    public NoticeDto selectNoticeDetail(Long noticeNumber){
        return adminMapper.selectNoticeDetail(noticeNumber);
    }
//    공지사항 등록
    public void noticeRegist(NoticeDto noticeDto){
        adminMapper.noticeRegist(noticeDto);
    }

//    매칭리스트 조회
    public List<MatchListVo> selectAllMatchs(Criteria criteria, SearchVo searchVo){
        return adminMapper.selectAllMatchs(criteria,searchVo);
    }
//    매칭 리스트 수
    public int getTotalMatchs(SearchVo searchVo){
        return adminMapper.getTotalMatchs(searchVo);
    }
}
