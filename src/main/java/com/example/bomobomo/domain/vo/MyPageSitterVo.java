package com.example.bomobomo.domain.vo;

import com.example.bomobomo.domain.dto.EstContentDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
public class MyPageSitterVo {


    private Long matchNumber;
    private Long userNumber;
    private Long submitOrderNumber;

    private Long empNumber;
    private String status;

    private String empName;
    private Long empImgNumber;
    private String empImgName;
    private String empImgUploadPath;
    private String empImgUuid;
    private Integer reviewCheck;

    //    private Integer estPrice;
    //    private Long estContentNumber;
    //    private String estTitle;

    // buy 페이지에서 씨터 결제내역을 저장하기위한 클래스
    private List<EstContentDto> estList;
    private int totalPrice;
}
