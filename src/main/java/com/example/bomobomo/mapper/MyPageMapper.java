package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.vo.MyPageSitterVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {

    // 조회
    public List<MyPageSitterVo> selectMyPageSitterList(MyPageSitterVo myPageSitterVo);



}
