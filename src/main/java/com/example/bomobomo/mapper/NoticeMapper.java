package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.NoticeDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.SearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {
    //게시물 조회 / 검색
    public List<NoticeDto> selectAll(@Param("criteria") Criteria criteria, @Param("searchVo")SearchVo searchVo);


    //공지사항 상세보기
    public NoticeDto selectOne(Long noticeNumber);


    //게시물 총 개수
    public int getTotal(SearchVo searchVo);


    //조회수
    public void updateCount(Long noticeNumber);


    //공지사항 전체조회
//    public List<NoticeDto> selectAll(Criteria criteria);

    //공지사항 게시물 총 개수
//    public int getTotal();

}
