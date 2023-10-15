package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EventDto {
    private Long eventNumber;
    private String eventName;
    private Integer eventPrice;
}
