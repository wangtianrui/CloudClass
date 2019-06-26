package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/26.
 */

public class CommunicationItem {
    private String uper_id;
    private String communicaiton_id;
    private String content;

    @Override
    public String toString() {
        return "CommunicationItem{" +
                "uper_id='" + uper_id + '\'' +
                ", communicaiton_id='" + communicaiton_id + '\'' +
                ", content='" + content + '\'' +
                '}';
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
