package com.cefet.dataEscola.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cefet.dataEscola.entities.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    
}
