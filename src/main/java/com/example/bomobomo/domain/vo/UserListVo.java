package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class UserListVo {
    private Long userNumber;
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String registerDate;
    private String userPhone;
    private Integer review;
}
