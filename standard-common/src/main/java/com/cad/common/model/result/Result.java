package com.cad.common.model.result;

import com.cad.common.facade.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 返回实体
 *
 * @author baiyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Result<T> extends BaseVO {

    /**
     * http状态码：请求成功
     */
    public static final Integer CODE_SUCCESS = 200;

    /**
     * http状态码：系统异常
     */
    public static final Integer CODE_SYSTEM_ERROR = 500;

    /**
     * 业务信息：系统错误
     */
    public static final String SYSTEM_ERROR = "系统错误";

    /**
     * 业务信息：请求成功
     */
    public static final String MESSAGE_SUCCESS = "请求成功";

    /**
     * 业务信息：查询成功
     */
    public static final String QUERY_SUCCESS = "查询成功";

    /**
     * 业务信息：新增成功
     */
    public static final String INSERT_SUCCESS = "新增成功";

    /**
     * 业务信息：更新成功
     */
    public static final String UPDATE_SUCCESS = "更新成功";

    /**
     * 业务信息：删除成功
     */
    public static final String DELETE_SUCCESS = "删除成功";

    /**
     * 业务信息：导入成功
     */
    public static final String IMPORT_SUCCESS = "导入成功";

    /**
     * 业务信息：导出成功
     */
    public static final String EXPORT_SUCCESS = "导出成功";

    /**
     * 业务信息：下载成功
     */
    public static final String DOWNLOAD_SUCCESS = "下载成功";

    private T data;

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public Result(Integer code, String errorCode, String message, T data) {
        super(code, errorCode, message);
        this.data = data;
    }

    public boolean success() {
        return CODE_SUCCESS.equals(getCode());
    }

    public boolean systemFail() {
        return CODE_SYSTEM_ERROR.equals(getCode());
    }

    public static Result<Object> ok() {
        return new Result<>(CODE_SUCCESS, "", null);
    }

    public static Result<Object> ok(String message) {
        return new Result<>(CODE_SUCCESS, message, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(CODE_SUCCESS, MESSAGE_SUCCESS, data);
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(CODE_SUCCESS, message, data);
    }

    public static Result<Object> error(String message) {
        return Result.error(CODE_SYSTEM_ERROR, null, message, null);
    }

    public static Result<Object> error(String errorCode, String message) {
        return Result.error(CODE_SYSTEM_ERROR, errorCode, message, null);
    }

    public static Result<Object> error(Integer code, String errorCode, String message, Object data) {
        return new Result<>(code, errorCode, message, data);
    }

}


