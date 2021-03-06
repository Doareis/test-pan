package com.test.backend.testpan.controller;

import static org.apache.logging.log4j.LogManager.getLogger;

import com.test.backend.testpan.dto.EstadoDTO;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author doareis@gmail.com
 * @since Março 2019
 */
@RestController
@RequestMapping("api/estados")
public class EstadoController {

    private static final Logger LOGGER = getLogger(EstadoController.class);

    private static final String URL = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";

    @GetMapping(value = "/busca", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EstadoDTO> busca() {

        LOGGER.debug("Iniciada busca de estados");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<EstadoDTO>>() {});

        assert response != null;
        List<EstadoDTO> estados = (List<EstadoDTO>) response.getBody();
        assert estados != null;
        Collections.sort(estados);

        Collections.swap(estados, 0, estados.indexOf(new EstadoDTO("SP", "São Paulo")));
        Collections.swap(estados, 1, estados.indexOf(new EstadoDTO("RJ", "Rio de Janeiro")));

        LOGGER.debug("Foram encontrados {} estados", estados.size());

        return estados;
    }
}
