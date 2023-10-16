package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.MatchDto;
import com.example.bomobomo.domain.dto.SitterBoardDto;
import com.example.bomobomo.domain.dto.SubmitOrderDto;
import com.example.bomobomo.domain.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SitterMapper {

    //시터 수 출력
    public int sitterTotal(
            @Param("cityName") String cityName,
            @Param("countryName") String countryName,
            @Param("actNumber") Integer actNumber
    );

    //시터 리스트 출력
    public List<EmpListVo> sitterList(
            @Param("criteria") Criteria criteria,
            @Param("cityName") String cityName,
            @Param("countryName") String countryName,
            @Param("actNumber") Integer actNumber
    );

    //    전체 게시물 수 조회
    public int selectTotal();

//    시터 정보 조회
    public EmpVo sitterInfo(Long empNumber);

//    시터 후기 리스트 출력
    public List<SitterBoardDto> sitterBoardList(Long empNumber);

    //    시터 평점
    public Double sitterReview(Long empNumber);

    //고객이 선택한 주소에 업무 가능한 시터들 리스트
    public List<EmpListVo> addrCheck(Criteria criteria);

    //주소 시터 수 출력
    public int sitterAddrTotal();

//    시터 평점
    public List<SitterBoardVo> sitterAvg();

    public List<ActVo> sitterPossibleList(Long empNumber);

    public List<EmpListVo> sitterActImg();

    public void register(SubmitOrderDto submitOrderDto);
    public void sitterMatching(MatchDto matchDto);
}
