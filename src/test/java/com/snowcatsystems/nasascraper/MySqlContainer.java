package com.snowcatsystems.nasascraper;

import org.testcontainers.containers.MySQLContainer;

public class MySqlContainer extends MySQLContainer<MySqlContainer> {

    private static final String IMAGE = "mysql";
    private static MySqlContainer mySqlContainer;

    private MySqlContainer() {
        super(IMAGE);
    }

    public static MySqlContainer getInstance() {
        if ( mySqlContainer == null ) {
            mySqlContainer = new MySqlContainer();
        }
        return mySqlContainer;
    }

    @Override
    public void start() {
        super.start();
        String jdbcUrl = mySqlContainer.getJdbcUrl();
        String a = jdbcUrl.substring(0,5);
        String b = jdbcUrl.substring(5,33);
        String testJdbcUrl = (a + "tc:" + b);
        System.setProperty("DB_URL", testJdbcUrl);
        System.setProperty("DB_USERNAME", mySqlContainer.getUsername());
        System.setProperty("DB_PASSWORD", mySqlContainer.getPassword());
    }

    @Override
    public void stop() {}
}
