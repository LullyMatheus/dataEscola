package com.cefet.dataEscola.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cefet.dataEscola.dto.UsuarioRequestDTO;
import com.cefet.dataEscola.dto.UsuarioResponseDTO;
import com.cefet.dataEscola.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
        List<UsuarioResponseDTO> usuariosResponseDTO = usuarioService.findAll();
        return ResponseEntity.ok(usuariosResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioResponseDTO>> findById(@PathVariable Long id) {
        Optional<UsuarioResponseDTO> usuarioResponseDTO = usuarioService.findById(id);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
    	UsuarioResponseDTO usuarioResponseDTO = usuarioService.save(usuarioRequestDTO);
        return ResponseEntity.status(201).body(usuarioResponseDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {   	
    	// Lança exceção se o ID estiver ausente
        if (usuarioRequestDTO.getId() == null) {
            throw new IllegalArgumentException("O campo 'id' é obrigatório para atualização.");
        }

        // Lança exceção se o ID do path for diferente do ID do DTO
        if (!id.equals(usuarioRequestDTO.getId())) {
            throw new IllegalArgumentException("O ID informado na URL é diferente do ID do corpo da requisição.");
        }
        
        // Lança exceção se o ID não existir
        if (!usuarioService.existsById(id)) {
            throw new EntityNotFoundException("Usuario com ID " + id + " não localizado.");
        }           
       
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.save(usuarioRequestDTO);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsByCpf(@RequestParam String cpf) {
        boolean exists = usuarioService.existsByCpf(cpf);
        return ResponseEntity.ok(exists);
    }    
    
    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> autenticar(@RequestBody UsuarioRequestDTO loginDTO) {

        // valida se CPF e senha foram enviados
        if (loginDTO.getCpf() == null || loginDTO.getSenha() == null) {
            throw new IllegalArgumentException("CPF e senha são obrigatórios para autenticação.");
        }

        UsuarioResponseDTO usuarioResponseDTO = usuarioService
                .findByCpfAndSenha(loginDTO.getCpf(), loginDTO.getSenha())
                .orElseThrow(() -> new EntityNotFoundException("CPF ou senha inválidos."));

        return ResponseEntity.ok(usuarioResponseDTO);
    }    
}
