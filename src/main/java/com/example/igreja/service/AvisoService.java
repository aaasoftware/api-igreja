package com.example.igreja.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.igreja.exception.EntidadeNaoEncontrada;
import com.example.igreja.model.Aviso;
import com.example.igreja.model.Igreja;
import com.example.igreja.model.Pastoral;
import com.example.igreja.repository.AvisoRepository;
import com.example.igreja.repository.IgrejaRepository;
import com.example.igreja.repository.PastoralRepository;

@Service
public class AvisoService {
	
	@Autowired
	private AvisoRepository avisoRepository;
	
	@Autowired
	private IgrejaRepository igrejaRepository;
	
	@Autowired
	private PastoralRepository pastoralRepository;
	
	@Transactional
	public Aviso adiconar(Aviso aviso, Long igrejaId, Long pastoralId) {
		aviso.setData(OffsetDateTime.now());
		
		Igreja igrejaRetornada = igrejaRepository.findById(igrejaId).orElseThrow(() ->
			new EntidadeNaoEncontrada("Entidade não encontrada"));
		
		Optional<Pastoral> pastoral = pastoralRepository.findById(pastoralId);
		
		if (pastoral.isEmpty()) {
			aviso.setPastoral(null);
		} else {
			aviso.setPastoral(pastoral.get());
		}
		
		aviso.setIgreja(igrejaRetornada);
		return avisoRepository.save(aviso);
	}
	
	public List<Aviso> listarTodos(Long igrejaId, Long pastoralId) {
		return avisoRepository.listarPorIgrejaIdEPastoralId(igrejaId, pastoralId);
	}

	public Aviso buscarPorId(Long id) {
		return avisoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontrada("Entidade não encontrada"));
	}
	
	@Transactional
	public Aviso editar(Long id, Aviso aviso) {
		Aviso avisoRetornado = buscarPorId(id);
		BeanUtils.copyProperties(aviso, avisoRetornado, "id");
		Aviso avisoSalvo = avisoRepository.save(avisoRetornado);
		return avisoSalvo;
	}
}
