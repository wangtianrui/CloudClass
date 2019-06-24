package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class Course {
    private String courseId;
    private String teacherId;
    private String inviteCode;
    private String courseName;
    private int rowNumber;
    private int columnNumber;
    private String classRoomNumber;
    private int week;
    private int span;
    private int startTime;

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", rowNumber=" + rowNumber +
                ", columnNumber=" + columnNumber +
                ", classRoomNumber='" + classRoomNumber + '\'' +
                ", week=" + week +
                ", span=" + span +
                ", startTime=" + startTime +
                '}';
    }

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
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public String getClassRoomNumber() {
        return classRoomNumber;
    }

    public void setClassRoomNumber(String classRoomNumber) {
        this.classRoomNumber = classRoomNumber;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTeacherNumber() {
        return teacherId;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherId = teacherNumber;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


}
