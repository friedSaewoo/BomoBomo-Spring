package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class OrderDto {
    private Long orderNumber;
    private Long userNumber;
    private String genderFirst;
    private String genderSecond;
    private String kidsContent;
}
