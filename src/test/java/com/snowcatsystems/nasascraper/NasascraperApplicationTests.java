package com.snowcatsystems.nasascraper;

import com.snowcatsystems.nasascraper.iotd.ImageOfTheDayEntity;
import com.snowcatsystems.nasascraper.iotd.ImageOfTheDayRepository;
import org.assertj.core.api.Fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.Null;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class NasascraperApplicationTests {

	@Autowired
	private ImageOfTheDayRepository imageOfTheDayRepository;

	@Test
	public void findByTitle() {

		String title = "A Harvest Moon";
		ImageOfTheDayEntity found = imageOfTheDayRepository.findByTitle(title);

		try {
			assertThat(found.getTitle()).isEqualTo(title);
		} catch ( NullPointerException npe ) {
			Fail.fail("We failed!");
		}
	}

}
