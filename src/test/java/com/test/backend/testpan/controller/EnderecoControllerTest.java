package com.test.backend.testpan.controller;

import static org.hamcrest.Matchers.is;
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
public class EnderecoControllerTest {

    private static final String ENDPOINT_BUSCA = "http://localhost:8080/api/endereco/busca";

    private static final String UM_CEP_VALIDO = "04431100";

    private static final String UM_CEP_INVALIDO = "999999999";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testaBuscaEnderecoValido() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT_BUSCA + "/" + UM_CEP_VALIDO)
            .contentType(MediaType.APPLICATION_JSON_VALUE))

            .andExpect(status().isOk())
            .andExpect(jsonPath("cep").value(is("04431-100")))
            .andExpect(jsonPath("logradouro").value(is("Rua José Frank")))
            .andExpect(jsonPath("complemento").value(is("")))
            .andExpect(jsonPath("bairro").value(is("Jardim Selma")))
            .andExpect(jsonPath("localidade").value(is("São Paulo")))
            .andExpect(jsonPath("uf").value(is("SP")));
    }

    @Test
    public void testaBuscaEnderecoInvalido() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT_BUSCA + "/" + UM_CEP_INVALIDO)
            .contentType(MediaType.APPLICATION_JSON_VALUE))

            .andExpect(status().isNotFound());
    }
}