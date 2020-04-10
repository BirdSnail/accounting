package com.birdsnail.accouting.exception;

import lombok.Builder;
import lombok.Data;

/**
 * @author BirdSnail
 * @date 2020/3/17
 */
@Data
@Builder
public class ErrorResponse {

    /**
     * 状态码
     */
    private int statusCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 自定义的错误码
     */
    private CustomErrorCode errorCode;

    /**
     * error发生在哪一边
     */
    private ServiceException.ErrorType errorType;

}
