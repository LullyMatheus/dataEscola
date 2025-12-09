package com.cefet.dataEscola.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_aluno")
public class Aluno {
    //id, matricula, nome, dataNascimento, email, contatos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false, length = 10)
    private LocalDate dataNascimento;

    @Column(nullable = false, length = 200)
    private String email;

    @ElementCollection
    @CollectionTable(name = "aluno_contatos", joinColumns = @JoinColumn(name = "aluno_id"))
    @Column(name = "contato")
    private List<String> contatos;    
    
    //Acessar atividades academicas a partir de aluno
    @OneToMany(mappedBy = "aluno")
    private List<AtividadeAcademica> atividades;

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

    public List<String> getContatos() {
        return contatos;
    }

    public void setContatos(List<String> contatos) {
        this.contatos = contatos;
    }

    public List<AtividadeAcademica> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<AtividadeAcademica> atividades) {
        this.atividades = atividades;
    }

    public Long getId() {
        return id;
    }

    
    
}
