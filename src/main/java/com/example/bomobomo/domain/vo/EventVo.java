package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EventVo {
    Long eventNumber;
    Long eventImgNumber;
    Long eventDetailNumber;
    String eventName;
    Integer eventPrice;
    String eventImgName;
    String eventDetailName;

}
