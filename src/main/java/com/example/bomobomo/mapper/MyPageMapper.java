package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.MyPageSitterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyPageMapper {

    // 조회
    public List<MyPageSitterVo> selectSitterList(@Param("criteria")Criteria criteria,@Param("userNumber")Long userNumber);

    // 전체 페이지 구하기
    public int selectTotal(Long userNumber);

}
