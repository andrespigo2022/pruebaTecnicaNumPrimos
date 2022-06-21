package com.pruebaprimos.app.dao;

public class Peticion {

	private String numIte;
	private String idPilaArrays;

	public String getNumIte() {
		return numIte;
	}

	public void setNumIte(String numIte) {
		this.numIte = numIte;
	}

	public String getIdPilaArrays() {
		return idPilaArrays;
	}

	public void setIdPilaArrays(String idPilaArrays) {
		this.idPilaArrays = idPilaArrays;
	}

	@Override
	public String toString() {
		return "Peticion [numIte=" + numIte + ", idPilaArrays=" + idPilaArrays + "]";
	}

}
