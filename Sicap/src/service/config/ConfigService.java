package service.config;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.ConfigDAO;
import dao.user.perfil.PerfilDAO;
import dto.user.menu.MenuDTO;
import dto.user.menu.submenu.SubMenuDTO;
import dto.user.perfil.MenuPerfilDTO;
import dto.user.perfil.PerfilDTO;

public class ConfigService {
	private ConfigDAO configDAO;
	private PerfilDAO perfilDAO;
	
	public ConfigService(){
		if(this.getConfigDAO() == null){
			setConfigDAO(new ConfigDAO());		
		} 
		if(this.getPerfilDAO() == null){
			setPerfilDAO(new PerfilDAO());		
		}
	}
	


	public void consultarMenusService(HttpServletRequest request, HttpServletResponse response){		
		request.setAttribute("menus", getConfigDAO().selectMenus()); 
	}
	
	public void consultarSubmenusService(HttpServletRequest request, HttpServletResponse response){
		Vector<SubMenuDTO> vSubmenu = new Vector<SubMenuDTO>();
		vSubmenu = getConfigDAO().selectSubmenus();
		if(vSubmenu != null && vSubmenu.size() > 0){
			request.setAttribute("submenus", vSubmenu);
		}else{
			request.setAttribute("mensaje", "No se encontro registro");
		}
	}
	
	public void consultarSubmenusServicePorMenu(HttpServletRequest request, HttpServletResponse response){
		Vector<SubMenuDTO> vSubmenu = new Vector<SubMenuDTO>();
		int idMenu = Integer.parseInt(request.getParameter("idMenu"));
		vSubmenu = getConfigDAO().selectSubmenusPorMenu(idMenu);
		if(vSubmenu != null && vSubmenu.size() > 0){
			String json = new Gson().toJson(vSubmenu);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().write(json);
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
	
	public void consultarPerfilesService(HttpServletRequest request, HttpServletResponse response){		
		Vector<PerfilDTO> vPerlfil = new Vector<PerfilDTO>();
		vPerlfil = getPerfilDAO().selectPerfiles();
		if(vPerlfil != null && vPerlfil.size() > 0){
			request.setAttribute("perfiles", vPerlfil);
		}else{
			request.setAttribute("mensaje", "No se encontro registro");
		}
	}
	
	public void consultarMenuPerfilesService(HttpServletRequest request, HttpServletResponse response){
		Vector<MenuPerfilDTO> vmenuPerfil = new Vector<MenuPerfilDTO>();
		vmenuPerfil = getConfigDAO().selectMenusPerfiles();
		if(vmenuPerfil != null && vmenuPerfil.size() > 0){
			request.setAttribute("menusPerfiles", vmenuPerfil);
		}else{
			request.setAttribute("mensaje", "No se encontro registro");
		}
	}
	
	public void inicializaLoginScreen(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.setAttribute("titulo", getConfigDAO().selectConfigSistema());
	}
	
	
	public void nuevoActualizarMenuService(HttpServletRequest request, HttpServletResponse response){
		MenuDTO menuNuevo = new MenuDTO();			
		try{
			if(request.getParameter("idMenuHidden") != null && request.getParameter("idMenuHidden").length() > 0){
				menuNuevo.setIdApp(Integer.parseInt(request.getParameter("idMenuHidden")));	
			}
			if(request.getParameter("field1") != null && request.getParameter("field1").length() > 0){
				menuNuevo.setIdApp(Integer.parseInt(request.getParameter("field1")));
			}
			if(request.getParameter("field2") != null && request.getParameter("field2").length() > 0){
				menuNuevo.setNombreApp(request.getParameter("field2"));
			}
			if(request.getParameter("field3") != null && request.getParameter("field3").length() > 0){
				menuNuevo.setDescApp(request.getParameter("field3"));
			}
			if(request.getParameter("field4") != null && request.getParameter("field4").length() > 0){
				menuNuevo.setUrlApp(request.getParameter("field4"));
			}
			if(request.getParameter("field5") != null && request.getParameter("field5").length() > 0){
				menuNuevo.setIconoApp(request.getParameter("field5"));
			}
			
			if(request.getParameter("field6") != null && request.getParameter("field6").length() > 0){
				menuNuevo.setIndexApp(Double.parseDouble(request.getParameter("field6")));
			}
			if(request.getParameter("field7") != null && request.getParameter("field7").length() > 0){
				if(request.getParameter("field7").equals("1")){
					menuNuevo.setStatusApp(1);
				}else{
					menuNuevo.setStatusApp(0);
				}
			}
		}catch(Exception error){
			System.out.println(error);
			try {
				response.getWriter().append("-2");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (menuNuevo.getIdApp() > 0){
			int editar = getConfigDAO().actualizarMenu(menuNuevo);
			if(editar > 0){
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(editar == 0){
				System.out.println("Error en databasegateway, nuevoSubmenuService: editaMenu");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ConfigDAO, nuevoSubmenuService: editaMenu");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}else{
			int res = getConfigDAO().insertaNuevoMenu(menuNuevo);
			if(res > 0){
				Vector<MenuDTO> menus = null;
				menus = getConfigDAO().selectMenus();
				if(menus!= null && menus.size() > 0){
					MenuDTO ultimoMenu = menus.lastElement();
					int idUltimoMenu= ultimoMenu.getIdApp();
					int ultMenu = idUltimoMenu+1;
					request.setAttribute("ultimoSubm", ultMenu);
					System.out.println(idUltimoMenu);
				}
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(res == 0){
				System.out.println("Error en databasegateway, nuevoSubmenuService: insertaNuevoSubmenu");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ConfigDAO, nuevoSubmenuService: insertaNuevoSubmenu");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}

	}
	
	public void nuevoActualizarSubmenusService(HttpServletRequest request, HttpServletResponse response){
		SubMenuDTO submenuNuevo = new SubMenuDTO();			
		try{
			if(request.getParameter("idSubmenuHidden") != null && request.getParameter("idSubmenuHidden").length() > 0){
				//if(HerramientasResultSet.tryParse(request.getParameter("skuEmpleadoHidden")) != null){
				submenuNuevo.setIdAction(Integer.parseInt(request.getParameter("idSubmenuHidden")));
				//}else{
				//}
				System.out.println("idSubmenuHidden");
				
			}
			if(request.getParameter("field1") != null && request.getParameter("field1").length() > 0){
				submenuNuevo.setIdAction(Integer.parseInt(request.getParameter("field1")));
				System.out.println("field1");
			}
			if(request.getParameter("field2") != null && request.getParameter("field2").length() > 0){
				submenuNuevo.setFkApp(Integer.parseInt(request.getParameter("field2")));
				System.out.println("field2");
			}
			if(request.getParameter("field3") != null && request.getParameter("field3").length() > 0){
				submenuNuevo.setNombreAction(request.getParameter("field3"));
				System.out.println("field3");
			}
			if(request.getParameter("field4") != null && request.getParameter("field4").length() > 0){
				submenuNuevo.setUrlAction(request.getParameter("field4"));
				System.out.println("field4");
			}
			if(request.getParameter("field5") != null && request.getParameter("field5").length() > 0){
				submenuNuevo.setIconoAction(request.getParameter("field5"));
				System.out.println("field5");
			}else{
				if(request.getParameter("field8") != null && request.getParameter("field8").length() > 0){
					if(!request.getParameter("field8").equals("otros")){
						submenuNuevo.setIconoAction(request.getParameter("field8"));
					}else{
						
					}
										
				}else{
					System.out.println("sin datos del select html");
				}
			}
			
			if(request.getParameter("field6") != null && request.getParameter("field6").length() > 0){
				if(request.getParameter("field6").equals("1")){
					submenuNuevo.setIsHeader(1);
				}else{
					submenuNuevo.setIsHeader(0);
				}
				System.out.println("field6");
			}
			if(request.getParameter("field7") != null && request.getParameter("field7").length() > 0){
				if(request.getParameter("field7").equals("1")){

					submenuNuevo.setStatusAction(1);
				}else{
					submenuNuevo.setStatusAction(0);
				}
				System.out.println("field7");
				System.out.println(":)");
			}
			
		}catch(Exception error){
			System.out.println(error);
			try {
				response.getWriter().append("-2");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (submenuNuevo.getIdAction() > 0){
			int editar = getConfigDAO().actualizarSubmenu(submenuNuevo);
			if(editar > 0){
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(editar == 0){
				System.out.println("Error en databasegateway, nuevoSubmenuService: editaMenu");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ConfigDAO, nuevoSubmenuService: editaMenu");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}else{
			int res = getConfigDAO().insertaNuevoSubmenu(submenuNuevo);
			if(res > 0){
				Vector<SubMenuDTO> subMenus = null;
				subMenus = getConfigDAO().selectSubmenus();
				if(subMenus!= null && subMenus.size() > 0){
					SubMenuDTO ultimoSubmenu = subMenus.lastElement();
					int idUltimoSubmenu= ultimoSubmenu.getIdAction();
					int ultSubmenu = idUltimoSubmenu+1;
					request.setAttribute("ultimoSubm", ultSubmenu);
					System.out.println(idUltimoSubmenu);
				}
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(res == 0){
				System.out.println("Error en databasegateway, nuevoSubmenuService: insertaNuevoSubmenu");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ConfigDAO, nuevoSubmenuService: insertaNuevoSubmenu");
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
	
	public void nuevoActualizarPerfilesService(HttpServletRequest request, HttpServletResponse response){
		PerfilDTO perfilNuevo = new PerfilDTO();			
		try{
			if(request.getParameter("idPerfilHidden") != null && request.getParameter("idPerfilHidden").length() > 0){
				perfilNuevo.setIdPerfil(Integer.parseInt(request.getParameter("idPerfilHidden")));	
			}
			if(request.getParameter("field1") != null && request.getParameter("field1").length() > 0){
				perfilNuevo.setIdPerfil(Integer.parseInt(request.getParameter("field1")));
			}
			if(request.getParameter("field2") != null && request.getParameter("field2").length() > 0){
				perfilNuevo.setDescription(request.getParameter("field2"));
			}
			if(request.getParameter("field3") != null && request.getParameter("field3").length() > 0){
				if(request.getParameter("field3").equals("1")){
					perfilNuevo.setStatus(1);
				}else{
					perfilNuevo.setStatus(0);
				}
			}
		}catch(Exception error){
			System.out.println(error);
			try {
				response.getWriter().append("-2");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (perfilNuevo.getIdPerfil() > 0){
			int editar = getPerfilDAO().actualizarPerfil(perfilNuevo);
			if(editar > 0){
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(editar == 0){
				System.out.println("Error en databasegateway, nuevoActualizarPerfilesService: editaPerfil");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ConfigDAO, nuevoActualizarPerfilesService: editaPerfil");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}else{
			int res = getPerfilDAO().insertaNuevoPerfil(perfilNuevo);
			if(res > 0){
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(res == 0){
				System.out.println("Error en databasegateway, nuevoActualizarPerfilesService: insertaNuevoPerfil");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ConfigDAO, nuevoActualizarPerfilesService: insertaNuevoPerfil");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}

	}
	
	public void nuevoActualizarMenus_PerfilesService(HttpServletRequest request, HttpServletResponse response){
		MenuPerfilDTO menuPerfilNuevo = new MenuPerfilDTO();			
		try{
			if(request.getParameter("idMenuPerfilHidden") != null && request.getParameter("idMenuPerfilHidden").length() > 0){
				menuPerfilNuevo.setIdMenuPerfil(Integer.parseInt(request.getParameter("idMenuPerfilHidden")));	
			}
			if(request.getParameter("field1") != null && request.getParameter("field1").length() > 0){
				menuPerfilNuevo.setIdMenuPerfil(Integer.parseInt(request.getParameter("field1")));
			}
			if(request.getParameter("field2") != null && request.getParameter("field2").length() > 0){
				menuPerfilNuevo.setFkPerfil(Integer.parseInt(request.getParameter("field2")));
			}
			if(request.getParameter("field3") != null && request.getParameter("field3").length() > 0){
				menuPerfilNuevo.setFkSubmenu(Integer.parseInt(request.getParameter("field3")));
			}
			if(request.getParameter("field4") != null && request.getParameter("field4").length() > 0){
				if(request.getParameter("field4").equals("1")){
					menuPerfilNuevo.setCreateMenuPerfil(1);
				}else{
					menuPerfilNuevo.setCreateMenuPerfil(0);
				}
			}
			if(request.getParameter("field5") != null && request.getParameter("field5").length() > 0){
				if(request.getParameter("field5").equals("1")){
					menuPerfilNuevo.setUpdateMenuPerfil(1);
				}else{
					menuPerfilNuevo.setUpdateMenuPerfil(0);
				}
			}
			if(request.getParameter("field6") != null && request.getParameter("field6").length() > 0){
				if(request.getParameter("field6").equals("1")){
					menuPerfilNuevo.setDeleteMenuPerfil(1);
				}else{
					menuPerfilNuevo.setDeleteMenuPerfil(0);
				}
			}
			if(request.getParameter("field7") != null && request.getParameter("field7").length() > 0){
				menuPerfilNuevo.setIndexMenuPerfil(Double.parseDouble(request.getParameter("field7")));
			}
		}catch(Exception error){
			System.out.println(error);
			try {
				response.getWriter().append("-2");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (menuPerfilNuevo.getIdMenuPerfil() > 0){
			int editar = getConfigDAO().actualizarMenuPerfil(menuPerfilNuevo);
			if(editar > 0){
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(editar == 0){
				System.out.println("Error en databasegateway, nuevoActualizarMenus_PerfilesService: actualizarMenuPerfil");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ConfigDAO, nuevoActualizarMenus_PerfilesService: actualizarMenuPerfil");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}else{
			int res = getConfigDAO().insertarNuevoMenuPerfil(menuPerfilNuevo);
			if(res > 0){
				try {
					response.getWriter().append("1");
				} catch (IOException e) {				
					e.printStackTrace();
				}		
			}else if(res == 0){
				System.out.println("Error en databasegateway, nuevoActualizarMenus_PerfilesService: insertarNuevoMenuPerfil");
				try {
					response.getWriter().append("0");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("Error en ConfigDAO, nuevoActualizarMenus_PerfilesService: insertarNuevoMenuPerfil");
				try {
					response.getWriter().append("-1");
				} catch (IOException e) {				
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * @return the configDAO
	 */
	private ConfigDAO getConfigDAO() {
		return configDAO;
	}

	/**
	 * @param configDAO the configDAO to set
	 */
	private void setConfigDAO(ConfigDAO configDAO) {
		this.configDAO = configDAO;
	}



	public PerfilDAO getPerfilDAO() {
		return perfilDAO;
	}



	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}
	
}
