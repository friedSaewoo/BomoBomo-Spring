package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.SitterCommentDto;
import com.example.bomobomo.domain.vo.SitterCommentVo;
import com.example.bomobomo.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyRestController {


    private final ReplyService replyService;


    @PostMapping("")
    public void serviceReviewReply(@RequestBody SitterCommentDto sitterCommentDto){
        replyService.register(sitterCommentDto);


    }


    @GetMapping("/list/{sitterBoardNumber}")
    public List<SitterCommentVo> showReplyList(@PathVariable("sitterBoardNumber") Long sitterBoardNumber){

        return replyService.findAll(sitterBoardNumber);
    }


}
