package com.example.bomobomo.service;


import com.example.bomobomo.domain.dto.EventBoardImgDto;
import com.example.bomobomo.mapper.EventBoardFileMapper;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EventBoardFileService {
    private final EventBoardFileMapper eventBoardFileMapper;

    @Value("${file.dir}")
    private String fileDir;

    // 파일 저장
    public void register(EventBoardImgDto eventBoardImgDto){
        eventBoardFileMapper.insert(eventBoardImgDto);
    }

    //전체조회
    public List<EventBoardImgDto> findList(Long eventBoardNumber){
        if (eventBoardNumber == null) {
            throw new IllegalArgumentException("게시물 번호 누락!");
        }
        return  eventBoardFileMapper.selectList(eventBoardNumber);
    };

    public EventBoardImgDto saveFile(MultipartFile file) throws IOException{
        //사용자가 올린 파일 이름
        String originName=file.getOriginalFilename();

        //파일 이름에 붙여줄 uuid 생성
        UUID uuid=UUID.randomUUID();
        //uuid와 이름을 합쳐준다
        String sysName=uuid.toString() + "_" + originName;
        //상위경로 , 하위경로 결합
        File uploadPath = new File(fileDir,getUploadPath());

        //경로가 없다면 경로를 만들어준다
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        //전체 경로, 파일이름 결합
        File uploadFile =new File(uploadPath,sysName);
        file.transferTo(uploadFile);
        //썸네일 저장 ~~ 확인후 추가
        // 이미지 파일인 경우에만 썸네일을 저장한다
//        if(Files.probeContentType(uploadFile.toPath()).startsWith("image")){
//            FileOutputStream out = new FileOutputStream(new File(uploadPath,"th_"+sysName));
//            Thumbnailator.createThumbnail(file.getInputStream(), out , 300,200);
//            out.close();
//        }

        // 파일이름을 DTO에 저장
        EventBoardImgDto eventBoardImgDto =new EventBoardImgDto();
        eventBoardImgDto.setEventBoardImgName(originName);
        eventBoardImgDto.setEventBoardImgUuid(uuid.toString());
        eventBoardImgDto.setEventBoardImgUploadPath(getUploadPath());

        return eventBoardImgDto;

    }


    //오늘날짜를 구하는 메소드

    private String getUploadPath(){

        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }
    //게시판에서는 파일을 하나만 저장한다
    public void registerAndSaveFile(MultipartFile file, Long eventBoardNumber)throws IOException{

            EventBoardImgDto eventBoardImgDto = saveFile(file);
            eventBoardImgDto.setEventBoardNumber(eventBoardNumber);
            register(eventBoardImgDto);


    }

}
