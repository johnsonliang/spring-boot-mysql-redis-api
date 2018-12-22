package com.test.springboot.common.base.dto;

import java.io.Serializable;
import java.util.UUID;

import com.alibaba.fastjson.JSON;

/**
 * 数据传输对象基类
 */
public abstract class BaseDTO implements Serializable {

    private static final long serialVersionUID = -4873814384088149839L;

    private String traceID;

    private long timestamp;

    private String language = "zh";

    protected BaseDTO() {
        this.traceID = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    /**
     * 获取国际化消息标识，
     *
     * <pre>
     *    zh - 中文
     *    en - 英文
     * </pre>
     *
     * @return 国际化消息标识
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * 设置国际化消息标识，
     *
     * @param language 国际化消息标识
     *
     *                 <pre>
     *                    zh - 中文
     *                    en - 英文
     *                                 </pre>
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTraceID() {
        return traceID;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
