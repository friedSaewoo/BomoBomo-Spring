package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.AddressDto;
import com.example.bomobomo.domain.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

@Mapper
public interface UserMapper {
    public UserDto select(@Param("userId")String userId, @Param("userPassword")String userPassword);

//    public void insert(HashMap map);

    public void insertUser(UserDto userDto);
    public void insertAddr(AddressDto addressDto);
    public int idCheck(String userId);
    public int nameCheck(String userName);


}
