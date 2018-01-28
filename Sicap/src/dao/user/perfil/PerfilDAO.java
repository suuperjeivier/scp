package dao.user.perfil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import dao.DatabaseGateway;
import dto.user.perfil.PerfilDTO;
import herramientas.herrramientasrs.HerramientasResultSet;

public class PerfilDAO {
	private DatabaseGateway database = null;
// DatabaseGateway database;
	private HerramientasResultSet herramientasResultSet;

		public PerfilDAO(){
			if(this.database == null){
				this.database = new DatabaseGateway();
			}
			if(this.getDatabase() == null){
				this.setDatabase(new DatabaseGateway());
			}
			if(this.herramientasResultSet == null){
				this.herramientasResultSet = new HerramientasResultSet();
			}
			if(getHerramientasResultSet() == null){
				setHerramientasResultSet(new HerramientasResultSet());
			}
			InitializeComponents();		
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
		
		public void InitializeComponents(){
			if (getHerramientasResultSet() == null){
				setHerramientasResultSet(new HerramientasResultSet());
			}else{
				System.out.println("herramientasRS != null en " + this.getClass().getSimpleName() );
			}
		}
		
		/**
		 * Selecciona todos los usuarios en la tabla
		 * @return Vector<PerfilDTO>
		 */
		public Vector<PerfilDTO> selectPerfiles(){
			Vector<PerfilDTO> perfiles = null;			
				if(database.openDatabase()){
					System.out.println("conexion abierta en " + this.getClass().getSimpleName());
					ResultSet rs = null;
					String query = "SELECT * FROM sec_groups LEFT JOIN tb_tipos_estatus ON sec_groups.`status` = tb_tipos_estatus.status_int WHERE tb_tipos_estatus.aplicacion = 'general';";
					try {
						rs = database.executeQuery(query);
					} catch (SQLException e) {					
						e.printStackTrace();
					}
					try {
						if(rs != null){
							perfiles = new Vector<PerfilDTO>();
							while(rs.next()){
								PerfilDTO perfil = herramientasResultSet.inicializaPerfilDTO(rs);
								if(perfil != null){
									perfiles.add(perfil);
								}
							}
							try{
								rs.close();
							}catch (Exception e) {
								e.printStackTrace();
							}	
						}else{
							System.out.println("rs == nullo en selectPerfiles");
						}
					} catch (SQLException e) {					
						e.printStackTrace();
					}
					if(database.closeDatabase()){
						System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
					}else{
						System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
					}
				}else{
					System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
				}
			
			return perfiles;	
		}
		
		/**
		 * Selecciona todos los usuarios por estatus en la tabla
		 * @return Vector<PerfilDTO>
		 */
		public Vector<PerfilDTO> selectPerfilesPorStatus(int status){
			Vector<PerfilDTO> perfiles = null;			
				if(database.openDatabase()){
					System.out.println("conexion abierta en " + this.getClass().getSimpleName());
					ResultSet rs = null;
					String query = "SELECT * FROM sec_groups WHERE status = " + status + ";";
					try {
						rs = database.executeQuery(query);
					} catch (SQLException e) {					
						e.printStackTrace();
					}
					try {
						if(rs != null){
							perfiles = new Vector<PerfilDTO>();
							while(rs.next()){
								PerfilDTO perfil = herramientasResultSet.inicializaPerfilDTOSimple(rs);
								if(perfil != null){
									perfiles.add(perfil);
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
					if(database.closeDatabase()){
						System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
					}else{
						System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
					}
				}else{
					System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
				}
			
			return perfiles;	
		}

		/**
		 * 
		 * @param menuNuevo
		 * @return
		 */
		public int insertaNuevoPerfil(PerfilDTO perfilNuevo){		
			int res = -1;
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				String tabla = "sec_groups";
				String query= "INSERT INTO " + tabla + " "
						+ "("
							+ tabla + ".description, "
							+ tabla + ".status "
						+ ") VALUES"
						+ "('"
							+ perfilNuevo.getDescription()
							+ "', '" + perfilNuevo.getStatus()
						+"');";
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
		 * @param perfilNuevo
		 * @return
		 */
		public int actualizarPerfil(PerfilDTO perfilNuevo){		
			int editar = -1;
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				String tabla = "sec_groups";
				String query= "UPDATE " + tabla + " " 
							+ " SET "
							+ tabla + ".group_id = " + perfilNuevo.getIdPerfil() + ","
							+ tabla + ".description = '" + perfilNuevo.getDescription() + "',"
							+ tabla + ".status = " + perfilNuevo.getStatus()
							+ " WHERE "
							+ tabla + ".group_id = " + perfilNuevo.getIdPerfil();
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
}
