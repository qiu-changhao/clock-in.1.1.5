package com.tyc.common.exception;

import com.tyc.common.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 全局异常处理器控制层
 *
 * @author 唐溢聪
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerController {

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result<Void> serviceException(ServiceException e){
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }

    /**
     * 校验异常
     *
     * @param e 异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handlerMethodArgumentException(MethodArgumentNotValidException e) {
        log.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage(), e);
        return Result.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return Result.error();
    }
}
