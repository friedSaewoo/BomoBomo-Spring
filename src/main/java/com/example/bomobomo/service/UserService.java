package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void register(UserDto userDto){
        userMapper.insert(userDto);
    }

    @Transactional
    public UserDto find(String userId, String userPassword){

        System.out.println("로그인 서비스");
        return Optional.ofNullable(userMapper.select(userId, userPassword))
                .orElseThrow( () -> {throw new IllegalArgumentException("조회 결과 없음"); });
    }
}



