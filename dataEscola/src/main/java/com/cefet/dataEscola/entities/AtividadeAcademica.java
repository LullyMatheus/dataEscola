package com.cefet.dataEscola.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_atividadeAcademica")
public class AtividadeAcademica {
    //id, descricao, observacao, situacao, idaluno(fk), idusuario(fk)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String descricao;
    
    @Column(length = 300)
    private String observacao;

    @Column(length = 200)
    private String situacao;

    
}
