package com.example.bomobomo.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MatchEmpRatingAvgVo {
    Long matchNumber;
    Long userNumber;
    Long submitOrderNumber;
    Long empNumber;
    Integer status;
    Integer avg;

}
