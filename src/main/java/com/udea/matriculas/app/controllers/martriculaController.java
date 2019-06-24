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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	String filepath = "C:\\Users\\tatis\\OneDrive\\Escritorio\\matriculas.xlsx";
	
	@GetMapping({"/","/listar"})
	
	public String showOffert(Model model) {
		oferta.setCursos(filepath);	
		model.addAttribute("title", "oferta 2019-2");
		model.addAttribute("curso", oferta.getCursos());
		return "listar";
	}
	
	 @RequestMapping(value = "/matricular/{id}",method=RequestMethod.GET)
	 public String crear(@PathVariable(value = "id") String id, Map<String,Object> model) {
		 Estudiante estudiante = new Estudiante();
         model.put("estudiante",estudiante);
		 model.put("title","Formulario de matricula para el curso: "+id);
		 
		 return "matricular";
		 
	 }
	
	@RequestMapping(value="/matricular",method=RequestMethod.POST)
    //Como la clase se llama igual al atributo no es necesario poner, @ModelAtribute("nombre con el que se pasa a la vista")
    public String enrollStudent(@Valid Estudiante estudiante,BindingResult result, Model model, RedirectAttributes flash) throws InvalidFormatException, IOException{
   	 
	if(result.hasErrors()) {
   		 model.addAttribute("titulo","Formulario de Matricula");
   		 return "matricular";
   	 }
		Excel excel = new Excel();
		Excel.modifyExistingWorkbook(filepath, estudiante);
        return "redirect:listar";
    }
}
