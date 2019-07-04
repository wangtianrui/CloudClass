package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/7/4.
 */

public class Power {
    private String student_id;
    private String level;
    private String status;
    private int time;
    private String course_id;
    private String student_name;
    private String id_card;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Power{" +
                "student_id='" + student_id + '\'' +
                ", level='" + level + '\'' +
                ", status='" + status + '\'' +
                ", time=" + time +
                ", course_id='" + course_id + '\'' +
                ", student_name='" + student_name + '\'' +
                ", id_card='" + id_card + '\'' +
                '}';
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }
}
