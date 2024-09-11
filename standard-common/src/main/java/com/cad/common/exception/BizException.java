package com.cad.common.exception;

import com.cad.common.exception.basic.BaseServerException;
import com.cad.common.exception.basic.ExceptionCode;
import com.cad.common.exception.enums.ExceptionLevelEnum;

/**
 * 业务级异常
 *
 * @author caiwei
 * @since 2024/9/10 00:36
 */
public class BizException extends BaseServerException {

    public BizException(String message) {
        super(new ExceptionCode() {

            @Override
            public Long getCode() {
                return -1L;
            }

            @Override
            public Integer getLevel() {
                return ExceptionLevelEnum.BIZ.getCode();
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

    public BizException(ExceptionCode exceptionCode) {
        super(validateExceptionCode(exceptionCode,ExceptionLevelEnum.BIZ));
    }

    public BizException(ExceptionCode exceptionCode, String message) {
        super(validateExceptionCode(exceptionCode.modifyMessage(message), ExceptionLevelEnum.BIZ));
    }

}
