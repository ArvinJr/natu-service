package com.nt.common.core.domain;

import com.nt.common.constant.HttpStatus;
import com.nt.common.enums.ResultCodeEnum;
import com.nt.common.utils.StringUtils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 操作消息提醒
 *
 * @author 唐僧
 */
public class AjaxResult implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public int code;

    /**
     * 返回内容
     */
    public String msg;

    /**
     * 数据对象
     */
    public Object data;

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public AjaxResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public <T> AjaxResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public AjaxResult(ResultCodeEnum resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    }

    public <T> AjaxResult(ResultCodeEnum resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
        this.data = data;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        return new AjaxResult(ResultCodeEnum.SUCCESS);
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static <T> AjaxResult success(T data) {
        return new AjaxResult(ResultCodeEnum.SUCCESS, data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static <T> AjaxResult success(String msg, T data) {
        return new AjaxResult(ResultCodeEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 警告消息
     */
    public static AjaxResult error() {
        return new AjaxResult(ResultCodeEnum.ERROR);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param data 返回数据
     * @param <T> 数据类型
     * @return 警告消息
     */
    public static <T> AjaxResult error(T data) {
        return new AjaxResult(ResultCodeEnum.ERROR, data);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static<T> AjaxResult error(String msg, T data) {
        return new AjaxResult(ResultCodeEnum.ERROR.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     * @param <T>  数据类型
     * @return 警告消息
     */
    public static <T> AjaxResult error(int code, String msg, T data) {
        return new AjaxResult(code, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param resultCode 消息码
     * @return 警告消息
     */
    public static AjaxResult error(ResultCodeEnum resultCode) {
        return AjaxResult.error(resultCode, null);
    }

    /**
     * 返回错误信息
     *
     * @param resultCode 消息码
     * @param data       数据对象
     * @param <T>        数据类型
     * @return 警告消息
     */
    public static <T> AjaxResult error(ResultCodeEnum resultCode, T data) {
        return new AjaxResult(resultCode, data);
    }
}
