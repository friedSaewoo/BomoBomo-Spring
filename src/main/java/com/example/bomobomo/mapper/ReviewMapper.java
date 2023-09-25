package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.SitterCommentDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.SearchReviewVo;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {

    //돌봄 서비스 리뷰게시판 리스트
    public List<SitterBoardVo> selectServiceReviewAll(@Param("criteria")Criteria criteria,
                                                      @Param("searchReviewVo")SearchReviewVo searchReviewVo);

    //돌봄 서비스 리뷰 게시판 글 수
    public int getTotal(SearchReviewVo searchReviewVo);


    //돌봄 서비스 리뷰 상세보기
    public SitterBoardVo selectOne(Long sitterBoardNumber);


    //돌봄서비스 리뷰 게시판 댓글
    public List<SitterCommentDto> selectServiceReviewReply();


    //조회수
    public void updateCount(Long sitterBoardNumber);

}
