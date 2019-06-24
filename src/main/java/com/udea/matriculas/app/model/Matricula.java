package com.udea.matriculas.app.model;

import java.io.Serializable;
import java.util.List;

public class Matricula implements Serializable{
	private Curso curso;
	private Estudiante estudiantes;
	
	public Matricula(Curso curso, Estudiante estudiantes) {
		this.curso = curso;
		this.estudiantes = estudiantes;
	}

	public Matricula() {
		
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Estudiante getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(Estudiante estudiantes) {
		this.estudiantes = estudiantes;
	} 
	
}
