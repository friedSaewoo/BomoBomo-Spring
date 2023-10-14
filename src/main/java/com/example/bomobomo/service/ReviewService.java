package com.example.bomobomo.service;


import com.example.bomobomo.domain.dto.EventBoardDto;
import com.example.bomobomo.domain.dto.EventBoardImgDto;
import com.example.bomobomo.domain.dto.SitterBoardDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.EventBoardVo;
import com.example.bomobomo.domain.vo.SearchReviewVo;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import com.example.bomobomo.mapper.EventBoardFileMapper;
import com.example.bomobomo.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {


    private final ReviewMapper reviewMapper;
    private final EventBoardFileService eventBoardFileService;


    //돌봄 서비스 후기 리스트

      public List<SitterBoardVo> selectAll(Criteria criteria,SearchReviewVo searchReviewVo){

      List<SitterBoardVo> selectListAll = reviewMapper.selectServiceReviewAll(criteria, searchReviewVo);

      return selectListAll;

  }


  //게시물 수
    public int getTotal(SearchReviewVo searchReviewVo){


          return reviewMapper.getTotal(searchReviewVo);


    }
    //리뷰 상세보기
    public SitterBoardVo selectOne(Long sitterBoardNumber){
        if (sitterBoardNumber == null) {
            throw new IllegalArgumentException("리뷰게시번호 누락");
        }

        return Optional.ofNullable(reviewMapper.selectOne(sitterBoardNumber))
                .orElseThrow(()->{throw new IllegalArgumentException("존재하지 않는 게시 번호");});
    }


    //평점
    public double getAvgRating(Long empNumber){

        if (empNumber == null) {
            throw new IllegalArgumentException("직원 번호 누락");
        }


         return reviewMapper.getAvgRating(empNumber);
    }


    //조회수
    public void updateCount(Long sitterBoardNumber){
        if (sitterBoardNumber == null) {
            throw new IllegalArgumentException("리뷰 게시 번호 누락");

        }

        reviewMapper.updateCount(sitterBoardNumber);

    }

    //돌봄 후기 수정
    public void modifyServiceReview(SitterBoardDto sitterBoardDto){

          reviewMapper.updateServiceReview(sitterBoardDto);

    }

    //돌봄 후기 삭제
    public void delete(Long sitterBoardNumber){
        if (sitterBoardNumber == null) {
            throw new IllegalArgumentException("리뷰 게시 번호 누락");
        }

        reviewMapper.delete(sitterBoardNumber);

    }

    //돌봄 후기 조회수 탑 6개 게시물 가져오기
    public List<SitterBoardVo> findTopCount(){
          return reviewMapper.selectTopCount();
    }

    //돌봄 후기 상세보기 페이지에 해당 시터 다른 리뷰들 뽑아오기
    public List<SitterBoardVo> findReviewDetail(Long empNumber){
        if (empNumber == null) {
            throw new IllegalArgumentException("직원 번호 누락");
        }

       return reviewMapper.serviceReviewDetailTopCount(empNumber);
    }



    //=================================================================

    //이벤트 서비스 리뷰 게시글 리스트
    public List<EventBoardVo> findEventReview(Criteria criteria, SearchReviewVo searchReviewVo){
          return reviewMapper.selectER(criteria, searchReviewVo);
    }

    //이벤트 서비스 리뷰 게시글 개수
    public int getTotalER(SearchReviewVo searchReviewVo){
          return reviewMapper.getTotalER(searchReviewVo);
    }

    //이벤트 서비스 리뷰 상세보기
    public EventBoardVo showEReviewDetail(Long eventBoardNumber){

          return Optional.ofNullable(reviewMapper.selectOne2(eventBoardNumber))
                  .orElseThrow(()->{throw new IllegalArgumentException("존재하지 않는 정보");});
    }


    //이벤트 리뷰 평점
    public double getAvgEventReviewRating(Long eventNumber){

        if (eventNumber == null) {
            throw new IllegalArgumentException("이벤트 번호 누락");
        }

        return reviewMapper.selectAvgEventReviewRating(eventNumber);
    }

    //이벤트 리뷰 삭제
    public void removeEventReview(Long eventBoardNumber){
        if (eventBoardNumber == null) {
            throw new IllegalArgumentException("이벤트 리뷰 게시판 번호 누락");
        }
        eventBoardFileService.remove(eventBoardNumber);
        reviewMapper.deleteEventReview(eventBoardNumber);
    }

    //이벤트 리뷰 조회수 증가
    public void updateEventReviewCount(Long eventBoardNumber){
        if (eventBoardNumber == null) {
            throw new IllegalArgumentException("이벤트 리뷰 번호 누락");
        }
        reviewMapper.updateEventReviewCount(eventBoardNumber);
    }

    //이벤트 리뷰 조회수 상위 6개 게시물 가져오기
    public List<EventBoardVo> findEventTopCount(){
          return reviewMapper.selectTopEventCount();
    }


    //이벤트 리뷰 수정

    public void modifyEventReview(EventBoardDto eventBoardDto, MultipartFile files){

            if(!files.isEmpty()){
                eventBoardFileService.remove(eventBoardDto.getEventBoardNumber());
                try{
                    eventBoardFileService.registerAndSaveFile(files, eventBoardDto.getEventBoardNumber());
                }catch(IOException e){
                    e.printStackTrace();
                }
                reviewMapper.updateEventReview(eventBoardDto);
            }
    }


    //이벤트 후기 상세보기 페이지에 동일 이벤트 리뷰들 뽑아오기
    public List<EventBoardVo> findEventReviewTopCount(Long eventNumber){

        if (eventNumber == null) {
            throw new IllegalArgumentException("이벤트 번호 누락");
        }
         return reviewMapper.eventReviewDetailTopCount(eventNumber);
    }


}
