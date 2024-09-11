package com.cad.common.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 对比工具类
 *
 * @author caiwei
 * @since 2020-09-09 18:00:00
 */
@Slf4j
public class CompareUtils {

    /**
     * 需要忽略的字段集
     */
    private final static List<String> IGNORE_FIELDS = Lists.newArrayList("serialVersionUID");

    public static boolean compareObjects(Object obj1, Object obj2) throws IllegalAccessException {
        if (Objects.equals(obj1, obj2)) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        // 采用obj1为标准字段集对比
        Class<?> clazz1 = obj1.getClass();
        Field[] fields = clazz1.getDeclaredFields();
        for (Field field : fields) {
            // 不需要对比的字段直接跳过
            if (IGNORE_FIELDS.contains(field.getName())) {
                continue;
            }
            field.setAccessible(true); // 设置为可访问私有字段
            Object val1 = field.get(obj1);
            Object val2 = field.get(obj2);

            if (val1 instanceof Collection && val2 instanceof Collection) {
                // 集合或者数组
                if (!compareObjects(val1, val2)) {
                    return false;
                }
            } else if (val1 instanceof Map && val2 instanceof Map) {
                // map
                return false;
            } else if (isSimpleObject(val1) && isSimpleObject(val2)) {
                // 简单类型
                if (!Objects.equals(val1, val2)) {
                    return false;
                }
            } else if (val1 instanceof BigDecimal && val2 instanceof BigDecimal) {

            }
        }
        return true;
    }

    private static boolean isSimpleObject(Object obj) {
        return obj instanceof Short || obj instanceof Character || obj instanceof Byte || obj instanceof String ||
                obj instanceof Integer ||
                obj instanceof Long || obj instanceof Double || obj instanceof Float || obj instanceof Boolean;
    }


}
