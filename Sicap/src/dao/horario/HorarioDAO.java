package dao.horario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import dao.DatabaseGateway;
import dto.horario.HorarioDTO;
import herramientas.herrramientasrs.HerramientasResultSet;

public class HorarioDAO {
	
	DatabaseGateway database;	
	private HerramientasResultSet herramientasRS = null;
	
	
	public HorarioDAO(){	
		if(database == null){			
			this.database = new DatabaseGateway();
		}		
		InitializeComponents();		
	}
	
	public void InitializeComponents(){
		if(this.herramientasRS == null){
			this.herramientasRS = new HerramientasResultSet();
		}
	}
	
	public HorarioDTO selectHorarioPorClaveHorario(String claveHorario){
		HorarioDTO horario = null;
		if(claveHorario != null && !claveHorario.equals("") && claveHorario.length() > 0){
			if(database.openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}

			ResultSet rs = null;
			String query = "SELECT * FROM tb_horarios WHERE Clave = '"+ claveHorario + "';";
			try {
				rs = database.executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(rs != null){
					while(rs.next()){
						horario = new HorarioDTO();
						try {
							horario.setIdHorario(rs.getInt("tb_horarios.id_horario"));
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectHorarioPorClaveHorario");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (database.closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("claveHorario es nulo");
		}
		return horario;
	}
	
	/**
	 * Seleclciona el ultimo sku para calcular uno nuevo
	 * @param prefix
	 * @return String ultimo SKU nuevo horario
	 */
	public String selectSkuNuevoHorario(String prefix){
		String skuNuevoHorario = null;
		if(prefix != null && !prefix.equals("") && prefix.length() > 0){
			if(database.openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				ResultSet rs = null;
				String query = "SELECT MAX(tb_horarios.sku) AS ultimo_sku FROM tb_horarios WHERE tb_horarios.prefix_sku LIKE '"+prefix+"%' ";
				try {
					rs = database.executeQuery(query);
				} catch (SQLException e) {				
					e.printStackTrace();
				}
				try {
					if(rs != null){
					while(rs.next()){					
						try {
							int sku = rs.getInt("ultimo_sku");
							skuNuevoHorario = prefix + (sku+1);
						} catch (SQLException e) {						
							e.printStackTrace();
						}
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectSkuNuevoHorario");
				}
				} catch (SQLException e) {			
					e.printStackTrace();
				}
				if (database.closeDatabase()){
					System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
				}else{
					System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("no se ha definido un prefix para la consulta dle sku del horario en " + this.getClass().getSimpleName() + ": selectSkuNuevoHorario");
		}	
		return skuNuevoHorario;
	}
	
	
	public Vector<HorarioDTO> selectHorariosTodos(){
		Vector<HorarioDTO> vHorarios = new Vector<HorarioDTO>();
		if(database.openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_horarios"
					+ " LEFT JOIN tb_tipos_estatus ON tb_horarios.tb_tipos_estatus_id_tipo_estatus = tb_tipos_estatus.id_tipo_estatus"
					+ " LEFT JOIN tb_horarios_configuracion ON tb_horarios.fk_configuracion_horario = tb_horarios_configuracion.id_configuracion_horario;";
			try {
				rs = database.executeQuery(query);
				if(rs != null){
					while(rs.next()){
						HorarioDTO horario = herramientasRS.inicializaHorarioDTO(rs);						
						if(horario != null){

							vHorarios.add(horario);
						}else{
							System.out.println("horario en selectHorariosTodos nullo");
						}
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					System.out.println("rs es nullo en selectHorariosTodos");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (database.closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return vHorarios;
	}

	
	public Vector<HorarioDTO> selectRegistrosHorariosFiltros(HashMap<String, String> map){	
		Vector<HorarioDTO> listaHorarios = new Vector<HorarioDTO>();
		if(database.openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT * FROM tb_horarios "
					+ " LEFT JOIN tb_tipos_estatus ON tb_horarios.tb_tipos_estatus_id_tipo_estatus = tb_tipos_estatus.id_tipo_estatus"
					+ " LEFT JOIN tb_horarios_configuracion ON tb_horarios.fk_configuracion_horario = tb_horarios_configuracion.id_configuracion_horario"
					+ " ";
			if(!HerramientasResultSet.valueToStringOrEmpty(map, "claveHorario").equals("") ){
				query += "	WHERE tb_horarios.Clave LIKE '%" + (String) map.get("claveHorario") + "%'";
			}else{
				System.out.println("claveHorario es vacia");
			}
			try {
				rs = database.executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(rs != null){
				try {
					while(rs.next()){
						try {
							HorarioDTO horario = herramientasRS.inicializaHorarioDTO(rs);
							if (horario != null){
								listaHorarios.add(horario);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("rs == nullo en selectRegistrosHorariosFiltros");
			}
			database.closeDatabase();	
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return listaHorarios;
	}
	

	public HorarioDTO selectHorarioPorClaveHorario(HorarioDTO horario){	
		if(horario != null){
			if(horario.getClaveHorario() != null && !horario.getClaveHorario().equals("") && horario.getClaveHorario().length() > 0){
				if(database.openDatabase()){
					System.out.println("conexion abierta en " + this.getClass().getSimpleName());
					ResultSet rs = null;
					String query = "SELECT * FROM tb_horarios WHERE Clave = '"+ horario.getClaveHorario() + "';";
					try {
						rs = database.executeQuery(query);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						if(rs != null){
							while(rs.next()){					
								try {
									horario.setIdHorario(rs.getInt("tb_horarios.id_horario"));
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							try{
								rs.close();
							}catch (Exception e) {
								e.printStackTrace();
							}	
						}else{
							System.out.println("rs == nullo en selectHorarioPorClaveHorario");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if (database.closeDatabase()){
						System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
					}else{
						System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
					}
				}else{
					System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
				}
			}else{
				System.out.println("claveHorario es nulo");
			}
		}else{
			System.out.println("horario es nulo");
		}
		return horario;
	}
	
	public Vector<HorarioDTO> selectIdHorariosPorClaveHorario(Vector<HorarioDTO> horarios){	
		if(horarios != null){
			Iterator<HorarioDTO> i = horarios.iterator();
			if(database.openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				while(i.hasNext()){
					HorarioDTO horario = i.next();
					if(horario.getClaveHorario() != null && !horario.getClaveHorario().equals("") && horario.getClaveHorario().length() > 0){
						ResultSet rs = null;
						String query = "SELECT id_horario FROM tb_horarios WHERE Clave = '"+ horario.getClaveHorario() + "';";
						try {
							rs = database.executeQuery(query);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						try {
							if(rs != null){
								while(rs.next()){					
									try {
										horario.setIdHorario(rs.getInt("tb_horarios.id_horario"));
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								try{
									rs.close();
								}catch (Exception e) {
									e.printStackTrace();
								}	
							}else{
								System.out.println("rs == nullo en selectIdHorariosPorClaveHorario");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}					
					}else{
						System.out.println("claveHorario es nulo");
					}
				}//Termina ciclo while
				if (database.closeDatabase()){
					System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
				}else{
					System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("horarios es nulo");
		}
		return horarios;
	}
	
	/**
	 * INSERTA EL HORARIO DE LA TALBA HORARIOS [JAVIER] 20170306
	 * @param horario
	 * @return int
	 */
	public int insertaNuevoHorario(HorarioDTO horario){		
		int res = -1;
		if(database.openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "tb_horarios";
			String query= "INSERT INTO tb_horarios "
							+ " ("+tabla+".prefix_sku, "
							+ tabla + ".sku, " 
							+ tabla + ".Clave, "
							+ tabla + ".Nombre_horario, "
							+ tabla + ".fecha_creacion, "
							+ tabla + ".hora_creacion, "							
							+ tabla + ".fk_id_usuario_creacion, "
							+ tabla + ".fecha_actualizacion, ";
			if(!horario.isHorarioNoLaboral()){
							query += tabla + ".Hora_entrada, "
							+ tabla + ".Hora_salida, "
							+ tabla + ".Hora_retardo, "
							+ tabla + ".Hora_entrada2, "
							+ tabla + ".Hora_salida2, "
							+ tabla + ".Hora_retardo2, ";
							
			}
					query += tabla + ".tb_tipos_estatus_id_tipo_estatus, "
							+ tabla + ".no_laboral)"
						+ " VALUES("
							+ "'"+ horario.getPrefix() + "', "
							+ horario.getSkuHorario() + ", '"
							+ horario.getClaveHorario() + "', '"
							+ horario.getNombreHorario() + "', "
							+ "'" + horario.getFechaHoraCreacion().getFecha().getFechaLD() + "', "
							+ "'" + horario.getFechaHoraCreacion().getHora().getHoraLT() + "', "
							+ horario.getUsuarioCreacion().getIdUser() + ", "
							+ "'" + horario.getFechaHoraActualizacion().getFecha().getFechaLD() + "', ";					
							if(!horario.isHorarioNoLaboral()){
								query += "'"+horario.getHoraEntrada().getHoraString() + "', '"
								+ horario.getHoraSalidaString() + "', '"
								+ horario.getHoraRetardoString() + "', '"
								+ horario.getHoraEntrada2String() + "', '"
								+ horario.getHoraSalida2String() + "', '"
								+ horario.getHoraRetardo2String() + "', "
								+ horario.getHorarioQuebradoEstatusDTO().getEstatusInt() + ", ";
							}
							query += horario.getTipoEstatusDTO().getEstatusInt() +", "
							+ (horario.isHorarioNoLaboral() ? "1": "0")
					+");";
			try {
				System.out.println(query);
				res = database.executeNonQuery(query);				
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
		return res;
	}
	
	/**
	 * ACTUALIZA EL HORARIO DE LA TALBA HORARIOS EN BASE A SU ID [JAVIER] 20170306
	 * @param horario
	 * @return int
	 */
	public int actualizarHorario(HorarioDTO horario){		
		int res = -1;
		if(database.openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "tb_horarios";
			String query= "UPDATE tb_horarios SET "
					+ ""+tabla+".Clave = '"+horario.getClaveHorario()+ "', "
					+tabla+".Nombre_horario = '"+horario.getNombreHorario()+ "', "
					+ tabla + ".fecha_actualizacion = '" + horario.getFechaHoraActualizacion().getFecha().getFechaLD() + "', "
					+ tabla + ".hora_actualizacion = '" +horario.getFechaHoraActualizacion().getHora().getHoraLT() + "', "
					+ tabla + ".fk_id_usuario_actualizacion = " + horario.getUsuarioActualizacion().getIdUser() + ", ";
			
			if(!horario.isHorarioNoLaboral()){
				query += tabla+".no_laboral = 0, "
						+tabla+".Hora_entrada = '"+horario.getHoraEntrada().getHoraString() + "', "
						+tabla+".Hora_salida = '"+horario.getHoraSalidaString()+ "', "
						+tabla+".Hora_retardo = '"+horario.getHoraRetardoString()+ "', ";
				if(horario.isHorarioQuebrado()){
					query += tabla+".Hora_entrada2 = '"+horario.getHoraEntrada2String()+ "', "
							+tabla+".Hora_salida2 = '"+horario.getHoraSalida2String()+ "', "
							+tabla+".h_quebrado = "+ horario.getHorarioQuebradoEstatusDTO().getEstatusInt() + ", ";
				}else{
					query += tabla+".Hora_entrada2 = '00:00:00', "
							+tabla+".Hora_salida2 = '00:00:00', "
							+tabla+".h_quebrado = "+ horario.getHorarioQuebradoEstatusDTO().getEstatusInt() + ", ";
				}
			}else{
				query += tabla+".no_laboral = 1, "
						+tabla+".Hora_entrada = '00:00:00', "
						+tabla+".Hora_salida = '00:00:00', "
						+tabla+".Hora_retardo = '00:00:00', "	
						+ tabla+".Hora_entrada2 = '00:00:00', "
						+tabla+".Hora_salida2 = '00:00:00', "
						+tabla+".h_quebrado = "+ horario.getHorarioQuebradoEstatusDTO().getEstatusInt()+ ", ";
			}
			
			query += tabla+".tb_tipos_estatus_id_tipo_estatus = "+ horario.getTipoEstatusDTO().getEstatusInt();
			query += " WHERE " +tabla+".id_horario = " + horario.getIdHorario();
			try {
				System.out.println(query);
				res = database.executeNonQuery(query);				
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
		return res;
	}
	
}
