package com.sundy.exercise.entity;

/**
 * 项目名称：MaterialDesign
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-30 15:02
 * 描述：
 */
public class CommonEntity {
    private int resId;
    private String title;
    private int position;

    public CommonEntity(String title) {
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
