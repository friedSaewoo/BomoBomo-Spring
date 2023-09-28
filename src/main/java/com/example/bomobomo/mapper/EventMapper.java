package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.EventDto;
import com.example.bomobomo.domain.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {
    //이벤트 페이지 전체 조회
    public List<EventDto> selectAll();

    //회사소개페이지 전체 조회
    public List<EmpDto> selectEmpAll(Criteria criteria);

    //회사소개페이지 전체 회원수 조회
    public int selectTotal();
}
