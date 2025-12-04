package com.diegoduarte.usuario.business.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {

    private String nome;
    private String senha;
    private List<EnderecoDTO> enderecoDTOS;
    private List<TelefoneDTO> telefoneDTOS;
}
