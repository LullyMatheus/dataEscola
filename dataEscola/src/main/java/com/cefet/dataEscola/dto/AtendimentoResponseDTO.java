package com.cefet.dataEscola.dto;

import java.time.LocalDate;
import com.cefet.dataEscola.Enums.SituacaoAtendimento;
import com.cefet.dataEscola.entities.Aluno;
import com.cefet.dataEscola.entities.Atendimento;
import com.cefet.dataEscola.entities.Usuario;

public class AtendimentoResponseDTO {

    private Long id;

    private String descricao;

    private LocalDate dataAtendimento;

    private LocalDate dataLembrete;

    private SituacaoAtendimento situacao;

    private Aluno aluno;

    private Usuario usuario;

    
    public AtendimentoResponseDTO(){
        //construtor vazio, eh util em alguns casos
    }

    //Construtor utilitario
    //Isso serve para converter a entidade atendimento (com todos os seus stributos) em um DTO
    //que possui apenas os atributos que queremos
    public AtendimentoResponseDTO(Atendimento atendimento){
        this.id = atendimento.getId();
        this.descricao = atendimento.getDescricao();
        this.dataAtendimento = atendimento.getDataAtendimento();
        this.dataLembrete = atendimento.getDataLembrete();
        this.situacao = atendimento.getSituacao();
        this.aluno = atendimento.getAluno();
        this.usuario = atendimento.getUsuario();
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
