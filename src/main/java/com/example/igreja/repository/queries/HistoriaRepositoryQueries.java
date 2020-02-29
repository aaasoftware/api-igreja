package com.example.igreja.repository.queries;

import java.util.List;

import com.example.igreja.model.Historia;

public interface HistoriaRepositoryQueries {
	
	List<Historia> filtrarPorIgrejaAndPastoral(Long igrejaId, Long pastoralId);
}
