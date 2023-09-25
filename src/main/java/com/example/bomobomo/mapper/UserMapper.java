package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public UserDto select(@Param("userId")String userId, @Param("userPassword")String userPassword);

    public void insert(UserDto userDto);
}
