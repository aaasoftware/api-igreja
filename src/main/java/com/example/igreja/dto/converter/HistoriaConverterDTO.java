package com.example.igreja.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.igreja.dto.HistoriaDTO;
import com.example.igreja.model.Historia;

@Component
public class HistoriaConverterDTO {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	public HistoriaDTO converterHistoriaParaHistoriaDTO(Historia historia) {
		return modelMapper.map(historia, HistoriaDTO.class);
	}
	
	public List<HistoriaDTO> converterListHistoriaParaHistoriaDTO(List<Historia> historias) {
		return historias.stream()
			.map(historia -> converterHistoriaParaHistoriaDTO(historia))
			.collect(Collectors.toList());
	}
	
}
