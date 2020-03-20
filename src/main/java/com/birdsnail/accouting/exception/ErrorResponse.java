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

    private int statusCode;
    private String errorMessage;
    private String errorCode;
    private ServiceException.ErrorType errorType;

}
