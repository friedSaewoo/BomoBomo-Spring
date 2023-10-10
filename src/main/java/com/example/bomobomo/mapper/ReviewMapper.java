package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.SitterCommentDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.EventBoardVo;
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

    //돌봄 서비스 보모 리뷰 평점
    public double getAvgRating(Long empNumber);

    //돌봄 서비스 리뷰 조회수
    public void updateCount(Long sitterBoardNumber);

    //돌봄 서비스 리뷰 삭제
    public void delete(Long sitterBoardNumber);
    
    //돌봄서비스 리뷰 게시판 댓글
    public List<SitterCommentDto> selectServiceReviewReply();

    //돌봄서비스 조회수 탑 6개 뽑아오기
    public List<SitterBoardVo> selectTopCount();


    //=====================================
    
    //이벤트 서비스 리뷰게시판 리스트
    public List<EventBoardVo> selectER(@Param("criteria") Criteria criteria,
                                       @Param("searchReviewVo")SearchReviewVo searchReviewVo);

    //이벤트 서비스 리뷰 게시판 글 수
    public int getTotalER(SearchReviewVo searchReviewVo);
    
    //이벤트 서비스 리뷰 상세보기
    public EventBoardVo selectOne2(Long eventBoardNumber);

    //이벤트 서비스 리뷰 조회수
    public void updateEventReviewCount(Long eventBoardNumber);

    //이벤트 서비스 이벤트 리뷰 평점
    public double selectAvgEventReviewRating(Long eventNumber);

    //이벤트 서비스 리뷰 삭제
    public void deleteEventReview(Long eventBoardNumber);

    //이벤트 서비스 리뷰 게시판 댓글


    //이벤트 서비스 조회수 상위 6개 게시물 가져오기
    public List<EventBoardVo> selectTopEventCount();

}
