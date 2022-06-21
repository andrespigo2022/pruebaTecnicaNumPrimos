package com.pruebaprimos.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebaprimos.app.dao.Peticion;
import com.pruebaprimos.app.entity.Arrays;
import com.pruebaprimos.app.repository.ArraysRepository;

@Service
public class ArraysServiceImpl implements ArraysService {

	@Autowired
	private ArraysRepository arrayRepository;

	@Autowired
	private ArrayServiceHelper helper;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Arrays> findAll() {
		return arrayRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Arrays> findAll(Pageable pageable) {
		return arrayRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Arrays> findById(Long id) {

		return arrayRepository.findById(id);
	}

	@Override
	@Transactional
	public Arrays save(Arrays array) {
		return arrayRepository.save(array);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		arrayRepository.deleteById(id);
	}

	@Override
	@Transactional
	public List<Arrays> findAll1() {

		return arrayRepository.findAll();
	}

	@Override
	public String respuestaPruebaTecnica(Peticion peticion) {
		String respuesta = " ";
		Long idArrays = Long.parseLong(peticion.getIdPilaArrays());
		int numIteraciones = Integer.parseInt(peticion.getNumIte());
		Optional<Arrays> pilaEvaluada = this.findById(idArrays);
		respuesta = helper.respuesta(numIteraciones, helper.vectorPrimosConvertido(), pilaEvaluada.get());
		
		return respuesta;

	}

	@Override
	public List<Integer> respuestaPruebaTecnicaFinal(Peticion peticion) {

		String respuesta = " ";
		List<Integer> listaRespuesta = new ArrayList<Integer>();
		Long idArrays = Long.parseLong(peticion.getIdPilaArrays());
		int numIteraciones = Integer.parseInt(peticion.getNumIte());
		Optional<Arrays> pilaEvaluada = this.findById(idArrays);
		respuesta = helper.respuesta(numIteraciones, helper.vectorPrimosConvertido(), pilaEvaluada.get());
		listaRespuesta = helper.ListaConvertidoSinInvertir(respuesta.split(","));

		return listaRespuesta;
	}

	@Override
	public List<Integer> respuestaPruebaTecnicaUr(String id, String iteraciones) {
		List<Integer> listaRespuesta = new ArrayList<Integer>();
		Long idArrays = Long.parseLong(id);
		int numIteraciones = Integer.parseInt(iteraciones);
		Optional<Arrays> pilaEvaluada = this.findById(idArrays);
		String respuesta = helper.respuesta(numIteraciones, helper.vectorPrimosConvertido(), pilaEvaluada.get());
		listaRespuesta = helper.ListaConvertidoSinInvertir(respuesta.split(","));

		return listaRespuesta;
	}

}
