package dao.movimientos.ordenes;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import dao.DatabaseGateway4_Ordenes;
import dto.asignacion.AsignacionDTO;
import dto.movimientos.MovimientoDTO;
import dto.movimientos.MovimientoItemDTO;
import dto.ordenes.DepartamentoDTO;
import dto.ordenes.DocumentoDTO;
import dto.ordenes.LugarDTO;
import dto.ordenes.OrdenDTO;
import dto.ordenes.OrdenLugarDTO;
import dto.ordenes.OrdenRadioDTO;
import dto.ordenes.OrdenTecnicoDTO;
import dto.ordenes.RadioDTO;
import dto.ordenes.TecnicoDTO;
import dto.ordenes.VehiculoDTO;
import herramientas.herrramientasrs.HerramientasResultSet;

public class OrdenesDAO {
	
	private DatabaseGateway4_Ordenes database = null;
	private HerramientasResultSet herramientasRS;
	
	/**
	 * @return the database
	 */
	public DatabaseGateway4_Ordenes getDatabase() {
		return database;
	}
	
	/**
	 * @param database the database to set
	 */
	public void setDatabase(DatabaseGateway4_Ordenes database) {
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
	public OrdenesDAO(){
		if(this.getDatabase() == null){
			this.setDatabase(new DatabaseGateway4_Ordenes());
		}else{
			System.out.println("database != null en " + this.getClass().getSimpleName() );
		}
		InitializeComponents();
	}
	
	/**
	 * Construye la clase con la instancia de base de datos por parametro para reutilizar el objeto
	 */
	public OrdenesDAO(DatabaseGateway4_Ordenes database){
		if(this.getDatabase() == null){
			if(database != null){
				this.setDatabase(database);
			}else{
				this.setDatabase(new DatabaseGateway4_Ordenes());
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
	
	public Vector<OrdenDTO> selectOrdenes(){		
		Vector<OrdenDTO> ordenes = new Vector<OrdenDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM ordenes \r\n" + 
					"LEFT JOIN vehiculos ON ordenes.vehiculo_id = vehiculos.vehiculo_id\r\n" + 
					"LEFT JOIN tecnicos ON ordenes.tecnico_id = tecnicos.tecnico_id\r\n" +	
					"LEFT JOIN tecnicos AS tecnicosChoferes ON ordenes.tecnico_chofer_id = tecnicosChoferes.tecnico_id\r\n" +	
					"LEFT JOIN departamentos ON tecnicos.departamento_id = departamentos.departamento_id\r\n" +
					" WHERE ordenes.status = 1 ORDER BY ordenes.fecha, ordenes.orden_id DESC";
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						OrdenDTO orden = getHerramientasRS().inicializaOrdenDTO(rs);
						ordenes.add(orden);					
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
		return ordenes;
	}
	
	public Vector<Object> selectTablaFromOrdenesOrderByNombre(String nombreTabla, boolean banderaControl){		
		Vector<Object> objeto = new Vector<Object>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT * FROM "+nombreTabla;
			switch (nombreTabla) {
			case "lugares":
			case "vehiculos":			
				query += " ORDER BY "+nombreTabla+".nombre ASC;";	
				
				break;
			case "tecnicos":
				query += " LEFT JOIN departamentos ON "+nombreTabla+".departamento_id = departamentos.departamento_id";
				if(banderaControl){
					query += " WHERE "+nombreTabla+".licencia IS NOT NULL AND TRIM("+nombreTabla+".licencia) <> ''" ;
				}
				query += " ORDER BY "+nombreTabla+".nombre ASC;";		
				break;
			case "radios":
				query += " LEFT JOIN tecnicos ON "+nombreTabla+".tecnico_id = tecnicos.tecnico_id"
						+ " LEFT JOIN departamentos ON tecnicos.departamento_id = departamentos.departamento_id"
						+ " ORDER BY tecnicos.nombre ASC;";				
				break;
			default:
				break;
			}
			System.out.println(query);
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					switch (nombreTabla) {
					case "lugares":
						while(rs.next()){
							LugarDTO lugarDTO = getHerramientasRS().inicializaLugarDTO(rs, nombreTabla);
							objeto.add(lugarDTO);
						}
						try{
							rs.close();
						}catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "radios":
						while(rs.next()){
							RadioDTO radioDTO = getHerramientasRS().inicializaRadioDTO(rs, nombreTabla);
							objeto.add(radioDTO);
						}
						try{
							rs.close();
						}catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "tecnicos":
						while(rs.next()){
							TecnicoDTO tecnicoDTO = getHerramientasRS().inicializaTecnicoDTO(rs, nombreTabla);
							objeto.add(tecnicoDTO);
						}
						try{
							rs.close();
						}catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "vehiculos":
						while(rs.next()){
							VehiculoDTO vehiculoDTO = getHerramientasRS().inicializaVehiculoDTO(rs, nombreTabla);
							objeto.add(vehiculoDTO);
						}
						try{
							rs.close();
						}catch (Exception e) {
							e.printStackTrace();
						}
						break;

					default:
						break;
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
		return objeto;
	}
	
	public Vector<Object> selectTablaFromOrdenesByOrderId(String nombreTabla, int orderId){		
		Vector<Object> objeto = new Vector<Object>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT * FROM "+nombreTabla;
			switch (nombreTabla) {
			case "ordeneslugares":
			case "ordenestecnicos":	
			case "ordenesradios":	
			case "documentos":
				query += " WHERE " + nombreTabla + ".orden_id = " + orderId + " AND " + nombreTabla + ".status = 1;";	

				break;
			default:
				break;
			}
			System.out.println(query);
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					switch (nombreTabla) {
					case "ordeneslugares":
						while(rs.next()){
							 OrdenLugarDTO ordenLugarDTO = getHerramientasRS().inicializaOrdenLugarDTO(rs, nombreTabla);
							objeto.add(ordenLugarDTO);
						}
						try{
							rs.close();
						}catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "ordenestecnicos":
						while(rs.next()){
							OrdenTecnicoDTO ordenTecnicoDTO = getHerramientasRS().inicializaOrdenTecnicoDTO(rs, nombreTabla);
							objeto.add(ordenTecnicoDTO);
						}
						try{
							rs.close();
						}catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "ordenesradios":
						while(rs.next()){
							OrdenRadioDTO ordenRadioDTO = getHerramientasRS().inicializaOrdenRadioDTO(rs, nombreTabla);
							objeto.add(ordenRadioDTO);
						}
						try{
							rs.close();
						}catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case "documentos":
						while(rs.next()){
							DocumentoDTO documentoDTO = getHerramientasRS().inicializaDocumentoDTO(rs, nombreTabla);
							objeto.add(documentoDTO);
						}
						try{
							rs.close();
						}catch (Exception e) {
							e.printStackTrace();
						}
						break;

					default:
						break;
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
		return objeto;
	}
	
	public int insertNewDepto(DepartamentoDTO depto){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());			
			String query= "INSERT INTO departamentos"
					+ "("
						+ "nombre"
						+ ", siglas"
					+ ") "
					+ "VALUES("
						+ "'"+ depto.getNombre() + "'"
						+ ", '"+ depto.getSiglas() + "'"
					+ ");";
			try {
				res = getDatabase().executeNonQuery(query);				
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
		return res;
	}
	
	public int updateDeptoDTO(DepartamentoDTO depto){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			
			String query= "UPDATE departamentos SET"
					+ " departamentos.nombre = '" + depto.getNombre() +"'"
					+ ", departamentos.siglas = '" + depto.getSiglas() + "'"
					+ " WHERE departamentos.departamento_id = " + depto.getDepartamentoId() + ";";
			try {
				res = getDatabase().executeNonQuery(query);				
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
		return res;
	}
	
	public int insertNewLugar(LugarDTO lugarDTO){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "lugares";
			String query= "INSERT INTO "+ tabla
					+ "("
						+ tabla+".nombre"
						+ ", "+tabla+".ubicacion"
						+ ", "+tabla+".tipo"
					+ ") "
					+ "VALUES("
						+ "'"+ lugarDTO.getNombre() + "'"
						+ ", '"+ lugarDTO.getUbicacion() + "'"
						+ ", '"+ lugarDTO.getTipo() + "'"
					+ ");";
			try {
				res = getDatabase().executeNonQuery(query);				
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
		return res;
	}
	
	public int updateLugarDTO(LugarDTO lugarDTO){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "lugares";
			String query= "UPDATE "+tabla+" SET"
					+ " "+tabla+".nombre = '" + lugarDTO.getNombre() +"'"
					+ ", "+tabla+".ubicacion = '" + lugarDTO.getUbicacion() + "'"
					+ ", "+tabla+".tipo = '" + lugarDTO.getTipo() + "'"
					+ " WHERE "+tabla+".lugar_id = " + lugarDTO.getLugarId() + ";";
			try {
				res = getDatabase().executeNonQuery(query);				
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
		return res;
	}
	
	public int insertNewRadioDTO(RadioDTO radioDTO){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "radios";
			String query= "INSERT INTO "+ tabla
					+ "("
						+ tabla+".RFSI"
						+ ", "+tabla+".tipo"
						+ ", "+tabla+".marca"
						+ ", "+tabla+".modelo"
						+ ", "+tabla+".serie"
						+ ", "+tabla+".tecnico_id"						
					+ ") "
					+ "VALUES("
						+ "'"+ radioDTO.getRfsi() + "'"
						+ ", '"+ radioDTO.getTipo() + "'"
						+ ", '"+ radioDTO.getMarca() + "'"
						+ ", '"+ radioDTO.getModelo() + "'"
						+ ", '"+ radioDTO.getSerie() + "'"
						+ ", "+ radioDTO.getTecnicoDTO().getTecnicoId()
					+ ");";
			try {
				res = getDatabase().executeNonQuery(query);				
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
		return res;
	}
	
	public int updateRadioDTO(RadioDTO radioDTO){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "radios";
			String query= "UPDATE "+tabla+" SET"
					+ " "+tabla+".RFSI = '" + radioDTO.getRfsi() +"'"
					+ ", "+tabla+".tipo = '" + radioDTO.getTipo() + "'"
					+ ", "+tabla+".marca = '" + radioDTO.getMarca() + "'"
					+ ", "+tabla+".modelo = '" + radioDTO.getModelo() + "'"
					+ ", "+tabla+".serie = '" + radioDTO.getSerie() + "'"
					+ ", "+tabla+".tecnico_id = " + radioDTO.getTecnicoDTO().getTecnicoId()					
					+ " WHERE "+tabla+".radio_id = " + radioDTO.getRadioId() + ";";
			try {
				res = getDatabase().executeNonQuery(query);				
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
		return res;
	}
	
	public int insertNewTecnicoDTO(TecnicoDTO tecnicoDTO){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "tecnicos";
			String query= "INSERT INTO "+ tabla
					+ "("
						+ tabla + ".iniciales"
						+ ", " + tabla + ".titulo"
						+ ", " + tabla + ".nombre"
						+ ", " + tabla + ".puesto"
						+ ", " + tabla + ".departamento_id"
						+ ", " + tabla + ".mando"
						+ ", " + tabla + ".licencia"
					+ ") "
					+ "VALUES("
						+ "'" + tecnicoDTO.getIniciales() + "'"
						+ ", '" + tecnicoDTO.getTitulo() + "'"
						+ ", '" + tecnicoDTO.getNombre() + "'"
						+ ", '" + tecnicoDTO.getPuesto() + "'"
						+ ", " + tecnicoDTO.getDepartamentoDTO().getDepartamentoId()
						+ ", '" + tecnicoDTO.getMando()+ "'"
						+ ", '" + tecnicoDTO.getLicencia()+ "'"						
					+ ");";
			try {
				System.out.println(query);
				res = getDatabase().executeNonQuery(query);				
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
		return res;
	}
	
	public int updateTecnicoDTO(TecnicoDTO tecnicoDTO){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "tecnicos";
			String query= "UPDATE "+tabla+" SET"
					+ " "+tabla+".inIciales = '" + tecnicoDTO.getNombre() +"'"
					+ ", "+tabla+".titulo = '" + tecnicoDTO.getTitulo() + "'"
					+ ", "+tabla+".nombre = '" + tecnicoDTO.getNombre() + "'"
					+ ", "+tabla+".puesto = '" + tecnicoDTO.getPuesto() + "'"					
					+ ", "+tabla+".departamento_id = " + tecnicoDTO.getDepartamentoDTO().getDepartamentoId()
					+ ", "+tabla+".mando = '" + tecnicoDTO.getMando() + "'"
					+ ", "+tabla+".licencia = '" + tecnicoDTO.getLicencia() + "'"
					+ " WHERE "+tabla+".tecnico_id = " + tecnicoDTO.getTecnicoId() + ";";
			try {
				res = getDatabase().executeNonQuery(query);				
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
		return res;
	}
	
	public int insertNewVehiculoDTO(VehiculoDTO vehiculoDTO){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "vehiculos";
			String query= "INSERT INTO "+ tabla
					+ "("
						+ tabla+".nombre"
						+ ", "+tabla+".tipo"
						+ ", "+tabla+".marca"
						+ ", "+tabla+".linea"
						+ ", "+tabla+".modelo"
						+ ", "+tabla+".placas"
						+ ", "+tabla+".inventario"
						+ ", "+tabla+".num_poliza_seguro"
						+ ", "+tabla+".activo"						
					+ ") "
					+ "VALUES("
						+ "'"+ vehiculoDTO.getNombre() + "'"
						+ ", '"+ vehiculoDTO.getTipo() + "'"
						+ ", '"+ vehiculoDTO.getMarca() + "'"
						+ ", '"+ vehiculoDTO.getLinea() + "'"
						+ ", '"+ vehiculoDTO.getModelo() + "'"
						+ ", '"+ vehiculoDTO.getPlacas() + "'"
						+ ", '"+ vehiculoDTO.getInventario() + "'"
						+ ", '"+ vehiculoDTO.getNumPolizaSeguro() + "'"
						+ ", '"+ vehiculoDTO.getActivo() + "'"
					+ ");";
			try {
				res = getDatabase().executeNonQuery(query);				
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
		return res;
	}
	
	public int insertNewVehiculoDTOReturningLastId(VehiculoDTO vehiculoDTO){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "vehiculos";
			String query= "INSERT INTO "+ tabla
					+ "("
						+ tabla+".nombre"
						+ ", "+tabla+".tipo"
						+ ", "+tabla+".marca"
						+ ", "+tabla+".linea"
						+ ", "+tabla+".modelo"
						+ ", "+tabla+".placas"
						+ ", "+tabla+".inventario"
						+ ", "+tabla+".num_poliza_seguro"
						+ ", "+tabla+".activo"						
					+ ") "
					+ "VALUES("
						+ "'"+ vehiculoDTO.getNombre() + "'"
						+ ", '"+ vehiculoDTO.getTipo() + "'"
						+ ", '"+ vehiculoDTO.getMarca() + "'"
						+ ", '"+ vehiculoDTO.getLinea() + "'"
						+ ", '"+ vehiculoDTO.getModelo() + "'"
						+ ", '"+ vehiculoDTO.getPlacas() + "'"
						+ ", '"+ vehiculoDTO.getInventario() + "'"
						+ ", '"+ vehiculoDTO.getNumPolizaSeguro() + "'"
						+ ", '"+ vehiculoDTO.getActivo() + "'"
					+ ");";
			try {
				res = getDatabase().executeNonQuery(query);
				res = selectLastInsert();
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
		return res;
	}
	
	public int updateVehiculoDTO(VehiculoDTO vehiculoDTO){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "vehiculos";
			String query= "UPDATE "+tabla+" SET"
					+ " "+tabla+".nombre = '" + vehiculoDTO.getNombre() +"'"
					+ ", "+tabla+".tipo = '" + vehiculoDTO.getTipo() + "'"
					+ ", "+tabla+".marca = '" + vehiculoDTO.getMarca() + "'"
					+ ", "+tabla+".linea = '" + vehiculoDTO.getLinea() + "'"
					+ ", "+tabla+".modelo = '" + vehiculoDTO.getModelo() + "'"
					+ ", "+tabla+".placas = '" + vehiculoDTO.getPlacas() + "'"
					+ ", "+tabla+".inventario = '" + vehiculoDTO.getInventario() + "'"
					+ ", "+tabla+".num_poliza_seguro = '" + vehiculoDTO.getNumPolizaSeguro() + "'"
					+ ", "+tabla+".activo = '" + vehiculoDTO.getActivo() + "'"					
					+ " WHERE "+tabla+".vehiculo_id = " + vehiculoDTO.getVehiculoId() + ";";
			try {
				res = getDatabase().executeNonQuery(query);				
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
		return res;
	}
	
	public Vector<DepartamentoDTO> selectDeptosDTO(){		
		Vector<DepartamentoDTO> deptos = new Vector<DepartamentoDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String tabla = "departamentos";
			String query= "SELECT * FROM "+tabla+" ORDER BY "+tabla+".nombre DESC;";			
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						DepartamentoDTO depto = getHerramientasRS().inicializaDepartamentoDTO(rs);
						deptos.add(depto);					
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
		return deptos;
	}
	
	public Vector<LugarDTO> selectLugaresDTO(){		
		Vector<LugarDTO> lugares = new Vector<LugarDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String tabla = "lugares";
			String query= "SELECT * FROM "+tabla+" ORDER BY "+tabla+".nombre DESC;";					
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						LugarDTO lugar = getHerramientasRS().inicializaLugarDTO(rs, tabla);
						lugares.add(lugar);					
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
		return lugares;
	}
	
	public Vector<RadioDTO> selectRadiosDTO(){		
		Vector<RadioDTO> radioDTOs = new Vector<RadioDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String tabla = "radios";
			String query= "SELECT * FROM "+tabla+" LEFT JOIN tecnicos ON "+tabla+".tecnico_id = tecnicos.tecnico_id"
					+ " LEFT JOIN departamentos ON tecnicos.departamento_id = departamentos.departamento_id ORDER BY "+tabla+".RFSI DESC;";					
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						RadioDTO radioDTO = getHerramientasRS().inicializaRadioDTO(rs, tabla);
						radioDTOs.add(radioDTO);					
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
		return radioDTOs;
	}
	
	public Vector<TecnicoDTO> selectTecnicosDTO(){		
		Vector<TecnicoDTO> tecnicosDTO = new Vector<TecnicoDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String tabla = "tecnicos";
			String query= "SELECT * FROM "+tabla+" LEFT JOIN departamentos ON "+tabla+".departamento_id = departamentos.departamento_id ORDER BY "+tabla+".nombre DESC;";					
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						TecnicoDTO tecnicoDTO = getHerramientasRS().inicializaTecnicoDTO(rs, tabla);
						tecnicosDTO.add(tecnicoDTO);					
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
		return tecnicosDTO;
	}
	
	public Vector<VehiculoDTO> selectVehiculosDTO(){		
		Vector<VehiculoDTO> vehiculoDTOs = new Vector<VehiculoDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String tabla = "vehiculos";
			String query= "SELECT * FROM "+tabla+" ORDER BY "+tabla+".nombre DESC;";					
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						VehiculoDTO vehiculoDTO = getHerramientasRS().inicializaVehiculoDTO(rs, tabla);
						vehiculoDTOs.add(vehiculoDTO);					
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
		return vehiculoDTOs;
	}
	
	public DocumentoDTO selectDocumentoDTO(int idDoc){		
		DocumentoDTO doc = new DocumentoDTO();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String tabla = "documentos";
			String query= "SELECT * FROM "+tabla+" WHERE "+tabla+".documento_id = "+idDoc+";";					
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						doc = getHerramientasRS().inicializaDocumentoDTOConArchivoBlob(rs, tabla);										
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
		return doc;
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

	public int insertOrder(OrdenDTO ordenDTO, Vector<OrdenLugarDTO> ordenLugarDTOs,
			Vector<OrdenTecnicoDTO> ordenTecnicoDTOs, Vector<OrdenRadioDTO> ordenRadioDTOs, Vector<DocumentoDTO> documentoDTOs) throws SQLException {
		int res = -1;
		if(ordenDTO != null){			
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName() + " metodo insertOrUpdateOrder");
				if(ordenDTO.getOrderId() > 0){
					//UPDATE DE ORDENES
				}else{
					String tabla = "ordenes";
					String sql = "INSERT INTO  " + tabla
							+ " ("
							+ "	fecha"
							+ ", noOficio"
							+ ", fechaSalida"
							+ ", fechaLlegada"
							+ ", vehiculo_id"
							+ ", tecnico_id"
							+ ", tecnico_chofer_id"
							+ ", reporte"
							+ ", fechaSalidaR"
							+ ", fechaLlegadaR"
							+ ", noOficioLib"
							+ ", sum_viatico"
							+ ", pagado"
							+ ", vehiculoR_id"
							+ ", noTarjeta"
							+ ", status"
							+ ")"
							+ " VALUES"
							+ "("
							+ "?"
							+ ", ?"
							+ ", ?"
							+ ", ?"
							+ ", ?"
							+ ", ?"
							+ ", ?"
							+ ", ?"
							+ ", ?"
							+ ", ?"
							+ ", ?"
							+ ", ?"
							+ ", ?"
							+ ", ?"
							+ ", ?"
							+ ", ?"
							+ ");";
					java.sql.PreparedStatement statement = null;	      	
					
					try {
						statement = getDatabase().getCon().prepareStatement(sql);
						statement.setDate(1, ordenDTO.getFecha().getFechaDateSQL());
						statement.setString(2, ordenDTO.getNoOficio());
						statement.setDate(3, ordenDTO.getFechaSalida().getFechaDateSQL());
						statement.setDate(4, ordenDTO.getFechaLlegada().getFechaDateSQL());
						statement.setInt(5, ordenDTO.getVehiculoDTO().getVehiculoId());
						statement.setInt(6, ordenDTO.getTecnicoDTO().getTecnicoId());
						statement.setInt(7, ordenDTO.getTecnicoChoferDTO().getTecnicoId());
						statement.setString(8, ordenDTO.getReporte());
						statement.setDate(9, ordenDTO.getFechaSalida().getFechaDateSQL());//solo se llenan pero son datos repetidos
						statement.setDate(10, ordenDTO.getFechaLlegada().getFechaDateSQL());//solo se llenan pero son datos repetidos
						statement.setString(11, ordenDTO.getNoOficio());//solo se llenan pero son datos repetidos
						statement.setDouble(12, ordenDTO.getSumViaticos());
						statement.setString(13, "N");
						statement.setInt(14, ordenDTO.getVehiculoDTO().getVehiculoId());//solo se llenan pero son datos repetidos
						statement.setString(15, ordenDTO.getNoOficio());//solo se llenan pero son datos repetidos
						statement.setInt(16, 1);
					} catch (SQLException e2) {

						e2.printStackTrace();
					}

					try {
						res = statement.executeUpdate();
					} catch (SQLException e1) {					
						e1.printStackTrace();
					}
					statement.clearParameters();
					statement.close();
				}
				if(res > 0){
					ordenDTO.setOrderId(selectLastInsert());//se obtiene el id de la orden para relizar los otros insert
					if(ordenDTO.getOrderId() > 0){
						res = ordenDTO.getOrderId();
						if(ordenLugarDTOs != null){
							if(ordenLugarDTOs.size() > 0){
								String tablaOrdenesLugares = "ordeneslugares";
								/*java.sql.PreparedStatement statement2 = null;							
								String sql = "INSERT INTO  " + tablaOrdenesLugares
										+" ("
										+ "orden_id"
										+ ", lugar_id"
										+ ", actividad"
										+ ")"
										+ " VALUES"
										+ "("
										+ "?"
										+ ", ?"
										+ ", ?"
										+ ");";
								statement2 = getDatabase().getCon().prepareStatement(sql);
								getDatabase().getCon().setAutoCommit(false);
								for(OrdenLugarDTO ordenLugarDTO : ordenLugarDTOs){
									ordenLugarDTO.setOrdenDTO(ordenDTO);
									statement2.setInt(1, ordenLugarDTO.getOrdenDTO().getOrderId());
									statement2.setInt(2, ordenLugarDTO.getLugarDTO().getLugarId());
									statement2.setString(3, ordenLugarDTO.getActividad());
									statement2.addBatch();						
								}
								statement2.executeBatch();
								getDatabase().getCon().commit();
								statement2.clearParameters();
								statement2.close();*/
								int resInPlaces = insertarOrdenesPlaces(ordenLugarDTOs, ordenDTO, tablaOrdenesLugares);
								if(resInPlaces > 0){
									System.out.println("lugar insertado");
								}else{
									System.out.println("lugar no insertado");
								}
								
							}else{
								System.out.println("sin ordenes lugares por insertar!");
							}
						}else{
							System.out.println("ordenes lugares == null!");
						}
						if(ordenTecnicoDTOs != null){							
							if(ordenTecnicoDTOs.size() > 0){
								String tablaOrdenesTecnicos = "ordenestecnicos";
								/*java.sql.PreparedStatement statement3 = null;								
								String sql = "INSERT INTO  " + tablaOrdenesTecnicos
										+" ("
										+ "orden_id"
										+ ", tecnico_id"
										+ ", viatico"
										+ ")"
										+ " VALUES"
										+ "("
										+ "?"
										+ ", ?"
										+ ", ?"
										+ ");";
								statement3 = getDatabase().getCon().prepareStatement(sql);								
								if(statement3 != null){
									getDatabase().getCon().setAutoCommit(false);									
									for(OrdenTecnicoDTO ordenTecnicoDTO : ordenTecnicoDTOs){
										if(ordenTecnicoDTO != null){
											ordenTecnicoDTO.setOrdenDTO(ordenDTO);
											statement3.setInt(1, ordenTecnicoDTO.getOrdenDTO().getOrderId());
											statement3.setInt(2, ordenTecnicoDTO.getTecnicoDTO().getTecnicoId());						
											statement3.setDouble(3, 0.00);
											statement3.addBatch();		
										}else{
											System.out.println("ordenTecnicoDTO == null");
										}
									}
									statement3.executeBatch();
									statement3.clearParameters();
									statement3.close();
									getDatabase().getCon().commit();
								}else{
									System.out.println("statement3 null");
								}*/	
								int resInTec = insertarOrdenesTecnicos(ordenTecnicoDTOs, ordenDTO, tablaOrdenesTecnicos);
								if(resInTec > 0){
									System.out.println("tecnico insertado");
								}else{
									System.out.println("tecnico no insertado");
								}
							}else{
								System.out.println("sin ordenes tecnicos por insertar!");
							}
						}else{
							System.out.println("sin ordenes tecnicos  == null!");
						}
						if(ordenRadioDTOs != null){							
							if(ordenRadioDTOs.size() > 0){
								String tablaOrdenesRadios = "ordenesradios";
								/*java.sql.PreparedStatement statement4 = null;								
								String sql = "INSERT INTO  " + tablaOrdenesRadios
										+" ("
										+ "orden_id"
										+ ", radio_id"							
										+ ")"
										+ " VALUES"
										+ "("
										+ "?"
										+ ", ?"							
										+ ");";
								statement4 = getDatabase().getCon().prepareStatement(sql);
								getDatabase().getCon().setAutoCommit(false);
								for(OrdenRadioDTO ordenRadioDTO : ordenRadioDTOs){
									ordenRadioDTO.setOrdenDTO(ordenDTO);
									statement4.setInt(1, ordenRadioDTO.getOrdenDTO().getOrderId());
									statement4.setInt(2, ordenRadioDTO.getRadioDTO().getRadioId());						
									statement4.addBatch();						
								}
								statement4.executeBatch();
								statement4.clearParameters();
								statement4.close();
								getDatabase().getCon().commit();*/
								//version con insert clasico
								int resInRad = insertarOrdenesRadios(ordenRadioDTOs, ordenDTO, tablaOrdenesRadios);								
								if(resInRad > 0){
									System.out.println("radio insertado");
								}else{
									System.out.println("radio no insertado");
								}
								
							}else{
								System.out.println("sin ordenes tecnicos por insertar!");
							}
						}else{
							System.out.println("sin ordenes tecnicos == null!");
						}
						if(documentoDTOs != null){							
							if(documentoDTOs.size() > 0){
								java.sql.PreparedStatement statement5 = null;
								String tablaDocumentos = "documentos";
								String sql = "INSERT INTO  " + tablaDocumentos
										+" ("
										+ "documento_id"
										+ ", orden_id"
										+ ", archivo"	
										+ ", fechacarga"	
										+ ", nombrearchivo"	
										+ ", descripcion"	
										+ ")"
										+ " VALUES"
										+ "("
										+ "?"
										+ ", ?"
										+ ", ?"
										+ ", ?"
										+ ", ?"
										+ ", ?"
										+ ");";
								statement5 = getDatabase().getCon().prepareStatement(sql);
								getDatabase().getCon().setAutoCommit(false);
								for(DocumentoDTO documento : documentoDTOs){
									documento.setOrdenDTO(ordenDTO);
									statement5.setInt(1, documento.getDocumentoId());
									statement5.setInt(2, documento.getOrdenDTO().getOrderId());		
									Blob blob = getDatabase().getCon().createBlob();
									blob.setBytes(1, documento.getArchivoEnByteArray());
									statement5.setBlob(3, blob);
									statement5.setDate(4, documento.getFechaHoraCargaDTO().getFechaHoraSql());
									statement5.setString(5, documento.getNombreArchivo());
									statement5.setString(6, documento.getDescripcion());
									statement5.addBatch();						
								}
								statement5.executeBatch();
								statement5.clearParameters();
								statement5.close();
								getDatabase().getCon().commit();
							}else{
								System.out.println("sin documentoDTOs en ordenes por insertar!");
							}
						}else{
							System.out.println("sin documentoDTOs en ordenes == null!");
						}
					}else{
						System.out.println("Sin id de orden para las relaciones!");
					}
				}else{
					System.out.println("ERROR ORDEN NO INSERTADA!");
				}
				// termian proceso de actualizacion o insercion
				if(getDatabase().closeDatabase()){
					System.out.println("conexion cerrada en " + this.getClass().getSimpleName() + " metodo actualizarDatosUsuario");
				}else{
					System.out.println("conexion no cerrada en " + this.getClass().getSimpleName() + " metodo actualizarDatosUsuario");
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName() + " metodo actualizarDatosUsuario");
			}
		}else{
			System.out.println("parametro de ordenDTO == nulo en OrdnesDAO");
		}
		return res;
		
	}
	
	private int insertarOrdenesPlaces(Vector<OrdenLugarDTO> ordenLugarDTOs, OrdenDTO ordenDTO, String tablaOrdenesLugares) throws SQLException{
		int resInPlace = -1;
		for(OrdenLugarDTO ordenLugarDTO : ordenLugarDTOs){
			ordenLugarDTO.setOrdenDTO(ordenDTO);									
			String sql = "INSERT INTO  " + tablaOrdenesLugares
					+" ("
					+ "orden_id"
					+ ", lugar_id"
					+ ", actividad"
					+ ")"
					+ " VALUES"
					+ "("
					+ ordenLugarDTO.getOrdenDTO().getOrderId()
					+ ", " + ordenLugarDTO.getLugarDTO().getLugarId()
					+ ", '" + ordenLugarDTO.getActividad() + "'"
					+ ");";	
			resInPlace = getDatabase().executeNonQuery(sql);			
		}
		return resInPlace;
	}
	
	
	private int insertarOrdenesTecnicos(Vector<OrdenTecnicoDTO> ordenTecnicoDTOs, OrdenDTO ordenDTO, String tablaOrdenesTecnicos) throws SQLException{
		int resInTec = -1;
		for(OrdenTecnicoDTO ordenTecnicoDTO : ordenTecnicoDTOs){
			if(ordenTecnicoDTO != null){
				ordenTecnicoDTO.setOrdenDTO(ordenDTO);	
				String sql = "INSERT INTO  " + tablaOrdenesTecnicos
						+" ("
						+ "orden_id"
						+ ", tecnico_id"
						+ ", viatico"
						+ ")"
						+ " VALUES"
						+ "("
						+ ordenTecnicoDTO.getOrdenDTO().getOrderId()
						+ ", " + ordenTecnicoDTO.getTecnicoDTO().getTecnicoId()
						+ ", " + 0.00
						+ ");";
				resInTec = getDatabase().executeNonQuery(sql);				
			}else{
				System.out.println("ordenTecnicoDTO == null");
			}
		}//termina ciclo
		return resInTec;
	}
	
	private int insertarOrdenesRadios(Vector<OrdenRadioDTO> ordenRadioDTOs, OrdenDTO ordenDTO, String tablaOrdenesRadios) throws SQLException{
		int resInRad = -1;
		for(OrdenRadioDTO ordenRadioDTO : ordenRadioDTOs){
			ordenRadioDTO.setOrdenDTO(ordenDTO);
			String sql = "INSERT INTO  " + tablaOrdenesRadios
					+" ("
					+ "orden_id"
					+ ", radio_id"							
					+ ")"
					+ " VALUES"
					+ "("
					+ ordenRadioDTO.getOrdenDTO().getOrderId()
					+ ", " + ordenRadioDTO.getRadioDTO().getRadioId() 							
					+ ");";
			resInRad = getDatabase().executeNonQuery(sql);
			
		}//termina ciclo
		return resInRad;
	}
	
	
	/**
	 * 
	 * @param ordenDTO
	 * @param ordenLugarDTOs
	 * @param ordenTecnicoDTOs
	 * @param ordenRadioDTOs
	 * @param documentoDTOs
	 * @return
	 * @throws SQLException
	 */
	public int updateOrder(OrdenDTO ordenDTO, Vector<OrdenLugarDTO> ordenLugarDTOs,
			Vector<OrdenTecnicoDTO> ordenTecnicoDTOs, Vector<OrdenRadioDTO> ordenRadioDTOs, Vector<DocumentoDTO> documentoDTOs) throws SQLException {
		int res = -1;
		if(ordenDTO != null){	
			if(ordenDTO.getOrderId() > 0){
				if(getDatabase().openDatabase()){
					System.out.println("conexion abierta en " + this.getClass().getSimpleName() + " metodo updateOrder");
					String tabla = "ordenes";
					String sql = "UPDATE " + tabla							
							+ "	SET fecha = ?"
							+ ", noOficio = ?"
							+ ", fechaSalida = ?"
							+ ", fechaLlegada = ?"
							+ ", vehiculo_id = ?"
							+ ", tecnico_id = ?"
							+ ", tecnico_chofer_id = ?"
							+ ", reporte = ?"
							+ ", fechaSalidaR = ?"
							+ ", fechaLlegadaR = ?"
							+ ", noOficioLib = ?"
							+ ", sum_viatico = ?"
							+ ", pagado = ?"
							+ ", vehiculoR_id = ?"
							+ ", noTarjeta = ?"
							+ ", status = ?"
							+ " WHERE orden_id = ?;";
					java.sql.PreparedStatement statement = null;
					try {
						statement = getDatabase().getCon().prepareStatement(sql);
						statement.setDate(1, ordenDTO.getFecha().getFechaDateSQL());
						statement.setString(2, ordenDTO.getNoOficio());
						statement.setDate(3, ordenDTO.getFechaSalida().getFechaDateSQL());
						statement.setDate(4, ordenDTO.getFechaLlegada().getFechaDateSQL());
						statement.setInt(5, ordenDTO.getVehiculoDTO().getVehiculoId());
						statement.setInt(6, ordenDTO.getTecnicoDTO().getTecnicoId());
						statement.setInt(7, ordenDTO.getTecnicoChoferDTO().getTecnicoId());
						statement.setString(8, ordenDTO.getReporte());
						statement.setDate(9, ordenDTO.getFechaSalida().getFechaDateSQL());//solo se llenan pero son datos repetidos
						statement.setDate(10, ordenDTO.getFechaLlegada().getFechaDateSQL());//solo se llenan pero son datos repetidos
						statement.setString(11, ordenDTO.getNoOficio());//solo se llenan pero son datos repetidos
						statement.setDouble(12, ordenDTO.getSumViaticos());
						statement.setString(13, "N");
						statement.setInt(14, ordenDTO.getVehiculoDTO().getVehiculoId());//solo se llenan pero son datos repetidos
						statement.setString(15, ordenDTO.getNoOficio());//solo se llenan pero son datos repetidos
						statement.setInt(16, 1);
						statement.setInt(17, ordenDTO.getOrderId());
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					try {
						res = statement.executeUpdate();
					} catch (SQLException e1) {					
						e1.printStackTrace();
					}
					statement.clearParameters();
					statement.close();
					if(res > 0){
						res = ordenDTO.getOrderId();
						if(ordenLugarDTOs != null){
							if(ordenLugarDTOs.size() > 0){								
								String tablaOrdenesLugares = "ordeneslugares";
								/*java.sql.PreparedStatement statement2 = null;
								String consultaInicializaLugares = "UPDATE " + tablaOrdenesLugares 
										+ " SET " + tablaOrdenesLugares + ".status = 0 WHERE " + tablaOrdenesLugares + ".orden_id = " + ordenDTO.getOrderId();
								int resUP = getDatabase().executeNonQuery(consultaInicializaLugares);
								if(resUP > 0){
									String sql1 = "INSERT INTO "+tablaOrdenesLugares
											+ " ("
											+ "orden_id"
											+ ", lugar_id"
											+ ", actividad"
											+ ", status"
											+ ") "
											+ "VALUES"
											+ "("
											+ "?"
											+ ", ?"
											+ ", ?"
											+ ", ?"
											+ ")"
											+ " ON DUPLICATE KEY UPDATE lugar_id = ?, actividad = ?, status = ?";
									statement2 = getDatabase().getCon().prepareStatement(sql1);
									 Properties props = new Properties();
									 props.put("useServerPrepStmts", "false"); // use client-side prepared statement
									 props.put("useUnicode", "true");
									 props.put("characterEncoding", "UTF-8"); // ensure charset is utf8 here
									getDatabase().getCon().setClientInfo(props);
									getDatabase().getCon().setAutoCommit(false);
									for(OrdenLugarDTO ordenLugarDTO : ordenLugarDTOs){
										ordenLugarDTO.setOrdenDTO(ordenDTO);
										statement2.setInt(1, ordenLugarDTO.getOrdenDTO().getOrderId());
										statement2.setInt(2, ordenLugarDTO.getLugarDTO().getLugarId());
										statement2.setString(3, ordenLugarDTO.getActividad());
										statement2.setBoolean(4, true);
										statement2.setInt(5, ordenLugarDTO.getLugarDTO().getLugarId());
										statement2.setString(6, ordenLugarDTO.getActividad());
										statement2.setBoolean(7, true);									
										statement2.addBatch();						
									}
									statement2.executeBatch();
									getDatabase().getCon().commit();
									statement2.clearParameters();
									statement2.close();
								else{
									System.out.println("Fallo en la consulta updte para reiniciar los lugares!");
								}*/
								int resDel = borrarElementosRelacionadosConOrdenPorTabla(tablaOrdenesLugares, ordenDTO.getOrderId());
								if(resDel > 0){
									System.out.println("relacion con " +tablaOrdenesLugares + " borrada!");
									
								}else{
									System.out.println("relacion con " +tablaOrdenesLugares + " no borrada!");
								}
								int resUpPlaces = insertarOrdenesPlaces(ordenLugarDTOs, ordenDTO, tablaOrdenesLugares);
								if(resUpPlaces > 0){
									System.out.println("lugares insertados como update!");
								}else{
									System.out.println("lugares no insertados como update!");
								}
							}else{
								System.out.println("sin ordenes lugares por insertar!");
							}
						}else{
							System.out.println("ordenes lugares == null!");
						}
						if(ordenTecnicoDTOs != null){
							System.out.println("null 1");
							if(ordenTecnicoDTOs.size() > 0){
								String tablaOrdenesTecnicos = "ordenestecnicos";
								/*java.sql.PreparedStatement statement3 = null;
								String sql2 = "INSERT INTO  " + tablaOrdenesTecnicos
										+" ("
										+ "orden_id"
										+ ", tecnico_id"
										+ ", viatico"
										+ ")"
										+ " VALUES"
										+ "("
										+ "?"
										+ ", ?"
										+ ", ?"
										+ ")"
										+ " ON DUPLICATE KEY UPDATE tecnico_id = ?, viatico = ?";
								statement3 = getDatabase().getCon().prepareStatement(sql2);
								System.out.println("null 1.1");
								if(statement3 != null){
									getDatabase().getCon().setAutoCommit(false);
									System.out.println("null 1.2");
									for(OrdenTecnicoDTO ordenTecnicoDTO : ordenTecnicoDTOs){
										if(ordenTecnicoDTO != null){
											ordenTecnicoDTO.setOrdenDTO(ordenDTO);
											statement3.setInt(1, ordenTecnicoDTO.getOrdenDTO().getOrderId());
											statement3.setInt(2, ordenTecnicoDTO.getTecnicoDTO().getTecnicoId());						
											statement3.setDouble(3, 0.00);
											statement3.setInt(4, ordenTecnicoDTO.getTecnicoDTO().getTecnicoId());						
											statement3.setDouble(5, 0.00);
											statement3.addBatch();		
										}else{
											System.out.println("ordenTecnicoDTO == null");
										}
									}
									System.out.println("null 1.3");
									statement3.executeBatch();
									statement3.clearParameters();
									statement3.close();
									System.out.println("null 1.4");
									getDatabase().getCon().commit();
								}else{
									System.out.println("statement3 null");
								}*/
								//borra!
								int resDel = borrarElementosRelacionadosConOrdenPorTabla(tablaOrdenesTecnicos, ordenDTO.getOrderId());
								if(resDel > 0){
									System.out.println("relacion con " +tablaOrdenesTecnicos + " borrada!");									
								}else{
									System.out.println("relacion con " +tablaOrdenesTecnicos + " no borrada!");
								}
								
								int resUpTec = insertarOrdenesTecnicos(ordenTecnicoDTOs, ordenDTO, tablaOrdenesTecnicos);
								if(resUpTec > 0){
									System.out.println("tecnico insertado como update!");
								}else{
									System.out.println("tecnico no insertado como update");
								}
							}else{
								System.out.println("sin ordenes tecnicos por insertar!");
							}
						}else{
							System.out.println("sin ordenes tecnicos  == null!");
						}
						if(ordenRadioDTOs != null){
							System.out.println("null 2");
							if(ordenRadioDTOs.size() > 0){
								String tablaOrdenesRadios = "ordenesradios";
								/*java.sql.PreparedStatement statement4 = null;								
								String sql3 = "INSERT INTO  " + tablaOrdenesRadios
										+" ("
										+ "orden_id"
										+ ", radio_id"							
										+ ")"
										+ " VALUES"
										+ "("
										+ "?"
										+ ", ?"							
										+ ")"
										+ " ON DUPLICATE KEY UPDATE radio_id = ?;";
								statement4 = getDatabase().getCon().prepareStatement(sql3);
								getDatabase().getCon().setAutoCommit(false);
								for(OrdenRadioDTO ordenRadioDTO : ordenRadioDTOs){
									ordenRadioDTO.setOrdenDTO(ordenDTO);
									statement4.setInt(1, ordenRadioDTO.getOrdenDTO().getOrderId());
									statement4.setInt(2, ordenRadioDTO.getRadioDTO().getRadioId());	
									statement4.setInt(3, ordenRadioDTO.getRadioDTO().getRadioId());	
									statement4.addBatch();						
								}
								statement4.executeBatch();
								statement4.clearParameters();
								statement4.close();
								getDatabase().getCon().commit();*/
								//borra!
								int resDel = borrarElementosRelacionadosConOrdenPorTabla(tablaOrdenesRadios, ordenDTO.getOrderId());
								if(resDel > 0){
									System.out.println("relacion con " +tablaOrdenesRadios + " borrada!");									
								}else{
									System.out.println("relacion con " +tablaOrdenesRadios + " no borrada!");
								}
								int resUpRad = insertarOrdenesRadios(ordenRadioDTOs, ordenDTO, tablaOrdenesRadios);
								if(resUpRad > 0){
									System.out.println("radio insertado como update!");
								}else{
									System.out.println("radio no insertado como update!");
								}
								
							}else{
								System.out.println("sin ordenes tecnicos por insertar!");
							}
						}else{
							System.out.println("sin ordenes tecnicos == null!");
						}
						if(documentoDTOs != null){
							System.out.println("null 3");
							if(documentoDTOs.size() > 0){
								java.sql.PreparedStatement statement5 = null;
								String tablaDocumentos = "documentos";
								String sql4 = "INSERT INTO  " + tablaDocumentos
										+" ("
										+ "documento_id"
										+ ", orden_id"
										+ ", archivo"	
										+ ", fechacarga"	
										+ ", nombrearchivo"	
										+ ", descripcion"	
										+ ")"
										+ " VALUES"
										+ "("
										+ "?"
										+ ", ?"
										+ ", ?"
										+ ", ?"
										+ ", ?"
										+ ", ?"
										+ ")";
								//+ " ON DUPLICATE KEY UPDATE orden_id = ?, archivo = ?, fechacarga = ?, nombrearchivo = ?, descripcion = ?;";
								statement5 = getDatabase().getCon().prepareStatement(sql4);
								getDatabase().getCon().setAutoCommit(false);
								for(DocumentoDTO documento : documentoDTOs){
									documento.setOrdenDTO(ordenDTO);
									statement5.setInt(1, documento.getDocumentoId());
									statement5.setInt(2, documento.getOrdenDTO().getOrderId());		
									Blob blob = getDatabase().getCon().createBlob();
									blob.setBytes(1, documento.getArchivoEnByteArray());
									statement5.setBlob(3, blob);
									statement5.setDate(4, documento.getFechaHoraCargaDTO().getFechaHoraSql());
									statement5.setString(5, documento.getNombreArchivo());
									statement5.setString(6, documento.getDescripcion());
									/*statement5.setInt(7, documento.getOrdenDTO().getOrderId());		
									Blob blob2 = getDatabase().getCon().createBlob();
									blob.setBytes(1, documento.getArchivoEnByteArray());
									statement5.setBlob(8, blob2);
									statement5.setDate(9, documento.getFechaHoraCargaDTO().getFechaHoraSql());
									statement5.setString(10, documento.getNombreArchivo());
									statement5.setString(11, documento.getDescripcion());*/
									statement5.addBatch();						
								}
								statement5.executeBatch();
								statement5.clearParameters();
								statement5.close();
								getDatabase().getCon().commit();
							}else{
								System.out.println("sin documentoDTOs en ordenes por insertar!");
							}
						}else{
							System.out.println("sin documentoDTOs en ordenes == null!");
						}

					}else{
						System.out.println("ERROR ORDEN NO INSERTADA!");
					}

					// termian proceso de actualizacion o insercion
					if(getDatabase().closeDatabase()){
						System.out.println("conexion cerrada en " + this.getClass().getSimpleName() + " metodo actualizarDatosUsuario");
					}else{
						System.out.println("conexion no cerrada en " + this.getClass().getSimpleName() + " metodo actualizarDatosUsuario");
					}

				}else{
					System.out.println("conexion no abierta en " + this.getClass().getSimpleName() + " metodo actualizarDatosUsuario");
				}
			}else{
				System.out.println("Sin id de orden para las relaciones!");
			}
		}else{
			System.out.println("parametro de ordenDTO == nulo en OrdnesDAO");
		}
		return res;
		
	}
	
	public int borrarElementosRelacionadosConOrdenPorTabla(String tabla, int ordenId) throws SQLException{
		int res = -1;
		String queryDelete = "DELETE FROM " + tabla + " WHERE orden_id = " + ordenId;
		res = getDatabase().executeNonQuery(queryDelete);
		return res;
	}
	
	
}
