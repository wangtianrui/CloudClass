package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/26.
 */

public class Sign {
    private String sign_code;
    private String up_time;
    private String course_id;

    @Override
    public String toString() {
        return "Sign{" +
                "sign_code='" + sign_code + '\'' +
                ", up_time='" + up_time + '\'' +
                ", course_id='" + course_id + '\'' +
                '}';
    }

    public String getSign_code() {
        return sign_code;
    }

    public void setSign_code(String sign_code) {
        this.sign_code = sign_code;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
}
