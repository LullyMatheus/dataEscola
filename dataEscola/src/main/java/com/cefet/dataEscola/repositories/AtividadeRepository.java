package com.cefet.dataEscola.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.dataEscola.entities.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long>{
    List<Atividade> findByAlunoId(Long idAluno);

}