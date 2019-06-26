package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/24.
 */

public class Student2Class {
    private String id;
    private String student_id;
    private String course_id;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "Student2Class{" +
                "student_id='" + student_id + '\'' +
                ", course_id='" + course_id + '\'' +
                '}';
    }
}
