package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.*;
import com.example.bomobomo.domain.vo.*;
import com.example.bomobomo.mapper.AdminMapper;
import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

//    application.properties에 각자 경로추가
    @Value("${file.eventImg}")
    private String fileEventImg;
    @Value("${file.eventDetail}")
    private String fileEventDetail;

// 관리자 로그인
    @Transactional
    public AdminDto adminLogin(String adminId, String adminPassword){
        return Optional.ofNullable(adminMapper.login(adminId, adminPassword))
                .orElseThrow( () -> {throw new IllegalArgumentException("로그인 실패!!"); });
    }
    
// 주간 회원가입수
    public List<WeeklyRegisterVo> weeklyRegister(){
        return adminMapper.weeklyRegisterSelect();
    }

// 회원조회
    public List<UserListVo> selectAllUsers(Criteria criteria, SearchVo searchVo){
        return adminMapper.selectAllUsers(criteria,searchVo);
    }
// 회원 수
    public int getTotalUsers(SearchVo searchVo){
        return adminMapper.getTotalUsers(searchVo);
    }
// 회원 상세정보
    public UserDetailVo selectUserDetail(Long userNumber){
        return adminMapper.selectUserDetail(userNumber);
    }

// 직원조회
    public List<EmpDto> selectAllEmp(Criteria criteria, SearchVo searchVo){
        return adminMapper.selectAllEmp(criteria,searchVo);
    }
// 직원 수
    public int getTotalEmp(SearchVo searchVo){
        return adminMapper.getTotalEmp(searchVo);
    }

//    공지사항 조회
    public List<NoticeDto> selectAllNotice(Criteria criteria, SearchVo searchVo){
        return adminMapper.selectAllNotice(criteria,searchVo);
    }
//    공지사항 수
    public int getTotalNotice(SearchVo searchVo){
        return adminMapper.getTotalNotice(searchVo);
    }
//    공지사항 상세정보
    public NoticeDto selectNoticeDetail(Long noticeNumber){
        return adminMapper.selectNoticeDetail(noticeNumber);
    }
//    공지사항 등록
    public void noticeRegist(NoticeDto noticeDto){
        adminMapper.noticeRegist(noticeDto);
    }
//    공지사항 수정
    public void noticeUpdate(NoticeDto noticeDto){
        adminMapper.noticeUpdate(noticeDto);
    }
//    공지사항 삭제
    public void noticeDelete(Long noticeNumber){
        adminMapper.noticeDelete(noticeNumber);
    }

//    매칭리스트 조회
    public List<MatchListVo> selectAllMatchs(Criteria criteria, SearchVo searchVo){
        return adminMapper.selectAllMatchs(criteria,searchVo);
    }
//    매칭 리스트 수
    public int getTotalMatchs(SearchVo searchVo){
        return adminMapper.getTotalMatchs(searchVo);
    }

//    이벤트 리스트
    public List<EventVo> selectAllEvents(Criteria criteria, SearchVo searchVo){
        return adminMapper.selectAllEvents(criteria,searchVo);
    }
//   이벤트 리스트 수
    public int getTotalEvents(SearchVo searchVo){
        return adminMapper.getTotalEvents(searchVo);
    }
//    이벤트 등록
    public void eventRegist(EventDto eventDto){
        adminMapper.eventRegist(eventDto);
    }
//    이벤트 이미지 등록
    public void eventImgRegist(EventImgDto eventImgDto){
        adminMapper.eventImgRegist(eventImgDto);
    }

//    이벤트 이미지 저장처리
public EventImgDto saveEventImg(MultipartFile evenImg) throws IOException {
//        사용자가 올린 파일 이름(확장자를 포함)
    String originName = evenImg.getOriginalFilename();
//        파일이름에 붙여줄 uuid를 생성(파일이름 중복이 나오지 않게 처리)
    UUID uuid = UUID.randomUUID();

//        uuid와 파일이름을 합쳐준다.
    String sysName = uuid.toString() + "_" + originName;

//        상위 경로와 하위경로를 합친다.
    File uploadPath = new File(fileEventImg, getUploadPath());

//        경로가 존재하지 않는다면 (폴더가 없다면)
    if(!uploadPath.exists()){
//            경로를 만들어준다.(폴더를 만든다)
        uploadPath.mkdirs();
    }

//        전체 경로와 파일이름을 연결한다.
    File uploadFile = new File(uploadPath,sysName);

//        매개변수로 받은 파일을 우리가 만든 경로와 이름으로 저장한다.
    evenImg.transferTo(uploadFile);

//        썸네일을 저장한다.
//        이미지 파일인 경우에만 썸네일을 저장해야한다.
    if(Files.probeContentType(uploadFile.toPath()).startsWith("image") ){
        FileOutputStream out = new FileOutputStream(new File(uploadPath, "th_" + sysName));
        Thumbnailator.createThumbnail(evenImg.getInputStream(), out, 300, 200);
        out.close();
    }

    EventImgDto eventImgDto = new EventImgDto();
    eventImgDto.setEventImgName(originName);
    eventImgDto.setEventImgUuid(uuid.toString());
    eventImgDto.setEventImgUploadPath(getUploadPath());

    return eventImgDto;
}
// 이벤트 이미지 저장, 데이터베이스 저장
    public void eventImgRegistAndSave(List<MultipartFile> eventImg, Long eventNumber) throws IOException{
        for(MultipartFile file : eventImg){
            EventImgDto eventImgDto = saveEventImg(file);
            eventImgDto.setEventNumber(eventNumber);
            eventImgRegist(eventImgDto);
        }
    }

//    이벤트 상세정보 등록
    public void eventDetailRegist(EventDetailDto eventDetailDto){
        adminMapper.eventDetailRegist(eventDetailDto);
    }
    //    이벤트 상세정보 저장처리
    public EventDetailDto saveDetailImg(MultipartFile detailImg) throws IOException {
//        사용자가 올린 파일 이름(확장자를 포함)
        String originName = detailImg.getOriginalFilename();
//        파일이름에 붙여줄 uuid를 생성(파일이름 중복이 나오지 않게 처리)
        UUID uuid = UUID.randomUUID();

//        uuid와 파일이름을 합쳐준다.
        String sysName = uuid.toString() + "_" + originName;

//        상위 경로와 하위경로를 합친다.
        File uploadPath = new File(fileEventDetail, getUploadPath());

//        경로가 존재하지 않는다면 (폴더가 없다면)
        if(!uploadPath.exists()){
//            경로를 만들어준다.(폴더를 만든다)
            uploadPath.mkdirs();
        }

//        전체 경로와 파일이름을 연결한다.
        File uploadFile = new File(uploadPath,sysName);

//        매개변수로 받은 파일을 우리가 만든 경로와 이름으로 저장한다.
        detailImg.transferTo(uploadFile);

        EventDetailDto eventDetailDto = new EventDetailDto();
        eventDetailDto.setEventDetailName(originName);
        eventDetailDto.setEventDetailUuid(uuid.toString());
        eventDetailDto.setEventDetailUploadPath(getUploadPath());

        return eventDetailDto;
    }
    // 이벤트 상세정보 저장, 데이터베이스 저장
    public void eventDetailRegistAndSave(List<MultipartFile> detailImg, Long eventNumber) throws IOException{
        for(MultipartFile file : detailImg){
            EventDetailDto eventDetailDto = saveDetailImg(file);
            eventDetailDto.setEventNumber(eventNumber);
            eventDetailRegist(eventDetailDto);
        }
    }
    //경로설정
    private String getUploadPath(){
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }
}
