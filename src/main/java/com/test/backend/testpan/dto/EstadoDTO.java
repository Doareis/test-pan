package com.test.backend.testpan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author doareis@gmail.com
 * @since Março 2019
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDTO implements Comparable<EstadoDTO> {
    private int id;
    private String sigla;
    private String nome;

    @Override
    public int compareTo(EstadoDTO other) {
        return this.getNome().compareTo(other.getNome());
    }
}
