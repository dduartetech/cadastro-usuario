package com.diegoduarte.usuario.business.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TelefoneDTO {

    private Long id;
    private String numero;
    private String ddd;
}
