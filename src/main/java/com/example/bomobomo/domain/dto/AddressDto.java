package com.example.bomobomo.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class AddressDto {
    Long addressNumber;
    Long userNumber;
    String addressPost;
    String address;
    String addressDetail;
}
