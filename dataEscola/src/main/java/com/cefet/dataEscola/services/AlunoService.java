package com.cefet.dataEscola.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cefet.dataEscola.dto.AlunoRequestDTO;
import com.cefet.dataEscola.dto.AlunoResponseDTO;
import com.cefet.dataEscola.entities.Aluno;
import com.cefet.dataEscola.repositories.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    //Buscar todos
    public List<AlunoResponseDTO> findAll(){
        List<Aluno> alunos = alunoRepository.findAll();
		return alunos.stream().map(AlunoResponseDTO::new).toList();     
    }

    //Buscar por ID
    public Optional<AlunoResponseDTO> findById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Aluno não localizado com ID: " + id));
		
		return Optional.of(new AlunoResponseDTO(aluno));
    }
    
    //Salvar ou atualizar
    public AlunoResponseDTO save(AlunoRequestDTO alunoRequestDTO){
        Aluno aluno = null;
		
		if (alunoRequestDTO.getId() == null) {
			aluno = new Aluno();
    	}else {
    		aluno = alunoRepository.findById(alunoRequestDTO.getId())
    			.orElseThrow(() -> new EntityNotFoundException("Aluno não localizado com ID: " + alunoRequestDTO.getId()));
    	
            }
            
        //os parametros abaixo devem ser passados para a API no momento de cadastrar um novo aluno		
        
    	aluno.setNome(alunoRequestDTO.getNome());
        aluno.setMatricula(alunoRequestDTO.getMatricula());
        aluno.setDataNascimento(alunoRequestDTO.getDataNascimento());
        aluno.setContatos(alunoRequestDTO.getContatos());
        aluno.setEmail(alunoRequestDTO.getEmail());
        aluno.setId(alunoRequestDTO.getId());
    	
    	Aluno alunoSalvo = alunoRepository.save(aluno);
        return new AlunoResponseDTO(alunoSalvo);
    }

    //Excluir por ID
    public void delete(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new EntityNotFoundException("Aluno não localizado com ID: " + id);
        }		
		alunoRepository.deleteById(id);
	}
    
	//verificar id
	public boolean existsById(Long id) {
	    return alunoRepository.existsById(id);
	}	

    //buscar por nome
    public List<AlunoResponseDTO> buscarPorNome(String nome) {
    return alunoRepository
        .findByNomeContainingIgnoreCase(nome)
        .stream()
        .map(this::toDTO)
        .toList();
    }

        private AlunoResponseDTO toDTO(Aluno aluno) {
        return new AlunoResponseDTO(
                aluno.getId(),
                aluno.getMatricula(),
                aluno.getNome(),
                aluno.getDataNascimento(),
                aluno.getEmail(),
                aluno.getContatos()
        );
    }
}
