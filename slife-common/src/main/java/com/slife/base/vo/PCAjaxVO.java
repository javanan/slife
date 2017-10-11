package com.slife.base.vo;

/**
 * Created by chen on 2017/8/30.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: PC 的 ajax请求 封装
 */
public class PCAjaxVO {
    private Boolean success;
    private String code;
    private String message;

    public PCAjaxVO(Boolean success) {
        this.success = success;
    }

    public PCAjaxVO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
