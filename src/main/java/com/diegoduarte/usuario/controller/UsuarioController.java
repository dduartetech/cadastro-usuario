package com.diegoduarte.usuario.controller;

import com.diegoduarte.usuario.business.DTO.EnderecoDTO;
import com.diegoduarte.usuario.business.DTO.TelefoneDTO;
import com.diegoduarte.usuario.business.DTO.UsuarioDTO;
import com.diegoduarte.usuario.business.UsuarioService;
import com.diegoduarte.usuario.business.ViaCepService;
import com.diegoduarte.usuario.infrastructure.client.ViaCepDTO;
import com.diegoduarte.usuario.infrastructure.entity.Usuario;
import com.diegoduarte.usuario.infrastructure.security.JwtUtil;
import com.diegoduarte.usuario.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastra usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ViaCepService viaCepService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario (@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.autenticarUsuario(usuarioDTO));
    }

    @GetMapping
    public ResponseEntity<UsuarioDTO> buscaPorEmail(@RequestParam("email")  String email) {
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email){
        usuarioService.deletaUsuarioPorEmail(email);
        return ResponseEntity.ok().build(); //.build ja q n temos retorno
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario (@RequestBody UsuarioDTO usuarioDTO,
                                                            @RequestHeader ("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizaEndereco (@RequestBody EnderecoDTO enderecoDTO,
                                                         @RequestParam("id") Long id) {
        return ResponseEntity.ok(usuarioService.atualizadoEndereco(id, enderecoDTO));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizaTelefone (@RequestBody TelefoneDTO telefoneDTO,
                                                         @RequestParam("id") Long id) {
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDTO));
    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTO> cadastraEndereco (@RequestBody EnderecoDTO enderecoDTO,
                                                         @RequestHeader("Authorizantion") String token) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, enderecoDTO));
    }

    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDTO> cadastraTelefone (@RequestBody TelefoneDTO telefoneDTO,
                                                         @RequestHeader("Authorizantion") String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, telefoneDTO));
    }

    @GetMapping("/endereco/{cep}")
    public ResponseEntity<ViaCepDTO> buscaDadosViaCep (@PathVariable("cep") String cep) {
        return ResponseEntity.ok(viaCepService.buscaDadosViaCep(cep));
    }

}
