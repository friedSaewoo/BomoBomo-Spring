package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto=new UserDto();

        userDto.setUserId("test");
        userDto.setUserPhone("a11");
        userDto.setUserEmail("test@");
        userDto.setUserPassword("12344");
        userDto.setUserName("ddd");

        userMapper.insert(userDto);


    }

    @Test
    void insert() {
    }

    @Test
    void update() {
        log.info(userDto.toString());
        userDto.setUserPassword("1111");
        userDto.setUserEmail("eeee@");
        userDto.setUserPhone("000");

        userDto.setUserNumber(userDto.getUserNumber());
        userMapper.update(userDto);
//        log.info(userVo.toString());
        log.info(userDto.toString());
    }
}