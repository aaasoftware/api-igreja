package com.example.igreja.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.igreja.model.Igreja;

@Repository
public interface IgrejaRepository extends JpaRepository<Igreja, Long>{
	
	@Query("from Igreja i left join fetch i.pastorais ORDER BY i.id")
	Set<Igreja> listarTodos();
}
