package com.scorpiomiku.cloudclass.bean;


/**
 * Created by weihuajian on 16/6/12.
 */
public class CourseModel {

    private String id; //课程ID
    private String course_name;
    private int section; //从第几节课开始
    private int span; //跨几节课
    private int week; //周几
    private String classroom_number; //教室
    private String user_id;
    private int courseFlag; //课程背景颜色

    public int getCourseFlag() {
        return courseFlag;
    }

    public void setCourseFlag(int courseFlag) {
        this.courseFlag = courseFlag;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getClassroom_number() {
        return classroom_number;
    }

    public void setClassroom_number(String classroom_number) {
        this.classroom_number = classroom_number;
    }
}
