package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.AddressDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
@Transactional
class AddressMapperTest {
    @Autowired
    AddressMapper addressMapper;

    AddressDto addressDto;

    @BeforeEach
    void setUp() {
        addressDto=new AddressDto();
        addressDto.setAddressNumber(30L);
        addressDto.setAddress("서울");
        addressDto.setUserNumber(22L);
        addressDto.setAddressDetail("110동");
        addressDto.setAddressPost("501-6");
        log.info(addressDto.toString());
    }

    @Test
    void update() {
        addressDto.setAddress("인천");
        addressDto.setUserNumber(22L);
        addressDto.setAddressDetail("111동");
        addressDto.setAddressPost("501-1");

        addressMapper.update(addressDto);
        log.info(addressDto.toString());
    }
}