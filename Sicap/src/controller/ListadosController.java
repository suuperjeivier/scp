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

import dto.listados.ConfigCamposTipoItemDTO;
import dto.user.UserDTO;
import dto.user.menu.MenuDTO;
import dto.user.menu.submenu.SubMenuDTO;
import service.listados.ListadosService;


/**
 * Servlet implementation class ListadosController
 */
@WebServlet("/listados")
@MultipartConfig
public class ListadosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ListadosService listadosService = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadosController() {
        super();
        if(this.getListadosService() == null){
        	this.setListadosService(new ListadosService());
        }
    }    
   
    /**
	 * @return the listadosService
	 */
	private ListadosService getListadosService() {
		return listadosService;
	}

	/**
	 * @param listadosService the listadosService to set
	 */
	private void setListadosService(ListadosService listadosService) {
		this.listadosService = listadosService;
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	if(session != null){
    		if(session.getAttribute("user") != null && !session.getAttribute("user").equals(null)){
    			//    			System.out.println(session.getAttribute("user").getClass().getName());
    			if(!session.getAttribute("user").getClass().getName().equals("java.lang.String")){
    				UserDTO user = (UserDTO)session.getAttribute("user");
    				if(user != null){
    					String action = (String) request.getParameter("action");
    					if(action != null){
    						if(action.equals("consultarEmpleados")){
    							consultarEmpleados(request, response);
    						}else if(action.equals("buscarEmpleado")){
    							buscarEmpleado(request, response);
    						}else if(action.equals("paginador")){
    							System.out.println("Sin accion dentro de listado de condicionales");
    						}else if(action.equals("selectGeneros")){
    							selectGeneros(request, response);
    						}else if(action.equals("selectEntidadesFederativas")){
    							selectEntidadesFederativas(request, response);
    						}else if(action.equals("selectMunicipios")){
    							selectMunicipios(request, response);
    						}else if(action.equals("selectColonias")){
    							selectColonias(request, response);
    						}else if(action.equals("selectEstadosCiviles")){
    							selectEstadosCiviles(request, response);
    						}else if(action.equals("selectGradosEscolares")){
    							selectGradosEscolares(request, response);
    						}else if(action.equals("selectEspecialidadesAcademicas")){
    							selectEspecialidadesAcademicas(request, response);
    						}else if(action.equals("nuevoEmpleado")){
    							nuevoEmpleado(request, response);
    						}else if(action.equals("nuevoSkuEmpleado")){
    							nuevoSkuEmpleado(request, response);
    						}else if(action.equals("buscaCurpEmpleado")){
    							buscaCurpEmpleado(request, response);
    						}else if(action.equals("consultarHorarios")){
    							consultarHorarios(request, response);
    						}else if(action.equals("buscarHorario")){
    							buscarHorario(request, response);
    						}else if(action.equals("actualizarEmpleado")){
    							actualizarEmpleado(request, response);
    						}else if(action.equals("nuevoHorario")){
    							nuevoHorario(request, response, user);
    						}else if(action.equals("actualizarHorario")){
    							actualizarHorario(request, response, user);
    						}else if(action.equals("consultarSkuHorario")){
    							consultarSkuHorario(request, response);
    						}else if(action.equals("actualizarDatosUsuario")){
    							actualizarDatosUsuario(request, response);
    						}else if(action.equals("selectTiposEstatus")){
    							selectTiposEstatus(request, response);
    						}else if(action.equals("consultarHorariosEmpleados")){
    							consultarHorariosEmpleados(request, response);
    						}else if(action.equals("filtrosHorariosEmpleados")){
    							filtrosHorariosEmpleados(request, response);
    						}else if(action.equals("consultarItems")){
    							consultarItems(request, response);
    						}else if(action.equals("guardarItem")){
    							guardarItem(request, response);
    						}else if(action.equals("selectMaxIdItem")){
    							selectMaxIdItem(request, response);
    						}else if(action.equals("selectMarcasItems")){
    							selectMarcasItems(request, response);
    						}else if(action.equals("selectModelosItems")){
    							selectModelosItems(request, response);
    						}else if(action.equals("selectEmpleado")){
    							selectEmpleado(request, response);
    						}else if(action.equals("consultarllamadas")){
    							consultarllamadas(request, response);
    						}else if(action.equals("filtrosBitacoraLlamadas")){
    							filtrosBitacoraLlamadas(request, response);
    						}else if(action.equals("nuevoUsuario")){//PARA EL RELOJ CHECADOR
    							nuevoUsuario(request, response);    							
    						}else if(action.equals("consultarllamadas")){
    							consultarllamadas(request, response);
    						}else if(action.equals("usuarios")){
    							boolean valido = false;
    							for(MenuDTO elementoMenuDTO : user.getMenu()){
    								for(SubMenuDTO elemetoSubmenuDTO : elementoMenuDTO.getSubMenu()){
    									if(elemetoSubmenuDTO.getUrlAction() != null && !elemetoSubmenuDTO.getUrlAction().isEmpty()){
    										String[] acciones = elemetoSubmenuDTO.getUrlAction().split("action=");
    										for(String accion : acciones){
    											if(accion.equals(action)){
    												valido = true;
    											}
    										}
    									}
    								}
    							}
    							if(valido){
    								usuarios(request, response);
    							}
    						}else if(action.equals("perfiles")){
    							boolean valido = false;
    							for(MenuDTO elementoMenuDTO : user.getMenu()){
    								for(SubMenuDTO elemetoSubmenuDTO : elementoMenuDTO.getSubMenu()){
    									if(elemetoSubmenuDTO.getUrlAction() != null && !elemetoSubmenuDTO.getUrlAction().isEmpty()){
    										String[] acciones = elemetoSubmenuDTO.getUrlAction().split("action=");
    										for(String accion : acciones){
    											if(accion.equals(action)){
    												valido = true;
    											}
    										}
    									}
    								}
    							}
    							if(valido){
    								perfiles(request, response);
    							}
    						}else if(action.equals("consultaUsuarios")){
    							consultaUsuarios(request, response);
    						}else if(action.equals("nuevoModelo")){
    							nuevoModelo(request, response);
    						}else if(action.equals("selectEmpleadosConSinUsario")){
    							selectEmpleadosConSinUsario(request, response);
    						}else if(action.equals("actualizarUsuario")){
    							actualizarUsuario(request, response);
    						}else if(action.equals("itemsOrdenes")){
    							itemsOrdenes(request, response);
    						}else if(action.equals("procesaFormItemOrdenes")){
    							procesaFormItemOrdenes(request, response);
    						}else if(action.equals("consultarPartners")){
    							consultarPartners(request, response);
    						}else if(action.equals("procesaFormPartner")){
    							procesaFormPartner(request, response);
    						}else{
    							System.out.println("El servicio no tiene accion0!");
    						}
    					}else{
    						System.out.println("El servicio no tiene accion1!");
    					}
    				}else{
    					System.err.println("sin usuario en : " + this.getClass().getSimpleName());
    					RequestDispatcher r = request.getRequestDispatcher("index.jsp");
    					try {
    						r.forward(request, response);
    					} catch (ServletException | IOException e) {
    						e.printStackTrace();
    					}
    				}
    			}else{
    				System.err.println("Session = string en : " + this.getClass().getSimpleName());
    				RequestDispatcher r = request.getRequestDispatcher("index.jsp");
    				try {
    					r.forward(request, response);
    				} catch (ServletException | IOException e) {
    					e.printStackTrace();
    				}
    			}

    		}else{
    			System.err.println("sin usuario2 en : " + this.getClass().getSimpleName());
    			RequestDispatcher r = request.getRequestDispatcher("index.jsp");
    			try {
    				r.forward(request, response);
    			} catch (ServletException | IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}else{
    		System.err.println("Session = null en : " + this.getClass().getSimpleName());
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
	public void usuarios(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectUsersService(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/usuarios/usuarios.jsp");
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
	public void itemsOrdenes(HttpServletRequest request, HttpServletResponse response){
		ListadosService ls = new ListadosService(12);
		String tipoItem = request.getParameter("item");
		switch (tipoItem) {
		case "departamentos":
			ls.selectDeptosOrdenesService(request, response);
			break;
		case "lugares":
			ls.selectLugaresOrdenesService(request, response);
			break;
		case "radios":			
			ls.selectRadiosOrdenesService(request, response);
			break;
		case "tecnicos":
			ls.selectTecnicosOrdenesService(request, response);
			break;
		case "vehiculos":
			ls.selectVehiculosOrdenesService(request, response);
			break;
		default:
			break;
		}
		
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/items/ordenes/"+tipoItem+".jsp");
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
	public void procesaFormItemOrdenes(HttpServletRequest request, HttpServletResponse response){		
		ListadosService ls = new ListadosService(12);
		ls.procesaFormItemOrdenesService(request, response);	
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void perfiles(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectPerfilUserService(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/perfiles/perfiles.jsp");
		
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
	public void consultaUsuarios(HttpServletRequest request, HttpServletResponse response){
//		getListadosService().selectUsersService(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectEmpleado(HttpServletRequest request, HttpServletResponse response){
		getListadosService().selectEmpleadoService(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectEmpleadosConSinUsario(HttpServletRequest request, HttpServletResponse response){
		getListadosService().selectEmpleadosConSinUsario(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void nuevoModelo(HttpServletRequest request, HttpServletResponse response){
		getListadosService().nuevoModeloService(request, response);
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void nuevoUsuario(HttpServletRequest request, HttpServletResponse response){
		getListadosService().nuevoUsuarioService(request, response);
	}
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void actualizarUsuario(HttpServletRequest request, HttpServletResponse response){
		getListadosService().nuevoUsuarioService(request, response);
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectEmpleadoRegistroAsistencia(HttpServletRequest request, HttpServletResponse response){
		getListadosService().selectEmpleadoService(request, response);
	}
	
	
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultarEmpleados(HttpServletRequest request, HttpServletResponse response){
		getListadosService().buscarEmpleado(request, response);		
		getListadosService().obtenerGruposSanguineos(request, response);
		getListadosService().obtenerPeriodosEscolares(request, response);
		getListadosService().obtenerNivelesAcademicos(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/empleados/empleado.jsp");
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
	public void buscarEmpleado(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().buscarEmpleado(request, response);
		getListadosService().obtenerGruposSanguineos(request, response);
		getListadosService().obtenerPeriodosEscolares(request, response);
		getListadosService().obtenerNivelesAcademicos(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/empleados/empleado.jsp");
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
	public void selectGeneros(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectGeneros(request, response);	
	}	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultarllamadas(HttpServletRequest request, HttpServletResponse response){		
//		getListadosService().consultarllamadasService(request, response);	
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/bitacora_llamadas/bitacora_llamadas.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void filtrosBitacoraLlamadas(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().consultarllamadasFiltrosService(request, response);	
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/bitacora_llamadas/bitacora_llamadas.jsp");
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
	public void selectEntidadesFederativas(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectEntidadesFederativas(request, response);		
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectMunicipios(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectMunicipios(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectColonias(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectColonias(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectEstadosCiviles(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectEstadosCiviles(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectGradosEscolares(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectGradosEscolares(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectEspecialidadesAcademicas(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectEspecialidadesAcademicas(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void nuevoEmpleado(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().nuevoEmpleadoService(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void nuevoSkuEmpleado(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().nuevoSkuEmpleado(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void buscaCurpEmpleado(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().buscaCurpEmpleado(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultarHorarios(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().consultarHorarios(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/horarios/horario.jsp");
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
	public void buscarHorario(HttpServletRequest request, HttpServletResponse response){			
		getListadosService().buscarHorarios(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/horarios/horario.jsp");
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
	public void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().nuevoEmpleadoService(request, response);
	}
	
	public void nuevoHorario(HttpServletRequest request, HttpServletResponse response, UserDTO usuario){		
		getListadosService().nuevoHorario(request, response, usuario);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void actualizarHorario(HttpServletRequest request, HttpServletResponse response, UserDTO usuario){		
		getListadosService().nuevoHorario(request, response, usuario);
	}
	
	/**
	 * Consulta el sku del horario al crear uno nuevo para mostrarlo en el modal 
	 * @param request
	 * @param response
	 */
	public void consultarSkuHorario(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().consultaSkuNuevoHorario(request, response);
	}
	
	/**
	 * Actualiza los datos del usuario
	 * @param request
	 * @param response
	 */
	public void actualizarDatosUsuario(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().actualizarDatosUsuario(request, response);
	}	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectTiposEstatus(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectTiposEstatus(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectMaxIdItem(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectMaxIdItem(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultarHorariosEmpleados(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectHorariosEmpleados(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/empleados_horarios.jsp");
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
	public void filtrosHorariosEmpleados(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectHorariosEmpleadosFiltros(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/empleados_horarios.jsp");
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
	public void consultarItems(HttpServletRequest request, HttpServletResponse response){		
		ConfigCamposTipoItemDTO configCamposItem = getListadosService().inicializaJSPItems(request, response);		
		getListadosService().selectItems(request, response, configCamposItem);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/items/items.jsp");
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
	public void consultarPartners(HttpServletRequest request, HttpServletResponse response){		
		//ConfigCamposTipoItemDTO configCamposItem = getListadosService().inicializaJSPItems(request, response);		
		getListadosService().selectPartnersPorTipo(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/partners/partner.jsp");
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
	public void procesaFormPartner(HttpServletRequest request, HttpServletResponse response){		
		
	}	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void guardarItem(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().guardarItemService(request, response);
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectModelosItems(HttpServletRequest request, HttpServletResponse response){	
		getListadosService().selectItemsTipos(request, response);
		getListadosService().selectModelosItems(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/items/modelos/modelos.jsp");
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
	public void selectMarcasItems(HttpServletRequest request, HttpServletResponse response){		
		getListadosService().selectMarcasItems(request, response);
		RequestDispatcher r = getServletContext().getRequestDispatcher("/jsp/listados/items/marcas/marcas.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
