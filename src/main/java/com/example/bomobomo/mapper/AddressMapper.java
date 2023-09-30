package com.example.bomobomo.mapper;


import com.example.bomobomo.domain.dto.AddressDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {

    // 마이페이지 회원정보 수정
    public void update(AddressDto addressDto);

}
