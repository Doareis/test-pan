package com.test.backend.testpan.controller;

import static org.apache.logging.log4j.LogManager.getLogger;

import com.test.backend.testpan.dto.EnderecoDTO;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * EndPoint para consulta de endereços
 *
 * @author doareis@gmail.com
 * @since Março 2019
 */
@RestController
@RequestMapping("api/endereco")
public class EnderecoController {

    private static final Logger LOGGER = getLogger(EnderecoController.class);

    private static final String URL = "https://viacep.com.br/ws/%s/json";

    @GetMapping(value = "/busca/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoDTO> busca(@PathVariable("cep") String cep) {

        LOGGER.debug("Iniciada busca de endereço para o CEP {}", cep);
        String url = String.format(URL, cep);
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(url, EnderecoDTO.class);
        } catch (Exception e) {
            LOGGER.error("Ocorreu erro na busca de endereço para o CEP {}", cep);
            return ResponseEntity.notFound().build();
        }
    }
}
