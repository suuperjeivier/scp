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
import service.movimientos.MovimientosService;

/**
 * Servlet implementation class MovimientosController
 */
@WebServlet("/movimientos")
@MultipartConfig
public class MovimientosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovimientosService movimientosService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovimientosController() {
        super();
        this.setMovimientosService(new MovimientosService());
    }

    /**
	 * @return the movimientosService
	 */
	public MovimientosService getMovimientosService() {
		return movimientosService;
	}

	/**
	 * @param movimientosService the movimientosService to set
	 */
	public void setMovimientosService(MovimientosService movimientosService) {
		this.movimientosService = movimientosService;
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
				if(action.equals("asignacion")){
					asignacion(request, response);				
				}else if(action.equals("nuevoMovimientoItem")){
					nuevoMovimientoItem(request, response); 				
				}else if(action.equals("movimientosItem")){
					movimientosItem(request, response);
				}else if(action.equals("consultaOrdenes")){
					consultaOrdenes(request, response);
				}else if(action.equals("procesaFormOrdenes")){
					procesaFormOrdenes(request, response);
				}else if(action.equals("selectCatalogs")){
					selectCatalogs(request, response);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void asignacion(HttpServletRequest request, HttpServletResponse response){
		getMovimientosService().selectMovimientosItemsPorAsignar(request, response);
		//getMovimientosService().selectItemsPorTipoItem(request, response);
		//getMovimientosService().selectMovimientosItems(request, response);
		getMovimientosService().selectItemsAsignadosPorTipoItem(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/movimientos/asignaciones/asignaciones.jsp");
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
	public void consultaOrdenes(HttpServletRequest request, HttpServletResponse response){
		getMovimientosService().inicializaJSPConsultaOrdenes(request, response);//VEHICULOS, tecnicos, lugares, radios		
		getMovimientosService().consultaOrdenesService(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/movimientos/ordenes/ordenes.jsp");
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
	public void procesaFormOrdenes(HttpServletRequest request, HttpServletResponse response){		
		getMovimientosService().procesaFormOrdenesService(request, response);		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectCatalogs(HttpServletRequest request, HttpServletResponse response){		
		getMovimientosService().inicializaModalEditar(request, response);		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void nuevoMovimientoItem(HttpServletRequest request, HttpServletResponse response){
		getMovimientosService().nuevoMovimientoItem(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void movimientosItem(HttpServletRequest request, HttpServletResponse response){
		getMovimientosService().selectMovimientosDisponiblesParaItems(request, response);
		getMovimientosService().selectItemsPorTipoItem(request, response);
		getMovimientosService().selectMovimientosItems(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/movimientos/movimiento_item.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
