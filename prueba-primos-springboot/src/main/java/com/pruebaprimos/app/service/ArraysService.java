package com.pruebaprimos.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pruebaprimos.app.dao.Peticion;
import com.pruebaprimos.app.entity.Arrays;

public interface ArraysService {

	public Iterable<Arrays> findAll();
	
	public Page<Arrays> findAll(Pageable pageable);
	
	public Optional<Arrays> findById(Long id);
	
	public Arrays save(Arrays array);
	
	public void deleteById(Long id);
	
	public List<Arrays> findAll1();
	
	public String respuestaPruebaTecnica(Peticion peticion);
	
	public List<Integer> respuestaPruebaTecnicaFinal(Peticion peticion);
	
	public List<Integer> respuestaPruebaTecnicaUr(String id, String iteraciones);
}
