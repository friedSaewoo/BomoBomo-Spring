package com.example.bomobomo.mapper;


import com.example.bomobomo.domain.dto.EmpImgDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface EmpImgMapper {


    //직원사진 리스트조회
    public List<EmpImgDto> selectList(Long sitterBoardNumber);

}
