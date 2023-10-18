package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class UserAddressVo {
    private Long userNumber;
    private Long matchNumber;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String address;
    private String addressDetail;
}
