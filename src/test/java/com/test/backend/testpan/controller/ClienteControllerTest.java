package com.test.backend.testpan.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.backend.testpan.dto.EnderecoDTO;
import com.test.backend.testpan.model.Cliente;
import com.test.backend.testpan.repository.ClienteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
public class ClienteControllerTest {

    private static final String UM_CPF_INVALIDO = "123456";

    private static final String UM_CPF_VALIDO = "123456789";

    private static final String ENDPOINT_BUSCA = "http://localhost:8080/api/cliente/busca";

    private static final String ENDPOINT_ALTERACAO_ENDERECO = "http://localhost:8080/api/cliente/altera/endereco";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testaBuscaClienteCpfInvalido() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT_BUSCA + "/" + UM_CPF_INVALIDO)
            .contentType(MediaType.APPLICATION_JSON_VALUE))

            .andExpect(status().isNotFound());
    }

    @Test
    public void testaBuscaTodosClientes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT_BUSCA)
            .contentType(MediaType.APPLICATION_JSON_VALUE))

            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(9)));
    }

    @Test
    public void testaBuscaClienteCpfValido() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .get(ENDPOINT_BUSCA + "/" + UM_CPF_VALIDO)
            .contentType(MediaType.APPLICATION_JSON_VALUE))

            .andExpect(status().isOk())
            .andExpect(jsonPath("nome").value(is("José da Silva")))
            .andExpect(jsonPath("numeroAgencia").value(is(1234)))
            .andExpect(jsonPath("numeroConta").value(is(13456)))
            .andExpect(jsonPath("endereco").value(is("Av Paulista, 800")))
            .andExpect(jsonPath("municipio").value(is("São Paulo")))
            .andExpect(jsonPath("estado").value(is("SP")));
    }

    @Test
    public void testaAlteracaoEnderecoCliente() throws Exception {

        EnderecoDTO dto = EnderecoDTO.of("04431100", "Rua da Paz, 890", "", "Jd Oliveira", "Manaus", "AM");
        ObjectMapper mapper = new ObjectMapper();

        String contentBody = mapper.writer().writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders
            .post(ENDPOINT_ALTERACAO_ENDERECO + "/1")
            .content(contentBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE))

            .andExpect(status().isOk())
            .andExpect(jsonPath("nome").value(is("José da Silva")))
            .andExpect(jsonPath("numeroAgencia").value(is(1234)))
            .andExpect(jsonPath("numeroConta").value(is(13456)))
            .andExpect(jsonPath("endereco").value(is("Rua da Paz, 890")))
            .andExpect(jsonPath("municipio").value(is("Manaus")))
            .andExpect(jsonPath("estado").value(is("AM")));
    }
}