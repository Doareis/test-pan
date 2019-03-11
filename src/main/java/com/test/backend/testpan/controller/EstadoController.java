package com.test.backend.testpan.controller;

import com.test.backend.testpan.dto.EstadoDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * @author doareis@gmail.com
 * @since Março 2019
 */
@RestController
@RequestMapping("api/estado")
public class EstadoController {

    @GetMapping(value = "/busca", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EstadoDTO> busca() {
        String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<EstadoDTO>>() {
                });

        assert response != null;
        List<EstadoDTO> estados = (List<EstadoDTO>) response.getBody();
        assert estados != null;
        Collections.sort(estados);

        Collections.swap(estados, 0, estados.indexOf(new EstadoDTO(1, "SP", "São Paulo")));
        Collections.swap(estados, 1, estados.indexOf(new EstadoDTO(1, "RJ", "Rio de Janeiro")));
        return estados;
    }
}
