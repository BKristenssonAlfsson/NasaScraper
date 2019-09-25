package com.snowcatsystems.nasascraper;

import com.snowcatsystems.nasascraper.iotd.ImageOfTheDayController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ImageOfTheDayControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ImageOfTheDayController imageOfTheDayController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(imageOfTheDayController).build();
    }

    @Test
    public void testGetAllImages() throws Exception {
        mockMvc.perform(get("/iotd/"))
                .andExpect(status().isForbidden());
    }
}
