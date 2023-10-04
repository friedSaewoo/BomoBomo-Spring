package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.SitterCommentDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.SitterCommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //돌봄 서비스 리뷰
    //댓글달기 삽입
    public void insert(SitterCommentDto sitterCommentDto);


    //댓글리스트 조회
    public List<SitterCommentVo> select(Long sitterBoardNumber);


    //댓글리스트 조회(페이징포함)
    public List<SitterCommentVo> selectList(@Param("sitterBoardNumber") Long sitterBoardNumber,
                                            @Param("criteria") Criteria criteria);


    //댓글 수정
    public void update(SitterCommentDto sitterCommentDto);


    //댓글삭제
    public void delete(Long sitterCommentNumber);


    //후기 게시물 당 댓글 총 개수
    public int getTotal(Long sitterBoardNumber);
    

    
    //======================================================
    
    //이벤트 서비스 리뷰
    //댓글달기 삽입
}
