package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.EventBoardDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.EventBoardVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EventBoardMapper {
    //리뷰 작성 후 삽입
    public void insert(EventBoardDto eventBoardDto);

    //리뷰 작성시 결제한 이벤트 이름 조회하기
    public String selectEventTitle(Long eventNumber);

    // 이벤트 게시판에서 내가 작성한 게시판 전체 조회
    public List<EventBoardVo> selectEventAll(@Param("criteria")Criteria criteria,@Param("userNumber")Long userNumber);

    //마이페이지의 페이징 처리를 위한 전체 글 조회
    public int selectEventReviewTotal(Long userNumber);



}
