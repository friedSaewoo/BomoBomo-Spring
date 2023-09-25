package com.example.bomobomo.service;


import com.example.bomobomo.domain.dto.SitterCommentDto;
import com.example.bomobomo.domain.vo.SitterCommentVo;
import com.example.bomobomo.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {


    private final ReplyMapper replyMapper;


    //댓글 입력
    public void register(SitterCommentDto sitterCommentDto){
       replyMapper.insert(sitterCommentDto);
    }



    //댓글 리스트 조회
    public List<SitterCommentVo> findAll(Long sitterBoardNumber) {

        if (sitterBoardNumber == null) {
            throw new IllegalArgumentException("리뷰 게시 번호 누락");
        }

        return replyMapper.selectList(sitterBoardNumber);
    }


    //댓글 삭제
    public void remove(Long sitterCommentNumber){
        if (sitterCommentNumber == null) {
            throw new IllegalArgumentException("댓글 번호 누락");

        }

        replyMapper.delete(sitterCommentNumber);
    }

}
