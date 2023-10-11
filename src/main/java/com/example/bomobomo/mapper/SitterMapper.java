package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.EmpDto;
import com.example.bomobomo.domain.dto.SitterBoardDto;
import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
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

        public List<SitterBoardDto> sitterBoardList(Long empNumber);

        public ArrayList<Double> sitterReview(Long empNumber);

        //고객이 선택한 주소에 업무 가능한 시터들 리스트
        public List<EmpDto> addrCheck(Criteria criteria);

    //주소 시터 수 출력
    public int sitterAddrTotal();

}
