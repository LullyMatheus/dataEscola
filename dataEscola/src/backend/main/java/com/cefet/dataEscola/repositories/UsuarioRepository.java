package com.cefet.dataEscola.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cefet.dataEscola.entities.Usuario;
import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    boolean existsByCpf(String cpf);
    Optional<Usuario>findByCpfAndSenha(String cpf, String senha);
}