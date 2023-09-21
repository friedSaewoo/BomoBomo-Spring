package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class OrderDto {
    Long orderNumber;
    Long userNumber;
    String genderFirst;
    String genderSecond;
    String kidsContent;
}
