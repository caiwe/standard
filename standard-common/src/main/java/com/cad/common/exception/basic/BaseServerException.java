package com.cad.common.exception.basic;

import com.cad.common.exception.WarnException;
import com.cad.common.exception.enums.BaseExceptionCodeEnum;
import com.cad.common.exception.enums.ExceptionLevelEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
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

    @Serial
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
            throw new WarnException(BaseExceptionCodeEnum.EXCEPTION_CODE_NOT_EXIST);
        }
        if (Objects.isNull(exceptionLevelEnum)) {
            throw new WarnException(BaseExceptionCodeEnum.EXCEPTION_CODE_NOT_EXIST);
        }
        if (Objects.equals(exceptionCode.getLevel(), exceptionLevelEnum.getCode())) {
            throw new WarnException(BaseExceptionCodeEnum.EXCEPTION_DEFINITION);
        }
        return exceptionCode;
    }


}

