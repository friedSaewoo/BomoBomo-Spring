package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MyPageSitterVo {
//    MATCH_NUMBER, USER_NUMBER, SUBMIT_ORDER_NUMBER, EST_NUMBER, EMP_NUMBER, STATUS,
//    TOTAL_PRICE,
//    EMP_NAME,
//    EMP_IMG_NUMBER, EMP_IMG_NAME, EMP_IMG_UPLOAD_PATH, EMP_IMG_UUID, CNT AS REVIEW_CHECK

    //MATCH_NUMBER, USER_NUMBER, SUBMIT_ORDER_NUMBER, EMP_NUMBER, STATUS, EST_TITLE, EMP_IMG_NAME, EMP_IMG_UPLOAD_PATH, EMP_IMG_UUID


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



}
