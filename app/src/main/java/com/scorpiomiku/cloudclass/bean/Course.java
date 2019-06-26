package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class Course {
    private String course_id;
    private String creator_id;
    private String invite_code;
    private String name;
    private int row_number;
    private int column_number;
    private String class_room_number;
    private int week;
    private int span;
    private int section;


    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }

    public int getStartTime() {
        return section;
    }

    public void setStartTime(int startTime) {
        this.section = startTime;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public int getRow_number() {
        return row_number;
    }

    public void setRow_number(int row_number) {
        this.row_number = row_number;
    }

    public int getColumn_number() {
        return column_number;
    }

    public void setColumn_number(int column_number) {
        this.column_number = column_number;
    }

    public String getClass_room_number() {
        return class_room_number;
    }

    public void setClass_room_number(String class_room_number) {
        this.class_room_number = class_room_number;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getTeacherNumber() {
        return creator_id;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.creator_id = teacherNumber;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
