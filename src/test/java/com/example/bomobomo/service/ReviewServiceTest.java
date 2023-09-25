package com.example.bomobomo.service;

import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.SearchReviewVo;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import com.example.bomobomo.mapper.ReviewMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class ReviewServiceTest {


    @Mock
    ReviewMapper reviewMapper;

    @InjectMocks
    ReviewService reviewService;


    SearchReviewVo searchReviewVo;
    SitterBoardVo sitterBoardVo;
    Criteria criteria;

    @BeforeEach
    void setUp() {


    }




//    @Test
//    void selectAll() {
//
//
//
//        Mockito.doReturn(List.of(new SitterBoardVo())).when(reviewMapper).selectServiceReviewAll(new Criteria(), new SearchReviewVo());
//
//        List<SitterBoardVo> list = reviewService.selectAll(criteria, searchReviewVo);
//
//        log.info(list.toString());
//        Assertions.assertThat(list.size()).isEqualTo(3);
//
//    }

    @Test
    void getTotal() {
    }
}