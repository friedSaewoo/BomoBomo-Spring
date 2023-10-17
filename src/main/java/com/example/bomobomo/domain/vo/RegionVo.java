package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class RegionVo {
    private Long cityNumber;
    private Long countryNumber;
    private String cityName;
    private String countryName;
}
