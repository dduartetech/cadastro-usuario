package com.diegoduarte.usuario.business;

import com.diegoduarte.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
}
