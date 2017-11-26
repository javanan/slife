package com.slife.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.slife.enums.HttpCodeEnum;
import com.slife.exception.SlifeException;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.UnsupportedEncodingException;

/**
 * @author: felixu.
 * @createTime: 2017/11/26.
 */
@Service
public class ModelService {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    ObjectMapper objectMapper;

    @Transactional(readOnly = false)
    public Model create(String name, String key, String desc, String category) {
        try {
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode properties = objectMapper.createObjectNode();
            properties.put("process_author", "slife");
            editorNode.put("properties", properties);
            ObjectNode stencilset = objectMapper.createObjectNode();
            stencilset.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilset);

            Model model = repositoryService.newModel();
            desc = StringUtils.defaultString(desc);
            model.setKey(StringUtils.defaultString(key));
            model.setName(name);
            model.setCategory(category);
            model.setVersion(Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(model.getKey()).count()+1)));

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, model.getVersion());
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, desc);
            model.setMetaInfo(modelObjectNode.toString());

            repositoryService.saveModel(model);
            repositoryService.addModelEditorSource(model.getId(), editorNode.toString().getBytes("utf-8"));
            return model;
        } catch (UnsupportedEncodingException e) {
            throw new SlifeException(HttpCodeEnum.INTERNAL_SERVER_ERROR);
        }
    }
}
