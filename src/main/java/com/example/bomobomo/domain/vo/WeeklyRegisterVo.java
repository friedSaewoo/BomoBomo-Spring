package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class WeeklyRegisterVo {
    private String monthDay;
    private int dailyUserCount;
}
