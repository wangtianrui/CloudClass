package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/26.
 */

public class Communication {
    private String course_id;
    private String uper_id;
    private String up_time;

    @Override
    public String toString() {
        return "Communication{" +
                "course_id='" + course_id + '\'' +
                ", uper_id='" + uper_id + '\'' +
                ", up_time='" + up_time + '\'' +
                '}';
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
