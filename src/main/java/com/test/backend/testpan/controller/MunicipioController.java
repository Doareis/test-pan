package com.test.backend.testpan.controller;

import com.test.backend.testpan.dto.MunicipioDTO;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author doareis@gmail.com
 * @since Março 2019
 */
@RestController
@RequestMapping("/api/municipios")
public class MunicipioController {

    private static final String URL = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/%d/municipios";

    @GetMapping(value = "/busca/{idEstado}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MunicipioDTO> busca(@PathVariable("idEstado") int idEstado) {
        String url = String.format(URL, idEstado);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<MunicipioDTO>>() {});
        assert response != null;
        return (List<MunicipioDTO>) response.getBody();
    }
}
