package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/24.
 */

public class Student2Class {
    private String studentId;
    private String classId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "Student2Class{" +
                "studentId='" + studentId + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }
}
