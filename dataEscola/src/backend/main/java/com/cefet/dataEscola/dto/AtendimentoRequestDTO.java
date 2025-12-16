package com.cefet.dataEscola.dto;

import java.time.LocalDate;
import com.cefet.dataEscola.Enums.SituacaoAtendimento;
import com.cefet.dataEscola.entities.Aluno;
import com.cefet.dataEscola.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AtendimentoRequestDTO {

    private Long id;

    @Size(max=500, message = "A descrição não pode ter mais de 500 caracteres")
    private String descricao;

    @NotNull(message = "O campo 'dataAtendimento' é obrigatório.")
    private LocalDate dataAtendimento;

    private LocalDate dataLembrete;

    @NotNull(message = "O campo 'situação' é obrigatório.")
    private SituacaoAtendimento situacao;
    
    @NotNull(message = "O campo 'aluno' é obrigatório.")
    private Long idAluno;
    
    @NotNull(message = "O campo 'usuário' é obrigatório.")
    private Long idUsuario;

    public AtendimentoRequestDTO(){
        //Contrutor Vazio
    }

    public AtendimentoRequestDTO(
            @Size(max = 500, message = "A descrição não pode ter mais de 500 caracteres") String descricao,
            @NotBlank(message = "O campo 'dataAtendimento' é obrigatório.") LocalDate dataAtendimento,
            LocalDate dataLembrete,
            @NotBlank(message = "O campo 'situação' é obrigatório.") SituacaoAtendimento situacao,
            @NotBlank(message = "O campo 'aluno' é obrigatório.") Long idAluno,
            @NotBlank(message = "O campo 'usuário' é obrigatório.") Long idUsuario) {
        this.descricao = descricao;
        this.dataAtendimento = dataAtendimento;
        this.dataLembrete = dataLembrete;
        this.situacao = situacao;
        this.idAluno = idAluno;
        this.idUsuario = idUsuario;
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

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
