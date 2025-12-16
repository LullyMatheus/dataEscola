package com.cefet.dataEscola.dto;
import com.cefet.dataEscola.entities.Atividade;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AtividadeRequestDTO {

    private Long id;

    @NotNull(message = "É obrigatório informar uma descrição para atividade.")
    @Size(max=200, message = "Máximo 200 caracteres")
    private String descricao;

    @Size(max=300, message = "Máximo 300 caracteres")
    private String observacao;

    @NotNull(message = "O status da atividade é obrigatório.")
    private String status;  

    @NotNull(message = "O campo 'idAluno' é obrigatório.")
    private Long idAluno;

    public AtividadeRequestDTO(Atividade atividade) {
        this.id = atividade.getId();
        this.descricao = atividade.getDescricao();
        this.observacao = atividade.getObservacao();
        this.status = atividade.getStatusAtividade().name();
        this.idAluno = atividade.getAluno().getId();
    }

    public AtividadeRequestDTO() {
    }

    public Long getId() {
        return id;
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