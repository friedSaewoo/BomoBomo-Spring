package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.OrderDto;
import com.example.bomobomo.domain.dto.SubmitOrderDto;
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
class OrderMapperTest {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
            UserMapper userMapper;

    OrderDto orderDto;
    UserDto userDto;
    @BeforeEach
    void setUp() {
       userDto=new UserDto();
       userDto.setUserId("test");
       userDto.setUserName("임형준");
       userDto.setUserEmail("ass@naver.com");
       userDto.setUserPassword("1234");
        userDto.setUserNumber(userDto.getUserNumber());
       userDto.setUserPhone("123123123");

       userMapper.insert(userDto);

       orderDto=new OrderDto();
//       orderDto.setGenderSecond("M");
       orderDto.setGenderFirst("F");
//       orderDto.setKidsContent("test");
       orderDto.setUserNumber(userDto.getUserNumber());

//       orderMapper.insert(orderDto);
    }

    @Test
    void insert() {

        orderMapper.insert(orderDto);
    }

    @Test
    void update(){
       orderDto.setGenderFirst("M");
        orderDto.setKidsContent("단것을 잘 못먹습니다.");

        orderMapper.update(orderDto);
    }
}