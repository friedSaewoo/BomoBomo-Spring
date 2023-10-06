package com.example.bomobomo.service;

import com.example.bomobomo.domain.vo.MyPageSitterVo;
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
}