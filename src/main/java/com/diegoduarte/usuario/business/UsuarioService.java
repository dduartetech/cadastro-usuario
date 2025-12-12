package com.diegoduarte.usuario.business;

import com.diegoduarte.usuario.business.DTO.UsuarioDTO;
import com.diegoduarte.usuario.business.converter.UsuarioConverter;
import com.diegoduarte.usuario.infrastructure.entity.Usuario;
import com.diegoduarte.usuario.infrastructure.exceptions.ConflictException;
import com.diegoduarte.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.diegoduarte.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public boolean verificaEmail (String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public void emailExiste (String email) {
        try {
            boolean existe = verificaEmail(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado.");
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado.",e.getCause());
        }
    }

    public UsuarioDTO salvaUsuario (UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuarioEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(usuario);
    }

    public Usuario buscaUsuarioPorEmail (String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não cadastrado."));
    }

    public void deletaUsuarioPorEmail (String email) {
        usuarioRepository.deleteByEmail(email);
    }
}
