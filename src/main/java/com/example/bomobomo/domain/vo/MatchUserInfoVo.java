package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MatchUserInfoVo {
    private Long matchNumber;
    private Long userNumber;
    private Long submitOrderNumber;
    private Long empNumber;
    private String status;
    private String userName;
    private String userphone;
    private String userEmail;
    private String address;
    private String addressDetail;

}
