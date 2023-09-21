package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EmpImgDto {
    private Long empImgNumber;
    private Long empNumber;
    private String empImgName;
    private String empImgUploadPath;
    private String empImgUuid;
}
