package com.diegoduarte.usuario.business;

import com.diegoduarte.usuario.business.DTO.UsuarioDTO;
import com.diegoduarte.usuario.business.converter.UsuarioConverter;
import com.diegoduarte.usuario.infrastructure.entity.Usuario;
import com.diegoduarte.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario (UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuarioEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }
}
