package com.udacity.pricing.api;

import com.udacity.pricing.service.PricingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PricingController.class)
public class PricingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PricingService pricingService;

    @Test
    public void test_get_price() throws Exception {
        mockMvc.perform(get("/services/price")
                    .param("vehicleId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{}"));

        // verify(PricingService.getPrice(1), times(1));
    }
}
