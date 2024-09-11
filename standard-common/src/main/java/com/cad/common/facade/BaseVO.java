package com.cad.common.facade;

import lombok.Data;

/**
 * 基础展示对象
 *
 * @author caiwei
 * @since 2024/9/12 00:31
 */
@Data
public abstract class BaseVO {



    /**
     * code
     */
    private Integer code;

    /**
     * 业务code
     */
    @Deprecated
    private String errorCode;

    /**
     * 业务信息
     */
    private String message;

    /**
     * 链路id
     */
    private String traceId;

    public BaseVO() {
    }

    public BaseVO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseVO(Integer code, String errorCode, String message) {
        this.code = code;
        this.errorCode = errorCode;
        this.message = message;
    }

}

