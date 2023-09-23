package com.example.bomobomo.service;



import com.example.bomobomo.domain.dto.NoticeDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.NoticeVo;
import com.example.bomobomo.domain.vo.SearchVo;
import com.example.bomobomo.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {


    private final NoticeMapper noticeMapper;


    //전체조회
    public List<NoticeDto> selectAll(Criteria criteria){
        return noticeMapper.selectAll(criteria);
    }

    //공지사항 상세보기
    public NoticeVo selectOne(Long noticeNumber){
        return noticeMapper.selectOne(noticeNumber);
    }

    public int getTotal(){
        return noticeMapper.getTotal();
    }


    public int getTotal2(SearchVo searchVo){
        return noticeMapper.getTotal2(searchVo);
    }

    //최종 공지사항 서비스
    public List<NoticeDto> searchResult(Criteria criteria, SearchVo searchVo){
        return noticeMapper.searchResult(criteria, searchVo);
    }
}
