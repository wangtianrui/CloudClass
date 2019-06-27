package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/24.
 */

public class MySource {
    private String source_path;
    private String course_id;
    private int type;
    private String uper_id;
    private int download_count;
    private String source_name;
    private String uper_name;

    @Override
    public String toString() {
        return "MySource{" +
                "source_path='" + source_path + '\'' +
                ", course_id='" + course_id + '\'' +
                ", type=" + type +
                ", uper_id='" + uper_id + '\'' +
                ", download_count=" + download_count +
                ", source_name='" + source_name + '\'' +
                ", uper_name='" + uper_name + '\'' +
                '}';
    }

    public String getUper_name() {
        return uper_name;
    }

    public void setUper_name(String uper_name) {
        this.uper_name = uper_name;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getSource_path() {
        return source_path;
    }

    public void setSource_path(String source_path) {
        this.source_path = source_path;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUper_id() {
        return uper_id;
    }

    public void setUper_id(String uper_id) {
        this.uper_id = uper_id;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }
}
