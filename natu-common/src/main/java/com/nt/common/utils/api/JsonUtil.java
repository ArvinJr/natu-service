package com.nt.common.utils.api;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: 唐僧
 * @Desc: json 工具类
 */
public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    private JsonUtil() {
    }

    /**
     * 校验json
     *
     * @param text 待校验文本
     * @return
     */
    public static Boolean verifyJson(String text) {
        try {
            JSON.parseObject(text);
            return true;
        } catch (Exception e) {
            log.error("不正确的json字符串");
            return false;
        }
    }

    /**
     * 转json
     *
     * @param text 待转换文本
     * @return JSONObject
     */
    public static JSONObject parseObject(String text) {
        try {
            return JSON.parseObject(text);
        } catch (Exception e) {
            log.error("不正确的json字符串==>{}", text);
            return null;
        }
    }

    /**
     * 初始化一个 JSONObject
     *
     * @return JSONObject
     */
    public static JSONObject newJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CODE_TAG, 200);
        jsonObject.put(MSG_TAG, "请求成功");
        return jsonObject;
    }

    /**
     * 请求成功
     *
     * @param msg 提示信息
     * @return JSONObject
     */
    public static JSONObject success(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CODE_TAG, 200);
        jsonObject.put(MSG_TAG, msg);
        return jsonObject;
    }

    /**
     * 请求成功
     *
     * @param data 返回数据
     * @return JSONObject
     */
    public static JSONObject success(Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CODE_TAG, 200);
        jsonObject.put(MSG_TAG, "请求成功");
        jsonObject.put(DATA_TAG, data);
        return jsonObject;
    }

    /**
     * 请求失败
     *
     * @param msg 提示信息
     * @return JSONObject
     */
    public static JSONObject fail(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CODE_TAG, 500);
        jsonObject.put(MSG_TAG, msg);
        return jsonObject;
    }

    /**
     * 自定义Jackson序列器
     * @return ObjectMapper
     */
    public static ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }
}
