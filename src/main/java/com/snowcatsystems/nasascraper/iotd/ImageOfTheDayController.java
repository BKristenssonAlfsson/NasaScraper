package com.snowcatsystems.nasascraper.iotd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/iotd")
public class ImageOfTheDayController {

    @Autowired
    private ImageOfTheDayRepository imageOfTheDayRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<ImageOfTheDayEntity> index() {
        return imageOfTheDayRepository.findAll();
    }
}
