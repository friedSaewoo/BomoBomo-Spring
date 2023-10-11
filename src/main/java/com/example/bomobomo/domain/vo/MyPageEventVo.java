package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class MyPageEventVo {
    Long eventPayNumber;
    Long applyNumber;
    Long userNumber;
    Long eventNumber;
    String eventDate;
    String eventName;
    Integer eventPrice;
    String EventImgName;
    String eventImgUploadPath;
    String eventImgUuid;
    Integer reviewCheck;
    Integer rnum;
    // buy 페이지에서 이벤트 결제내역을 저장하기위한 클래스
}
