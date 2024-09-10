package com.cad.common.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 对比工具类
 *
 * @author caiwei
 * @since 2020-09-09 18:00:00
 */
@Slf4j
public class CompareUtils {

    private final static List<String> IGNORE_FIELDS = Lists.newArrayList("serialVersionUID");

    public boolean compareObjects(Object obj1, Object obj2) throws IllegalAccessException {
        if (obj1 == obj2) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        Class<?> clazz1 = obj1.getClass();

        Field[] fields = clazz1.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // 设置为可访问私有字段
            Object val1 = field.get(obj1);
            Object val2 = field.get(obj2);

            if (val1 instanceof Object && val2 instanceof Object) {
                // 如果是对象类型，则递归比较
                if (!compareObjects(val1, val2)) {
                    return false;
                }
            } else if (!val1.equals(val2)) {
                // 如果不是对象类型，则直接比较
                return false;
            }
        }
        return true;
    }



}
