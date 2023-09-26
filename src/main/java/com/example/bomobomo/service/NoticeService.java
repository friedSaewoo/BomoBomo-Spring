package com.example.bomobomo.service;



import com.example.bomobomo.domain.dto.NoticeDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.SearchVo;
import com.example.bomobomo.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {


    private final NoticeMapper noticeMapper;



    //공지사항 리스트 및 검색 리스트
    public List<NoticeDto> selectAll(Criteria criteria, SearchVo searchVo){
        return noticeMapper.selectAll(criteria, searchVo);
    }

    //공지사항 상세보기
    public NoticeDto selectOne(Long noticeNumber){

        if (noticeNumber == null) {
            throw new IllegalArgumentException("공지사항 번호 누락");
        }

        return Optional.ofNullable(noticeMapper.selectOne(noticeNumber))
                .orElseThrow(()->{throw new IllegalArgumentException("존재하지 않는 공지 게시 번호");});
    }


    //공지사항 게시물 수(검색결과 포함)
    public int getTotal(SearchVo searchVo){
        return noticeMapper.getTotal(searchVo);
    }



    //공지사항 조회수
    public void updateCount(Long noticeNumber){


        if (noticeNumber == null) {
            throw new IllegalArgumentException("공지사항 번호 누락!");
        }

        noticeMapper.updateCount(noticeNumber);
    }



}
