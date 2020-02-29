package com.example.igreja.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.igreja.exception.EntidadeNaoEncontrada;
import com.example.igreja.model.Igreja;
import com.example.igreja.model.IgrejaPastoral;
import com.example.igreja.model.Pastoral;
import com.example.igreja.repository.IgrejaPastoralRepository;
import com.example.igreja.repository.IgrejaRepository;
import com.example.igreja.repository.PastoralRepository;

@Service
public class PastoralService {
	
	@Autowired
	private PastoralRepository pastoralRepository;
	
	@Autowired
	private IgrejaPastoralRepository igrejaPastoralRepository;
	
	@Autowired
	private IgrejaRepository igrejaRepository;
	
	@Transactional
	public Pastoral salvar(Pastoral pastoral) {
		System.out.println(pastoral);
		Optional<Igreja> igrejaRetornada = igrejaRepository.findById(1L);
		
		Pastoral pastoralSalva = pastoralRepository.save(pastoral);
		
		IgrejaPastoral igrejaPastoral = new IgrejaPastoral();
		igrejaPastoral.setIgreja(igrejaRetornada.get());
		igrejaPastoral.setPastoral(pastoralSalva);
		
		igrejaPastoralRepository.save(igrejaPastoral);
		
		return pastoralSalva;
	}
	
	public List<Pastoral> listar() {
		return pastoralRepository.findAll();
	}
	
	public Pastoral buscarPorId(Long id) {
		return pastoralRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontrada("Entidade não encontrada"));
	}
	
	@Transactional
	public Pastoral editar(Long id, Pastoral pastoral) {
		Pastoral pastoralRetornada = buscarPorId(id);
		
		BeanUtils.copyProperties(pastoral, pastoralRetornada, "id", "dataCadastro");
		Pastoral pastoralSalva = pastoralRepository.save(pastoralRetornada);
		return pastoralSalva;
	}
	
}
