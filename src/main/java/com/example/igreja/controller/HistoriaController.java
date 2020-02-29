package com.example.igreja.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.igreja.dto.HistoriaDTO;
import com.example.igreja.dto.converter.HistoriaConverterDTO;
import com.example.igreja.model.Historia;
import com.example.igreja.service.HistoriaService;

@RestController
@RequestMapping("/historias")
public class HistoriaController {
	
	@Autowired
	private HistoriaService historiaService;
	
	@Autowired
	private HistoriaConverterDTO historiaConverterDTO;
	
	@PostMapping
	public ResponseEntity<HistoriaDTO> adicionar(@RequestBody Historia historia, @RequestParam Long igrejaId, @RequestParam Long pastoralId) {
		Historia historiaSalva = historiaService.adicionar(historia, igrejaId, pastoralId);
		return ResponseEntity.status(HttpStatus.CREATED).body(historiaConverterDTO.converterHistoriaParaHistoriaDTO(historiaSalva));
	}
	
	@GetMapping
	public List<HistoriaDTO> listarTodos(@RequestParam Long igrejaId, @RequestParam Long pastoralId) {
		List<Historia> historias = historiaService.listar(igrejaId, pastoralId);
		return historiaConverterDTO.converterListHistoriaParaHistoriaDTO(historias);
	}
	
	@GetMapping("/{id}")
	public HistoriaDTO buscarPorId(@PathVariable Long id) {
		Historia historiaRetornada = historiaService.buscarPorId(id);
		return historiaConverterDTO.converterHistoriaParaHistoriaDTO(historiaRetornada);
	}
	
	@PutMapping("/{id}")
	public HistoriaDTO atualizar(@PathVariable Long id, @RequestBody Historia historia) {
		Historia historiaAtualizada = historiaService.atualizar(id, historia);
		return historiaConverterDTO.converterHistoriaParaHistoriaDTO(historiaAtualizada);
	}
	
}
