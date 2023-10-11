package com.example.bomobomo.domain.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
// 마이페이지 직원 이미지 및 직원 정보를 담는 Vo
@Component
@Data
@NoArgsConstructor
public class MatchEmpInfoVo {
    Long empNumber;
    String empName;
    Integer empAge;
    String empGender;
    String empPhone;
    String empEmail;
    String empDate;
    String empContent;
    String empImgName;
    String empImgUploadPath;
    String empImgUuid;

}
