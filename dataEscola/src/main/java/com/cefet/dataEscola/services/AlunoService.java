package com.cefet.dataEscola.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cefet.dataEscola.entities.Aluno;
import com.cefet.dataEscola.repositories.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    //Buscar todos
    public List<Aluno> findAll(){
        return alunoRepository.findAll();        
    }

    //Buscar por ID
    public Optional<Aluno> findById(Long id) {
        return alunoRepository.findById(id);
    }
    
    //Salvar ou atualizar
    public Aluno save(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    //Excluir por ID
    public void delete(Long id){
        alunoRepository.deleteById(id);
    }
}
