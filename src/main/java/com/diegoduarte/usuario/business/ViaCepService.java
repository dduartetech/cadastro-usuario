package com.diegoduarte.usuario.business;

import com.diegoduarte.usuario.infrastructure.client.ViaCepClient;
import com.diegoduarte.usuario.infrastructure.client.ViaCepDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final ViaCepClient viaCepClient;

    private String cepFormatado (String cep) {
        String cepFormatado = cep.replace(" ", "").
                replace("-", "");

        if(!cepFormatado.matches("//d+") || !Objects.equals(cepFormatado.length(), 8)) {
            throw new IllegalArgumentException("O CEP contém caracteres inválidos, por favor verificar.");
        }

        return cepFormatado;
    }

    public ViaCepDTO buscaDadosViaCep (String cep) {
        return viaCepClient.buscaDadosViaCep(cepFormatado(cep));
    }
}
