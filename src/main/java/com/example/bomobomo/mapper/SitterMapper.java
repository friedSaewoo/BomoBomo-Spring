package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitterMapper {

    //시터 수 출력
    public int sitterTotal();

    //시터 리스트 출력
    public List<EmpDto> sitterList(Criteria criteria);

    //    전체 게시물 수 조회
        public int selectTotal();

        public EmpDto sitterInfo(Long empNumber);

}
