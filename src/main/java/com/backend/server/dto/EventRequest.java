package com.backend.server.dto;

public class EventRequest {

    private String name;
    private String username;
    private String title;
    private Boolean is_Paid_Free;
    private String type;
    private String image;
    private String date;

    public EventRequest(String date, String image, Boolean is_Paid_Free, String name, String title, String type, String username) {
        this.date = date;
        this.image = image;
        this.is_Paid_Free = is_Paid_Free;
        this.name = name;
        this.title = title;
        this.type = type;
        this.username = username;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean isIs_Paid_Free() {
        return is_Paid_Free;
    }

    public void setIs_Paid_Free(Boolean is_Paid_Free) {
        this.is_Paid_Free = is_Paid_Free;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
