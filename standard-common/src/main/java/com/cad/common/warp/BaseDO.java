package com.cad.common.warp;

import lombok.Data;

import java.util.Date;

/**
 * 基础表结构实体
 *
 * @author caiwei
 * @since 2024/9/6 22:43
 */
@Data
public class BaseDO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人id
     */
    private Long createUserId;

    /**
     * 创建人
     */
    private String createUserName;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人id
     */
    private Long updateUserId;

    /**
     * 修改人
     */
    private String updateUserName;

    /**
     * 是否删除，0为未删除
     */
    private Integer isDel;

    /**
     * 版本号
     */
    private Integer version;
}
