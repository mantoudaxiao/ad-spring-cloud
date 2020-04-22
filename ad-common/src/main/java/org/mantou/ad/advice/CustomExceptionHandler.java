package org.mantou.ad.advice;

import org.mantou.ad.common.RespWrapper;
import org.mantou.ad.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public RespWrapper handler1(HttpServletRequest req, CustomException customEx) {
        System.err.println(123);
        return RespWrapper.error(customEx.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public RespWrapper handler2(HttpServletRequest req, Exception ex) {
        System.err.println(456);
        return RespWrapper.error(ex.getMessage());
    }


}
