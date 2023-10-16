package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.EventDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.EmpVo;
import com.example.bomobomo.domain.vo.EventPayVo;
import com.example.bomobomo.domain.vo.EventVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {
//    이벤트 상세페이지 이동
    public EventVo select(Long eventNumber);

//    이벤트 결제페이지 이동
    public EventVo payment(long eventNumber);

//    이벤트 페이지 전체 조회
    public List<EventVo> selectAll();

//    회사소개페이지 전체 조회
    public List<EmpVo> selectEmpAll(Criteria criteria);

//    회사소개페이지 전체 회원수 조회
    public int selectTotal();

//    이벤트 결제 데이터 저장
    void saveEvent(EventPayVo eventPayVo);

    void savePayment(EventPayVo eventPayVo);
}
