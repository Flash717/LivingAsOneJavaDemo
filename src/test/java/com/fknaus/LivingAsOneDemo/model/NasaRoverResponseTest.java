package com.fknaus.LivingAsOneDemo.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NasaRoverResponseTest {

    public static final String TEST = "test";
    private NasaRoverResponse nasaRoverResponse;
    private List<Photo> photoList;

    @Test
    public void getPhotos() {
        photoList.add(new Photo(TEST));
        nasaRoverResponse.addPhotos(photoList);
        assertNotNull("we get something back", nasaRoverResponse.getPhotos());
        assertEquals("returns proper photos", photoList, nasaRoverResponse.getPhotos());
        assertEquals("and we get the matching photo back", TEST, nasaRoverResponse.getPhotos().get(0).getImg_name());
    }

    @Test
    public void setPhotos() {
        photoList.add(new Photo(TEST));
        nasaRoverResponse.setPhotos(photoList);
        assertEquals("we get the same photo list", photoList, nasaRoverResponse.getPhotos());
    }

    @Test
    public void addPhotos() {
        photoList.add(new Photo(TEST));
        nasaRoverResponse.addPhotos(photoList);
        assertEquals("something has been added", 1, nasaRoverResponse.getPhotos().size());
        List<Photo> newPhotoList = new ArrayList<>();
        newPhotoList.add(new Photo("another test"));
        nasaRoverResponse.addPhotos(newPhotoList);
        assertEquals("we now got more photos", 2, nasaRoverResponse.getPhotos().size());
    }

    @Before
    public void setUp() throws Exception {
        nasaRoverResponse = new NasaRoverResponse();
        photoList = new ArrayList<>();
    }
}