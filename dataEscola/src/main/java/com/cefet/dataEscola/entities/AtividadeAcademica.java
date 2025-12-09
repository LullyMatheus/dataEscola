package com.cefet.dataEscola.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_atividadeAcademica")
public class AtividadeAcademica {
    //id, descricao, observacao, situacao, id_aluno(fk), id_usuario(fk)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String descricao;
    
    @Column(length = 300)
    private String observacao;

    @Column(length = 200)
    private String situacao;

    //Uma atividade acadêmica é referente a um aluno, um aluno possui várias atividades academicas
    @ManyToOne 
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;

    //Falta relacionamento com Usuário

    

}
