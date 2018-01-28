package herramientas.archivos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.documentos.DocumentosDAO;
import herramientas.ArchivoDTO;

public class ProcesarArchivos {
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param nombreInput
	 * @return Part El archivo
	 */
	public Part procesarArchivosRequest(HttpServletRequest request, HttpServletResponse response, String nombreInput){
		response.setContentType("text/html;charset=UTF-8");
	    Part filePart = null;
		try {
			filePart = request.getPart(nombreInput);
			System.out.println("Procesa file part");
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ServletException e) {			
			e.printStackTrace();
		}	   
	    return filePart;
	}
	
	
	public ArchivoDTO armaArchivoDTO(ArchivoDTO archivo, String rutaAux){
		
		if(archivo.getParteArchivo() != null){ 	
			String fileName = Paths.get(archivo.getParteArchivo().getSubmittedFileName()).getFileName().toString(); // MSIE fix.	    
			System.out.println("Nombre " + fileName);
			String rutaResp = "";
			String os = System.getProperty("os.name").toLowerCase();
			System.out.println("OS:" + System.getProperty("os.name").toLowerCase());
			if(os.contains("linux")){
				rutaResp = "/home/jbritop/archivos/"+rutaAux;///home/jbritop/archivos/horarios
			}else{
				rutaResp = "C:/pruebas/archivos/"+rutaAux;
			}

			String ext = fileName.substring(fileName.lastIndexOf('.') + 1);

			
			archivo.setNombreDocumento(fileName);
			//	    	doc.setDocumento(targetFile);
			archivo.setExtDocumento(ext);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			formatter = formatter.withLocale( Locale.US );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
			LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));	    	
			archivo.setFechaCargaDocumento(date);
			archivo.setFechaValidezDocumento(date);
			formatter = DateTimeFormatter.ofPattern("HH:mm:ss");	    	
			LocalTime time = LocalTime.parse(LocalTime.now().format(formatter));
			archivo.setHoraCargaDocumento(time);
			archivo.setRutaDocumento(rutaResp);
			archivo.setTamanoDocumento(archivo.getParteArchivo().getSize());

		}else{
			
		}
		return archivo;
	}
	
	public void guardaArchivoEnRuta(ArchivoDTO archivo, String nombre){
		System.out.println("Hola procesando archivo");
		InputStream fileContent = null;
		File targetFile = new File(archivo.getRutaDocumento()+nombre);
		try {
			fileContent = archivo.getParteArchivo().getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}	    
		try {
			java.nio.file.Files.copy(
					fileContent, 
					targetFile.toPath(), 
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public int insertarArchivo(ArchivoDTO archivo){
		DocumentosDAO documentosDAO = new DocumentosDAO();
		int res = 0;
		res = documentosDAO.insertRegistroArchivoDTO(archivo);
		return res;
	}

}
