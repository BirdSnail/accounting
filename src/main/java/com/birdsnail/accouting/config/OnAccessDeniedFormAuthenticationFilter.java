package com.birdsnail.accouting.config;

import com.birdsnail.accouting.exception.CustomErrorCode;
import com.birdsnail.accouting.exception.ErrorResponse;
import com.birdsnail.accouting.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author BirdSnail
 * @date 2020/4/10
 */
@Slf4j
public class OnAccessDeniedFormAuthenticationFilter extends FormAuthenticationFilter {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            log.info("On access denied for OnAccessDeniedFormAuthenticationFilter");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

            ErrorResponse accessDeniedResponse = ErrorResponse.builder()
                    .errorCode(CustomErrorCode.NO_AUTHORIZE)
                    .errorType(ServiceException.ErrorType.CLIENT)
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .errorMessage("No access for related url")
                    .build();
            try (PrintWriter writer = httpServletResponse.getWriter()) {
                writer.write(objectMapper.writeValueAsString(accessDeniedResponse));
            } catch (IOException ex) {
                log.error("Write to response failed");
                return false;
            }

            return false;
        }
    }
}
