//package com.example.bomobomo.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Date;
//
//@Slf4j
//public class CookieCount {
//
//    public boolean cookies(HttpServletRequest req, String cookieName, String number){
//
//
//        Cookie[] cookies = req.getCookies();
//
//        if (cookies != null) {
//        for (Cookie cookie : cookies) {
//            if (cookieName.equals(cookie.getName())) {
//                String cookieValue = cookie.getValue();
//
//                String[] values = cookieValue.split("_");
//                String numbers = values[0];
//                log.info(cookie.getName());
//                log.info(cookieValue+"=====================================================");
//                log.info(values[0]+"=====================================================");
//                log.info(values[1]+"=====================================================");
//
//                long storedTimestamp = Long.parseLong(values[1]);
//                long currentTimestamp = new Date().getTime();
//
//                if(numbers.equals(req.getParameter(number)) && (currentTimestamp - storedTimestamp) < (24 * 60 * 60 * 1000)) {
//
//                    return false;
//
//                }
//            }
//        }
//    }
//        return true;
//    }
//}
