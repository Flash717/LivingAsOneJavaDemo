package com.fknaus.LivingAsOneDemo.model;

import java.util.ArrayList;
import java.util.List;

public class PictureDate {
    private String date;
    private List<Photo> photos;

    public PictureDate() {
        date = "";
        photos = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String _date) {
        this.date = _date;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> _photos) {
        this.photos = _photos;
    }


}
