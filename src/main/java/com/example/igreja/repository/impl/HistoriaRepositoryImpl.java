package com.example.igreja.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.igreja.model.Historia;
import com.example.igreja.model.Igreja;
import com.example.igreja.model.Pastoral;
import com.example.igreja.repository.queries.HistoriaRepositoryQueries;

public class HistoriaRepositoryImpl implements HistoriaRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Historia> filtrarPorIgrejaAndPastoral(Long igrejaId, Long pastoralId) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Historia> criteria = builder.createQuery(Historia.class);
		Root<Historia> root = criteria.from(Historia.class);
		
		Fetch<Historia, Igreja> igreja = root.fetch("igreja", JoinType.INNER);
		Fetch<Historia, Pastoral> pastoral = root.fetch("pastoral", JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<>();
		if(pastoralId == 0) {
			predicates.add(builder.and(builder.equal(root.get("igreja"), igrejaId),
					builder.and(builder.isNull(root.get("pastoral")))));
		} else {
			predicates.add(builder.and(builder.equal(root.get("igreja"), 
					igrejaId),builder.and(builder.equal(root.get("pastoral"), pastoralId))));
		}
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		
		return manager.createQuery(criteria).getResultList();
	}

}
