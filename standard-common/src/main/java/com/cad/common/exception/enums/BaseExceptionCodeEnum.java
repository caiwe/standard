package com.cad.common.exception.enums;

import com.cad.common.exception.basic.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用异常枚举
 *
 * @author caiwei
 * @since 2024/9/10 01:32
 */
@Getter
@AllArgsConstructor
public enum BaseExceptionCodeEnum implements ExceptionCode {

    

    ;

    /**
     * 异常码
     */
    private final Long code;

    /**
     * 异常等级
     */
    private final Integer level;

    /**
     * 异常描述
     */
    private final String message;


    @Override
    public Integer getModuleCode() {
        return -1;
    }
}
