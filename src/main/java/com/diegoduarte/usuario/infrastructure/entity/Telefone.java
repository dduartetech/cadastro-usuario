package com.diegoduarte.usuario.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero", length = 9)
    private String numero;
    @Column(name = "ddd", length = 3)
    private String ddd;
}
