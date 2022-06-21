package com.pruebaprimos.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebaprimos.app.entity.Arrays;

@Repository
public interface ArraysRepository extends JpaRepository<Arrays, Long>{

}
