package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class AddressDto {

    private Long addressNumber;
    private Long userNumber;
    private String addressPost;
    private String address;
    private String addressDetail;
}
