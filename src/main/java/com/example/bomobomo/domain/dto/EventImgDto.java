package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EventImgDto {
    Long eventImgNumber;
    Long eventNumber;
    String eventImgName;
    String eventImgUploadPath;
    String eventImgUuid;
}
