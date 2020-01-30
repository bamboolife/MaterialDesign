package com.sundy.exercise.entity;

/**
 * 项目名称：MaterialDesign
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-30 10:51
 * 描述：
 */
public class Widget {
    private String name;
    private Class clazz;

    public Widget(String name,Class clazz) {
        this.name = name;
        this.clazz=clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
