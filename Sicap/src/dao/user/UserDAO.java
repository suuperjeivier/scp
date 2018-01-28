package dao.user;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.Part;

import dao.DatabaseGateway;
import dto.user.UserConfigDTO;
import dto.user.UserDTO;
import dto.user.UserSimpleDTO;
import dto.user.menu.MenuDTO;
import dto.user.menu.submenu.SubMenuDTO;
import herramientas.herrramientasrs.HerramientasResultSet;

public class UserDAO {
	
private DatabaseGateway database = null;
private HerramientasResultSet herramientasResultSet;

	public UserDAO(){
		if(this.getDatabase() == null){
			this.setDatabase(new DatabaseGateway());
		}
		if(this.herramientasResultSet == null){
			this.herramientasResultSet = new HerramientasResultSet();
		}
		InitializeComponents();		
	}
	
	public void InitializeComponents(){
		
	}
	
	public UserSimpleDTO insertUserSimpleDTO(UserSimpleDTO userSimpleDTO){		
		if(userSimpleDTO != null){			
			int res = -1;			
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				String query = "INSERT INTO sec_users"
						+ " ("
						+ "login"
						+ ", fk_id_user_config"						
						+ ", name"
						+ ", email"
						+ ", session_timeout"
						+ ", active"	
						+ ", fk_id_empleado"						
						+ ")"
						+ " VALUES ("
						+ "'" + userSimpleDTO.getLogin() + "'"
						+ ", " + userSimpleDTO.getFkIdUserConfig() 						
						+ ", '" + userSimpleDTO.getName() + "'"
						+ ", '" + userSimpleDTO.getEmail() + "'"
						+ ", " + userSimpleDTO.getSessionTimeOut()
						+ ", '" + userSimpleDTO.getActive() + "'"						
						+ ", " + userSimpleDTO.getFkIdEmpleado()						
						+ ");";
				System.out.println("query de alta de usario: " + query);
				try {
					res = getDatabase().executeNonQuery(query);					
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				if(res > 0){
					query  = "SELECT LAST_INSERT_ID() AS max_user_id";
					ResultSet rs;
					try {
						rs = getDatabase().executeQuery(query);
						while(rs.next()){
							res = rs.getInt("max_user_id");
						}
						userSimpleDTO.setIdUser(res);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(userSimpleDTO.getIdUser() > 0){
					query = "INSERT INTO sec_users_groups"
							+ " ("
							+ "login"
							+ ", group_id"		
							+ ")"
							+ " VALUES ("
							+ "'" + userSimpleDTO.getLogin() + "'"
							+ ", " + userSimpleDTO.getUserProfile().getIdPerfil()	
							+ ");";
					//					System.out.println("query de perfil de usario: " + query);
					try {
						res = getDatabase().executeNonQuery(query);					
					} catch (SQLException e) {					
						e.printStackTrace();
					}
				}

				if(getDatabase().closeDatabase()){
					System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
				}else{
					System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}

		}else{
			System.out.println("sin usuario lipio en " + this.getClass().getSimpleName());
		}
		return userSimpleDTO;
		
	}
	
	public UserSimpleDTO actualizaUserSimpleDTO(UserSimpleDTO userSimpleDTO){		
		if(userSimpleDTO != null){	
			if(userSimpleDTO.getIdUser() > 0){
				int res = -1;			
				if(getDatabase().openDatabase()){
					System.out.println("conexion abierta en " + this.getClass().getSimpleName());
					String query = "UPDATE sec_users"
							+ " SET"
							+ " login = " + "'" + userSimpleDTO.getLogin() + "'"
							+ ", fk_id_user_config = "	+ userSimpleDTO.getFkIdUserConfig() 					
							+ ", name = "+ "'" + userSimpleDTO.getName() + "'"
							+ ", email = " + "'" + userSimpleDTO.getEmail() + "'"
							+ ", session_timeout = "+ userSimpleDTO.getSessionTimeOut()
							+ ", active = "	+ "'" + userSimpleDTO.getActive() + "'"	
							+ ", fk_id_empleado = " + userSimpleDTO.getFkIdEmpleado() 
							+ " WHERE"
							+ " id_user = " + userSimpleDTO.getIdUser()
							+ ";";
					System.out.println("query de update de usario: " + query);
					try {
						res = getDatabase().executeNonQuery(query);
						if(res > 0){
							
						}else{
							userSimpleDTO.setIdUser(0);
						}
					} catch (SQLException e) {					
						e.printStackTrace();
					}				

					query = "UPDATE sec_users_groups"
							+ " SET"
							+ " group_id = " + userSimpleDTO.getUserProfile().getIdPerfil()							
							+ " WHERE"
							+ " login = " + "'" + userSimpleDTO.getLogin() + "'"
							+ ";";
					//					System.out.println("query de perfil de usario: " + query);
					try {
						res = getDatabase().executeNonQuery(query);
						if(res > 0){
							
						}else{
							userSimpleDTO.setIdUser(0);
						}
					} catch (SQLException e) {					
						e.printStackTrace();
					}
					if(getDatabase().closeDatabase()){
						System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
					}else{
						System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
					}
				}else{
					System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
				}
			}else{
				System.out.println("sin id de usaurio simple en " + this.getClass().getSimpleName());
			}

		}else{
			System.out.println("sin usuario simple en " + this.getClass().getSimpleName());
		}
		return userSimpleDTO;
		
	}
	
	/**
	 * Selecciona todos los usaurios en la tabla
	 * @return Vector<UserDTO>
	 */
	public Vector<UserDTO> selectUsers(){
		Vector<UserDTO> usuarios = null;
		
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				ResultSet rs = null;
				String query = "SELECT * FROM sec_users 					\r\n" + 
						"						LEFT JOIN sec_users_config ON sec_users.fk_id_user_config = sec_users_config.id_user_config\r\n" + 
						"						LEFT JOIN sec_users_groups ON sec_users.login = sec_users_groups.login\r\n" + 
						"						LEFT JOIN sec_groups ON sec_users_groups.group_id = sec_groups.group_id\r\n" + 												
						"						LEFT JOIN tb_empleados ON sec_users.fk_id_empleado = tb_empleados.id_empleado\r\n" +						
						"						;";
				try {
					rs = getDatabase().executeQuery(query);
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				try {
					if(rs != null){
						usuarios = new Vector<UserDTO>();
						while(rs.next()){
							UserDTO usuario = herramientasResultSet.inicializaUserDTConEmpleadoSimpleConPerfilDTO(rs);
							if(usuario != null){
								usuarios.add(usuario);
							}
						}
						try{
							rs.close();
						}catch (Exception e) {
							e.printStackTrace();
						}	
					}else{
						System.out.println("rs == nullo en selectUserPorUsernameAndPassword");
					}
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				if(getDatabase().closeDatabase()){
					System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
				}else{
					System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}
		
		return usuarios;	
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public UserDTO selectUserPorUsernameAndPassword(String username, String password){
		UserDTO user = null;
		if(username != null && !username.equals("") && username.length() > 0){
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());

				ResultSet rs = null;
				String query = "SELECT * FROM sec_users "
						+ "LEFT JOIN tb_empleados ON sec_users.fk_id_empleado = tb_empleados.id_empleado "
						+ "LEFT JOIN sec_users_config ON sec_users.fk_id_user_config = sec_users_config.id_user_config "
						+ "WHERE sec_users.login = '" + username + "' AND sec_users.pswd_txt = '"+ password +"' AND sec_users.active = 'Y';";
				try {
					rs = getDatabase().executeQuery(query);
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				try {
					if(rs != null){
						while(rs.next()){
							user = herramientasResultSet.inicializaUserDTO(rs);
							UserConfigDTO userConfig = herramientasResultSet.inicializaUserConfigDTO(rs);
							user.setUserConfigDTO(userConfig);
						}
						try{
							rs.close();
						}catch (Exception e) {
							e.printStackTrace();
						}	
					}else{
						System.out.println("rs == nullo en selectUserPorUsernameAndPassword");
					}
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				if(getDatabase().closeDatabase()){
					System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
				}else{
					System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("parametro de usuario nulo");
		}
		return user;	
	}
	
	public UserDTO selectUserPorUsername(String username){
		UserDTO user = null;
		if(username != null && !username.equals("") && username.length() > 0){
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());

				ResultSet rs = null;
				String query = "SELECT * FROM sec_users "
						+ "LEFT JOIN tb_empleados ON sec_users.fk_id_empleado = tb_empleados.id_empleado "
						+ "LEFT JOIN sec_users_config ON sec_users.fk_id_user_config = sec_users_config.id_user_config "
						+ "WHERE sec_users.login = '" + username + "' AND sec_users.active = 'Y';";
				try {
					rs = getDatabase().executeQuery(query);
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				try {
					if(rs != null){
						while(rs.next()){
							user = herramientasResultSet.inicializaUserDTO(rs);
							UserConfigDTO userConfig = herramientasResultSet.inicializaUserConfigDTO(rs);
							user.setUserConfigDTO(userConfig);
						}
						try{
							rs.close();
						}catch (Exception e) {
							e.printStackTrace();
						}	
					}else{
						System.out.println("rs == nullo en selectUserPorUsername");
					}
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				if(getDatabase().closeDatabase()){
					System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
				}else{
					System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("parametro de usuario nulo");
		}
		return user;	
	}
	
	public UserDTO selectUserPorUserId(int userId){
		UserDTO user = null;
		if(userId > 0){
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());

				ResultSet rs = null;
				String query = "SELECT * FROM sec_users "
						+ "LEFT JOIN tb_empleados ON sec_users.fk_id_empleado = tb_empleados.id_empleado "
						+ "LEFT JOIN sec_users_config ON sec_users.fk_id_user_config = sec_users_config.id_user_config "
						+ "WHERE sec_users.id_user = " + userId + " AND sec_users.active = 'Y';";
				try {
					System.out.println(query);
					rs = getDatabase().executeQuery(query);
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				try {
					if(rs != null){
						while(rs.next()){
							user = herramientasResultSet.inicializaUserDTO(rs);
							UserConfigDTO userConfig = herramientasResultSet.inicializaUserConfigDTO(rs);
							user.setUserConfigDTO(userConfig);
							System.out.println(userConfig.getUserInitService());
						}
						try{
							rs.close();
						}catch (Exception e) {
							e.printStackTrace();
						}	
					}else{
						System.out.println("rs == nullo en selectUserPorUserId");
					}
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				if(getDatabase().closeDatabase()){
					System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
				}else{
					System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("parametro de usuario nulo");
		}
		return user;	
	}
	
	/**
	 * SELECCIONA LAS CONFIGURACIONES DE UISER INIT SERVICE POR STATUS 
	 * -VERDADERO: SOLO LOS ACTIVOS 
	 * -FALSO: TODOS
	 * @param activos
	 * @return Vector<UserConfigDTO>
	 */
	public Vector<UserConfigDTO> selectUserConfigDTO(boolean activos){
		Vector<UserConfigDTO> userConfigs = new Vector<>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());

			ResultSet rs = null;
			String query = "SELECT * FROM sec_users_config";
					if(activos)
						query += " WHERE sec_users_config.status = 1 ORDER BY sec_users_config.nombre ASC";
					query += ";";
			try {
//				System.out.println(query);
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e) {					
				e.printStackTrace();
			}
			try {
				if(rs != null){
					while(rs.next()){
						UserConfigDTO userConfig = herramientasResultSet.inicializaUserConfigDTO(rs);
						if(userConfig != null){
							userConfigs.add(userConfig);
						}
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectUserConfigDTO");
				}
			} catch (SQLException e) {					
				e.printStackTrace();
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return userConfigs;
	}
	
	public Vector<MenuDTO> selectUserMenu(UserDTO user){	
		Vector<MenuDTO> menus = null;
		if(user != null ){
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				menus = new Vector<MenuDTO>();
				ResultSet rs = null;
				String query = "SELECT * FROM sec_groups_apps_actions"
						+ " LEFT JOIN sec_groups ON sec_groups_apps_actions.fk_group = sec_groups.group_id"
						+ " LEFT JOIN sec_users_groups ON sec_groups.group_id = sec_users_groups.group_id"
						+ " LEFT JOIN sec_users ON sec_users_groups.login = sec_users.login"
						+ " LEFT JOIN sec_apps_actions ON sec_groups_apps_actions.fk_app_action = sec_apps_actions.id_action"
						+ " LEFT JOIN sec_apps ON sec_apps_actions.fk_app = sec_apps.id_app"
						+ " WHERE sec_users.id_user = "+user.getUserId()+"  AND sec_groups.`status` = 1 AND sec_apps_actions.status_action = 1"
						+ " ORDER BY sec_apps.index_app, sec_apps_actions.fk_app, sec_groups_apps_actions.`index` ASC;";
				try {
					rs = getDatabase().executeQuery(query);
				} catch (SQLException e) {					
					e.printStackTrace();
				}
				if(rs != null){
					try {
						int idApp = 0;
						int i = -1;						
						MenuDTO menu = null;
						Vector<SubMenuDTO> subMenus = null;
						while(rs.next()){						
							if(idApp == 0){
								idApp = rs.getInt("sec_apps.id_app");
								user.getPerfil().setDescription(rs.getString("sec_groups.description"));
								menu = herramientasResultSet.inicializaUserMenuDTO(rs);																					
								if(menu != null){
									subMenus = new Vector<SubMenuDTO>();
									SubMenuDTO subMenu = new SubMenuDTO();
									subMenu = herramientasResultSet.inicializaUserSubMenuDTO(rs);
									if(subMenu != null){
										subMenus.add(subMenu);
										menu.setSubMenu(subMenus);										
									}else{
										System.out.println("un item del subMenu es nullo");
									}								
									menus.add(menu);	
									i++;
								}else{
									System.out.println("un item del menu es nullo");
								}							
							}else{
								if(idApp != rs.getInt("sec_apps.id_app")){
									idApp = rs.getInt("sec_apps.id_app");
									menu = herramientasResultSet.inicializaUserMenuDTO(rs);
									if(menu != null){
										subMenus = new Vector<SubMenuDTO>();
										SubMenuDTO subMenu = new SubMenuDTO();
										subMenu = herramientasResultSet.inicializaUserSubMenuDTO(rs);
										if(subMenu != null){
											subMenus.add(subMenu);
											menu.setSubMenu(subMenus);											
										}else{
											System.out.println("un item del subMenu es nullo");
										}
										menus.add(menu);
										i++;
									}else{
										System.out.println("un item del menu es nullo");
									}
								}else{								
									SubMenuDTO subMenu = new SubMenuDTO();
									subMenu = herramientasResultSet.inicializaUserSubMenuDTO(rs);
									if(subMenu != null){
										subMenus.add(subMenu);
										menu.setSubMenu(subMenus);
										
									}else{
										System.out.println("un item del subMenu es nullo");
									}								
									menus.set(i, menu); 
								}
							}
							
						}
						user.setMenu(menus);
					} catch (SQLException e) {						
						e.printStackTrace();
					}
					try {
						rs.close();
					} catch (SQLException e) {						
						e.printStackTrace();
					}
				}else{
					System.out.println("rs == null en selectUserMenu:" + this.getClass().getSimpleName());
				}
				if(getDatabase().closeDatabase()){
					System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
				}else{
					System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("parametro de usuario nulo");
		}
		return menus;		
	}
	
	public int actualizarPswdUsuario(UserDTO user){
		int res = -1;
		if(user != null && user.getIdEmpleado() > 0){
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName() + " metodo actualizarDatosUsuario");	
				String query = "UPDATE sec_users SET sec_users.pswd_txt ='" + user.getPassword() + "' WHERE sec_users.id_user = " + user.getUserId();
				try {
					res = getDatabase().executeNonQuery(query);					
				} catch (SQLException e) {					
					e.printStackTrace();
				}				
				if(getDatabase().closeDatabase()){
					System.out.println("conexion cerrada en " + this.getClass().getSimpleName() + " metodo actualizarDatosUsuario");
				}else{
					System.out.println("conexion no cerrada en " + this.getClass().getSimpleName() + " metodo actualizarDatosUsuario");
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName() + " metodo actualizarDatosUsuario");
			}
		}else{
			System.out.println("parametro de usuario nulo");
		}
		return res;
		
	}
	
	/**
	 * ACTUALIZA UNA IMAGEN PARA EL USUARIO
	 * @param user
	 * @param filePart
	 * @return
	 */
	public int actualizarAvatarUsuario(UserDTO user, Part filePart){
		int res = -1;
		if(user != null && user.getUserId() > 0){
			
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName() + " metodo actualizarAvatarUsuario");				
	            String sql = "UPDATE sec_users SET sec_users.user_avatar = ? WHERE sec_users.id_user = ?";
	            java.sql.PreparedStatement statement = null;	            
				try {
					statement = getDatabase().getCon().prepareStatement(sql);
					statement.setInt(2,user.getUserId());
				} catch (SQLException e2) {
					
					e2.printStackTrace();
				}
	            try {
					if (filePart.getInputStream() != null) {
					    // fetches input stream of the upload file for the blob column
					    statement.setBlob(1, filePart.getInputStream());
					}
				} catch (IOException e1) {
					
					e1.printStackTrace();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}	            
	            try {
	            	res = statement.executeUpdate();
				} catch (SQLException e1) {					
					e1.printStackTrace();
				}						
				if(getDatabase().closeDatabase()){
					System.out.println("conexion cerrada en " + this.getClass().getSimpleName() + " metodo actualizarDatosUsuario");
				}else{
					System.out.println("conexion no cerrada en " + this.getClass().getSimpleName() + " metodo actualizarDatosUsuario");
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName() + " metodo actualizarDatosUsuario");
			}
		}else{
			System.out.println("parametro de usuario nulo");
		}
		return res;
		
	}

	/**
	 * @return the database
	 */
	private DatabaseGateway getDatabase() {
		return database;
	}

	/**
	 * @param database the database to set
	 */
	private void setDatabase(DatabaseGateway database) {
		this.database = database;
	}
	
}//Termina clase