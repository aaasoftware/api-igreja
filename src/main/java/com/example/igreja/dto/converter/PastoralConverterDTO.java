package com.example.igreja.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.igreja.dto.PastoralDTO;
import com.example.igreja.model.Pastoral;

@Component
public class PastoralConverterDTO {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public PastoralDTO converterPastoralParaPastoralDTO(Pastoral pastoral) {
		return modelMapper.map(pastoral, PastoralDTO.class);
	}
	
	public List<PastoralDTO> converterListPastoralParaPastoralDTO(List<Pastoral> pastorais) {
		return pastorais.stream()
				.map(pastoral -> converterPastoralParaPastoralDTO(pastoral))
				.collect(Collectors.toList());
	}
	
}
