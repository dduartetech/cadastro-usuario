package com.diegoduarte.usuario.infrastructure.repository;

import com.diegoduarte.usuario.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail (String email); // verifica se o email existe

    Optional<Usuario> findByEmail (String email);
    //optional: nao quebra o codigo, trata a excecao de uma forma melhor

    @Transactional //ajuda a causar nenhum erro na hora de deletar
    void deleteByEmail(String email);
}
