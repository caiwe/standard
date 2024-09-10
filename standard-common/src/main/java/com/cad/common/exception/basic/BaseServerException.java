package com.cad.common.exception.basic;

import com.cad.common.exception.enums.ExceptionLevelEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;
import java.util.Optional;

/**
 * 基础服务异常
 *
 * @author caiwei
 * @since 2024/9/10 00:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseServerException extends RuntimeException {

    private static final long serialVersionUID = 430933593095358673L;

    private final static String EMPTY = "";

    /**
     * 返回错误代码的字符串表示。
     */
    private String errorCode;

    /**
     * 返回异常信息。
     */
    private String errorMessage;

    /**
     * 错误等级
     */
    private String level;

    /**
     * 模块code
     */
    private String moduleCode;



    public BaseServerException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.errorCode = Optional.ofNullable(exceptionCode.getCode()).map(String::valueOf).orElse(EMPTY);
        this.errorMessage = exceptionCode.getMessage();
        this.level = Optional.ofNullable(exceptionCode.getLevel()).map(String::valueOf).orElse(EMPTY);
        this.moduleCode = Optional.ofNullable(exceptionCode.getModuleCode()).map(String::valueOf).orElse(EMPTY);
    }

    /**
     * 校验异常code是否属于这个异常类
     *
     * @param exceptionCode 异常code
     * @param exceptionLevelEnum 异常等级枚举
     * @return ExceptionCode
     * @author caiwei
     * @since 2024/9/10 01:19
     */
    protected static ExceptionCode validateExceptionCode(ExceptionCode exceptionCode,
                                                         ExceptionLevelEnum exceptionLevelEnum) {
        if (exceptionCode == null) {
            throw new NullPointerException("ExceptionCode cannot be null");
        }
        if (Objects.isNull(exceptionLevelEnum)) {
            throw new NullPointerException("ExceptionLevelEnum cannot be null");
        }
        if (Objects.equals(exceptionCode.getLevel(), exceptionLevelEnum.getCode())) {
            throw new IllegalArgumentException("ExceptionCode's level cannot be null");
        }
        return exceptionCode;
    }


}

