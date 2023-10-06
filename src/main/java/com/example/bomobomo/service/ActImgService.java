package com.example.bomobomo.service;

import com.example.bomobomo.domain.dto.ActDto;
import com.example.bomobomo.domain.dto.ActImgDto;
import com.example.bomobomo.mapper.ActImgMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ActImgService {


    private final ActImgMapper actImgMapper;


    //활동등록
    public void registerAct(ActDto actDto){
        actImgMapper.insertAct(actDto);
    }
    
    @Value("${file.dir}")
    private String imgDir;

    //활동 사진 등록
    public void register(ActImgDto actImgDto){
        actImgMapper.insert(actImgDto);

    }


    //활동 사진 저장 처리
    public ActImgDto saveImg(MultipartFile file) throws IOException{

        String originName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();

        String sysName = uuid.toString() + "_" + originName;


        File uploadPath = new File(imgDir, getUploadPath());

        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        File uploadFile = new File(uploadPath, sysName);
        file.transferTo(uploadFile);

        ActImgDto actImgDto = new ActImgDto();
        actImgDto.setActImgName(originName);
        actImgDto.setActImgUuid(uuid.toString());
        actImgDto.setActImgUploadPath(getUploadPath());

        return actImgDto;

    }

    //경로설정 추후 변경가능
    private String getUploadPath(){
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }


    
    //활동 사진 db등록 및 사진 저장처리
    public void registerAndSaveFile(List<MultipartFile> files, Long actNumber) throws IOException{

        for(MultipartFile file : files){
            ActImgDto actImgDto = saveImg(file);
            actImgDto.setActNumber(actNumber);
            register(actImgDto);
        }
    }



    //활동 등록 최종(파일처리 포함)
    public void registerAndFileProc(ActImgDto actImgDto, List<MultipartFile> files){

        //비어있으면 메소드 종료
        if(files.isEmpty()){
            return;
        }

        try {
            registerAndSaveFile(files, actImgDto.getActNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
