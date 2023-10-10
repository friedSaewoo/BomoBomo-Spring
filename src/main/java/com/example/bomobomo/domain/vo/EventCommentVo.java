package com.example.bomobomo.domain.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class EventCommentVo {


    private Long eventCommentNumber;
    private String eventCommentContent;
    private String eventCommentRegisterDate;
    private String eventCommentModifyDate;
    private Long eventBoardNumber;
    private Long userNumber;
    private String userId;


}
