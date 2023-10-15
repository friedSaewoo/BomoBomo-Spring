package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MyPageSitterVo {


    private Long matchNumber;
    private Long userNumber;
    private Long submitOrderNumber;
    private Long estContentNumber;
    private Long empNumber;
    private String status;
    private Integer estPrice;
    private String empName;
    private Long empImgNumber;
    private String empImgName;
    private String empImgUploadPath;
    private String empImgUuid;
    private Integer reviewCheck;
    private String estTitle;

// buy 페이지에서 씨터 결제내역을 저장하기위한 클래스


}
