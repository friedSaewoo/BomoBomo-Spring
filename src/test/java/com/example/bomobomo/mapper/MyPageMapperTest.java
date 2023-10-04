package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.vo.MyPageSitterVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class MyPageMapperTest {
    @Autowired
    MyPageMapper myPageMapper;

//    MyPageSitterVo myPageSitterVo;

//    @BeforeEach
//    void setUp() {
//        myPageSitterVo=new MyPageSitterVo();
//        myPageSitterVo.setEmpImgName("test");
//        myPageSitterVo.setEmpImgNumber(1L);
//        myPageSitterVo.setUserNumber(1L);
//        myPageSitterVo.setEmpImgUploadPath("asdasd");
//        myPageSitterVo.setEmpImgUuid("123123");
//        myPageSitterVo.setEmpName("test");
//        myPageSitterVo.setReviewCheck(1);
//        myPageSitterVo.setMatchNumber(1L);
////        myPageSitterVo.setEstNumber(1L);
//        myPageSitterVo.setStatus("1");
//        myPageSitterVo.setSubmitOrderNumber(1L);
//        myPageSitterVo.setTotalPrice(123);
//        myPageSitterVo.setEmpImgName("ddd");



    }

//    @Test
//    void selectSitterList() {
//        List<MyPageSitterVo> myPageSitterVos =myPageMapper.selectSitterList(myPageSitterVo);
//        log.info(myPageSitterVos.toString());
//    }
}