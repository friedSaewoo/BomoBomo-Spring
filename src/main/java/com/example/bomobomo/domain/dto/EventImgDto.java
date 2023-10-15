package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EventImgDto {
    private Long eventImgNumber;
    private Long eventNumber;
    private String eventImgName;
    private String eventImgUploadPath;
    private String eventImgUuid;
}
