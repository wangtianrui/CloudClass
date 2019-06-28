package com.scorpiomiku.cloudclass.bean;

import java.io.Serializable;

/**
 * Created by ScorpioMiku on 2019/6/26.
 */

public class Communication implements Serializable {
    private String course_id;
    private String uper_id;
    private String up_time;
    private String content;
    private String title;
    private int user_type;
    private String user_name;
    private String id;

    @Override
    public String toString() {
        return "Communication{" +
                "course_id='" + course_id + '\'' +
                ", uper_id='" + uper_id + '\'' +
                ", up_time='" + up_time + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", user_type=" + user_type +
                ", user_name='" + user_name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getUper_id() {
        return uper_id;
    }

    public void setUper_id(String uper_id) {
        this.uper_id = uper_id;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }
}
