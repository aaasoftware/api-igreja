package com.example.igreja.repository.queries;

import java.util.List;

import com.example.igreja.model.Aviso;

public interface AvisoRepositoryQueries {

	List<Aviso> listarPorIgrejaIdEPastoralId(Long igrejaId, Long pastoralId);
	
}
