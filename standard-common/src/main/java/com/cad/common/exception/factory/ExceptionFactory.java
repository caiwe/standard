package com.cad.common.exception.factory;

import com.cad.common.exception.basic.BaseServerException;
import com.cad.common.exception.basic.ExceptionCode;

/**
 * 异常工厂
 *
 * @author caiwei
 * @since 2024/9/10 00:00
 */
public class ExceptionFactory {

    private static BaseServerException createException(Object obj) {
        if (obj instanceof BaseServerException) {
            return (BaseServerException) obj;
        } else if (obj instanceof ExceptionCode) {

        }
        return new BaseServerException();
    }
}
