package com.snowcatsystems.nasascraper;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MySQLContainer;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    @TestConfiguration
    public static class Config {
        @Bean
        public MySQLContainer testContainer() {
            MySQLContainer container = new MySQLContainer();
            container.start();

            return container;
        }

        @Bean
        @Primary
        public DataSource dataSource(MySQLContainer container) {
            return DataSourceBuilder.create()
                    .url(container.getJdbcUrl())
                    .username(container.getUsername())
                    .password(container.getPassword())
                    .driverClassName(container.getDriverClassName())
                    .build();
        }
    }

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockUser;

    @Before
    public void setup() throws Exception {
        mockUser = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        mockUser.perform(post("/login/adduser")
                .content("{ \"username\": \"test\", \"password\": \"test\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(1)
    public void testLogin() throws Exception {
        MvcResult result = mockUser.perform(post("/login")
                .content("{ \"username\": \"test\", \"password\": \"test\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getHeader("Authorization")).isNotNull();
    }
}
