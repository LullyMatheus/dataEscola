package com.cefet.dataEscola.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cefet.dataEscola.dto.AtividadeRequestDTO;
import com.cefet.dataEscola.dto.AtividadeResponseDTO;
import com.cefet.dataEscola.services.AtividadeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public ResponseEntity<List<AtividadeResponseDTO>> findAll() {
        return ResponseEntity.ok(atividadeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeResponseDTO> findById(@PathVariable Long id) {
        AtividadeResponseDTO dto = atividadeService.findById(id);
        return ResponseEntity.ok(dto);
    }
    
    @PostMapping
    public ResponseEntity<AtividadeResponseDTO> create(
            @Valid @RequestBody AtividadeRequestDTO dto) {

        AtividadeResponseDTO criado = atividadeService.create(dto);
        return ResponseEntity.status(201).body(criado);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AtividadeResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody AtividadeRequestDTO dto) {

        AtividadeResponseDTO atualizado = atividadeService.update(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atividadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/aluno/{idAluno}")
    public ResponseEntity<List<AtividadeResponseDTO>> findByAlunoId(
            @PathVariable Long idAluno) {
        
        return ResponseEntity.ok(atividadeService.findByAlunoId(idAluno));
    }  

}