package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class MyPageEventVo {
    private Long eventPayNumber;
    private Long applyNumber;
    private Long userNumber;
    private Long eventNumber;
    private String eventDate;
    private String eventName;
    private Integer eventPrice;
    private String eventImgName;
    private String eventImgUploadPath;
    private String eventImgUuid;
    private Integer reviewCheck;
    private Integer rnum;
    // buy 페이지에서 이벤트 결제내역을 저장하기위한 클래스
}
