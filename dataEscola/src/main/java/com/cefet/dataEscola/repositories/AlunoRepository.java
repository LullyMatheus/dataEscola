package com.cefet.dataEscola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cefet.dataEscola.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
}
