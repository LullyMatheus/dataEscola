package com.cefet.dataEscola.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cefet.dataEscola.dto.AlunoResponseDTO;
import com.cefet.dataEscola.dto.AlunoRequestDTO;
import com.cefet.dataEscola.services.AlunoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/alunos")

public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> findAll(){
        List<AlunoResponseDTO> alunosResponseDTO = alunoService.findAll();
        return ResponseEntity.ok(alunosResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AlunoResponseDTO>> findById(@PathVariable Long id){
        Optional<AlunoResponseDTO> alunoResponseDTO = alunoService.findById(id);
        return ResponseEntity.ok(alunoResponseDTO);         
    } 

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<AlunoResponseDTO>> buscarPorNome(@PathVariable String nome) {
        List<AlunoResponseDTO> alunos = alunoService.buscarPorNome(nome);

        if (alunos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alunos);
    }


    @PostMapping
    public ResponseEntity<AlunoResponseDTO> create(@Valid @RequestBody AlunoRequestDTO alunoRequestDTO){
        AlunoResponseDTO alunoResponseDTO = alunoService.save(alunoRequestDTO);
        return ResponseEntity.status(201).body(alunoResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> update(@PathVariable Long id,@Valid @RequestBody AlunoRequestDTO alunoRequestDTO){
        // Lança exceção se o ID estiver ausente
        if (alunoRequestDTO.getId() == null) {
            throw new IllegalArgumentException("O campo 'id' é obrigatório para atualização.");
        }

        // Lança exceção se o ID do path for diferente do ID do DTO
        if (!id.equals(alunoRequestDTO.getId())) {
            throw new IllegalArgumentException("O ID informado na URL é diferente do ID do corpo da requisição.");
        }
        
        // Lança exceção se o ID não existir
        if (!alunoService.existsById(id)) {
            throw new EntityNotFoundException("Aluno com ID " + id + " não localizado.");
        }           
       
        AlunoResponseDTO alunoResponseDTO = alunoService.save(alunoRequestDTO);
        return ResponseEntity.ok(alunoResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alunoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
