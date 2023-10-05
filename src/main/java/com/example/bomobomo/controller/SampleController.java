package com.example.bomobomo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sample/*")
public class SampleController {


        @GetMapping("/checkbox")
        public String move(){
            return "/board/sample";
        }

        @PostMapping("/choices")
        public String sample(HttpServletRequest req){
            String[] array = req.getParameterValues("sample");

            for(int i =0;i<array.length;i++){
                log.info(array[i]);

            }

            return "common/index";
        }


}
