package com.xx.savethedate.Bean;

public class MarkContent {
    private String photo;
    private String username;
    private String kind;
    private String markcontent;
    private String selected;
    private String unselected;

    public String getUsername() {
        return username;
    }

    public String getPhoto() {
        return photo;
    }

    public String getKind() {
        return kind;
    }

    public String getMarkcontent() {
        return markcontent;
    }

    public String getSelected() {
        return selected;
    }

    public String getUnselected() {
        return unselected;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setMarkcontent(String markcontent) {
        this.markcontent = markcontent;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public void setUnselected(String unselected) {
        this.unselected = unselected;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
