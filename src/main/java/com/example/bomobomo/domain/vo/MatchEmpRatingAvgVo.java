package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MatchEmpRatingAvgVo {
    private Long matchNumber;
    private Long userNumber;
    private Long submitOrderNumber;
    private Long empNumber;
    private Integer status;
    private Integer avg;

}
