package com.snowcatsystems.nasascraper;

import com.snowcatsystems.nasascraper.iotd.ImageOfTheDayEntity;
import com.snowcatsystems.nasascraper.iotd.ImageOfTheDayRepository;
import org.assertj.core.api.Fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NasascraperApplicationTests {

	@Autowired
	private ImageOfTheDayRepository imageOfTheDayRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void findByTitle() {
		ImageOfTheDayEntity image = new ImageOfTheDayEntity("A Harvest Moon");

		testEntityManager.persist(image);
		testEntityManager.flush();

		ImageOfTheDayEntity found = imageOfTheDayRepository.findByTitle(image.getTitle());

		try {
			assertThat(found.getTitle()).isEqualTo(image.getTitle());
		} catch ( NullPointerException npe ) {
			Fail.fail("We failed!");
		}
	}
}
