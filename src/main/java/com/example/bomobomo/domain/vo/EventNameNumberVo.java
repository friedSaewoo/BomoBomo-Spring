package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class EventNameNumberVo {
    private Long eventNumber;
    private String eventName;
    private Long eventPayNumber;

}
