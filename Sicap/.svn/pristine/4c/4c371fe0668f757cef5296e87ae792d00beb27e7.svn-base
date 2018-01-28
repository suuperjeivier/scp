package dao.bitacora;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

import dao.DatabaseGateway;
import dao.empleado.EmpleadoDAO;
import dao.empleadoHorario.EmpleadoHorarioDAO;
import dao.listados.ListadosDAO;
import dto.bitacora.BitacoraDTO;
import dto.bitacora.BitacoraEmpleadoDTO;
import dto.bitacora.BitacoraFechaDTO;
import dto.bitacora.BitacoraProcesarDTO;
import dto.bitacora.PermisoDTO;
import dto.documentos.DocumentoHorarioDTO;
import dto.empleado.EmpleadoDTO;
import dto.empleado.EmpleadoHorarioDTO;
import dto.horario.HorarioDTO;
import dto.listados.TipoEstatusDTO;
import dto.user.UserDTO;
import herramientas.FechaDTO;
import herramientas.HoraDTO;
import herramientas.Operaciones;
import herramientas.herrramientasrs.HerramientasResultSet;

public class BitacoraDAO {
	
	private DatabaseGateway database = null;
	private HerramientasResultSet herramientasRS;
	
	public BitacoraDAO(){
		if(this.getDatabase() == null){
			this.setDatabase(new DatabaseGateway());
		}
		InitializeComponents();		
	}
	
	public BitacoraDAO(DatabaseGateway database){
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
		if(this.getHerramientasRS() == null)
			this.setHerramientasRS(new HerramientasResultSet());
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
	
	/**
	 * INSERTA UN NUEVO REGISRO DE ASISTENCIA [Javier]20170705
	 * @param permisoNuevo
	 * @return int
	 */
	public int inserAttendanceRecord(BitacoraDTO attendanceRecord){		
		int res = -1;
		FechaDTO fechaInsercion = new FechaDTO();
		HoraDTO horaInsercion = new HoraDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter = formatter.withLocale( Locale.US );// Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
    	LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));	
    	fechaInsercion.setFechaLD(date);
    	formatter = DateTimeFormatter.ofPattern("HH:mm:ss");	    	
    	LocalTime time = LocalTime.parse(LocalTime.now().format(formatter));
    	horaInsercion.setHoraLT(time);
    	attendanceRecord.setFechaInsercionRegistro(fechaInsercion);
    	attendanceRecord.setHoraInsercionRegistro(horaInsercion);
    	
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			
			String tabla = "tb_ct_bitacora";
			String query = "INSERT INTO "+ tabla;
			query +="(";
			query += tabla+".fk_id_empleado, "
					+tabla+".Hora, "
					+tabla+".Fecha, "
					+tabla+".tipo, ";			
			query += tabla+".status, "
//					+tabla+".comentarios, "
					+tabla+".fecha_insercion, "
					+tabla+".hora_insercion, "					
					+tabla+".fk_id_usuario_insercion, "					
					+tabla+".fk_id_dispositivo "
					+") "
					+ "VALUES("
				    + attendanceRecord.getEmpleado().getSkuEmpleado() + ", "
					+"'"+ attendanceRecord.getHoraRegistro().getHoraLT() +"', "
					+"'"+ attendanceRecord.getFechaRegistro().getFechaLD() + "', "
					+ attendanceRecord.getTipoRegistro().getEstatusInt() + ", "				
					+ attendanceRecord.getStatusRegistro().getEstatusInt() + ", "
//					+"'"+ attendanceRecord.getComentarioRegistro() + "', "
					+"'"+ attendanceRecord.getFechaInsercionRegistro().getFechaLD() + "', "
					+"'"+ attendanceRecord.getHoraInsercionRegistro().getHoraLT() + "', "					
					+ attendanceRecord.getUsuarioInsercion().getUserId() + ", "					
					+ attendanceRecord.getDevice().getDeviceId() + ""
					+");";			
			try {
				System.out.println(query);
				res = getDatabase().executeNonQuery(query);				
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
		return res;

	}
	
	/**
	 * Selecciona todos los registros de la tabla bitacora de la fecha actual	 * 
	 * @return Vector<BitacoraDTO>
	 */
	public Vector<BitacoraDTO> selectRegistrosBitacora(){
		Vector<BitacoraDTO> Lista = new Vector<BitacoraDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT tb_ct_bitacora.id_bitacora, tb_ct_bitacora.fk_id_empleado, tb_ct_bitacora.Hora, tb_ct_bitacora.Fecha,"
					+ "		tb_ct_bitacora.tipo, tb_ct_bitacora.status, CONCAT(tb_empleados.Nombre, ' ' , tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno)"
					+ "		AS nombre_empleado , tb_empleados.Sku_empleado, tb1.status_string, tb2.status_string"
					+ "	FROM tb_ct_bitacora "
					+ "		LEFT JOIN tb_empleados ON tb_ct_bitacora.fk_id_empleado = tb_empleados.Sku_empleado"
					+ "		LEFT JOIN tb_tipos_estatus AS tb1 ON tb_ct_bitacora.`status` = tb1.status_int"
					+ "		LEFT JOIN tb_tipos_estatus AS tb2 ON tb_ct_bitacora.`tipo` = tb2.status_int"
					+ "	WHERE tb1.aplicacion = 'bitacora' AND tb2.aplicacion = 'bitacora' AND tb_ct_bitacora.Fecha = CURDATE()" 
					+ "		ORDER BY tb_ct_bitacora.fk_id_empleado, tb_ct_bitacora.Fecha, tb_ct_bitacora.Hora ASC;";
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(rs != null){
					while(rs.next()){
						BitacoraDTO registro = new BitacoraDTO();
						try {
							registro = getHerramientasRS().inicializaBitacoraDTOSinEmpleado(rs);
							registro.setEmpleado(new EmpleadoDTO());
							registro.getEmpleado().setIdEmpleado(rs.getInt("tb_ct_bitacora.fk_id_empleado"));
							registro.getEmpleado().setSkuEmpleado(rs.getInt("tb_empleados.Sku_empleado"));
							registro.getEmpleado().setNombreCompletoEmpleado(rs.getString("nombre_empleado"));
							if(registro.getEmpleado().getNombreCompletoEmpleado() == null || registro.getEmpleado().getNombreCompletoEmpleado().equals("") || registro.getEmpleado().getNombreCompletoEmpleado().length() == 0)
								registro.getEmpleado().setNombreCompletoEmpleado("Empleado: " + String.valueOf(registro.getEmpleado().getIdEmpleado() +", no encontrado!"));

						} catch (SQLException e) {

							e.printStackTrace();
						}
						Lista.add(registro);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo");
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
		return Lista;
	}
	
	/**
	 * Selecciona todos los registros de la tabla bitacora de la fecha actual	 * 
	 * @return Vector<BitacoraDTO>
	 */
	public Vector<BitacoraDTO> selectRegistrosBitacora(int qtty){
		Vector<BitacoraDTO> Lista = new Vector<BitacoraDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT tb_ct_bitacora.id_bitacora, tb_ct_bitacora.fk_id_empleado, tb_ct_bitacora.Hora, tb_ct_bitacora.Fecha,"
					+ "		tb_ct_bitacora.tipo, tb_ct_bitacora.status, CONCAT(tb_empleados.Nombre, ' ' , tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno)"
					+ "		AS nombre_empleado , tb_empleados.Sku_empleado, tb1.status_string, tb2.status_string"
					+ "	FROM tb_ct_bitacora "
					+ "		LEFT JOIN tb_empleados ON tb_ct_bitacora.fk_id_empleado = tb_empleados.Sku_empleado"
					+ "		LEFT JOIN tb_tipos_estatus AS tb1 ON tb_ct_bitacora.`status` = tb1.status_int"
					+ "		LEFT JOIN tb_tipos_estatus AS tb2 ON tb_ct_bitacora.`tipo` = tb2.status_int"
					+ "	WHERE tb1.aplicacion = 'bitacora' AND tb2.aplicacion = 'bitacora' AND tb_ct_bitacora.Fecha = CURDATE()" 
					+ "		ORDER BY tb_ct_bitacora.Fecha, tb_ct_bitacora.Hora DESC LIMIT " +qtty+ ";";
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(rs != null){
					while(rs.next()){
						BitacoraDTO registro = new BitacoraDTO();
						try {
							registro = getHerramientasRS().inicializaBitacoraDTOSinEmpleado(rs);
							registro.setEmpleado(new EmpleadoDTO());
							registro.getEmpleado().setIdEmpleado(rs.getInt("tb_ct_bitacora.fk_id_empleado"));
							registro.getEmpleado().setSkuEmpleado(rs.getInt("tb_empleados.Sku_empleado"));
							registro.getEmpleado().setNombreCompletoEmpleado(rs.getString("nombre_empleado"));
							if(registro.getEmpleado().getNombreCompletoEmpleado() == null || registro.getEmpleado().getNombreCompletoEmpleado().equals("") || registro.getEmpleado().getNombreCompletoEmpleado().length() == 0)
								registro.getEmpleado().setNombreCompletoEmpleado("Empleado: " + String.valueOf(registro.getEmpleado().getIdEmpleado() +", no encontrado!"));

						} catch (SQLException e) {

							e.printStackTrace();
						}
						Lista.add(registro);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo");
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
		return Lista;
	}
	
	/**
	 * Selecciona todos los registros de la tabla bitacora	 * 
	 * @return Vector<BitacoraDTO>
	 */
	public Vector<BitacoraDTO> selectRegistrosBitacoraFiltros(HashMap<String, String> map){
		Vector<BitacoraDTO> Lista = new Vector<BitacoraDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT tb_ct_bitacora.id_bitacora, tb_ct_bitacora.fk_id_empleado, tb_ct_bitacora.Hora, tb_ct_bitacora.Fecha,"
					+ "		tb_ct_bitacora.tipo, tb_ct_bitacora.status, CONCAT(tb_empleados.Nombre, ' ' , tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno)"
					+ "		AS nombre_empleado , tb_empleados.Sku_empleado, tb1.status_string, tb2.status_string"
					+ "	FROM tb_ct_bitacora "
					+ "		LEFT JOIN tb_empleados ON tb_ct_bitacora.fk_id_empleado = tb_empleados.Sku_empleado"
					+ "		LEFT JOIN tb_tipos_estatus AS tb1 ON tb_ct_bitacora.`status` = tb1.status_int"
					+ "		LEFT JOIN tb_tipos_estatus AS tb2 ON tb_ct_bitacora.`tipo` = tb2.status_int"
					+ "	WHERE tb1.aplicacion = 'bitacora' AND tb2.aplicacion = 'bitacora' ";

			query += queryFiltrosBitacoraAsistencia(map);					
			query += "		ORDER BY tb_ct_bitacora.fk_id_empleado, tb_ct_bitacora.Fecha, tb_ct_bitacora.Hora ASC;";
			try {
				System.out.println(query);
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			if(rs != null){
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				formatter.withLocale(Locale.US);
				try {
					while(rs.next()){
						BitacoraDTO registro = new BitacoraDTO();
						try {
							registro = getHerramientasRS().inicializaBitacoraDTOSinEmpleado(rs);
							registro.setEmpleado(new EmpleadoDTO());
							registro.getEmpleado().setIdEmpleado(rs.getInt("tb_ct_bitacora.fk_id_empleado"));
							registro.getEmpleado().setSkuEmpleado(rs.getInt("tb_empleados.Sku_empleado"));
							registro.getEmpleado().setNombreCompletoEmpleado(rs.getString("nombre_empleado"));
							if(registro.getEmpleado().getNombreCompletoEmpleado() == null || registro.getEmpleado().getNombreCompletoEmpleado().equals("") || registro.getEmpleado().getNombreCompletoEmpleado().length() == 0)
								registro.getEmpleado().setNombreCompletoEmpleado("Empleado: " + String.valueOf(registro.getEmpleado().getIdEmpleado() +", no encontrado!"));


						} catch (SQLException e) {							
							e.printStackTrace();
						}
						Lista.add(registro);
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
				System.out.println("rs == null en selectRegistrosBitacoraFiltros");
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return Lista;
	}
	
	/**
	 * 
	 * @return Vector<BitacoraEmpleadoDTO>
	 */
	public Vector<BitacoraEmpleadoDTO> selectRegistrosAsistencia(){
		Vector<BitacoraEmpleadoDTO> Lista = new Vector<BitacoraEmpleadoDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT tb_ct_bitacora.id_bitacora, tb_ct_bitacora.fk_id_empleado, tb_ct_bitacora.Hora, tb_ct_bitacora.Fecha, "
					+ "	tb_ct_bitacora.tipo, tb_ct_bitacora.status, CONCAT(tb_empleados.Nombre, ' ' , tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno) "
					+ "			 AS nombre_empleado, tb_empleados.Sku_empleado, tb_empleados.id_empleado, tb1.status_string, tb2.status_string"
					+ "			 FROM tb_ct_bitacora "
					+ "			 LEFT JOIN tb_empleados ON tb_ct_bitacora.fk_id_empleado = tb_empleados.Sku_empleado "
					+ "			 LEFT JOIN tb_tipos_estatus AS tb1 ON tb_ct_bitacora.`status` = tb1.status_int"
					+ "			 LEFT JOIN tb_tipos_estatus AS tb2 ON tb_ct_bitacora.`tipo` = tb2.status_int"
					+ "			 WHERE tb1.aplicacion = 'bitacora' AND tb2.aplicacion = 'bitacora' AND tb_ct_bitacora.status > 0 AND tb_ct_bitacora.Fecha = CURDATE()"
					+ "			 ORDER BY tb_ct_bitacora.fk_id_empleado, tb_ct_bitacora.Fecha, tb_ct_bitacora.Hora ASC ";
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e) {					
				e.printStackTrace();
			}
			if(rs != null){
				try {
					BitacoraEmpleadoDTO bitacoraEmpleadoDTO = new BitacoraEmpleadoDTO();				
					bitacoraEmpleadoDTO.setEmpleado(new EmpleadoDTO());
					bitacoraEmpleadoDTO.setRegistrosAsistencia(new Vector<BitacoraFechaDTO>());
					BitacoraFechaDTO registros = null;
					int i = 0;
					int z = 0;
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					formatter.withLocale(Locale.US);
					while(rs.next()){					
						try {	
							BitacoraDTO reg = new BitacoraDTO();
							if(i != 0){
								if(bitacoraEmpleadoDTO.getEmpleado().getIdEmpleado() == rs.getInt("tb_ct_bitacora.fk_id_empleado")){
									reg = getHerramientasRS().inicializaBitacoraDTOSinEmpleado(rs);
									reg.setEmpleado(bitacoraEmpleadoDTO.getEmpleado());
									if(registros.getFecha().getFechaLD().compareTo(rs.getDate("tb_ct_bitacora.Fecha").toLocalDate()) == 0){
										registros.getRegistrosEnFecha().add(reg);
									}else{									
										registros = new BitacoraFechaDTO();
										registros.setFecha(new FechaDTO());
										registros.getFecha().setFechaLD(rs.getDate("tb_ct_bitacora.Fecha").toLocalDate());
										registros.setRegistrosEnFecha(new Vector<BitacoraDTO>());
										registros.getRegistrosEnFecha().add(reg);	
										bitacoraEmpleadoDTO.getRegistrosAsistencia().add(registros);
									}
									Lista.set(z-1, bitacoraEmpleadoDTO);
								}else{								
									bitacoraEmpleadoDTO = new BitacoraEmpleadoDTO();
									bitacoraEmpleadoDTO.setEmpleado(new EmpleadoDTO());
									bitacoraEmpleadoDTO.setRegistrosAsistencia(new Vector<BitacoraFechaDTO>());
									bitacoraEmpleadoDTO.getEmpleado().setIdEmpleado(rs.getInt("tb_ct_bitacora.fk_id_empleado"));
									bitacoraEmpleadoDTO.getEmpleado().setSkuEmpleado(rs.getInt("tb_empleados.Sku_empleado"));
									bitacoraEmpleadoDTO.getEmpleado().setNombreCompletoEmpleado(rs.getString("nombre_empleado"));							
									if(bitacoraEmpleadoDTO.getEmpleado().getNombreCompletoEmpleado() == null || bitacoraEmpleadoDTO.getEmpleado().getNombreCompletoEmpleado().equals("") || bitacoraEmpleadoDTO.getEmpleado().getNombreCompletoEmpleado().length() == 0){
										bitacoraEmpleadoDTO.getEmpleado().setNombreCompletoEmpleado("Empleado: " + String.valueOf(bitacoraEmpleadoDTO.getEmpleado().getIdEmpleado() +", no encontrado!"));
									}								
									registros = new BitacoraFechaDTO();
									registros.setFecha(new FechaDTO());								
									registros.getFecha().setFechaLD(rs.getDate("tb_ct_bitacora.Fecha").toLocalDate());		
									registros.getFecha().setFechaString(registros.getFecha().getFechaLD().format(formatter));
									registros.setRegistrosEnFecha(new Vector<BitacoraDTO>());	

									reg = getHerramientasRS().inicializaBitacoraDTOSinEmpleado(rs);

									reg.setEmpleado(bitacoraEmpleadoDTO.getEmpleado());
									registros.getRegistrosEnFecha().add(reg);
									bitacoraEmpleadoDTO.getRegistrosAsistencia().add(registros);
									Lista.add(bitacoraEmpleadoDTO);
									z++;
								}
							}else{							
								bitacoraEmpleadoDTO = getHerramientasRS().inicializaBitacoraEmpleadoDTO(rs);									
								Lista.add(bitacoraEmpleadoDTO);
								z++;													
							}
							i++;
						} catch (SQLException e) {							
							e.printStackTrace();
						}	
					}//Temina while
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {				
					e.printStackTrace();
				}
			}else{
				System.out.println("rs == null en selectRegistrosAsistencia");
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return Lista;	
	}
	
	public Vector<BitacoraEmpleadoDTO> selectRegistrosAsistencia2(){
		Vector<BitacoraEmpleadoDTO> Lista = new Vector<BitacoraEmpleadoDTO>();
		if(getDatabase().openDatabase()){			
			List<Date> fechasFiltro = new ArrayList<Date>();
			Date fechaActual = new Date();
			fechasFiltro.add(fechaActual);
			if(!fechasFiltro.isEmpty() && fechasFiltro.size() > 0){					
				EmpleadoDAO empleadoDAO = new EmpleadoDAO(getDatabase());
				ListadosDAO listadosDAO = new ListadosDAO(getDatabase());			
				EmpleadoHorarioDAO empleadoHorario = new EmpleadoHorarioDAO(getDatabase());
				Vector<EmpleadoDTO> empleadosActivos = empleadoDAO.selectIdSkuEmpleados();				
				if(empleadosActivos.size() > 0){
					//					HorarioDAO horarioDAO = new HorarioDAO();
					Locale loc = new Locale("es","MX");
					for(EmpleadoDTO empleado : empleadosActivos){
						BitacoraEmpleadoDTO historial = new BitacoraEmpleadoDTO();
						historial.setEmpleado(empleado);
						historial.setRegistrosAsistencia(new Vector<BitacoraFechaDTO>());
						for(Date fecha : fechasFiltro){
							LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();								
							int dia = localDate.getDayOfMonth();
							int mes = localDate.getMonthValue();
							int anio = localDate.getYear();
							BitacoraFechaDTO bitacoraEnFecha = new BitacoraFechaDTO();						
							bitacoraEnFecha.getFecha().setFechaDateJava(fecha);
							bitacoraEnFecha.getFecha().setDiaDeLaSemana(localDate.getDayOfWeek());
							String nombreDiaSemana = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, loc).substring(0, 1).toUpperCase() + localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, loc).substring(1);
							bitacoraEnFecha.getFecha().setNombreDiaDeLaSemana(nombreDiaSemana);
							java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
							String fechaString = sdf.format(fecha);			
							java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("dd/MM/yyyy");
							String fechaString2 = sdf2.format(fecha);		
							bitacoraEnFecha.getFecha().setFechaString(fechaString2);						
							Vector<BitacoraDTO> registrosEnFecha = selectRegistrosBitacoraDTOEmpleadoEnFecha(empleado.getSkuEmpleado(), fechaString);
							if(registrosEnFecha != null && registrosEnFecha.size() > 0){								
									bitacoraEnFecha.setRegistrosEnFecha(registrosEnFecha);								
							}else{
								//TODO busca el horario para verificar si es laboral para el empleado
								//							if(si es laborar busca en los permisos para ver si tiene permisos)
								//							if(no tiene permiso para faltar se agrega la falta)
								Vector<BitacoraDTO> faltaAsistencia = new Vector<BitacoraDTO>();
								
								HorarioDTO horarioDTO = empleadoHorario.selectHorarioDTOEnDiaPorEmpleadoConConexionAbierta(dia, mes, anio, empleado.getIdEmpleado());
								BitacoraDTO registroDeFaltaAsistencia = new BitacoraDTO();
								registroDeFaltaAsistencia.setEmpleado(empleado);
								registroDeFaltaAsistencia.setFechaRegistro(bitacoraEnFecha.getFecha());
								HoraDTO horaFalta = new HoraDTO();
								horaFalta.setHoraString("-- (sin registro)");
								registroDeFaltaAsistencia.setHoraRegistro(horaFalta);
								if(horarioDTO != null){
									if(horarioDTO.isHorarioNoLaboral()){
										TipoEstatusDTO tipoNoLaboral = new TipoEstatusDTO();
										tipoNoLaboral.setIdTipoEstatus(21);
										tipoNoLaboral.setEstatusString(horarioDTO.getClaveHorario() + " ("+horarioDTO.getNombreHorario()+")");
										registroDeFaltaAsistencia.setTipoRegistro(tipoNoLaboral);
									}else{
										PermisoDTO permisoDTO = selectPermisoDTOPorEmpleadoPorFechaConConexionAbierta(empleado, fechaString);
										if(permisoDTO != null){
											TipoEstatusDTO tipoJustificante = listadosDAO.selectTiposEstatusDTOSimplePorIdTipoEstatusConConexionAbierta(permisoDTO.getMotivoDTO().getIdTipoEstatus());
											if(tipoJustificante == null){
												tipoJustificante = new TipoEstatusDTO();
												tipoJustificante.setIdTipoEstatus(114);
												tipoJustificante.setEstatusString("JUSTIFICANTE");													
											}else{
												if(horarioDTO.getHoraEntrada().getHoraLT().isBefore(LocalTime.now())){														
													tipoJustificante.setEstatusString(tipoJustificante.getEstatusString());
												}
											}
											registroDeFaltaAsistencia.setTipoRegistro(tipoJustificante);
										}else{//Falta
											TipoEstatusDTO tipoFalta = listadosDAO.selectTiposEstatusDTOSimplePorIdTipoEstatusConConexionAbierta(113);
											if(tipoFalta == null){
												tipoFalta = new TipoEstatusDTO();
												tipoFalta.setIdTipoEstatus(113);
												if(!horarioDTO.isHorarioQuebrado()){
													tipoFalta.setEstatusString("FALTA ("+horarioDTO.getHoraEntrada().getHoraString()+"-"+horarioDTO.getHoraSalida() + ")");
												}else{
													tipoFalta.setEstatusString("FALTA ("+horarioDTO.getHoraEntrada().getHoraString()+"-"+horarioDTO.getHoraSalida() + " y "+ horarioDTO.getHoraEntrada2String()+"-"+horarioDTO.getHoraSalida2String()+")");
												}
											}else{
												if(horarioDTO.getHoraEntrada().getHoraLT().isBefore(LocalTime.now())){
													if(!horarioDTO.isHorarioQuebrado()){
														tipoFalta.setEstatusString(tipoFalta.getEstatusString() +" ("+horarioDTO.getHoraEntrada().getHoraString() + "-"+horarioDTO.getHoraSalida()+")");
													}else{
														tipoFalta.setEstatusString(tipoFalta.getEstatusString() +" ("+horarioDTO.getHoraEntrada().getHoraString() + "-"+horarioDTO.getHoraSalida()+ " y "+ horarioDTO.getHoraEntrada2String()+"-"+horarioDTO.getHoraSalida2String()+")");
													}
												}
											}
											registroDeFaltaAsistencia.setTipoRegistro(tipoFalta);
										}											
									}
								}else{
									TipoEstatusDTO tipoIndefinido = listadosDAO.selectTiposEstatusDTOSimplePorIdTipoEstatusConConexionAbierta(23);
									if(tipoIndefinido == null){
										tipoIndefinido = new TipoEstatusDTO();
										tipoIndefinido.setIdTipoEstatus(23);
										tipoIndefinido.setEstatusString("SIN HORARIO");
									}							
									registroDeFaltaAsistencia.setTipoRegistro(tipoIndefinido);
								}
								faltaAsistencia.add(registroDeFaltaAsistencia);
								bitacoraEnFecha.setRegistrosEnFecha(faltaAsistencia);
							}
							historial.getRegistrosAsistencia().add(bitacoraEnFecha);
						}						
						Lista.add(historial);
						

					}//Termina ciclo de empleados
				}else{
					System.out.println("sin empleados");
				}
			}else{
				System.out.println("sin fechas");
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return Lista;

	}
	
	
	/**
	 * 
	 * @param HashMap<String, String> map
	 * @return Vector<BitacoraEmpleadoDTO>
	 */
	public Vector<BitacoraEmpleadoDTO> selectRegistrosAsistenciaFiltros(HashMap<String, String> map){
		Vector<BitacoraEmpleadoDTO> Lista = new Vector<BitacoraEmpleadoDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());			
			ResultSet rs = null;
			String query = "SELECT tb_ct_bitacora.id_bitacora, tb_ct_bitacora.fk_id_empleado, tb_empleados.id_empleado, tb_empleados.Sku_empleado, tb_empleados.Nombre, tb_empleados.Ap_paterno, tb_empleados.Ap_materno, "
					+ " tb_ct_bitacora.Hora, tb_ct_bitacora.Fecha, "
					+ "	tb_ct_bitacora.tipo, tb_ct_bitacora.status, CONCAT(tb_empleados.Nombre, ' ' , tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno) "
					+ "			 AS nombre_empleado , tb1.status_string, tb2.status_string "
					+ "			 FROM tb_ct_bitacora "
					+ "			 LEFT JOIN tb_empleados ON tb_ct_bitacora.fk_id_empleado = tb_empleados.Sku_empleado "
					+ "			 LEFT JOIN tb_tipos_estatus AS tb1 ON tb_ct_bitacora.`status` = tb1.status_int"
					+ "			 LEFT JOIN tb_tipos_estatus AS tb2 ON tb_ct_bitacora.`tipo` = tb2.status_int "
					+ "	WHERE "
					+ "		tb1.aplicacion = 'bitacora' AND tb2.aplicacion = 'bitacora' AND tb_ct_bitacora.status > 0  ";

			query += queryFiltrosBitacoraAsistencia(map);

			query += " ORDER BY tb_ct_bitacora.fk_id_empleado, tb_ct_bitacora.Fecha, tb_ct_bitacora.Hora ASC ";
			//			query += " LIMIT 1000 ";

			System.out.println(query);
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e) {

				e.printStackTrace();
			}
			if(rs != null){
				try {
					/*List<Date> fechas = null;
					if(!valueToStringOrEmpty(map, "fechaDe").equals("") && !valueToStringOrEmpty(map, "fechaA").equals("")){
						fechas = Operaciones.GetRangeOfDates(map.get(""), map.get(""));
					}*/

					BitacoraEmpleadoDTO bitacoraEmpleadoDTO = null;		
					BitacoraFechaDTO registros = null;
					int i = 0;
					int z = 0;
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					formatter.withLocale(Locale.US);
					while(rs.next()){
						try {	
							BitacoraDTO reg = new BitacoraDTO();
							if(i != 0){
								if(bitacoraEmpleadoDTO != null){
									if(bitacoraEmpleadoDTO.getEmpleado().getSkuEmpleado() == rs.getInt("tb_ct_bitacora.fk_id_empleado")){
										reg = getHerramientasRS().inicializaBitacoraDTOSinEmpleado(rs);
										reg.setEmpleado(bitacoraEmpleadoDTO.getEmpleado());
										if(bitacoraEmpleadoDTO.getRegistrosAsistencia() != null){
											if(bitacoraEmpleadoDTO.getRegistrosAsistencia().get(bitacoraEmpleadoDTO.getRegistrosAsistencia().size()-1).getFecha().getFechaLD().compareTo(rs.getDate("tb_ct_bitacora.Fecha").toLocalDate()) == 0){
												bitacoraEmpleadoDTO.getRegistrosAsistencia().get(bitacoraEmpleadoDTO.getRegistrosAsistencia().size()-1).getRegistrosEnFecha().add(reg);
											}else{
												registros = getHerramientasRS().inicializaBitacoraFechaDTO(rs, bitacoraEmpleadoDTO.getEmpleado());												
												bitacoraEmpleadoDTO.getRegistrosAsistencia().add(registros);
											}
										}else{
											System.out.println("bitacoraEmpleadoDTO.getRegistrosAsistencia() == null");
										}
										Lista.set(z-1, bitacoraEmpleadoDTO);
									}else{
										bitacoraEmpleadoDTO = getHerramientasRS().inicializaBitacoraEmpleadoDTO(rs);
										Lista.add(bitacoraEmpleadoDTO);
										z++;
									}
								}else{
									bitacoraEmpleadoDTO = getHerramientasRS().inicializaBitacoraEmpleadoDTO(rs);
									if(bitacoraEmpleadoDTO == null){
										System.out.println("ERROR BITACORA EMPLEADO NO SE ESTA INICIALIZANDO");
										break;
									}else{
										Lista.add(bitacoraEmpleadoDTO);
										z++;
									}
								}
							}else{								
								bitacoraEmpleadoDTO = getHerramientasRS().inicializaBitacoraEmpleadoDTO(rs);
								if(bitacoraEmpleadoDTO != null){
									/*BitacoraFechaDTO bitacoraFechaDTO = new BitacoraFechaDTO();
									bitacoraFechaDTO.setFecha(new FechaDTO());
									bitacoraFechaDTO.setRegistrosEnFecha(new Vector<BitacoraDTO>());
									BitacoraDTO falta = new BitacoraDTO();
									TipoEstatusDTO tipoFalta = new TipoEstatusDTO();
									tipoFalta.setEstatusString("FALTA");
									falta.setTipoRegistro(tipoFalta);
									bitacoraFechaDTO.getRegistrosEnFecha().add(falta);
									bitacoraEmpleadoDTO.getRegistrosAsistencia().add(bitacoraFechaDTO);*/
									Lista.add(bitacoraEmpleadoDTO);
									z++;
								}else{
									System.out.println("ERROR BITACORA EMPLEADO NO SE HA INICIALIZADO");
								}																					
							}
							i++;

						} catch (SQLException e) {							
							e.printStackTrace();
						}
					}// Fin del while
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				} catch (SQLException e) {					
					e.printStackTrace();
				}
			}else{
				System.out.println("rs == nullo en selectRegistrosAsistenciaFiltros");// 4
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return Lista;
	}
	
	/**
	 * 
	 * @param hash
	 * @return
	 */
	public Vector<BitacoraEmpleadoDTO> selectRegistrosAsistenciaFiltros2(HashMap<String, String> hash){
		Vector<BitacoraEmpleadoDTO> Lista = new Vector<BitacoraEmpleadoDTO>();
		if(hash != null && !hash.isEmpty()){
			if(!HerramientasResultSet.valueToStringOrEmpty(hash, "fechaDe").equals("") && !HerramientasResultSet.valueToStringOrEmpty(hash, "fechaA").equals("")){
				List<Date> fechasFiltro = Operaciones.GetRangeOfDates(hash.get("fechaDe"), hash.get("fechaA"));
				boolean soloFaltas = false;	
				if(!HerramientasResultSet.valueToStringOrEmpty(hash, "tipoReg").equals("")){
					if(hash.get("tipoReg").equals("113")){//falta
						soloFaltas = true;
					}else{
						soloFaltas = false;
					}
				}else{
					soloFaltas = false;
				}
				if(getDatabase().openDatabase()){					
					if(!fechasFiltro.isEmpty() && fechasFiltro.size() > 0){
						EmpleadoDAO empleadoDAO = new EmpleadoDAO(getDatabase());
						ListadosDAO listadosDAO = new ListadosDAO(getDatabase());			
						EmpleadoHorarioDAO empleadoHorario = new EmpleadoHorarioDAO(getDatabase());
						Vector<EmpleadoDTO> empleadosActivos = empleadoDAO.selectIdSkuEmpleados(hash);

						if(empleadosActivos.size() > 0){
							//					HorarioDAO horarioDAO = new HorarioDAO();
							Locale loc = new Locale("es","MX");
							for(EmpleadoDTO empleado : empleadosActivos){
								BitacoraEmpleadoDTO historial = new BitacoraEmpleadoDTO();
								historial.setEmpleado(empleado);
								historial.setRegistrosAsistencia(new Vector<BitacoraFechaDTO>());
								for(Date fecha : fechasFiltro){
									LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();								
									int dia = localDate.getDayOfMonth();
									int mes = localDate.getMonthValue();
									int anio = localDate.getYear();
									BitacoraFechaDTO bitacoraEnFecha = new BitacoraFechaDTO();						
									bitacoraEnFecha.getFecha().setFechaDateJava(fecha);
									bitacoraEnFecha.getFecha().setDiaDeLaSemana(localDate.getDayOfWeek());
									String nombreDiaSemana = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, loc).substring(0, 1).toUpperCase() + localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, loc).substring(1);
									bitacoraEnFecha.getFecha().setNombreDiaDeLaSemana(nombreDiaSemana);
									java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
									String fechaString = sdf.format(fecha);			
									java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("dd/MM/yyyy");
									String fechaString2 = sdf2.format(fecha);		
									bitacoraEnFecha.getFecha().setFechaString(fechaString2);						
									Vector<BitacoraDTO> registrosEnFecha = selectRegistrosBitacoraDTOEmpleadoEnFecha(empleado.getSkuEmpleado(), fechaString, hash);
									if(registrosEnFecha != null && registrosEnFecha.size() > 0){
										if(!soloFaltas){
											bitacoraEnFecha.setRegistrosEnFecha(registrosEnFecha);
										}
									}else{
										if(soloFaltas || HerramientasResultSet.valueToStringOrEmpty(hash, "tipoReg").equals("")){
											//TODO busca el horario para verificar si es laboral para el empleado
											//							if(si es laborar busca en los permisos para ver si tiene permisos)
											//							if(no tiene permiso para faltar se agrega la falta)
											Vector<BitacoraDTO> faltaAsistencia = new Vector<BitacoraDTO>();

											HorarioDTO horarioDTO = empleadoHorario.selectHorarioDTOEnDiaPorEmpleadoConConexionAbierta(dia, mes, anio, empleado.getIdEmpleado());
											BitacoraDTO registroDeFaltaAsistencia = new BitacoraDTO();
											registroDeFaltaAsistencia.setEmpleado(empleado);
											registroDeFaltaAsistencia.setFechaRegistro(bitacoraEnFecha.getFecha());
											HoraDTO horaFalta = new HoraDTO();
											horaFalta.setHoraString("-- (sin registro)");
											registroDeFaltaAsistencia.setHoraRegistro(horaFalta);
											if(horarioDTO != null){
												if(horarioDTO.isHorarioNoLaboral()){
													TipoEstatusDTO tipoNoLaboral = new TipoEstatusDTO();
													tipoNoLaboral.setIdTipoEstatus(21);
													tipoNoLaboral.setEstatusString(horarioDTO.getClaveHorario() + " ("+horarioDTO.getNombreHorario()+")");
													registroDeFaltaAsistencia.setTipoRegistro(tipoNoLaboral);
												}else{
													PermisoDTO permisoDTO = selectPermisoDTOPorEmpleadoPorFechaConConexionAbierta(empleado, fechaString);
													if(permisoDTO != null){
														registroDeFaltaAsistencia.setFaltaJustificada(true);
														TipoEstatusDTO tipoJustificante = listadosDAO.selectTiposEstatusDTOSimplePorIdTipoEstatusConConexionAbierta(permisoDTO.getMotivoDTO().getIdTipoEstatus());
														if(tipoJustificante == null){
															tipoJustificante = new TipoEstatusDTO();
															tipoJustificante.setIdTipoEstatus(114);
															tipoJustificante.setEstatusString("JUSTIFICANTE");													
														}else{
															if(horarioDTO.getHoraEntrada().getHoraLT().isBefore(LocalTime.now())){														
																tipoJustificante.setEstatusString(tipoJustificante.getEstatusString());
															}
														}
														registroDeFaltaAsistencia.setTipoRegistro(tipoJustificante);
													}else{//Falta											

														registroDeFaltaAsistencia.setFaltaJustificada(false);
														TipoEstatusDTO tipoFalta = listadosDAO.selectTiposEstatusDTOSimplePorIdTipoEstatusConConexionAbierta(113);
														if(tipoFalta == null){
															tipoFalta = new TipoEstatusDTO();
															tipoFalta.setIdTipoEstatus(113);
															if(!horarioDTO.isHorarioQuebrado()){
																tipoFalta.setEstatusString("FALTA ("+horarioDTO.getHoraEntrada().getHoraString()+"-"+horarioDTO.getHoraSalida() + ")");
															}else{
																tipoFalta.setEstatusString("FALTA ("+horarioDTO.getHoraEntrada().getHoraString()+"-"+horarioDTO.getHoraSalida() + " y "+ horarioDTO.getHoraEntrada2String()+"-"+horarioDTO.getHoraSalida2String()+")");
															}
														}else{
															if(horarioDTO.getHoraEntrada().getHoraLT().isBefore(LocalTime.now())){
																if(!horarioDTO.isHorarioQuebrado()){
																	tipoFalta.setEstatusString(tipoFalta.getEstatusString() +" ("+horarioDTO.getHoraEntrada().getHoraString() + "-"+horarioDTO.getHoraSalida()+")");
																}else{
																	tipoFalta.setEstatusString(tipoFalta.getEstatusString() +" ("+horarioDTO.getHoraEntrada().getHoraString() + "-"+horarioDTO.getHoraSalida()+ " y "+ horarioDTO.getHoraEntrada2String()+"-"+horarioDTO.getHoraSalida2String()+")");
																}
															}
														}
														registroDeFaltaAsistencia.setTipoRegistro(tipoFalta);
													}											
												}
											}else{
												TipoEstatusDTO tipoIndefinido = listadosDAO.selectTiposEstatusDTOSimplePorIdTipoEstatusConConexionAbierta(23);
												if(tipoIndefinido == null){
													tipoIndefinido = new TipoEstatusDTO();
													tipoIndefinido.setIdTipoEstatus(23);
													tipoIndefinido.setEstatusString("SIN HORARIO");
												}							
												registroDeFaltaAsistencia.setTipoRegistro(tipoIndefinido);
											}
											faltaAsistencia.add(registroDeFaltaAsistencia);
											bitacoraEnFecha.setRegistrosEnFecha(faltaAsistencia);
										}else{
											System.out.println("Sin tipo definido de filtro para definir las faltas D: ?");
										}
									}
									historial.getRegistrosAsistencia().add(bitacoraEnFecha);
								}//Termina ciclo for de fechas
								/*if(soloFaltas){
									if(historial.getRegistrosAsistencia() != null && historial.getRegistrosAsistencia().size() > 0){
										if(historial.getRegistrosAsistencia().firstElement().getRegistrosEnFecha() != null && historial.getRegistrosAsistencia().firstElement().getRegistrosEnFecha().size() > 0){
											String value = historial.getRegistrosAsistencia().firstElement().getRegistrosEnFecha().firstElement().getTipoRegistro().getEstatusString();
											if(!value.isEmpty() && value.contains("FALTA")){
												Lista.add(historial);
											}else{
												System.out.println("SIN REGISTRO DE FALTAS EN EL PRIMER ELEMENTO DE LA FECHA");
											}
										}else{
											System.out.println("SIN REGISTROS DEL PRIMER ELEMENTO DE FECHA");
										}
									}else{
										System.out.println("SIN REGISTRO DE FALTA A PESAR DE LA PETICION DE SOLO FALTAS");
									}
								}else{*/
									Lista.add(historial);
								/*}*/

							}//Termina ciclo de empleados
						}else{
							System.out.println("sin empleados");
						}
					}else{
						System.out.println("sin fechas");
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
				System.out.println("fecha null o vacio, O fechaA null o vacio en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("hash null o vacio en " + this.getClass().getSimpleName());
		}			
		return Lista;
	}
	
	/**
	 * 
	 * @return Vector<BitacoraDTO>
	 */
	public Vector<BitacoraDTO> selectRegistrosEmpladosHorarios(UserDTO user){
		Vector<BitacoraDTO> Lista = new Vector<BitacoraDTO>();		
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT "
						+ "		tb_ct_bitacora.id_bitacora, "
						+ "		tb_ct_bitacora.fk_id_empleado," 
						+ "		tb_ct_bitacora.Hora, tb_ct_bitacora.Fecha," 
						+ "		tb_ct_bitacora.tipo, tb_ct_bitacora.status, "
						+ "		CONCAT(tb_empleados.Nombre, ' ' , tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno) AS nombre_empleado ,"
						+ "		tb_empleados.id_empleado,"
						+ "		tb_empleados.Sku_empleado,"
						+ "		tb_tipos_estatus.status_string," 
						+ "		tb_empleados_has_tb_horarios.id_empleado_horario, tb_empleados_has_tb_horarios.fk_documentos_empleados_horario," 
						+ "			CASE DAYOFMONTH(tb_ct_bitacora.Fecha)"
						+ "				WHEN 1 THEN   tb_empleados_has_tb_horarios.dia_01"
						+ "				WHEN 2 THEN tb_empleados_has_tb_horarios.dia_02"
						+ "				WHEN 3 THEN tb_empleados_has_tb_horarios.dia_03"
						+ "				WHEN 4 THEN tb_empleados_has_tb_horarios.dia_04"
						+ "				WHEN 5 THEN tb_empleados_has_tb_horarios.dia_05"
						+ "				WHEN 6 THEN tb_empleados_has_tb_horarios.dia_06"
						+ "				WHEN 7 THEN tb_empleados_has_tb_horarios.dia_07"
						+ "				WHEN 8 THEN tb_empleados_has_tb_horarios.dia_08"
						+ "				WHEN 9 THEN tb_empleados_has_tb_horarios.dia_09"
						+ "				WHEN 10 THEN tb_empleados_has_tb_horarios.dia_10"
						+ "				WHEN 11 THEN tb_empleados_has_tb_horarios.dia_11"
						+ "				WHEN 12 THEN tb_empleados_has_tb_horarios.dia_12"
						+ "				WHEN 13 THEN tb_empleados_has_tb_horarios.dia_13"
						+ "				WHEN 14 THEN tb_empleados_has_tb_horarios.dia_14"
						+ "				WHEN 15 THEN tb_empleados_has_tb_horarios.dia_15"
						+ "				WHEN 16 THEN tb_empleados_has_tb_horarios.dia_16"
						+ "				WHEN 17 THEN tb_empleados_has_tb_horarios.dia_17"
						+ "				WHEN 18 THEN tb_empleados_has_tb_horarios.dia_18"
						+ "				WHEN 19 THEN tb_empleados_has_tb_horarios.dia_19"
						+ "				WHEN 20 THEN tb_empleados_has_tb_horarios.dia_20"
						+ "				WHEN 21 THEN tb_empleados_has_tb_horarios.dia_21"
						+ "				WHEN 22 THEN tb_empleados_has_tb_horarios.dia_22"
						+ "				WHEN 23 THEN tb_empleados_has_tb_horarios.dia_23"
						+ "				WHEN 24 THEN tb_empleados_has_tb_horarios.dia_24"
						+ "				WHEN 25 THEN tb_empleados_has_tb_horarios.dia_25"
						+ "				WHEN 26 THEN tb_empleados_has_tb_horarios.dia_26"
						+ "				WHEN 27 THEN tb_empleados_has_tb_horarios.dia_27"					
						+ "				WHEN 28 THEN tb_empleados_has_tb_horarios.dia_28"
						+ "				WHEN 29 THEN tb_empleados_has_tb_horarios.dia_29"
						+ "				WHEN 30 THEN tb_empleados_has_tb_horarios.dia_30"
						+ "				WHEN 31 THEN tb_empleados_has_tb_horarios.dia_31"
						+ "				ELSE tb_empleados_has_tb_horarios.dia_01"
						+ "			END AS id_horario_dia, "
						+ "		DAYOFMONTH(tb_ct_bitacora.Fecha) AS dia_del_mes"								
						+ "	FROM tb_ct_bitacora "
						+ "		LEFT JOIN tb_empleados ON tb_ct_bitacora.fk_id_empleado = tb_empleados.Sku_empleado" 
						+ "		LEFT JOIN tb_empleados_has_tb_horarios ON tb_empleados.id_empleado = tb_empleados_has_tb_horarios.fk_id_empleado"
						+ "		LEFT JOIN tb_tipos_estatus ON tb_ct_bitacora.`status` = tb_tipos_estatus.status_int"
						+ "		LEFT JOIN tb_documentos ON tb_empleados_has_tb_horarios.fk_documentos_empleados_horario = tb_documentos.id_documento"
						+ "	WHERE tb_tipos_estatus.aplicacion = 'bitacora' "
						+ "		AND tb_ct_bitacora.`status` = 0"
						+ "		AND MONTH(tb_ct_bitacora.Fecha) = tb_empleados_has_tb_horarios.mes_validez"				
						+ "		AND YEAR(tb_ct_bitacora.Fecha) = tb_empleados_has_tb_horarios.anio_validez"
						+ "		AND tb_empleados_has_tb_horarios.status_horario = 1"
						+ "		ORDER BY tb_ct_bitacora.fk_id_empleado, tb_ct_bitacora.Fecha, tb_ct_bitacora.Hora ASC";
			try{
				rs = getDatabase().executeQuery(query);

			} catch (SQLException e) {				
				e.printStackTrace();
			}
			try {
				int idEmpleadoAnterior = -1;
				LocalDate fechaAnterior = null;
				boolean buscaEntrada = false;
				boolean buscaSalida = false;
				boolean mismoEmpleado = false;
				boolean horarioDiaSiguiente = false;
				LocalTime horaSalidaDiaSiguiente = null;
				LocalDate diaEntradaParaDiaSiguiente = null;
				while(rs.next()){
					BitacoraProcesarDTO registroProcesar = new BitacoraProcesarDTO();
					try {
						registroProcesar.setId(rs.getInt("tb_ct_bitacora.id_bitacora"));
						registroProcesar.setEmpleado(new EmpleadoDTO());
						registroProcesar.getEmpleado().setIdEmpleado(rs.getInt("tb_empleados.id_empleado"));
						registroProcesar.getEmpleado().setSkuEmpleado(rs.getInt("tb_ct_bitacora.fk_id_empleado"));
						registroProcesar.getEmpleado().setNombreEmpleado(rs.getString("nombre_empleado"));
						if(registroProcesar.getEmpleado().getNombreEmpleado() == null || registroProcesar.getEmpleado().getNombreEmpleado().equals("") || registroProcesar.getEmpleado().getNombreEmpleado().length() == 0)
							registroProcesar.getEmpleado().setNombreEmpleado("Empleado: " + String.valueOf(registroProcesar.getEmpleado().getIdEmpleado() +", no encontrado!"));
						registroProcesar.getHoraRegistro().setHoraLT(rs.getTime("tb_ct_bitacora.Hora").toLocalTime());
						registroProcesar.getHoraRegistro().setHoraString(rs.getTime("tb_ct_bitacora.Hora").toString());
						registroProcesar.getFechaRegistro().setFechaLD(rs.getDate("tb_ct_bitacora.Fecha").toLocalDate());
						registroProcesar.getFechaRegistro().setFechaStringSQL(rs.getDate("tb_ct_bitacora.Fecha").toString());
						registroProcesar.getFechaRegistro().setFechaDateSQL(rs.getDate("tb_ct_bitacora.Fecha"));
						registroProcesar.getTipoRegistro().setIdTipoEstatus(rs.getInt("tb_ct_bitacora.tipo"));						
						registroProcesar.getStatusRegistro().setIdTipoEstatus(rs.getInt("tb_ct_bitacora.status"));						
						registroProcesar.getStatusRegistro().setEstatusString(rs.getString("status_string"));
						//						System.out.println("Empleado: " + registroProcesar.getEmpleado().getIdEmpleado() + " FECHA: " + registroProcesar.getFechaRegistro());
						if(idEmpleadoAnterior != registroProcesar.getEmpleado().getIdEmpleado()){
							idEmpleadoAnterior = registroProcesar.getEmpleado().getIdEmpleado();
							fechaAnterior = registroProcesar.getFechaRegistro().getFechaLD();
							buscaEntrada = true;
							buscaSalida = false;
							mismoEmpleado = false;
							horarioDiaSiguiente = false;	

						}else{							
							mismoEmpleado = true;
						}
						if(rs.getInt("id_empleado_horario") > 0 ){
							registroProcesar.setEmpleadoHorario(new EmpleadoHorarioDTO());
							registroProcesar.getEmpleadoHorario().setEmpleado(registroProcesar.getEmpleado());
							registroProcesar.getEmpleadoHorario().setHorario(new HorarioDTO());
							registroProcesar.getEmpleadoHorario().setIdEmpleadoHorario(rs.getInt("id_empleado_horario"));
							registroProcesar.getEmpleadoHorario().getHorario().setIdHorario(rs.getInt("id_horario_dia"));
							registroProcesar.setDocumentoHorario(new DocumentoHorarioDTO());
							registroProcesar.getDocumentoHorario().setIdDocumentoHorarioEmpleados(rs.getInt("tb_empleados_has_tb_horarios.fk_documentos_empleados_horario"));
							String querySelect = "SELECT * FROM tb_horarios WHERE tb_horarios.id_horario = " + registroProcesar.getEmpleadoHorario().getHorario().getIdHorario() + " AND tb_horarios.tb_tipos_estatus_id_tipo_estatus = 5";
							ResultSet rs2 = null;
							rs2 = getDatabase().executeQuery(querySelect);
							while(rs2.next()){
								registroProcesar.getEmpleadoHorario().getHorario().getTipoEstatusDTO().setEstatusInt(rs2.getInt("tb_horarios.tb_tipos_estatus_id_tipo_estatus"));						
								if(registroProcesar.getEmpleadoHorario().getHorario().getTipoEstatusDTO().getEstatusInt() == 5){
									registroProcesar.getEmpleadoHorario().getHorario().setClaveHorario(rs2.getString("tb_horarios.Clave"));
									registroProcesar.getEmpleadoHorario().getHorario().setNombreHorario(rs2.getString("tb_horarios.Nombre_horario"));
									registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada().setHoraLT(rs2.getTime("tb_horarios.Hora_entrada").toLocalTime());
									registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada().setHoraString(rs2.getTime("tb_horarios.Hora_entrada").toString());
									registroProcesar.getEmpleadoHorario().getHorario().setHoraSalida(rs2.getTime("tb_horarios.Hora_salida").toLocalTime());
									registroProcesar.getEmpleadoHorario().getHorario().setHoraRetardo(rs2.getTime("tb_horarios.hora_retardo").toLocalTime());
									registroProcesar.getEmpleadoHorario().getHorario().setHorarioQuebrado((rs2.getInt("tb_horarios.h_quebrado") == 4 ? false : true));
									registroProcesar.getEmpleadoHorario().getHorario().setHorarioNoLaboral((rs2.getInt("tb_horarios.no_laboral") == 1 ? true : false));
									if(registroProcesar.getEmpleadoHorario().getHorario().isHorarioQuebrado()){
										registroProcesar.getEmpleadoHorario().getHorario().setHoraEntrada2(rs2.getTime("tb_horarios.Hora_entrada2").toLocalTime());
										registroProcesar.getEmpleadoHorario().getHorario().setHoraSalida2(rs2.getTime("tb_horarios.Hora_salida2").toLocalTime());
										registroProcesar.getEmpleadoHorario().getHorario().setHoraRetardo2(rs2.getTime("tb_horarios.Hora_retardo2").toLocalTime());
									}
									registroProcesar.getEmpleadoHorario().getHorario().setHorarioDeDiaSiguiente(rs2.getBoolean("tb_horarios.de_dia_siguiente"));
									boolean horarioNoLaboral = registroProcesar.getEmpleadoHorario().getHorario().isHorarioNoLaboral();
									boolean horarioQuebrado = registroProcesar.getEmpleadoHorario().getHorario().isHorarioQuebrado();
									if(registroProcesar.getEmpleadoHorario().getHorario().isHorarioDeDiaSiguiente()){
										diaEntradaParaDiaSiguiente = registroProcesar.getFechaRegistro().getFechaLD();
										horaSalidaDiaSiguiente = registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida();
									}

									if(mismoEmpleado){
										if(fechaAnterior.equals(registroProcesar.getFechaRegistro())){											
											if(!horarioDiaSiguiente){
												horarioDiaSiguiente = registroProcesar.getEmpleadoHorario().getHorario().isHorarioDeDiaSiguiente();
												if(horarioDiaSiguiente){
													buscaEntrada = false;
													buscaSalida = true;
												}else{
													buscaEntrada = false;
													buscaSalida = true;
												}
											}else{
												buscaEntrada = false;
												buscaSalida = true;
											}
											//										System.out.println("Buscando salida: ");
										}else{											
											if(!horarioDiaSiguiente){
												buscaEntrada = true;
												buscaSalida = false;																							
											}else{												
												if(!horarioNoLaboral){
													buscaEntrada = true;
													buscaSalida = false;	
													horarioDiaSiguiente = registroProcesar.getEmpleadoHorario().getHorario().isHorarioDeDiaSiguiente();
												}else{
													buscaEntrada = false;
													buscaSalida = true;
												}
											}
											//										System.out.println("Buscando entrada: ");
										}
									}


									//									System.out.println("Horario valido!, Empleado: " + registroProcesar.getEmpleado().getIdEmpleado() 
									//											+ ", idHorario: " + registroProcesar.getEmpleadoHorario().getHorario().getIdHorario());
									//									String claveHorario = registroProcesar.getEmpleadoHorario().getHorario().getClaveHorario();

									if(!horarioDiaSiguiente){
										if(!horarioNoLaboral){
											int tiempoTolerancia = registroProcesar.getEmpleadoHorario().getHorario().getHoraRetardo().toSecondOfDay();
											DateTimeFormatter horaToleranciaFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
											if(!horarioQuebrado){
												if(buscaEntrada){
													DateTimeFormatter fechaSQLFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
													String querySelectEntradaReg = "SELECT * FROM tb_ct_bitacora WHERE tb_ct_bitacora.fk_id_empleado= " + registroProcesar.getEmpleado().getSkuEmpleado() 
															+ " AND tb_ct_bitacora.Fecha = '" + registroProcesar.getFechaRegistro().getFechaLD().format(fechaSQLFormatter) + "'"
															+ " AND tb_ct_bitacora.Hora < '" + registroProcesar.getHoraRegistro().getHoraLT().format(horaToleranciaFormatter) + "'"
															+ " AND (tb_ct_bitacora.tipo >= 5 AND tb_ct_bitacora.tipo <= 10)"
															+ " AND tb_ct_bitacora.status = 1 ORDER BY tb_ct_bitacora.Hora DESC LIMIT 1";
													ResultSet rs3 = null;
													//												System.out.println("Query busca salida = " + querySelectEntradaReg);
													rs3 = getDatabase().executeQuery(querySelectEntradaReg);
													while(rs3.next()){
														buscaSalida = true;
														buscaEntrada = false;
//														System.out.println("busca salida: " + buscaSalida);
													}
												}
												if(buscaEntrada){												
													LocalTime horaEntradaDeHorarioConTolerancia =  registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada().getHoraLT().plusSeconds(tiempoTolerancia);
													horaEntradaDeHorarioConTolerancia = LocalTime.parse(horaEntradaDeHorarioConTolerancia.format(horaToleranciaFormatter));
													//												System.out.println("hora del horario de entrada con la tolerancia incluida:  " + horaEntradaDeHorarioConTolerancia);
													int ontime = horaEntradaDeHorarioConTolerancia.compareTo(registroProcesar.getHoraRegistro().getHoraLT());
													if(ontime < 0){
														//													System.out.println("Llego tarde: debe de llegar a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada() + " -- y llego a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(10);
													}else if(ontime == 0){
														//													System.out.println("llego a tiempo debe de llegar a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada() + " -- y llego a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(5);
													}else if(ontime > 0){

														if(registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada().getHoraLT().compareTo(registroProcesar.getHoraRegistro().getHoraLT()) > 0){
															registroProcesar.getTipoRegistro().setEstatusInt(7);
															System.out.println("llego temprano debe de llegar a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada().getHoraString() + " -- y llego a las: " + registroProcesar.getHoraRegistro().getHoraString());
														}else{
															registroProcesar.getTipoRegistro().setEstatusInt(8);
															System.out.println("llego con tolerancia debe de llegar a las:  " +  horaEntradaDeHorarioConTolerancia + " -- y llego a las: " + registroProcesar.getHoraRegistro().getHoraString());
														}

													}else{
														//													System.out.println("?? no puedo determinar si llego temprano a tiempo o tarde :(" + registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada() + " -- y llego a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(33);
													}
													buscaEntrada = false;
												}else if (buscaSalida) {												
													DateTimeFormatter fechaSQLFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
													String querySelectEntradaReg = "SELECT * FROM tb_ct_bitacora WHERE tb_ct_bitacora.fk_id_empleado= " + registroProcesar.getEmpleado().getIdEmpleado() 
															+ " AND tb_ct_bitacora.Fecha = '" + registroProcesar.getFechaRegistro().getFechaLD().format(fechaSQLFormatter) + "'"
															+ " AND tb_ct_bitacora.Hora < '" + registroProcesar.getHoraRegistro().getHoraLT().format(horaToleranciaFormatter) + "'"
															+ " AND (tb_ct_bitacora.tipo >= 15 AND tb_ct_bitacora.tipo <= 20)"
															+ " AND tb_ct_bitacora.status = 1 ORDER BY tb_ct_bitacora.Hora DESC";
													ResultSet rs4 = null;
//													System.out.println("Query busca salida = " + querySelectEntradaReg);
													rs4 = getDatabase().executeQuery(querySelectEntradaReg);
													if(rs4 != null){
														while(rs4.next()){
															String queryUpdateSalidas = "UPDATE tb_ct_bitacora SET tb_ct_bitacora.tipo = 50 WHERE tb_ct_bitacora.id_bitacora=" + String.valueOf(rs4.getInt("tb_ct_bitacora.id_bitacora"));
															int res = getDatabase().executeNonQuery(queryUpdateSalidas); 
															if(res > 0){
																//SALIDA MODIFICADA A INTERMEDIA
															}
														}
													}

													/*LocalTime horaSalidaDeHorarioConTolerancia =  registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida().minusSeconds(tiempoTolerancia);
													horaSalidaDeHorarioConTolerancia = LocalTime.parse(horaSalidaDeHorarioConTolerancia.format(horaToleranciaFormatter));
													System.out.println("hora del horario de salida  con la tolerancia incluida:  " + horaSalidaDeHorarioConTolerancia);*/
													int ontime = registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida().compareTo(registroProcesar.getHoraRegistro().getHoraLT());
													if(ontime < 0){
														//													System.out.println("Salio tarde debe de salir a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- y Salio a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(17);
													}else if(ontime == 0){
														//													System.out.println("Salio a tiempo debe de salir a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- y Salio a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(15);
													}else if(ontime > 0){
														//													System.out.println("Salio temprano debe de salir a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- y Salio a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(20);
													}else{
														//													System.out.println("?? no puedo determinar si Salio temprano a tiempo o tarde :( " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(33);
													}
													buscaSalida = false;
												}
											}else{
												//											System.out.println("Horario quebrado!! falta trabajar en este clave: " + claveHorario);
												boolean buscaSalidaIntermedia = false;
												boolean buscaEntradaIntermedia = false;
												if(buscaEntrada){
													DateTimeFormatter fechaSQLFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
													String querySelectEntradaReg = "SELECT * FROM tb_ct_bitacora WHERE tb_ct_bitacora.fk_id_empleado= " + registroProcesar.getEmpleado().getSkuEmpleado() 
															+ " AND tb_ct_bitacora.Fecha = '" + registroProcesar.getFechaRegistro().getFechaLD().format(fechaSQLFormatter) + "'"
															+ " AND tb_ct_bitacora.Hora < '" + registroProcesar.getHoraRegistro().getHoraLT().format(horaToleranciaFormatter) + "'"
															+ " AND (tb_ct_bitacora.tipo >= 5 AND tb_ct_bitacora.tipo <= 10)"
															+ " AND tb_ct_bitacora.status = 1 ORDER BY tb_ct_bitacora.Hora DESC LIMIT 1";
													ResultSet rs3 = null;
													//												System.out.println("Query busca salida = " + querySelectEntradaReg);
													rs3 = getDatabase().executeQuery(querySelectEntradaReg);
													while(rs3.next()){
														String querySelectSalidaIntermediaReg = "SELECT * FROM tb_ct_bitacora WHERE tb_ct_bitacora.fk_id_empleado= " + registroProcesar.getEmpleado().getSkuEmpleado() 
																+ " AND tb_ct_bitacora.Fecha = '" + registroProcesar.getFechaRegistro().getFechaLD().format(fechaSQLFormatter) + "'"
																+ " AND tb_ct_bitacora.Hora < '" + registroProcesar.getHoraRegistro().getHoraLT().format(horaToleranciaFormatter) + "'"
																+ " AND (tb_ct_bitacora.tipo = 38 OR tb_ct_bitacora.tipo = 39 OR tb_ct_bitacora.tipo = 41"
																+ " AND tb_ct_bitacora.status = 1 ORDER BY tb_ct_bitacora.Hora DESC LIMIT 1";
														ResultSet rs4 = null;
														//												System.out.println("Query busca salida = " + querySelectEntradaReg);
														rs4 = getDatabase().executeQuery(querySelectSalidaIntermediaReg);
														while(rs4.next()){
															buscaEntradaIntermedia = true;
															buscaSalidaIntermedia = false;
														}
														
														if(buscaEntradaIntermedia){
															String querySelectEntradaIntReg = "SELECT * FROM tb_ct_bitacora WHERE tb_ct_bitacora.fk_id_empleado= " + registroProcesar.getEmpleado().getSkuEmpleado() 
																	+ " AND tb_ct_bitacora.Fecha = '" + registroProcesar.getFechaRegistro().getFechaLD().format(fechaSQLFormatter) + "'"
																	+ " AND tb_ct_bitacora.Hora < '" + registroProcesar.getHoraRegistro().getHoraLT().format(horaToleranciaFormatter) + "'"
																	+ " AND (tb_ct_bitacora.tipo = 3 OR tb_ct_bitacora.tipo = 4 OR tb_ct_bitacora.tipo = 11 OR tb_ct_bitacora.tipo = 12"
																	+ " AND tb_ct_bitacora.status = 1 ORDER BY tb_ct_bitacora.Hora DESC LIMIT 1";
															ResultSet rs5 = null;
															//												System.out.println("Query busca salida = " + querySelectEntradaReg);
															rs5 = getDatabase().executeQuery(querySelectEntradaIntReg);
															while(rs5.next()){
																buscaSalida = true;
																buscaEntrada = false;
															}
														}else{
															buscaSalidaIntermedia = true;
															buscaEntrada = false;
														}
														
//														System.out.println("busca salida: " + buscaSalida);
													}
												}
												
												if(buscaEntrada){												
													LocalTime horaEntradaDeHorarioConTolerancia =  registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada().getHoraLT().plusSeconds(tiempoTolerancia);
													horaEntradaDeHorarioConTolerancia = LocalTime.parse(horaEntradaDeHorarioConTolerancia.format(horaToleranciaFormatter));
													//												System.out.println("hora del horario de entrada con la tolerancia incluida:  " + horaEntradaDeHorarioConTolerancia);
													int ontime = horaEntradaDeHorarioConTolerancia.compareTo(registroProcesar.getHoraRegistro().getHoraLT());
													if(ontime < 0){
														//													System.out.println("Llego tarde: debe de llegar a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada() + " -- y llego a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(10);
													}else if(ontime == 0){
														//													System.out.println("llego a tiempo debe de llegar a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada() + " -- y llego a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(5);
													}else if(ontime > 0){

														if(registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada().getHoraLT().compareTo(registroProcesar.getHoraRegistro().getHoraLT()) > 0){
															registroProcesar.getTipoRegistro().setEstatusInt(7);
															System.out.println("llego temprano debe de llegar a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada().getHoraString() + " -- y llego a las: " + registroProcesar.getHoraRegistro().getHoraString());
														}else{
															registroProcesar.getTipoRegistro().setEstatusInt(8);
															System.out.println("llego con tolerancia debe de llegar a las:  " +  horaEntradaDeHorarioConTolerancia + " -- y llego a las: " + registroProcesar.getHoraRegistro().getHoraString());
														}

													}else{
														//													System.out.println("?? no puedo determinar si llego temprano a tiempo o tarde :(" + registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada() + " -- y llego a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(33);
													}
													buscaEntrada = false;
												}else if (buscaSalida) {												
													DateTimeFormatter fechaSQLFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
													String querySelectEntradaReg = "SELECT * FROM tb_ct_bitacora WHERE tb_ct_bitacora.fk_id_empleado= " + registroProcesar.getEmpleado().getIdEmpleado() 
															+ " AND tb_ct_bitacora.Fecha = '" + registroProcesar.getFechaRegistro().getFechaLD().format(fechaSQLFormatter) + "'"
															+ " AND tb_ct_bitacora.Hora < '" + registroProcesar.getHoraRegistro().getHoraLT().format(horaToleranciaFormatter) + "'"
															+ " AND (tb_ct_bitacora.tipo >= 15 AND tb_ct_bitacora.tipo <= 20)"
															+ " AND tb_ct_bitacora.status = 1 ORDER BY tb_ct_bitacora.Hora DESC";
													ResultSet rs4 = null;
//													System.out.println("Query busca salida = " + querySelectEntradaReg);
													rs4 = getDatabase().executeQuery(querySelectEntradaReg);
													if(rs4 != null){
														while(rs4.next()){
															String queryUpdateSalidas = "UPDATE tb_ct_bitacora SET tb_ct_bitacora.tipo = 50 WHERE tb_ct_bitacora.id_bitacora=" + String.valueOf(rs4.getInt("tb_ct_bitacora.id_bitacora"));
															int res = getDatabase().executeNonQuery(queryUpdateSalidas); 
															if(res > 0){
																//SALIDA MODIFICADA A INTERMEDIA
															}
														}
													}

													/*LocalTime horaSalidaDeHorarioConTolerancia =  registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida().minusSeconds(tiempoTolerancia);
													horaSalidaDeHorarioConTolerancia = LocalTime.parse(horaSalidaDeHorarioConTolerancia.format(horaToleranciaFormatter));
													System.out.println("hora del horario de salida  con la tolerancia incluida:  " + horaSalidaDeHorarioConTolerancia);*/
													int ontime = registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida().compareTo(registroProcesar.getHoraRegistro().getHoraLT());
													if(ontime < 0){
														//													System.out.println("Salio tarde debe de salir a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- y Salio a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(17);
													}else if(ontime == 0){
														//													System.out.println("Salio a tiempo debe de salir a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- y Salio a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(15);
													}else if(ontime > 0){
														//													System.out.println("Salio temprano debe de salir a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- y Salio a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(20);
													}else{
														//													System.out.println("?? no puedo determinar si Salio temprano a tiempo o tarde :( " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(33);
													}
													buscaSalida = false;
												}else if (buscaSalidaIntermedia) {
													int tiempoToleranciaSalida2 = registroProcesar.getEmpleadoHorario().getHorario().getHoraRetardo().toSecondOfDay();
													LocalTime horaSalidaDeHorarioConTolerancia =  registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida2().plusSeconds(tiempoToleranciaSalida2);
													horaSalidaDeHorarioConTolerancia = LocalTime.parse(horaSalidaDeHorarioConTolerancia.format(horaToleranciaFormatter));
													System.out.println("hora del horario de salida  con la tolerancia incluida:  " + horaSalidaDeHorarioConTolerancia);
													int ontime = horaSalidaDeHorarioConTolerancia.compareTo(registroProcesar.getHoraRegistro().getHoraLT());
													if(ontime < 0){
														//													System.out.println("Salio tarde debe de salir a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- y Salio a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(17);
													}else if(ontime == 0){
														//													System.out.println("Salio a tiempo debe de salir a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- y Salio a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(15);
													}else if(ontime > 0){
														//													System.out.println("Salio temprano debe de salir a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- y Salio a las: " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(20);
													}else{
														//													System.out.println("?? no puedo determinar si Salio temprano a tiempo o tarde :( " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- " + registroProcesar.getHoraRegistro());
														registroProcesar.getTipoRegistro().setEstatusInt(33);
													}
													buscaSalida = false;
												}else{
													System.out.println("ERROR DESCONOCIDO 2358 en busca entrada salida con horario quebrado ");
												}
												registroProcesar.getTipoRegistro().setEstatusInt(34);//ESTATUS QUEBRADO


											}
										}else{
											//											System.out.println("Horario no laboral clave: " + claveHorario);
											registroProcesar.getTipoRegistro().setEstatusInt(35);											
										}
									}else{
										int tiempoTolerancia = registroProcesar.getEmpleadoHorario().getHorario().getHoraRetardo().toSecondOfDay();
										DateTimeFormatter horaToleranciaFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
										if(!horarioQuebrado){
											if(buscaEntrada){
												DateTimeFormatter fechaSQLFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
												String querySelectEntradaReg = "SELECT * FROM tb_ct_bitacora WHERE tb_ct_bitacora.fk_id_empleado= " + registroProcesar.getEmpleado().getSkuEmpleado() 
														+ " AND tb_ct_bitacora.Fecha = '" + registroProcesar.getFechaRegistro().getFechaLD().format(fechaSQLFormatter) + "'"
														+ " AND tb_ct_bitacora.Hora < '" + registroProcesar.getHoraRegistro().getHoraLT().format(horaToleranciaFormatter) + "'"
														+ " AND (tb_ct_bitacora.tipo >= 5 AND tb_ct_bitacora.tipo <= 10)"
														+ " AND tb_ct_bitacora.status = 1 ORDER BY tb_ct_bitacora.Hora DESC LIMIT 1";
												ResultSet rs3 = null;
												//												System.out.println("Query busca salida = " + querySelectEntradaReg);
												rs3 = getDatabase().executeQuery(querySelectEntradaReg);
												while(rs3.next()){
													buscaSalida = true;
													buscaEntrada = false;
													String queryUpdateEntradas = "UPDATE tb_ct_bitacora SET tb_ct_bitacora.tipo = 6 WHERE tb_ct_bitacora.id_bitacora=" + String.valueOf(rs3.getInt("tb_ct_bitacora.id_bitacora"));
													int res = getDatabase().executeNonQuery(queryUpdateEntradas); 
													if(res > 0){

													}
												}
											}
											if(buscaEntrada){												
												LocalTime horaEntradaDeHorarioConTolerancia =  registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada().getHoraLT().plusSeconds(tiempoTolerancia);
												horaEntradaDeHorarioConTolerancia = LocalTime.parse(horaEntradaDeHorarioConTolerancia.format(horaToleranciaFormatter));
												//												System.out.println("hora del horario de entrada con la tolerancia incluida:  " + horaEntradaDeHorarioConTolerancia);
												int ontime = horaEntradaDeHorarioConTolerancia.compareTo(registroProcesar.getHoraRegistro().getHoraLT());
												if(ontime < 0){
													//													System.out.println("Llego tarde: debe de llegar a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada() + " -- y llego a las: " + registroProcesar.getHoraRegistro());
													registroProcesar.getTipoRegistro().setEstatusInt(10);
												}else if(ontime == 0){
													//													System.out.println("llego a tiempo debe de llegar a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada() + " -- y llego a las: " + registroProcesar.getHoraRegistro());
													registroProcesar.getTipoRegistro().setEstatusInt(5);
												}else if(ontime > 0){

													if(registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada().getHoraLT().compareTo(registroProcesar.getHoraRegistro().getHoraLT()) > 0){
														registroProcesar.getTipoRegistro().setEstatusInt(7);
														System.out.println("llego temprano debe de llegar a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada().getHoraString() + " -- y llego a las: " + registroProcesar.getHoraRegistro().getHoraString());
													}else{
														registroProcesar.getTipoRegistro().setEstatusInt(8);
														System.out.println("llego con tolerancia debe de llegar a las:  " +  horaEntradaDeHorarioConTolerancia + " -- y llego a las: " + registroProcesar.getHoraRegistro().getHoraString());
													}

												}else{
													//													System.out.println("?? no puedo determinar si llego temprano a tiempo o tarde :(" + registroProcesar.getEmpleadoHorario().getHorario().getHoraEntrada() + " -- y llego a las: " + registroProcesar.getHoraRegistro());
													registroProcesar.getTipoRegistro().setEstatusInt(33);
												}
												buscaEntrada = false;
											}else if (buscaSalida) {												
												DateTimeFormatter fechaSQLFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
												String querySelectEntradaReg = "SELECT * FROM tb_ct_bitacora WHERE tb_ct_bitacora.fk_id_empleado= " + registroProcesar.getEmpleado().getIdEmpleado() 
														+ " AND tb_ct_bitacora.Fecha >= '" + diaEntradaParaDiaSiguiente.format(fechaSQLFormatter) + "'"														
														+ " AND (tb_ct_bitacora.tipo >= 15 AND tb_ct_bitacora.tipo <= 20)"
														+ " AND tb_ct_bitacora.status = 1 ORDER BY tb_ct_bitacora.Hora DESC";
												ResultSet rs4 = null;
//												System.out.println("Query busca salida = " + querySelectEntradaReg);
												rs4 = getDatabase().executeQuery(querySelectEntradaReg);
												if(rs4 != null){
													while(rs4.next()){
														String queryUpdateSalidas = "UPDATE tb_ct_bitacora SET tb_ct_bitacora.tipo = 50 WHERE tb_ct_bitacora.id_bitacora=" + String.valueOf(rs4.getInt("tb_ct_bitacora.id_bitacora"));
														int res = getDatabase().executeNonQuery(queryUpdateSalidas); 
														if(res > 0){

														}
													}
												}												
												/*LocalTime horaSalidaDeHorarioConTolerancia =  registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida().minusSeconds(tiempoTolerancia);
												horaSalidaDeHorarioConTolerancia = LocalTime.parse(horaSalidaDeHorarioConTolerancia.format(horaToleranciaFormatter));
												System.out.println("hora del horario de salida  con la tolerancia incluida:  " + horaSalidaDeHorarioConTolerancia);*/
												int ontime = horaSalidaDiaSiguiente.compareTo(registroProcesar.getHoraRegistro().getHoraLT());
												if(ontime < 0){
													//													System.out.println("Salio tarde debe de salir a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- y Salio a las: " + registroProcesar.getHoraRegistro());
													registroProcesar.getTipoRegistro().setEstatusInt(17);
												}else if(ontime == 0){
													//													System.out.println("Salio a tiempo debe de salir a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- y Salio a las: " + registroProcesar.getHoraRegistro());
													registroProcesar.getTipoRegistro().setEstatusInt(15);
												}else if(ontime > 0){
													//													System.out.println("Salio temprano debe de salir a las:  " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- y Salio a las: " + registroProcesar.getHoraRegistro());
													registroProcesar.getTipoRegistro().setEstatusInt(20);
												}else{
													//													System.out.println("?? no puedo determinar si Salio temprano a tiempo o tarde :( " + registroProcesar.getEmpleadoHorario().getHorario().getHoraSalida() + " -- " + registroProcesar.getHoraRegistro());
													registroProcesar.getTipoRegistro().setEstatusInt(33);
												}
												buscaSalida = false;
											}						

										}else{
											//											System.out.println("Horario quebrado!! falta trabajar en este clave: " + claveHorario);
											registroProcesar.getTipoRegistro().setEstatusInt(34);
										}

									}
								}else{
									//									System.out.println("Horario invalido!, Empleado: " + registroProcesar.getEmpleado().getIdEmpleado() 
									//											+ ", idHorario: " + registroProcesar.getEmpleadoHorario().getHorario().getIdHorario());
									registroProcesar.getTipoRegistro().setEstatusInt(36);
								}

							}//Termina ciclo while para los datos del horario

							registroProcesar.getStatusRegistro().setEstatusInt(1);
						}else{
							//							System.out.println("Sin horario!, Empleado: " + registroProcesar.getEmpleado().getIdEmpleado());
							registroProcesar.getTipoRegistro().setEstatusInt(37);
							registroProcesar.getStatusRegistro().setEstatusInt(2);
						}
						Calendar cal = Calendar.getInstance();						
						SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");	
						String formatted = format1.format(cal.getTime());
						String formatted2 = format2.format(cal.getTime());
						int resUpdate = -1;
						String queryUpdate = "UPDATE tb_ct_bitacora SET tipo = "+registroProcesar.getTipoRegistro().getEstatusInt()+", "
								+ "status = " + registroProcesar.getStatusRegistro().getEstatusInt() 
								+ ", fecha_actualizacion = '" + formatted 
								+ "', hora_actualizacion = '" + formatted2
								+ "', fk_id_usuario_actualizacion = " + user.getUserId()
								+ " WHERE id_bitacora = "+registroProcesar.getId()+";";
						resUpdate = getDatabase().executeNonQuery(queryUpdate);
						if(resUpdate > 0){
							//							System.out.println("registro procesado!");
						}else{
//							System.out.println("registro no procesado!");
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
					Lista.add(registroProcesar);
					//					System.out.println("");
				}//Termina ciclo while para las incidencias
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

		return Lista;

	}
	
	/**
	 * 
	 * @return Vector<PermisoDTO>
	 */
	public Vector<PermisoDTO> selectPermisos(){
		Vector<PermisoDTO> permisos = new Vector<PermisoDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT * FROM tb_ct_permisos"
					+ "	LEFT JOIN tb_empleados ON tb_ct_permisos.fk_id_empleado = tb_empleados.id_empleado"
					+ "	LEFT JOIN sec_users AS tb_sec_user_1 ON tb_ct_permisos.fk_id_usuario_creacion = tb_sec_user_1.id_user"
					+ "	LEFT JOIN sec_users AS tb_sec_user_2 ON tb_ct_permisos.fk_id_usuario_ejecucion = tb_sec_user_2.id_user"
					+ "	LEFT JOIN tb_tipos_estatus ON tb_ct_permisos.fk_id_estatus = tb_tipos_estatus.id_tipo_estatus"
					+ "	LEFT JOIN tb_documentos AS tb_docs ON tb_ct_permisos.fk_id_documento = tb_docs.id_documento"
					+ "	LEFT JOIN tb_tipos_estatus AS tb_motivos ON tb_ct_permisos.fk_id_motivo = tb_motivos.id_tipo_estatus"
					+ "	LEFT JOIN tb_ct_bitacora ON tb_ct_permisos.fk_bitacora = tb_ct_bitacora.id_bitacora";
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			try {
				if(rs != null){
					while(rs.next()){
						PermisoDTO permiso = getHerramientasRS().inicializaPermisoDTO(rs);
						if(permiso != null){
							permisos.add(permiso);
						}else{
							System.out.println("permiso == null en " + this.getClass().getSimpleName());
						}
					}//termina while
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					System.out.println("rs == null en " + this.getClass().getSimpleName());
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
		return permisos;

	}
	
	/**
	 * 
	 * @return Vector<PermisoDTO>
	 */
	public Vector<PermisoDTO> selectPermisosFiltros(HashMap<String, String> map){
		Vector<PermisoDTO> permisos = new Vector<PermisoDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT * FROM tb_ct_permisos"
					+ "	LEFT JOIN tb_empleados ON tb_ct_permisos.fk_id_empleado = tb_empleados.id_empleado"
					+ "	LEFT JOIN sec_users AS tb_sec_user_1 ON tb_ct_permisos.fk_id_usuario_creacion = tb_sec_user_1.id_user"
					+ "	LEFT JOIN sec_users AS tb_sec_user_2 ON tb_ct_permisos.fk_id_usuario_ejecucion = tb_sec_user_2.id_user"
					+ "	LEFT JOIN tb_tipos_estatus ON tb_ct_permisos.fk_id_estatus = tb_tipos_estatus.id_tipo_estatus"
					+ "	LEFT JOIN tb_tipos_estatus AS tb_motivos ON tb_ct_permisos.fk_id_motivo = tb_motivos.id_tipo_estatus"
					+ "	LEFT JOIN tb_ct_bitacora ON tb_ct_permisos.fk_bitacora = tb_ct_bitacora.id_bitacora";
			String condiciones = queryFiltrosPermisos(map);
			if(!map.isEmpty()){
				if(condiciones.length() > 0){
					query += " WHERE ";
					query += condiciones;
				}else{
					query += ";";
				}
			}else{
				query += ";";
			}
			System.out.println(query);
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(rs != null){
					while(rs.next()){
						PermisoDTO permiso = getHerramientasRS().inicializaPermisoDTO(rs);
						if(permiso != null){
							permisos.add(permiso);
						}else{
							System.out.println("permiso == null en " + this.getClass().getSimpleName());
						}
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					System.out.println("rs == null en " + this.getClass().getSimpleName());
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
		return permisos;

	}
	
	
	
	/**
	 * Evalua si el valor del mapa esta vacio
	 * @param map
	 * @param key
	 * @return
	 */
	private String valueToStringOrEmpty(HashMap<String, ?> map, String key) {
	    Object value = map.get(key);
	    return value == null ? "" : value.toString();
	}
	
	private String queryFiltrosPermisos(HashMap<String, ?> map) {
		String query = "";
		if(!valueToStringOrEmpty(map, "cveJustificante").equals("") ){
			query += " tb_ct_permisos.id_permiso LIKE  '%" + (String) map.get("cveJustificante") + "%'";
			if(!valueToStringOrEmpty(map, "idEmp").equals("")){
				query += "	AND tb_ct_permisos.fk_id_empleado = " + (String) map.get("idEmp");
			}
		}else{
			if(!valueToStringOrEmpty(map, "idEmp").equals("")){
				query += " tb_ct_permisos.fk_id_empleado = " + (String) map.get("idEmp");
			}else{
				System.out.println("Sin filtros");
			}
		}
		return query;
	}
	
	private String queryFiltrosBitacoraAsistencia(HashMap<String, ?> map) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",  Locale.US);
		dateFormat.setTimeZone(TimeZone.getDefault());
		String query = "";
		if(!valueToStringOrEmpty(map, "fechaDe").equals("")){
            Date fechaDe = null;
			try {
				fechaDe = dateFormat.parse((String) map.get("fechaDe"));
				
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			if(fechaDe != null){
				java.sql.Date fDe =new java.sql.Date(fechaDe.getTime());
				
				query += "	AND Fecha >= '" + fDe.toString() + "'";
				
			}else{
				System.out.print("fecha de == null: ");
			}
            
			if(!valueToStringOrEmpty(map, "fechaA").equals("")){
				Date fechaA = null;
				try {
					fechaA = dateFormat.parse((String) map.get("fechaA"));
				} catch (ParseException e) {					
					e.printStackTrace();
				}
				
                java.sql.Date fA =new java.sql.Date(fechaA.getTime());
				query += " AND Fecha <= '" + fA.toString() + "'";
			}else{
				System.out.println("sin filtros 0");
			}
			
		}else{
			if(!valueToStringOrEmpty(map, "fechaA").equals("")){ 
				Date fechaA = null;
				try {
					fechaA = dateFormat.parse((String) map.get("fechaA"));
				} catch (ParseException e) {					
					e.printStackTrace();
				}		
				
                java.sql.Date fA = new java.sql.Date(fechaA.getTime());
                
				query += "	AND Fecha <= '" + fA.toString() + "'";
				
			}else{
				System.out.println("sin filtros de fecha 1");
			}
		}
		
		if(!valueToStringOrEmpty(map, "skuEmpleado").equals("") ){
			query += "	AND tb_empleados.Sku_empleado = '" + (String) map.get("skuEmpleado") + "'";
		}
		
		if(!valueToStringOrEmpty(map, "nombreEmpleado").equals("")){
			query += "	AND CONCAT(tb_empleados.Nombre, ' ' , tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno) LIKE '%" + (String) map.get("nombreEmpleado") + "%'";
		}
		if(!valueToStringOrEmpty(map, "tipoReg").equals("")){
			query += "	AND tb2.id_tipo_estatus = " + (String) map.get("tipoReg") + " ";
		}
		
		return query;
	}
	
private String queryFiltrosBitacoraAsistenciaTipoReg(HashMap<String, ?> map) {
		String query = "";				
		if(!valueToStringOrEmpty(map, "tipoReg").equals("")){
			query += "	AND tb2.id_tipo_estatus = " + (String) map.get("tipoReg") + " ";
		}
		
		return query;
	}
	
	/**
	 * 
	 * @return int
	 */
	public int selectUltimoSkuPermiso(){
		int skuPermiso = 0;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT MAX(id_permiso) AS ultimo_id FROM tb_ct_permisos "
					+ ";";

			System.out.println(query);
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(rs != null){
					while(rs.next()){
						skuPermiso = rs.getInt("ultimo_id");
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					System.out.println("rs == null en " + this.getClass().getSimpleName());
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
		return skuPermiso;
	}
	
	/**
	 * INSERTA EL HORARIO DE LA TABLA PERMISOS  17/03/2017
	 * @param permisoNuevo
	 * @return int
	 */
	public int insertaNuevoPermiso(PermisoDTO permiso){		
		int res = -1;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter = formatter.withLocale( Locale.US );// Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
    	LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));	
    	permiso.setFechaCreacionPermiso(date);
    	formatter = DateTimeFormatter.ofPattern("HH:mm:ss");	    	
    	LocalTime time = LocalTime.parse(LocalTime.now().format(formatter));
    	permiso.setHoraCreacionPermiso(time);
    	
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "tb_ct_permisos";
			String query = "INSERT INTO tb_ct_permisos ";
			query +="(";
			query += tabla + ".fk_id_empleado, "
					+ tabla + ".descripcion, "
					+ tabla + ".fecha_de, "
					+ tabla + ".fecha_a, ";
			if(permiso.getHoraDePermiso().getHoraLT() != null){
				query += tabla + ".hora_de, "
					+ tabla + ".hora_a, ";
			}
			query += tabla +".fk_id_motivo, "
					+ tabla + ".fecha_creacion, "
					+ tabla + ".hora_creacion, "
					+ tabla + ".fk_id_usuario_creacion, "
					+ tabla + ".fk_id_estatus"
					+ tabla + ".fk_id_documento"
							+ ") "
					+ "VALUES"
					+ "("
					    + permiso.getEmpledoDTO().getIdEmpleado() + ", "
						+ "'"+ permiso.getDescripcionPermiso() + "', "
						+ "'"+ permiso.getFechaDePermiso().getFechaLD() + "', "
						+ "'"+ permiso.getFechaAPermiso().getFechaLD() + "', ";
						if(permiso.getHoraDePermiso().getHoraLT() != null){
							query += "'"+ permiso.getHoraDePermiso().getHoraLT() + "', "
								+"'"+ permiso.getHoraAPermiso().getHoraLT() + "', ";
						}
						query += permiso.getMotivoDTO().getIdTipoEstatus() + ", "
						+ "'" + permiso.getFechaCreacionPermiso() + "', "
						+ "'" + permiso.getHoraCreacionPermiso() + "', "
						+ permiso.getUsuarioCreacionDTO().getUserId() + ", "
						+ permiso.getEstatusDTO().getIdTipoEstatus() + ", "
						+ permiso.getArchivoTarjeta().getIdDocumento()
					+ ");";			
			try {
				System.out.println(query);
				res = getDatabase().executeNonQuery(query);				
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
		return res;

	}
	
	
	/**
	 * ACTUALIZA EL PERMISO DE LA TABLA HORARIOS EN BASE A SU ID 
	 * @param permiso
	 * @return int
	 */
	public int actualizarPermiso(PermisoDTO permiso){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			formatter = formatter.withLocale( Locale.US );// Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
	    	LocalDate date = LocalDate.parse(LocalDate.now().format(formatter));	
	    	permiso.getFechaActualizacion().setFechaLD(date);
	    	formatter = DateTimeFormatter.ofPattern("HH:mm:ss");	    	
	    	LocalTime time = LocalTime.parse(LocalTime.now().format(formatter));
	    	permiso.getHoraActualizacion().setHoraLT(time);
			String tabla = "tb_ct_permisos";			
			String query= "UPDATE tb_ct_permisos SET "
					+ " "
					+ tabla +".fk_id_empleado = "+permiso.getEmpledoDTO().getIdEmpleado()+ ", "
					+ tabla +".descripcion = '"+permiso.getDescripcionPermiso()+ "', "
					+ tabla +".fecha_de = '" +permiso.getFechaDePermiso().getFechaLD()+ "', "
					+ tabla +".fecha_a = '" +permiso.getFechaAPermiso().getFechaLD() + "', ";
					if(permiso.getHoraDePermiso().getHoraLT() != null){
						query += tabla +".hora_de = '" +permiso.getHoraDePermiso().getHoraLT() + "', "
							+ tabla +".hora_a = '" +permiso.getHoraAPermiso().getHoraLT() + "', ";
					}
					query += tabla +".fk_id_motivo = " +permiso.getMotivoDTO().getIdTipoEstatus() + ", "
					+ tabla +".fecha_actualizacion = '" +permiso.getFechaActualizacion().getFechaLD()+ "', "
					+ tabla +".hora_actualizacion = '" +permiso.getHoraActualizacion().getHoraLT() + "', "
					+ tabla +".fk_id_usuario_actualizacion = " +permiso.getUsuarioActualizacionDTO().getUserId() + ", "
					+ tabla +".fk_id_estatus = " + permiso.getEstatusDTO().getIdTipoEstatus() + ", "
					+ tabla +".fk_id_documento = " + permiso.getArchivoTarjeta().getIdDocumento();
			query += " WHERE " 
			+tabla+".id_permiso = " + permiso.getIdPermiso()
			+";";
			
			try {
				System.out.println(query);
				res = getDatabase().executeNonQuery(query);				
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
		return res;

	}
	
	/**
	 * SELECCIONA LOS REGISTROS DE BITACORA DEL EMPLEADO ESPECIFICADO EN LA FECHA ESPECIFICADA [Javier]20170710
	 * @param sku, fecha
	 * @return Vector<BitacoraDTO>
	 */
	public Vector<BitacoraDTO> selectRegistrosBitacoraDTOEmpleadoEnFecha(int skuEmpleado, String fecha){		
		Vector<BitacoraDTO> registrosEnFecha = null;
		/*if(database.openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());*/
			String query = "SELECT * FROM tb_ct_bitacora";
			query += " LEFT JOIN tb_tipos_estatus AS tb1 ON tb_ct_bitacora.`status` = tb1.status_int"
					+ " LEFT JOIN tb_tipos_estatus AS tb2 ON tb_ct_bitacora.`tipo` = tb2.status_int "
					+ " WHERE tb_ct_bitacora.fk_id_empleado ="+skuEmpleado + " AND tb_ct_bitacora.Fecha = '"+fecha+"' AND tb1.aplicacion = 'bitacora'	AND tb2.aplicacion = 'bitacora'	";
			
			try {
//				System.out.println(query);
				ResultSet rs;
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					registrosEnFecha = new Vector<BitacoraDTO>();
					while (rs.next()) {
						BitacoraDTO registro = getHerramientasRS().inicializaBitacoraDTO(rs);
						if(registro != null){
							registrosEnFecha.add(registro);
						}else{
							System.out.println("ERROR EN selectRegistrosBitacoraDTOEmpleadoEnFecha " + this.getClass().getSimpleName());
						}
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == null en " + this.getClass().getSimpleName());
				}
			} catch (SQLException e) {				
				e.printStackTrace();
			}

			/*if (database.closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}*/
		/*}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}*/
		return registrosEnFecha;

	}
	
	
	/**
	 * SELECCIONA LOS REGISTROS DE BITACORA DEL EMPLEADO ESPECIFICADO EN LA FECHA ESPECIFICADA [Javier]20170710
	 * @param sku, fecha
	 * @return Vector<BitacoraDTO>
	 */
	public BitacoraDTO selectRegistroBitacoraDTOEmpleadoEnFechaConConexionAbierta(int skuEmpleado, String fecha){		
		BitacoraDTO registroEnFecha = null;
		/*if(database.openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());*/
			String query = "SELECT * FROM tb_ct_bitacora";
			query += " LEFT JOIN tb_tipos_estatus AS tb1 ON tb_ct_bitacora.`status` = tb1.status_int"
					+ " LEFT JOIN tb_tipos_estatus AS tb2 ON tb_ct_bitacora.`tipo` = tb2.status_int "
					+ " WHERE tb_ct_bitacora.fk_id_empleado ="+skuEmpleado + " AND tb_ct_bitacora.Fecha = '"+fecha+"' AND tb1.aplicacion = 'bitacora'	AND tb2.aplicacion = 'bitacora'	ORDER BY tb_ct_bitacora.Hora DESC LIMIT 1";
			
			try {
//				System.out.println(query);
				ResultSet rs;
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					
					while (rs.next()) {
						BitacoraDTO registro = getHerramientasRS().inicializaBitacoraDTO(rs);
						if(registro != null){
							registroEnFecha = registro;
						}else{
							System.out.println("ERROR EN selectRegistrosBitacoraDTOEmpleadoEnFecha " + this.getClass().getSimpleName());
						}
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == null en " + this.getClass().getSimpleName());
				}
			} catch (SQLException e) {				
				e.printStackTrace();
			}

			/*if (database.closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}*/
		/*}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}*/
		return registroEnFecha;
	}
	
	/**
	 * SELECCIONA LOS REGISTROS DE BITACORA DEL EMPLEADO ESPECIFICADO EN LA FECHA ESPECIFICADA [Javier]20170710
	 * @param sku, fecha
	 * @return Vector<BitacoraDTO>
	 */
	public BitacoraDTO selectRegistroBitacoraDTOEmpleadoEnFecha(int skuEmpleado, String fecha){		
		BitacoraDTO registroEnFecha = null;
		if(database.openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String query = "SELECT * FROM tb_ct_bitacora";
			query += " LEFT JOIN tb_tipos_estatus AS tb1 ON tb_ct_bitacora.`status` = tb1.status_int"
					+ " LEFT JOIN tb_tipos_estatus AS tb2 ON tb_ct_bitacora.`tipo` = tb2.status_int "
					+ " WHERE tb_ct_bitacora.fk_id_empleado ="+skuEmpleado + " AND tb_ct_bitacora.Fecha = '"+fecha+"' AND tb1.aplicacion = 'bitacora'	AND tb2.aplicacion = 'bitacora'	ORDER BY tb_ct_bitacora.Hora DESC LIMIT 1";
			
			try {
//				System.out.println(query);
				ResultSet rs;
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					
					while (rs.next()) {
						BitacoraDTO registro = getHerramientasRS().inicializaBitacoraDTO(rs);
						if(registro != null){
							registroEnFecha = registro;
						}else{
							System.out.println("ERROR EN selectRegistrosBitacoraDTOEmpleadoEnFecha " + this.getClass().getSimpleName());
						}
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == null en " + this.getClass().getSimpleName());
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
		return registroEnFecha;
	}
	
	/**
	 * ACTUALIZA EL PERMISO DE LA TABLA HORARIOS EN BASE A SU ID 
	 * @param permiso
	 * @return int
	 */
	private Vector<BitacoraDTO> selectRegistrosBitacoraDTOEmpleadoEnFecha(int skuEmpleado, String fecha, HashMap<String, String> map){		
		Vector<BitacoraDTO> registrosEnFecha = null;
		/*if(database.openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());*/
			String query = "SELECT * FROM tb_ct_bitacora";
			query += " LEFT JOIN tb_tipos_estatus AS tb1 ON tb_ct_bitacora.`status` = tb1.status_int"
					+ " LEFT JOIN tb_tipos_estatus AS tb2 ON tb_ct_bitacora.`tipo` = tb2.status_int "
					+ " WHERE tb_ct_bitacora.fk_id_empleado = "+skuEmpleado+" AND tb_ct_bitacora.Fecha = '"+fecha+"' AND tb1.aplicacion = 'bitacora' AND tb2.aplicacion = 'bitacora'	";
			if(!HerramientasResultSet.valueToStringOrEmpty(map, "tipoReg").equals("")){
				if(!map.get("tipoReg").equals("113")){
					query += queryFiltrosBitacoraAsistenciaTipoReg(map);
				}
			}
				
			try {
//				System.out.println(query);
				ResultSet rs;
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					registrosEnFecha = new Vector<BitacoraDTO>();
					while (rs.next()) {
						BitacoraDTO registro = getHerramientasRS().inicializaBitacoraDTO(rs);
						if(registro != null){
							registrosEnFecha.add(registro);
						}else{
							System.out.println("ERROR EN selectRegistrosBitacoraDTOEmpleadoEnFecha " + this.getClass().getSimpleName());
						}
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == null en " + this.getClass().getSimpleName());
				}
			} catch (SQLException e) {				
				e.printStackTrace();
			}

			/*if (database.closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}*/
		/*}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}*/
		return registrosEnFecha;

	}
	
	/**
	 * 
	 * @param empleado
	 * @param fecha
	 * @return
	 */
	private PermisoDTO selectPermisoDTOPorEmpleadoPorFechaConConexionAbierta(EmpleadoDTO empleado, String fecha){
		PermisoDTO permisoDTO = null;
		String query = "SELECT * from tb_ct_permisos where tb_ct_permisos.fk_id_empleado = "+empleado.getIdEmpleado()+" AND '"+fecha+"' BETWEEN tb_ct_permisos.fecha_de AND tb_ct_permisos.fecha_a AND tb_ct_permisos.fk_id_estatus = 7";
		try {
//			System.out.println(query);
			ResultSet rs;
			rs = getDatabase().executeQuery(query);
			if(rs != null){				
				while (rs.next()) {
					permisoDTO = getHerramientasRS().inicializaPermisoSimpleDTO(rs);
					permisoDTO.setEmpledoDTO(empleado);
				}
				try{
					rs.close();
				}catch (Exception e) {
					e.printStackTrace();
				}	
			}else{
				System.out.println("rs == null en " + this.getClass().getSimpleName());
			}
		} catch (SQLException e) {				
			e.printStackTrace();
		}
			
		return permisoDTO;
	}

	
	
	
}//Termina clase
