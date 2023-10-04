package com.example.bomobomo.controller;

import com.example.bomobomo.domain.vo.Criteria;
import com.example.bomobomo.domain.vo.PageVo;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import com.example.bomobomo.service.SitterBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/myPages")
@RequiredArgsConstructor
public class MyPageRestController {
    private final SitterBoardService sitterBoardService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/img")
    public byte[] getPfpImg(String fileFullPath) throws IOException {
        return FileCopyUtils.copyToByteArray(new File(fileDir, fileFullPath));
    }

    @GetMapping("/sitterReviewList")
    public Map<String, Object> showSitterReviewList(HttpServletRequest req, Criteria criteria){
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        PageVo pageVo = new PageVo(sitterBoardService.findSitterReviewTotal(userNumber), criteria);
        List<SitterBoardVo> sitterBoardVoList = sitterBoardService.findAll(criteria,userNumber);

        Map<String, Object> map = new HashMap<>();
        map.put("pageVo", pageVo);
        map.put("list", sitterBoardVoList);
        return map;
    }
}
