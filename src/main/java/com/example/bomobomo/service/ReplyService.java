package com.example.bomobomo.service;


import com.example.bomobomo.domain.dto.SitterCommentDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.EventCommentVo;
import com.example.bomobomo.domain.vo.SitterCommentVo;
import com.example.bomobomo.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {


    private final ReplyMapper replyMapper;


    //돌봄서비스 리뷰 댓글
    //댓글 입력
    public void register(SitterCommentDto sitterCommentDto){
       replyMapper.insert(sitterCommentDto);
    }


    //댓글 리스트 조회
    public List<SitterCommentVo> find(Long sitterBoardNumber){
        if (sitterBoardNumber == null) {

            throw new IllegalArgumentException("리뷰 게시번호 누락");
        }

        return replyMapper.select(sitterBoardNumber);
    }


    //댓글 리스트 조회(페이징 포함)
    public List<SitterCommentVo> findAll(Long sitterBoardNumber, Criteria criteria) {

        if (sitterBoardNumber == null) {
            throw new IllegalArgumentException("리뷰 게시 번호 누락");
        }

        return replyMapper.selectList(sitterBoardNumber, criteria);
    }

    //댓글 수정
    public void modify(SitterCommentDto sitterCommentDto){
        replyMapper.update(sitterCommentDto);

    }

    //댓글 삭제
    public void remove(Long sitterCommentNumber){
        if (sitterCommentNumber == null) {
            throw new IllegalArgumentException("댓글 번호 누락");

        }

        replyMapper.delete(sitterCommentNumber);
    }

    //댓글 개수 가져오기
    public int getTotal(Long sitterBoardNumber){
        if (sitterBoardNumber == null) {
            throw new IllegalArgumentException("리뷰게시판 번호 누락");
        }
       return replyMapper.getTotal(sitterBoardNumber);
    }


    //============================================
    //이벤트 서비스 리뷰 댓글
    //댓글 등록
    public void registerEventReply(EventCommentVo eventCommentVo){
        replyMapper.insertEventReply(eventCommentVo);

    }

    //댓글 리스트조회(페이징 포함)
    public List<EventCommentVo> findAllEventReply(Long eventBoardNumber, Criteria criteria){

        if (eventBoardNumber == null) {
            throw new IllegalArgumentException("이벤트 보드 넘버 누락");
        }

        return replyMapper.selectListEventReply(eventBoardNumber, criteria);
    }

    //댓글 수정
    public void modifyEventReply(EventCommentVo eventCommentVo){
        replyMapper.updateEventReply(eventCommentVo);
    }


    //댓글 삭제
    public void removeEventReply(Long eventCommentNumber){
        if (eventCommentNumber == null) {
            throw new IllegalArgumentException("이벤트 댓글 번호 누락");
        }

        replyMapper.deleteEventReply(eventCommentNumber);
    }

    //댓글 개수 가져오기
    public int getTotalEventReply(Long eventBoardNumber){
        if (eventBoardNumber == null) {
            throw new IllegalArgumentException("이벤트 게시판 번호 누락");
        }

        return replyMapper.getTotalEventReply(eventBoardNumber);

    }
}
