package dao.empleadoHorario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import dao.DatabaseGateway;
import dao.empleado.EmpleadoDAO;
import dao.horario.HorarioDAO;
import dto.documentos.DocumentoHorarioDTO;
import dto.empleado.EmpleadoDTO;
import dto.empleado.EmpleadoHorarioDTO;
import dto.horario.HorarioDTO;
import dto.user.UserDTO;
import herramientas.herrramientasrs.HerramientasResultSet;

public class EmpleadoHorarioDAO {
	private DatabaseGateway database = null;
	EmpleadoDAO empleadoDAO = null;
	HorarioDAO horarioDAO = null;
	HerramientasResultSet herramientasRS = null;
		
	/**
	 * Contruye esta instancia de clase
	 */
	public EmpleadoHorarioDAO(){
		if(this.database == null){
			this.database = new DatabaseGateway();
		}else{		
			System.out.println("DATABASE no es null en: " + this.getClass().getSimpleName());			
		}
		InitializeComponents();		
	}
	
	/**
	 * Construye esta instancia de clase tomando como paramentro la misma conexion del parametro
	 * @param database
	 */
	public EmpleadoHorarioDAO(DatabaseGateway database){	
		if(this.database == null){
			if(database != null){
				this.database = database;
			}else{
				this.database = new DatabaseGateway();
			}
		}else{
			System.out.println("DATABASE no es null en: " + this.getClass().getSimpleName());
		}		
		InitializeComponents();		
	}
	
	public void InitializeComponents(){		
		if(this.empleadoDAO == null){
			this.empleadoDAO = new EmpleadoDAO();
		}
		if(this.horarioDAO == null){
			this.horarioDAO = new HorarioDAO();
		}
		if(this.herramientasRS == null){
			this.herramientasRS = new HerramientasResultSet();
		}
	}
		
	
	public int insertHorarioExcel(Vector<EmpleadoHorarioDTO> v, int horarioId, DocumentoHorarioDTO doc, UserDTO user){
		int res = -1;
		if(database.openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			if(v != null && v.size() > 0){
				System.out.println("Tamano del vector: " + v.size());
				for (EmpleadoHorarioDTO empleadoHorarioDTO: v){
					empleadoHorarioDTO.setFechaValidez(doc.getFechaValidezDocumento());
					empleadoHorarioDTO.setAnioValidez(empleadoHorarioDTO.getFechaValidez().getYear());
					empleadoHorarioDTO.setMesValidez(empleadoHorarioDTO.getFechaValidez().getMonthValue());
					EmpleadoDTO empleado = empleadoHorarioDTO.getEmpleado();
					if(empleado.getNombreCompletoEmpleado() != null && !empleado.getNombreCompletoEmpleado().equals("") && empleado.getNombreCompletoEmpleado().length() > 0)
						empleado.setNombreCompletoEmpleado(empleado.getNombreCompletoEmpleado().trim());				
					String nombreEmpleadoExcel = empleado.getNombreCompletoEmpleado().toUpperCase().replace(".","");				
					ResultSet rs = null;
					ResultSet rs2 = null;
					String queryEmpleado = "SELECT id_empleado FROM tb_empleados "
							+ "WHERE "
							+ "tb_empleados.Sku_empleado = " + empleado.getSkuEmpleado();//							
					try {
						rs = database.executeQuery(queryEmpleado);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						if(rs != null){
							while(rs.next()){					
								try {
									empleado.setIdEmpleado(rs.getInt("tb_empleados.id_empleado"));									
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							if(empleado.getIdEmpleado() <= 0){
								String queryEmpleado2 = "SELECT id_empleado FROM tb_empleados "
										+ "WHERE "									
										+ "CONCAT(LTRIM(RTRIM(tb_empleados.Nombre)), ' ', LTRIM(RTRIM(tb_empleados.Ap_paterno)), ' ', LTRIM(RTRIM(tb_empleados.Ap_materno))) "
										+ "LIKE '%" + nombreEmpleadoExcel + "%';";
								try {
									rs2 = database.executeQuery(queryEmpleado2);
								} catch (SQLException e) {
									e.printStackTrace();
								}
								try {
									if(rs2 != null){
										while(rs.next()){
											try {
												empleado.setIdEmpleado(rs.getInt("tb_empleados.id_empleado"));									
											} catch (SQLException e) {
												e.printStackTrace();
											}
										}
									}									
								}catch (Exception e) {
									e.printStackTrace();
								}
							}
							try{
								rs.close();
								if(!rs.isClosed()){
									rs2.close();
								}
							}catch (Exception e) {
								e.printStackTrace();
							}	
						}else{
							System.out.println("rs == nullo en insertHorarioExcel empleado");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					//				empleado = empleadoHorarioDTO.getEmpleado();
					if(empleado.getIdEmpleado() > 0){
						System.out.println("Nombre empleado: " + empleado.getNombreCompletoEmpleado() + ", ID empleado: " + empleado.getIdEmpleado());
						if(empleadoHorarioDTO.getHorarios() != null && empleadoHorarioDTO.getHorarios().size() > 0){
							int cantidadHorarios = empleadoHorarioDTO.getHorarios().size()-1;
							/*for(HorarioDTO horario : empleadoHorarioDTO.getHorarios()){
								System.out.println(horario.getClaveHorario());
							}*/
							
							System.out.println("cantidad horario: " + cantidadHorarios);
							for(HorarioDTO horario : empleadoHorarioDTO.getHorarios()){
								if(horario.getClaveHorario() != null && !horario.getClaveHorario().equals("") && horario.getClaveHorario().length() > 0){
									if(horario.getClaveHorario().matches("-?\\d+(\\.\\d+)?")){
										double d = 0.0;
										try{
											d = Double.parseDouble(horario.getClaveHorario());
										}catch (NumberFormatException e) {
											e.printStackTrace();
										}
										int i = (int) d;					
										if(i > 0){
											horario.setClaveHorario(String.valueOf(i));
										}						
									}
									horario.setClaveHorario(horario.getClaveHorario().trim());		
									String queryHorario = "SELECT tb_horarios.id_horario FROM tb_horarios WHERE tb_horarios.Clave = '"+ horario.getClaveHorario() + "' "
											+ " AND tb_horarios.tb_tipos_estatus_id_tipo_estatus = 5;";
									try {
										rs = database.executeQuery(queryHorario);
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
											System.out.println("rs == nullo en insertHorarioExcel en claveHorario (HORARIO "+horario.getClaveHorario()+" INEXISTENTE)");
										}
									} catch (SQLException e) {									
										e.printStackTrace();
									}
									System.out.print(horario.getClaveHorario() + " : " + horario.getIdHorario() +'\t');
								}
							}//Termnia ciclo for de horarios
							System.out.println("");
							
							String querySelectHorarioEmpleado = "SELECT id_empleado_horario FROM tb_empleados_has_tb_horarios WHERE "
									+ "tb_empleados_has_tb_horarios.fk_id_empleado = "+ empleado.getIdEmpleado() + " AND fecha_validez = '"+empleadoHorarioDTO.getFechaValidez()+"' AND tb_empleados_has_tb_horarios.status_horario = 1;";
							System.out.println(querySelectHorarioEmpleado);
							try {
								rs = database.executeQuery(querySelectHorarioEmpleado);
							} catch (SQLException e) {							
								e.printStackTrace();
							}
							int idHorarioEmpleado = 0;
							if(rs != null){
								try {
									while(rs.next()){					
										try {
											idHorarioEmpleado = rs.getInt("tb_empleados_has_tb_horarios.id_empleado_horario");
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
								System.out.println("Empleado sin horario");
							}
							Calendar c =  Calendar.getInstance();
							String fecha = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
							String hora = new SimpleDateFormat("HH:mm:ss").format(c.getTime());
							if(idHorarioEmpleado > 0){								
								String queryUpdateHorarioEmpleado = "UPDATE tb_empleados_has_tb_horarios";
								queryUpdateHorarioEmpleado = armaQueryUpdateHorarioEmpleado(cantidadHorarios, queryUpdateHorarioEmpleado, empleadoHorarioDTO, fecha, hora, horarioId, idHorarioEmpleado);
								System.out.println(queryUpdateHorarioEmpleado);
								try {
									res = database.executeNonQuery(queryUpdateHorarioEmpleado);
								} catch (SQLException e) {								
									e.printStackTrace();
								}
								if(res > 0){
									//Se inserta el horario que sustituye al anterior o los anteriores.
									System.out.println("HORARIO ANTERIOR ACTUALIZADO");
									/*String queryInsertHorarioEmpleado = armaQueryInsertHorarioEmpleado(cantidadHorarios, horarioId, empleadoHorarioDTO, empleado, fecha, hora);
									
									try {
										res = -1;
										res = database.executeNonQuery(queryInsertHorarioEmpleado);
										if(res > 0){
											System.out.println("HORARIO INSERTADO");
										}else{
											System.out.println("NO HA SIDO POSBLE POSIBLE REALIZAR LA INSERCION DEL HORARIO AL ACTUALIZAR");
										}
									} catch (SQLException e) {								
										e.printStackTrace();
									}*/
								}else{
									System.out.println("NO HA SIDO POSBLE POSIBLE REALIZAR LA ACTUALIZACION DEL HORARIO");
								}
								
							}else{				
								//					INSERT PARA LOS HORARIOS DE LOS EMPLEADOS

								String queryInsertHorarioEmpleado = armaQueryInsertHorarioEmpleado(cantidadHorarios, horarioId, empleadoHorarioDTO, empleado, fecha, hora, user);

								System.out.println(queryInsertHorarioEmpleado);					
								try {
									res = -1;
									res = database.executeNonQuery(queryInsertHorarioEmpleado);
									if(res > 0){
										
									}else{
										System.out.println("NO HA SIDO POSBLE POSIBLE REALIZAR LA INSERCION DEL HORARIO");
									}
								} catch (SQLException e) {								
									e.printStackTrace();
								}
							}
						}else{
							System.out.println("No se pudo crear el horario del empleado: " + empleado.getNombreCompletoEmpleado() + " ya que no hay horarios por crear, a pesar de que el empleado existe");
						}
					}else{
						System.out.println("No se pudo crear el horario del empleado: " + empleado.getNombreCompletoEmpleado() + " ya que no existe en la BD!");
					}

				}//Termina ciclo for de empleados

			}else{
				System.out.println("Objeto de vector de empleados horarios DTO vacio!");
			}		
			if (database.closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return res; 
	}
	
	public int actualizarStatusEmpleadoHorario(){
//		UPDATE horarioEmpleado se desabilitan los anteriores
		/*String queryUpdateHorarioEmpleado = "UPDATE tb_empleados_has_tb_horarios";
		queryUpdateHorarioEmpleado += " SET tb_empleados_has_tb_horarios.status_horario = 0";
		queryUpdateHorarioEmpleado += ", fecha_actualizacion = '" + fecha + "'";
		queryUpdateHorarioEmpleado += ", hora_actualizacion = '" + hora + "'";
		queryUpdateHorarioEmpleado += " WHERE tb_empleados_has_tb_horarios.id_empleado_horario = " + idHorarioEmpleado 
				+ " AND tb_empleados_has_tb_horarios.fecha_validez = '"+empleadoHorarioDTO.getFechaValidez()+"';";			*/
		return 0;
	}
	
	/**
	 * 
	 * @param cantidadHorarios
	 * @param horarioId
	 * @param empleadoHorarioDTO
	 * @param empleado
	 * @param fecha
	 * @param hora
	 * @return
	 */
	private String armaQueryInsertHorarioEmpleado(int cantidadHorarios, int horarioId, EmpleadoHorarioDTO empleadoHorarioDTO, EmpleadoDTO empleado, String fecha, String hora, UserDTO user){
		String queryInsertHorarioEmpleado = "INSERT INTO tb_empleados_has_tb_horarios (";
//		CAMPOS
				queryInsertHorarioEmpleado += "fk_id_empleado, ";		
		for (int i = 0; i <= cantidadHorarios; i++){
			if(i < 9){							
				queryInsertHorarioEmpleado += "dia_0" + (i + 1) + ", ";							
			}else{
				if(i == cantidadHorarios){
					queryInsertHorarioEmpleado += "dia_" + (i + 1) + "";
				}else{
					queryInsertHorarioEmpleado += "dia_" + (i + 1) + ", ";
				}
			}
		}
		queryInsertHorarioEmpleado += ", fecha_creacion";
		queryInsertHorarioEmpleado += ", hora_creacion";
		queryInsertHorarioEmpleado += ", mes_validez";
		queryInsertHorarioEmpleado += ", anio_validez";
		queryInsertHorarioEmpleado += ", fecha_validez";
		if(horarioId > 0){
			queryInsertHorarioEmpleado += ", fk_documentos_empleados_horario";
		}
		queryInsertHorarioEmpleado += ", usuario_creacion";		
//		TERMINAN CAMPOS
		queryInsertHorarioEmpleado += ") VALUES(";
		//VALORES
		queryInsertHorarioEmpleado += empleado.getIdEmpleado() + ", ";

		for (int i = 0; i <= cantidadHorarios; i++){		
			if(i == cantidadHorarios){
				queryInsertHorarioEmpleado += empleadoHorarioDTO.getHorarios().get(i).getIdHorario() + "";
			}else{
				queryInsertHorarioEmpleado += empleadoHorarioDTO.getHorarios().get(i).getIdHorario() + ", ";
			}
		}
		queryInsertHorarioEmpleado += ", '" + fecha + "'";
		queryInsertHorarioEmpleado += ", '" + hora + "'";	
		queryInsertHorarioEmpleado += ", "+empleadoHorarioDTO.getMesValidez();
		queryInsertHorarioEmpleado += ", "+empleadoHorarioDTO.getAnioValidez();
		queryInsertHorarioEmpleado += ", '" + empleadoHorarioDTO.getFechaValidez()+"'";		
		if(horarioId > 0){
			queryInsertHorarioEmpleado += ", " + horarioId + "";
		}
		queryInsertHorarioEmpleado += ", "+ user.getUserId();
//		TERMINAN VALORES
		queryInsertHorarioEmpleado += ");";
		return queryInsertHorarioEmpleado;
	}
	
	public String armaQueryUpdateHorarioEmpleado(int cantidadHorarios, String queryUpdateHorarioEmpleado, EmpleadoHorarioDTO empleadoHorarioDTO, String fecha, String hora, int horarioId, int idHorarioEmpleado){		
		for (int i = 0; i <= cantidadHorarios; i++){								
			if(i < 9){
				if(i != 0){
					queryUpdateHorarioEmpleado += "dia_0" + (i + 1) + " = " + empleadoHorarioDTO.getHorarios().get(i).getIdHorario() + ", ";
				}else{
					queryUpdateHorarioEmpleado += " SET dia_0" + (i + 1) + " = " + empleadoHorarioDTO.getHorarios().get(i).getIdHorario() + ", ";												
				}
			}else{
				if(i == cantidadHorarios){
					queryUpdateHorarioEmpleado += "dia_" + (i + 1) + " = " + empleadoHorarioDTO.getHorarios().get(i).getIdHorario() + "";
				}else{
					queryUpdateHorarioEmpleado += "dia_" + (i + 1) + " = " + empleadoHorarioDTO.getHorarios().get(i).getIdHorario() + ", ";
				}
			}
		}
		queryUpdateHorarioEmpleado += ", fecha_actualizacion = '" + fecha + "'";
		queryUpdateHorarioEmpleado += ", hora_actualizacion = '" + hora + "'";
		if(horarioId > 0){
			queryUpdateHorarioEmpleado += ", fk_documentos_empleados_horario = " + horarioId + "";
		}
		queryUpdateHorarioEmpleado += " WHERE id_empleado_horario = " + idHorarioEmpleado + ";";
		//System.out.println(queryUpdateHorarioEmpleado);
		return queryUpdateHorarioEmpleado;
	}
	
	public HorarioDTO selectHorarioDTOEnDiaPorEmpleado(int dia, int mes, int anio, int id_empleado){
		HorarioDTO horario = null;
		if(dia > 0){
			if(database.openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				String campoDia = "";
				String campoMes = "";
				String campoAnio = "";
				if(dia > 9){
					campoDia = String.valueOf(dia);
				}else{
					campoDia = "0" + String.valueOf(dia);
				}
				if(mes > 9){
					campoMes = String.valueOf(mes);
				}else{
					campoMes = "0" + String.valueOf(mes);
				}
				if(anio > 9){
					campoAnio = String.valueOf(anio);
				}else{
					campoAnio = "0" + String.valueOf(anio);
				}
				ResultSet rs = null;
				String query = "SELECT"
						+ " tb_empleados_has_tb_horarios.dia_" +campoDia+ ","
						+ " tb_horarios.* "
						+ " FROM tb_empleados_has_tb_horarios"
						+ " LEFT JOIN tb_horarios ON tb_empleados_has_tb_horarios.dia_"+campoDia+" = tb_horarios.id_horario"
						+ " WHERE "
							+ "tb_empleados_has_tb_horarios.fk_id_empleado = " +id_empleado+" AND tb_empleados_has_tb_horarios.fecha_validez = '"+campoAnio+"-"+campoMes+"-01' AND tb_empleados_has_tb_horarios.status_horario = 1;";
				try {
					System.out.println("query: " + query);
					rs = database.executeQuery(query);
				} catch (SQLException e) {				
					e.printStackTrace();
				}
				if(rs != null){
					try {
						while(rs.next()){							
							try {
								horario = herramientasRS.inicializaHorarioSimpleDTO(rs);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectEmpleadoIdDia");
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("filtro dia nulo");
		}
		return horario;
	}
	
	/**
	 * 
	 * @param dia
	 * @param mes
	 * @param anio
	 * @param id_empleado
	 * @return
	 */
	public HorarioDTO selectHorarioDTOEnDiaPorEmpleadoConConexionAbierta(int dia, int mes, int anio, int id_empleado){
		HorarioDTO horario = null;
		if(dia > 0){
//			if(database.openDatabase()){
//				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				String campoDia = "";
				String campoMes = "";
				String campoAnio = "";
				if(dia > 9){
					campoDia = String.valueOf(dia);
				}else{
					campoDia = "0" + String.valueOf(dia);
				}
				if(mes > 9){
					campoMes = String.valueOf(mes);
				}else{
					campoMes = "0" + String.valueOf(mes);
				}
				if(anio > 9){
					campoAnio = String.valueOf(anio);
				}else{
					campoAnio = "0" + String.valueOf(anio);
				}
				ResultSet rs = null;
				String query = "SELECT"
						+ " tb_empleados_has_tb_horarios.dia_" +campoDia+ ","
						+ " tb_horarios.* "
						+ " FROM tb_empleados_has_tb_horarios"
						+ " LEFT JOIN tb_horarios ON tb_empleados_has_tb_horarios.dia_"+campoDia+" = tb_horarios.id_horario"
						+ " WHERE "
							+ "tb_empleados_has_tb_horarios.fk_id_empleado = " +id_empleado+" AND tb_empleados_has_tb_horarios.fecha_validez = '"+campoAnio+"-"+campoMes+"-01' AND tb_empleados_has_tb_horarios.status_horario = 1;";
				try {
//					System.out.println("query: " + query);
					rs = database.executeQuery(query);
				} catch (SQLException e) {				
					e.printStackTrace();
				}
				if(rs != null){
					try {
						while(rs.next()){							
							try {
								horario = herramientasRS.inicializaHorarioSimpleDTO(rs);
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectEmpleadoIdDia");
				}
			/*}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}*/
		}else{
			System.out.println("filtro dia nulo");
		}
		return horario;
	}
	
	public Vector<EmpleadoHorarioDTO> selectEmpleadosHorariosDTO(){
		Vector<EmpleadoHorarioDTO> empleadosHorarios = null;		
		if(database.openDatabase()){
			System.out.println("Conexion abierta en " + this.getClass().getSimpleName());
			LocalDate localDate = LocalDate.now();
//			int dia = localDate.getDayOfMonth();
			int mes = localDate.getMonthValue();
			int anio = localDate.getYear();			
			ResultSet rs = null;			
					String query  = armaQueryHorariosEmpleados() + "WHERE tb_empleados_has_tb_horarios.status_horario = 1 "
							+ "AND tb_empleados_has_tb_horarios.anio_validez = "+anio+" "
									+ "AND tb_empleados_has_tb_horarios.mes_validez = "+mes+" "
											+ "ORDER BY nombre_empleado";
			try {
//				System.out.println(query);
				rs = database.executeQuery(query);
				empleadosHorarios = new Vector<EmpleadoHorarioDTO>();
				while (rs.next()) {
					EmpleadoHorarioDTO empleadoHorario = herramientasRS.inicializaEmpleadoHorarioDTO(rs);
					if(empleadoHorario != null){
						empleadosHorarios.add(empleadoHorario);
					}else{
						System.out.println("empleadoHorario == NULL");
					}
					
				}
				
				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			if(database.closeDatabase()){
				System.out.println("Conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("Conexion NO cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("Conexion NO abierta en " + this.getClass().getSimpleName());
		}
		return empleadosHorarios;
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector<EmpleadoHorarioDTO> selectEmpleadosHorariosDTO(HashMap<String, String> map){
		Vector<EmpleadoHorarioDTO> empleadosHorarios = null;		
		if(database.openDatabase()){
			System.out.println("Conexion abierta en " + this.getClass().getSimpleName());
			LocalDate localDate = null;
			if(!HerramientasResultSet.valueToStringOrEmpty(map, "fechaDe").isEmpty()){
				Locale local = new Locale("es", "MX");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy").withLocale(local);
				YearMonth ym = YearMonth.parse(map.get("fechaDe"), formatter);
				localDate = ym.atDay(1);
//				localDate = LocalDate.parse(map.get("fechaDe"));
			}else{
				localDate = LocalDate.now();
			}
			
//			int dia = localDate.getDayOfMonth();
			int mes = localDate.getMonthValue();
			int anio = localDate.getYear();			
			ResultSet rs = null;			
					String query  = armaQueryHorariosEmpleados();
						/*if(!HerramientasResultSet.valueToStringOrEmpty(map, "idEmpleado").isEmpty() || !HerramientasResultSet.valueToStringOrEmpty(map, "nombreEmpleado").isEmpty()){
							query += "LEFT JOIN tb_empleados ON tb_empleados_has_tb_horarios.fk_id_empleado = tb_empleados.id_empleado ";
						}*/
							query+=  "WHERE tb_empleados_has_tb_horarios.status_horario = 1 "
							+ "AND tb_empleados_has_tb_horarios.anio_validez = "+anio+" "
							+ "AND tb_empleados_has_tb_horarios.mes_validez = "+mes+" ";
						if(!HerramientasResultSet.valueToStringOrEmpty(map, "idEmpleado").isEmpty()){		
							query += "AND tb_empleados.Sku_empleado = '"+map.get("idEmpleado")+"'";
						}
						if(!HerramientasResultSet.valueToStringOrEmpty(map, "nombreEmpleado").isEmpty()){
							query += "AND CONCAT(tb_empleados.Nombre, ' ', tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno) LIKE '%"+map.get("nombreEmpleado")+"%'";
						}
							query += " ORDER BY nombre_empleado";
			try {
//				System.out.println(query);
				rs = database.executeQuery(query);
				empleadosHorarios = new Vector<EmpleadoHorarioDTO>();
				while (rs.next()) {
					EmpleadoHorarioDTO empleadoHorario = herramientasRS.inicializaEmpleadoHorarioDTO(rs);
					if(empleadoHorario != null){
						empleadosHorarios.add(empleadoHorario);
					}else{
						System.out.println("empleadoHorario == NULL");
					}
					
				}
				
				
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			if(database.closeDatabase()){
				System.out.println("Conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("Conexion NO cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("Conexion NO abierta en " + this.getClass().getSimpleName());
		}
		return empleadosHorarios;
	}
	
	/**
	 * 
	 * @return El query de consulta de listados horarios empleados
	 */
	private String armaQueryHorariosEmpleados(){
		String query = "SELECT tb_empleados_has_tb_horarios.*,\r\n" + 
				" tb_cve_dia_01.Clave AS cve_dia_01,\r\n" +
				" tb_cve_dia_01.color_hexadecimal AS color_dia_01,\r\n" +
				" tb_cve_dia_02.Clave AS cve_dia_02,\r\n" + 
				" tb_cve_dia_02.color_hexadecimal AS color_dia_02,\r\n" +
				" tb_cve_dia_03.Clave AS cve_dia_03,\r\n" +
				" tb_cve_dia_03.color_hexadecimal AS color_dia_03,\r\n" +
				" tb_cve_dia_04.Clave AS cve_dia_04,\r\n" + 
				" tb_cve_dia_04.color_hexadecimal AS color_dia_04,\r\n" +
				" tb_cve_dia_05.Clave AS cve_dia_05,\r\n" +
				" tb_cve_dia_05.color_hexadecimal AS color_dia_05,\r\n" +
				" tb_cve_dia_06.Clave AS cve_dia_06,\r\n" +
				" tb_cve_dia_06.color_hexadecimal AS color_dia_06,\r\n" +
				" tb_cve_dia_07.Clave AS cve_dia_07,\r\n" +
				" tb_cve_dia_07.color_hexadecimal AS color_dia_07,\r\n" +
				" tb_cve_dia_08.Clave AS cve_dia_08,\r\n" +
				" tb_cve_dia_08.color_hexadecimal AS color_dia_08,\r\n" +
				" tb_cve_dia_09.Clave AS cve_dia_09,\r\n" +
				" tb_cve_dia_09.color_hexadecimal AS color_dia_09,\r\n" +
				" tb_cve_dia_10.Clave AS cve_dia_10,\r\n" +
				" tb_cve_dia_10.color_hexadecimal AS color_dia_10,\r\n" +
				" tb_cve_dia_11.Clave AS cve_dia_11,\r\n" +
				" tb_cve_dia_11.color_hexadecimal AS color_dia_11,\r\n" +
				" tb_cve_dia_12.Clave AS cve_dia_12,\r\n" +
				" tb_cve_dia_12.color_hexadecimal AS color_dia_12,\r\n" +
				" tb_cve_dia_13.Clave AS cve_dia_13,\r\n" +
				" tb_cve_dia_13.color_hexadecimal AS color_dia_13,\r\n" +
				" tb_cve_dia_14.Clave AS cve_dia_14,\r\n" +
				" tb_cve_dia_14.color_hexadecimal AS color_dia_14,\r\n" +
				" tb_cve_dia_15.Clave AS cve_dia_15,\r\n" + 
				" tb_cve_dia_15.color_hexadecimal AS color_dia_15,\r\n" +
				" tb_cve_dia_16.Clave AS cve_dia_16,\r\n" +
				" tb_cve_dia_16.color_hexadecimal AS color_dia_16,\r\n" +
				" tb_cve_dia_17.Clave AS cve_dia_17,\r\n" +
				" tb_cve_dia_17.color_hexadecimal AS color_dia_17,\r\n" +
				" tb_cve_dia_18.Clave AS cve_dia_18,\r\n" +
				" tb_cve_dia_18.color_hexadecimal AS color_dia_18,\r\n" +
				" tb_cve_dia_19.Clave AS cve_dia_19,\r\n" + 
				" tb_cve_dia_19.color_hexadecimal AS color_dia_19,\r\n" +
				" tb_cve_dia_20.Clave AS cve_dia_20,\r\n" +
				" tb_cve_dia_20.color_hexadecimal AS color_dia_20,\r\n" +
				" tb_cve_dia_21.Clave AS cve_dia_21,\r\n" +
				" tb_cve_dia_21.color_hexadecimal AS color_dia_21,\r\n" +
				" tb_cve_dia_22.Clave AS cve_dia_22,\r\n" +
				" tb_cve_dia_22.color_hexadecimal AS color_dia_22,\r\n" +
				" tb_cve_dia_23.Clave AS cve_dia_23,\r\n" +
				" tb_cve_dia_23.color_hexadecimal AS color_dia_23,\r\n" +
				" tb_cve_dia_24.Clave AS cve_dia_24,\r\n" +
				" tb_cve_dia_24.color_hexadecimal AS color_dia_24,\r\n" +
				" tb_cve_dia_25.Clave AS cve_dia_25,\r\n" +
				" tb_cve_dia_25.color_hexadecimal AS color_dia_25,\r\n" +
				" tb_cve_dia_26.Clave AS cve_dia_26,\r\n" +
				" tb_cve_dia_26.color_hexadecimal AS color_dia_26,\r\n" +
				" tb_cve_dia_27.Clave AS cve_dia_27,\r\n" +
				" tb_cve_dia_27.color_hexadecimal AS color_dia_27,\r\n" +
				" tb_cve_dia_28.Clave AS cve_dia_28,\r\n" +
				" tb_cve_dia_28.color_hexadecimal AS color_dia_28,\r\n" +
				" tb_cve_dia_29.Clave AS cve_dia_29,\r\n" +
				" tb_cve_dia_29.color_hexadecimal AS color_dia_29,\r\n" +
				" tb_cve_dia_30.Clave AS cve_dia_30,\r\n" +
				" tb_cve_dia_30.color_hexadecimal AS color_dia_30,\r\n" +
				" tb_cve_dia_31.Clave AS cve_dia_31,\r\n" +
				" tb_cve_dia_31.color_hexadecimal AS color_dia_31,\r\n" +
				" tb_empleados.id_empleado, tb_empleados.Sku_empleado, tb_empleados.Nombre, tb_empleados.Ap_paterno, tb_empleados.Ap_materno, CONCAT(tb_empleados.Nombre, \" \", tb_empleados.Ap_paterno, \" \", tb_empleados.Ap_materno)  AS nombre_empleado " +
				"FROM tb_empleados_has_tb_horarios " +
				"LEFT JOIN tb_empleados ON tb_empleados_has_tb_horarios.fk_id_empleado = tb_empleados.id_empleado\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_01 ON tb_empleados_has_tb_horarios.dia_01 = tb_cve_dia_01.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_02 ON tb_empleados_has_tb_horarios.dia_02 = tb_cve_dia_02.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_03 ON tb_empleados_has_tb_horarios.dia_03 = tb_cve_dia_03.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_04 ON tb_empleados_has_tb_horarios.dia_04 = tb_cve_dia_04.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_05 ON tb_empleados_has_tb_horarios.dia_05 = tb_cve_dia_05.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_06 ON tb_empleados_has_tb_horarios.dia_06 = tb_cve_dia_06.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_07 ON tb_empleados_has_tb_horarios.dia_07 = tb_cve_dia_07.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_08 ON tb_empleados_has_tb_horarios.dia_08 = tb_cve_dia_08.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_09 ON tb_empleados_has_tb_horarios.dia_09 = tb_cve_dia_09.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_10 ON tb_empleados_has_tb_horarios.dia_10 = tb_cve_dia_10.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_11 ON tb_empleados_has_tb_horarios.dia_11 = tb_cve_dia_11.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_12 ON tb_empleados_has_tb_horarios.dia_12 = tb_cve_dia_12.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_13 ON tb_empleados_has_tb_horarios.dia_13 = tb_cve_dia_13.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_14 ON tb_empleados_has_tb_horarios.dia_14 = tb_cve_dia_14.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_15 ON tb_empleados_has_tb_horarios.dia_15 = tb_cve_dia_15.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_16 ON tb_empleados_has_tb_horarios.dia_16 = tb_cve_dia_16.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_17 ON tb_empleados_has_tb_horarios.dia_17 = tb_cve_dia_17.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_18 ON tb_empleados_has_tb_horarios.dia_18 = tb_cve_dia_18.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_19 ON tb_empleados_has_tb_horarios.dia_19 = tb_cve_dia_19.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_20 ON tb_empleados_has_tb_horarios.dia_20 = tb_cve_dia_20.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_21 ON tb_empleados_has_tb_horarios.dia_21 = tb_cve_dia_21.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_22 ON tb_empleados_has_tb_horarios.dia_22 = tb_cve_dia_22.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_23 ON tb_empleados_has_tb_horarios.dia_23 = tb_cve_dia_23.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_24 ON tb_empleados_has_tb_horarios.dia_24 = tb_cve_dia_24.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_25 ON tb_empleados_has_tb_horarios.dia_25 = tb_cve_dia_25.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_26 ON tb_empleados_has_tb_horarios.dia_26 = tb_cve_dia_26.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_27 ON tb_empleados_has_tb_horarios.dia_27 = tb_cve_dia_27.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_28 ON tb_empleados_has_tb_horarios.dia_28 = tb_cve_dia_28.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_29 ON tb_empleados_has_tb_horarios.dia_29 = tb_cve_dia_29.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_30 ON tb_empleados_has_tb_horarios.dia_30 = tb_cve_dia_30.id_horario\r\n" + 
				"LEFT JOIN tb_horarios AS tb_cve_dia_31 ON tb_empleados_has_tb_horarios.dia_31 = tb_cve_dia_31.id_horario\r\n";
		return query;
				
	}
	
	
	
	
}//Termina clase
