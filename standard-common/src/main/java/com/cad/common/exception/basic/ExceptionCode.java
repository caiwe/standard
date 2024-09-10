package com.cad.common.exception.basic;

/**
 * 异常码接口
 *
 * @author caiwei
 * @since 2024/9/9 23:22
 */
public interface ExceptionCode {

    /**
     * 异常code
     */
    Long getCode();

    /**
     * 异常等级
     */
    Integer getLevel();

    /**
     * 异常描述
     */
    String getMessage();

    /**
     * 模块code
     */
    Integer getModuleCode();

}
