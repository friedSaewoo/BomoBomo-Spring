package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.EventBoardDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.EventBoardVo;
import com.example.bomobomo.domain.vo.EventNameNumberVo;
import com.example.bomobomo.mapper.EventBoardMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventBoardService {
    private final EventBoardMapper eventBoardMapper;
    private final EventBoardFileService eventBoardFileService;

    //리뷰 작성 후 삽입
    public void register(EventBoardDto eventBoardDto){
        eventBoardMapper.insert(eventBoardDto);
    }

    //리뷰 작성시 결제한 이벤트 이름 조회하기
    public EventNameNumberVo findEventTitle(Long eventPayNumber){
        if (eventPayNumber == null) {
            throw new IllegalArgumentException("조회된 이벤트 번호 누락!");
        }

        return Optional.ofNullable(eventBoardMapper.selectEventTitle(eventPayNumber))
                .orElseThrow(()->{throw new IllegalArgumentException("조회결과 없음!");});

    }

    // 이벤트 게시판에서 내가 작성한 게시판 전체 조회
    public List<EventBoardVo> findEventAll(@Param("criteria") Criteria criteria,
                                             @Param("userNumber")Long userNumber){
        if (userNumber == null) {
            throw new IllegalArgumentException("조회된 유저가 없습니다");
        }

        return eventBoardMapper.selectEventAll(criteria,userNumber);

    }

    //마이페이지의 페이징 처리를 위한 전체 글 조회
    public int findEventReviewTotal(Long userNumber){
        if (userNumber == null) {
            throw new IllegalArgumentException("유저 번호 누락!");
        }
        return eventBoardMapper.selectEventReviewTotal(userNumber);
    }

    // 게시물 파일 저장 최종 처리
    public void registerAndFileproc(EventBoardDto eventBoardDto, MultipartFile file){
        register(eventBoardDto);

        if(file.isEmpty()){
            return; // 파일을 올리지 않을시 메소드 종료처리
        }

        try {
            eventBoardFileService.registerAndSaveFile(file,eventBoardDto.getEventBoardNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
