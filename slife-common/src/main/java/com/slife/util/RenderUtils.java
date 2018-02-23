package com.slife.util;

import com.alibaba.fastjson.JSON;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

;

/**
 * 渲染工具类
 *
 * @author chen
 */
public class RenderUtils {

    /**
     * 渲染json对象
     */
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            //response.setHeader("content-type", "application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            throw new SlifeException(HttpCodeEnum.FAIL);
        }
    }

    /**
     * 跳转到html页面
     *
     * @param request
     * @param response
     */
    public static void renderHtml(HttpServletRequest request, HttpServletResponse response,String html) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(html);
        } catch (IOException e) {
            throw new SlifeException(HttpCodeEnum.FAIL);
        }
    }


    /**
     * 跳转到html页面
     *
     * @param request
     * @param response
     */
    public static void renderForward(HttpServletRequest request, HttpServletResponse response,String addres) {
        try {
            request.getRequestDispatcher("/index.html").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SlifeException(HttpCodeEnum.FAIL);
        }
    }

    public static void renderRedirect(HttpServletRequest request, HttpServletResponse response,String addres) {
        try {
            response.sendRedirect(request.getContextPath() + addres); // 重定向到文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
