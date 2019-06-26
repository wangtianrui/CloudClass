package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/26.
 */

public class Score {
    private String course_id;
    private String student_id;
    private float score;

    @Override
    public String toString() {
        return "Score{" +
                "course_id='" + course_id + '\'' +
                ", student_id='" + student_id + '\'' +
                ", score=" + score +
                '}';
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
