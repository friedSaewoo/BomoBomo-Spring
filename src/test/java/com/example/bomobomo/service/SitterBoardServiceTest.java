package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.SitterBoardDto;
import com.example.bomobomo.mapper.SitterBoardMapper;
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
class SitterBoardServiceTest {
    @Mock
    SitterBoardMapper sitterBoardMapper;

    @InjectMocks
    SitterBoardService sitterBoardService;


    @Test
    void register() {
        doNothing().when(sitterBoardMapper).insert(any(SitterBoardDto.class));

        sitterBoardService.register(new SitterBoardDto());

        verify(sitterBoardMapper,times(1)).insert(any(SitterBoardDto.class));

    }

//    @Test
//    void findAll() {
//        doReturn(List.of(new SitterBoardDto(),new SitterBoardDto())).when(sitterBoardMapper).selectAll(any(Long.class));
//
//        List<SitterBoardDto> sitterBoardDtoList = sitterBoardService.findAll(1L);
//
//        assertThat(sitterBoardDtoList.size()).isEqualTo(2);
//
//    }
}