package com.udea.matriculas.app.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.udea.matriculas.app.model.Curso;
import com.udea.matriculas.app.model.Estudiante;
import com.udea.matriculas.app.model.Matricula;

public class Excel {

	public List<String> excelReader(String filepath)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		List<String> cells = new ArrayList<String>();

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(filepath));

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);
		 DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
		 

		//iterating with lambda for java 8
		sheet.forEach(row -> {
			row.forEach(cell -> {
				cells.add(formatter.formatCellValue(cell));
			});
		});
		
		// Closing the workbook
		workbook.close();

		return cells;

	}
	//class to fit the data in to the logic business
	public List<Curso> getOffert(List<String> cells){
		List<Curso> oferta = new ArrayList<Curso>();
		System.out.println("cells size"+cells.size());
		int i= 0;
		while (i < cells.size()) {
			 if(cells.get(i)=="" || cells.get(i)==null) break;
			Curso curso = new Curso();
			curso.setId(cells.get(i)); 
			curso.setNombre(cells.get(i+1));
			curso.setCupos(cells.get(i+2));
			curso.setHorario(cells.get(i+3));
			curso.setAula(cells.get(i+4));
		    oferta.add(curso);
		    System.out.println("aula: "+curso.getAula());
		    System.out.println("oferta size: "+oferta.size());
		    System.out.println("i values: "+ i);
		    i=i+5;
		}
		oferta.remove(0);
		return oferta;
	}

	private Object cellValue(Cell cell) {
		Object valueCell = new Object();
		switch (cell.getCellTypeEnum()) {
		case BOOLEAN:
			valueCell = cell.getBooleanCellValue();
			break;
		case STRING:
			valueCell = cell.getRichStringCellValue().getString();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				valueCell = cell.getDateCellValue();
			} else {
				valueCell = cell.getNumericCellValue();
			}
			break;
		case FORMULA:
			valueCell = cell.getCellFormula();
			break;
		case BLANK:
			valueCell = "";
			break;
		default:
			valueCell = "";
		}
		return valueCell;
	}

	public static void modifyExistingWorkbook(String filePath, Estudiante estudiante) throws InvalidFormatException, IOException {
        
		// Obtain a workbook from the excel file
        Workbook workbook = WorkbookFactory.create(new FileInputStream(filePath));
        int numberSheets = workbook.getNumberOfSheets();
        if(numberSheets<2) {
        	 Sheet sheet = workbook.createSheet("Matriculados");
        	 Row row = sheet.createRow(0);
        	 int columnCount = 0;
        	 Cell cell = row.createCell(columnCount);
             cell.setCellValue("id");
             cell = row.createCell(++columnCount);
             cell.setCellValue("Nombre");
             cell = row.createCell(++columnCount);
             cell.setCellValue("Apellido");
             cell = row.createCell(++columnCount);
             cell.setCellValue("Email");
            
        }
             Sheet sheet = workbook.getSheetAt(1);
             
             int rowCount = sheet.getLastRowNum();
             //generate a  new row
             Row row = sheet.createRow(++rowCount);
        
             int columnCount = 0;
             	String codigo=estudiante.getId();
             	Cell cell = row.createCell(columnCount);
                cell.setCellValue(codigo);
             	
                cell = row.createCell(++columnCount);
             	codigo=estudiante.getNombre();
             	cell.setCellValue(codigo);
             	
             	cell = row.createCell(++columnCount);
             	codigo=estudiante.getApellido();
                cell.setCellValue(codigo);
                 
                cell = row.createCell(++columnCount);
             	codigo=estudiante.getEmail();
             	cell.setCellValue(codigo);
                
                
		        // Write the output to a file
		        FileOutputStream fileOut = new FileOutputStream(filePath);
		        workbook.write(fileOut);
		        fileOut.close();
		
		        // Closing the workbook
		        workbook.close();
    }

}
