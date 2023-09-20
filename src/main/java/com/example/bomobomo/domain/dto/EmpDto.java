package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EmpDto {
    private Long empNumber;
    private String empName;
    private Integer empAge;
    private String empGender;
    private String empPhone;
    private String empEmail;
    private String empDate;
    private String empContent;
}
