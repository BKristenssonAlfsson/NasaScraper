package com.snowcatsystems.nasascraper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImageOfTheDayControllerTest {

    private MockMvc mockUser;
    private String token = null;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() throws Exception {
        mockUser = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        MvcResult result = mockUser.perform(post("/login")
                .content("{ \"username\": \"test\", \"password\": \"test\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        this.token = result.getResponse().getHeader("Authorization");
    }

    @Test
    public void T1GetAllImagesSuccess() throws Exception {

        HttpHeaders httpHeaders = getHttpHeaders();

        MvcResult success = mockUser.perform(get("/iotd/").secure(true)
                .headers(httpHeaders))
                .andReturn();

        MvcResult fail = mockUser.perform(get("/iotd/").secure(true))
                .andReturn();

        assertThat(success.getResponse().getStatus()).isEqualTo(200);
        assertThat(fail.getResponse().getStatus()).isEqualTo(403);
    }

    @Test
    public void T2TestStoreImage() {

    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", token);
        httpHeaders.add("Content-Type", "application/json");

        return httpHeaders;
    }
}
