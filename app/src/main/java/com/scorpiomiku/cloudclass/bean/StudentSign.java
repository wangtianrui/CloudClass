package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/26.
 */

public class StudentSign {
    private String sign_id;
    private String up_time;
    private String student_id;

    @Override
    public String toString() {
        return "StudentSign{" +
                "sign_id='" + sign_id + '\'' +
                ", up_time='" + up_time + '\'' +
                ", student_id='" + student_id + '\'' +
                '}';
    }

    public String getSign_id() {
        return sign_id;
    }

    public void setSign_id(String sign_id) {
        this.sign_id = sign_id;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
}
