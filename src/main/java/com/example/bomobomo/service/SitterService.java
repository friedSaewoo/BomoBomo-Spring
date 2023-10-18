package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.*;
import com.example.bomobomo.domain.vo.*;
import com.example.bomobomo.mapper.AdminMapper;
import com.example.bomobomo.mapper.SitterMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SitterService {
    private final SitterMapper sitterMapper;
    private final AdminMapper adminMapper;

    // 전체 시터의 수 조회
    public int sitterTotalPeople(String cityName, String countryName, Integer actNumber) {
        return sitterMapper.sitterTotal(cityName, countryName, actNumber);
    }

    // 시터 전체 조회
    public List<EmpListVo> sitterSelect(Criteria criteria, String cityName, String countryName, Integer actNumber) {
        List<EmpListVo> empListVos = sitterMapper.sitterList(criteria, cityName, countryName, actNumber);

        for(EmpListVo empListVo : empListVos){
            Long num = empListVo.getEmpNumber();
            List<ActVo> actVo= adminMapper.selectEmpAct(num);
            empListVo.setActImgList(actVo);
        }


        return empListVos;
    }

    // 시터 정보 조회
    public EmpVo sitterInfo(Long empNumber) {
        System.out.println("서비스 시터 번호 : " + empNumber);
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
    public Double sitterReview(Long empNumber){

        if (empNumber == null) {
            throw new IllegalArgumentException("시터 번호 누락");
        }

        return sitterMapper.sitterReview(empNumber);
//        return Optional.ofNullable(sitterMapper.sitterReview(empNumber))
//                                    .orElseThrow(()->{throw new IllegalArgumentException("널값이 존재함");});
    }

    //    고객이 선택한 시터 주소 리스트
        public List<EmpListVo> addrCheck(Criteria criteria) {
            return sitterMapper.addrCheck(criteria);
        }
    //주소로 선택한 시터 수
    public int sitterAddrTotal() {
        return sitterMapper.sitterAddrTotal();
    }

    //시터 활동 리스트
    public List<ActVo> sitterPossibleList(Long empNumber) {
        System.out.println("서비스 진입 확인");
        System.out.println("서비스 확인 : " + sitterMapper.sitterPossibleList(empNumber));
        return  sitterMapper.sitterPossibleList(empNumber);
    }

    //시터 정보 + 이미지 리스트
    public List<EmpListVo> sitterActImg() {
        return  sitterMapper.sitterActImg();
    }

//    시터 이미지
    public EmpImgDto sitterImg(Long empNumber) {

        return sitterMapper.sitterImg(empNumber);
    }

    //고객이 선택한 시터 신청서 제출
    public void register(SubmitOrderDto submitOrderDto) {
        System.out.println("register 서비스 오는지");
        sitterMapper.register(submitOrderDto);
    }

//       신청서 제출 시터와 매칭
    public void sitterMatching(MatchDto matchDto) {
        System.out.println("sitterMatching 서비스 오는지");
        sitterMapper.sitterMatching(matchDto);
    }

    //고객이 매칭중인 시터가 있는지 확인
    public Long userMatchCheck(Long userNumber) {
        return sitterMapper.userMatchCheck(userNumber);
    }
}
