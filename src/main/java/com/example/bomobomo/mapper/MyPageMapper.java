package com.example.bomobomo.mapper;

import com.example.bomobomo.domain.dto.AddressDto;
import com.example.bomobomo.domain.dto.MatchDto;
import com.example.bomobomo.domain.dto.UserDto;
import com.example.bomobomo.domain.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyPageMapper {

    // 시터 결제 내역 조회
    public List<MyPageSitterVo> selectSitterList(@Param("criteria")Criteria criteria,@Param("userNumber")Long userNumber);

    // 시터 결제 내역 전체 페이지 구하기
    public int selectTotal(Long userNumber);

    //이벤트 결제 내역 조회
    public List<MyPageEventVo> selectEventList(@Param("criteria")Criteria criteria,@Param("userNumber")Long userNumber);

    //이벤트 결제 내역 전체 페이지 구하기
    public int selectEventTotal(Long userNumber);

   // 마이페이지 진입시 매칭되는 직원의 상태 출력
    public MatchDto selectMatch(Long userNumber);

    //메칭된 직원의 정보와 이미지 조회
    public MatchEmpInfoVo selectEmpInfoImg(Long empNumber);

    //매칭된 직원의 활동 이름과 활동 이미지 조회
    public List<EmpActItemImgVo> selectEmpActItemImg(Long empNumber);

    //매칭된 직원의 평점을 구하는 쿼리
    public double selectMatchEmpRating(Long empNumber);

    //매칭 단건 조회
//    public MatchDto selectOne(Long userNumber);

    // 매치된 회원의 정보
    public MatchUserInfoVo selectMatchUserInfo(Long userNumber);

    //결제 정보
    public List<MatchBuyInfoVo> selectMatchBuyInfo(Long userNumber);

    //결제후 매칭되었던 데이터 상태 수정
    public void update(Long matchNumber);


    //회원정보수정 디폴트 값 조회(아이디 ,휴대전화)
    public UserDto selectUser(Long userNumber);

    //회원정보수정 디폴트 값 조회(주솟)
    public AddressDto selectUserAddress(Long userNumber);
    //회원정보 삭제
    public void deleteUser(Long userNumber);

}
