package com.example.igreja.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.igreja.model.Aviso;
import com.example.igreja.repository.queries.AvisoRepositoryQueries;

public class AvisoRepositoryImpl implements AvisoRepositoryQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Aviso> listarPorIgrejaIdEPastoralId(Long igrejaId, Long pastoralId) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Aviso> criteria = builder.createQuery(Aviso.class);
		Root<Aviso> root = criteria.from(Aviso.class);
		
		root.fetch("igreja", JoinType.INNER);
		root.fetch("pastoral", JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (pastoralId == 0) {
			predicates.add(builder.and(builder.equal(root.get("igreja"), igrejaId),
					builder.and(builder.isNull(root.get("pastoral")))));
		} else {
			predicates.add(builder.and(builder.equal(root.get("igreja"), igrejaId),
					builder.and(builder.equal(root.get("pastoral"), pastoralId))));
		}
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		return manager.createQuery(criteria).getResultList();
	}

}
