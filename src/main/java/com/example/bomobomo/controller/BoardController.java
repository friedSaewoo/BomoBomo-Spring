package com.example.bomobomo.controller;

import com.example.bomobomo.domain.dto.SitterBoardDto;
import com.example.bomobomo.domain.vo.SitterBoardVo;
import com.example.bomobomo.service.NoticeService;
import com.example.bomobomo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.Date;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {


    private final NoticeService noticeService;
    private final ReviewService reviewService;

    @GetMapping("/faq")
    public String showFaqPage(){
        return "board/boardFaq";
    }


    @GetMapping("/notice")
    public String showNoticePage(){
        return "board/boardNotice";
    }


    @GetMapping("/detail")
    public String showNoticeDetailPage(@RequestParam(name = "noticeNumber") Long noticeNumber, Model model, HttpServletRequest req, HttpServletResponse resp){
        
        
        //모델 객체를 통해 detail페이지로 해당 공지사항 세부내역 전달
        model.addAttribute("noticeDetail",  noticeService.selectOne(noticeNumber));



        //쿠키를 활용
        Cookie[] cookies = req.getCookies();
        boolean updateCount = true;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("notice_count_cookie".equals(cookie.getName())) {
                    // 해당 쿠키가 이미 존재하는 경우

                    String cookieValue = cookie.getValue();

                    //쿠키밸루를 가져와서 _마다 쪼개어 배열로 저장한다.
                    String[] values = cookieValue.split("_");
                    String boardNoticeNumber = values[0];

                    log.info(cookieValue+"=====================================================");
                    log.info(values[0]+"=====================================================");
                    log.info(values[1]+"=====================================================");

                    long storedTimestamp = Long.parseLong(values[1]); //쿠키를 생성했을 때 발급해준 발급시간을 저장
                    long currentTimestamp = new Date().getTime(); // 현재 시간을 저장

                    // 현재 날짜와 저장된 날짜를 비교하여 하루에 한 번만 업데이트
                    if (boardNoticeNumber.equals(req.getParameter("noticeNumber")) && (currentTimestamp - storedTimestamp) < (24 * 60 * 60 * 1000)) {

                        //조회수 증가x
                        updateCount = false;
                        break;
                    }
                }
            }
        }

        //쿠키가 없다면(또는 이미 발급 받았던 notice_count_cookie의 지속시간이 지났을 경우)
        // notice_count_cookie를 생성해주고 해당 게시물 들어갔을 때 조회수 증가
        if (updateCount) {
            Cookie newCookie = new Cookie("notice_count_cookie", req.getParameter("noticeNumber") + "_" + new Date().getTime());
            newCookie.setMaxAge(24 * 60 * 60); // 쿠키 생성 시 24시간 유지
            resp.addCookie(newCookie);
            
            //조회수 증가
            noticeService.updateCount(noticeNumber);
        }


        return "board/detail";
    }


    @GetMapping("/serviceReview")
    public String showServiceReviewPage(){
        return "board/boardServiceReview";
    }





    @GetMapping("/reviewDetail")
    public String showServiceReviewDetailPage(@RequestParam("sitterBoardNumber") Long sitterBoardNumber,
           Model model, HttpServletRequest req, HttpServletResponse resp){

        SitterBoardVo sitterBoardVo = reviewService.selectOne(sitterBoardNumber);
        double getAvg = reviewService.getAvgRating(sitterBoardVo.getEmpNumber());



        model.addAttribute("serviceReviewDetail", sitterBoardVo);
        model.addAttribute("getAvg", (Math.round(getAvg*100) / 100.0));

        log.info(String.valueOf(getAvg));


        Cookie[] cookies = req.getCookies();
        boolean updateCount = true;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("reviewDetail_count_cookie".equals(cookie.getName())) {
                    String cookieValue = cookie.getValue();

                    String[] values = cookieValue.split("_");
                    String sitterBoardNumbers = values[0];

                    log.info(cookieValue+"=====================================================");
                    log.info(values[0]+"=====================================================");
                    log.info(values[1]+"=====================================================");

                    long storedTimestamp = Long.parseLong(values[1]);
                    long currentTimestamp = new Date().getTime();

                    if (sitterBoardNumbers.equals(req.getParameter("sitterBoardNumber")) && (currentTimestamp - storedTimestamp) < (24 * 60 * 60 * 1000)) {

                        updateCount = false;
                        break;
                    }
                }
            }
        }

        if (updateCount) {
            Cookie newCookie = new Cookie("reviewDetail_count_cookie", req.getParameter("sitterBoardNumber") + "_" + new Date().getTime());
            newCookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(newCookie);

            reviewService.updateCount(sitterBoardNumber);
        }


        log.info(sitterBoardVo.toString());
        return "board/serviceReviewDetail";
    }

    
    //돌봄후기 수정
    


    //돌봄후기 삭제
    @GetMapping("/removeSReview")
    public RedirectView removeServiceReview(Long sitterBoardNumber){
        reviewService.delete(sitterBoardNumber);

        return new RedirectView("serviceReview");
    }




}
