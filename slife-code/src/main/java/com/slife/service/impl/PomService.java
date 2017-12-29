package com.slife.service.impl;

import com.slife.service.IPomService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by chen on 2017/12/28.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Service
public class PomService implements IPomService {

    /**
     * 生成 maven 项目
     * @param parentVersion
     * @param artifactId
     * @param pomName
     * @param packageName
     * @param description
     * @return
     */
    @Override
    public byte[] createMavenCode(String parentVersion, String artifactId, String pomName,String packageName, String
            description) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        createPomFile(parentVersion,artifactId,pomName,description);
        createSrcFile(packageName);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }


    private void createPomFile(String parentVersion, String artifactId,
                               String pomName, String description) {
    }



    private void createSrcFile(String packageName) {

    }




}
