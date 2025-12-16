package com.cefet.dataEscola.entities;

import com.cefet.dataEscola.Enums.StatusAtividade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_atividadeAcademica")
public class Atividade {
    //id, descricao, observacao, situacao, id_aluno(fk), id_usuario(fk)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String descricao;
    
    @Column(length = 300)
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusAtividade statusAtividade;    

    //Uma atividade acadêmica é referente a um aluno, um aluno possui várias atividades academicas
    @ManyToOne 
    @JoinColumn(name = "id_aluno", nullable = false)
    private Aluno aluno;

    public Atividade(Long id, String descricao, String observacao,
        StatusAtividade statusAtividade, Aluno aluno) {
        this.id = id;
        this.descricao = descricao;
        this.observacao = observacao;
        this.statusAtividade = statusAtividade;
        this.aluno = aluno;
    }

    public Atividade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusAtividade getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(StatusAtividade statusAtividade) {
        this.statusAtividade = statusAtividade;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }  

}