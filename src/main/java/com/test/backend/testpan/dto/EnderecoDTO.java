package com.test.backend.testpan.dto;

import lombok.Data;

/**
 * @author doareis@gmail.com
 * @since Março 2019
 */
@Data
public class EnderecoDTO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}
