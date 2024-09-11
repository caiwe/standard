package com.cad.common.exception.enums;

import com.cad.common.enums.ModuleEnum;
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

    /**
     * 异常码不存在
     */
    EXCEPTION_CODE_NOT_EXIST(1001L, ExceptionLevelEnum.WARN.getCode(), "异常码不能为null"),

    /**
     * 异常等级不存在
     */
    EXCEPTION_LEVEL_NOT_EXIST(1002L, ExceptionLevelEnum.WARN.getCode(), "异常等级不能为null"),

    /**
     * 异常定义规范错误
     */
    EXCEPTION_DEFINITION(1002L, ExceptionLevelEnum.WARN.getCode(), "异常定义不符合规范"),

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
        return ModuleEnum.BASE.getCode();
    }

}
