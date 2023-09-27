package com.example.bomobomo.service;


import com.example.bomobomo.domain.dto.EmpImgDto;
import com.example.bomobomo.mapper.EmpImgMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpImgService {


   private final EmpImgMapper empImgMapper;



   //직원 사진 리스트 조회

    public List<EmpImgDto> selectList(Long sitterBoardNumber){

        if (sitterBoardNumber == null) {

            throw new IllegalArgumentException("리뷰 게시 번호 누락");
        }


        return empImgMapper.selectList(sitterBoardNumber);
    }

}
