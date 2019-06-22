package com.scorpiomiku.cloudclass.bean;

import java.util.ArrayList;

/**
 * Created by ScorpioMiku on 2019/6/22.
 */

public class Course {
    private String courseId;
    private String teacherNumber;
    private String inviteCode;
    private String courseName;
    private ArrayList<String> studentList = new ArrayList<>();
    private ArrayList<String> informList = new ArrayList<>();
    private ArrayList<String> questionList = new ArrayList<>();
    private ArrayList<String> homeWorkList = new ArrayList<>();
    private ArrayList<String> mySourceList = new ArrayList<>();
    private ArrayList<String> discussionList = new ArrayList<>();

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", teacherNumber='" + teacherNumber + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", studentList=" + studentList +
                ", informList=" + informList +
                ", questionList=" + questionList +
                ", homeWorkList=" + homeWorkList +
                ", mySourceList=" + mySourceList +
                ", discussionList=" + discussionList +
                '}';
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
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

    public ArrayList<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<String> studentList) {
        this.studentList = studentList;
    }

    public ArrayList<String> getInformList() {
        return informList;
    }

    public void setInformList(ArrayList<String> informList) {
        this.informList = informList;
    }

    public ArrayList<String> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<String> questionList) {
        this.questionList = questionList;
    }

    public ArrayList<String> getHomeWorkList() {
        return homeWorkList;
    }

    public void setHomeWorkList(ArrayList<String> homeWorkList) {
        this.homeWorkList = homeWorkList;
    }

    public ArrayList<String> getMySourceList() {
        return mySourceList;
    }

    public void setMySourceList(ArrayList<String> mySourceList) {
        this.mySourceList = mySourceList;
    }

    public ArrayList<String> getDiscussionList() {
        return discussionList;
    }

    public void setDiscussionList(ArrayList<String> discussionList) {
        this.discussionList = discussionList;
    }
}
