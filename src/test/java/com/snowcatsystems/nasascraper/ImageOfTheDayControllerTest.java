package com.snowcatsystems.nasascraper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowcatsystems.nasascraper.iotd.ImageOfTheDayEntity;
import com.snowcatsystems.nasascraper.iotd.ImageOfTheDayModel;
import com.snowcatsystems.nasascraper.iotd.ImageOfTheDayService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MySQLContainer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ImageOfTheDayControllerTest {

    private MockMvc mockUser;
    private String token = null;
    private ObjectMapper mapper = new ObjectMapper();

    @ClassRule
    public static MySQLContainer mySQLContainer = MySqlContainer.getInstance();

    @Autowired
    private WebApplicationContext context;

    @MockBean
    ImageOfTheDayService imageOfTheDayService;

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

        MvcResult result = mockUser.perform(post("/login")
                .content("{ \"username\": \"test\", \"password\": \"test\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        this.token = result.getResponse().getHeader("Authorization");
    }


    @After
    public void clean() throws Exception {
        HttpHeaders httpHeaders = getHttpHeaders();

        mockUser.perform(delete("/login/delete")
                .headers(httpHeaders)
                .content("{ \"username\": \"test\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void t1GetAllImages() throws Exception {

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
    public void t2TestStoreImage() throws Exception {
        HttpHeaders httpHeaders = getHttpHeaders();

        ImageOfTheDayModel iotdm = new ImageOfTheDayModel();
        iotdm.setCopyright("test");

        ImageOfTheDayEntity iotd = new ImageOfTheDayEntity();
        iotd.setCopyright(iotdm.getCopyright());


        when(imageOfTheDayService.save(any(ImageOfTheDayEntity.class))).thenReturn(iotd);

        MvcResult result = mockUser.perform(post("/iotd/store")
                .headers(httpHeaders)
                .content(mapper.writeValueAsString(iotdm))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", token);
        httpHeaders.add("Content-Type", "application/json");

        return httpHeaders;
    }
}
