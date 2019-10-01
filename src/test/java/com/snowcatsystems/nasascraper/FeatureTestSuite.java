package com.snowcatsystems.nasascraper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.properties")
@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        ImageOfTheDayControllerTest.class,
        NasascraperApplicationTests.class
})

public class FeatureTestSuite {

}
