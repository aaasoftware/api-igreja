package com.example.igreja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.igreja.model.Historia;
import com.example.igreja.repository.queries.HistoriaRepositoryQueries;

@Repository
public interface HistoriaRepository extends JpaRepository<Historia, Long>, HistoriaRepositoryQueries {

}
