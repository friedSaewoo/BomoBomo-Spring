package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyPageMapper {

    // 시터 결제 내역 조회
    public List<MyPageSitterVo> selectSitterList(@Param("criteria")Criteria criteria,@Param("userNumber")Long userNumber);

    // 시터 결제 내역 전체 페이지 구하기
    public int selectTotal(Long userNumber);

    //이벤트 결제 내역 조회
    public List<MyPageEventVo> selectEventList(@Param("criteria")Criteria criteria,@Param("userNumber")Long userNumber);

    //이벤트 결제 내역 전체 페이지 구하기
    public int selectEventTotal(Long userNumber);

    //마이페이지 유저와 매치된 직원의 정보 출력()
    public List<MatchEmpInfoVo> selectMachEmpInfo(Long userNumber);
    //마이페이지 유저와 매칭된 직원이 평점 출력
    public MatchEmpRatingAvgVo  selectMatchEmpAvg(Long userNumber);
}
