package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class UserDetailVo {
    private Long userNumber;
    private String userId;
    private String userName;
    private String userEmail;
    private String registerDate;
    private String userPhone;
    private String addressPost;
    private String address;
    private String addressDetail;
}
