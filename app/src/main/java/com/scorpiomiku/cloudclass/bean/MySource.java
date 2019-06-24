package com.scorpiomiku.cloudclass.bean;

/**
 * Created by ScorpioMiku on 2019/6/24.
 */

public class MySource {
    private String sourceId;
    private String classId;
    private int type;
    private String upperId;
    private int downloadTime;

    @Override
    public String toString() {
        return "MySource{" +
                "sourceId='" + sourceId + '\'' +
                ", classId='" + classId + '\'' +
                ", type=" + type +
                ", upperId='" + upperId + '\'' +
                ", downloadTime=" + downloadTime +
                '}';
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUpperId() {
        return upperId;
    }

    public void setUpperId(String upperId) {
        this.upperId = upperId;
    }

    public int getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(int downloadTime) {
        this.downloadTime = downloadTime;
    }
}
