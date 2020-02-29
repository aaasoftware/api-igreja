package com.example.igreja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.igreja.model.Pastoral;

@Repository
public interface PastoralRepository extends JpaRepository<Pastoral, Long>{

}
