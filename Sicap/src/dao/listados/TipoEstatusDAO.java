package dao.listados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import dao.DatabaseGateway;
import dto.listados.TipoEstatusDTO;
import herramientas.herrramientasrs.HerramientasResultSet;

public class TipoEstatusDAO {
	
	private DatabaseGateway database = null;
	private HerramientasResultSet herramientasRS;
	
	public TipoEstatusDAO(){
		if(this.getDatabase() == null){
			this.setDatabase(new DatabaseGateway());
		}
		InitializeComponents();		
	}
	
	public TipoEstatusDAO(DatabaseGateway database){
		if(this.getDatabase() == null){
			if(database != null){
				this.setDatabase(database);
			}else{
				this.setDatabase(new DatabaseGateway());
			}
		}else{
			System.out.println("database != null en " + this.getClass().getSimpleName() );
		}
		InitializeComponents();		
	}
	
	public void InitializeComponents(){
		if(this.getHerramientasRS() == null){
			this.setHerramientasRS(new HerramientasResultSet());
		}
	}
	
	public Vector<TipoEstatusDTO> selectTiposEstatusesDTO(int tipoItem, String app){
		Vector<TipoEstatusDTO> tiposEstatusesDTO = null;
		String query = "SELECT * FROM tb_tipos_estatus WHERE tb_tipos_estatus.fk_id_item_tipo = " + tipoItem + " AND tb_tipos_estatus.aplicacion = '"+ app +"'";
		try {
			System.out.println(query);
			if(getDatabase() != null){
				if(getDatabase().openDatabase()){
					System.out.println("conexion abierta en " + this.getClass().getSimpleName());
					ResultSet rs = null;
					rs = getDatabase().executeQuery(query);
					tiposEstatusesDTO = new Vector<TipoEstatusDTO>();
					while (rs.next()) {
						TipoEstatusDTO tipoEstatus = null;
						tipoEstatus = getHerramientasRS().inicializaEstatusSimpleDTO(rs);
						if(tipoEstatus != null){
							tiposEstatusesDTO.add(tipoEstatus);
						}else{
							System.out.println("tipoEstatus == null en " + this.getClass().getSimpleName());
						}
					}
					try{
						rs.close();
					}catch (Exception e) {
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
			}else{
				System.out.println("database == null en " + this.getClass().getSimpleName() );
			}			
			
		} catch (Exception e) {				
			e.printStackTrace();
		}
			
		return tiposEstatusesDTO;
	}
	
	/**
	 * SELECCIONA EL ESTADO Y TIPO DE LA TABLA TIPOS_ESTATUS [JAVIER]20170314
	 * @param aplicacion
	 * @return Vector<EstatusDTO>
	 */
	public Vector<TipoEstatusDTO> selectTiposEstatusDTO(String aplicacion){		
		Vector<TipoEstatusDTO> tiposEstatus = new Vector<TipoEstatusDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_tipos_estatus WHERE tb_tipos_estatus.aplicacion ='" + aplicacion + "';";
			try {
				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						TipoEstatusDTO tipEstatus = getHerramientasRS().inicializaEstatusDTO(rs);
						if (tipEstatus != null){
							tiposEstatus.add(tipEstatus);
						}	
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectTiposEstatusDTO");
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
		return tiposEstatus;
	}
	
	/**
	 * SELECCIONA EL ESTADO Y TIPO DE LA TABLA TIPOS_ESTATUS [JAVIER]20170314
	 * @param aplicacion
	 * @return EstatusDTO
	 */
	public TipoEstatusDTO selectTiposEstatusDTOPorIdTipoEstatus(int idTipoEstatus){		
		TipoEstatusDTO tiposEstatus = null;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_tipos_estatus WHERE tb_tipos_estatus.id_tipo_estatus ='" + idTipoEstatus + "';";
			try {
//				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						tiposEstatus = getHerramientasRS().inicializaEstatusDTO(rs);
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
			if (getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return tiposEstatus;
	}

	/**
	 * @return the database
	 */
	public DatabaseGateway getDatabase() {
		return database;
	}

	/**
	 * @param database the database to set
	 */
	public void setDatabase(DatabaseGateway database) {
		this.database = database;
	}

	/**
	 * @return the herramientasRS
	 */
	public HerramientasResultSet getHerramientasRS() {
		return herramientasRS;
	}

	/**
	 * @param herramientasRS the herramientasRS to set
	 */
	public void setHerramientasRS(HerramientasResultSet herramientasRS) {
		this.herramientasRS = herramientasRS;
	}

}
