package com.cefet.dataEscola.dto;

import java.time.LocalDate;
import com.cefet.dataEscola.Enums.SituacaoAtendimento;
import com.cefet.dataEscola.entities.Aluno;
import com.cefet.dataEscola.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AtendimentoRequestDTO {

    private Long id;

    @Size(max=500, message = "A descrição não pode ter mais de 500 caracteres")
    private String descricao;

    @NotBlank(message = "O campo 'dataAtendimento' é obrigatório.")
    private LocalDate dataAtendimento;

    private LocalDate dataLembrete;

    @NotBlank(message = "O campo 'situação' é obrigatório.")
    private SituacaoAtendimento situacao;
    
    @NotBlank(message = "O campo 'aluno' é obrigatório.")
    private Aluno aluno;
    
    @NotBlank(message = "O campo 'usuário' é obrigatório.")
    private Usuario usuario;

    public AtendimentoRequestDTO(){
        //Contrutor Vazio
    }


    public AtendimentoRequestDTO(
            @Size(max = 500, message = "A descrição não pode ter mais de 500 caracteres") String descricao,
            @NotBlank(message = "O campo 'dataAtendimento' é obrigatório.") LocalDate dataAtendimento,
            LocalDate dataLembrete,
            @NotBlank(message = "O campo 'situação' é obrigatório.") SituacaoAtendimento situacao,
            @NotBlank(message = "O campo 'aluno' é obrigatório.") Aluno aluno,
            @NotBlank(message = "O campo 'usuário' é obrigatório.") Usuario usuario) {
        this.descricao = descricao;
        this.dataAtendimento = dataAtendimento;
        this.dataLembrete = dataLembrete;
        this.situacao = situacao;
        this.aluno = aluno;
        this.usuario = usuario;
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
