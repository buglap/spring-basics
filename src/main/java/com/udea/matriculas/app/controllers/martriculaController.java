package com.udea.matriculas.app.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.udea.matriculas.app.excel.Excel;
import com.udea.matriculas.app.model.Curso;
import com.udea.matriculas.app.model.Estudiante;
import com.udea.matriculas.app.model.Matricula;
import com.udea.matriculas.app.model.Oferta;

@Controller
public class martriculaController {
	Oferta oferta = new Oferta();
	String filepath;
	
	//For set the filepath of the excel
	@GetMapping("/")
	public String getFilePath(Model model) {	
		model.addAttribute("title", "File path");
		return "file";
	}
	
	//set the list course from the excel file
	@PostMapping("/")
	public String setFilePath(@RequestParam(name = "sourceText") String sourceText, Model model) {	
		filepath = sourceText; 
		oferta.setCursos(sourceText);
		return "redirect:listar";
    }
	
	//get the list of courses
	@GetMapping("/listar")
	public String showOffert(Model model) {	
		model.addAttribute("title", "oferta 2019-2");
		model.addAttribute("curso", oferta.getCursos());
		return "listar";
	}
	
	 @RequestMapping(value = "/matricular/{id}",method=RequestMethod.GET)
	 public String crear(@PathVariable(value = "id") String id, Map<String,Object> model) {
		 Estudiante estudiante = new Estudiante();
         model.put("estudiante",estudiante);
		 model.put("title","Formulario de matricula para el curso: ");
		 model.put("curso", id);
		 
		 return "matricular";
		 
	 }
	
	@RequestMapping(value="/matricular",method=RequestMethod.POST)
    public String enrollStudent(@Valid Estudiante estudiante,@RequestParam(name = "curso") String curso, BindingResult result, Model model, RedirectAttributes flash) throws InvalidFormatException, IOException{
   	 
	if(result.hasErrors()) {
   		 model.addAttribute("titulo","Formulario de Matricula");
   		 return "matricular";
   	 }
		Excel excel = new Excel();
		Excel.modifyExistingWorkbook(filepath, estudiante, curso);
		oferta.updateCursos(curso);
        return "redirect:listar";
    }
}
