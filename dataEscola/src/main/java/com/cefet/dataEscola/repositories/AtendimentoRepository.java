package com.cefet.dataEscola.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cefet.dataEscola.entities.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    // Preciso disso para conseguir filtrar os atendimentos pelo nome do aluno
    List<Atendimento> findByAlunoNome(String nome);
    
    // Tambem possivel buscar por parte do nome (LIKE)
    List<Atendimento> findByAlunoNomeContainingIgnoreCase(String nome);

    //Busca por Id
    List<Atendimento> findByAlunoId(Long alunoId);

    // Busca onde dataAtendimento está entre dataInicio E dataFim
    List<Atendimento> findByDataAtendimentoBetween(LocalDate inicio, LocalDate fim);

    
}
