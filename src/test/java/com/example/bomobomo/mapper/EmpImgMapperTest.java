package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.EmpImgDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class EmpImgMapperTest {

    @Autowired
    EmpImgMapper empImgMapper;

    EmpImgDto empImgDto;


    @BeforeEach
    void setUp() {
    }

    @Test
    void selectList() {

        List<EmpImgDto> list = empImgMapper.selectList(1L);

        Assertions.assertThat(list.size()).isEqualTo(1);



    }
}