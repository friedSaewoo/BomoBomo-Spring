package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class SitterBoardDto {
    private Long sitterCommentNumber;
    private Long sitterBoardNumber;
    private Long userNumber;
    private String sitterCommentContent;
    private String sitterCommentRegisterDate;
    private String sitterCommentModifyDate;
}
