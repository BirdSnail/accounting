package com.birdsnail.accouting.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * accounting service basic exception
 *
 * @author yanghuadong
 * @date 2020-03-17
 */
@Data
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ServiceException extends RuntimeException {

    private int statusCode;
    private String errorCode;
    private ErrorType errorType;


    public ServiceException(String message) {
        super(message);
    }

    public enum ErrorType {
        /**
         * 服务端出错
         */
        SERVICE,
        /**
         * 客户端出错
         */
        CLIENT,
        /**
         * 不知道哪一方出错
         */
        UNKNOWN
    }

}
