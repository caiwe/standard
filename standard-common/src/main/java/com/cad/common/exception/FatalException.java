package com.cad.common.exception;

import com.cad.common.exception.basic.BaseServerException;
import com.cad.common.exception.basic.ExceptionCode;
import com.cad.common.exception.enums.ExceptionLevelEnum;

/**
 * 致命级异常
 *
 * @author caiwei
 * @since 2024/9/10 00:36
 */
public class FatalException extends BaseServerException {

    public FatalException(String message) {
        super(new ExceptionCode() {

            @Override
            public Long getCode() {
                return -1L;
            }

            @Override
            public Integer getLevel() {
                return ExceptionLevelEnum.FATAL.getCode();
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

    public FatalException(ExceptionCode exceptionCode) {
        super(validateExceptionCode(exceptionCode,ExceptionLevelEnum.FATAL));
    }

    public FatalException(ExceptionCode exceptionCode, String message) {
        super(validateExceptionCode(exceptionCode.modifyMessage(message), ExceptionLevelEnum.FATAL));
    }

}
