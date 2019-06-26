package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/26.
 */

public class HomeWork {
    private String course_id;
    private String title;
    private String content;
    private String up_time;
    private String uper_id;

    @Override
    public String toString() {
        return "HomeWork{" +
                "course_id='" + course_id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", up_time='" + up_time + '\'' +
                ", uper_id='" + uper_id + '\'' +
                '}';
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

    public String getUper_id() {
        return uper_id;
    }

    public void setUper_id(String uper_id) {
        this.uper_id = uper_id;
    }
}
