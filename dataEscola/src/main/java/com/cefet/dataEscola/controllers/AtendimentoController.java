package com.cefet.dataEscola.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cefet.dataEscola.dto.AtendimentoRequestDTO;
import com.cefet.dataEscola.dto.AtendimentoResponseDTO;
import com.cefet.dataEscola.entities.Atendimento;
import com.cefet.dataEscola.services.AtendimentoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/atendimento")

public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping
    public ResponseEntity<List<AtendimentoResponseDTO>> findAll(){
        List<AtendimentoResponseDTO> atendimentosResponseDTO = atendimentoService.findAll();
        return ResponseEntity.ok(atendimentosResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AtendimentoResponseDTO>> findById(@PathVariable Long id){
        Optional<AtendimentoResponseDTO> atendimentoResponseDTO = atendimentoService.findById(id);
        return ResponseEntity.ok(atendimentoResponseDTO);         
    } 

    @GetMapping("/por-aluno")
    public List<Atendimento> listarPorAluno(@RequestParam String nome) {
        //filtra os atendimentos por aluno
        return atendimentoService.buscarPorNomeDoAluno(nome);
    }

    @PostMapping
    public ResponseEntity<AtendimentoResponseDTO> create(@Valid @RequestBody AtendimentoRequestDTO atendimentoRequestDTO){
        AtendimentoResponseDTO atendimentoResponseDTO = atendimentoService.save(atendimentoRequestDTO);
        return ResponseEntity.status(201).body(atendimentoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoResponseDTO> update(@PathVariable Long id,@Valid @RequestBody AtendimentoRequestDTO atendimentoRequestDTO){
        // Lança exceção se o ID estiver ausente
        if (atendimentoRequestDTO.getId() == null) {
            throw new IllegalArgumentException("O campo 'id' é obrigatório para atualização.");
        }

        // Lança exceção se o ID do path for diferente do ID do DTO
        if (!id.equals(atendimentoRequestDTO.getId())) {
            throw new IllegalArgumentException("O ID informado na URL é diferente do ID do corpo da requisição.");
        }
        
        // Lança exceção se o ID não existir
        if (!atendimentoService.existsById(id)) {
            throw new EntityNotFoundException("Atendimento com ID " + id + " não localizado.");
        }           
       
        AtendimentoResponseDTO atendimentoResponseDTO = atendimentoService.save(atendimentoRequestDTO);
        return ResponseEntity.ok(atendimentoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atendimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
