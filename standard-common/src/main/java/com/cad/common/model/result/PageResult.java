package com.cad.common.model.result;

import com.cad.common.facade.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 分页数据返回实体
 *
 * @author baiyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageResult<T> extends BaseVO {

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

    private Long total;

    private List<T> data;

    public PageResult() {
    }

    public static <T> PageResult<T> ok(Page<T> result) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCode(CODE_SUCCESS);
        pageResult.setMessage(QUERY_SUCCESS);
        pageResult.setTotal(result.getTotal());
        pageResult.setData(result.getRecords());
        return pageResult;
    }

    public static <T> PageResult<T> ok(Long total,List<T> data) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCode(CODE_SUCCESS);
        pageResult.setMessage(QUERY_SUCCESS);
        pageResult.setTotal(total);
        pageResult.setData(data);
        return pageResult;
    }

}

