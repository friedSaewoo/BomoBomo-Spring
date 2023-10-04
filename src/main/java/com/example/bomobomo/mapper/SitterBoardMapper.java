package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.SitterBoardDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SitterBoardMapper {


    //삽입
    public void insert(SitterBoardDto sitterBoardDto);

    //리스트 전체 조회 (마이페이지 및 게시판에서 조회해서 사용)
    public List<SitterBoardVo> selectAll(@Param("criteria") Criteria criteria, @Param("userNumber") Long userNumber);

    //    글작성 페이지 타이틀 조회
    public String selectTitle(Long matchNumber);

    //    메인페이지 시터 후기 토탈 조회
    public int selectSitterReviewTotal(Long userNumber);
}
