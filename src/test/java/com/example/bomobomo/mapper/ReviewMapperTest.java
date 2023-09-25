package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.SitterBoardDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.SearchReviewVo;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class ReviewMapperTest {


    @Autowired
    ReviewMapper reviewMapper;


    Criteria criteria;
    SearchReviewVo searchReviewVo;
    SitterBoardVo sitterBoardVo;
    SitterBoardDto sitterBoardDto;

    @BeforeEach
    void setUp() {
    }

    @Test
    void selectServiceReviewAll() {

        List<SitterBoardVo> list = reviewMapper.selectServiceReviewAll(new Criteria(), new SearchReviewVo());

        log.info(list.toString());

        assertThat(list.size()).isEqualTo(3);

    }

    @Test
    void getTotal() {

        reviewMapper.getTotal(new SearchReviewVo());
    }

    @Test
    void selectServiceReviewReply() {
    }
}