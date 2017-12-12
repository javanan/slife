package com.slife.vo;

/**
 * @author: felix.
 * @createTime: 2017/12/11.
 */
public class ProcessVO {

    public String category;
    public String processonDefinitionId;
    public String key;
    public String name;
    public Integer revision;
    public Long deploymentTime;
    public String xmlName;
    public String picName;
    public String deploymentId;
    public Boolean suspend;
    public String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSuspend() {
        return suspend;
    }

    public void setSuspend(Boolean suspend) {
        this.suspend = suspend;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProcessonDefinitionId() {
        return processonDefinitionId;
    }

    public void setProcessonDefinitionId(String processonDefinitionId) {
        this.processonDefinitionId = processonDefinitionId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public Long getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(Long deploymentTime) {
        this.deploymentTime = deploymentTime;
    }

    public String getXmlName() {
        return xmlName;
    }

    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }
}
