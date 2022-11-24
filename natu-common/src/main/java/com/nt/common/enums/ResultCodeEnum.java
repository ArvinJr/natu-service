package com.nt.common.enums;

/**
 * @Author: 天蓬
 * @Date: 2022-11-24 22:08:32
 * @Desc: 消息码枚举类
 *        自己添加需要的消息码，消息码分类不需要太详细，前端能分清哪个业务的大致哪一部分发生了错误就可以
 *        eg：登录场景，可以返回：密码或用户名不存在，用户名/密码/验证码必填，验证码已过期，验证码错误等等
 */
public enum ResultCodeEnum {
    /**
     * 成功消息码
     */
    SUCCESS(999999, "成功"),

    /**
     * 失败消息码
     */
    ERROR(0, "失败"),

    /**
     * 1 前台业务消息码
     */
    FRONT_UNKNOWN_ERROR(100001, "前台未知错误"),

    /**
     * 2 后台业务消息码(按照原型图顺序进行排序)
     */
    //2.0 首页
    FRONT_PAGE_UNKNOWN_ERROR(200001, "首页未知错误"),


    //2.1 系统管理
    //2.1.0 用户管理
    USER_MANAGE_UNKNOWN_ERROR(210001, "用户管理未知错误"),

    //2.1.1 角色管理
    ROLE_MANAGE_UNKNOWN_ERROR(211001, "角色管理未知错误"),

    //2.1.2 菜单管理
    MENU_MANAGE_UNKNOWN_ERROR(212001, "菜单管理未知错误"),

    //2.1.3 字典管理
    DICT_MANAGE_UNKNOWN_ERROR(213001, "字典管理未知错误"),

    //2.1.4 参数设置
    PARAM_SET_UNKNOWN_ERROR(214001, "参数设置未知错误"),

    //2.1.5 日志管理
    LOG_MANAGE_UNKNOWN_ERROR(215001, "日志管理未知错误"),

    //2.1.6 反馈管理
    FEEDBACK_MANAGE_UNKNOWN_ERROR(216001, "用户管理未知错误"),

    //2.2 系统监控
    //2.2.0 在线用户
    //2.2.1 数据监控
    //2.2.2 服务监控
    //2.2.3 缓存监控

    //2.3 系统工具

    //2.4 项目管理

    //2.5 项目故事

    //2.6 文化旅游

    //2.7 旅游服务
    //2.7.0 商家管理
    //2.7.1 酒店管理
    //2.7.2 餐饮管理
    //2.7.3 商品管理

    //2.8 景点管理

    //2.9 内容管理
    //2.9.0 banner轮播
    //2.9.1 新闻咨询
    //2.9.2 活动发布
    //2.9.3 创A专题
    //2.9.4 公告管理

    END(900001, "仅用于结尾,无任何作用请勿删除!");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
