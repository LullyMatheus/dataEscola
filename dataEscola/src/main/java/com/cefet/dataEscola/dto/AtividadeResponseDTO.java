package com.cefet.dataEscola.dto;

import com.cefet.dataEscola.entities.Atividade;

public class AtividadeResponseDTO {

    private Long id;
    private String alunoNome;
    private String descricao;
    private String observacao;
    private String status;  
    private Long idAluno;

    public AtividadeResponseDTO() {
    }

    public AtividadeResponseDTO(Atividade atividade) {
        this.id = atividade.getId();
        this.alunoNome = atividade.getAluno().getNome();   
        this.descricao = atividade.getDescricao();
        this.observacao = atividade.getObservacao();
        this.status = atividade.getStatusAtividade().name();
        this.idAluno = atividade.getAluno().getId();
    }

    public Long getId() {
        return id;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public String getStatus() {
        return status;
    }

    public Long getIdAluno() {
        return idAluno;
    }

}