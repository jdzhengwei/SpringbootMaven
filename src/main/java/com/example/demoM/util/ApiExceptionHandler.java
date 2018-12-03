package com.example.demoM.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demoM.error.ErrorInfo;


/**
 * 异常处理
 *
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @Autowired
    private MessageSource messageSource;

    /**
     * 验证异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseBody
    public ApiResult<String> handleException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> volations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : volations) {
            return ApiResult.errorResult(ErrorInfo.PARAM_INVALID, violation.getMessage());
        }
        LOGGER.warn(ex.getMessage());
        return ApiResult.errorResult(ErrorInfo.PARAM_INVALID);
    }


    /**
     * 服务端错误,未捕获异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({RuntimeException.class, Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<Void> handleUnexpectedServerError(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ApiResult.errorResult(ErrorInfo.SYS_SERVER_ERROR);
    }

}