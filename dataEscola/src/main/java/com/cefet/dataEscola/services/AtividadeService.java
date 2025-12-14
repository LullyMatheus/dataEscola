package com.cefet.dataEscola.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cefet.dataEscola.Enums.StatusAtividade;
import com.cefet.dataEscola.dto.AtividadeRequestDTO;
import com.cefet.dataEscola.dto.AtividadeResponseDTO;
import com.cefet.dataEscola.entities.Aluno;
import com.cefet.dataEscola.entities.Atividade;
import com.cefet.dataEscola.repositories.AlunoRepository;
import com.cefet.dataEscola.repositories.AtividadeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
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
        atividade.setStatusAtividade(StatusAtividade.valueOf(dto.getStatus().toUpperCase()));
        // vincular aluno
        Aluno aluno = alunoRepository.findById(dto.getIdAluno())
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + dto.getIdAluno()));

        atividade.setAluno(aluno);

        atividadeRepository.save(atividade);

        return new AtividadeResponseDTO(atividade);
    }

    // ATUALIZAR
    public AtividadeResponseDTO update(Long id, AtividadeRequestDTO dto) {
        Atividade atividade = atividadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Atividade não encontrada"));

        atividade.setDescricao(dto.getDescricao());
        atividade.setObservacao(dto.getObservacao());
        atividade.setStatusAtividade(StatusAtividade.valueOf(dto.getStatus().toUpperCase()));
        // Verifica se mudou o aluno
        if (!atividade.getAluno().getId().equals(dto.getIdAluno())) {
            Aluno aluno = alunoRepository.findById(dto.getIdAluno())
                    .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado"));
            atividade.setAluno(aluno);
        }

        atividadeRepository.save(atividade);

        return new AtividadeResponseDTO(atividade);
    }

        // BUSCAR POR ALUNO
    public List<AtividadeResponseDTO> findByAlunoId(Long idAluno) {
        return atividadeRepository.findByAlunoId(idAluno)
                .stream()
                .map(AtividadeResponseDTO::new)
                .toList();
    }

    @Transactional
    public AtividadeResponseDTO create(AtividadeRequestDTO dto) {
        // validação simples (opcional)
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Não informe ID ao criar uma nova atividade.");
        }

        // buscar o aluno (lança EntityNotFoundException se não existir)
        Aluno aluno = alunoRepository.findById(dto.getIdAluno())
                .orElseThrow(() -> new EntityNotFoundException(
                    "Aluno não encontrado com ID: " + dto.getIdAluno()));

        // montar a entidade
        Atividade atividade = new Atividade();
        atividade.setDescricao(dto.getDescricao());
        atividade.setObservacao(dto.getObservacao());
        atividade.setStatusAtividade(StatusAtividade.valueOf(dto.getStatus().toUpperCase()));
        atividade.setAluno(aluno);

        // salvar
        atividade = atividadeRepository.save(atividade);

        // retornar DTO de resposta
        return new AtividadeResponseDTO(atividade);
    }

}