package com.cefet.dataEscola.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cefet.dataEscola.entities.Atendimento;
import com.cefet.dataEscola.services.AtendimentoService;
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
    public ResponseEntity<List<Atendimento>> findAll(){
        List<Atendimento> atendimento = atendimentoService.findAll();
        return ResponseEntity.ok(atendimento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Atendimento>> findById(@PathVariable Long id){
        Optional<Atendimento> atendimento = atendimentoService.findById(id);
        return ResponseEntity.ok(atendimento);         
    } 

    @PostMapping
    public ResponseEntity<Atendimento> create(@RequestBody Atendimento atendimento){
        Atendimento objeto = atendimentoService.save(atendimento);
        return ResponseEntity.status(201).body(objeto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendimento> update(@PathVariable Long id, @RequestBody Atendimento atendimento){
        if(!id.equals(atendimento.getId())){
            return ResponseEntity.badRequest().build();
        }

        Atendimento atualizado = atendimentoService.save(atendimento);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        atendimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
