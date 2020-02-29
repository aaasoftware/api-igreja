package com.example.igreja.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.igreja.dto.AvisoDTO;
import com.example.igreja.dto.converter.AvisoConverterDTO;
import com.example.igreja.model.Aviso;
import com.example.igreja.service.AvisoService;

@RequestMapping("/avisos")
@RestController
public class AvisoController {
	
	@Autowired
	private AvisoService avisoService;
	
	@Autowired
	private AvisoConverterDTO avisoConverterDTO;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AvisoDTO salvar(@RequestBody Aviso aviso, @RequestParam Long igrejaId, @RequestParam Long pastoralId) {
		Aviso avisoSalvo = avisoService.adiconar(aviso, igrejaId, pastoralId);
		return avisoConverterDTO.converterAvisoParaAvisoDTO(avisoSalvo);
	}
	
	@GetMapping
	public List<AvisoDTO> listarTodos(@RequestParam Long igrejaId, @RequestParam Long pastoralId) {
		List<Aviso> avisos = avisoService.listarTodos(igrejaId, pastoralId);
		List<AvisoDTO> converterListAvisoParaAvisoDTO = avisoConverterDTO.converterListAvisoParaAvisoDTO(avisos);
		return converterListAvisoParaAvisoDTO;
	}
	
	@GetMapping("/{id}")
	public AvisoDTO buscarPorId(@PathVariable Long id) {
		Aviso avisoRetornado = avisoService.buscarPorId(id);
		return avisoConverterDTO.converterAvisoParaAvisoDTO(avisoRetornado);
	}
	
	@PutMapping("/{id}")
	public AvisoDTO atualizar(@PathVariable Long id, @RequestBody Aviso aviso) {
		Aviso avisoRetornado = avisoService.editar(id, aviso);
		return avisoConverterDTO.converterAvisoParaAvisoDTO(avisoRetornado);
	}
	
	
	
}
