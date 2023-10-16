package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EventBoardDto {
    private Long eventBoardNumber;
    private Long userNumber;
    private Long eventNumber;
    private String eventBoardContent;
    private String eventBoardRegisterDate;
    private String eventBoardModifyDate;
    private Integer rating;
    private Integer eventBoardCount;
    private Long eventPayNumber;
}
