package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EventApplyDto {
    private Long applyNumber;
    private Long userNumber;
    private Long eventNumber;
}
