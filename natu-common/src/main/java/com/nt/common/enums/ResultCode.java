package com.nt.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息码枚举类
 * 优先考虑已存在的消息码，若实在有需要可自行添加，在此处添加后务必在{@code Resp}中添加相应工具方法
 * 注意：在添加前需提前与前端协商
 * @see com.nt.common.core.domain.resp.Resp
 * @see com.nt.common.core.domain.resp.DataResp
 * @see com.nt.common.core.domain.resp.PageResp
 *
 * @author 天蓬
 * @Date 2022-11-24
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

	/* 成功的返回体 */
	OK(200, "请求成功"),
	CREATED(201, "创建成功"),
	UPDATED(202, "添加成功"),
	DELETED(204, "删除成功"),

	/* 失败的返回体 */
	BAD_REQUEST(400, "请求的地址不存在或者包含不支持的参数"),
	UNAUTHORIZED(401, "未授权"),
	FORBIDDEN(403, "被禁止访问"),
	NOT_FOUND(404, "请求的资源不存在"),
	UNPROCESSABLE_ENTITY(422, "当创建一个对象时，发生一个验证错误"),
	INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    END(900001, "仅用于结尾,无任何作用请勿删除!");

	private final Integer code;
	private final String message;

}
