package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EventCommentDto {
    private Long eventCommentNumber;
    private Long eventBoardNumber;
    private Long userNumber;
    private String eventCommentContent;
    private String eventCommentRegisterDate;
    private String eventCommentModifyDate;
}
