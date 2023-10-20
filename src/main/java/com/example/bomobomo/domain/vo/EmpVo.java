package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EmpVo {
    private Long empNumber;
    private String empName;
    private Integer empAge;
    private String empGender;
    private String empPhone;
    private String empEmail;
    private String empDate;
    private String empContent;

    private Long empImgNumber;
    private String empImgName;
    private String empImgUploadPath;
    private String empImgUuid;

    private Long cityNumber;
    private String cityName;
    private Long countryNumber;
    private String countryName;
}
