package com.bratek.transit.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TransitControllerIT {

    private static final String TransitJson = "{\n" +
            "  \"sourceAddress\" : \"ul. Zakręt 8, Poznań\",\n" +
            "  \"destinationAddress\": \"Złota 44, Warszawa\",\n" +
            "  \"price\": 450,\n" +
            "  \"date\": \"2018-03-15\"\n" +
            "}";
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldReturnStatusCreatedWhenBodyIsValid() throws Exception {
        mockMvc.perform(post("/api/transit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TransitJson))
                .andExpect(status().isCreated());
    }
}