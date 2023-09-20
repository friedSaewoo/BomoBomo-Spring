package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EventBoardDto {
    Long eventBoardNumber;
    Long userNumber;
    Long eventNumber;
    String eventBoardContent;
    String eventBoardRegisterDate;
    String eventBoardModifyDate;
    Integer rating;
    Integer eventBoardCount;
}
