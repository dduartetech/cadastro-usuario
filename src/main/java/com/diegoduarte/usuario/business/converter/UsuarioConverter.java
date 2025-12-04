package com.diegoduarte.usuario.business.converter;

import com.diegoduarte.usuario.business.DTO.EnderecoDTO;
import com.diegoduarte.usuario.business.DTO.TelefoneDTO;
import com.diegoduarte.usuario.business.DTO.UsuarioDTO;
import com.diegoduarte.usuario.infrastructure.entity.Endereco;
import com.diegoduarte.usuario.infrastructure.entity.Telefone;
import com.diegoduarte.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuarioEntity (UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .email(usuarioDTO.getEmail())
                .nome(usuarioDTO.getNome())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecoEntity(usuarioDTO.getEndereco()))
                .telefones(paraListaTelefoneEntity(usuarioDTO.getTelefone()))
                .build();
    }

    public Endereco paraEnderecoEntity (EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .cep(enderecoDTO.getCep())
                .bairro(enderecoDTO.getBairro())
                .complemento(enderecoDTO.getComplemento())
                .estado(enderecoDTO.getEstado())
                .numero(enderecoDTO.getNumero())
                .build();
    }

    public List<Endereco> paraListaEnderecoEntity (List<EnderecoDTO> enderecosDTO) {
        List<Endereco> enderecos = new ArrayList<>();
        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            enderecos.add(paraEnderecoEntity(enderecoDTO));
        }
        return enderecos;
    }

    public Telefone paraTelefoneEntity (TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }

    public List<Telefone> paraListaTelefoneEntity (List<TelefoneDTO> telefonesDTO){
        List<Telefone> telefones = new ArrayList<>();
        for (TelefoneDTO telefoneDTO : telefonesDTO) {
            telefones.add(paraTelefoneEntity(telefoneDTO));
        }
        return telefones;
    }

    public UsuarioDTO paraUsuarioDTO (Usuario usuario) {
        return UsuarioDTO.builder()
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .senha(usuario.getSenha())
                .endereco(paraListaEnderecoDTO(usuario.getEnderecos()))
                .telefone(paraListaTelefoneDTO(usuario.getTelefones()))
                .build();
    }

    public EnderecoDTO paraEnderecoDTO (Endereco endereco) {
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .cep(endereco.getCep())
                .bairro(endereco.getBairro())
                .complemento(endereco.getComplemento())
                .estado(endereco.getEstado())
                .numero(endereco.getNumero())
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO (List<Endereco> enderecos) {
        List<EnderecoDTO> enderecosDTO = new ArrayList<>();
        for (Endereco endereco : enderecos) {
            enderecosDTO.add(paraEnderecoDTO(endereco));
        }
        return enderecosDTO;
    }

    public TelefoneDTO paraTelefoneDTO (Telefone telefone) {
        return TelefoneDTO.builder()
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO (List<Telefone> telefones) {
        List<TelefoneDTO> telefonesDTO = new ArrayList<>();
        for (Telefone telefone : telefones) {
            telefonesDTO.add(paraTelefoneDTO(telefone));
        }
        return telefonesDTO;
    }
}
