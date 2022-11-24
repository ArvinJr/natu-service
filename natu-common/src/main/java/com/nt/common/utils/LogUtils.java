package com.nt.common.utils;

/**
 * 处理并记录日志文件
 *
 * @author 唐僧
 */
public class LogUtils {
    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
