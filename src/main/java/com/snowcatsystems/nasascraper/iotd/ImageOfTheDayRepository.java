package com.snowcatsystems.nasascraper.iotd;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageOfTheDayRepository extends JpaRepository<ImageOfTheDayEntity, Long> {
    ImageOfTheDayEntity findByTitle(String title);
}
