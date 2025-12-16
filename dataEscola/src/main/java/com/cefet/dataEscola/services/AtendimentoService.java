package com.cefet.dataEscola.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cefet.dataEscola.dto.AtendimentoRequestDTO;
import com.cefet.dataEscola.dto.AtendimentoResponseDTO;
import com.cefet.dataEscola.entities.Aluno;
import com.cefet.dataEscola.entities.Usuario;
import com.cefet.dataEscola.entities.Atendimento;
import com.cefet.dataEscola.repositories.AlunoRepository;
import com.cefet.dataEscola.repositories.AtendimentoRepository;
import com.cefet.dataEscola.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AtendimentoService {

    private final AlunoRepository alunoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    public AtendimentoService(
        AtendimentoRepository atendimentoRepository,
        AlunoRepository alunoRepository,
        UsuarioRepository usuarioRepository
    ) {
        this.atendimentoRepository = atendimentoRepository;
        this.alunoRepository = alunoRepository;
        this.usuarioRepository = usuarioRepository;
    }

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

    public List<AtendimentoResponseDTO> buscarPorNomeDoAluno(String nome) {
        // Se eu quiser acrescentar uma regra de negocio, ela fica aqui
        List<Atendimento> atendimentos = atendimentoRepository.findByAlunoNomeContainingIgnoreCase(nome);        List<AtendimentoResponseDTO> dtos = atendimentos.stream()
        .map(AtendimentoResponseDTO::new)
        .collect(Collectors.toList());
        return dtos;
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

        //os parametros abaixo devem ser passados para a API no momento de cadastrar um novo atendimento
        
        atendimento.setDescricao(atendimentoRequestDTO.getDescricao());
    	atendimento.setDataAtendimento(atendimentoRequestDTO.getDataAtendimento());
        atendimento.setDataLembrete(atendimentoRequestDTO.getDataLembrete());
        atendimento.setSituacao(atendimentoRequestDTO.getSituacao());

        Long idAluno = atendimentoRequestDTO.getIdAluno();
        Aluno aluno = alunoRepository.findById(idAluno)
            .orElseThrow(() -> new EntityNotFoundException("Aluno não localizado com ID: " + idAluno));

        Long idUsuario = atendimentoRequestDTO.getIdUsuario();
        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new EntityNotFoundException("Usuario não localizado com ID: " + idUsuario));

        atendimento.setAluno(aluno);
        atendimento.setUsuario(usuario);
    	
    	Atendimento atendimentoSalvo = atendimentoRepository.save(atendimento);
        return new AtendimentoResponseDTO(atendimentoSalvo);
    }

    //Excluir por ID
    public void delete(Long id){
        atendimentoRepository.deleteById(id);
    }

    //verificar id
	public boolean existsById(Long id) {
	    return atendimentoRepository.existsById(id);
	}

    public List<AtendimentoResponseDTO> buscarPorAlunoId(Long alunoId) {
    return atendimentoRepository
            .findByAlunoId(alunoId)
            .stream()
            .map(AtendimentoResponseDTO::new)
            .toList();
    }

}