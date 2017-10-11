package com.slife.base.vo;

import java.io.Serializable;

/**
 * Created by chen on 2017/8/11.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class JsTreeState implements Serializable {
    private static final long serialVersionUID = 8872587664256359256L;
    private Boolean opened=false;
    private Boolean disabled=false;
    private Boolean selected=false;
    public JsTreeState(){
    }
    public Boolean getDisabled(){
        return disabled;
    }
    public void setDisabled(Boolean disabled){
        this.disabled=disabled;
    }
    public Boolean getSelected(){
        return selected;
    }
    public void setSelected(Boolean selected){
        this.selected=selected;
    }
    public Boolean getOpened(){
        return opened;
    }
    public void setOpened(Boolean opened){
        this.opened=opened;
    }

}
