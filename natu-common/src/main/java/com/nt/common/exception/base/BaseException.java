package com.nt.common.exception.base;

import com.nt.common.enums.ResultCode;
import com.nt.common.utils.MessageUtils;
import com.nt.common.utils.StringUtils;

/**
 * 基础异常
 *
 * @author 唐僧
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private final String module;

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误码对应的参数
     */
    private final Object[] args;

    /**
     * 错误消息
     */
    private final String defaultMessage;

    public BaseException(String module, String code, Object[] args, String defaultMessage) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.defaultMessage = defaultMessage;
    }

    public BaseException(String module, ResultCode resultCode, Object[] args) {
        this(module, resultCode.getCode().toString(), args, resultCode.getMessage());
    }

    public BaseException(String module, String code, Object[] args) {
        this(module, code, args, null);
    }

    public BaseException(String module, String defaultMessage) {
        this(module, null, null, defaultMessage);
    }

    public BaseException(String code, Object[] args) {
        this(null, code, args, null);
    }

    public BaseException(String defaultMessage) {
        this(null, null, null, defaultMessage);
    }

    public BaseException(ResultCode resultCode) {
        this(null, resultCode, null);
    }

    public BaseException(ResultCode resultCode, Object[] args) {
        this(null, resultCode, args);
    }

    public BaseException(String module, ResultCode resultCode) {
        this(module, resultCode, null);
    }

    @Override
    public String getMessage() {
        String message = null;
        if (!StringUtils.isEmpty(code)) {
            message = MessageUtils.message(code, args);
        }
        if (message == null) {
            message = defaultMessage;
        }
        return message;
    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
