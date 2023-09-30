package com.example.bomobomo.mapper;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
class EventMapperTest {
    @Autowired
    EventMapper eventMapper;


    @Test
    void selectAllTest() {
        eventMapper.selectAll().forEach(mem -> log.info(mem.toString()));
    }
}