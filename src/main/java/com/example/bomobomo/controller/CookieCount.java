package com.example.bomobomo.controller;

import com.example.bomobomo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CookieCount {



    private final ReviewService reviewService;


    public void cookies(HttpServletRequest req, HttpServletResponse resp, String cookieName, Long number,
                           String serviceName) {




        Cookie[] cookies = req.getCookies();
        boolean updateCount = true;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    String cookieValue = cookie.getValue();

                    String[] values = cookieValue.split("/");
                    log.info("%%%%%%%%%% {}", Arrays.toString(values));

                    List<Long> valueList = Arrays.stream(values).mapToLong(Long::parseLong).boxed().collect(Collectors.toList());

                    if (valueList.contains(number)) {
                        updateCount = false;
                        break;
                    }

                    valueList.add(number);
                    log.info("##############3 {}", valueList);

                    String result = String.join("/", valueList.stream().map(ele -> ele + "").collect(Collectors.toList()));

                    log.info("**************************** {}", result);
                    cookie.setValue(result);
                    resp.addCookie(cookie);
                    updateCount = false;
                    if (serviceName.equals("reviewService")) {
                        reviewService.updateCount(number);
                    }
                }

            }
        }

        if (updateCount) {
            Cookie newCookie = new Cookie("reviewDetail_count_cookie", req.getParameter("sitterBoardNumber"));
            newCookie.setMaxAge(24 * 60 * 60);
            resp.addCookie(newCookie);

            if (serviceName.equals("reviewService")) {
                reviewService.updateCount(number);
            }
        }

    }}

