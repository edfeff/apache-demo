package com.wpp.shiro.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.wpp.shiro.util.CommonUtil;
import com.wpp.shiro.util.constants.ErrorEnum;

public class CommonJsonException extends RuntimeException {
    private JSONObject resultJson;

    public JSONObject getResultJson() {
        return resultJson;
    }

    public CommonJsonException(ErrorEnum errorEnum) {
        this.resultJson = CommonUtil.errJson(errorEnum);
    }

    public CommonJsonException(JSONObject resultJson) {
        this.resultJson = resultJson;
    }

    public CommonJsonException() {
        super();
    }

    public CommonJsonException(String message) {
        super(message);
    }

    public CommonJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonJsonException(Throwable cause) {
        super(cause);
    }

    protected CommonJsonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
