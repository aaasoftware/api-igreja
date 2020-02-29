package com.example.igreja.service;



import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.igreja.exception.EntidadeNaoEncontrada;
import com.example.igreja.exception.ViolacaoException;
import com.example.igreja.model.Igreja;
import com.example.igreja.repository.IgrejaRepository;

import org.springframework.stereotype.Service;

@Service
public class IgrejaService {
	
	@Autowired
	private IgrejaRepository igrejaRepository;

	@Transactional
	public Igreja salvar(Igreja igreja) {
		try {
			return igrejaRepository.save(igreja);
		} catch (Exception e) {
			throw new ViolacaoException();
		}
	}
	
	@Transactional
	public Igreja atualizar(Long id, Igreja igreja) {
		System.out.println(igreja);
		Igreja igrejaReOptional = igrejaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontrada("Entidade não encontrada"));
		BeanUtils.copyProperties(igreja, igrejaReOptional, "id", "dataCadastro");
		return igrejaRepository.save(igrejaReOptional);
	}

}
