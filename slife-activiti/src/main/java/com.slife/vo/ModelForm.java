package com.slife.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author: felix.
 * @createTime: 2017/12/11.
 */
public class ModelForm {
    private String category;
    @NotBlank
    private String name;
    @NotBlank
    private String key;
    @NotBlank
    private String desc;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
