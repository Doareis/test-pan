package com.test.backend.testpan.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MunicipioControllerTest {

    private static final String ENDPOINT = "http://localhost:8080/api/municipios/busca";

    private static final String UM_ID_ESTADO_VALIDO = "11";

    private static final String UM_ID_ESTADO_INVALIDO = "222";


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testaBuscaMunicipiosEstadoValido() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT + "/" + UM_ID_ESTADO_VALIDO)
            .contentType(MediaType.APPLICATION_JSON_VALUE))

            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(52)));
    }

    @Test
    public void testaBuscaMunicipiosEstadoInvalido() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT + "/" + UM_ID_ESTADO_INVALIDO)
            .contentType(MediaType.APPLICATION_JSON_VALUE))

            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }
}