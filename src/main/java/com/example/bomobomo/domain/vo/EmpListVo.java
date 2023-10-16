package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@NoArgsConstructor
public class EmpListVo {
    private Long empNumber;
    private String empName;
    private Integer empAge;
    private String empGender;
    private String empPhone;
    private String empEmail;
    private String empDate;
    private String empContent;
    private String avg;
    private String empImgName;
    private String empImgUploadPath;
    private String empImgUuid;

    private List<ActVo> ActImgList;
}
