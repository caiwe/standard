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

    /**
     * 修改错误码描述
     *
     * @param message 错误码描述
     * @return ExceptionCode
     * @author caiwei
     * @since 2024/9/11 22:34
     */
    default ExceptionCode modifyMessage(String message) {
        Long code = this.getCode();
        Integer level = this.getLevel();
        Integer moduleCode = this.getModuleCode();
        return new ExceptionCode() {

            @Override
            public Long getCode() {
                return code;
            }

            @Override
            public Integer getLevel() {
                return level;
            }

            @Override
            public String getMessage() {
                return message;
            }

            @Override
            public Integer getModuleCode() {
                return moduleCode;
            }
        };

    }

}
