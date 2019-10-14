package com.fknaus.LivingAsOneDemo.controller;

import com.fknaus.LivingAsOneDemo.handler.LivingAsOneHandler;
import com.fknaus.LivingAsOneDemo.model.NasaRoverResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LivingAsOneController {

    static final Logger logger = LoggerFactory.getLogger(LivingAsOneController.class);
    private static final String NASA_IMAGES = "nasaImages";

    private final LivingAsOneHandler livingAsOneHandler;

    @Autowired
    public LivingAsOneController(final LivingAsOneHandler livingAsOneHandler) {
        this.livingAsOneHandler = livingAsOneHandler;
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        /**
         * this returns all currently downloaded images
         */
        logger.info("index");
        NasaRoverResponse nasaImages = livingAsOneHandler.getAllPhotos();
        model.addAttribute(NASA_IMAGES, nasaImages);
        return "index";
    }

    @GetMapping(value = "/picture/{date}")
    public String getPicturesByDate(@PathVariable("date") String date, Model model) {
        /**
         * this downloads all images for the specified date (if not already local) and displays them
         */
        logger.info("getPicturesByDate");
        NasaRoverResponse nasaImages = livingAsOneHandler.getPicturesByDate(date);
        model.addAttribute(NASA_IMAGES, nasaImages);
        return "index";
    }

    @GetMapping(value = "/acceptance")
    public String runAcceptanceTest(Model model) {
        /**
         * this runs the acceptance criteria (see README.md)
         */
        logger.info("runAcceptanceTest");
        NasaRoverResponse nasaImages = livingAsOneHandler.runAcceptanceTest();
        model.addAttribute(NASA_IMAGES, nasaImages);
        return "index";
    }
}
