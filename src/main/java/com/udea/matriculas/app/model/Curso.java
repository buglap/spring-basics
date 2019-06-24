package com.udea.matriculas.app.model;

import java.io.Serializable;

public class Curso implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String nombre;
	private String cupos;
	private String horario;
	private String aula;
	
	public Curso() {
		
	}
	
	public Curso(String id, String nombre, String cupos, String horario, String aula) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cupos = cupos;
		this.horario = horario;
		this.aula = aula;
	}

	
	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCupos() {
		return cupos;
	}
	public void setCupos(String string) {
		this.cupos = string;
	}


}
