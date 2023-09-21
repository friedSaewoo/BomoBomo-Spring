package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EventDetailDto {
    Long eventDetailNumber;
    Long eventNumber;
    String eventDetailName;
    String eventDetailUploadPath;
    String eventDetailUuid;
}
