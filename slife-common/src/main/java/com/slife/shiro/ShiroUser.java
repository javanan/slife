package com.slife.shiro;



import java.io.Serializable;
import java.util.Objects;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 *
 */
public class ShiroUser implements Serializable{
    private static final long serialVersionUID=-1373760761780840081L;
    public Long id;
    public String username;
    public String name;
    public String photo;
    public ShiroUser(Long id, String username, String name,String photo){
        this.id=id;
        this.username=username;
        this.name=name;
        this.photo=photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString(){
        return username;
    }
    /**
     * 重载hashCode,只计算loginName;
     */
    @Override
    public int hashCode(){
        return Objects.hashCode(username);
    }
    /**
     * 重载equals,只计算loginName;
     */
    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }
        if(obj==null){
            return false;
        }
        if(getClass()!=obj.getClass()){
            return false;
        }
        ShiroUser other=(ShiroUser)obj;
        if(username==null){
            if(other.username!=null){
                return false;
            }
        }else if(!username.equals(other.username)){
            return false;
        }
        return true;
    }
}