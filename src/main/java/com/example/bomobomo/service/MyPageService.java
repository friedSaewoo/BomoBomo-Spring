package com.example.bomobomo.service;


import com.example.bomobomo.domain.dto.AddressDto;
import com.example.bomobomo.domain.dto.MatchDto;
import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.domain.vo.*;
import com.example.bomobomo.mapper.MyPageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.binding.BindingException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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

    // 마이페이지 진입시 매칭되는 직원의 상태 출력
    public MatchDto findMatch(Long userNumber) throws NullPointerException{
        if (userNumber == null) {
            throw new IllegalArgumentException("회원정보 누락!");
        }
        System.out.println("유저번호 : " + userNumber);
        return myPageMapper.selectMatch(userNumber);
    }

    //메칭된 직원의 정보와 이미지 조회
    public MatchEmpInfoVo findEmpInfoImg(Long empNumber){
        if (empNumber == null) {
            throw new IllegalArgumentException("직원정보 누락!");
        }

        return Optional.ofNullable(myPageMapper.selectEmpInfoImg(empNumber))
                .orElseThrow(()->{throw new IllegalArgumentException("매칭정보 없음");});
    }

    //매칭된 직원의 활동 이름과 활동 이미지 조회
    public List<EmpActItemImgVo> findEmpActItemImg(Long empNumber){
        if (empNumber == null) {
            throw new IllegalArgumentException("직원번호누락!");
        }
        return myPageMapper.selectEmpActItemImg(empNumber);


    }

    //매칭된 직원의 평점을 구하는 쿼리
    public double findMatchEmpRating(Long empNumber){
        if (empNumber == null) {
            throw new IllegalArgumentException("회원정보 없음!");
        }
        log.info("=========================전");

        double avg = 0;
        try {
            avg = myPageMapper.selectMatchEmpRating(empNumber);
        } catch (BindingException e) {
            avg=0.0;
        }

        log.info("===================바보놈{}",avg);
        log.info("=========================후");
        log.info("===================바보놈{}",avg);
        return avg;
//        return Optional.ofNullable(myPageMapper.selectMatchEmpRating(empNumber))
//                .orElseThrow(()->{throw new IllegalArgumentException("평균점수 조회 없음!");});
    }

     // 매치된 회원의 정보
        public MatchUserInfoVo findMatchUserInfo(Long userNumber){
            if (userNumber == null) {
                throw new IllegalArgumentException("결제 정보 없음!");
            }

            return Optional.ofNullable(myPageMapper.selectMatchUserInfo(userNumber))
                    .orElseThrow(()->{throw new IllegalArgumentException("회원정보 없음!");});
        }
    //
    //    //결제 정보
        public MatchBuyInfoVo findMatchBuyInfo(Long userNumber){
            if (userNumber == null) {
                throw new IllegalArgumentException("정보없음!");

            }

            return Optional.ofNullable(myPageMapper.selectMatchBuyInfo(userNumber))
                    .orElseThrow(()->{throw new IllegalArgumentException("없음");});
        }
        // 결제후 상태 수정
        public MatchDto statusUpdate(Long matchNumber){
            if ( matchNumber == null) {
                throw new IllegalArgumentException("회원 정보 없음!");
            }

            return Optional.ofNullable(myPageMapper.update(matchNumber))
                    .orElseThrow(()->{throw new IllegalArgumentException("일치하는 정보가 없습니다");});
        }

        // 마이페이지 회원정보 수정 회원 디폴트값 조회
        public UserDto findUser(Long userNumber){
            if (userNumber == null) {
                throw new IllegalArgumentException("회원정보 없음!");
            }

            return Optional.ofNullable(myPageMapper.selectUser(userNumber))
                    .orElseThrow(()->{throw new IllegalArgumentException("회원정보 없음!");});
        }

        //마이페이지 회원정보 수정 회원 주소 조회
        public AddressDto findUserAddress(Long userNumber){
            if (userNumber == null) {
                throw new IllegalArgumentException("회원정보 없음!");
            }

            return Optional.ofNullable(myPageMapper.selectUserAddress(userNumber))
                    .orElseThrow(()->{throw new IllegalArgumentException("주소정보 없음!");});

        }



}

























