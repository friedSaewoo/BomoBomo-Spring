package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.SitterBoardDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.EmpVo;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import com.example.bomobomo.mapper.SitterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SitterService {
    private final SitterMapper sitterMapper;

    // 전체 시터의 수 조회
    public int sitterTotalPeople() {
        return sitterMapper.sitterTotal();
    }

    // 시터 전체 조회
    public List<EmpVo> sitterSelect(Criteria criteria) {

        return sitterMapper.sitterList(criteria);
    }

    // 시터 정보 조회
    public EmpDto sitterInfo(Long empNumber) {
        if (empNumber == null) {
            throw new IllegalArgumentException("시터 번호 누락");
        }
        return Optional.ofNullable(sitterMapper.sitterInfo(empNumber))
                .orElseThrow(() -> { throw new IllegalArgumentException("없는 시터 정보"); });
    }

    //시터 후기 리스트 출력
    public List<SitterBoardDto> selectSitterBoardList(Long empNumber) {
        if (empNumber == null) {
            throw new IllegalArgumentException("시터 번호 누락");
        }
//        return sitterMapper.sitterBoard(empNumber);
        return Optional.ofNullable(sitterMapper.sitterBoardList(empNumber))
                .orElseThrow(() -> { throw new IllegalArgumentException("없는 시터 정보"); });
    }

    //시터 평점
    public ArrayList<Double> sitterReview(Long empNumber){
        if (empNumber == null) {
            throw new IllegalArgumentException("시터 번호 누락");
        }

        return Optional.ofNullable(sitterMapper.sitterReview(empNumber))
                .orElseThrow(()->{throw new IllegalArgumentException("없는 시터 정보");});
    }

    //    고객이 선택한 시터 주소 리스트
        public List<EmpVo> addrCheck(Criteria criteria) {
            return sitterMapper.addrCheck(criteria);
        }
    //주소로 선택한 시터 수
    public int sitterAddrTotal() {
        return sitterMapper.sitterAddrTotal();
    }

//    시터 평점
    public List<SitterBoardVo> selectSitterAvg() {

        return sitterMapper.sitterAvg();
    }

}
