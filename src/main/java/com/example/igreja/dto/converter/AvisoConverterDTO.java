package com.example.igreja.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.igreja.dto.AvisoDTO;
import com.example.igreja.model.Aviso;

@Component
public class AvisoConverterDTO {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AvisoDTO converterAvisoParaAvisoDTO(Aviso aviso) {
		return modelMapper.map(aviso, AvisoDTO.class);
	}
	
	public List<AvisoDTO> converterListAvisoParaAvisoDTO(List<Aviso> avisos) {
		return avisos.stream()
				.map(aviso -> converterAvisoParaAvisoDTO(aviso))
				.collect(Collectors.toList());
	}
	
	
	
}
