package com.example.bomobomo.service;


import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.MyPageEventVo;
import com.example.bomobomo.domain.vo.MyPageSitterVo;
import com.example.bomobomo.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final MyPageMapper myPageMapper;

    // 시터이용 결제 전체 리스트 조회
    public List<MyPageSitterVo> findSitterList(Criteria criteria, Long userNumer){
        if (userNumer == null) {
            throw new IllegalArgumentException("회원정보 없음!");
        }

        return myPageMapper.selectSitterList(criteria,userNumer);

    };
    //시터 결제 리스트 토탈 조회
    public int getTotal(Long userNumber){
        if (userNumber == null) {
            throw new IllegalArgumentException("회원 번호 누락!");
        }
        return myPageMapper.selectTotal(userNumber);
    }

    //이벤트 결제 내역 전체 리스트 조회
    public List<MyPageEventVo> findEventList(Criteria criteria,Long userNumber){
        if (userNumber == null) {
            throw new IllegalArgumentException("회원번호 누락!");
        }

        return myPageMapper.selectEventList(criteria,userNumber);
    }

    //이벤트 결제 내역 전체 페이지 조회
    public int findEventTotal(Long userNumber){
        if (userNumber == null) {
            throw new IllegalArgumentException("회원번호 누락!");
        }

        return myPageMapper.selectEventTotal(userNumber);
    }
}
