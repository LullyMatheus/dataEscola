package com.cefet.dataEscola.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cefet.dataEscola.entities.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    // Preciso disso para conseguir filtrar os atendimentos pelo nome do aluno
    List<Atendimento> findByAlunoNome(String nome);
    
    // Tambem possivel buscar por parte do nome (LIKE)
    List<Atendimento> findByAlunoNomeContaining(String nome);
    
}
