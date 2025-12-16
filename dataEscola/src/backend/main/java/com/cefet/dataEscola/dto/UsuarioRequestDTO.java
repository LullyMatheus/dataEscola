package com.cefet.dataEscola.dto;

import com.cefet.dataEscola.Enums.TipoUsuario;
import com.cefet.dataEscola.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UsuarioRequestDTO {

    private Long id;    

    @NotBlank(message = "O campo 'nome' é obrigatório.")
    @Size(max = 200, message = "O nome não pode ter mais de 200 caracteres.")
    private String nome;

    @NotBlank(message = "O campo 'cpf' é obrigatório.")
    @Size(min = 11, max = 14, message = "O CPF deve ter entre 11 e 14 caracteres.")
    private String cpf; 

    @NotBlank(message = "O campo 'email' é obrigatório.")
    @Size(max=200, message = "O email não pode ter mais de 200 caracteres")
    @Email(message = "O formato do e-mail é inválido")
    private String email;

    @Size(min = 3, message = "A senha deve conter no mínimo 3 caracteres.")
    private String senha;

    @NotNull(message = "O tipo do usuário é obrigatório.")
    private TipoUsuario tipoUsuario;     
    
    public UsuarioRequestDTO() {}

    public UsuarioRequestDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.senha = usuario.getSenha();
        this.tipoUsuario = usuario.getTipo();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }  

    
    
}