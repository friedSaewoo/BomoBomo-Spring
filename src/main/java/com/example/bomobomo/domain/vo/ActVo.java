package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ActVo {
    private Long actImgNumber;
    private Long actNumber;
    private String actImgName;
    private String actImgUploadPath;
    private String actImgUuid;
    private String actName;
}
