package com.example.igreja.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.igreja.dto.IgrejaDTO;
import com.example.igreja.model.Igreja;

@Component
public class IgrejaConverterDTO {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public IgrejaDTO igrejaConverterDTO(Igreja igreja) {
		return modelMapper.map(igreja, IgrejaDTO.class);
	}	
	
	public List<IgrejaDTO> collectionIgrejaDTO(List<Igreja> igrejas) {
		List<IgrejaDTO> igrejasDTO = igrejas.stream()
			.map(igreja -> igrejaConverterDTO(igreja))
			.collect(Collectors.toList());
		return igrejasDTO;
	}
	
}
