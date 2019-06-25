package com.udea.matriculas.app.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.udea.matriculas.app.excel.Excel;

public class Oferta implements Serializable{
	Map<String,Curso> cursos = new HashMap<String, Curso>();
	//List<Curso> cursos = new ArrayList<Curso>();
	
	
	public Oferta() {
		
	}

	public Oferta(Map<String, Curso> cursos) {
		this.cursos = cursos;
	}

	public Map<String, Curso> getCursos() {
		return cursos;
	}

	public void setCursos(String filepath) {
		Excel excel = new Excel();
		List<String> offer = new ArrayList<String>();
		Map<String,Curso> curso = new HashMap<String, Curso>();
		try {
			offer = excel.excelReader(filepath);
			curso = excel.getOffer(offer);
			System.out.println(curso.size());
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cursos = curso;
	}
	
	public void updateCursos(String curso) {
		Curso actual = cursos.get(curso);
		actual.setCupos(actual.getCupos() - 1);
	}
	
	public boolean hayCupos(String curso) {
		Curso actual = cursos.get(curso);
		if(actual.getCupos()>0) {
			return true;
		}else {
			return false;
		}
	}
}
