package herramientas.imagenes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class ProcesarImagen {
	
	
	public Part procesarImagenRequest(HttpServletRequest request, HttpServletResponse response, String nombreInput){
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
}
