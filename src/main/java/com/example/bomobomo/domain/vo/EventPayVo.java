package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EventPayVo {
    Long applyNumber;
    Long userNumber;
    Long eventNumber;
    String eventDate;
    Long eventPayNumber;

}
