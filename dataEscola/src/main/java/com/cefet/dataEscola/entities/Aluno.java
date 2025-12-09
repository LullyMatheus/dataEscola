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

    
}
