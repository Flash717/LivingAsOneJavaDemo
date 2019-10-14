package com.fknaus.LivingAsOneDemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NasaRoverResponse {
    private List<Photo> photos;

    public NasaRoverResponse() {
        this.photos = new ArrayList<>();
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public void addPhotos(List<Photo> photos) {
        this.photos.addAll(photos);
    }


}
