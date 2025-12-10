package com.cefet.dataEscola.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cefet.dataEscola.dto.AtendimentoRequestDTO;
import com.cefet.dataEscola.dto.AtendimentoResponseDTO;
import com.cefet.dataEscola.entities.Atendimento;
import com.cefet.dataEscola.repositories.AtendimentoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    //Buscar todos
    public List<AtendimentoResponseDTO> findAll(){
        List<Atendimento> atendimentos = atendimentoRepository.findAll();
		return atendimentos.stream().map(AtendimentoResponseDTO::new).toList();        
    }

    //Buscar por ID
    public Optional<AtendimentoResponseDTO> findById(Long id) {
        Atendimento atendimento = atendimentoRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Atendimento não localizado com ID: " + id));
		
		return Optional.of(new AtendimentoResponseDTO(atendimento));
    }
    
    //Salvar ou atualizar
    public AtendimentoResponseDTO save(AtendimentoRequestDTO atendimentoRequestDTO){
        Atendimento atendimento = null;
		
		if (atendimentoRequestDTO.getId() == null) {
			atendimento = new Atendimento();
    	}else {
    		atendimento = atendimentoRepository.findById(atendimentoRequestDTO.getId())
    			.orElseThrow(() -> new EntityNotFoundException("Atendimento não localizado com ID: " + atendimentoRequestDTO.getId()));
    	}
		
    	atendimento.setDataAtendimento(atendimentoRequestDTO.getDataAtendimento());
        atendimento.setAluno(atendimentoRequestDTO.getAluno());
    	
    	Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);
        return new AtendimentoResponseDTO(atendimentoSalvo);
    }

    //Excluir por ID
    public void delete(Long id){
        atendimentoRepository.deleteById(id);
    }
}
