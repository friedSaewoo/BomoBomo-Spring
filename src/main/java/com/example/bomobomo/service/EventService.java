package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.EventDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventMapper eventMapper;


    public List<EventDto> findAll(){
        return eventMapper.selectAll();
    }

//      전체 직원 조회
    public List<EmpDto> findEmpAll(Criteria criteria) {
        return eventMapper.selectEmpAll(criteria);
    }

//    전체 직원수 조회

    public int getTotal(){
        return eventMapper.selectTotal();
    }

}
