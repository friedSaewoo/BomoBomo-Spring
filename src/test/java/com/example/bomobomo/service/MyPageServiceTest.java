package com.example.bomobomo.service;

import com.example.bomobomo.domain.vo.*;
import com.example.bomobomo.mapper.MyPageMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyPageServiceTest {
    @Mock
    MyPageMapper myPageMapper;

    @InjectMocks
    MyPageService myPageService;

    @Test
    void findSitterList() {
//        doReturn(List.of(new MyPageSitterVo(),new MyPageSitterVo())).when(myPageMapper)
//                .selectSitterList(any(MyPageSitterVo.class));

//
//        List<MyPageSitterVo> myPageServiceSitterList = myPageService.findSitterList(new MyPageSitterVo());
//
//        assertThat(myPageServiceSitterList.size()).isEqualTo(2);
    }

    @Test
    void findEventList(){
        doReturn(List.of(new MyPageEventVo(),new MyPageEventVo())).when(myPageMapper).selectEventList(any(Criteria.class),any(Long.class));

        List<MyPageEventVo> list=myPageService.findEventList(new Criteria(),1L);

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void findEventTotal(){
        doReturn(1).when(myPageMapper).selectEventTotal(any(Long.class));

        myPageService.findEventTotal(1L);

        assertThat(myPageService.findEventTotal(1L)).isNotNull();
    }
    // 사용자와 매칭된 결제대기 상테의 직원의 정보 출력 테스트
//    @Test
//    void findMatchEmpInfoList(){
//        doReturn(List.of(new MatchEmpInfoVo(),new MatchEmpInfoVo())).when(myPageMapper)
//            .selectMachEmpInfo(any(Long.class));
//
//        List<MatchEmpInfoVo> list = myPageService.findMachEmpInfoList(1L);
//
//        assertThat(list.size()).isNotNull();
//
//    }

    @Test
    void findMatchEmpAvg(){
        doReturn(new MatchEmpRatingAvgVo()).when(myPageMapper).selectMatchEmpAvg(any(Long.class));


        MatchEmpRatingAvgVo avg=myPageService.findMatchEmpAvg(1L);

        assertThat(avg).isNotNull();


    }

}











