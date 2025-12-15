package com.diegoduarte.usuario.business;

import com.diegoduarte.usuario.business.DTO.EnderecoDTO;
import com.diegoduarte.usuario.business.DTO.TelefoneDTO;
import com.diegoduarte.usuario.business.DTO.UsuarioDTO;
import com.diegoduarte.usuario.business.converter.UsuarioConverter;
import com.diegoduarte.usuario.infrastructure.entity.Endereco;
import com.diegoduarte.usuario.infrastructure.entity.Telefone;
import com.diegoduarte.usuario.infrastructure.entity.Usuario;
import com.diegoduarte.usuario.infrastructure.exceptions.ConflictException;
import com.diegoduarte.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.diegoduarte.usuario.infrastructure.repository.EnderecoRepository;
import com.diegoduarte.usuario.infrastructure.repository.TelefoneRepository;
import com.diegoduarte.usuario.infrastructure.repository.UsuarioRepository;
import com.diegoduarte.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.LongFunction;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

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

    public UsuarioDTO buscaUsuarioPorEmail (String email) {
        try {
            return usuarioConverter.paraUsuarioDTO(usuarioRepository.findByEmail(email).orElseThrow(
                    () -> new ResourceNotFoundException("Email não cadastrado.")));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Email não encontrado: " + email);
        }

    }

    public void deletaUsuarioPorEmail (String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaDadosUsuario (String token, UsuarioDTO usuarioDTO) {
        String email = jwtUtil.extractUsername(token.substring(7));

        usuarioDTO.setSenha(usuarioDTO.getSenha() != null ? passwordEncoder.encode(usuarioDTO.getSenha()) : null);

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email não encontrado"));

        Usuario usuario1 = usuarioConverter.updateUsuario(usuarioDTO, usuario);

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario1));
    }

    public EnderecoDTO atualizadoEndereco (Long idEndereco, EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoRepository.findById(idEndereco).orElseThrow(() ->
                new ResourceNotFoundException("Endereço não localizado."));
        Endereco endereco1 = usuarioConverter.updateEndereco(enderecoDTO, endereco);
        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco1));
    }

    public TelefoneDTO atualizaTelefone (Long idTelefone, TelefoneDTO telefoneDTO) {
        Telefone telefone = telefoneRepository.findById(idTelefone).orElseThrow(() ->
                new ResourceNotFoundException("Telefone não localizado"));
        Telefone telefone1 = usuarioConverter.updateTelefone(telefoneDTO, telefone);
        return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone1));
    }
}
