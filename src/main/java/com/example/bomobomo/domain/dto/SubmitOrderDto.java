package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SubmitOrderDto {
    private Long submitOrderNumber;
    private String genderFirst;
    private String genderSecond;
    private String kidsContent;
}
