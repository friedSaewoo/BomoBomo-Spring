package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.AddressDto;
import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void register(UserDto userDto, AddressDto addressDto){
        userMapper.insertUser(userDto);
        userMapper.insertAddr(addressDto);

        System.out.println("서비스 유저 : " + userDto.getUserName());
        System.out.println("서비스 주소 : " + addressDto.getAddress());
    }

    @Transactional
    public UserDto find(String userId, String userPassword){

        System.out.println("로그인 서비스");
        return Optional.ofNullable(userMapper.select(userId, userPassword))
                .orElseThrow( () -> {throw new IllegalArgumentException("조회 결과 없음"); });
    }

    public int idCheck(String userId) {
        return userMapper.idCheck(userId);
    }

    public int nameCheck(String userName) {
        return userMapper.nameCheck(userName);
    }

}



