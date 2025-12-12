package com.diegoduarte.usuario.business.DTO;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class EnderecoDTO {

    private String rua;
    private int numero;
    private String complemento;
    private String cep;
    private String estado;
    private String bairro;
}
