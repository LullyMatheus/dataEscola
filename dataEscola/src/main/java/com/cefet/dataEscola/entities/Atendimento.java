package com.cefet.dataEscola.entities;

import java.time.LocalDate;

import com.cefet.dataEscola.Enums.SituacaoAtendimento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_atendimento")
public class Atendimento {

    //id descricao dataAtendimento dataLembrete situacao idAluno idUsuario

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 400)
    private String descricao;

    @Column(nullable = false, length = 10)
    private LocalDate dataAtendimento;

    @Column(nullable = false, length = 10)
    private LocalDate dataLembrete;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private SituacaoAtendimento situacao;

    @OneToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Atendimento(){
        
    }

    public Atendimento(LocalDate dataAtendimento, LocalDate dataLembrete, SituacaoAtendimento situacao, Aluno aluno) {
        this.dataAtendimento = dataAtendimento;
        this.dataLembrete = dataLembrete;
        this.situacao = situacao;
        this.aluno = aluno;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public LocalDate getDataLembrete() {
        return dataLembrete;
    }

    public void setDataLembrete(LocalDate dataLembrete) {
        this.dataLembrete = dataLembrete;
    }

    public SituacaoAtendimento getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoAtendimento situacao) {
        this.situacao = situacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
