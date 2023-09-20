package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class EmpActItemDto {

    private Long empActItemNumber;
    private Long actNumber;
    private Long empNumber;
}
