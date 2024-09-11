package com.cad.common.exception;

import com.cad.common.exception.basic.BaseServerException;
import com.cad.common.exception.basic.ExceptionCode;
import com.cad.common.exception.enums.ExceptionLevelEnum;

/**
 * 严重级异常
 *
 * @author caiwei
 * @since 2024/9/10 00:36
 */
public class CriticalException extends BaseServerException {

    public CriticalException(String message) {
        super(new ExceptionCode() {

            @Override
            public Long getCode() {
                return -1L;
            }

            @Override
            public Integer getLevel() {
                return ExceptionLevelEnum.CRITICAL.getCode();
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public Integer getModuleCode() {
                return -1;
            }

        });
    }

    public CriticalException(ExceptionCode exceptionCode) {
        super(validateExceptionCode(exceptionCode,ExceptionLevelEnum.CRITICAL));
    }

    public CriticalException(ExceptionCode exceptionCode, String message) {
        super(validateExceptionCode(exceptionCode.modifyMessage(message), ExceptionLevelEnum.CRITICAL));
    }

}
