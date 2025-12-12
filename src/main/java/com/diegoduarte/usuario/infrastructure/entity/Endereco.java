package com.diegoduarte.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rua")
    private String rua;

    @Column(name = "numero")
    private int numero;

    @Column(name = "complemento", length = 100)
    private String complemento;

    @Column(name = "cep", length = 9)
    private String cep;

    @Column(name = "estado", length = 100)
    private String estado;

    @Column(name = "bairro", length = 100)
    private String bairro;
}
