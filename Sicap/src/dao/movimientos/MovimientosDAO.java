package dao.movimientos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import dao.DatabaseGateway;
import dto.asignacion.AsignacionDTO;
import dto.listados.items.AccesorioDTO;
import dto.movimientos.MovimientoDTO;
import dto.movimientos.MovimientoItemDTO;
import herramientas.herrramientasrs.HerramientasResultSet;

public class MovimientosDAO {
	
	private DatabaseGateway database = null;
	private HerramientasResultSet herramientasRS;
	
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
	
	/**
	 * Construye la clase sin paramentros
	 */
	public MovimientosDAO(){
		if(this.getDatabase() == null){
			this.setDatabase(new DatabaseGateway());
		}else{
			System.out.println("database != null en " + this.getClass().getSimpleName() );
		}
		InitializeComponents();
	}
	
	/**
	 * Construye la clase con la instancia de base de datos por parametro para reutilizar el objeto
	 */
	public MovimientosDAO(DatabaseGateway database){
		if(this.getDatabase() == null){
			if(database != null){
				this.setDatabase(database);
			}else{
				this.setDatabase(new DatabaseGateway());
			}
		}else{
			System.out.println("database != null en " + this.getClass().getSimpleName());
		}
		InitializeComponents();
	}
	
	/**
	 * INICIALIZA LOS COMPONENTES DE LA CLASE
	 */
	private void InitializeComponents() {
		if(this.getHerramientasRS() == null){
			this.setHerramientasRS(new HerramientasResultSet());
		}	
	}
	
	public int insertMovimientoDTO(MovimientoDTO movDTO){		
		int res = -1;
		if(movDTO != null){
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				String tabla = "tb_movimientos";
				String query= "INSERT INTO " + tabla
						+ " ("
							+ "sku_movimiento"
							+ ", sku_movimiento_string"
							+ ", fk_id_partner_asignacion"
							+ ", comments"							
							+ ", fk_id_tipo_movimiento"
							+ ", fk_id_estatus_movimiento"
						+ ") "
						+ "VALUES"
						+ "("
							+ movDTO.getSkuMovimiento()
							+ ", '" + movDTO.getSkuMovimientoString() + "'"
							+ ", " + movDTO.getPartnerAsignacion().getIdPartner()
							+ ", '" + movDTO.getComments() + "'"						
							+ ", " + movDTO.getFkIdTipoMovimiento()
							+ ", " + movDTO.getStatusDTO().getIdTipoEstatus()
						+ ");";
				
				try {
					System.out.println(query);
					res = getDatabase().executeNonQuery(query);
					if(res > 0){
						res = selectLastInsert();
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
		}
		return res;
	}
	
	
	public int insertMovimientoItemDTO(MovimientoItemDTO movItemDTO){		
		int res = -1;
		if(movItemDTO != null){
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				
					String query= "INSERT INTO tb_movimientos_items"
							+ "("
								+ "fk_id_movimiento"
								+ ", fk_id_item"
								+ ", sku_movimiento_item"
								+ ", sku_string_movimiento_item"
								+ ", string_1"
								+ ", string_2"
								+ ", comments"
								+ ", boolean_1"
								+ ", boolean_2"
								+ ", boolean_3"
								+ ", boolean_4"
								+ ", boolean_5"
								+ ", boolean_6"
								+ ", boolean_7"
								+ ", boolean_8"
								+ ", boolean_9"
								+ ", boolean_10"
								+ ", boolean_11"
								+ ", boolean_12"
								+ ", status"
							+ ")"
						+ "VALUES"
							+ "("
								+ movItemDTO.getFkIdMovimiento().getIdMovimiento() + ", "
								+ movItemDTO.getFkIdItem().getIdItem() + ", "
								+ movItemDTO.getFkIdMovimiento().getSkuMovimiento()+ ", '"
								+ movItemDTO.getFkIdMovimiento().getSkuMovimientoString()+ "', '"
								+ movItemDTO.getString1() + "', '"
								+ movItemDTO.getString2()+ "', '"
								+ movItemDTO.getComentarios() + "', "
								+ (movItemDTO.isBoolean1() ? 1 : 0)+ ", "
								+ (movItemDTO.isBoolean2() ? 1 : 0)+ ", "
								+ (movItemDTO.isBoolean3() ? 1 : 0)+ ", "
								+ (movItemDTO.isBoolean4() ? 1 : 0)+ ", "
								+ (movItemDTO.isBoolean5() ? 1 : 0)+ ", "
								+ (movItemDTO.isBoolean6() ? 1 : 0)+ ", "
								+ (movItemDTO.isBoolean7() ? 1 : 0)+ ", "
								+ (movItemDTO.isBoolean8() ? 1 : 0)+ ", "
								+ (movItemDTO.isBoolean9() ? 1 : 0)+ ", "
								+ (movItemDTO.isBoolean10() ? 1 : 0)+ ", "
								+ (movItemDTO.isBoolean11() ? 1 : 0)+ ", "
								+ (movItemDTO.isBoolean12() ? 1 : 0) + ", "
								+ 1
							+ ")"
						;
				try {
					System.out.println(query);
					res = getDatabase().executeNonQuery(query);
					if(res > 0){
						res = selectLastInsert();
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
		}
		return res;
	}	
	
	
	public int selectLastInsert(){
		int res = -1;	
		String query= "SELECT LAST_INSERT_ID() AS lastId;";
		try {
			System.out.println(query);
			ResultSet rs = getDatabase().executeQuery(query);
			while (rs.next()) {
				res = rs.getInt("lastId");						
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}					
		return res;
	}
	
	
	/**
	 * METODOS DE LA CLASE MovimientosDAO--------------------------------------
	 */
	
	public Vector<MovimientoDTO> selectMovimientosDisponiblesSinItems(int tipoMovimiento){		
		Vector<MovimientoDTO> vMov = new Vector<MovimientoDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_movimientos LEFT JOIN tb_movimientos_items ON tb_movimientos.id_movimiento = tb_movimientos_items.fk_id_movimiento WHERE tb_movimientos_items.`status` = 0 AND (select count(*) from tb_movimientos_items where tb_movimientos_items.`status` = 1"
					+ " AND tb_movimientos_items.fk_id_movimiento = tb_movimientos.id_movimiento) = 0 GROUP BY tb_movimientos.id_movimiento ORDER BY tb_movimientos.id_movimiento;";
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						MovimientoDTO mov = getHerramientasRS().inicializaMovimientoDTO(rs);
						vMov.add(mov);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectMovimientosItem");
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
		return vMov;
	}	
	
	
	/**
	 * 
	 * @param tipoMovimiento
	 * @return
	 */
	public Vector<MovimientoItemDTO> selectMovimientosItem(int tipoMovimiento){		
		Vector<MovimientoItemDTO> vMovItem = new Vector<MovimientoItemDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_movimientos_items AS movItem "
					+ "LEFT JOIN tb_movimientos AS mov "
					+ "ON movItem.fk_id_movimiento = mov.id_movimiento "
					+ "LEFT JOIN tb_items ON movItem.fk_id_item = tb_items.id_item "
					+ "WHERE mov.fk_id_tipo_movimiento = "+tipoMovimiento;
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						MovimientoItemDTO MovItem = getHerramientasRS().inicializaMovimientosItemsDTO(rs);
						vMovItem.add(MovItem);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectMovimientosItem");
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
		return vMovItem;
	}
	
	/**
	 * 
	 * @param tipoMovimiento
	 * @return
	 */
	public Vector<AccesorioDTO> selectAccesoriosRadio(){		
		Vector<AccesorioDTO> accesoriosDTOs = new Vector<AccesorioDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_accesorios;";
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						AccesorioDTO accesorio = getHerramientasRS().inicializaAccesorioDTO(rs);
						accesoriosDTOs.add(accesorio);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectMovimientosItem");
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
		return accesoriosDTOs;
	}	
	
	
	/*
	 * METODOS DE LA CLASE MovimientosDAO--------------------------------------
	 */
	
	public Vector<AsignacionDTO> selectMovimientosAsignacionesItem(int tipoMovimiento){		
		Vector<AsignacionDTO> vMovItem = new Vector<AsignacionDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_asignaciones \r\n" + 
					"LEFT JOIN tb_movimientos_items ON tb_movimientos_items.id_movimiento_item = tb_asignaciones.fk_id_movimiento_item\r\n" + 
					"LEFT JOIN tb_movimientos ON tb_movimientos.id_movimiento = tb_movimientos_items.fk_id_movimiento\r\n" + 
					"LEFT JOIN tb_items ON tb_movimientos_items.fk_id_item = tb_items.id_item\r\n"+
					"LEFT JOIN tb_partners ON tb_partners.id_partner = tb_asignaciones.fk_id_partner where tb_movimientos.fk_id_partner_asignacion  > 0 and tb_movimientos.fk_id_tipo_movimiento = 1 and tb_partners.id_partner > 0\r\n" + 
					"ORDER BY tb_partners.Nombre";
			try {
				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						AsignacionDTO MovItem = getHerramientasRS().inicializaAsignacionDTO(rs);
						vMovItem.add(MovItem);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectMovimientosItem");
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
		return vMovItem;
	}
	
}
