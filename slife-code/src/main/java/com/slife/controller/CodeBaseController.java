package com.slife.controller;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by chen on 2017/12/28.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class CodeBaseController {

    /**
     * 设置response 头部信息
     * @param response
     * @param data
     * @param zipName
     */
    public void setResponseHead(HttpServletResponse response, byte[] data ,String zipName){
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename="+zipName);
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
    }
}
