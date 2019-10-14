package com.fknaus.LivingAsOneDemo.handler;

import com.fknaus.LivingAsOneDemo.model.NasaRoverResponse;
import com.fknaus.LivingAsOneDemo.model.Photo;
import com.fknaus.LivingAsOneDemo.service.HelperService;
import com.fknaus.LivingAsOneDemo.service.RoverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivingAsOneHandler {

    static final Logger logger = LoggerFactory.getLogger(LivingAsOneHandler.class);
    private final RoverService roverService;

    @Autowired
    public LivingAsOneHandler(final RoverService roverService) {
        this.roverService = roverService;
    }

    public NasaRoverResponse getAllPhotos() {
        NasaRoverResponse response = new NasaRoverResponse();
        List<Photo> localImages = HelperService.getLocalImages();
        response.setPhotos(localImages);
        return response;
    }

    public NasaRoverResponse getPicturesByDate(String date) {
        // check date string
        String parseDate = HelperService.parseDate(date);
        if (parseDate == "") {
            logger.error("invalid date string " + date);
            return new NasaRoverResponse();
        }
        // get pictures from rover service
        return this.roverService.getPicturesByDate(parseDate);
    }

    public NasaRoverResponse runAcceptanceTest() {
        // run acceptance test
        NasaRoverResponse response = new NasaRoverResponse();
        List<String> acceptanceDates = HelperService.getDatesFromFile(HelperService.DATES_FILE);
        for (String date : acceptanceDates) {
            NasaRoverResponse dateResponse = getPicturesByDate(date);
            response.addPhotos(dateResponse.getPhotos());
        }
        return response;
    }
}
