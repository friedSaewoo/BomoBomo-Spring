package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

    @Data
    @Component
    @NoArgsConstructor
    public class EventVo {
        Long eventNumber;
        String eventName;
        Integer eventPrice;
        String eventImgName;
        String eventImgUploadPath;
        String eventImgUuid;
        String eventDetailName;
        String eventDetailUploadPath;
        String eventDetailUuid;
    }
