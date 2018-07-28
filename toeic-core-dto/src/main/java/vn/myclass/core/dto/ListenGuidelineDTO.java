package vn.myclass.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ListenGuidelineDTO implements Serializable {
    private Integer listenGuidelineId;
    private String title;
    private String image;
    private String content;
    private Timestamp createdDay;
    private Timestamp modifiedDay;

    public Integer getListenGuidelineId() {
        return listenGuidelineId;
    }

    public void setListenGuidelineId(Integer listenGuidelineId) {
        this.listenGuidelineId = listenGuidelineId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedDay() {
        return createdDay;
    }

    public void setCreatedDay(Timestamp createdDay) {
        this.createdDay = createdDay;
    }

    public Timestamp getModifiedDay() {
        return modifiedDay;
    }

    public void setModifiedDay(Timestamp modifiedDay) {
        this.modifiedDay = modifiedDay;
    }
}
