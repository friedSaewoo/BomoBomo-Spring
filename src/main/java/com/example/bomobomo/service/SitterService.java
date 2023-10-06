package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.mapper.SitterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SitterService {

    private final SitterMapper sitterMapper;

    public int sitterTotalPeople() {

        System.out.println("확인 : " + sitterMapper.sitterTotal());
        return sitterMapper.sitterTotal();
    }

    // 시터 전체 조회
    public List<EmpDto> sitterSelect(Criteria criteria) {
        System.out.println("서비스의 sitterSelect 메소드");
        return sitterMapper.sitterList(criteria);
    }
    //    전체 시터 수 조회
    public int getTotal(){
        System.out.println("서비스의 getTotal 메소드");
        return sitterMapper.selectTotal();
    }

    // 시터 정보 조회
    public EmpDto sitterInfo(Long empNumber) {
        if (empNumber == null) {
            throw new IllegalArgumentException("게시판 번호 누락!!");
        }

        return Optional.ofNullable(sitterMapper.sitterInfo(empNumber))
                .orElseThrow(() -> { throw new IllegalArgumentException("없는 시터 정보"); });

    }

//    public BoardVo find(Long boardNumber){
//        if (boardNumber == null) {
//            throw new IllegalArgumentException("게시판 번호 누락!!");
//        }
//
//        return Optional.ofNullable(boardMapper.select(boardNumber))
//                .orElseThrow(() -> { throw new IllegalArgumentException("존재하지 않는 회원 번호!"); });
//    }


}
