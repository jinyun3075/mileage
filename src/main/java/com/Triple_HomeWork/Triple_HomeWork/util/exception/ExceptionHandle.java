package com.Triple_HomeWork.Triple_HomeWork.util.exception;


import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@Log4j2
@RestControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler
    public ErrorResult Handle(Exception e) {
        if(e.getMessage().split(" ")[0].equals("not-null")||e.getMessage().split(" ")[0].equals("Cannot")){
            return new ErrorResult("500", "요구사항을 다 입력해 주세요.");
        }
        if (e.getMessage() == null) {
            return new ErrorResult("402", "null 값이 있네요.");
        }
        return new ErrorResult("500", e.getMessage());
    }
}
