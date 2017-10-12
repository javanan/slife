package com.slife.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by chen on 2017/7/10.
 * <p>
 * Describe: 附件  访问附件的另一种形式，可以在这里加权限
 */
@Controller
@RequestMapping(value = {"/sys/user"})
public class AttachmentController {


    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * demo  http://localhost:8081/sys/user/attach/head?filename=150641181924855150.png&s=s
     * @param baseFloder
     * @param filename
     * @param type
     * @param path
     * @param s
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "系统请求文件",notes = "系统请求文件  demo  http://localhost:8081/sys/user/attach/head?filename=150641181924855150.png&s=s")
    @GetMapping("/{basefloder}/{type}")
    public String download(@PathVariable("basefloder") String baseFloder,
                           @RequestParam("filename") String filename,
                           @PathVariable("type") String type,
                           @RequestParam(value = "path", required = false) String path,
                           @RequestParam(value = "s", required = false) String s,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        response.setContentType("multipart/form-data;charset=utf-8");

        String retName = filename;
        logger.info("retName========================== " + retName);

        try {
            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
                retName = URLEncoder.encode(retName, "UTF-8");
            } else {
                retName = new String(retName.getBytes("UTF-8"), "ISO8859-1");
            }
        } catch (Exception e) {

        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + retName);

        try {
            response.reset(); // 非常重要--在线阅读

            StringBuffer filePath = new StringBuffer();
            filePath.append(baseFloder).append("/").append(type);
            if (StringUtils.isNotBlank(path)) {
                filePath.append("/").append(path);
            }
            if (StringUtils.isNotBlank(s)) {
                filePath.append("/").append(s);
            }
            filePath.append("/").append(filename);

            File file = new File(filePath.toString());

            logger.info("file.getName()===============  " + file.getName());
            InputStream inputStream = new FileInputStream(file);

            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            // 这里主要关闭。
            os.close();

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
        return null;
    }


}
