package com.test.backend.testpan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author doareis@gmail.com
 * @since Março 2019
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class EstadoDTO implements Comparable<EstadoDTO> {

    private int id;
    @NonNull
    private String sigla;
    @NonNull
    private String nome;

    @Override
    public int compareTo(EstadoDTO other) {
        return this.getSigla().compareTo(other.getSigla());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EstadoDTO) {
            EstadoDTO other = (EstadoDTO) obj;
            return this.getSigla().compareTo(other.getSigla()) == 0;
        }
        return false;
    }

}