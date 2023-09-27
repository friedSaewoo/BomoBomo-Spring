package com.example.bomobomo.mapper;


import com.example.bomobomo.domain.dto.OrderDto;
import com.example.bomobomo.domain.dto.SubmitOrderDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    //삽입
    public void insert(OrderDto orderDto);

    //수정
    public void update(OrderDto orderDto);

}
