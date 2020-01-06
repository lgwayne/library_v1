package com.library.bean;

/**
 * 图书类别实体类
 */
public class ClassInfo {
    private int class_id;
    private String class_name;

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "class_id=" + class_id +
                ", class_name='" + class_name + '\'' +
                '}';
    }
}
