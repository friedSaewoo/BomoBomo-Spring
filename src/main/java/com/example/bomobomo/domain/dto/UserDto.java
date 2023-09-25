package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@NoArgsConstructor
public class UserDto {
    private Long userNumber;
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String registerDate;
    private String userPhone;
    private String userPostal;
    private String userAddr1;
    private String userAddr2;

}
