package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/26.
 */

public class CommunicationItem {
    private String uper_id;
    private String communicaiton_id;
    private String content;
    private String up_time;
    private String user_name;
    private int user_type;

    @Override
    public String toString() {
        return "CommunicationItem{" +
                "uper_id='" + uper_id + '\'' +
                ", communicaiton_id='" + communicaiton_id + '\'' +
                ", content='" + content + '\'' +
                ", up_time='" + up_time + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_type=" + user_type +
                '}';
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getUper_id() {
        return uper_id;
    }

    public void setUper_id(String uper_id) {
        this.uper_id = uper_id;
    }

    public String getCommunicaiton_id() {
        return communicaiton_id;
    }

    public void setCommunicaiton_id(String communicaiton_id) {
        this.communicaiton_id = communicaiton_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
