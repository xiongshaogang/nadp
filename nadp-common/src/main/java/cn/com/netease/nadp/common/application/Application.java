package cn.com.netease.nadp.common.application;

import java.io.Serializable;

/**
 * Created by bianlanzhou on 16/6/3.
 * Description
 */
public class Application implements Serializable{
    private static final long serialVersionUID = 1L; //Serializable ID
    private String name;  //名称
    private String type;  //类型

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}