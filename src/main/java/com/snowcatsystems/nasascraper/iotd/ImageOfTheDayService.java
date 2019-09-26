package com.snowcatsystems.nasascraper.iotd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageOfTheDayService {

    @Autowired
    private ImageOfTheDayRepository imageOfTheDayRepository;


    public List<ImageOfTheDayEntity> findAll() {
        imageOfTheDayRepository.findAll();
        return null;
    }

    public ImageOfTheDayEntity save(ImageOfTheDayEntity temp) {
        imageOfTheDayRepository.save(temp);
        return null;
    }

    public ImageOfTheDayEntity findByTitle(String title) {
        imageOfTheDayRepository.findByTitle(title);

        return null;
    }
}
