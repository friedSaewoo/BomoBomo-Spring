package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class NoticeDto {

    private Long noticeNumber;
    private String noticeTitle;
    private String noticeContent;
    private String noticeRegisterDate;
    private String noticeModifyDate;
    private Integer noticeCount;

}
