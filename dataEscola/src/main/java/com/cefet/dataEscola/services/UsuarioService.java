package com.cefet.dataEscola.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cefet.dataEscola.dto.UsuarioRequestDTO;
import com.cefet.dataEscola.dto.UsuarioResponseDTO;
import com.cefet.dataEscola.entities.Usuario;
import com.cefet.dataEscola.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Buscar todos
	public List<UsuarioResponseDTO> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(UsuarioResponseDTO::new).toList();
	}

    // Buscar por id
	public Optional<UsuarioResponseDTO> findById(Long id) {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Usuario não localizado com ID: " + id));
		
		return Optional.of(new UsuarioResponseDTO(usuario));
	}

	// Salvar ou atualizar
	public UsuarioResponseDTO save(UsuarioRequestDTO usuarioRequestDTO) {
		Usuario usuario = null;
		
		if (usuarioRequestDTO.getId() == null) {
			usuario = new Usuario();
    		usuario.setSenha(usuarioRequestDTO.getSenha());
    	}else {
    		usuario = usuarioRepository.findById(usuarioRequestDTO.getId())
    				.orElseThrow(() -> new EntityNotFoundException("Usuario não localizado com ID: " + usuarioRequestDTO.getId()));
    	}
		
    	usuario.setNome(usuarioRequestDTO.getNome());
    	usuario.setCpf(usuarioRequestDTO.getCpf());
		usuario.setEmail(usuarioRequestDTO.getEmail());
		usuario.setSenha(usuarioRequestDTO.getSenha());
		usuario.setTipo(usuarioRequestDTO.getTipoUsuario());
    	
    	Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuarioSalvo);
	}

	// Excluir por id
	public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario não localizado com ID: " + id);
        }		
		usuarioRepository.deleteById(id);
	}
    
	//verificar id
	public boolean existsById(Long id) {
	    return usuarioRepository.existsById(id);
	}	
	
	//verificar CPF
	public boolean existsByCpf(String cpf) {
	    return usuarioRepository.existsByCpf(cpf);
	}
    
    // Autenticar 
	public Optional<UsuarioResponseDTO> findByCpfAndSenha(String cpf, String senha) {
	    return usuarioRepository.findByCpfAndSenha(cpf, senha)
	        .map(UsuarioResponseDTO::new)
	        .or(() -> {
	            throw new EntityNotFoundException("CPF ou senha inválidos.");
	        });
	}

}
