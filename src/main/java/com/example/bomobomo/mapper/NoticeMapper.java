package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.NoticeDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.NoticeVo;
import com.example.bomobomo.domain.vo.SearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {

    //공지사항 전체조회
    public List<NoticeDto> selectAll(Criteria criteria);

    //공지사항 상세보기
    public NoticeVo selectOne(Long noticeNumber);

    //공지사항 게시물 총 개수
    public int getTotal();

    public int getTotal2(SearchVo searchVo);

    public List<NoticeDto> searchResult(@Param("criteria") Criteria criteria, @Param("searchVo")SearchVo searchVo);

}
