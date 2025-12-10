package com.cefet.dataEscola.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import com.cefet.dataEscola.Enums.StatusAtividade;
import com.cefet.dataEscola.dto.AtividadeRequestDTO;
import com.cefet.dataEscola.dto.AtividadeResponseDTO;
import com.cefet.dataEscola.entities.Aluno;
import com.cefet.dataEscola.entities.Atividade;
import com.cefet.dataEscola.repositories.AlunoRepository;
import com.cefet.dataEscola.repositories.AtividadeRepository;

import jakarta.persistence.EntityNotFoundException;

public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

	@Autowired
    private AlunoRepository alunoRepository; 

    //Buscar todos
	public List<AtividadeResponseDTO> findAll() {
		List<Atividade> atividades = atividadeRepository.findAll();
		return atividades.stream().map(AtividadeResponseDTO::new).toList();
	}

    // Buscar por id
	public AtividadeResponseDTO findById(Long id) {
		Atividade atividade = atividadeRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Atividade não localizada com ID: " + id));

		return new AtividadeResponseDTO(atividade);
	}

	// Excluir por id
	public void delete(Long id) {
        if (!atividadeRepository.existsById(id)) {
            throw new EntityNotFoundException("Atividade não localizado com ID: " + id);
        }		
		atividadeRepository.deleteById(id);
	}
    
	//verificar id
	public boolean existsById(Long id) {
	    return atividadeRepository.existsById(id);
	}

	public AtividadeResponseDTO save(AtividadeRequestDTO dto) {

        Atividade atividade;

        if (dto.getId() != null) {
            // UPDATE
            atividade = atividadeRepository.findById(dto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Atividade não encontrada para atualização"));
        } else {
            // CREATE
            atividade = new Atividade();
        }

        // copiar atributos
        atividade.setDescricao(dto.getDescricao());
        atividade.setObservacao(dto.getObservacao());
        atividade.setStatusAtividade(StatusAtividade.valueOf(dto.getStatus().toUpperCase())
);


        // vincular aluno
        Aluno aluno = alunoRepository.findById(dto.getIdAluno())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + dto.getIdAluno()));

        atividade.setAluno(aluno);

        atividadeRepository.save(atividade);

        return new AtividadeResponseDTO(atividade);
    }

}