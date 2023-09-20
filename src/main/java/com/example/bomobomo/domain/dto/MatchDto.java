package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class MatchDto {
    private Long matchNumber;
    private Long userNumber;
    private Long submitOrderNumber;
    private Long estNumber;
    private Long empNumber;
    private String status;
}
