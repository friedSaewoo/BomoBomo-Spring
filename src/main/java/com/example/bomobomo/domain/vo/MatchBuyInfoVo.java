package com.example.bomobomo.domain.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MatchBuyInfoVo {
    private Long matchNumber;
    private Long userNumber;
    private Long submitOrderNumber;
    private Long empNumber;
    private String status;
    private String estContent;
    private String estTitle;
    private Integer estPrice;

}
