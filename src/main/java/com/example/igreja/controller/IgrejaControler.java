package com.example.igreja.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.igreja.dto.IgrejaDTO;
import com.example.igreja.dto.converter.IgrejaConverterDTO;
import com.example.igreja.model.Igreja;
import com.example.igreja.repository.IgrejaRepository;
import com.example.igreja.service.IgrejaService;

@RequestMapping("/igrejas")
@RestController
public class IgrejaControler {
	
	@Autowired
	private IgrejaRepository igrejaRepository;
	
	@Autowired
	private IgrejaService IgrejaService;
	
	@Autowired
	private IgrejaConverterDTO igrejaConverterDTO;
	
	@GetMapping
	public List<IgrejaDTO> listarTodos() {
		List<Igreja> igrejas = igrejaRepository.findAll();
		return igrejaConverterDTO.collectionIgrejaDTO(igrejas);
	}
	
	@GetMapping("/todos")
	public Set<Igreja> todos() {
		return igrejaRepository.listarTodos();
	}
	
	
	@PostMapping
	public ResponseEntity<IgrejaDTO> adicionar(@Valid @RequestBody Igreja igreja) {
		Igreja igrejaSalva = IgrejaService.salvar(igreja);
		return ResponseEntity.status(HttpStatus.CREATED).body(igrejaConverterDTO.igrejaConverterDTO(igrejaSalva));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<IgrejaDTO> atualizar(@Valid @PathVariable Long id, @RequestBody Igreja igreja) {
		Igreja igrejaAtualizada = IgrejaService.atualizar(id, igreja);
		return ResponseEntity.ok(igrejaConverterDTO.igrejaConverterDTO(igrejaAtualizada));
	}
	
}
