package com.cefet.dataEscola.dto;

import java.time.LocalDate;
import com.cefet.dataEscola.Enums.SituacaoAtendimento;
import com.cefet.dataEscola.entities.Atendimento;
import com.cefet.dataEscola.entities.Usuario;

public class AtendimentoResponseDTO {

    //LEMBRAR: esses atributos vao aparecer como resposta na minha requisicao JSON

    private Long id;

    private String descricao;

    private LocalDate dataAtendimento;

    private LocalDate dataLembrete;

    private SituacaoAtendimento situacao;

    private String alunoNome;

    private String usuarioNome;

    
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
        this.alunoNome = atendimento.getAluno().getNome();
        this.usuarioNome = atendimento.getUsuario().getNome();
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

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAluno(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }
    
}
