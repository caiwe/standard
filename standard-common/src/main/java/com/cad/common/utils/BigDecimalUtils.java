package com.cad.common.utils;

import com.cad.common.exception.ValidException;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal工具类
 *
 * @author caiwei
 * @since 2024/9/11 23:46
 */
public class BigDecimalUtils {

    private BigDecimalUtils() {
        // 私有构造函数防止实例化
    }

    /**
     * 将 Object 类型的参数转换为 BigDecimal 类型。
     *
     * @param value 可能为 String 或 Number 类型的值。
     * @return 转换后的 BigDecimal 对象。
     */
    private static BigDecimal convertToBigDecimal(Object value) {
        if (value instanceof String) {
            return new BigDecimal((String) value);
        } else if (value instanceof Number) {
            return new BigDecimal(value.toString());
        } else {
            throw new ValidException("Unsupported type for conversion to BigDecimal.");
        }
    }

    /**
     * 加法运算
     *
     * @param value1 第一个数
     * @param value2 第二个数
     * @return 两个数的和
     */
    public static BigDecimal add(Object value1, Object value2) {
        return convertToBigDecimal(value1).add(convertToBigDecimal(value2));
    }

    /**
     * 减法运算
     *
     * @param value1 第一个数
     * @param value2 第二个数
     * @return 两个数的差
     */
    public static BigDecimal subtract(Object value1, Object value2) {
        return convertToBigDecimal(value1).subtract(convertToBigDecimal(value2));
    }

    /**
     * 乘法运算
     *
     * @param value1 第一个数
     * @param value2 第二个数
     * @return 两个数的积
     */
    public static BigDecimal multiply(Object value1, Object value2) {
        return convertToBigDecimal(value1).multiply(convertToBigDecimal(value2));
    }

    /**
     * 除法运算
     *
     * @param value1 被除数
     * @param value2 除数
     * @param scale  小数点后保留位数
     * @return 两个数的商
     */
    public static BigDecimal divide(Object value1, Object value2, int scale) {
        BigDecimal bd1 = convertToBigDecimal(value1);
        BigDecimal bd2 = convertToBigDecimal(value2);

        if (bd2.signum() == 0) {
            throw new ArithmeticException("Divisor cannot be zero.");
        }
        return bd1.divide(bd2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 比较两个数的大小
     *
     * @param value1 第一个数
     * @param value2 第二个数
     * @return 如果value1大于value2则返回1，小于则返回-1，等于则返回0
     */
    public static int compare(Object value1, Object value2) {
        return convertToBigDecimal(value1).compareTo(convertToBigDecimal(value2));
    }

    /**
     * 格式化数字，保留指定位数的小数
     *
     * @param value 数值
     * @param scale 保留的小数位数
     * @return 格式化后的数值
     */
    public static BigDecimal format(Object value, int scale) {
        return convertToBigDecimal(value).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 判断BigDecimal是否为零
     *
     * @param value 数值
     * @return 如果数值为零则返回true，否则返回false
     */
    public static boolean isZero(Object value) {
        return convertToBigDecimal(value).signum() == 0;
    }

    /**
     * 判断两个BigDecimal是否相等
     *
     * @param value1 第一个数
     * @param value2 第二个数
     * @return 如果两个数相等则返回true，否则返回false
     */
    public static boolean isEqual(Object value1, Object value2) {
        return convertToBigDecimal(value1).compareTo(convertToBigDecimal(value2)) == 0;
    }
}