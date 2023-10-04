package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class SitterBoardDto {
    private Long sitterBoardNumber;
    private String sitterBoardContent;
    private String sitterBoardRegisterDate;
    private String sitterBoardModifyDate;
    private Integer rating ;
    private Integer sitterBoardCount;
    private Long userNumber;
    private Long empNumber;
    private Long matchNumber;
}
