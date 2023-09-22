package com.tyc.common.exception;

/**
 * 业务异常
 *
 * @author 唐溢聪
 */
public class ServiceException extends RuntimeException{

    public ServiceException(String message) {
        super(message);
    }
}
