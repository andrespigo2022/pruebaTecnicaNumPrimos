package com.pruebaprimos.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaprimos.app.dao.Peticion;
import com.pruebaprimos.app.entity.Arrays;
import com.pruebaprimos.app.service.ArrayServiceHelper;
import com.pruebaprimos.app.service.ArraysService;

@RestController
@RequestMapping("/api/arrays")
public class ArraysController {

	@Autowired
	private ArraysService arrayService;
	@Autowired
	private ArrayServiceHelper helper;

	// create a new arrays

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Arrays array) {

		return ResponseEntity.status(HttpStatus.CREATED).body(arrayService.save(array));
	}

	// getArrayByid
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long ArraysId) {
		Optional<Arrays> oArrays = arrayService.findById(ArraysId);

		if (!oArrays.isPresent()) {

			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(oArrays);
	}

	// update an Array

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Arrays arreglo, @PathVariable(value = "id") Long arrayId) {
		Optional<Arrays> oArrays = arrayService.findById(arrayId);

		if (!oArrays.isPresent()) {

			return ResponseEntity.notFound().build();
		}

		// se copian las propiedades del objeto arreglo en el observable oArrays, este
		// observable nos evita el problema de que el valor obtenido sea un nullo
		// el metodo get del observable nos permnite traer el objeto al que este mismo
		// hace referencia
		oArrays.get().setInputArray(arreglo.getInputArray());

		// para copiar dos objetos en su totalidad de caracteristicas usamos el
		// siguiente metodo

		// BeanUtils.copyProperties(arreglo, oArrays.get());

		return ResponseEntity.status(HttpStatus.CREATED).body(arrayService.save(oArrays.get()));
	}

	@DeleteMapping("/{id}")
	// como el valor del parametro id es igual al del valor variable del delete
	// mapping no es necesario poner el value entre corchetes
	public ResponseEntity<?> delete(@PathVariable(value = "id") long arregloId) {
		if (!arrayService.findById(arregloId).isPresent()) {
			return ResponseEntity.notFound().build();

		}

		arrayService.deleteById(arregloId);

		return ResponseEntity.ok().build();

	}

	@GetMapping
	public List<Arrays> readAll() {

		List<Arrays> arreglos = StreamSupport.stream(arrayService.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return arreglos;

	}
	
	@PostMapping(path ="/respuesta", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> respuestaPruebaTecnica(@RequestBody Peticion peticion ) {
		if(!helper.validadorId(peticion.getIdPilaArrays()).equals("")) {
			return ResponseEntity.badRequest().body(helper.validadorId(peticion.getIdPilaArrays()));
		}
		
		if(!helper.validadorIteraciones(peticion.getNumIte()).equals("")) {
			return ResponseEntity.badRequest().body(helper.validadorIteraciones(peticion.getNumIte()));
		}
		
		return ResponseEntity.ok().body(arrayService.respuestaPruebaTecnica(peticion));
		
	}
	
	@PostMapping(path ="/respuestaFinal", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> respuestaPruebaTecnicaFinal(@RequestBody Peticion peticion ) {
	
		if(!helper.validadorId(peticion.getIdPilaArrays()).equals("")) {
			return ResponseEntity.badRequest().body(helper.validadorId(peticion.getIdPilaArrays()));
		}
		
		if(!helper.validadorIteraciones(peticion.getNumIte()).equals("")) {
			return ResponseEntity.badRequest().body(helper.validadorIteraciones(peticion.getNumIte()));
		}
		
		return ResponseEntity.ok().body(arrayService.respuestaPruebaTecnicaFinal(peticion));
		
		
	}
	
	@PostMapping(path="/respuestaUr", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
		public ResponseEntity<?>respuestaPruebaTecnica(String id, String iteraciones){
		if(!helper.validadorId(id).equals("")) {
			return ResponseEntity.badRequest().body(helper.validadorId(id));
		}
		
		if(!helper.validadorIteraciones(iteraciones).equals("")) {
			return ResponseEntity.badRequest().body(helper.validadorIteraciones(iteraciones));
		}
		
		return ResponseEntity.ok().body(arrayService.respuestaPruebaTecnicaUr(id, iteraciones));
		
		
	}
	
	@PostMapping("/respuestaUrl/{id}/{iteraciones}")
	public ResponseEntity<?>respuestaPruebaTecnicaUrl(@PathVariable(value = "id")String id, @PathVariable(value = "iteraciones")String iteraciones){
	if(!helper.validadorId(id).equals("")) {
		return ResponseEntity.badRequest().body(helper.validadorId(id));
	}
	
	if(!helper.validadorIteraciones(iteraciones).equals("")) {
		return ResponseEntity.badRequest().body(helper.validadorIteraciones(iteraciones));
	}
	
	return ResponseEntity.ok().body(arrayService.respuestaPruebaTecnicaUr(id, iteraciones));
	
	
}
}
