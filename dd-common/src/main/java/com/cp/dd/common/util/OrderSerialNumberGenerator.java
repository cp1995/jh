package com.cp.dd.common.util;

import com.cp.dd.common.constant.OrderSerialNumberEnum;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 单号生成器
 *
 * @author chengp
 * @date 2019/10/11
 */
public class OrderSerialNumberGenerator {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final int DEFAULT_COUNT = 4;

    /**
     * 生成订单号
     *
     * @param prefix 前缀 {@link OrderSerialNumberEnum
     * }
     * @param count  随机位数
     * @return 订单号
     */
    public static String generate(OrderSerialNumberEnum prefix, int count) {
        // 生成订单号
        return prefix.name() + sdf.format(new Date()) + RandomStringUtils.randomNumeric(count);
    }


    /**
     * 生成订单号
     *
     * @param prefix 前缀 {@link OrderSerialNumberEnum}
     * @return 订单号
     */
    public static String generate(OrderSerialNumberEnum prefix) {
        // 生成订单号
        return generate(prefix, DEFAULT_COUNT);
    }
}
