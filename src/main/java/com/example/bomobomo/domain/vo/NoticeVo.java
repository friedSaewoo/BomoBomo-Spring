package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class NoticeVo {

    Long noticeNumber;
    String noticeTitle;
    String noticeContent;
    String noticeRegisterDate;
    String noticeModifyDate;
    Integer noticeCount;

}
