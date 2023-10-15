package com.example.bomobomo.domain.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
// 마이페이지 직원 이미지 및 직원 정보를 담는 Vo
@Component
@Data
@NoArgsConstructor
public class MatchEmpInfoVo {
    private Long empNumber;
    private String empName;
    private Integer empAge;
    private String empGender;
    private String empPhone;
    private String empEmail;
    private String empDate;
    private String empContent;
    private String empImgName;
    private String empImgUploadPath;
    private String empImgUuid;

}
