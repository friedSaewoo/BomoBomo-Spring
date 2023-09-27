package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.OrderDto;
import com.example.bomobomo.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    OrderMapper orderMapper;

    @InjectMocks
    OrderService orderService;


    @Test
    void register() {
        doNothing().when(orderMapper).insert(any(OrderDto.class));

        orderService.register(new OrderDto());

        verify(orderMapper,times(1)).insert(any(OrderDto.class));

    }
}