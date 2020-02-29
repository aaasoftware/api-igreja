package com.example.igreja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.igreja.model.Aviso;
import com.example.igreja.repository.queries.AvisoRepositoryQueries;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Long>, AvisoRepositoryQueries{

}
