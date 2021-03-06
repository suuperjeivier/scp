package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.user.UserDTO;
import net.sf.jasperreports.engine.JRException;
import service.documentos.DocumentosService;
@MultipartConfig

/**
 * Servlet implementation class DocumentosController
 */
@WebServlet(asyncSupported = true, description = "Controlador de peticiones para documentos", urlPatterns = { "/documentos" })
public class DocumentosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DocumentosService documentosService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocumentosController() {
        super();
        if(this.getDocumentosService() == null){
        	this.setDocumentosService(new DocumentosService());
        }
    }
    
    /**
	 * @return the documentosService
	 */
	public DocumentosService getDocumentosService() {
		return documentosService;
	}

	/**
	 * @param documentosService the documentosService to set
	 */
	public void setDocumentosService(DocumentosService documentosService) {
		this.documentosService = documentosService;
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO)session.getAttribute("user");
		if(user != null){
			String action = request.getParameter("action");		
			if(action != null && !action.equals("") && action.length() > 0){
				if(action.equals("cargarArchivoExcel")){
					cargarArchivoExcel(request, response);
				}else if(action.equals("procesarArchivo")){
					procesarHorarioArchivoExcel(request, response);
				}else if(action.equals("guardarHorarioArchivo")){
					guardarHorarioArchivo(request, response, user);
				}else if(action.equals("borrarArchivo")){

				}else if(action.equals("documentoProcesado")){
					documentoProcesado(request, response);
				}else if(action.equals("descargarArchivo")){
					descargarArchivo(request, response);
				}else if(action.equals("descargarArchivoOrdenes")){
					descargarArchivoOrdenes(request, response);
				}else if(action.equals("reportesJasper")){
					reportesJasper(request, response);
				}else if(action.equals("filtrosCartografia")){
					filtrosCartografia(request, response);
				}else{
					System.out.println("El servicio no tiene accion1!");
				}
			}else{
				System.out.println("El servicio no tiene accion0! "+ action +" "+ this.getClass().getSimpleName());
			}
		}else{
			RequestDispatcher r = request.getRequestDispatcher("index.jsp");
			try {
				r.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void reportesJasper(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("tipo_doc")!= null){
			if(!request.getParameter("tipo_doc").isEmpty()){
				if(request.getParameter("tipo_doc").equals("none")){
					try {
						request.getRequestDispatcher("./jsp/documentos/reportes.jsp").forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					try {
						getDocumentosService().reportesJasper(request, response);
					} catch (JRException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}
		}else{
			try {
				request.getRequestDispatcher("./jsp/documentos/reportes.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void procesarHorarioArchivoExcel(HttpServletRequest request, HttpServletResponse response){		
		getDocumentosService().procesarHorarioArchivoExcel(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void descargarArchivo(HttpServletRequest request, HttpServletResponse response){		
		getDocumentosService().descargarArchivo(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void descargarArchivoOrdenes(HttpServletRequest request, HttpServletResponse response){		
		getDocumentosService().descargarArchivoOrdenes(request, response);
	}	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param user
	 */
	public void guardarHorarioArchivo(HttpServletRequest request, HttpServletResponse response, UserDTO user){
		getDocumentosService().guardarHorarioArchivoService(request, response, user);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void documentoProcesado(HttpServletRequest request, HttpServletResponse response){		
		try {
			request.getRequestDispatcher("./jsp/documentos/documentoProcesado.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void filtrosCartografia(HttpServletRequest request, HttpServletResponse response){		
		try {
			request.getRequestDispatcher("./jsp/documentos/cartografia/filtros_cartografia.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void cargarArchivoExcel(HttpServletRequest request, HttpServletResponse response){	
		getDocumentosService().selectDocumentosHorarios(request, response);
		try {
			request.getRequestDispatcher("./jsp/documentos/cargaHorario.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
