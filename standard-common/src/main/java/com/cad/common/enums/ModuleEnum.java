package com.cad.common.enums;

import com.cad.common.marker.UniqueKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 模块枚举
 *
 * @author caiwei
 * @since 2024/9/11 23:53
 */
@Getter
@AllArgsConstructor
public enum ModuleEnum implements UniqueKey {

    BASE(10, "通用"),

    UTIL(21, "工具类"),

    COMPONENT(22, "组件"),
    ;

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;

    @Override
    public String uniqueKey() {
        return code.toString();
    }
}
