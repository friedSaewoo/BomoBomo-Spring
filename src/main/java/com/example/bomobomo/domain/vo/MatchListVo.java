package com.example.bomobomo.domain.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class MatchListVo {
//    MATCH_NUMBER,STATUS,USER_NUMBER,USER_NAME,USER_ID,USER_PHONE,emp_number,emp_name,emp_email,emp_phone
    private Long matchNumber;
    private Long userNumber;
    private Long empNumber;
    private String status;
    private String userName;
    private String userId;
    private String userPhone;
    private String empName;
    private String empEmail;
    private String empPhone;
}
