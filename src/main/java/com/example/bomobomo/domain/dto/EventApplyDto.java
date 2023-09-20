package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EventApplyDto {
    Long applyNumber;
    Long userNumber;
    Long eventNumber;
}
