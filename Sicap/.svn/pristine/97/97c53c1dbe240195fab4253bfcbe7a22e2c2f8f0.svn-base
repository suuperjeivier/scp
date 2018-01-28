package dao.empleado;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;


import dao.DatabaseGateway;
import dto.empleado.EmpleadoDTO;
import herramientas.herrramientasrs.HerramientasResultSet;

public class EmpleadoDAO {
	
	
	private DatabaseGateway database = null;
	private HerramientasResultSet herramientasRS = null;

	public EmpleadoDAO(){
		if(this.getDatabase() == null){
			this.setDatabase(new DatabaseGateway());
		}else{
			System.out.println("database != null en " + this.getClass().getSimpleName() );
		}
		InitializeComponents();
	}
	
	public EmpleadoDAO(DatabaseGateway database){
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
		if (herramientasRS == null){
			herramientasRS = new HerramientasResultSet();
		}else{
			System.out.println("herramientasRS != null en " + this.getClass().getSimpleName() );
		}
	}
	
	public int insertaNuevoEmpleado(EmpleadoDTO empleadoNuevo){		
		int res = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "tb_empleados";
			String query= "INSERT INTO " + tabla + " "
					+ "("
						+ tabla + ".Sku_empleado, "
						+ tabla + ".Nombre, "
						+ tabla + ".Ap_paterno, "
						+ tabla + ".Ap_materno, "
						+ tabla + ".fk_genero, "
						+ tabla + ".fk_e_c, "
						+ tabla + ".F_nac, "
						+ tabla + ".fk_grupo_sanguineo, "
						+ tabla + ".fk_estado, "
						+ tabla + ".fk_municipio, "
						+ tabla + ".fk_colonia,"
						+ tabla + ".Codigo_postal, "
						+ tabla + ".Calle, "
						+ tabla + ".NumeroExt, "
						+ tabla + ".NumeroInt, "
						+ tabla + ".Tel_fijo, "
						+ tabla + ".Tel_movil, "
						+ tabla + ".Correo_e, "
						+ tabla + ".Curp, "
						+ tabla + ".Rfc, "
						+ tabla + ".Fk_especialidad, "
						+ tabla + ".Fecha_alta, "
						+ tabla + ".c_trunca, "
						+ tabla + ".fk_g_p, "
						+ tabla + ".fk_p, "
						+ tabla + ".fk_nivel_academico, "
						+ tabla + ".fk_estatus"
					+ ") VALUES"
					+ "("
						+ "'" + empleadoNuevo.getSkuEmpleado() 
						+ "', '" + empleadoNuevo.getNombreEmpleado() 
						+ "', '" + empleadoNuevo.getApPaternoEmpleado() 
						+ "', '" + empleadoNuevo.getApMaternoEmpleado() 
						+ "', '" + empleadoNuevo.getGeneroEmpleadoDTO().getIdGenero()
						+ "', '" + empleadoNuevo.getEdoCivilEmpladoDTO().getIdEstadoCivil()
						+ "', '" + Date.valueOf(empleadoNuevo.getFechaNacimientoEmpleado())
						+ "', '" + empleadoNuevo.getGrupoSanguineoEmpleadoDTO().getIdGrupoSanguineo()
						+ "', '" + empleadoNuevo.getEntidadFederativaDomicilioEmpleadoDTO().getIdEntidadFederativa()
						+ "', '" + empleadoNuevo.getMunicipioDomicilioEmpleadoDTO().getIdMunicipio()
						+ "', '" + empleadoNuevo.getColoniaDomicilioEmpleadoDTO().getIdColonia()
						+ "', '" + empleadoNuevo.getCodigoPostalDomicilioEmpleado()
						+ "', '" + empleadoNuevo.getCalleDomicilioEmpleadoDTO().getNombreCalle() 
						+ "', '"+ empleadoNuevo.getNoExtDomicilioEmpleado() 
						+ "', '" + empleadoNuevo.getNoIntDomicilioEmpleado()
						+ "', '" + empleadoNuevo.getTelFijoEmpleado()
						+ "', '" + empleadoNuevo.getTelMovilEmpleado()
						+ "', '" + empleadoNuevo.getCorreoElectronicoEmpleado()
						+ "', '" + empleadoNuevo.getCurpEmpleado()
						+ "', '" + empleadoNuevo.getRfcEmpleado()
						+ "', '" + empleadoNuevo.getEspecialidadDTO().getIdEspecialidad()
						+ "', '" + Date.valueOf(empleadoNuevo.getFechaAltaEmpleado())
						+ "', '" + (empleadoNuevo.getCarreraTruncaEmpleado().getEstatusInt() == 1 ? 9 : 10)
						+ "', '" + empleadoNuevo.getGradoPeridoEscolarEmpleadoDTO().getGradoDTO().getIdGradoEscolar()
						+ "', '" + empleadoNuevo.getPeridoEscolarEmpleadoDTO().getIdPeriodoEscolar()
						+ "', '" + empleadoNuevo.getNivelAcademicoEmpleadoDTO().getIdNivelAcademico()
						+ "', '1'"
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
	
	public int actualizarEmpleado(EmpleadoDTO empleadoNuevo){		
		int editar = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			String tabla = "tb_empleados";
			String query= "UPDATE " + tabla + " " 
						+ " SET "
						+ tabla + ".Sku_empleado = " + empleadoNuevo.getSkuEmpleado() + ","
						+ tabla + ".Nombre = '" + empleadoNuevo.getNombreEmpleado() + "',"
						+ tabla + ".Ap_paterno = '" + empleadoNuevo.getApPaternoEmpleado() + "',"
						+ tabla + ".Ap_materno = '" + empleadoNuevo.getApMaternoEmpleado() + "',"
						+ tabla + ".fk_genero = " + empleadoNuevo.getGeneroEmpleadoDTO().getIdGenero() + ","
						+ tabla + ".fk_e_c = " + empleadoNuevo.getEdoCivilEmpladoDTO().getIdEstadoCivil() + ","
						+ tabla + ".F_nac = '" + Date.valueOf(empleadoNuevo.getFechaNacimientoEmpleado()) + "',"
						+ tabla + ".fk_grupo_sanguineo = " + empleadoNuevo.getGrupoSanguineoEmpleadoDTO().getIdGrupoSanguineo() + ","
						+ tabla + ".fk_estado = " + empleadoNuevo.getEntidadFederativaDomicilioEmpleadoDTO().getIdEntidadFederativa() + ","
						+ tabla + ".fk_municipio = " + empleadoNuevo.getMunicipioDomicilioEmpleadoDTO().getIdMunicipio() + ","
						+ tabla + ".fk_colonia = " + empleadoNuevo.getColoniaDomicilioEmpleadoDTO().getIdColonia() + ","
						+ tabla + ".Codigo_postal = " + empleadoNuevo.getCodigoPostalDomicilioEmpleado() + ","
						+ tabla + ".Calle = '" + empleadoNuevo.getCalleDomicilioEmpleadoDTO().getNombreCalle() + "',"
						+ tabla + ".NumeroExt = '" + empleadoNuevo.getNoExtDomicilioEmpleado() + "',"
						+ tabla + ".NumeroInt = '" + empleadoNuevo.getNoIntDomicilioEmpleado() + "',"
						+ tabla + ".Tel_fijo = '" + empleadoNuevo.getTelFijoEmpleado() + "',"
						+ tabla + ".Tel_movil = '" + empleadoNuevo.getTelMovilEmpleado() + "',"
						+ tabla + ".Correo_e = '" + empleadoNuevo.getCorreoElectronicoEmpleado() + "',"
						+ tabla + ".Curp = '" + empleadoNuevo.getCurpEmpleado() + "',"
						+ tabla + ".Rfc = '" + empleadoNuevo.getRfcEmpleado() + "',"
						+ tabla + ".Fk_especialidad = " + empleadoNuevo.getEspecialidadDTO().getIdEspecialidad() + ","
						+ tabla + ".Fecha_alta = '" + Date.valueOf(empleadoNuevo.getFechaAltaEmpleado()) + "',"
						+ tabla + ".c_trunca = " + (empleadoNuevo.getCarreraTruncaEmpleado().getEstatusInt() == 1 ? 9 : 10) + ","
						+ tabla + ".fk_g_p = " + empleadoNuevo.getGradoPeridoEscolarEmpleadoDTO().getGradoDTO().getIdGradoEscolar() + ","
						+ tabla + ".fk_p = " + empleadoNuevo.getPeridoEscolarEmpleadoDTO().getIdPeriodoEscolar() + ","
						+ tabla + ".fk_nivel_academico = " + empleadoNuevo.getNivelAcademicoEmpleadoDTO().getIdNivelAcademico() + ","
						+ tabla + ".fk_estatus = 1"
						+ " WHERE "
						+ tabla + ".id_empleado = " + empleadoNuevo.getIdEmpleado();
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
	
	public int selectIdEmpleadoPorNombre(String nombreEmpleado){
		int idEmpleadp = -1;
		if(nombreEmpleado != null && !nombreEmpleado.equals("") && nombreEmpleado.length() > 0){
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				ResultSet rs = null;
				String query = "SELECT * FROM tb_empleados WHERE CONCAT(tb_emplados.Nombre, ' ', tb_emplados.Ap_paterno, ' ', tb_emplados.Ap_materno) LIKE '%" + nombreEmpleado + "%';";
				try {
					rs = getDatabase().executeQuery(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if(rs != null){
					while(rs.next()){					
						try {
							idEmpleadp = rs.getInt("tb_emplados.id_empleado");
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
						System.out.println("rs == nullo en selectIdEmpleadoPorNombre");
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
		}else{
			System.out.println("parametro nombreEmpleado nulo");
		}
		return idEmpleadp;
	}
	
	public EmpleadoDTO selectIdEmpleadoPorNombre(EmpleadoDTO empleado){	
		if(empleado != null){
			if(empleado.getNombreEmpleado() != null && !empleado.getNombreEmpleado().equals("") && empleado.getNombreEmpleado().length() > 0){
				if(getDatabase().openDatabase()){
					System.out.println("conexion abierta en " + this.getClass().getSimpleName());
					ResultSet rs = null;
					String query = "SELECT id_empleado FROM tb_empleados "
							+ "WHERE CONCAT(tb_emplados.Nombre, ' ', tb_emplados.Ap_paterno, ' ', tb_emplados.Ap_materno) "
							+ "LIKE '%" + empleado.getNombreEmpleado() + "%';";
					try {
						rs = getDatabase().executeQuery(query);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						if(rs != null){
							while(rs.next()){					
								try {
									empleado.setIdEmpleado(rs.getInt("tb_emplados.id_empleado"));
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
							System.out.println("rs == nullo en selectIdEmpleadoPorNombre");
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
			}else{
				System.out.println("parametro nombreEmpleado nulo");
			}
		}else{
			System.out.println("parametro empleado de tipo EmpleadoDTO nulo");
		}
		return empleado;	
	}
	
	public EmpleadoDTO selectEmpleadoPorNombre(EmpleadoDTO empleado){	
		if(empleado != null){
			if(empleado.getNombreEmpleado() != null && !empleado.getNombreEmpleado().equals("") && empleado.getNombreEmpleado().length() > 0){
				if(getDatabase().openDatabase()){
					System.out.println("conexion abierta en " + this.getClass().getSimpleName());
					ResultSet rs = null;
					String query = "SELECT * FROM tb_empleados "
							+ "WHERE CONCAT(tb_empleados.Nombre, ' ', tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno) "
							+ "LIKE '%" + empleado.getNombreEmpleado() + "%';";
					try {
						rs = getDatabase().executeQuery(query);
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
							try{
								rs.close();
							}catch (Exception e) {
								e.printStackTrace();
							}
						}else{
							System.out.println("rs == nullo en selectEmpleadoPorNombre");
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
			}else{
				System.out.println("parametro nombreEmpleado nulo");
			}
		}else{
			System.out.println("parametro empleado de tipo EmpleadoDTO nulo");
		}
		return empleado;	
	}
	
	/**
	 * Selecciona el empleado deseado en base al id de un EmpleadoDTO
	 * @param empleado
	 * @return EmpleadoDTO
	 */
	public EmpleadoDTO selectEmpleadoPorId(EmpleadoDTO empleado){	
		if(empleado != null){
			if(empleado.getIdEmpleado() > -1 ){
				if(getDatabase().openDatabase()){
					System.out.println("conexion abierta en " + this.getClass().getSimpleName());
					ResultSet rs = null;
					String query = "SELECT * FROM tb_empleados "
							+ "WHERE id_empleado = "+ empleado.getIdEmpleado() +";";
					try {
						rs = getDatabase().executeQuery(query);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					try {
						if(rs != null){
							while(rs.next()){					
								try {
									empleado.setIdEmpleado(rs.getInt("tb_empleados.id_empleado"));
									empleado.setNombreEmpleado(rs.getString("tb_empleados.Nombre"));
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
							System.out.println("rs == nullo en selectEmpleadoPorId");
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
			}else{
				System.out.println("parametro idEmpleado nulo");
			}
		}else{
			System.out.println("parametro empleado de tipo EmpleadoDTO nulo");
		}
		return empleado;	
	}
	
	public int nuevoSkuEmpleado(){		
		int nuevoSku = -1;
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT MAX(tb_empleados.Sku_empleado) + 1 AS sku_nuevo  from tb_empleados";
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
				while(rs.next()){
					try {
						nuevoSku = rs.getInt("sku_nuevo");
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
				System.out.println("rs == nullo en nuevoSkuEmpleado");
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
		return nuevoSku;
	}
	
	/**
	 * SELECCIONA EL EMPLEADO DESEADO EN BASE AL ID DEL EMPLEADO EN INT [JAVIER] 01022017
	 * @param idEmpleado
	 * @return EmpleadoDTO
	 */
	public EmpleadoDTO selectEmpleadoPorId(int idEmpleado){	
		EmpleadoDTO empleado = null;
		if(idEmpleado > -1 ){
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				ResultSet rs = null;
				String query = "SELECT * FROM tb_empleados "
						+ "LEFT JOIN tb_especialidades_academicas ON tb_empleados.Fk_especialidad = tb_especialidades_academicas.id_especialidad  "
						+ "LEFT JOIN tb_niveles_academicos ON tb_empleados.fk_nivel_academico = tb_niveles_academicos.id_escolaridad  "
						+ "LEFT JOIN tb_tipos_estatus ON tb_empleados.fk_estatus = tb_tipos_estatus.id_tipo_estatus "
						+ "WHERE "
						+ "id_empleado = "+ idEmpleado + ";";
				try {
					rs = getDatabase().executeQuery(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if(rs != null){
						while(rs.next()){					
							try {
								empleado = herramientasRS.inicializaEmpleadoDTO(rs);

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
						System.out.println("rs == nullo en selectEmpleadoPorId");
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
		}else{
			System.out.println("parametro idEmpleado -1");
		}
		return empleado;
	}
	
	
	public Vector<EmpleadoDTO> selectEmpleadoPorNombre(String nombre, int numLimit, int idUltimoEmpleado){	
		Vector<EmpleadoDTO> v = new Vector<EmpleadoDTO>();
		if(nombre != null ){
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				ResultSet rs = null;
				String query = "SELECT *,estatus_carrera_trunca.status_int AS carrera_trunca,estatus_empleado.status_int AS estatus_emp FROM tb_empleados  "
						+ " LEFT JOIN tb_generos ON tb_empleados.fk_genero = tb_generos.id_genero "
						+ " LEFT JOIN tb_estados_civiles ON tb_empleados.fk_e_c = tb_estados_civiles.id_estado_civil "
						+ " LEFT JOIN tb_grupos_sanguineos ON tb_empleados.fk_grupo_sanguineo = tb_grupos_sanguineos.id_grupo_sanguineo "
						+ " LEFT JOIN tb_catalogo_estados ON tb_empleados.fk_estado = tb_catalogo_estados.id_estado "
						+ " LEFT JOIN tb_catalogo_municipios ON tb_empleados.fk_municipio = tb_catalogo_municipios.id_municipio "
						+ " LEFT JOIN tb_catalogo_colonias ON tb_empleados.fk_colonia = tb_catalogo_colonias.id_colonia "
						+ " LEFT JOIN tb_especialidades_academicas ON tb_empleados.Fk_especialidad = tb_especialidades_academicas.id_especialidad  "
						+ " LEFT JOIN tb_grados_periodos ON tb_empleados.fk_g_p = tb_grados_periodos.id_grado "
						+ " LEFT JOIN tb_periodos ON tb_empleados.fk_p = tb_periodos.id_periodosA "
						+ " LEFT JOIN tb_niveles_academicos ON tb_empleados.fk_nivel_academico = tb_niveles_academicos.id_escolaridad  "
						+ " LEFT JOIN tb_corporaciones ON tb_empleados.fk_corporacion = tb_corporaciones.id_corporacion "
						+ " LEFT JOIN tb_tipos_estatus AS estatus_empleado ON tb_empleados.fk_estatus = estatus_empleado.id_tipo_estatus "
						+ " LEFT JOIN tb_tipos_estatus AS estatus_carrera_trunca ON tb_empleados.c_trunca = estatus_carrera_trunca.id_tipo_estatus "
						+ "WHERE "
						+ "CONCAT(LTRIM(RTRIM(tb_empleados.Nombre)), ' ', LTRIM(RTRIM(tb_empleados.Ap_paterno)), ' ', LTRIM(RTRIM(tb_empleados.Ap_materno)))  LIKE '%"+ nombre + "%'";
				if (idUltimoEmpleado != 0){
					query+= " AND tb_empleados.id_empleado > " + idUltimoEmpleado + " ";
				}
				if (numLimit != 0){
					query+= " LIMIT " + numLimit + ";";
				}
				// System.out.println(query);
				try {
					rs = getDatabase().executeQuery(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if(rs != null){
						while(rs.next()){					
							try {
								EmpleadoDTO empleado = herramientasRS.inicializaEmpleadoDTO(rs);
								if(empleado != null ){
									v.add(empleado);
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
					}else{
						System.out.println("rs == nullo en selectEmpleadoPorNombre");
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
		}else{
			System.out.println("parametro nombre es nulo");
		}
		return v;
	}
	
	/**
	 * SELECCIONA EL EMPLEADO DESEADO EN BASE AL SKU DEL EMPLEADO EN INT [YULIANA] 02022017
	 * @param skuEmpleado
	 * @return EmpleadoDTO
	 */
	public EmpleadoDTO selectEmpleadoPorSku(String skuEmpleado,int numLimit){	

		EmpleadoDTO empleado = null;
		if(skuEmpleado != null ){
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				ResultSet rs = null;
				String query = "SELECT *, estatus_carrera_trunca.status_int AS carrera_trunca,estatus_empleado.status_int AS estatus_emp FROM tb_empleados  "
						+ " LEFT JOIN tb_generos ON tb_empleados.fk_genero = tb_generos.id_genero "
						+ " LEFT JOIN tb_estados_civiles ON tb_empleados.fk_e_c = tb_estados_civiles.id_estado_civil "
						+ " LEFT JOIN tb_grupos_sanguineos ON tb_empleados.fk_grupo_sanguineo = tb_grupos_sanguineos.id_grupo_sanguineo "
						+ " LEFT JOIN tb_catalogo_estados ON tb_empleados.fk_estado = tb_catalogo_estados.id_estado "
						+ " LEFT JOIN tb_catalogo_municipios ON tb_empleados.fk_municipio = tb_catalogo_municipios.id_municipio "
						+ " LEFT JOIN tb_catalogo_colonias ON tb_empleados.fk_colonia = tb_catalogo_colonias.id_colonia "
						+ " LEFT JOIN tb_especialidades_academicas ON tb_empleados.Fk_especialidad = tb_especialidades_academicas.id_especialidad  "
						+ " LEFT JOIN tb_grados_periodos ON tb_empleados.fk_g_p = tb_grados_periodos.id_grado "
						+ " LEFT JOIN tb_periodos ON tb_empleados.fk_p = tb_periodos.id_periodosA "
						+ " LEFT JOIN tb_niveles_academicos ON tb_empleados.fk_nivel_academico = tb_niveles_academicos.id_escolaridad  "
						+ " LEFT JOIN tb_corporaciones ON tb_empleados.fk_corporacion = tb_corporaciones.id_corporacion "
						+ " LEFT JOIN tb_tipos_estatus AS estatus_empleado ON tb_empleados.fk_estatus = estatus_empleado.id_tipo_estatus "
						+ " LEFT JOIN tb_tipos_estatus AS estatus_carrera_trunca ON tb_empleados.c_trunca = estatus_carrera_trunca.id_tipo_estatus "
						+ "WHERE "
						+ "tb_empleados.Sku_empleado = '"+ skuEmpleado + "'";
				if (numLimit != 0){
					query+= " LIMIT " + numLimit + ";";
				}

				//	System.out.println(query);
				try {
					rs = getDatabase().executeQuery(query);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					if(rs != null){
						while(rs.next()){					

							try {
								empleado = herramientasRS.inicializaEmpleadoDTO(rs);
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
						System.out.println("rs == nullo en selectEmpleadoPorSku");
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
		}else{
			System.out.println("parametro skuEmpleado nulo");
		}
		return empleado;
	}
	
	/**
	 * SELECCIONA EL EMPLEADO DESEADO EN BASE AL SKU DEL EMPLEADO EN INT [YULIANA] 02022017
	 * @param skuEmpleado
	 * @return EmpleadoDTO
	 */
	public EmpleadoDTO selectEmpleadoParaRegistroAsistenciaPorSku(String skuEmpleado,int numLimit){	

		EmpleadoDTO empleado = null;
		if(skuEmpleado != null ){
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				ResultSet rs = null;
				String query = "SELECT *, estatus_carrera_trunca.status_int AS carrera_trunca,estatus_empleado.status_int AS estatus_emp FROM tb_empleados  "
						+ " LEFT JOIN tb_generos ON tb_empleados.fk_genero = tb_generos.id_genero "
						+ " LEFT JOIN tb_estados_civiles ON tb_empleados.fk_e_c = tb_estados_civiles.id_estado_civil "
						+ " LEFT JOIN tb_grupos_sanguineos ON tb_empleados.fk_grupo_sanguineo = tb_grupos_sanguineos.id_grupo_sanguineo "
						+ " LEFT JOIN tb_catalogo_estados ON tb_empleados.fk_estado = tb_catalogo_estados.id_estado "
						+ " LEFT JOIN tb_catalogo_municipios ON tb_empleados.fk_municipio = tb_catalogo_municipios.id_municipio "
						+ " LEFT JOIN tb_catalogo_colonias ON tb_empleados.fk_colonia = tb_catalogo_colonias.id_colonia "
						+ " LEFT JOIN tb_especialidades_academicas ON tb_empleados.Fk_especialidad = tb_especialidades_academicas.id_especialidad  "
						+ " LEFT JOIN tb_grados_periodos ON tb_empleados.fk_g_p = tb_grados_periodos.id_grado "
						+ " LEFT JOIN tb_periodos ON tb_empleados.fk_p = tb_periodos.id_periodosA "
						+ " LEFT JOIN tb_niveles_academicos ON tb_empleados.fk_nivel_academico = tb_niveles_academicos.id_escolaridad  "
						+ " LEFT JOIN tb_corporaciones ON tb_empleados.fk_corporacion = tb_corporaciones.id_corporacion "
						+ " LEFT JOIN tb_tipos_estatus AS estatus_empleado ON tb_empleados.fk_estatus = estatus_empleado.id_tipo_estatus "
						+ " LEFT JOIN tb_tipos_estatus AS estatus_carrera_trunca ON tb_empleados.c_trunca = estatus_carrera_trunca.id_tipo_estatus "
						+ "WHERE "
						+ "tb_empleados.Sku_empleado = '"+ skuEmpleado + "'";
				if (numLimit != 0){
					query+= " LIMIT " + numLimit + ";";
				}

				//	System.out.println(query);
				try {
					rs = getDatabase().executeQuery(query);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					if(rs != null){
						while(rs.next()){					

							try {
								empleado = herramientasRS.inicializaEmpleadoDTO(rs);
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
						System.out.println("rs == nullo en selectEmpleadoPorSku");
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
		}else{
			System.out.println("parametro skuEmpleado nulo");
		}
		return empleado;
	}
	
	/**
	 * SELECCIONA EL EMPLEADO DESEADO EN BASE A LA CURP DEL EMPLEADO EN string [JAVIER] 02172017
	 * @param curp
	 * @return EmpleadoDTO
	 */
	public EmpleadoDTO selectEmpleadoPorCurp(String curp,int numLimit){	

		EmpleadoDTO empleado = null;
		if(curp != null ){
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());
				ResultSet rs = null;
				String query = "SELECT *, estatus_carrera_trunca.status_int AS carrera_trunca,estatus_empleado.status_int AS estatus_emp FROM tb_empleados "
						+ " LEFT JOIN tb_generos ON tb_empleados.fk_genero = tb_generos.id_genero "
						+ " LEFT JOIN tb_estados_civiles ON tb_empleados.fk_e_c = tb_estados_civiles.id_estado_civil "
						+ " LEFT JOIN tb_grupos_sanguineos ON tb_empleados.fk_grupo_sanguineo = tb_grupos_sanguineos.id_grupo_sanguineo "
						+ " LEFT JOIN tb_catalogo_estados ON tb_empleados.fk_estado = tb_catalogo_estados.id_estado "
						+ " LEFT JOIN tb_catalogo_municipios ON tb_empleados.fk_municipio = tb_catalogo_municipios.id_municipio "
						+ " LEFT JOIN tb_catalogo_colonias ON tb_empleados.fk_colonia = tb_catalogo_colonias.id_colonia "
						+ " LEFT JOIN tb_especialidades_academicas ON tb_empleados.Fk_especialidad = tb_especialidades_academicas.id_especialidad  "
						+ " LEFT JOIN tb_grados_periodos ON tb_empleados.fk_g_p = tb_grados_periodos.id_grado "
						+ " LEFT JOIN tb_periodos ON tb_empleados.fk_p = tb_periodos.id_periodosA "
						+ " LEFT JOIN tb_niveles_academicos ON tb_empleados.fk_nivel_academico = tb_niveles_academicos.id_escolaridad  "
						+ " LEFT JOIN tb_corporaciones ON tb_empleados.fk_corporacion = tb_corporaciones.id_corporacion "
						+ " LEFT JOIN tb_tipos_estatus AS estatus_empleado ON tb_empleados.fk_estatus = estatus_empleado.id_tipo_estatus "
						+ " LEFT JOIN tb_tipos_estatus AS estatus_carrera_trunca ON tb_empleados.c_trunca = estatus_carrera_trunca.id_tipo_estatus "
						+ "WHERE "
						+ "tb_empleados.Curp = '"+ curp + "'";
				if (numLimit != 0){
					query+= " LIMIT " + numLimit + ";";
				}

			//	System.out.println(query);
				try {
					rs = getDatabase().executeQuery(query);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					if(rs != null){
					while(rs.next()){					
						try {
							empleado = herramientasRS.inicializaEmpleadoDTO(rs);
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
					System.out.println("rs == nullo en selectEmpleadoPorCurp");
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
		}else{
			System.out.println("parametro curp nulo");
		}
		return empleado;

	}
	
	/**
	 * SELECCIONA EL EMPLEADO DESEADO EN BASE AL SKU DEL EMPLEADO EN INT [YULIANA] 02022017
	 * @param skuEmpleado
	 * @return EmpleadoDTO
	 */
	public Vector<EmpleadoDTO> selectEmpleadoTodos(int numLimit){	
		Vector<EmpleadoDTO> vtodos = new Vector<EmpleadoDTO>();
		//EmpleadoDTO empleado = null;
		//if(skuEmpleado != null ){
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT *, estatus_carrera_trunca.status_int AS carrera_trunca,estatus_empleado.status_int AS estatus_emp FROM tb_empleados "
					+ " LEFT JOIN tb_generos ON tb_empleados.fk_genero = tb_generos.id_genero "
					+ " LEFT JOIN tb_estados_civiles ON tb_empleados.fk_e_c = tb_estados_civiles.id_estado_civil "
					+ " LEFT JOIN tb_grupos_sanguineos ON tb_empleados.fk_grupo_sanguineo = tb_grupos_sanguineos.id_grupo_sanguineo "
					+ " LEFT JOIN tb_catalogo_estados ON tb_empleados.fk_estado = tb_catalogo_estados.id_estado "
					+ " LEFT JOIN tb_catalogo_municipios ON tb_empleados.fk_municipio = tb_catalogo_municipios.id_municipio "
					+ " LEFT JOIN tb_catalogo_colonias ON tb_empleados.fk_colonia = tb_catalogo_colonias.id_colonia "
					+ " LEFT JOIN tb_niveles_academicos ON tb_empleados.fk_nivel_academico = tb_niveles_academicos.id_escolaridad  "
					+ " LEFT JOIN tb_especialidades_academicas ON tb_empleados.Fk_especialidad = tb_especialidades_academicas.id_especialidad  "
					+ " LEFT JOIN tb_grados_periodos ON tb_empleados.fk_g_p = tb_grados_periodos.id_grado "
					+ " LEFT JOIN tb_periodos ON tb_empleados.fk_p = tb_periodos.id_periodosA "
					+ " LEFT JOIN tb_corporaciones ON tb_empleados.fk_corporacion = tb_corporaciones.id_corporacion "
					+ " LEFT JOIN tb_tipos_estatus AS estatus_empleado ON tb_empleados.fk_estatus = estatus_empleado.id_tipo_estatus "
					+ " LEFT JOIN tb_tipos_estatus AS estatus_carrera_trunca ON tb_empleados.c_trunca = estatus_carrera_trunca.id_tipo_estatus "
					+ " ORDER BY tb_empleados.Nombre";
			if (numLimit != 0){
				query+= " LIMIT " + numLimit + ";";
			}
			try {
				rs = getDatabase().executeQuery(query);
				try {
					if(rs != null){
						while(rs.next()){					
							try {
								EmpleadoDTO	empleado = herramientasRS.inicializaEmpleadoDTO(rs);
								vtodos.add(empleado);

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
						System.out.println("rs == nullo en selectEmpleadoTodos");
					}				
				} catch (SQLException e) {
					e.printStackTrace();
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
		//}
		return vtodos;
	}
	
	/**
	 * SELECCIONA EL EMPLEADO DESEADO EN BASE AL SKU DEL EMPLEADO EN INT [YULIANA] 02022017
	 * @param skuEmpleado
	 * @return EmpleadoDTO
	 */
	public Vector<EmpleadoDTO> selectNombreIdEmpleados(int numLimit){	
		Vector<EmpleadoDTO> vtodos = new Vector<EmpleadoDTO>();
		//EmpleadoDTO empleado = null;
		//if(skuEmpleado != null ){
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT tb_empleados.id_empleado, CONCAT(tb_empleados.Nombre, ' ',tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno) AS nombre_empleado FROM tb_empleados "
					+ " WHERE tb_empleados.fk_estatus = 1"
					+ " ORDER BY tb_empleados.Nombre";
			if (numLimit != 0){
				query+= " LIMIT " + numLimit + ";";
			}else{
				query+= ";"; 
			}
			try {
				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){					
						EmpleadoDTO	empleado = herramientasRS.inicializaEmpleadoSoloIdNombreCompleto(rs);
						vtodos.add(empleado);
					}
					/*try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}*/

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
		//}
		return vtodos;
	}
	
	/**
	 * SELECCIONA EL EMPLEADO DESEADO EN BASE AL SKU DEL EMPLEADO EN INT [YULIANA] 02022017
	 * @param skuEmpleado
	 * @return EmpleadoDTO
	 */
	public Vector<EmpleadoDTO> selectNombreIdEmpleadosSinUsuario(int numLimit){	
		Vector<EmpleadoDTO> vtodos = new Vector<EmpleadoDTO>();
		//EmpleadoDTO empleado = null;
		//if(skuEmpleado != null ){
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT\r\n" + 
					"    tb_empleados.id_empleado, CONCAT(tb_empleados.Nombre, ' ',tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno) AS nombre_empleado\r\n" + 
					"FROM\r\n" + 
					"    tb_empleados\r\n" + 
					"    LEFT OUTER JOIN sec_users ON\r\n" + 
					"        tb_empleados.id_empleado = sec_users.fk_id_empleado\r\n" + 
					"        AND sec_users.fk_id_empleado IS NOT NULL\r\n" + 
					"WHERE\r\n" + 
					"    sec_users.fk_id_empleado IS NULL "
					+ "ORDER BY tb_empleados.Nombre";
			if (numLimit != 0){
				query+= " LIMIT " + numLimit + ";";
			}else{
				query+= ";"; 
			}
			try {
//				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){					
						EmpleadoDTO	empleado = herramientasRS.inicializaEmpleadoSoloIdNombreCompleto(rs);
						vtodos.add(empleado);
					}
					try {
						rs.close();
					} catch (SQLException e) {
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
		//}
		return vtodos;
	}
	
	/**
	 * SELECCIONA LOS ID Y SKUS DE EMPLEADOS ACTIVOS EN LA BD
	 * @return Vector<EmpleadoDTO> 
	 */
	public Vector<EmpleadoDTO> selectIdSkuEmpleados(){	
		Vector<EmpleadoDTO> vtodos = new Vector<EmpleadoDTO>();		
//		if(database.openDatabase()){
			//System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query = "SELECT tb_empleados.id_empleado, tb_empleados.Sku_empleado, CONCAT(tb_empleados.Nombre, ' ',tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno) AS nombre_empleado FROM tb_empleados"
					+ " WHERE tb_empleados.fk_estatus = 1"					
					+ " ORDER BY tb_empleados.Nombre;";
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					try {
						while(rs.next()){					
							try {
								EmpleadoDTO	empleado = herramientasRS.inicializaEmpleadoSoloIdSkuNombreCompleto(rs);
								if(empleado != null){
									vtodos.add(empleado);
								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						rs.close();						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else{
					System.out.println("rs == null en selectIdSkuEmpleados");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/*if(database.closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}*/
		/*}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}*/
		return vtodos;
	}
	
	/**
	 * SELECCIONA LOS ID Y SKUS DE EMPLEADOS ACTIVOS EN LA BD
	 * @return Vector<EmpleadoDTO> 
	 */
	public Vector<EmpleadoDTO> selectIdSkuEmpleados(HashMap<String, String> map){	
		Vector<EmpleadoDTO> vtodos = new Vector<EmpleadoDTO>();		
		/*if(database.openDatabase()){			
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());*/
			ResultSet rs = null;
			String query = "SELECT tb_empleados.id_empleado, tb_empleados.Sku_empleado, CONCAT(tb_empleados.Nombre, ' ',tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno) AS nombre_empleado FROM tb_empleados"
					+ " WHERE tb_empleados.fk_estatus = 1";
					if(!HerramientasResultSet.valueToStringOrEmpty(map, "skuEmpleado").equals("") ){
						query += "	AND tb_empleados.Sku_empleado = '" + (String) map.get("skuEmpleado") + "'";
					}
					
					if(!HerramientasResultSet.valueToStringOrEmpty(map, "nombreEmpleado").equals("")){
						query += "	AND CONCAT(tb_empleados.Nombre, ' ' , tb_empleados.Ap_paterno, ' ', tb_empleados.Ap_materno) LIKE '%" + (String) map.get("nombreEmpleado") + "%'";
					}
					
					query += " ORDER BY tb_empleados.Nombre;";
			try {
				System.out.println("query selectIdSkuEmpleados " + query);
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					try {
						while(rs.next()){					
							try {
								EmpleadoDTO	empleado = herramientasRS.inicializaEmpleadoSoloIdSkuNombreCompleto(rs);
								if(empleado != null){
									vtodos.add(empleado);
								}								
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						rs.close();						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else{
					System.out.println("rs == null en selectIdSkuEmpleados");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			/*if(database.closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}*/
		return vtodos;
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
	
}
