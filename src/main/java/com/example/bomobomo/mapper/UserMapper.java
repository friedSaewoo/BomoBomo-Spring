package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.AddressDto;
import com.example.bomobomo.domain.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

@Mapper
public interface UserMapper {
    public UserDto select(@Param("userId")String userId, @Param("userPassword")String userPassword);


    public void insertUser(UserDto userDto);
    public void insertAddr(AddressDto addressDto);

    public int idCheck(String userId);
    public int nameCheck(String userName);
    public void insert(UserDto userDto);

    // 마이 페이지에서 회원정보 수정
    public void update(UserDto userDto);
}
