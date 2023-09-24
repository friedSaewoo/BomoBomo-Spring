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
}
