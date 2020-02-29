package com.example.igreja.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.igreja.exception.EntidadeNaoEncontrada;
import com.example.igreja.model.Historia;
import com.example.igreja.model.Igreja;
import com.example.igreja.model.Pastoral;
import com.example.igreja.repository.HistoriaRepository;
import com.example.igreja.repository.IgrejaRepository;
import com.example.igreja.repository.PastoralRepository;

@Service
public class HistoriaService {
	
	@Autowired
	private IgrejaRepository IgrejaRepository;
	
	@Autowired
	private PastoralRepository pastoralRepository;
	
	@Autowired
	private HistoriaRepository historiaRepository;
	
	@Transactional
	public Historia adicionar(Historia historia, Long igrejaId, Long pastoralId) {
		Igreja igrejaRetornada = IgrejaRepository.findById(igrejaId).orElseThrow(() 
				-> new EntidadeNaoEncontrada("Entidade não encontrada"));
		
		Pastoral pastoralRetornada = pastoralRepository.findById(pastoralId).orElseThrow(() 
				-> new EntidadeNaoEncontrada("Entidade não encontrada"));
		
		historia.setIgreja(igrejaRetornada);
		historia.setPastoral(pastoralRetornada);
		
		return historiaRepository.save(historia);
	}
	
	public List<Historia> listar(Long igrejaId, Long pastoralId) {
		return historiaRepository.filtrarPorIgrejaAndPastoral(igrejaId, pastoralId);
	}
	
	public Historia buscarPorId(Long id) {
		return historiaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontrada("Entidade não encontrada"));
	}
	
	@Transactional
	public Historia atualizar(Long id, Historia historia) {
		Historia historiaRetornada = buscarPorId(id);
		BeanUtils.copyProperties(historia, historiaRetornada, "id");
		Historia historiaSalva = historiaRepository.save(historiaRetornada);
		return historiaSalva;
	}
}
