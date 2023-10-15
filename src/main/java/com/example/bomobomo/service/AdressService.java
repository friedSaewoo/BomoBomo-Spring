package com.example.bomobomo.service;


import com.example.bomobomo.domain.dto.AddressDto;
import com.example.bomobomo.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdressService {
    private  final AddressMapper addressMapper;

    public void modify(AddressDto addressDto){
        if (addressDto == null) {
            throw new IllegalArgumentException("주소내용 누락!!");
        }
        addressMapper.update(addressDto);
    }
}
