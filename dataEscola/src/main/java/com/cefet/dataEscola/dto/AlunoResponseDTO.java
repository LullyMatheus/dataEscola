package com.cefet.dataEscola.dto;

import java.time.LocalDate;

import com.cefet.dataEscola.entities.Aluno;

public class AlunoResponseDTO {

    private Long id;

    private String nome;

    private String matricula;

    private LocalDate dataNascimento;

    private String email;

    private String contatos;

    
    public AlunoResponseDTO(Long long1, String string, String string2, LocalDate localDate, String string3, String string4){
        //construtor vazio, eh util em alguns casos
    }
    
    //Construtor utilitario
    //Isso serve para converter a entidade aluno (com todos os seus stributos) em um DTO
    //que possui apenas os atributos que queremos
    public AlunoResponseDTO(Aluno aluno){
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.dataNascimento = aluno.getDataNascimento();
        this.email = aluno.getEmail();
        this.contatos = aluno.getContatos();
        this.matricula = aluno.getMatricula();
    }

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContatos() {
        return contatos;
    }

    public void setContatos(String contatos) {
        this.contatos = contatos;
    }

    public String getMatricula() {
        return matricula;
    }    

    
    
}
