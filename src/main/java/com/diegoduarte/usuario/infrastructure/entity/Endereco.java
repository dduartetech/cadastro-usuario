package com.diegoduarte.usuario.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
}
