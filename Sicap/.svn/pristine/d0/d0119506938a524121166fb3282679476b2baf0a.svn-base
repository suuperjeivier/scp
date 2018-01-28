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
import service.bitacora.BitacoraService;
@MultipartConfig
/**
 * Servlet implementation class BitacoraController
 */
@WebServlet(description = "Procesa las peticiones para mostrar registro de bitacora", urlPatterns = { "/bitacora" })
public class BitacoraController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BitacoraService bitacoraService; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BitacoraController() {
        super();
       if(this.getBitacoraService() == null){
    	   this.setBitacoraService(new BitacoraService());
       }
    }

    /**
	 * @return the bitacoraService
	 */
	private BitacoraService getBitacoraService() {
		return bitacoraService;
	}

	/**
	 * @param bitacoraService the bitacoraService to set
	 */
	private void setBitacoraService(BitacoraService bitacoraService) {
		this.bitacoraService = bitacoraService;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserDTO user = null;
		try{
		user = (UserDTO) session.getAttribute("user");
		}catch (ClassCastException e) {
			System.out.println("Intento de uso sin sesion");
		}
		if(user != null){
			String action = (String) request.getParameter("action");
			if(action != null){
				if(action.equals("registrarAsistencia")){
					registrarAsistencia(request, response);					
				}else if(action.equals("guardarRegistroDeAsistencia")){
					guardarRegistroDeAsistencia(request, response, user);					
				}else if(action.equals("consultarBitacora")){
					consultarBitacora(request, response);					
				}else if(action.equals("consultarBitacoraFiltros")){
					consultarBitacoraFiltros(request, response);
				}else if(action.equals("procesarBitacora")){
					procesarBitacora(request, response, user);
				}else if(action.equals("listaAsistencia")){
					listaAsistencia(request, response);
				}else if(action.equals("listaAsistenciaSistPer")){
					listaAsistenciaSistPer(request, response);
				}else if(action.equals("listaAsistenciaFiltros")){
					listaAsistenciaFiltros(request, response);
				}else if(action.equals("consultarPermisos")){
					consultarPermisos(request, response);
				}else if(action.equals("buscarPermiso")){
					buscarPermiso(request, response);
				}else if(action.equals("listaAsitencia2")){
					listaAsitencia2(request, response);
				}else if(action.equals("nuevoPermiso")){
    				nuevoPermiso(request, response);
				}else if(action.equals("actualizarPermiso")){
					actualizarPermiso(request, response);
				}else if(action.equals("listaAsistenciaFiltros2")){
					listaAsistenciaFiltros2(request, response);
				}else if(action.equals("consultaNuevoSkuPermiso")){
					consultaNuevoSkuPermiso(request, response);
				}else if(action.equals("reporteJasper")){
					reporteJasper(request, response);
				}				
				else{
					System.out.println("El servicio no tiene accion1!");
				}
			}else{
				System.out.println("El servicio no tiene accion0!");
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
	 * Inicializa la pagina del reloj checador
	 * @param request
	 * @param response
	 */
	public void registrarAsistencia(HttpServletRequest request, HttpServletResponse response){
		getBitacoraService().selectLast10AttendanceRecords(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/bitacora/registro_asistencia.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Realiza el registro de la asistencia
	 * @param request
	 * @param response
	 */
	public void guardarRegistroDeAsistencia(HttpServletRequest request, HttpServletResponse response, UserDTO user){
		String  a = request.getRemoteAddr();
		//REGISTRA LA IP DEL CLIENTE QUE SOLICITO EL GUARDADO DEL REGISTRO
		System.out.println("ip: " + a + ".");
		getBitacoraService().saveAttendanceRecord(request, response, user);
		
	}
	
	public void consultarBitacora(HttpServletRequest request, HttpServletResponse response){
		getBitacoraService().consultarBitacora(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/bitacora/asistencia.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Consulta los registros de bitacora con los filtros definidos
	 * @param request
	 * @param response
	 */
	public void consultarBitacoraFiltros(HttpServletRequest request, HttpServletResponse response){	
		getBitacoraService().consultarBitacoraFiltros(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/bitacora/asistencia.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void procesarBitacora(HttpServletRequest request, HttpServletResponse response, UserDTO user){			
		getBitacoraService().procesarBitacora(request, response, user);		
	}
	
	/**
	 * Muestra la lista de asistencia
	 * @param request
	 * @param response
	 */
	public void listaAsistencia(HttpServletRequest request, HttpServletResponse response){
		getBitacoraService().inicializaListaAsistenciaJSP(request, response);
		getBitacoraService().consultarListaAsistencia(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/bitacora/listaAsistencia.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Muestra reporte de asistencia del Sistema Personal Antiguo
	 * @param request
	 * @param response
	 */
	public void listaAsistenciaSistPer(HttpServletRequest request, HttpServletResponse response){
		//getBitacoraService().inicializaListaAsistenciaJSP(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/documentos/reporte_AsistenciaSistPer.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Muestra la lista de asistencia con filtros
	 * @param request
	 * @param response
	 */
	public void listaAsistenciaFiltros(HttpServletRequest request, HttpServletResponse response){
		getBitacoraService().inicializaListaAsistenciaJSP(request, response);
		getBitacoraService().consultarListaAsistenciaFiltros(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/bitacora/listaAsistencia.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * consultarPermisos CONSULTA LOS PERMISOS QUE TIENEN LOS EMPLEADOS
	 * @param request
	 * @param response
	 */
	public void consultarPermisos(HttpServletRequest request, HttpServletResponse response){
		getBitacoraService().inicializaConsultarPermisos(request, response);
		getBitacoraService().consultaNombreIdEmpleados(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/bitacora/permisos_justificantes.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {			
			e.printStackTrace();
		}
	}	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void buscarPermiso(HttpServletRequest request, HttpServletResponse response){
		getBitacoraService().consultarPermisosFiltros(request, response);		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void listaAsitencia2(HttpServletRequest request, HttpServletResponse response){			
		getBitacoraService().inicializaListaAsistenciaJSP(request, response);
		//getBitacoraService().consultarListaAsistencia2(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/bitacora/listaAsistencia2.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {			
			e.printStackTrace();
		}
	}
	
	/**
	 * Muestra la lista de asistencia con filtros
	 * @param request
	 * @param response
	 */
	public void listaAsistenciaFiltros2(HttpServletRequest request, HttpServletResponse response){
		getBitacoraService().inicializaListaAsistenciaJSP(request, response);
		getBitacoraService().consultarListaAsistenciaFiltros2(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/bitacora/listaAsistencia2.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void nuevoPermiso(HttpServletRequest request, HttpServletResponse response){
		getBitacoraService().nuevoPermiso(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void reporteJasper(HttpServletRequest request, HttpServletResponse response){
		try {
			getBitacoraService().reporteJasper(request, response);
		} catch (JRException e) {
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
	public void actualizarPermiso(HttpServletRequest request, HttpServletResponse response){
		getBitacoraService().nuevoPermiso(request, response);
	}
	
	public void consultaNuevoSkuPermiso(HttpServletRequest request, HttpServletResponse response){
		getBitacoraService().consultaNuevoSkuPermiso(request, response);
	}
	
}
