package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.SitterBoardDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class SitterBoardMapperTest {
    @Autowired
    SitterBoardMapper sitterBoardMapper;

    SitterBoardDto sitterBoardDto;

//    @BeforeEach
//    void setUp() {
//        sitterBoardDto = new SitterBoardDto();
//        sitterBoardDto.setSitterBoardContent("test");
//        sitterBoardDto.setRating(1);
//        sitterBoardDto.setEmpNumber(1L);
//        sitterBoardDto.setMatchNumber(1L);
//        sitterBoardDto.setUserNumber(1L);
//
//        sitterBoardMapper.insert(sitterBoardDto);
//
//    }
//
//    @Test
//    void insert() {
//        sitterBoardMapper.insert(sitterBoardDto);
//    }
//
//    @Test
//    void selectAll() {
//        sitterBoardMapper.selectAll(sitterBoardDto.getUserNumber());
//        log.info(sitterBoardMapper.selectAll(sitterBoardDto.getUserNumber()).toString());
//    }
}