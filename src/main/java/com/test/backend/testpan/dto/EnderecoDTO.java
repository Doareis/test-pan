package com.test.backend.testpan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author doareis@gmail.com
 * @since Março 2019
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class EnderecoDTO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}
