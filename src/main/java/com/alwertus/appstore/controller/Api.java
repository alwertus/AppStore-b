package com.alwertus.appstore.controller;

import com.alwertus.appstore.view.request.LoginRq;
import com.alwertus.appstore.view.response.Response;
import com.alwertus.appstore.view.response.ResponseError;
import com.alwertus.appstore.view.response.ResponseOk;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api")
public class Api {

    @PostMapping("/auth")
    public Response login(@RequestBody LoginRq rq) {
        log.info(String.format("Try to log in with user='%s', password='%s'", rq.getLogin(), rq.getPassword()));
        if (rq.getLogin().equals("user") && rq.getPassword().equals("password"))
            return new ResponseOk();
        return new ResponseError("Wrong login or password");
    }

    @ExceptionHandler(Exception.class)
    public Response errorHandler(Exception e) {
        log.error("Exception: " + e.getMessage());
        return new ResponseError(e.getMessage());
    }

}