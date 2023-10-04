package com.example.bomobomo.service;


import com.example.bomobomo.domain.dto.SitterBoardDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import com.example.bomobomo.mapper.SitterBoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SitterBoardService {
    private SitterBoardMapper sitterBoardMapper;

    //등록
    public void register(SitterBoardDto sitterBoardDto){
        if (sitterBoardDto == null) {
            throw new IllegalArgumentException("등록된 게시물이 없습니다.");
        }
        sitterBoardMapper.insert(sitterBoardDto);
        log.info(sitterBoardDto.toString());
        // 결제내역에서 확인된 직원 번호, 매칭번호가 필요함 해당

    }

    //해당 유저가 작성한 게시판 후기 조회(마이페이지 및 게시판에서 조회해서 사용)
    public List<SitterBoardVo> findAll(Criteria criteria, Long userNumber){
        if (userNumber == null) {
            throw new IllegalArgumentException("등록한 회원이 없습니다");
        }

        return sitterBoardMapper.selectAll(criteria, userNumber);
    };

    public String findTitle(Long matchNumber){
        if (matchNumber == null) {
            throw new IllegalArgumentException("매치번호 누락!");
        }
        return Optional.ofNullable(sitterBoardMapper.selectTitle(matchNumber))
                .orElseThrow(()->{throw new IllegalArgumentException("조회 결과 없음"); });
    }


    //    메인페이지 시터 후기 토탈 조회
    public int findSitterReviewTotal(Long userNumber){
        if (userNumber == null) {
            throw new IllegalArgumentException("회원 번호 누락!");
        }

        return sitterBoardMapper.selectSitterReviewTotal(userNumber);
    }
}
