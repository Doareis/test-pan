package com.test.backend.testpan.model;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

/**
 * @author doareis@gmail.com
 * @since Março 2019
 */
@Entity
@Data
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "numero_agencia", nullable = false)
    private Integer numeroAgencia;

    @Column(name = "numero_conta", nullable = false)
    private Integer numeroConta;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "municipio", nullable = false)
    private String municipio;

}
