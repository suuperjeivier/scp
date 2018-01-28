package service.partners;


import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ConfigDAO;
import dao.empleado.EmpleadoDAO;
import dao.horario.HorarioDAO;
import dao.item.ItemDAO;
import dao.listados.ListadosDAO;
import dao.listados.TipoEstatusDAO;
import dao.movimientos.MovimientosDAO;
import dao.partners.PartnerDAO;
import dao.user.UserDAO;
import dao.user.perfil.PerfilDAO;
import dto.asignacion.AsignacionDTO;
import herramientas.imagenes.ProcesarImagen;

public class PartnersService {	

	
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
		private PartnerDAO partnerDAO;
		 
		/**
		 * INICIALIZA LOS COMPONENTES DE LA CLASE
		 */
		public PartnersService(){
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
		 * 
		 * @param request
		 * @param response
		 */
		public void selectPartnersSinAsignacionPorTipoMovimiento(HttpServletRequest request, HttpServletResponse response){			
			int tipoMovimiento = Integer.parseInt(request.getParameter("movId"));
			
				Vector<AsignacionDTO> movs = getMovimientosDAO().selectMovimientosAsignacionesItem(tipoMovimiento);
				if(movs != null){
					String json = new Gson().toJson(movs);
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
		
		
		
		
	}
