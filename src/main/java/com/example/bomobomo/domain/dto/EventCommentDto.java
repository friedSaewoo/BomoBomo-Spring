package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EventCommentDto {
    Long eventCommentNumber;
    Long eventBoardNumber;
    Long userNumber;
    String eventCommentContent;
    String eventCommentRegisterDate;
    String eventCommentModifyDate;
}
