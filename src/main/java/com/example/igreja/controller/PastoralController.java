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
import org.springframework.web.bind.annotation.RestController;

import com.example.igreja.dto.PastoralDTO;
import com.example.igreja.dto.converter.PastoralConverterDTO;
import com.example.igreja.model.Pastoral;
import com.example.igreja.service.PastoralService;

@RequestMapping("/pastorais")
@RestController
public class PastoralController {
	
	@Autowired
	private PastoralService pastoralService;
	
	@Autowired
	private PastoralConverterDTO pastoralConverterDTO;
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Pastoral pastoral) {
		Pastoral pastoralSalva = pastoralService.salvar(pastoral);
		return ResponseEntity.status(HttpStatus.CREATED).body(pastoralConverterDTO.converterPastoralParaPastoralDTO(pastoralSalva));
	}
	
	@GetMapping
	public List<PastoralDTO> listarTodos() {
		List<Pastoral> pastorais = pastoralService.listar();
		return pastoralConverterDTO.converterListPastoralParaPastoralDTO(pastorais);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
		Pastoral pastoralRetornada = pastoralService.buscarPorId(id);
		return ResponseEntity.ok(pastoralConverterDTO.converterPastoralParaPastoralDTO(pastoralRetornada));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Pastoral pastoral) {
		Pastoral pastoralRetornada = pastoralService.editar(id, pastoral);
		return ResponseEntity.ok(pastoralConverterDTO.converterPastoralParaPastoralDTO(pastoralRetornada));
	}
	
}
