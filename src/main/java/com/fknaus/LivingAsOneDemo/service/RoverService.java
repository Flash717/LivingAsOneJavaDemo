package com.fknaus.LivingAsOneDemo.service;

import com.fknaus.LivingAsOneDemo.model.NasaRoverResponse;
import com.fknaus.LivingAsOneDemo.model.Photo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class RoverService {

    @Value("${roverService.url}")
    private String url;
    @Value("${roverService.key}")
    private String urlKey;

    private static final Logger log = LoggerFactory.getLogger(RoverService.class);

    public RoverService() {

    }

    /**
     * for testing configuration only
     */
    public void logConfig() {
        log.debug("url: " + this.url + ", key: " + this.urlKey + ", dest: " + HelperService.getFullDestinationFolder());
    }

    public NasaRoverResponse getPicturesByDate(String dateStr) {
        NasaRoverResponse result = new NasaRoverResponse();
        int page = 1;
        NasaRoverResponse data = new NasaRoverResponse();
        while (!data.getPhotos().isEmpty() || page == 1) {
            data = getNasaRoverApiData(dateStr, page);
            result.addPhotos(data.getPhotos());
            page += 1;
        }

        if (!result.getPhotos().isEmpty()) {
            for(Photo photo : result.getPhotos()) {
                String fileName = "";
                try {
                    fileName = savePictureFromUrl(photo);
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
                photo.setLocal_filename(fileName);
            }
        }
        return result;
    }

    public String savePictureFromUrl(final Photo photo) throws IOException {
        String destFileName = HelperService.getFullDestinationFolder() + "/" + photo.getImg_name();
        File destFolder = new File(HelperService.getFullDestinationFolder());
        if (!destFolder.exists()) {
            destFolder.mkdir();
            if (!destFolder.exists()) {
                log.error("Could not create folder " + HelperService.getFullDestinationFolder());
                return "";
            }
        }
        File file = new File(destFileName);

        // if file is not already local then download it
        if (!file.exists()) {
            log.debug(Paths.get(destFileName).toString());
            try(InputStream in = new URL(photo.getImg_src()).openStream()) {
                Files.copy(in, Paths.get(destFileName));
            }
        }

        return destFileName;
    }

    private NasaRoverResponse getNasaRoverApiData(final String dateString, final int page) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(this.url, dateString, page, this.urlKey);
        log.debug(url);
        NasaRoverResponse response = new NasaRoverResponse();
        try {
            response = restTemplate.getForObject(url, NasaRoverResponse.class);
        } catch (RestClientException e) {
            log.error(e.getMessage());
        }
        return response;
    }

}
