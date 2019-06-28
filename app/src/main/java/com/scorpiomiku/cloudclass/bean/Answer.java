package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/28.
 */

public class Answer {
    private String homework_id;
    private String content;
    private String uper_id;
    private String up_time;
    private String image;
    private float score;

    @Override
    public String toString() {
        return "Answer{" +
                "homework_id='" + homework_id + '\'' +
                ", content='" + content + '\'' +
                ", uper_id='" + uper_id + '\'' +
                ", up_time='" + up_time + '\'' +
                ", image='" + image + '\'' +
                ", score=" + score +
                '}';
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getHomework_id() {
        return homework_id;
    }

    public void setHomework_id(String homework_id) {
        this.homework_id = homework_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUper_id() {
        return uper_id;
    }

    public void setUper_id(String uper_id) {
        this.uper_id = uper_id;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
