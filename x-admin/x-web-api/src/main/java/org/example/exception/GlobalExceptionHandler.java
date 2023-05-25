package org.example.exception;

import org.example.exceptionhandler.KeyRepeatException;
import org.example.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(KeyRepeatException.class)
    @ResponseBody
    public Result error(KeyRepeatException e) {
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }

}
