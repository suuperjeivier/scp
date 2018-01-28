package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import dto.ConfigDTO;
import dto.listados.ConfigCamposTipoItemDTO;
import dto.user.menu.MenuDTO;
import dto.user.menu.submenu.SubMenuDTO;
import dto.user.perfil.MenuPerfilDTO;
import herramientas.herrramientasrs.HerramientasResultSet;

/**
 * 
 * @author jbritop
 *
 */
public class ConfigDAO {
	
	private DatabaseGateway database;
	private HerramientasResultSet herramientasResultSet;
	
	/**
	 * 
	 */
	public ConfigDAO(){
		if(this.getDatabase() == null){
			this.setDatabase(new DatabaseGateway());
		}			
		if(getHerramientasResultSet() == null){
			setHerramientasResultSet(new HerramientasResultSet());
		}
	}
	
	/**
	 * @return the database
	 */
	DatabaseGateway getDatabase() {
		return database;
	}

	/**
	 * @param database the database to set
	 */
	void setDatabase(DatabaseGateway database) {
		this.database = database;
	}

	/**
	 * @return the herramientasResultSet
	 */
	HerramientasResultSet getHerramientasResultSet() {
		return herramientasResultSet;
	}

	/**
	 * @param herramientasResultSet the herramientasResultSet to set
	 */
	void setHerramientasResultSet(HerramientasResultSet herramientasResultSet) {
		this.herramientasResultSet = herramientasResultSet;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public ConfigDTO selectConfigSistema(){
		ConfigDTO config = null;
		if(getDatabase().openDatabase()){
			config = new ConfigDTO();
			ResultSet rs = null;
			String query = "SELECT * FROM tb_configuracion_sistema";
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(rs != null){
				try {
					while (rs.next()) {					
						config = getHerramientasResultSet().inicializaConfigSistemaDTO(rs);
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("rs == nullo");
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}	
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return config;
	}
	
	/**
	 * 
	 * @return
	 */
	public ConfigDTO selectConfigLoginSistema(){
		ConfigDTO config = null;
		if(getDatabase().openDatabase()){
			config = new ConfigDTO();
			ResultSet rs = null;
			String query = "SELECT * FROM tb_configuracion_sistema";
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(rs != null){
				try {
					while (rs.next()) {					
						config = getHerramientasResultSet().inicializaConfigLoginSistemaDTO(rs);
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("rs == nullo");
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}	
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return config;
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector<MenuDTO> selectMenus(){
		Vector<MenuDTO> menus = new Vector<MenuDTO>();		
		if(getDatabase().openDatabase()){			
			ResultSet rs = null;
			String query = "SELECT * FROM sec_apps LEFT JOIN tb_tipos_estatus ON sec_apps.status_app = tb_tipos_estatus.status_int WHERE tb_tipos_estatus.aplicacion = 'general';";
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(rs != null){
				try {
					while (rs.next()) {					
						MenuDTO menu = getHerramientasResultSet().inicializaMenuSistemaDTO(rs);
						menus.add(menu);
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("rs == nullo");
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}	
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return menus;	
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Vector<SubMenuDTO> selectSubmenus(){
		Vector<SubMenuDTO> subMenus = new Vector<SubMenuDTO>();		
		if(getDatabase().openDatabase()){			
			ResultSet rs = null;
			String query = "SELECT * FROM sec_apps_actions "
					+ "LEFT JOIN sec_apps ON sec_apps_actions.fk_app = sec_apps.id_app "
					+ "LEFT JOIN tb_tipos_estatus ON sec_apps_actions.status_action = tb_tipos_estatus.status_int "
					+ "LEFT JOIN tb_tipos_estatus AS status_header ON sec_apps_actions.is_header = status_header.status_int "
					+ "WHERE tb_tipos_estatus.aplicacion = 'general' and status_header.aplicacion = 'general';";
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(rs != null){
				try {
					while (rs.next()) {					
						SubMenuDTO submenu = getHerramientasResultSet().inicializaSubmenuSistemaDTO(rs);
						subMenus.add(submenu);
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("rs == nullo");
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}	
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return subMenus;	
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector<SubMenuDTO> selectSubmenusPorMenu(int idMenu){
		Vector<SubMenuDTO> subMenus = new Vector<SubMenuDTO>();		
		if(getDatabase().openDatabase()){			
			ResultSet rs = null;
			String query = "SELECT * FROM sec_apps_actions "
					+ "LEFT JOIN sec_apps ON sec_apps_actions.fk_app = sec_apps.id_app "
					+ "LEFT JOIN tb_tipos_estatus ON sec_apps_actions.status_action = tb_tipos_estatus.status_int "
					+ "LEFT JOIN tb_tipos_estatus AS status_header ON sec_apps_actions.is_header = status_header.status_int "
					+ "WHERE tb_tipos_estatus.aplicacion = 'general' and status_header.aplicacion = 'general' AND sec_apps_actions.fk_app = " + idMenu + ";";
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(rs != null){
				try {
					while (rs.next()) {					
						SubMenuDTO submenu = getHerramientasResultSet().inicializaSubmenuSistemaDTO(rs);
						subMenus.add(submenu);
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("rs == nullo");
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}	
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return subMenus;	
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector<MenuPerfilDTO> selectMenusPerfiles(){
		Vector<MenuPerfilDTO> vmenuPerfiles = new Vector<MenuPerfilDTO>();		
		if(getDatabase().openDatabase()){			
			ResultSet rs = null;
			String query = "SELECT * FROM sec_groups_apps_actions \r\n" + 
					"					LEFT JOIN sec_groups ON sec_groups_apps_actions.fk_group = sec_groups.group_id \r\n" + 
					"					LEFT JOIN sec_apps_actions AS submenus ON sec_groups_apps_actions.fk_app_action = submenus.id_action\r\n" + 
					"					LEFT JOIN sec_apps AS menus ON submenus.fk_app = menus.id_app\r\n" + 
					"					LEFT JOIN sec_apps_actions ON sec_groups_apps_actions.fk_app_action = sec_apps_actions.id_action \r\n" + 
					"					LEFT JOIN tb_tipos_estatus AS accion_create ON sec_groups_apps_actions.action_create = accion_create.status_int \r\n" + 
					"					LEFT JOIN tb_tipos_estatus AS accion_update ON sec_groups_apps_actions.action_update = accion_update.status_int \r\n" + 
					"					LEFT JOIN tb_tipos_estatus AS accion_delete ON sec_groups_apps_actions.action_delete = accion_delete.status_int					\r\n" + 
					"					WHERE accion_create.aplicacion = 'general' and accion_update.aplicacion = 'general' AND accion_delete.aplicacion = 'general'"
					+ " ORDER BY sec_groups.description, menus.index_app, sec_groups_apps_actions.`index` DESC;";
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(rs != null){
				try {
					while (rs.next()) {					
						MenuPerfilDTO menuPerfil = getHerramientasResultSet().inicializaMenuPerfilDTO(rs);
						vmenuPerfiles.add(menuPerfil);
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("rs == nullo");
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}	
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return vmenuPerfiles;	
	}
	
	
	/**
	 * 
	 * @param menuNuevo
	 * @return
	 */
	public int insertaNuevoMenu(MenuDTO menuNuevo){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "sec_apps";
			String query= "INSERT INTO " + tabla + " "
					+ "("
						+ tabla + ".nombre_app, "
						+ tabla + ".desc_app, "
						+ tabla + ".url_app, "
						+ tabla + ".icono_app, "
						+ tabla + ".index_app, "
						+ tabla + ".status_app"
					+ ") VALUES"
					+ "("
						+ "'" + menuNuevo.getNombreApp()
						+ "', '" + menuNuevo.getDescApp()
						+ "', '" + menuNuevo.getUrlApp() 
						+ "', '" + menuNuevo.getIconoApp() 
						+ "', " + menuNuevo.getIndexApp()
						+ ", " + menuNuevo.getStatusApp()
					+");";
			try {
				System.out.println(query);
				res = getDatabase().executeNonQuery(query);				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return res;
	}
	
	/**
	 * 
	 * @param menuNuevo
	 * @return
	 */
	public int actualizarMenu(MenuDTO menuNuevo){		
		int editar = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "sec_apps";
			String query= "UPDATE " + tabla + " " 
						+ " SET "
						+ tabla + ".nombre_app = '" + menuNuevo.getNombreApp() + "',"
						+ tabla + ".desc_app = '" + menuNuevo.getDescApp() + "',"
						+ tabla + ".url_app = '" + menuNuevo.getUrlApp() + "',"
						+ tabla + ".icono_app = '" + menuNuevo.getIconoApp() + "',"
						+ tabla + ".index_app = " + menuNuevo.getIndexApp() + ","
						+ tabla + ".status_app = " + menuNuevo.getStatusApp()
						+ " WHERE "
						+ tabla + ".id_app = " + menuNuevo.getIdApp();
			try {
				System.out.println(query);
				editar = getDatabase().executeNonQuery(query);				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			getDatabase().closeDatabase();
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return editar;
	}
	
	/**
	 * 
	 * @param menuNuevo
	 * @return
	 */
	public int insertaNuevoSubmenu(SubMenuDTO submenuNuevo){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "sec_apps_actions";
			String query= "INSERT INTO " + tabla + " "
					+ "(" 
						+ tabla + ".fk_app, "
						+ tabla + ".nombre_action, "
						+ tabla + ".url_action, "
						+ tabla + ".icono_action, "
						+ tabla + ".is_header, "
						+ tabla + ".status_action"
					+ ") VALUES"
					+ "("
						+ "'" + submenuNuevo.getFkApp()
						+ "', '" + submenuNuevo.getNombreAction()
						+ "', '" + submenuNuevo.getUrlAction() 
						+ "', '" + submenuNuevo.getIconoAction() 
						+ "', " + submenuNuevo.getIsHeader()
						+ ", " + submenuNuevo.getStatusAction()
					+");";
			try {
				System.out.println(query);
				res = getDatabase().executeNonQuery(query);				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return res;
	}
	
	/**
	 * 
	 * @param menuNuevo
	 * @return
	 */
	public int actualizarSubmenu(SubMenuDTO submenuNuevo){		
		int editar = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "sec_apps_actions";
			String query= "UPDATE " + tabla + " " 
						+ " SET "
						+ tabla + ".fk_app = " + submenuNuevo.getFkApp() + ","
						+ tabla + ".nombre_action = '" + submenuNuevo.getNombreAction() + "',"
						+ tabla + ".url_action = '" + submenuNuevo.getUrlAction() + "',"
						+ tabla + ".icono_action = '" + submenuNuevo.getIconoAction() + "',"
						+ tabla + ".is_header = " + submenuNuevo.getIsHeader() + ","
						+ tabla + ".status_action = " + submenuNuevo.getStatusAction()
						+ " WHERE "
						+ tabla + ".id_action = " + submenuNuevo.getIdAction();
			try {
				System.out.println(query);
				editar = getDatabase().executeNonQuery(query);				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			getDatabase().closeDatabase();
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return editar;
	}
	
	/**
	 * 
	 * @param menuNuevo
	 * @return
	 */
	public int insertarNuevoMenuPerfil(MenuPerfilDTO menuPerfilNuevo){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "sec_groups_apps_actions";
			String query= "INSERT INTO " + tabla + " "
					+ "(" 
						+ tabla + ".fk_group, "
						+ tabla + ".fk_app_action, "
						+ tabla + ".action_create, "
						+ tabla + ".action_update, "
						+ tabla + ".action_delete, "
						+ tabla + ".index"
					+ ") VALUES"
					+ "("
						+ "" + menuPerfilNuevo.getFkPerfil()
						+ ", " + menuPerfilNuevo.getFkSubmenu()
						+ ", " + menuPerfilNuevo.getCreateMenuPerfil() 
						+ ", " + menuPerfilNuevo.getUpdateMenuPerfil() 
						+ ", " + menuPerfilNuevo.getDeleteMenuPerfil()
						+ ", " + menuPerfilNuevo.getIndexMenuPerfil()
					+");";
			try {
				System.out.println(query);
				res = getDatabase().executeNonQuery(query);				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return res;
	}
	
	/**
	 * 
	 * @param menuNuevo
	 * @return
	 */
	public int actualizarMenuPerfil(MenuPerfilDTO menuPerfilNuevo){		
		int editar = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "sec_groups_apps_actions";
			String query= "UPDATE " + tabla + " " 
						+ " SET "
						+ tabla + ".fk_group = " + menuPerfilNuevo.getFkPerfil() + ","
						+ tabla + ".fk_app_action = " + menuPerfilNuevo.getFkSubmenu() + ","
						+ tabla + ".action_create = " + menuPerfilNuevo.getCreateMenuPerfil() + ","
						+ tabla + ".action_update = " + menuPerfilNuevo.getUpdateMenuPerfil() + ","
						+ tabla + ".action_delete = " + menuPerfilNuevo.getDeleteMenuPerfil() + ","
						+ tabla + ".index = " + menuPerfilNuevo.getIndexMenuPerfil()
						+ " WHERE "
						+ tabla + ".id_group_apps_actions = " + menuPerfilNuevo.getIdMenuPerfil();
			try {
				System.out.println(query);
				editar = getDatabase().executeNonQuery(query);				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			getDatabase().closeDatabase();
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return editar;
	}
	
	/**
	 * 
	 * @param idTipoItem
	 * @return
	 */
	public ConfigCamposTipoItemDTO selectConfigCamposItem(int idTipoItem){		
		ConfigCamposTipoItemDTO configCamposItems = null;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_items_campos_config WHERE tb_items_campos_config.fk_id_item_tipo = "+idTipoItem+";";
			try {
//				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				if(rs != null){					
					while(rs.next()){
						configCamposItems = getHerramientasResultSet().inicializaConfigCamposTipoItem(rs);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectItems");
				}
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			if (getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return configCamposItems;
	}

	
	
}
