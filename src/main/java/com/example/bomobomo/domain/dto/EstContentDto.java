package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class EstContentDto {
    private Long estContentNumber;
    private String estContent;
    private Integer estPrice;
    private String estTitle;
    private Long matchNumber;



}
