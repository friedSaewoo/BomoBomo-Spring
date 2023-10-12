package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class EventBoardImgDto {

    private Long eventBoardImgNumber;
    private Long eventBoardNumber;
    private String eventBoardImgName;
    private String eventBoardImgUploadPath;
    private String eventBoardImgUuid;

}
