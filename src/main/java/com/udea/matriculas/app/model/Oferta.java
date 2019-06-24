package com.udea.matriculas.app.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.udea.matriculas.app.excel.Excel;

public class Oferta implements Serializable{
	List<Curso> cursos = new ArrayList<Curso>();
	private int cuposRestantes;
	
	public Oferta() {
		
	}

	public Oferta(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(String filepath) {
		Excel excel = new Excel();
		List<String> offer = new ArrayList<String>();
		List <Curso> curso = new ArrayList<Curso>();
		try {
			offer = excel.excelReader(filepath);
			curso = excel.getOffert(offer);
			System.out.println(curso.size());
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cursos = curso;
	}
	
	
	
	
	
	
	
}
