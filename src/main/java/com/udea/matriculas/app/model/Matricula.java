package com.udea.matriculas.app.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Matricula implements Serializable{
	
	Map<String, Estudiante> matriculas = new HashMap<String, Estudiante>();
	
	
	public Matricula() {
	}
	
	
	public Matricula(Map<String, Estudiante> matriculas) {
		super();
		this.matriculas = matriculas;
	}


	public Map<String, Estudiante> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(Map<String, Estudiante> matriculas) {
		this.matriculas = matriculas;
	}
	
	public boolean yaMatriculo(String idMatricula) {
		Estudiante matriculado = matriculas.get(idMatricula);
		if(matriculado == null) {
			return false;
		}else {
			return true;
		}
		
	}
	public void updateMatriculados(String idMatricula, Estudiante estudiante) {
		matriculas.put(idMatricula, estudiante);
	}
	
	
}
