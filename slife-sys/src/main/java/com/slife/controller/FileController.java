package com.slife.controller;

import com.slife.base.entity.ReturnDTO;
import com.slife.constant.Setting;
import com.slife.util.FileUtils;
import com.slife.util.ReturnDTOUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/file")
public class FileController {
    protected Logger logger = LoggerFactory.getLogger(getClass());



    @ApiOperation(value = "后台删除文件", notes = "后台删除文件")
    @PostMapping(value = "/delete")
    @ResponseBody
    public ReturnDTO delete(@RequestParam("name") String name) throws IOException {
        Boolean b = FileUtils.deleteFile(name);
        if (b) {
            return ReturnDTOUtil.success();
        } else {
            return ReturnDTOUtil.error();
        }
    }

    /**
     * 后台用户头像上传（图片）
     *
     * @param file     上传的文件
     * @param path     文件上传指定根目录下的目录
     * @param response
     * @param request
     * @throws IOException
     */
    @ApiOperation(value = "后台上传文件", notes = "后台上传文件")
    @PostMapping(value = "/upload/{type}")
    @ResponseBody
    public ReturnDTO uploadTransImg(@PathVariable("type") String type, @RequestParam("file") MultipartFile file,
                                    @RequestParam(value = "path", defaultValue = "") String path,
                                    HttpServletResponse response, HttpServletRequest request) throws IOException {

        response.setContentType("text/html; charset=UTF-8");

        List<Map<String, String> > rt=new ArrayList<>();

        rt.add( upload( type, path, file));

        return ReturnDTOUtil.success(rt);
    }

    private Map upload(String type,String path,MultipartFile file){
        String uuid = FileUtils.createFileName();//创建文件名称

        String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();//扩展名

        String fileName = Setting.BASEFLODER;
        if (StringUtils.isNotBlank(path)) {
            fileName = fileName + "/" + path;
        }

        String savePath = fileName + "/" + type + "/" + uuid + "." + fileExt;//附件路径+类型（头像、附件等）+名称+扩展名
        File localFile = FileUtils.saveFileToDisk(file, savePath); //保存到磁盘

        String thumbnailName = "";
        if (FileUtils.getImageFormat(fileExt)) {
            //创建缩略图
            thumbnailName = fileName + "/" + type + "/s/" + uuid + "." + fileExt;//附件路径+类型（头像、附件等）+s(文件夹)+名称+扩展名
            FileUtils.createThumbnail(localFile, thumbnailName);
        }


        Map<String, String> rt = new HashMap<String, String>();

        rt.put("uuid", uuid);
        rt.put("path", Setting.BASEFLODER);
        rt.put("ext", fileExt);
        rt.put("url", "/" + savePath);
        rt.put("s_url", "/" + thumbnailName);

        logger.info("上传的文件地址为 fileName={}", savePath);
        return rt;
    }




}
