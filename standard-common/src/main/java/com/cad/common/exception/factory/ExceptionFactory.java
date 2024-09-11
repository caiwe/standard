package com.cad.common.exception.factory;

import com.cad.common.exception.*;
import com.cad.common.exception.basic.BaseServerException;
import com.cad.common.exception.basic.ExceptionCode;
import com.cad.common.exception.enums.BaseExceptionCodeEnum;
import com.cad.common.exception.enums.ExceptionLevelEnum;

import java.util.Objects;

/**
 * 异常工厂
 *
 * @author caiwei
 * @since 2024/9/10 00:00
 */
public class ExceptionFactory {

    /**
     * 创建异常
     *
     * @param obj 异常对象
     * @return BaseServerException
     * @author caiwei
     * @since 2024/9/11 22:45
     */
    private static BaseServerException createException(Object obj) {
        if (obj instanceof BaseServerException) {
            return (BaseServerException) obj;
        } else if (obj instanceof ExceptionCode exceptionCode) {
            if (Objects.equals(exceptionCode.getLevel(), ExceptionLevelEnum.FATAL.getCode())) {
                return new FatalException(exceptionCode);
            } else if (Objects.equals(exceptionCode.getLevel(), ExceptionLevelEnum.CRITICAL.getCode())) {
                return new CriticalException(exceptionCode);
            } else if (Objects.equals(exceptionCode.getLevel(), ExceptionLevelEnum.ERROR.getCode())) {
                return new ErrorException(exceptionCode);
            } else if (Objects.equals(exceptionCode.getLevel(), ExceptionLevelEnum.WARN.getCode())) {
                return new WarnException(exceptionCode);
            } else if (Objects.equals(exceptionCode.getLevel(), ExceptionLevelEnum.BIZ.getCode())) {
                return new BizException(exceptionCode);
            } else if (Objects.equals(exceptionCode.getLevel(), ExceptionLevelEnum.VALID.getCode())) {
                return new ValidException(exceptionCode);
            }
        }
        return new WarnException(BaseExceptionCodeEnum.EXCEPTION_DEFINITION);
    }
}
