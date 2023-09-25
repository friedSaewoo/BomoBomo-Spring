package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.AdminDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    public AdminDto login(@Param("adminId")String adminId, @Param("adminPassword")String adminPassword);
}
