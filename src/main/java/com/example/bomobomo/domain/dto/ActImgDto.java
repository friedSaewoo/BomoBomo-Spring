package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class ActImgDto {
    private Long actImgNumber;
    private Long actNumber;
    private String actImgName;
    private String actImgUploadPath;
    private String actImgUuid;
}
