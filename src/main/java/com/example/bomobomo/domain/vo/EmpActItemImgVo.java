package com.example.bomobomo.domain.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

// 마이페이지에서 직원의 활동과 활동 이미지를 담는 Vo
@Component
@Data
@NoArgsConstructor
public class EmpActItemImgVo {

    private Long empNumber;
    private Long empActItemNumber;
    private Long actNumber;
    private Long actImgNumber;
    private String actImgName;
    private String actImgUploadPath;
    private String actImgUuid;
    private String actName;

}
