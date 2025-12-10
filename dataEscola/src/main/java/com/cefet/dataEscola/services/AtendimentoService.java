package com.cefet.dataEscola.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cefet.dataEscola.entities.Atendimento;
import com.cefet.dataEscola.repositories.AtendimentoRepository;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    //Buscar todos
    public List<Atendimento> findAll(){
        return atendimentoRepository.findAll();        
    }

    //Buscar por ID
    public Optional<Atendimento> findById(Long id) {
        return atendimentoRepository.findById(id);
    }
    
    //Salvar ou atualizar
    public Atendimento save(Atendimento atendimento){
        return atendimentoRepository.save(atendimento);
    }

    //Excluir por ID
    public void delete(Long id){
        atendimentoRepository.deleteById(id);
    }
}
