package com.cefet.dataEscola.dto;
import com.cefet.dataEscola.entities.Usuario;

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    
    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
    }

    public UsuarioResponseDTO() {
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

}