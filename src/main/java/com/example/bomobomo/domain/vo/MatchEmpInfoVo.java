package com.example.bomobomo.domain.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MatchEmpInfoVo {
    private Long matchNumber;
    private Long userNumber;
    private Long submitOrderNumber;
    private Long empNumber;
    private Integer status;
    private String empName;
    private Integer empAge;
    private String empGender;
    private Long empImgNumber;
    private String empImgName;
    private String empImgUploadPath;
    private String empImgUuid;
    private Long empActItemNumber;
    private Long actNumber;
    private String actName;
    private String actImgName;
    private String actImgUploadPath;
    private String actImgUuid;
}
