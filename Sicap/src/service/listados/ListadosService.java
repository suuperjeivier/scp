package service.listados;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import dao.ConfigDAO;
import dao.empleado.EmpleadoDAO;
import dao.empleadoHorario.EmpleadoHorarioDAO;
import dao.horario.HorarioDAO;
import dao.item.ItemDAO;
import dao.listados.ListadosDAO;
import dao.listados.ListadosDAOInsertUpdate;
import dao.listados.TipoEstatusDAO;
import dao.movimientos.MovimientosDAO;
import dao.movimientos.ordenes.OrdenesDAO;
import dao.partners.PartnerDAO;
import dao.user.UserDAO;
import dao.user.perfil.PerfilDAO;
import dto.empleado.EmpleadoDTO;
import dto.empleado.EmpleadoHorarioDTO;
import dto.empleado.EspecialidadDTO;
import dto.empleado.NivelAcademicoDTO;
import dto.horario.HorarioDTO;
import dto.listados.ColoniaDTO;
import dto.listados.ConfigCamposTipoItemDTO;
import dto.listados.EntidadFederativaDTO;
import dto.listados.EstadoCivilDTO;
import dto.listados.GeneroDTO;
import dto.listados.GradoEscolarDTO;
import dto.listados.GrupoSanguineoDTO;
import dto.listados.MunicipioDTO;
import dto.listados.PeriodoEscolarDTO;
import dto.listados.TipoEstatusDTO;
import dto.listados.TotalTiposLlamadasDTO;
import dto.listados.items.ItemDTO;
import dto.listados.items.LineaItemDTO;
import dto.listados.items.MarcaItemDTO;
import dto.listados.items.ModeloItemDTO;
import dto.listados.items.TipoItemDTO;
import dto.logs.ErroresDTO;
import dto.ordenes.DepartamentoDTO;
import dto.ordenes.LugarDTO;
import dto.ordenes.RadioDTO;
import dto.ordenes.TecnicoDTO;
import dto.ordenes.VehiculoDTO;
import dto.partner.PartnerDTO;
import dto.user.UserConfigDTO;
import dto.user.UserDTO;
import dto.user.UserSimpleDTO;
import dto.user.perfil.PerfilDTO;
import herramientas.FechaDTO;
import herramientas.herrramientasrs.HerramientasResultSet;
import herramientas.imagenes.ProcesarImagen;


public class ListadosService {
	
	private EmpleadoDAO empleadoDAO; 
	private ListadosDAO listadosDAO;
	private HorarioDAO horarioDAO;
	private UserDAO userDAO;
	private ItemDAO itemDAO;
	private ConfigDAO configDAO;
	private TipoEstatusDAO tipoEstatusDAO;
	private PerfilDAO perfilDAO;
	private ProcesarImagen procesarImagen;
	private MovimientosDAO movimientosDAO;
	private ListadosDAOInsertUpdate listadoDAOIU;
	private OrdenesDAO ordenesDAO;
	private PartnerDAO partnerDAO;
	 
	/**
	 * INICIALIZA LOS COMPONENTES DE LA CLASE
	 */
	public ListadosService(){
		if(this.getEmpleadoDAO() == null){
			setEmpleadoDAO(new EmpleadoDAO());
		}
		if(this.getListadosDAO() == null){
			setListadosDAO(new ListadosDAO());
		}
		if(this.getHorarioDAO() == null){
			setHorarioDAO(new HorarioDAO());	
		}
		if(this.getUserDAO() == null){		
			setUserDAO(new UserDAO());
		}
		if(this.getItemDAO() == null){
			this.setItemDAO(new ItemDAO());
		}
		if(this.getConfigDAO() == null){
			this.setConfigDAO(new ConfigDAO());
		}
		if(this.getTipoEstatusDAO() == null){
			this.setTipoEstatusDAO(new TipoEstatusDAO());
		}
		if(this.getPerfilDAO() == null){
			this.setPerfilDAO(new PerfilDAO());
		}
		if(this.getProcesarImagen() == null){
			this.setProcesarImagen(new ProcesarImagen());
		}	
		if(this.getMovimientosDAO() == null){
			this.setMovimientosDAO(new MovimientosDAO());
		}
		if(this.getListadoDAOIU() == null){
			this.setListadoDAOIU(new ListadosDAOInsertUpdate());
		}
		if(this.getOrdenesDAO() == null){
			this.setOrdenesDAO(new OrdenesDAO());
		}
		if(this.getPartnerDAO() == null){
			this.setPartnerDAO(new PartnerDAO());
		}
		
	}
	
	/**
	 * INICIALIZA LOS COMPONENTES DE LA CLASE
	 */
	public ListadosService(int conexion){
		if(this.getEmpleadoDAO() == null && conexion == 1){
			setEmpleadoDAO(new EmpleadoDAO());
		}
		if(this.getListadosDAO() == null && conexion == 2){
			setListadosDAO(new ListadosDAO());
		}
		if(this.getHorarioDAO() == null && conexion == 3){
			setHorarioDAO(new HorarioDAO());	
		}
		if(this.getUserDAO() == null && conexion == 4){		
			setUserDAO(new UserDAO());
		}
		if(this.getItemDAO() == null && conexion == 5){
			this.setItemDAO(new ItemDAO());
		}
		if(this.getConfigDAO() == null && conexion == 6){
			this.setConfigDAO(new ConfigDAO());
		}
		if(this.getTipoEstatusDAO() == null && conexion == 7){
			this.setTipoEstatusDAO(new TipoEstatusDAO());
		}
		if(this.getPerfilDAO() == null && conexion == 8){
			this.setPerfilDAO(new PerfilDAO());
		}
		if(this.getProcesarImagen() == null && conexion == 9){
			this.setProcesarImagen(new ProcesarImagen());
		}	
		if(this.getMovimientosDAO() == null && conexion == 10){
			this.setMovimientosDAO(new MovimientosDAO());
		}
		if(this.getListadoDAOIU() == null && conexion == 11){
			this.setListadoDAOIU(new ListadosDAOInsertUpdate());
		}
		if(this.getOrdenesDAO() == null && conexion == 12){
			this.setOrdenesDAO(new OrdenesDAO());
		}
		
	}
	
	
	/**
	 * @return the empleadoDAO
	 */
	public EmpleadoDAO getEmpleadoDAO() {
		return empleadoDAO;
	}

	/**
	 * @param empleadoDAO the empleadoDAO to set
	 */
	public void setEmpleadoDAO(EmpleadoDAO empleadoDAO) {
		this.empleadoDAO = empleadoDAO;
	}

	/**
	 * @return the listadosDAO
	 */
	public ListadosDAO getListadosDAO() {
		return listadosDAO;
	}

	/**
	 * @param listadosDAO the listadosDAO to set
	 */
	public void setListadosDAO(ListadosDAO listadosDAO) {
		this.listadosDAO = listadosDAO;
	}

	/**
	 * @return the horarioDAO
	 */
	public HorarioDAO getHorarioDAO() {
		return horarioDAO;
	}

	/**
	 * @param horarioDAO the horarioDAO to set
	 */
	public void setHorarioDAO(HorarioDAO horarioDAO) {
		this.horarioDAO = horarioDAO;
	}

	/**
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @return the itemDAO
	 */
	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	/**
	 * @param itemDAO the itemDAO to set
	 */
	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	/**
	 * @return the configDAO
	 */
	public ConfigDAO getConfigDAO() {
		return configDAO;
	}


	/**
	 * @param configDAO the configDAO to set
	 */
	public void setConfigDAO(ConfigDAO configDAO) {
		this.configDAO = configDAO;
	}


	/**
	 * @return the tipoEstatusDAO
	 */
	public TipoEstatusDAO getTipoEstatusDAO() {
		return tipoEstatusDAO;
	}


	/**
	 * @param tipoEstatusDAO the tipoEstatusDAO to set
	 */
	public void setTipoEstatusDAO(TipoEstatusDAO tipoEstatusDAO) {
		this.tipoEstatusDAO = tipoEstatusDAO;
	}


	/**
	 * @return the perfilDAO
	 */
	public PerfilDAO getPerfilDAO() {
		return perfilDAO;
	}


	/**
	 * @param perfilDAO the perfilDAO to set
	 */
	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}


	/**
	 * @return the procesarImagen
	 */
	public ProcesarImagen getProcesarImagen() {
		return procesarImagen;
	}


	/**
	 * @param procesarImagen the procesarImagen to set
	 */
	public void setProcesarImagen(ProcesarImagen procesarImagen) {
		this.procesarImagen = procesarImagen;
	}


	public MovimientosDAO getMovimientosDAO() {
		return movimientosDAO;
	}


	public void setMovimientosDAO(MovimientosDAO movimientosDAO) {
		this.movimientosDAO = movimientosDAO;
	}
	
	/**
	 * @return the listadoDAOIU
	 */
	public ListadosDAOInsertUpdate getListadoDAOIU() {
		return listadoDAOIU;
	}


	/**
	 * @param listadoDAOIU the listadoDAOIU to set
	 */
	public void setListadoDAOIU(ListadosDAOInsertUpdate listadoDAOIU) {
		this.listadoDAOIU = listadoDAOIU;
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectUsersService(HttpServletRequest request, HttpServletResponse response){			
			Vector<UserDTO> usuarios = getUserDAO().selectUsers();			
			if(usuarios != null && usuarios.size() > 0){
				request.setAttribute("elementos", usuarios);
			}else{
				request.setAttribute("mensaje", "No se encontro registro");
			}
			Vector<UserConfigDTO> userConfigs = new Vector<>();
			userConfigs = getUserDAO().selectUserConfigDTO(true);
			if(userConfigs != null && userConfigs.size() > 0){
				request.setAttribute("initServices", userConfigs);
			}else{
				request.setAttribute("mensaje", "No se encontro registro");
			}
		
			Vector<PerfilDTO> perfiles = new Vector<>();
			perfiles = getPerfilDAO().selectPerfilesPorStatus(1);
			if(perfiles != null && perfiles.size() > 0){
				request.setAttribute("perfiles", perfiles);
			}else{
				request.setAttribute("mensaje", "No se encontro registro");
			}
			
			Vector<EmpleadoDTO> empleados = new Vector<>();
			empleados = getEmpleadoDAO().selectNombreIdEmpleados(0);
			if(empleados != null && empleados.size() > 0){
				request.setAttribute("empleados", empleados);
			}else{
				request.setAttribute("mensaje", "No se encontro registro");
			}
			
			/*if(usuarios != null){
				String json = new Gson().toJson(usuarios);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				try {
					response.getWriter().write(json);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}*/
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectDeptosOrdenesService(HttpServletRequest request, HttpServletResponse response){			
			Vector<DepartamentoDTO> deptos = getOrdenesDAO().selectDeptosDTO();
			if(deptos != null && deptos.size() > 0){
				request.setAttribute("items", deptos);
			}else{
				request.setAttribute("mensaje", "No se encontro registro");
			}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectDeptosComoboTecnicosOrdenesService(HttpServletRequest request, HttpServletResponse response){			
			Vector<DepartamentoDTO> deptos = getOrdenesDAO().selectDeptosDTO();
			if(deptos != null && deptos.size() > 0){
				request.setAttribute("departamentos", deptos);
			}else{
				request.setAttribute("mensaje", "No se encontro registro");
			}
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectLugaresOrdenesService(HttpServletRequest request, HttpServletResponse response){			
			Vector<LugarDTO> lugares = getOrdenesDAO().selectLugaresDTO();
			if(lugares != null && lugares.size() > 0){
				request.setAttribute("items", lugares);
			}else{
				request.setAttribute("mensaje", "No se encontro registro");
			}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectRadiosOrdenesService(HttpServletRequest request, HttpServletResponse response){	
			selectTecnicosComboRadioOrdenesService(request, response);
			Vector<RadioDTO> radiosDTO = getOrdenesDAO().selectRadiosDTO();
			if(radiosDTO != null && radiosDTO.size() > 0){
				request.setAttribute("items", radiosDTO);
			}else{
				request.setAttribute("mensaje", "No se encontro registro");
			}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectTecnicosComboRadioOrdenesService(HttpServletRequest request, HttpServletResponse response){		
		Vector<TecnicoDTO> tecnicosDTO = getOrdenesDAO().selectTecnicosDTO();
		if(tecnicosDTO != null && tecnicosDTO.size() > 0){
			request.setAttribute("tecnicos", tecnicosDTO);
		}else{
			request.setAttribute("mensaje", "No se encontro registro");
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectTecnicosOrdenesService(HttpServletRequest request, HttpServletResponse response){
		selectDeptosComoboTecnicosOrdenesService(request, response);
		Vector<TecnicoDTO> tecnicosDTO = getOrdenesDAO().selectTecnicosDTO();
		if(tecnicosDTO != null && tecnicosDTO.size() > 0){
			request.setAttribute("items", tecnicosDTO);
		}else{
			request.setAttribute("mensaje", "No se encontro registro");
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectVehiculosOrdenesService(HttpServletRequest request, HttpServletResponse response){			
			Vector<VehiculoDTO> vehiculoDTOs = getOrdenesDAO().selectVehiculosDTO();
			if(vehiculoDTOs != null && vehiculoDTOs.size() > 0){
				request.setAttribute("items", vehiculoDTOs);
			}else{
				request.setAttribute("mensaje", "No se encontro registro");
			}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void procesaFormItemOrdenesService(HttpServletRequest request, HttpServletResponse response){			
		String tipoItem = request.getParameter("itemDesc") + "DTO";
		switch (tipoItem) {//EN SIGULAR
		case "DepartamentoDTO":
			procesaFormItemDeptoOrdenesDTO(request, response);
			break;
		case "LugarDTO":
			procesaFormItemLugarOrdenesDTO(request, response);
			break;
		case "RadioDTO":
			procesaFormItemRadioOrdenesDTO(request, response);
			break;
		case "TecnicoDTO":
			procesaFormItemTecnicoOrdenesDTO(request, response);
			break;
		case "VehiculoDTO":
			procesaFormItemVehiculoOrdenesDTO(request, response);
			break;
			
		default:
			break;
		}
		
			
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void procesaFormItemDeptoOrdenesDTO(HttpServletRequest request, HttpServletResponse response){			
		DepartamentoDTO depto = new DepartamentoDTO();
		String campo = "";
		String missingFields = "";
		campo = "field1";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			depto.setDepartamentoId(Integer.parseInt(request.getParameter(campo)));
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field2";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			depto.setNombre(request.getParameter(campo));
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field3";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			depto.setSiglas(request.getParameter(campo));
		}else{
			missingFields += "missing: "+campo;
		}
		if(missingFields.length() > 0){
			System.out.println(missingFields);
		}
		int res = -1;
		if(depto.getDepartamentoId() == 0){			
			res = getOrdenesDAO().insertNewDepto(depto);							
		}else{
			if(depto.getDepartamentoId() > 0){				
				res = getOrdenesDAO().updateDeptoDTO(depto);								
			}else{
				System.out.println("error depto.getDepartamentoId() < 0");
			}
		}
		if(res > 0){
			try {
				response.getWriter().append("1");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().append("0");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}		
		System.out.println("Faltantes: " + missingFields);
		depto = null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void procesaFormItemLugarOrdenesDTO(HttpServletRequest request, HttpServletResponse response){			
		LugarDTO lugarDTO = new LugarDTO();
		String campo = "";
		String missingFields = "";
		campo = "field1";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			lugarDTO.setLugarId(Integer.parseInt(request.getParameter(campo)));
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field2";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			lugarDTO.setNombre(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field3";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			lugarDTO.setUbicacion(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field4";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			lugarDTO.setTipo(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		
		if(missingFields.length() > 0){
			System.out.println(missingFields);
		}
		int res = -1;
		if(lugarDTO.getLugarId() == 0){			
			res = getOrdenesDAO().insertNewLugar(lugarDTO);							
		}else{
			if(lugarDTO.getLugarId() > 0){				
				res = getOrdenesDAO().updateLugarDTO(lugarDTO);								
			}else{
				System.out.println("error depto.getDepartamentoId() < 0");
			}
		}
		if(res > 0){
			try {
				response.getWriter().append("1");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().append("0");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}		
		System.out.println("Faltantes: " + missingFields);
		lugarDTO = null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void procesaFormItemRadioOrdenesDTO(HttpServletRequest request, HttpServletResponse response){			
		RadioDTO radioDTO = new RadioDTO();
		String campo = "";
		String missingFields = "";
		campo = "field1";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			radioDTO.setRadioId(Integer.parseInt(request.getParameter(campo)));
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field2";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			radioDTO.setRfsi(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field3";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			radioDTO.setTipo(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field4";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			radioDTO.setMarca(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field5";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			radioDTO.setModelo(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field6";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			radioDTO.setSerie(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field7";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			radioDTO.getTecnicoDTO().setTecnicoId(Integer.parseInt(request.getParameter(campo)));
		}else{
			missingFields += "missing: "+campo;
		}
		
		if(missingFields.length() > 0){
			System.out.println(missingFields);
		}
		int res = -1;
		if(radioDTO.getRadioId() == 0){			
			res = getOrdenesDAO().insertNewRadioDTO(radioDTO);							
		}else{
			if(radioDTO.getRadioId() > 0){				
				res = getOrdenesDAO().updateRadioDTO(radioDTO);								
			}else{
				System.out.println("error depto.getDepartamentoId() < 0");
			}
		}
		if(res > 0){
			try {
				response.getWriter().append("1");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().append("0");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}		
		System.out.println("Faltantes: " + missingFields);
		radioDTO = null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void procesaFormItemTecnicoOrdenesDTO(HttpServletRequest request, HttpServletResponse response){			
		TecnicoDTO tecnicoDTO = new TecnicoDTO();
		String campo = "";
		String missingFields = "";
		campo = "field1";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			tecnicoDTO.setTecnicoId(Integer.parseInt(request.getParameter(campo)));
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field2";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			tecnicoDTO.setIniciales(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field3";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			tecnicoDTO.setTitulo(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field4";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			tecnicoDTO.setNombre(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field5";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			tecnicoDTO.setPuesto(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field6";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			tecnicoDTO.getDepartamentoDTO().setDepartamentoId(Integer.parseInt(request.getParameter(campo)));			
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field7";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			tecnicoDTO.setMando(request.getParameter(campo).toUpperCase());
		}else{
			tecnicoDTO.setMando("N/D");
			missingFields += "missing: "+campo;
		}
		campo = "field8";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			tecnicoDTO.setLicencia(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		if(missingFields.length() > 0){
			System.out.println(missingFields);
		}else{
			System.out.println("Sin campos faltantes");
		}
		int res = -1;
		if(tecnicoDTO.getTecnicoId() == 0){			
			res = getOrdenesDAO().insertNewTecnicoDTO(tecnicoDTO);							
		}else{
			if(tecnicoDTO.getTecnicoId() > 0){				
				res = getOrdenesDAO().updateTecnicoDTO(tecnicoDTO);								
			}else{
				System.out.println("error depto.getDepartamentoId() < 0");
			}
		}
		if(res > 0){
			try {
				response.getWriter().append("1");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().append("0");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}		
		System.out.println("Faltantes: " + missingFields);
		tecnicoDTO = null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void procesaFormItemVehiculoOrdenesDTO(HttpServletRequest request, HttpServletResponse response){			
		VehiculoDTO vehiculoDTO = new VehiculoDTO();
		String campo = "";
		String missingFields = "";
		campo = "field1";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			vehiculoDTO.setVehiculoId(Integer.parseInt(request.getParameter(campo)));
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field2";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			vehiculoDTO.setNombre(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field3";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			vehiculoDTO.setTipo(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field4";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			vehiculoDTO.setMarca(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field5";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			vehiculoDTO.setLinea(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field6";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			vehiculoDTO.setModelo(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field7";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			vehiculoDTO.setPlacas(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}campo = "field8";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			vehiculoDTO.setInventario(request.getParameter(campo).toUpperCase());
		}else{
			vehiculoDTO.setInventario("N/D");			
			missingFields += "missing: "+campo;
		}
		campo = "field9";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			vehiculoDTO.setNumPolizaSeguro(request.getParameter(campo).toUpperCase());
		}else{
			missingFields += "missing: "+campo;
		}
		campo = "field10";
		if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
			vehiculoDTO.setActivo(String.valueOf(request.getParameter(campo)).charAt(0));
		}else{
			missingFields += "missing: "+campo;
		}
		
		if(missingFields.length() > 0){
			System.out.println(missingFields);
		}
		int res = -1;
		if(vehiculoDTO.getVehiculoId() == 0){			
			res = getOrdenesDAO().insertNewVehiculoDTO(vehiculoDTO);							
		}else{
			if(vehiculoDTO.getVehiculoId() > 0){				
				res = getOrdenesDAO().updateVehiculoDTO(vehiculoDTO);								
			}else{
				System.out.println("error depto.getDepartamentoId() < 0");
			}
		}
		if(res > 0){
			try {
				response.getWriter().append("1");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().append("0");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}		
		System.out.println("Faltantes: " + missingFields);
		vehiculoDTO = null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectPerfilUserService(HttpServletRequest request, HttpServletResponse response){			
		Vector<PerfilDTO> perfiles = getPerfilDAO().selectPerfiles();			
		if(perfiles != null && perfiles.size() > 0){
			request.setAttribute("elementos", perfiles);
		}else{
			request.setAttribute("mensaje", "No se encontro registro");
		}
}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectEmpleadoService(HttpServletRequest request, HttpServletResponse response){
		String empleadoSku = request.getParameter("skuEmpleado");
		if(empleadoSku != null && !empleadoSku.isEmpty()){
			EmpleadoDTO empleado = getEmpleadoDAO().selectEmpleadoPorSku(empleadoSku, 1);
			if(empleado != null){
				String json = new Gson().toJson(empleado);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				try {
					response.getWriter().write(json);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			try {
				response.getWriter().append("-2");
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
	public void selectEmpleadosConSinUsario(HttpServletRequest request, HttpServletResponse response){


		String conSinUsuario = request.getParameter("usuario");
		if(conSinUsuario != null && !conSinUsuario.isEmpty()){
			Vector<EmpleadoDTO> empleados = null;
			if(conSinUsuario.equals("si")){
				empleados = getEmpleadoDAO().selectNombreIdEmpleadosSinUsuario(0);				
			}else{
				empleados = getEmpleadoDAO().selectNombreIdEmpleados(0);
			}
			if(empleados != null && empleados.size() > 0){
				String json = new Gson().toJson(empleados);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				try {
					response.getWriter().write(json);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void nuevoModeloService(HttpServletRequest request, HttpServletResponse response){		
		ModeloItemDTO modelo = new ModeloItemDTO();
		modelo.setNombreModeloItem(request.getParameter("nombreModelo") != null ? request.getParameter("nombreModelo") : "");
		modelo.setSkuModeloItem(request.getParameter("sku") != null ? Integer.parseInt(request.getParameter("sku")) : 0);
		modelo.getTipoItem().setIdTipoItem(request.getParameter("tiposItems") != null ? Integer.parseInt(request.getParameter("tiposItems")) : 0);
		modelo.setStatusModelo(request.getParameter("statusModelo") != null ? Integer.parseInt(request.getParameter("statusModelo")) : 0);
		int res = getListadoDAOIU().insertModeloItemDTO(modelo);
		try {
			response.getWriter().append(String.valueOf(res));
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void nuevoUsuarioService(HttpServletRequest request, HttpServletResponse response){
		UserSimpleDTO userSimpleDTO = verificaFormUsuario(request, response);	
		if(userSimpleDTO != null){
			if(userSimpleDTO.getIdUser() > 0) {
				//actualiza
				userSimpleDTO = getUserDAO().actualizaUserSimpleDTO(userSimpleDTO);
			}else{
				//crea
				userSimpleDTO = getUserDAO().insertUserSimpleDTO(userSimpleDTO);				
			}		
			
			if(userSimpleDTO.getIdUser() > 0){
			//actualiza la foto en base a un usuario creado
				
				if(userSimpleDTO.getUserAvatar().getImgPart() != null){
					int res = actualizaImagenUsuario(userSimpleDTO);
					if (res > 0) {
						System.out.println("foto procesada correctamente");		
						System.out.println("usuario con foto insertado correctamente");
						try {
							response.getWriter().append("1");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}else{
//							erroresString += "Foto,";
						try {
							response.getWriter().append("0");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}else{
					System.out.println("avatar2 == null");				
					System.out.println("usuario insertado correctamente");
					try {
						response.getWriter().append("1");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}			
				
			}else{
				try {
					response.getWriter().append("0");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public int actualizaImagenUsuario(UserSimpleDTO userSimpleDTO){
		int res = -1;
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(userSimpleDTO.getIdUser());		
		res = getUserDAO().actualizarAvatarUsuario(userDTO, userSimpleDTO.getUserAvatar().getImgPart());		
		return res;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectEmpleadoParaRegistroAsistenciaService(HttpServletRequest request, HttpServletResponse response){
		String empleadoSku = request.getParameter("skuEmpleado");
		if(empleadoSku != null && !empleadoSku.isEmpty()){
			EmpleadoDTO empleado = getEmpleadoDAO().selectEmpleadoPorSku(empleadoSku, 1);
			if(empleado != null){
				String json = new Gson().toJson(empleado);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				try {
					response.getWriter().write(json);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			try {
				response.getWriter().append("-2");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public UserSimpleDTO verificaFormUsuario(HttpServletRequest request, HttpServletResponse response){
		UserSimpleDTO userDTO = null;
		String errores = null; 
		System.out.println(request.getParameter("login"));
		if(request.getParameter("login") != null && !request.getParameter("login").isEmpty()){
			userDTO = new UserSimpleDTO();
			if(request.getParameter("idUserHidden") != null && !request.getParameter("idUserHidden").isEmpty()){
				userDTO.setIdUser(Integer.parseInt(request.getParameter("idUserHidden")));
			}else{
//				errores = ", idUserHidden";
			}
			userDTO.setLogin(request.getParameter("login"));
			if(request.getParameter("perfil") != null && !request.getParameter("perfil").isEmpty()){
				userDTO.getUserProfile().setIdPerfil(Integer.parseInt(request.getParameter("perfil")));
			}else{
				errores = "perfil";
			}
			if(request.getParameter("initService") != null && !request.getParameter("initService").isEmpty()){
				userDTO.setFkIdUserConfig(Integer.parseInt(request.getParameter("initService")));
			}else{
				errores = ", initService";
			}
			if(request.getParameter("name") != null && !request.getParameter("name").isEmpty()){
				userDTO.setName(request.getParameter("name"));
			}else{
				errores = ", name";
			}
			if(request.getParameter("email") != null && !request.getParameter("email").isEmpty()){
				userDTO.setEmail(request.getParameter("email"));
			}else{
				errores = ", email";
			}
			if(request.getParameter("timeOut") != null && !request.getParameter("timeOut").isEmpty()){
				userDTO.setSessionTimeOut(Integer.parseInt(request.getParameter("timeOut")));
			}else{
				errores = ", timeOut";
			}
			if(request.getParameter("activo") != null && !request.getParameter("activo").isEmpty()){
				userDTO.setActive(request.getParameter("activo").charAt(0));
			}else{
				userDTO.setActive('N');
			}
			try {
				if(request.getPart("userAvatar") != null && request.getPart("userAvatar").getSize() > 0){
					userDTO.getUserAvatar().setImgPart(getProcesarImagen().procesarImagenRequest(request, response, "userAvatar"));
				}else{
					errores = ", userAvatar";
				}
			} catch (IOException e) {				
				e.printStackTrace();
			} catch (ServletException e) {				
				e.printStackTrace();
			}
			if(request.getParameter("idEmpleado") != null && !request.getParameter("idEmpleado").isEmpty()){
				userDTO.setFkIdEmpleado(Integer.parseInt(request.getParameter("idEmpleado")));
			}else{
				errores = ", idEmpleado";
			}
		}else{
			errores = ", login";
		}
		if(errores != null && !errores.isEmpty()){
			System.err.println(errores);
		}
		return userDTO;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void buscarEmpleado(HttpServletRequest request, HttpServletResponse response){	
		int numLimit = (request.getParameter("numLimit") != null &&  !request.getParameter("numLimit").equals("") &&  request.getParameter("numLimit").length() > 0 ?  Integer.parseInt( request.getParameter("numLimit")): 30);
		int ultimoE = 0;
		String skuEmpleado = request.getParameter("skuEmpleado") != null &&  !request.getParameter("skuEmpleado").equals("") &&  request.getParameter("skuEmpleado").length() > 0 ?  (String) request.getParameter("skuEmpleado") : null ;
		EmpleadoDTO persona = null;
		if(skuEmpleado != null) {			
			persona = getEmpleadoDAO().selectEmpleadoPorSku(skuEmpleado,numLimit);
			if (persona != null){
				System.out.println("datos bd  " + persona.getNombreEmpleado() );
				Vector <EmpleadoDTO>  v = new Vector<EmpleadoDTO>();
				v.add(persona);
				request.setAttribute("empleados", v);
				request.setAttribute("filtroLimit", numLimit);
			}else{
				request.setAttribute("mensaje", "No se encontro registro");
			}
			request.setAttribute("filtroId",skuEmpleado);
		}else{ 
			String nombre = request.getParameter("nombre") != null &&  !request.getParameter("nombre").equals("") &&  request.getParameter("nombre").length() > 0 ?   (String) request.getParameter("nombre"): null;
			if(nombre != null){
				Vector<EmpleadoDTO> v = null;
				v = getEmpleadoDAO().selectEmpleadoPorNombre(nombre,numLimit,ultimoE);
				if (v.size() > 0){
					//System.out.println("datos bd  " + v.getNombreEmpleado() );
					EmpleadoDTO ultimoEmpleado = v.lastElement();
					int idUltimoEmpleado = ultimoEmpleado.getIdEmpleado();
					request.setAttribute("empleados", v);
					request.setAttribute("filtroLimit", numLimit);
					request.setAttribute("ultEmpleado", idUltimoEmpleado);
				}else{
					request.setAttribute("mensaje", "No se encontro registro");
				}
				request.setAttribute("filtroNombre", nombre);

			}else {
				Vector<EmpleadoDTO> vtodos = null;
				vtodos = getEmpleadoDAO().selectEmpleadoTodos(numLimit);
				if(vtodos.size() > 0){
					EmpleadoDTO ultimoEmpleado = vtodos.lastElement();
					int idUltimoEmpleado = ultimoEmpleado.getIdEmpleado();
					int skuUltimoEmpleado = ultimoEmpleado.getSkuEmpleado();
					int ultSkuEmpleado = skuUltimoEmpleado+1;
					request.setAttribute("empleados",vtodos);
					request.setAttribute("filtroLimit", numLimit);
					request.setAttribute("ultEmpleado", idUltimoEmpleado);
					request.setAttribute("ultimoSkuEmpleado", ultSkuEmpleado);
				}
			}
		}
	}	
		
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void obtenerGeneros(HttpServletRequest request, HttpServletResponse response){
		Vector<GeneroDTO> vgeneros = new Vector<GeneroDTO>();
		vgeneros = getListadosDAO().selectGeneros();
		if(vgeneros.size() > 0){
			request.setAttribute("listadoGeneros", vgeneros);
		}else{
			request.setAttribute("mensaje", "No se encontro registro");
		}

	}	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void nuevoSkuEmpleado(HttpServletRequest request, HttpServletResponse response){
		int nuevoSku = -1;
		nuevoSku = getEmpleadoDAO().nuevoSkuEmpleado();
		if(nuevoSku > 0){
			System.out.println(nuevoSku);
			try {
				response.getWriter().append(String.valueOf(nuevoSku));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().write("No se encontro registro");
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
	public void selectGeneros(HttpServletRequest request, HttpServletResponse response){
		Vector<GeneroDTO> vgeneros = new Vector<GeneroDTO>();
		vgeneros = getListadosDAO().selectGeneros();
		if(vgeneros.size() > 0){
			String json = new Gson().toJson(vgeneros);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("VECTOR VACIO DE GENEROS");
			GeneroDTO genero = new GeneroDTO();
			genero.setNombreGenero("Sin generos!");
			vgeneros.add(genero);
			String json = new Gson().toJson(vgeneros);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    try {
				response.getWriter().write(json);
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
	public void selectEstadosCiviles(HttpServletRequest request, HttpServletResponse response){
		Vector<EstadoCivilDTO> vEstCiviles = new Vector<EstadoCivilDTO>();
		int idGenero = (request.getParameter("idGenero") != null ? Integer.parseInt(request.getParameter("idGenero")) : 0);
		//System.out.println("idGenero "+idGenero);
		vEstCiviles = getListadosDAO().selectEstadosCivilesPorGenero(idGenero);
		if(vEstCiviles.size() > 0){
			for (EstadoCivilDTO estadoCivilDTO : vEstCiviles) {
				System.out.println(estadoCivilDTO.getNombreEstadoCivil());
			}
			
		}else{
			System.out.println("VECTOR VACIO DE ESTADOS CIVILES");
			EstadoCivilDTO estCiv = new EstadoCivilDTO();
			estCiv.setNombreEstadoCivil("Sin Estado Civil");
			vEstCiviles.add(estCiv);
		}
		String json = new Gson().toJson(vEstCiviles);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void obtenerEstados(HttpServletRequest request, HttpServletResponse response){
		Vector<EntidadFederativaDTO> ventidades = new Vector<EntidadFederativaDTO>();
		ventidades = getListadosDAO().selectEntidadFederativa();
		if(ventidades.size() > 0){
			request.setAttribute("listadoEntidades", ventidades);
		}else{
			request.setAttribute("mensaje", "No se encontro registro");
		}

	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultarllamadasService(HttpServletRequest request, HttpServletResponse response){
		Vector<TotalTiposLlamadasDTO> llamadasTotales = new Vector<TotalTiposLlamadasDTO>();
		llamadasTotales = getListadosDAO().selectTotalesTiposDeLlamadasDTO();
		if(llamadasTotales.size() > 0){
			request.setAttribute("llamadas", llamadasTotales);
		}else{
			request.setAttribute("mensaje", "No se encontro llamadasTotales");
		}

	}
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultarllamadasFiltrosService(HttpServletRequest request, HttpServletResponse response){
		Vector<TotalTiposLlamadasDTO> llamadasTotales = new Vector<TotalTiposLlamadasDTO>();
		LocalDate ldi = LocalDate.now();
		LocalDate ldf = LocalDate.now();
		FechaDTO fechaInicialDTO = new FechaDTO();
		FechaDTO fechaFinalDTO = new FechaDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//		DateTimeFormatter formatterT = DateTimeFormatter.ofPattern("HH:mm:ss");
		formatter = formatter.withLocale(Locale.US);//01/01/2017
		if(request.getParameter("inputFechaInicial") != null && !request.getParameter("inputFechaInicial").isEmpty()){
			ldi = LocalDate.parse(request.getParameter("inputFechaInicial"), formatter);	
			fechaInicialDTO.setFechaString(request.getParameter("inputFechaInicial"));
		}		
		if(request.getParameter("inputFechaFinal") != null && !request.getParameter("inputFechaFinal").isEmpty()){
			ldf = LocalDate.parse(request.getParameter("inputFechaFinal"), formatter);		
			fechaFinalDTO.setFechaString(request.getParameter("inputFechaFinal"));
		}
		
		fechaInicialDTO.setFechaLD(ldi);
		fechaFinalDTO.setFechaLD(ldf);
		
		request.setAttribute("fechaInicial", fechaInicialDTO);
		request.setAttribute("fechaFinal", fechaFinalDTO);
		llamadasTotales = getListadosDAO().selectTotalesTiposDeLlamadasDTOFiltros(ldi, ldf);
		if(llamadasTotales != null && llamadasTotales.size() > 0){
			request.setAttribute("llamadas", llamadasTotales);
			String array = "";
			String totales = "";
			for (int i = 0; i < llamadasTotales.size(); i++) {
				if(i == 0){
					array = "['"+ llamadasTotales.get(i).getTipoLlamada().getNombreTipoLlamada()+"'";
					totales = "["+llamadasTotales.get(i).getTotalLlamadas();
				}else if(i > 0){
					array += ", '"+ llamadasTotales.get(i).getTipoLlamada().getNombreTipoLlamada()+"'";
					totales += ", "+llamadasTotales.get(i).getTotalLlamadas();
				}else{
					System.out.println("llamadasTotales error !?");
				}
			}
			array += "]";
			totales += "]";
			
			request.setAttribute("lblLlamadas", array);
			request.setAttribute("totalesLlamadas", totales);
		}else{
			request.setAttribute("mensaje", "No se encontro llamadasTotales");
		}

	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectEntidadesFederativas(HttpServletRequest request, HttpServletResponse response){
		Vector<EntidadFederativaDTO> ventidades = new Vector<EntidadFederativaDTO>();
		ventidades = getListadosDAO().selectEntidadFederativa();
		if(ventidades.size() > 0){
			String json = new Gson().toJson(ventidades);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("VECTOR VACIO DE ESTADOS");
			EntidadFederativaDTO edo = new EntidadFederativaDTO();
			edo.setNombreEntidadFederativa("Sin estados!");
			ventidades.add(edo);
			String json = new Gson().toJson(ventidades);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    try {
				response.getWriter().write(json);
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
	public void selectMunicipios(HttpServletRequest request, HttpServletResponse response){
		Vector<MunicipioDTO> vmunicipios = new Vector<MunicipioDTO>();
		int idEstado = (request.getParameter("idEstado") != null ? Integer.parseInt(request.getParameter("idEstado")) : 0);
		vmunicipios = getListadosDAO().selectMunicipiosPorEntidadFederativa(idEstado);
		if(vmunicipios.size() > 0){			
			String json = new Gson().toJson(vmunicipios);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("VECTOR VACIO DE MUNICIPIOS");
			MunicipioDTO mun = new MunicipioDTO();
			mun.setNombreMunicipio("Sin municipios");
			vmunicipios.add(mun);
			String json = new Gson().toJson(vmunicipios);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    try {
				response.getWriter().write(json);
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
	public void selectColonias(HttpServletRequest request, HttpServletResponse response){
		Vector<ColoniaDTO> vcolonias = new Vector<ColoniaDTO>();
		int idMunicipio = (request.getParameter("idMunicipio") != null ? Integer.parseInt(request.getParameter("idMunicipio")) : 0);
		vcolonias = getListadosDAO().selectColoniasPorMunicipios(idMunicipio);
		if(vcolonias.size() > 0){
			String json = new Gson().toJson(vcolonias);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			System.out.println("VECTOR VACIO DE COLONIAS");
			ColoniaDTO col = new ColoniaDTO();
			col.setNombreColonia("Sin colonias");
			vcolonias.add(col);
			String json = new Gson().toJson(vcolonias);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    try {
				response.getWriter().write(json);
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
	public void obtenerGruposSanguineos(HttpServletRequest request, HttpServletResponse response){
		Vector<GrupoSanguineoDTO> vgposanguineos = new Vector<GrupoSanguineoDTO>();
		vgposanguineos = getListadosDAO().selectGruposSanguineos();
		if(vgposanguineos.size() > 0){
			request.setAttribute("listadoGruposSanguineos", vgposanguineos);
		}else{
			request.setAttribute("mensaje", "No se encontro registro");
		}

	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void obtenerPeriodosEscolares(HttpServletRequest request, HttpServletResponse response){
		Vector<PeriodoEscolarDTO> vPerEscolcares = new Vector<PeriodoEscolarDTO>();
		vPerEscolcares = getListadosDAO().selectPeriodosEscolares();
		if(vPerEscolcares.size() > 0){
			request.setAttribute("listadoPeriodosEscolares", vPerEscolcares);
		}else{
			request.setAttribute("mensaje", "No se encontro registro");
		}

	}
	
	public void selectGradosEscolares(HttpServletRequest request, HttpServletResponse response){
		Vector<GradoEscolarDTO> vGdosEscolares = new Vector<GradoEscolarDTO>();
		int idPeriodoEscolar = (request.getParameter("idPeriodoEscolar") != null ? Integer.parseInt(request.getParameter("idPeriodoEscolar")) : 0);
		vGdosEscolares = getListadosDAO().selectGradosEscolaresPorPeriodoEscolar(idPeriodoEscolar);
		if(vGdosEscolares.size() > 0){
			for (GradoEscolarDTO gradoEscolarDTO : vGdosEscolares) {
				System.out.println(gradoEscolarDTO.getNombreGradoAcademico());
			}
			
		}else{
			System.out.println("VECTOR VACIO DE GRADOS ESCOLARES");
			GradoEscolarDTO gdoEsc = new GradoEscolarDTO();
			gdoEsc.setNombreGradoAcademico("Sin grados escolares");
			vGdosEscolares.add(gdoEsc);
		}
		String json = new Gson().toJson(vGdosEscolares);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void obtenerNivelesAcademicos(HttpServletRequest request, HttpServletResponse response){
		Vector<NivelAcademicoDTO> vNvelAcademicos = new Vector<NivelAcademicoDTO>();
		vNvelAcademicos = getListadosDAO().selectNivelesAcademicos();
		if(vNvelAcademicos.size() > 0){
			request.setAttribute("listadoNivelesAcademicos", vNvelAcademicos);
		}else{
			request.setAttribute("mensaje", "No se encontro registro");
		}

	}
	
	
	public void selectEspecialidadesAcademicas(HttpServletRequest request, HttpServletResponse response){
		Vector<EspecialidadDTO> vEspAcademicas = new Vector<EspecialidadDTO>();
		int idNivelAcademico = (request.getParameter("idNivelAcademico") != null ? Integer.parseInt(request.getParameter("idNivelAcademico")) : 0);
		vEspAcademicas = getListadosDAO().selectEspecialidadesAcademicasPorNivel(idNivelAcademico);
		if(vEspAcademicas.size() > 0){
			
			
		}else{
			System.out.println("VECTOR VACIO DE ESPECIALIDADES");
			EspecialidadDTO espAcad = new EspecialidadDTO();
			espAcad.setNombreEspecialidad("Sin especialidades academicas");
			vEspAcademicas.add(espAcad);
		}
		String json = new Gson().toJson(vEspAcademicas);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void nuevoEmpleadoService(HttpServletRequest request, HttpServletResponse response){
		EmpleadoDTO empleadoNuevo = new EmpleadoDTO();			
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		formatter = formatter.withLocale(Locale.US);//01/01/2017
		try{
			if(request.getParameter("idEmpleadoHidden") != null && request.getParameter("idEmpleadoHidden").length() > 0){
				if(HerramientasResultSet.tryParse(request.getParameter("skuEmpleadoHidden")) != null){
					empleadoNuevo.setIdEmpleado(Integer.parseInt(request.getParameter("idEmpleadoHidden")));
				}else{
					//TODO add error
				}
				
			}
			if(request.getParameter("nombreEmpleado") != null){
				empleadoNuevo.setNombreEmpleado(request.getParameter("nombreEmpleado"));
			}
			if(request.getParameter("apPaterno") != null){
				empleadoNuevo.setApPaternoEmpleado(request.getParameter("apPaterno"));
			}
			if(request.getParameter("apMaterno") != null){
				empleadoNuevo.setApMaternoEmpleado(request.getParameter("apMaterno"));
			}
			if(request.getParameter("skuEmpleadoHidden") != null && request.getParameter("skuEmpleadoHidden").length() > 0){
				if(HerramientasResultSet.tryParse(request.getParameter("skuEmpleadoHidden")) != null){
					empleadoNuevo.setSkuEmpleado(Integer.parseInt(request.getParameter("skuEmpleadoHidden")));
				}else{
					//TODO add error
				}
			}
			if(request.getParameter("cuip") != null){
				empleadoNuevo.setCuipEmpleado(request.getParameter("cuip").toUpperCase());
			}
			if(request.getParameter("curp") != null){
				empleadoNuevo.setCurpEmpleado(request.getParameter("curp").toUpperCase());
			}
			if(request.getParameter("rfcEmpleado") != null){
				empleadoNuevo.setRfcEmpleado(request.getParameter("rfcEmpleado").toUpperCase());
			}
			if(request.getParameter("fechaNac") != null && request.getParameter("fechaNac").length() > 0){
				empleadoNuevo.setFechaNacimientoEmpleado(LocalDate.parse(request.getParameter("fechaNac"), formatter));
			}
			if(request.getParameter("inputGenero") != null && request.getParameter("inputGenero").length() > 0){
				empleadoNuevo.getGeneroEmpleadoDTO().setIdGenero(Integer.parseInt(request.getParameter("inputGenero")));
			}
			if(request.getParameter("estadoCivil") != null && request.getParameter("estadoCivil").length() > 0){
				empleadoNuevo.getEdoCivilEmpladoDTO().setIdEstadoCivil(Integer.parseInt(request.getParameter("estadoCivil")));
			}
			if(request.getParameter("grupoSanguineo") != null && request.getParameter("estadoDomicilio").length() > 0){
				empleadoNuevo.getGrupoSanguineoEmpleadoDTO().setIdGrupoSanguineo(Integer.parseInt(request.getParameter("grupoSanguineo")));
			}
			if(request.getParameter("estadoDomicilio") != null && request.getParameter("estadoDomicilio").length() > 0){
				empleadoNuevo.getEntidadFederativaDomicilioEmpleadoDTO().setIdEntidadFederativa(Integer.parseInt(request.getParameter("estadoDomicilio")));
			}
			if(request.getParameter("municipioDomicilio") != null && request.getParameter("municipioDomicilio").length() > 0){
				empleadoNuevo.getMunicipioDomicilioEmpleadoDTO().setIdMunicipio(Integer.parseInt(request.getParameter("municipioDomicilio")));
			}
			if(request.getParameter("coloniaDomicilio") != null && request.getParameter("coloniaDomicilio").length() > 0){
				empleadoNuevo.getColoniaDomicilioEmpleadoDTO().setIdColonia(Integer.parseInt(request.getParameter("coloniaDomicilio")));
			}
			if(request.getParameter("cpDomicilio") != null && request.getParameter("cpDomicilio").length() > 0){
				empleadoNuevo.setCodigoPostalDomicilioEmpleado(Integer.parseInt(request.getParameter("cpDomicilio")));
			}
			if(request.getParameter("calleDomicilio") != null ){
				empleadoNuevo.getCalleDomicilioEmpleadoDTO().setNombreCalle(request.getParameter("calleDomicilio"));
			}
			/*if(request.getParameter("fkCalle") != null){
			empleadoNuevo.getCalleDomicilioEmpleadoDTO().setIdCalle(Integer.parseInt(request.getParameter("fkCalle")));
			}*/
			if(request.getParameter("numExtDomicilio") != null){
				empleadoNuevo.setNoExtDomicilioEmpleado(request.getParameter("numExtDomicilio"));
			}
			if(request.getParameter("numIntDomicilio") != null){
				empleadoNuevo.setNoIntDomicilioEmpleado(request.getParameter("numIntDomicilio"));
			}
			if(request.getParameter("numTelFijo") != null){
				empleadoNuevo.setTelFijoEmpleado(request.getParameter("numTelFijo"));
			}
			if(request.getParameter("numTelMovil") != null){
				empleadoNuevo.setTelMovilEmpleado(request.getParameter("numTelMovil"));
			}
			if(request.getParameter("correoe") != null){
				empleadoNuevo.setCorreoElectronicoEmpleado(request.getParameter("correoe"));
			}
			if(request.getParameter("nivelAcademicoEmpleado") != null && request.getParameter("nivelAcademicoEmpleado").length() > 0){
				empleadoNuevo.getNivelAcademicoEmpleadoDTO().setIdNivelAcademico(Integer.parseInt(request.getParameter("nivelAcademicoEmpleado")));
			}
			if(request.getParameter("especialidadEmpleado") != null && request.getParameter("especialidadEmpleado").length() > 0){
				empleadoNuevo.setEspecialidadDTO(new EspecialidadDTO());
				empleadoNuevo.getEspecialidadDTO().setIdEspecialidad(Integer.parseInt(request.getParameter("especialidadEmpleado")));
			}
			//System.out.println(1);
			if(request.getParameter("carreraTrunca") != null){
				if(request.getParameter("carreraTrunca").equals("on")){

					empleadoNuevo.getCarreraTruncaEmpleado().setEstatusInt(1);
				}else{
					empleadoNuevo.getCarreraTruncaEmpleado().setEstatusInt(0);
				}

			}
			//System.out.println(2);
			if(empleadoNuevo.getCarreraTruncaEmpleado().getEstatusInt() == 1){
				if(request.getParameter("tipoPeriodoEscolar") != null && request.getParameter("tipoPeriodoEscolar").length() > 0){
					empleadoNuevo.getPeridoEscolarEmpleadoDTO().setIdPeriodoEscolar(Integer.parseInt(request.getParameter("tipoPeriodoEscolar")));
				}else{
					System.out.println("Error tipoPeriodo");
				}

				if(request.getParameter("ultimoGradoEmpleado") != null && request.getParameter("ultimoGradoEmpleado").length() > 0){
					empleadoNuevo.getGradoPeridoEscolarEmpleadoDTO().getGradoDTO().setIdGradoEscolar(Integer.parseInt(request.getParameter("ultimoGradoEmpleado")));
				}else{
					System.out.println("Error ultimoGrado");
				}
			}else{
				empleadoNuevo.getPeridoEscolarEmpleadoDTO().setIdPeriodoEscolar(4);
				empleadoNuevo.getGradoPeridoEscolarEmpleadoDTO().getGradoDTO().setIdGradoEscolar(37);
			}
			//System.out.println(3);
			if(request.getParameter("tipoComprobanteEmpleado") != null){
				//			empleadoNuevo.setNombreEmpleado(request.getParameter("tipoComprobanteEmpleado"));
			}
			if(request.getParameter("documentoComprobanteEmpleado") != null){
				//empleadoNuevo.setDocumentoComprobanteInfoEmpleado(request.getParameter("documentoComprobanteEmpleado"));
			}
			if(request.getParameter("fechaAltaEmpleado") != null && request.getParameter("fechaAltaEmpleado").length() > 0){
				empleadoNuevo.setFechaAltaEmpleado(LocalDate.parse(request.getParameter("fechaAltaEmpleado"), formatter));
			}
			if(request.getParameter("fotoeEmpleado") != null){
				//empleadoNuevo.setFotoEmpleado(request.getParameter("fotoeEmpleado")));
			}
		}catch(Exception error){
			System.out.println(error);
			try {
				response.getWriter().append("-2");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//		int editarGlobal = -1;
		//System.out.println("Nombre y sku empleado nuevo" + empleadoNuevo.getNombreEmpleado() +  " ,  " + empleadoNuevo.getSkuEmpleado() + " , " + empleadoNuevo.getApPaternoEmpleado() + " , " + empleadoNuevo.getApMaternoEmpleado() );
		if (empleadoNuevo.getIdEmpleado() > 0){
			int editar = getEmpleadoDAO().actualizarEmpleado(empleadoNuevo);
			if(editar > 0){
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(editar == 0){
				System.out.println("Error en databasegateway, nuevoEmpleadoService: editaEmpleado");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ListadosDAO, nuevoEmpleadoService: editaEmpleado");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}else{
			int res = getEmpleadoDAO().insertaNuevoEmpleado(empleadoNuevo);
			if(res > 0){
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(res == 0){
				System.out.println("Error en databasegateway, nuevoEmpleadoService: insertaNuevoEmpleado");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ListadosDAO, nuevoEmpleadoService: insertaNuevoEmpleado");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}
		/*if(request.getParameter("existeCalle") != null){
			boolean existeCalle = false;
			existeCalle = Boolean.parseBoolean(request.getParameter("existeCalle")); 
			if(!existeCalle){
				listadosDAO.insertaCalleNueva(calleDTO);
			}
		}*/

	}
	
	public void buscaCurpEmpleado(HttpServletRequest request, HttpServletResponse response){
		String curp = request.getParameter("curpEmpleado");
		Vector<EmpleadoDTO> v = new Vector<EmpleadoDTO>();
		EmpleadoDTO emp = getEmpleadoDAO().selectEmpleadoPorCurp(curp, 1);
		if(emp != null){
			v.addElement(emp);
			String json = new Gson().toJson(v);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().append("0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void prepararVistaEmpleado (HttpServletRequest request, HttpServletResponse response){
		
	}
	
	public void consultarHorarios(HttpServletRequest request, HttpServletResponse response){ 
		Vector<HorarioDTO> vHorarios = new Vector<HorarioDTO>();
		vHorarios = getHorarioDAO().selectHorariosTodos();
		if (vHorarios.size()>0){
			request.setAttribute("horarios", vHorarios);			
		}else{
			request.setAttribute("mensaje", "No se encontro registro");
		}

	}
	
	public void buscarHorarios(HttpServletRequest request, HttpServletResponse response){
		Vector<ErroresDTO> vErrores = new Vector<ErroresDTO>();
		HashMap<String, String> hash = new HashMap<String, String>();
		String claveHorario = request.getParameter("claveHorario") != null && !request.getParameter("claveHorario").equals("") && request.getParameter("claveHorario").length() > 0 ? (String) request.getParameter("claveHorario") : "-1" ;
		if(!claveHorario.equals("-1")){
			hash.put("claveHorario", claveHorario);
		}
		Vector<HorarioDTO> Lista = getHorarioDAO().selectRegistrosHorariosFiltros(hash);
		if (Lista.size()>0){
			request.setAttribute("horarios", Lista);			
		}else{

			ErroresDTO error = new ErroresDTO();
			error.setTituloError("Sin registros!");
			error.setMensajeError("No se encontraron registros con los filtros especificados");
			vErrores.add(error);
			//			System.out.println("Error " + vErrores.size());
			request.setAttribute("errores", vErrores);
		}	
	}
	
	public void nuevoHorario(HttpServletRequest request, HttpServletResponse response, UserDTO usuario){
		HorarioDTO horarioNuevo = new HorarioDTO();			
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatterT = DateTimeFormatter.ofPattern("HH:mm:ss");
		formatter = formatter.withLocale(Locale.US);//01/01/2017
		try{
			if(request.getParameter("idHorarioHidden") != null && !request.getParameter("idHorarioHidden").isEmpty()){
				horarioNuevo.setIdHorario(Integer.parseInt(request.getParameter("idHorarioHidden")));
			}
			if(request.getParameter("noLaboral") != null && !request.getParameter("noLaboral").isEmpty()){
				if(request.getParameter("noLaboral").equals("1")){
					horarioNuevo.setHorarioNoLaboral(true);
				}else{
					horarioNuevo.setHorarioNoLaboral(false);
				}				
			}else{
				horarioNuevo.setHorarioNoLaboral(false);
			}
			if(!horarioNuevo.isHorarioNoLaboral()){
				if(request.getParameter("cveHorarioHidden") != null && !request.getParameter("cveHorarioHidden").isEmpty()){
					System.out.println("cve: " + request.getParameter("cveHorarioHidden"));
					horarioNuevo.setClaveHorario(request.getParameter("cveHorarioHidden"));
					if(horarioNuevo.getClaveHorario() != null && horarioNuevo.getClaveHorario().length() > 0){
						if(horarioNuevo.getClaveHorario().startsWith("T")){
							horarioNuevo.setPrefix(String.valueOf(horarioNuevo.getClaveHorario().charAt(0)));
							String[] a = horarioNuevo.getClaveHorario().split("T");
							if(a != null){
								if(a.length > 0){
									for(String s : a){						
										if(s != null && !s.isEmpty()){
											System.out.println(s);
											horarioNuevo.setSkuHorario(Integer.parseInt(s));
										}else{
											System.out.println("S ESTA VACIO!");
										}
									}//Termina ciclo for
								}else{
									System.out.println("a es < 0!");
								}
							}else{
								System.out.println("a == null!");
							}
						}else{
							System.out.println("Prefix de clave del horario diferente a T!");
						}
					}else{
						System.out.println("horarioNuevo.getClaveHorario() == null || horarioNuevo.getClaveHorario().lenght() <= 0! en " + this.getClass().getSimpleName() );
					}
					
				}
			}else{
				if(request.getParameter("cveHorario") != null && !request.getParameter("cveHorario").isEmpty()){
					horarioNuevo.setClaveHorario(request.getParameter("cveHorario"));
				}else{
					horarioNuevo.setClaveHorario("N/A");
				}
			}
			System.out.println("cve: " + horarioNuevo.getClaveHorario());
			if(request.getParameter("nombreHorario") != null && !request.getParameter("nombreHorario").isEmpty()){
				horarioNuevo.setNombreHorario(request.getParameter("nombreHorario"));
			}
			System.out.println("name: " + request.getParameter("nombreHorario"));
			
			if(request.getParameter("e1Horario") != null && !request.getParameter("e1Horario").isEmpty()){
				horarioNuevo.getHoraEntrada().setHoraLT(LocalTime.parse(request.getParameter("e1Horario"), formatterT));
				horarioNuevo.getHoraEntrada().setHoraString(horarioNuevo.getHoraEntrada().getHoraLT().format(formatterT));
			}
			if(request.getParameter("s1Horario") != null && !request.getParameter("s1Horario").isEmpty()){
				horarioNuevo.setHoraSalida(LocalTime.parse(request.getParameter("s1Horario"), formatterT));
				horarioNuevo.setHoraSalidaString(horarioNuevo.getHoraSalida().format(formatterT));
			}
			if(request.getParameter("hr1Horario") != null && !request.getParameter("hr1Horario").isEmpty()){
				horarioNuevo.setHoraRetardo(LocalTime.parse(request.getParameter("hr1Horario"), formatterT));
				horarioNuevo.setHoraRetardoString(horarioNuevo.getHoraRetardo().format(formatterT));
			}
			
			if(request.getParameter("horarioQuebrado") != null && !request.getParameter("horarioQuebrado").isEmpty()){
				System.out.println(request.getParameter("horarioQuebrado"));
				if(request.getParameter("horarioQuebrado").equals("3")){
					horarioNuevo.setHorarioQuebrado(true);
					horarioNuevo.getHorarioQuebradoEstatusDTO().setEstatusInt(3);
				}else{
					horarioNuevo.setHorarioQuebrado(false);
					horarioNuevo.getHorarioQuebradoEstatusDTO().setEstatusInt(4);
				}
			}else{
				horarioNuevo.setHorarioQuebrado(false);
				horarioNuevo.getHorarioQuebradoEstatusDTO().setEstatusInt(4);
			}
			
			
			if(horarioNuevo.isHorarioQuebrado()){
				if(request.getParameter("e2Horario") != null && !request.getParameter("e2Horario").isEmpty()){
					horarioNuevo.setHoraEntrada2(LocalTime.parse(request.getParameter("e2Horario"), formatterT));
					horarioNuevo.setHoraEntrada2String(horarioNuevo.getHoraEntrada2().format(formatterT));
				}
				if(request.getParameter("s2Horario") != null && !request.getParameter("s2Horario").isEmpty()){
					horarioNuevo.setHoraSalida2(LocalTime.parse(request.getParameter("s2Horario"), formatterT));
					horarioNuevo.setHoraSalida2String(horarioNuevo.getHoraSalida2().format(formatterT));
				}
				if(request.getParameter("hr2Horario") != null && !request.getParameter("hr2Horario").isEmpty()){
					horarioNuevo.setHoraRetardo2(LocalTime.parse(request.getParameter("hr2Horario"), formatterT));
					horarioNuevo.setHoraRetardo2String(horarioNuevo.getHoraRetardo2().format(formatterT));
				}
			}else{
				horarioNuevo.setHoraEntrada2(LocalTime.parse("00:00:00", formatterT));
				horarioNuevo.setHoraEntrada2String(horarioNuevo.getHoraEntrada2().format(formatterT));
				horarioNuevo.setHoraSalida2(LocalTime.parse("00:00:00", formatterT));
				horarioNuevo.setHoraSalida2String(horarioNuevo.getHoraSalida2().format(formatterT));
				horarioNuevo.setHoraRetardo2(LocalTime.parse("00:00:00", formatterT));
				horarioNuevo.setHoraRetardo2String(horarioNuevo.getHoraRetardo2().format(formatterT));
			}
			if(request.getParameter("statusHorario") != null && !request.getParameter("statusHorario").isEmpty()){				
				horarioNuevo.getTipoEstatusDTO().setEstatusInt(Integer.parseInt(request.getParameter("statusHorario")));				
			}else{
				horarioNuevo.getTipoEstatusDTO().setEstatusInt(5);
			}
			
			
			
		}catch(Exception error){
			System.out.println(error);
			try {
				response.getWriter().append("-2");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//		int editarGlobal = -1;
		//System.out.println("Nombre y sku empleado nuevo" + empleadoNuevo.getNombreEmpleado() +  " ,  " + empleadoNuevo.getSkuEmpleado() + " , " + empleadoNuevo.getApPaternoEmpleado() + " , " + empleadoNuevo.getApMaternoEmpleado() );
		if (horarioNuevo.getIdHorario() > 0){
			horarioNuevo.getFechaHoraActualizacion().getFecha().setFechaLD(LocalDate.now());
			horarioNuevo.getFechaHoraActualizacion().getHora().setHoraLT(LocalTime.now());
			
			horarioNuevo.getUsuarioActualizacion().setIdUser(usuario.getUserId());
			int editar = getHorarioDAO().actualizarHorario(horarioNuevo);
			if(editar > 0){
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(editar == 0){
				System.out.println("Error en databasegateway, nuevoEmpleadoService: editaEmpleado");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ListadosDAO, nuevoEmpleadoService: editaEmpleado");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}else{
			horarioNuevo.getFechaHoraCreacion().getFecha().setFechaLD(LocalDate.now());
			horarioNuevo.getFechaHoraCreacion().getHora().setHoraLT(LocalTime.now());
			horarioNuevo.getFechaHoraActualizacion().getFecha().setFechaLD(LocalDate.now());
			horarioNuevo.getUsuarioCreacion().setIdUser(usuario.getUserId());
			int res = getHorarioDAO().insertaNuevoHorario(horarioNuevo);
			if(res > 0){
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(res == 0){
				System.out.println("Error en databasegateway, nuevoEmpleadoService: insertaNuevoEmpleado");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ListadosDAO, nuevoEmpleadoService: insertaNuevoEmpleado");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void consultaSkuNuevoHorario(HttpServletRequest request, HttpServletResponse response){ 
		String skuNuevoHorario = "";
		String prefix = request.getParameter("prefix");
		if(prefix != null){
			skuNuevoHorario = getHorarioDAO().selectSkuNuevoHorario(prefix);
			if (skuNuevoHorario.length()>0){
				try {
					response.getWriter().append(skuNuevoHorario);
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else{
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}else{
			try {
				response.getWriter().append("0");
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
	public void actualizarDatosUsuario(HttpServletRequest request, HttpServletResponse response){ 
		int res = -1;
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO)session.getAttribute("user");		
		String erroresString = "";
//		String mensajesEstring = "";
		if(user != null){
			String pswd = request.getParameter("passwordField2");
			if(pswd != null && pswd.length() > 0 && !pswd.equals("1111112")){
				user.setPassword(pswd);				
			}else{
				System.out.println("pswd == null");
			}
			if(user.getPassword() != null && user.getPassword().length() > 0){
				res = getUserDAO().actualizarPswdUsuario(user);
				if (res>0){
						
				}else{					
					erroresString += "Password,";
				}
			}
			ProcesarImagen pi = new ProcesarImagen();
			Part imgAvatar = pi.procesarImagenRequest(request, response, "userAvatar");
			if(imgAvatar != null){
				int row = -1;
				row = getUserDAO().actualizarAvatarUsuario(user, imgAvatar);
				if (row > 0) {
							
				}else{
					erroresString += "Foto,";
				}
			}else{
				System.out.println("avatar == null");
			}
			
			if(erroresString.length() > 0){
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
			
		}else{
			try {
				response.getWriter().append("-1");
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}	
	

	@SuppressWarnings("unused")
	private String getFileName(final Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(
						content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectTiposEstatus(HttpServletRequest request, HttpServletResponse response){
		String app = request.getParameter("app");
		if(app != null && !app.equals("") && app.length() > 0){
			Vector<TipoEstatusDTO> vTpermisos = new Vector<TipoEstatusDTO>();
			vTpermisos = getTipoEstatusDAO().selectTiposEstatusDTO(app);
			if(vTpermisos.size() > 0){			
				String json = new Gson().toJson(vTpermisos);
			    response.setContentType("application/json");
			    response.setCharacterEncoding("UTF-8");
			    try {
					response.getWriter().write(json);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					response.getWriter().append("0");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			try {
				response.getWriter().append("-1");
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
	public void selectHorariosEmpleados(HttpServletRequest request, HttpServletResponse response){
		EmpleadoHorarioDAO empleadoHorarioDAO = new EmpleadoHorarioDAO();		
		Vector<EmpleadoHorarioDTO> empleadosHorarios = empleadoHorarioDAO.selectEmpleadosHorariosDTO();
		if(empleadosHorarios != null && empleadosHorarios.size() > 0){			
			request.setAttribute("empleadosHorarios", empleadosHorarios);			
		}else{
			System.out.println("MENOR A 0");
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectHorariosEmpleadosFiltros(HttpServletRequest request, HttpServletResponse response){
		EmpleadoHorarioDAO empleadoHorarioDAO = new EmpleadoHorarioDAO();	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("fechaDe", request.getParameter("fechaDe"));
		map.put("idEmpleado", request.getParameter("idEmpleado"));
		map.put("nombreEmpleado", request.getParameter("nombreEmpleado"));		
		Vector<EmpleadoHorarioDTO> empleadosHorarios = empleadoHorarioDAO.selectEmpleadosHorariosDTO(map);
		if(empleadosHorarios != null && empleadosHorarios.size() > 0){			
			request.setAttribute("empleadosHorarios", empleadosHorarios);			
		}else{
			System.out.println("MENOR A 0");
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public ConfigCamposTipoItemDTO inicializaJSPItems(HttpServletRequest request, HttpServletResponse response){
		ConfigCamposTipoItemDTO configCamposItem = null;
		int idTipoItem = Integer.parseInt(request.getParameter("tipoItem"));
		if(idTipoItem > 0){
			TipoItemDTO tipoItem = getItemDAO().selectTipoItem(idTipoItem);
			if(tipoItem != null){
				HttpSession session = request.getSession();
				session.setAttribute("tipoItem", tipoItem);		
				configCamposItem = getConfigDAO().selectConfigCamposItem(tipoItem.getIdTipoItem());
				if(configCamposItem != null){			
					request.setAttribute("configCampos", configCamposItem);					
					selectTipoEstatusPorTipoItem(idTipoItem, configCamposItem, request);					
					if(configCamposItem.getConfigCampoMarcaItem().isMostrar()){						
						selectMarcasItemPorTipoItem(request, response, tipoItem);
					}
					if(configCamposItem.getConfigCampoModeloItem().isMostrar()){
						selectModelosItemsPorTipoItem(request, response, tipoItem);
					}
					if(configCamposItem.getConfigCampoLineaItem().isMostrar()){
						selectLineasItemPorTipoItem(request, response, tipoItem);
					}
					if(configCamposItem.getConfigCampoStatusItem().isMostrar()){
						selectStatusPorTipoItem(request, response, tipoItem, "items_status");
					}
					if(configCamposItem.getConfigCampoStatusItem().isMostrar()){
						selectStatusPorTipoItem(request, response, tipoItem, "items_situacion");
					}
					if(configCamposItem.getConfigCampoStatusItem().isMostrar()){
						selectStatusPorTipoItem(request, response, tipoItem, "items_ubicacion");
					}
				}else{
					System.out.println("SIN CONFIGURACION DE CAMPOS PARA EL TIPO DE ITEM");
				}
			}else{
				System.out.println("SIN tipo de item encontrado en la bd: tipoItem == null");
			}
		}else{
			System.out.println("SIN tipo de item definido");
		}
		return configCamposItem;
	}
	
	
	
	/**
	 * TIENE QUE ESTAR INICIALIZADO EL configCamposItem
	 * @param request
	 * @param response
	 */
	public void selectItems(HttpServletRequest request, HttpServletResponse response, ConfigCamposTipoItemDTO configCamposItem){
		int idTipoItem = -1;
		if(configCamposItem != null){
			if(configCamposItem.getIdTipoItem() > 0){
				idTipoItem = configCamposItem.getIdTipoItem() ;
			}else{
				idTipoItem = Integer.parseInt(request.getParameter("tipoItem"));
			}
		}else{
			idTipoItem = Integer.parseInt(request.getParameter("tipoItem"));
			System.out.println("SIN CONFIGURACION DE CAMPOS PARA EL TIPO DE ITEM 0");
		}
		if(idTipoItem > 0){
			HttpSession session = request.getSession();
			TipoItemDTO tipoItem = (TipoItemDTO) session.getAttribute("tipoItem");
			if(tipoItem != null){				
				if(configCamposItem != null){					
					Vector<ItemDTO> items = getItemDAO().selectItems(tipoItem.getIdTipoItem(), configCamposItem);					
					if(items != null && items.size() > 0){			
						request.setAttribute("items", items);			
					}else{
						System.out.println("resultado de vector por tipo de item MENOR A 0");
					}
				}else{
					System.out.println("SIN CONFIGURACION DE CAMPOS PARA EL TIPO DE ITEM 1");
				}
			}else{
				System.out.println("SIN tipo de item encontrado en la bd: tipoItem == null");
			}
		}else{
			System.out.println("SIN tipo de item definido");
		}
		
	}
	
	
	/**
	 * TIENE SELECCIONA LOS PARTNERS EN BASE AL TIOPO DE PARTNERS
	 * @param request
	 * @param response
	 */
	public void selectPartnersPorTipo(HttpServletRequest request, HttpServletResponse response){
		int tipoPartner = -1;
		tipoPartner = Integer.parseInt(request.getParameter("tipoPartner"));
		if(tipoPartner > 0){						
			Vector<PartnerDTO> partners = getPartnerDAO().selectPartnersPorTipo(tipoPartner);					
			if(partners != null && partners.size() > 0){			
				request.setAttribute("partners", partners);			
			}else{
				System.out.println("resultado de vector por tipo de item MENOR A 0");
			}
		}else{
			System.out.println("SIN tipo de item definido");
		}
		
	}
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectItems(HttpServletRequest request, HttpServletResponse response){
		int idTipoItem = Integer.parseInt(request.getParameter("tipoItem"));
		if(idTipoItem > 0){
			TipoItemDTO tipoItem = getItemDAO().selectTipoItem(idTipoItem);
			if(tipoItem != null){
				HttpSession session = request.getSession();
				session.setAttribute("tipoItem", tipoItem);		
				ConfigCamposTipoItemDTO configCamposItem = getConfigDAO().selectConfigCamposItem(tipoItem.getIdTipoItem());
				if(configCamposItem != null){
					request.setAttribute("configCampos", configCamposItem);
					Vector<ItemDTO> items = getItemDAO().selectItems(tipoItem.getIdTipoItem(), configCamposItem);
//					selectTipoEstatusPorTipoItem(idTipoItem, configCamposItem, request);
					if(items != null && items.size() > 0){			
						request.setAttribute("items", items);			
					}else{
						System.out.println("resultado de vector por tipo de item MENOR A 0");
					}
				}else{
					System.out.println("SIN CONFIGURACION DE CAMPOS PARA EL TIPO DE ITEM");
				}
			}else{
				System.out.println("SIN tipo de item encontrado en la bd: tipoItem == null");
			}
		}else{
			System.out.println("SIN tipo de item definido");
		}
		
	}	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectItemsTipos(HttpServletRequest request, HttpServletResponse response){		
			Vector<TipoItemDTO> tiposItems = getItemDAO().selectTiposItems();
			if(tiposItems != null && tiposItems.size() > 0){
				HttpSession session = request.getSession();
				session.setAttribute("itemsTipos", tiposItems);						
			}else{
				System.out.println("SIN tipos de items encontrados en la bd: tiposItems == null");
			}
		
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectModelosItems(HttpServletRequest request, HttpServletResponse response){	
		Vector<ModeloItemDTO> modelosItems = getItemDAO().selectModelosItems();
		if(modelosItems != null){
			if(modelosItems.size() > 0){
				request.setAttribute("modelosItems", modelosItems);
			}else{
				System.out.println("el vector de modelosItem es menor o igual  a 0");
			}
		}else{
			System.out.println("el vector de modelosItem es menor o igual  a 0");
		}


	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectModelosItemsPorTipoItem(HttpServletRequest request, HttpServletResponse response, TipoItemDTO tipoItem){	
		Vector<ModeloItemDTO> modelosItems = getItemDAO().selectModelosItemsPorTipoItem(tipoItem);
		if(modelosItems != null){
			if(modelosItems.size() > 0){
				request.setAttribute("modelosItems", modelosItems);
			}else{
				System.out.println("el vector de modelosItem es menor o igual  a 0");
			}
		}else{
			System.out.println("el vector de modelosItem es menor o igual  a 0");
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectLineasItemPorTipoItem(HttpServletRequest request, HttpServletResponse response, TipoItemDTO tipoItem){	
		Vector<LineaItemDTO> lineasItem = getItemDAO().selectLineasItemPorTipoItem(tipoItem);
		if(lineasItem != null){
			if(lineasItem.size() > 0){
				request.setAttribute("lineasItem", lineasItem);
			}else{
				System.out.println("el vector de modelosItem es menor o igual  a 0");
			}
		}else{
			System.out.println("el vector de modelosItem es menor o igual  a 0");
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectStatusPorTipoItem(HttpServletRequest request, HttpServletResponse response, TipoItemDTO tipoItem, String aplicacion){	
		Vector<TipoEstatusDTO> tiposEstatuses = getItemDAO().selectStatusesPorTipoItem(tipoItem, aplicacion);
		if(tiposEstatuses != null){
			if(tiposEstatuses.size() > 0){
				request.setAttribute(aplicacion, tiposEstatuses);
			}else{
				System.out.println("el vector de statusesItem es menor o igual  a 0");
			}
		}else{
			System.out.println("el vector de statusesItem es menor o igual  a 0");
		}
	}
	
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectMarcasItemPorTipoItem(HttpServletRequest request, HttpServletResponse response, TipoItemDTO tipoItem){	
		Vector<MarcaItemDTO> marcasItem = getItemDAO().selectMarcasItemDTOPorTipoItem(tipoItem);
		if(marcasItem != null){
			if(marcasItem.size() > 0){
				request.setAttribute("marcasItem", marcasItem);
			}else{
				System.out.println("el vector de modelosItem es menor o igual  a 0");
			}
		}else{
			System.out.println("el vector de modelosItem es menor o igual  a 0");
		}


	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectMarcasItems(HttpServletRequest request, HttpServletResponse response){
		Vector<MarcaItemDTO> marcasItems = getItemDAO().selectMarcasItems();
		if(marcasItems != null){
			if(marcasItems.size() > 0){
				request.setAttribute("marcasItems", marcasItems);
			}else{
				System.out.println("el vector de marcasItem es menor o igual  a 0");
			}
		}else{
			System.out.println("el vector de marcasItem es menor o igual  a 0");
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectMaxIdItem(HttpServletRequest request, HttpServletResponse response){
		if(request.getParameter("typeId") != null && !request.getParameter("typeId").isEmpty()){
			int idTipoItem = Integer.parseInt(request.getParameter("typeId"));
			if(idTipoItem > 0){
				TipoItemDTO tipoItem = getItemDAO().selectTipoItem(idTipoItem);
				if(tipoItem != null){
					HttpSession session = request.getSession();
					session.setAttribute("tipoItem", tipoItem);		
					//				ConfigCamposTipoItemDTO configCamposItem = getConfigDAO().selectConfigCamposItem(tipoItem.getIdTipoItem());		
					int max_id = getItemDAO().selectMaxIdTipoItem(tipoItem.getIdTipoItem());
					//					selectTipoEstatusPorTipoItem(idTipoItem, configCamposItem, request);
					if(max_id != 0 && max_id > 0){	
						int newId = max_id + 1;
						try {
							response.getWriter().append(String.valueOf(newId));
						} catch (IOException e) {

							e.printStackTrace();
						}		
					}else{
						System.out.println("resultado de vector por tipo de item MENOR A 0");
						try {
							response.getWriter().append("0");
						} catch (IOException e) {

							e.printStackTrace();
						}
					}

				}else{
					System.out.println("SIN tipo de item encontrado en la bd: tipoItem == null");
				}
			}else{
				System.out.println("SIN tipo de item definido");
			}
		}else{
			System.out.println("SIN tipo de item encontrado en la url: tipoItem == null");
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void selectTipoEstatusPorTipoItem(HttpServletRequest request, HttpServletResponse response){
		int idTipoItem = Integer.parseInt(request.getParameter("tipoItem"));
		if(idTipoItem > 0){
			Vector <TipoEstatusDTO> tiposEstatusesDTOSituacion = getTipoEstatusDAO().selectTiposEstatusesDTO(idTipoItem, "items_situacion");
			Vector <TipoEstatusDTO> tiposEstatusesDTOUbicacion = getTipoEstatusDAO().selectTiposEstatusesDTO(idTipoItem, "items_ubicacion");
			Vector <TipoEstatusDTO> tiposEstatusesDTOStatus = getTipoEstatusDAO().selectTiposEstatusesDTO(idTipoItem, "items_status");
			Vector <TipoEstatusDTO> tiposEstatusesDTOPropietario = getTipoEstatusDAO().selectTiposEstatusesDTO(idTipoItem, "items_propietario");
			TipoEstatusDTO tipoError = new TipoEstatusDTO();
			tipoError.setEstatusString("Sin datos!");
			
			if(tiposEstatusesDTOSituacion != null && tiposEstatusesDTOSituacion.size() > 0){
				request.setAttribute("items_situacion", tiposEstatusesDTOSituacion);
			}else{				
				request.setAttribute("items_situacion", tipoError);
			}
			if(tiposEstatusesDTOUbicacion != null && tiposEstatusesDTOUbicacion.size() > 0){
				request.setAttribute("items_ubicacion", tiposEstatusesDTOUbicacion);
			}else{
				request.setAttribute("items_situacion", tipoError);
			}
			if(tiposEstatusesDTOStatus != null && tiposEstatusesDTOStatus.size() > 0){
				request.setAttribute("items_status", tiposEstatusesDTOStatus);
			}else{
				request.setAttribute("items_situacion", tipoError);
			}
			if(tiposEstatusesDTOPropietario != null && tiposEstatusesDTOPropietario.size() > 0){
				request.setAttribute("items_propietario", tiposEstatusesDTOPropietario);
			}else{
				request.setAttribute("items_situacion", tipoError);
			}
						
		}else{
			System.out.println("SIN tipo de item definido");
		}
		
	}
	
	public void selectTipoEstatusPorTipoItem(int tipoItem, ConfigCamposTipoItemDTO configCampos, HttpServletRequest request){
		int idTipoItem = tipoItem;		
		if(idTipoItem > 0){
			TipoEstatusDTO tipoError = new TipoEstatusDTO();
			tipoError.setEstatusString("Sin datos!");
			TipoEstatusDTO tipoDesha = new TipoEstatusDTO();
			tipoError.setEstatusString("Deshabilitado!");
			if(configCampos.getConfigCampoSituacionItem().isMostrar()){
				Vector <TipoEstatusDTO> tiposEstatusesDTOSituacion = getTipoEstatusDAO().selectTiposEstatusesDTO(idTipoItem, "items_situacion");
				if(tiposEstatusesDTOSituacion != null && tiposEstatusesDTOSituacion.size() > 0){
					request.setAttribute("items_situacion", tiposEstatusesDTOSituacion);
				}else{	
					tiposEstatusesDTOSituacion = new Vector<TipoEstatusDTO>();
					tiposEstatusesDTOSituacion.add(tipoError);
					request.setAttribute("items_situacion", tiposEstatusesDTOSituacion);
				}
			
			}else{
				Vector <TipoEstatusDTO> tiposEstatusesDTOSituacion = new Vector<TipoEstatusDTO>();
				tiposEstatusesDTOSituacion.add(tipoDesha);
				request.setAttribute("items_situacion", tiposEstatusesDTOSituacion);
			}
			if(configCampos.getConfigCampoSituacionItem().isMostrar()){
				Vector <TipoEstatusDTO> tiposEstatusesDTOUbicacion = getTipoEstatusDAO().selectTiposEstatusesDTO(idTipoItem, "items_ubicacion");
				if(tiposEstatusesDTOUbicacion != null && tiposEstatusesDTOUbicacion.size() > 0){
					request.setAttribute("items_ubicacion", tiposEstatusesDTOUbicacion);
				}else{
					tiposEstatusesDTOUbicacion = new Vector<TipoEstatusDTO>();
					tiposEstatusesDTOUbicacion.add(tipoError);
					request.setAttribute("items_ubicacion", tiposEstatusesDTOUbicacion);
				}
			}else{
				Vector <TipoEstatusDTO> tiposEstatusesDTOUbicacion = new Vector<TipoEstatusDTO>();
				tiposEstatusesDTOUbicacion.add(tipoDesha);
				request.setAttribute("items_ubicacion", tiposEstatusesDTOUbicacion);
			}
			if(configCampos.getConfigCampoSituacionItem().isMostrar()){
				Vector <TipoEstatusDTO> tiposEstatusesDTOStatus = getTipoEstatusDAO().selectTiposEstatusesDTO(idTipoItem, "items_status");
				if(tiposEstatusesDTOStatus != null && tiposEstatusesDTOStatus.size() > 0){
					request.setAttribute("items_status", tiposEstatusesDTOStatus);
				}else{
					tiposEstatusesDTOStatus = new Vector<TipoEstatusDTO>();
					tiposEstatusesDTOStatus.add(tipoError);
					request.setAttribute("items_status", tiposEstatusesDTOStatus);
				}
			}else{
				Vector <TipoEstatusDTO> tiposEstatusesDTOUbicacion = new Vector<TipoEstatusDTO>();
				tiposEstatusesDTOUbicacion.add(tipoDesha);				
				request.setAttribute("items_status", tiposEstatusesDTOUbicacion);
			}
			if(configCampos.getConfigCampoSituacionItem().isMostrar()){
				Vector <TipoEstatusDTO> tiposEstatusesDTOPropietario = getTipoEstatusDAO().selectTiposEstatusesDTO(idTipoItem, "items_propietario");
				if(tiposEstatusesDTOPropietario != null && tiposEstatusesDTOPropietario.size() > 0){
					request.setAttribute("items_propietario", tiposEstatusesDTOPropietario);
				}else{
					tiposEstatusesDTOPropietario = new Vector<TipoEstatusDTO>();
					tiposEstatusesDTOPropietario.add(tipoError);
					request.setAttribute("items_propietario", tiposEstatusesDTOPropietario);
				}
			}else{
				Vector <TipoEstatusDTO> tiposEstatusesDTOPropietario = new Vector<TipoEstatusDTO>();
				tiposEstatusesDTOPropietario.add(tipoDesha);
				request.setAttribute("items_propietario", tiposEstatusesDTOPropietario);
			}						
		}else{
			System.out.println("SIN tipo de item definido");
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void guardarItemService(HttpServletRequest request, HttpServletResponse response){		
		String typeId = request.getParameter("typeId");
		if(typeId != null && !typeId.isEmpty() && typeId.length() > 0){
			int idTipoItem = Integer.parseInt(typeId);
			if(idTipoItem > 0){
				HttpSession session = request.getSession();
				TipoItemDTO tipoSession = (TipoItemDTO) session.getAttribute("tipoItem");
				UserDTO usuarioCompleto = (UserDTO) session.getAttribute("user");
				UserSimpleDTO usuarioSimple = new UserSimpleDTO();
				usuarioSimple.setIdUser(usuarioCompleto.getUserId());
				TipoItemDTO tipoItem = null;
				if(idTipoItem == tipoSession.getIdTipoItem()){
					tipoItem = tipoSession;
				}else{
					tipoItem = getItemDAO().selectTipoItem(idTipoItem);
					session.setAttribute("tipoItem", tipoItem);
				}
				if(tipoItem != null){
					ItemDTO itemGuardar = new ItemDTO();
					Locale local = new Locale("es", "MX");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(local);
					formatter = formatter.withLocale(Locale.US);//01/01/2017
					try{
						String campo = "";
						String missingFields = "";
						campo = "idItemHidden";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setIdItem(Integer.parseInt(request.getParameter(campo)));
						}else{
							missingFields += "missing: "+campo;
						}
						itemGuardar.setTipoItem(tipoItem);
						campo = "skuItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setSkuItem(request.getParameter(campo));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "nombreItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setNombreItem(request.getParameter(campo));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "noSerieItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setNoSerieItem(request.getParameter(campo));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "placasItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setPlacasItem(request.getParameter(campo));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "categoriaItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setCategoriaItem(request.getParameter(campo));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "modeloItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.getModeloItem().setIdModeloItem(Integer.parseInt(request.getParameter(campo)));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "colorItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setColorItem(request.getParameter(campo));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "tamanoItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setTamanoItem(request.getParameter(campo));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "marcaItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.getMarcaItem().setIdMarcaItem(Integer.parseInt(request.getParameter(campo)));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "formaItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setFormaItem(request.getParameter(campo));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "lineaItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.getLineaItem().setIdLinea(Integer.parseInt(request.getParameter(campo)));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "itemAsignadoItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setItemAsignadoItem(Boolean.parseBoolean(request.getParameter(campo)));
						}else{
							itemGuardar.setItemAsignadoItem(false);
							missingFields += ", missing: "+campo;
						}
						campo = "asignacionItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setAsignacionItem(Integer.parseInt(request.getParameter(campo)));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "string1Item";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setString1Item(request.getParameter(campo));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "fechaActualizacionItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.getFechaHoraActualizacion().getFecha().setFechaLD(LocalDate.parse(request.getParameter(campo)));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "comentariosItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setComentariosItem(request.getParameter(campo));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "situacionItem";						
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.getSituacionItemDTO().setEstatusInt(Integer.parseInt(request.getParameter(campo)));;
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "ubicacionItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.getUbicacionItemDTO().setEstatusInt(Integer.parseInt(request.getParameter(campo)));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "contabilidadRecursoItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setIdContabilidadRecursoItem(Integer.parseInt(request.getParameter(campo)));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "fechaRecepcionItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.getFechaHoraRecepcionItem().getFecha().setFechaLD(LocalDate.parse(request.getParameter(campo)));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "contratoItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setContratoItem(request.getParameter(campo));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "proyectoItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.setProyectoItem(request.getParameter(campo));
						}else{
							missingFields += ", missing: "+campo;
						}
						campo = "statusItem";
						if(request.getParameter(campo) != null && request.getParameter(campo).length() > 0){
							itemGuardar.getStatusItem().setEstatusInt(Integer.parseInt(request.getParameter(campo)));
						}else{
							missingFields += ", missing: "+campo;
						}
						if(missingFields.length() > 0){
							System.out.println(missingFields);
						}
						int res = -1;
						if(itemGuardar.getIdItem() == 0){
							itemGuardar.getFechaHoraCreacion().getFecha().setFechaLD(LocalDate.now());
							itemGuardar.getFechaHoraCreacion().getHora().setHoraLT(LocalTime.now());
							itemGuardar.setUsuarioCreacion(usuarioSimple);
							res = getItemDAO().insertNewItem(itemGuardar);							
						}else{
							if(itemGuardar.getIdItem() > 0){		
								itemGuardar.setUsuarioActualizacion(usuarioSimple);
								itemGuardar.getFechaHoraActualizacion().getFecha().setFechaLD(LocalDate.now());
								itemGuardar.getFechaHoraActualizacion().getHora().setHoraLT(LocalTime.now());
								res = getItemDAO().updateItemDTO(itemGuardar);								
							}else{
								System.out.println("error idItemGuardar < 0");
							}
						}
						if(res > 0){
							response.getWriter().append("1");
						}else{
							response.getWriter().append("0");
						}
						
					}
					catch (Exception e) {
						System.out.println(e);
					}
					
				}else{
					System.out.println("SIN tipo de item encontrado en la bd: tipoItem == null");
				}
			}else{
				System.out.println("SIN tipo desde int, de item definido");
			}
		}else{
			System.out.println("SIN tipo desde string, de item definido");
		}

	}


	/**
	 * @return the ordenesDAO
	 */
	public OrdenesDAO getOrdenesDAO() {
		return ordenesDAO;
	}


	/**
	 * @param ordenesDAO the ordenesDAO to set
	 */
	public void setOrdenesDAO(OrdenesDAO ordenesDAO) {
		this.ordenesDAO = ordenesDAO;
	}

	/**
	 * @return the partnerDAO
	 */
	public PartnerDAO getPartnerDAO() {
		return partnerDAO;
	}

	/**
	 * @param partnerDAO the partnerDAO to set
	 */
	public void setPartnerDAO(PartnerDAO partnerDAO) {
		this.partnerDAO = partnerDAO;
	}


	
	
	
	
	
}//Termina clase

