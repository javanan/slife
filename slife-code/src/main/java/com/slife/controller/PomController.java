package com.slife.controller;

import com.slife.service.IPomService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by chen on 2017/12/28.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Controller
@RequestMapping("/code/pom")
public class PomController extends CodeBaseController{

    @Autowired
    private IPomService pomService;

    /**
     * 创建一个 pom项目
     * @param response
     * @param parentVersion
     * @param artifactId
     * @param pomName
     * @param packageName
     * @param description
     * @throws IOException
     */
    @GetMapping("/create")
    public void create( HttpServletResponse response,
                        @RequestParam("parentVersion") String parentVersion,
                        @RequestParam("artifactId") String artifactId,
                        @RequestParam("pomName") String pomName,
                        @RequestParam("packageName") String packageName,
                        @RequestParam(value = "description",required = false) String description) throws IOException {

        byte[] data = pomService.createMavenCode(parentVersion,artifactId,pomName,packageName,description);
        setResponseHead(response,data,artifactId);
        IOUtils.write(data, response.getOutputStream());
    }

}
