package com.example.bomobomo.service;


import com.example.bomobomo.domain.dto.OrderDto;
import com.example.bomobomo.domain.dto.SubmitOrderDto;
import com.example.bomobomo.mapper.OrderMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;

    //삽입(둘째와 content값 null값 시 해당 내용으로 입력 - 디폴트 값)
    public void register(OrderDto orderDto){
        if (orderDto == null) {
            throw new IllegalArgumentException("신청내용 누락!!");
        }

        if(orderDto.getGenderSecond()==null){
            orderDto.setGenderSecond("n");
        }
        if(orderDto.getKidsContent()==null){
            orderDto.setKidsContent("n");
        }
        System.out.println(orderDto.toString());
        orderMapper.insert(orderDto);

    }


}
