package com.example.igreja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.igreja.model.IgrejaPastoral;

@Repository
public interface IgrejaPastoralRepository extends JpaRepository<IgrejaPastoral, Long>{

}
