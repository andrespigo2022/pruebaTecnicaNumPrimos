package com.pruebaprimos.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebaprimos.app.entity.Arrays;
import com.pruebaprimos.app.repository.ArraysRepository;

@Service
public class ArrayServiceHelper {
	
	private final String validadorNeg = "^-[0-9]*";
	private final String validador = "^[0-9]*";
	private String vectorPrimos = "2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97";
	@Autowired
	private ArraysRepository arrayRepository;
	
	
	public String vectorPrimosConvertido() {
		return vectorPrimos.replace(", ", ",");
	}
	
	public boolean validarconversionString(String cadena) {
		return cadena.matches(validador);
	}

	public boolean validarTamaño(Long numero) {
		return (numero >= 1 && numero <= 5);

	}
	
	public boolean validarTamañoIteraciones(Long numero) {
		return (numero >= 1);

	}

	public boolean validarNegativo(String cadena) {

		return cadena.matches(validadorNeg);
	}
	
	public String validadorId(String id){
		String respuestaValidada="";
		if(!this.validarconversionString(id) && !this.validarNegativo(id)) {
			respuestaValidada= "el id ingresado " + id + " no es un valor numerico"; 
		}
		if(!this.validarconversionString(id) && this.validarNegativo(id)) {
			respuestaValidada= "el id ingresado " + id + " es un numero negativo"; 
		}
		
		if(this.validarconversionString(id) && !this.validarNegativo(id)) {
			if(!this.validarTamaño(Long.parseLong(id))) {
				respuestaValidada = "el id ingresado " + id + " no esta en el rango necesario de 1 a 5";
			}else if(this.validarTamaño(Long.parseLong(id)) && !arrayRepository.findById(Long.parseLong(id)).isPresent()) {
				
				respuestaValidada = "el id ingresado " + id + "no tiene un valor en estos momentos en base de datos";
			}
		} 
		return respuestaValidada;
		
	}
	
	public String validadorIteraciones(String iteraciones){
		String respuestaValidada="";
		if(!this.validarconversionString(iteraciones) && !this.validarNegativo(iteraciones)) {
			respuestaValidada= "el numero de iteraciones ingresado " + iteraciones + " no es un valor numerico"; 
		}
		if(!this.validarconversionString(iteraciones) && this.validarNegativo(iteraciones)) {
			respuestaValidada= "el numero de iteraciones  ingresado " + iteraciones + " es un numero negativo"; 
		}
		
		if(this.validarconversionString(iteraciones) && !this.validarNegativo(iteraciones)) {
			if(!this.validarTamañoIteraciones(Long.parseLong(iteraciones))) {
				respuestaValidada = "el numero de iteraciones  ingresado " + iteraciones + " no es mayor o igual de 1";
			}
		} 
		return respuestaValidada;
		
	}

	public int[] vectorConvertido(String[] vectorParaConvertir) {
		int[] vectorConvertido = new int[vectorParaConvertir.length];
		for (int i = 0; i < vectorParaConvertir.length; i++) {
			vectorConvertido[i] = Integer.parseInt(vectorParaConvertir[i]);
		}
		return vectorConvertido;
	}

	public List<Integer> ListaConvertidoinvertido(String[] vectorParaConvertir) {

		List<Integer> list2 = new ArrayList<Integer>();

		for (String numeros : vectorParaConvertir) {
			list2.add(Integer.parseInt(numeros));
		}
		Collections.reverse(list2);

		return list2;
	}
	
	public List<Integer> ListaConvertidoSinInvertir(String[] vectorParaConvertir) {

		List<Integer> list2 = new ArrayList<Integer>();

		for (String numeros : vectorParaConvertir) {
			list2.add(Integer.parseInt(numeros));
		}

		return list2;
	}
	public String respuesta(int iteraciones, String vectorPrimos, Arrays pilaEvaluada) {

		int[] vectorPrimosConvertido = vectorConvertido(vectorPrimos.split(","));

		List<Integer> listaPregunta = ListaConvertidoinvertido(pilaEvaluada.getInputArray().split(","));

		String respuestaVector = respuestaProcesada(listaPregunta, iteraciones, vectorPrimosConvertido);

		return respuestaVector;
	}

	public String respuestaProcesada(List<Integer> pregunta, int valor, int[] primos) {
		String respuesta = "";
		List<Integer> aux = new ArrayList<Integer>();

		for (int i = 0; i < valor; i++) {
			int numeroDivisor = primos[i];
			aux.clear();
			for (int numeroQueSeDivide : pregunta) {
				if (numeroQueSeDivide % numeroDivisor == 0) {
					if (respuesta.equals("")) {
						respuesta = String.valueOf(numeroQueSeDivide) + ",";
					} else {
						respuesta = respuesta + String.valueOf(numeroQueSeDivide) + ",";
					}
				} else {
					aux.add(numeroQueSeDivide);

				}
			}

			if (!pregunta.equals(aux)) {
				pregunta.clear();
				pregunta = aux;
				aux = new ArrayList<Integer>();

			} else {

				break;
			}
		}

		if (!pregunta.isEmpty()) {

			for (int i = 0; i < pregunta.size(); i++) {

				respuesta = respuesta + String.valueOf(pregunta.get(i)) + ",";

			}

		}

		return respuesta.substring(0, respuesta.length() - 1);

	}
	
	


}
