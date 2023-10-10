package com.example.bomobomo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @GetMapping("/error")
    public String error(HttpServletRequest req) {

        Object status = req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);



        if (status != null) {
            int code = Integer.valueOf(status.toString());
            if (code == HttpStatus.NOT_FOUND.value()) ;
            {
                return "error/404";
            }
        }

        return "error/500";
    }
}
