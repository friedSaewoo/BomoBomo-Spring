package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.SitterCommentDto;
import com.example.bomobomo.domain.vo.SitterCommentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

        //댓글달기 삽입

    public void insert(SitterCommentDto sitterCommentDto);



        //댓글리스트 조회
    public List<SitterCommentVo> selectList(Long sitterBoardNumber);


    //댓글삭제

    public void delete(Long sitterCommentNumber);

}
