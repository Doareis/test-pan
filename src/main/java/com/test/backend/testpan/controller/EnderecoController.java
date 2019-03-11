package com.test.backend.testpan.controller;

import com.test.backend.testpan.dto.EnderecoDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/busca/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EnderecoDTO busca(@PathVariable("cep") String cep) {
        String url = String.format("https://viacep.com.br/ws/%s/json", cep);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, EnderecoDTO.class);
    }
}
