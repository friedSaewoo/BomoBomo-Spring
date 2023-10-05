package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class EventBoardImgDto {

    Long eventBoardImgNumber;
    Long eventBoardNumber;
    String eventBoardImgName;
    String evntBoardImgUploadPath;
    String eventBoardImgUuid;

}
