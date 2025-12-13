package com.cefet.dataEscola.dto;

import java.time.LocalDate;
import java.util.List;
import com.cefet.dataEscola.entities.Atividade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AlunoRequestDTO {

    private Long id;

    @NotBlank(message = "O campo 'nome' é obrigatório.")
    @Size(max=200, message = "O nome não pode ter mais de 200 caracteres")
    private String nome;

    @NotBlank(message = "O campo 'matricula' é obrigatório.")
    @Size(max=11, message = "A matricula não pode ter mais de 11 caracteres")
    private String matricula;

    private LocalDate dataNascimento;

    @NotBlank(message = "O campo 'email' é obrigatório.")
    @Size(max=200, message = "O email não pode ter mais de 200 caracteres")
    @Email(message = "O formato do e-mail é inválido")
    private String email;

    private String contatos;

    public Long getId() {
        return id;
    }
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public AlunoRequestDTO(){
    }

    public AlunoRequestDTO(
            @NotBlank(message = "O campo 'nome' é obrigatório.") @Size(max = 200, message = "O nome não pode ter mais de 200 caracteres") String nome,
            @NotBlank(message = "O campo 'dataNascimento' é obrigatório.") LocalDate dataNascimento,
            @NotBlank(message = "O campo 'email' é obrigatório.") @Size(max = 200, message = "O email não pode ter mais de 200 caracteres") @Email(message = "O formato do e-mail é inválido") String email,
            String contatos) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.contatos = contatos;
    }
    
}
