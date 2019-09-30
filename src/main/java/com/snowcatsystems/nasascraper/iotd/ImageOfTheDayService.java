package com.snowcatsystems.nasascraper.iotd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageOfTheDayService {

    @Autowired
    private ImageOfTheDayRepository imageOfTheDayRepository;


    public List<ImageOfTheDayEntity> findAll() {
        return imageOfTheDayRepository.findAll();
    }

    public ImageOfTheDayEntity save(ImageOfTheDayEntity temp) {
        return imageOfTheDayRepository.save(temp);
    }

    public ImageOfTheDayEntity findByTitle(String title) {
        return imageOfTheDayRepository.findByTitle(title);
    }
}
