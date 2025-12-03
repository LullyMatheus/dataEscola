package com.cefet.dataEscola.dto;

import com.cefet.dataEscola.entities.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class UsuarioRequestDTO {

    private Long id;    

    @NotBlank(message = "O campo 'nome' é obrigatório.")
    @Size(max = 200, message = "O nome não pode ter mais de 200 caracteres.")
    private String nome;

    @NotBlank(message = "O campo 'cpf' é obrigatório.")
    @Size(min = 11, max = 14, message = "O CPF deve ter entre 11 e 14 caracteres.")
    private String cpf; 

    @Size(min = 3, message = "A senha deve conter no mínimo 3 caracteres.")
    private String senha;

    @NotBlank(message = "O tipo do usuário é obrigatório.")
    private String tipoUsuario;     
    
    public UsuarioRequestDTO() {}

    public UsuarioRequestDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.senha = usuario.getSenha();
        this.tipoUsuario = usuario.getTipo().name();
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }  
    

}