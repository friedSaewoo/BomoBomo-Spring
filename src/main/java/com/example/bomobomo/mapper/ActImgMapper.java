package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.ActDto;
import com.example.bomobomo.domain.dto.ActImgDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActImgMapper {

    //활동 입력
    public void insertAct(ActDto actDto);

    //활동 이미지 삽입
    public void insert(ActImgDto actImgDto);


}
