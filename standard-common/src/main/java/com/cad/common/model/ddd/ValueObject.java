package com.cad.common.model.ddd;

import com.cad.common.marker.DDDInterface;

/**
 * 值对象标记接口
 *
 * @author baiyan
 */
public interface ValueObject<T> extends DDDInterface {

    /**
     * 值对象通过属性比较
     *
     * @param other
     * @return
     */
    boolean sameValueAs(T other);

}
