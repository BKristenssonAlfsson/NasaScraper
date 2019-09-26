package com.snowcatsystems.nasascraper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserTest.class,
        ImageOfTheDayControllerTest.class,
        NasascraperApplicationTests.class
})

public class FeatureTestSuite {
}
