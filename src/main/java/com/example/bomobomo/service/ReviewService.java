package com.example.bomobomo.service;


import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.SearchReviewVo;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import com.example.bomobomo.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {


    private final ReviewMapper reviewMapper;


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

    //돌봄 후기 삭제
    public void delete(Long sitterBoardNumber){
        if (sitterBoardNumber == null) {
            throw new IllegalArgumentException("리뷰 게시 번호 누락");
        }

        reviewMapper.delete(sitterBoardNumber);

    }
}
