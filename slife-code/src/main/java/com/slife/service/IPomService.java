package com.slife.service;

import org.springframework.stereotype.Service;

/**
 * Created by chen on 2017/12/28.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
@Service
public interface IPomService {


    /**
     * 生成 maven 项目
     * @param parentVersion
     * @param artifactId
     * @param pomName
     * @param packageName
     * @param description
     * @return
     */
     byte[] createMavenCode(String parentVersion, String artifactId,  String pomName,String packageName, String description) ;
}
