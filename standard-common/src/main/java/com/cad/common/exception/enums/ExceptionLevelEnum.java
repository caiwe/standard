package com.cad.common.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常等级枚举类
 *
 * @author caiwei
 * @since 2024/9/9 23:32
 */
@Getter
@AllArgsConstructor
public enum ExceptionLevelEnum {

    /**
     * 系统崩溃或无法恢复;快速响应
     */
    FATAL(10,"致命级"),
    
    /**
     * 关键组件或服务完全失效时;立即处理
     */
    CRITICAL(20,"严重级"),
    
    /**
     * 应用程序的功能部分失败;尽快修复
     */
    ERROR(30,"错误级"),
    
    /**
     * 功能出现故障或者有潜在问题;分析原因
     */
    WARN(40,"警告级"),

    /**
     * 用户操作不当提示;提醒用户
     */
    BIZ(50,"业务级"),

    /**
     * 数据校验,不影响功能使用;提示用户
     */
    VALID(60,"校验级"),
    ;

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;
}
