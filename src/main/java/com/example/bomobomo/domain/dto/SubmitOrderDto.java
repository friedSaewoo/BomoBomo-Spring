package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SubmitOrderDto {
    private Long submitOrderNumber;
    private Integer genderFirst;
    private Integer genderSecond;
    private String kidsContent;
}
