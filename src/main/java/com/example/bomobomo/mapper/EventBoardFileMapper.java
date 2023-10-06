package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.EventBoardImgDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventBoardFileMapper {

    //삽입
    public void insert(EventBoardImgDto eventBoardImgDto);

    //조회
    public List<EventBoardImgDto> selectList(Long eventBoardNumber);
}
