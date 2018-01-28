package dao.listados;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;

import dao.DatabaseGateway;
import dao.DatabaseGateway2;
import dao.DatabaseGateway3;
import dto.empleado.EspecialidadDTO;
import dto.empleado.NivelAcademicoDTO;
import dto.listados.ColoniaDTO;
import dto.listados.EntidadFederativaDTO;
import dto.listados.EstadoCivilDTO;
import dto.listados.GeneroDTO;
import dto.listados.GradoEscolarDTO;
import dto.listados.GrupoSanguineoDTO;
import dto.listados.MunicipioDTO;
import dto.listados.PeriodoEscolarDTO;
import dto.listados.TipoEstatusDTO;
import dto.listados.TotalTiposLlamadasDTO;
import herramientas.herrramientasrs.HerramientasResultSet;

public class ListadosDAO {

	private DatabaseGateway database = null;
	private HerramientasResultSet herramientasRS = null;
	
	/**
	 * Construye la clase sin paramentros
	 */
	public ListadosDAO(){
		if(this.getDatabase() == null){
			this.setDatabase(new DatabaseGateway());
		}else{
			System.out.println("database != null en " + this.getClass().getSimpleName() );
		}
		InitializeComponents();
	}	
	
	/**
	 * Constrye la clase con la instancia de base de datos por parametro para reutilizar el objeto
	 */
	public ListadosDAO(DatabaseGateway database){
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
	
	/**
	 * INICIALIZA LOS COMPONENTES DE LA CLASE
	 */
	public void InitializeComponents(){
		if (getHerramientasRS() == null){
			setHerramientasRS(new HerramientasResultSet());
		}else{
			System.out.println("herramientasRS != null en " + this.getClass().getSimpleName() );
		}
	}


	public Vector<GeneroDTO> selectGeneros(){		
		Vector<GeneroDTO> vgeneros = new Vector<GeneroDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_generos";
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						GeneroDTO genero = getHerramientasRS().inicializaGeneroDTO(rs);
						vgeneros.add(genero);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectGeneros");
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
		return vgeneros;
	}
	
	
	
	public Vector<EstadoCivilDTO> selectEstadosCivilesPorGenero(int idGenero){		
		Vector<EstadoCivilDTO> vEstCiviles = new Vector<EstadoCivilDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_estados_civiles WHERE fk_genero=" + idGenero;
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						EstadoCivilDTO estCivil = getHerramientasRS().inicializaEstadoCivilDTO(rs);
						vEstCiviles.add(estCivil);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectEstadosCivilesPorGenero");
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
		return vEstCiviles;
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector<EntidadFederativaDTO> selectEntidadFederativa(){		
		Vector<EntidadFederativaDTO> ventidades = new Vector<EntidadFederativaDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_catalogo_estados";
			try {
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						EntidadFederativaDTO entidadf = getHerramientasRS().inicializaEntidadFederativaDTO(rs);
						ventidades.add(entidadf);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectEntidadFederativa");
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
		return ventidades;
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector<TotalTiposLlamadasDTO> selectTotalesTiposDeLlamadasDTO(){		
		Vector<TotalTiposLlamadasDTO> llamadasTotales = new Vector<TotalTiposLlamadasDTO>();
		dao.DatabaseGateway2 db2 = new DatabaseGateway2(); 		
		if(db2.openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT COUNT(*) AS total_llamadas,"
					+ " tipollamada.tipo FROM llamadas"
					+ " INNER JOIN tipollamada ON llamadas.tipollamada = tipollamada.tipo_id"
					+ " AND fecha BETWEEN '2016-01-01' AND '2016-01-31' GORUP BY tipollamada;";
			try {
				rs = db2.executeQuery(query);
				if(rs != null){
					while(rs.next()){
						TotalTiposLlamadasDTO totalLlamadasTipo = getHerramientasRS().inicializaTotaldeLlamadasTipoDTO2(rs);
						if(totalLlamadasTipo != null){
							llamadasTotales.add(totalLlamadasTipo);
						}else{
							System.out.println("totalLlamadasTipo inicial == null");
						}
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectEntidadFederativa");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (db2.closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return llamadasTotales;
	}
	
	/**
	 * 
	 * @return
	 */
	public Vector<TotalTiposLlamadasDTO> selectTotalesTiposDeLlamadasDTOFiltros(LocalDate fi, LocalDate ff){		
		Vector<TotalTiposLlamadasDTO> llamadasTotales = null;
		Vector<TotalTiposLlamadasDTO> llamadasTotalesTemp = new Vector<TotalTiposLlamadasDTO>();
		final LocalDate fechaFinalDb2 = LocalDate.parse("2016-06-01");
		final LocalDate fechaInicialDb3 = LocalDate.parse("2016-06-02");
		if(fi.isBefore(fechaFinalDb2) || fi.isEqual(fechaFinalDb2) && fi.isBefore(fechaInicialDb3)){
//			BUSCA EN LA BASE DE DATOS ANTIGUA			
			llamadasTotales = selectTotalTiposLlamadasDTODB2(fi, ff, llamadasTotales);
			if(llamadasTotales != null && llamadasTotales.size() > 0){
				if(ff.isAfter(fechaInicialDb3) || ff.isEqual(fechaInicialDb3)){
//					SUMA LA CANTIDAD DE LA BASE DE DATOS NUEVA CON LA CANTIDAD DE LA BD ANTIGUA
					Vector<TotalTiposLlamadasDTO> llamadasTotales3 = null;
					llamadasTotales3 = selectTotalTiposLlamadasDTODB3(fi, ff, llamadasTotales3);
					if(llamadasTotales3 != null && llamadasTotales3.size() > 0){
						if(llamadasTotales.size() > llamadasTotales3.size()){														
							for(TotalTiposLlamadasDTO totalTipoLlamada : llamadasTotales){
								TotalTiposLlamadasDTO totalTipoTemp = new TotalTiposLlamadasDTO();							
								for (int j = 0; j < llamadasTotales3.size(); j++) {
									if(totalTipoLlamada.getTipoLlamada().getIdTipoLlamada() == llamadasTotales3.get(j).getTipoLlamada().getIdTipoLlamada()){
										System.out.println("Encontrado! " + totalTipoLlamada.getTipoLlamada().getIdTipoLlamada() +" == " +llamadasTotales3.get(j).getTipoLlamada().getIdTipoLlamada());
										totalTipoTemp = totalTipoLlamada;									
										totalTipoTemp.setTotalLlamadas(totalTipoLlamada.getTotalLlamadas() + llamadasTotales3.get(j).getTotalLlamadas()); 
										llamadasTotalesTemp.add(totalTipoTemp);
										llamadasTotales3.remove(j);
										j--;
										break;
									}else{
										System.out.println("Sin coincidencia..buscando.... totalTipoLlamada.getTipoLlamada().getIdTipoLlamada()?" + totalTipoLlamada.getTipoLlamada().getIdTipoLlamada() +" != " +llamadasTotales3.get(j).getTipoLlamada().getIdTipoLlamada());
										
									}
								}
								
							}//TERMINA CICLO
							if(llamadasTotales3.size() > 0){
								llamadasTotalesTemp.addAll(llamadasTotales3);
								System.out.println("agregando elementos no coincidentes");
							}
						}else{
							if(llamadasTotales3.size() >= llamadasTotales.size()){
								for(TotalTiposLlamadasDTO totalTipoLlamada : llamadasTotales3){
									TotalTiposLlamadasDTO totalTipoTemp = new TotalTiposLlamadasDTO();							
									for (int j = 0; j < llamadasTotales.size(); j++) {
										if(totalTipoLlamada.getTipoLlamada().getIdTipoLlamada() == llamadasTotales.get(j).getTipoLlamada().getIdTipoLlamada()){
											System.out.println("Encontrado! " + totalTipoLlamada.getTipoLlamada().getIdTipoLlamada() +" == " +llamadasTotales.get(j).getTipoLlamada().getIdTipoLlamada());
											totalTipoTemp = totalTipoLlamada;									
											totalTipoTemp.setTotalLlamadas(totalTipoLlamada.getTotalLlamadas() + llamadasTotales.get(j).getTotalLlamadas()); 
											llamadasTotalesTemp.add(totalTipoTemp);
											llamadasTotales.remove(j);
											j--;
											break;
										}else{
											System.out.println("Sin coincidencia..buscando.... totalTipoLlamada.getTipoLlamada().getIdTipoLlamada()?" + totalTipoLlamada.getTipoLlamada().getIdTipoLlamada() +" != " +llamadasTotales.get(j).getTipoLlamada().getIdTipoLlamada());
											
										}
										
									}
									
								}//TERMINA CICLO
								if(llamadasTotales.size() > 0){
									llamadasTotalesTemp.addAll(llamadasTotales);
									System.out.println("agregando elementos no coincidentes");
								}
							}else{
//								RESULTADO DE VECTOR NI MAYOR NI MENOR NI IGUAL !
							}
						}
					}else{
//						SIN RESULTADO DB3
					}
				}else{
//					NO SE SUMA NADA SIGUE ESTANDO DENTRO DEL RANGO
					llamadasTotalesTemp = llamadasTotales;
				}
			}else{
//				SIN RESULTADO DE BD2
			}
		}else if(fi.isAfter(fechaFinalDb2) || fi.isEqual(fechaInicialDb3) || fi.isAfter(fechaInicialDb3)){
//			BUSCA DIRECTO EN LA BD NUEVA
			llamadasTotalesTemp = selectTotalTiposLlamadasDTODB3(fi, ff, llamadasTotales);
		}else{
//			ESTA FUERA DE RANGO!
		}
		return llamadasTotalesTemp;
	}

	/**
	 * 
	 * @param fi
	 * @param ff
	 * @param llamadasTotales
	 * @return
	 */
	private Vector<TotalTiposLlamadasDTO> selectTotalTiposLlamadasDTODB2(LocalDate fi, LocalDate ff, Vector<TotalTiposLlamadasDTO> llamadasTotales){
		llamadasTotales = new Vector<TotalTiposLlamadasDTO>();
		dao.DatabaseGateway2 db2 = new DatabaseGateway2();
		if(db2.openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT COUNT(*) AS total_llamadas,"
					+ " tipollamada.tipo_id, "
					+ " tipollamada.tipo "
					+ "FROM llamadas"
					+ " INNER JOIN tipollamada ON llamadas.tipollamada = tipollamada.tipo_id"
					+ " AND fecha BETWEEN '"+fi+"' AND '"+ff+"' GROUP BY tipollamada.tipo_id ORDER BY tipollamada.tipo_id;";
			try {
				System.out.println(query);
				rs = db2.executeQuery(query);
				if(rs != null){
					while(rs.next()){
						TotalTiposLlamadasDTO totalLlamadasTipo = getHerramientasRS().inicializaTotaldeLlamadasTipoDTO2(rs);
						llamadasTotales.add(totalLlamadasTipo);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectEntidadFederativa");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (db2.closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return llamadasTotales;		
	}
	
	/**
	 * 
	 * @param fi
	 * @param ff
	 * @param llamadasTotales
	 * @return
	 */
	private Vector<TotalTiposLlamadasDTO> selectTotalTiposLlamadasDTODB3(LocalDate fi, LocalDate ff, Vector<TotalTiposLlamadasDTO> llamadasTotales){
		llamadasTotales = new Vector<TotalTiposLlamadasDTO>();
		dao.DatabaseGateway3 db3 = new DatabaseGateway3();
		if(db3.openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT COUNT(*) AS total_llamadas,\r\n" + 
					"					 tipos.tipo_id, \r\n" + 
					"					 tipos.tipo \r\n" + 
					"					FROM llamadas\r\n" + 
					"					 INNER JOIN tipos ON llamadas.tipo_id = tipos.tipo_id\r\n" + 
					"					 AND fecha BETWEEN '"+fi+"' AND '"+ff+"' GROUP BY tipos.tipo_id ORDER BY tipos.tipo_id;";
			try {
				System.out.println(query);
				rs = db3.executeQuery(query);
				if(rs != null){
					while(rs.next()){
						TotalTiposLlamadasDTO totalLlamadasTipo = getHerramientasRS().inicializaTotaldeLlamadasTipoDTO3(rs);
						llamadasTotales.add(totalLlamadasTipo);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectEntidadFederativa");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (db3.closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		return llamadasTotales;		
	}
	
	public Vector<MunicipioDTO> selectMunicipiosPorEntidadFederativa(int idEntidadFederativa){		
		Vector<MunicipioDTO> vmunicipios = new Vector<MunicipioDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_catalogo_municipios WHERE estado_id = " + idEntidadFederativa;
			try {
				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						MunicipioDTO municipio = getHerramientasRS().iniciailizaMunicipioDTO(rs);
						vmunicipios.add(municipio);
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
		return vmunicipios;
	}
	
	
	public Vector<ColoniaDTO> selectColoniasPorMunicipios(int idMunicipio){		
		Vector<ColoniaDTO> vcolonias = new Vector<ColoniaDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_catalogo_colonias WHERE tb_catalogo_colonias.municipio_id = " + idMunicipio + " ORDER BY tb_catalogo_colonias.nombre";
			try {
				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						ColoniaDTO colonia = getHerramientasRS().iniciailizaColoniaDTO(rs);
						vcolonias.add(colonia);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectColoniasPorMunicipios");
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
		return vcolonias;
	}

	public Vector<GrupoSanguineoDTO> selectGruposSanguineos(){
		Vector<GrupoSanguineoDTO> vgpoSanguineos = new Vector<GrupoSanguineoDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("Conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query ="SELECT * FROM tb_grupos_sanguineos";
			try{
				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				while (rs.next()){
					GrupoSanguineoDTO gpoSang = getHerramientasRS().inicializaGrupoSanguineoDTO(rs);
					vgpoSanguineos.add(gpoSang);
				}
				rs.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("Conexion no abierta en " + this.getClass().getSimpleName());
		}
		return vgpoSanguineos;
	}
	
	public Vector<PeriodoEscolarDTO> selectPeriodosEscolares(){
		Vector<PeriodoEscolarDTO> vPerEscolares = new Vector<PeriodoEscolarDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("Conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query ="SELECT * FROM tb_periodos";
			try{
				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				while (rs.next()){
					PeriodoEscolarDTO perEscolar = getHerramientasRS().inicializaPeriodoEscolarDTO(rs);
					vPerEscolares.add(perEscolar);
				}
				rs.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("Conexion no abierta en " + this.getClass().getSimpleName());
		}
		return vPerEscolares;
	}
	
	public Vector<GradoEscolarDTO> selectGradosEscolaresPorPeriodoEscolar(int idPeriodoEscolar){		
		Vector<GradoEscolarDTO> vGdosEsc = new Vector<GradoEscolarDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_grados_periodos WHERE fk_periodo = " + idPeriodoEscolar;
			try {
				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						GradoEscolarDTO gdoEsc = getHerramientasRS().inicializaGradoEscolarDTO(rs);
						vGdosEsc.add(gdoEsc);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectGradosEscolaresPorPeriodoEscolar");
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
		return vGdosEsc;
	}
	
	public Vector<NivelAcademicoDTO> selectNivelesAcademicos(){
		Vector<NivelAcademicoDTO> vNvelAcademicos = new Vector<NivelAcademicoDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("Conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query ="SELECT * FROM tb_niveles_academicos";
			try{
				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while (rs.next()){
						NivelAcademicoDTO nvelAcademico = getHerramientasRS().inicializaNivelAcademicoDTO(rs);
						vNvelAcademicos.add(nvelAcademico);
					}
					try{
						rs.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				}else{
					System.out.println("rs == nullo en selectNivelesAcademicos");
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("Conexion no abierta en " + this.getClass().getSimpleName());
		}
		return vNvelAcademicos;
	}
	
	public Vector<EspecialidadDTO> selectEspecialidadesAcademicasPorNivel(int idNivelAcademico){		
		Vector<EspecialidadDTO> vEspAcademicas = new Vector<EspecialidadDTO>();
		if(getDatabase().openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());
			ResultSet rs = null;
			String query= "SELECT * FROM tb_especialidades_academicas WHERE Fk_nivel_academico = " + idNivelAcademico;
			try {
				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						EspecialidadDTO espAcad = getHerramientasRS().inicializaEspecialidadDTO(rs);
						vEspAcademicas.add(espAcad);
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
		return vEspAcademicas;
	}
	
	/**
	 * SELECCIONA EL ESTADO Y TIPO DE LA TABLA TIPOS_ESTATUS [JAVIER]20170314
	 * @param aplicacion
	 * @return EstatusDTO
	 */
	public TipoEstatusDTO selectTiposEstatusDTOSimplePorIdTipoEstatusConConexionAbierta(int idTipoEstatus){		
		TipoEstatusDTO tiposEstatus = null;
		/*if(database.openDatabase()){
			System.out.println("conexion abierta en " + this.getClass().getSimpleName());*/
			ResultSet rs = null;
			String query= "SELECT * FROM tb_tipos_estatus WHERE tb_tipos_estatus.id_tipo_estatus ='" + idTipoEstatus + "';";
			try {
//				System.out.println(query);
				rs = getDatabase().executeQuery(query);
				if(rs != null){
					while(rs.next()){
						tiposEstatus = getHerramientasRS().inicializaEstatusSimpleDTO(rs);
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
			/*if (database.closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}*/
		/*}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}*/
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
