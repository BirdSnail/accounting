package com.birdsnail.accouting.exception;

import org.springframework.http.HttpStatus;

/**
 * 参数name为空的异常
 *
 * @author BirdSnail
 * @date 2020/3/17
 */
public class ResourceNotFoundException extends ServiceException{

    public ResourceNotFoundException(String message) {
        super(message);
        this.setStatusCode(HttpStatus.BAD_REQUEST.value());
        this.setErrorType(ErrorType.CLIENT);
    }
}
