package dao.documentos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import dao.DatabaseGateway;
import dao.empleado.EmpleadoDAO;
import dao.horario.HorarioDAO;
import dto.documentos.DocumentoHorarioDTO;
import herramientas.ArchivoDTO;
import herramientas.herrramientasrs.HerramientasResultSet;

public class DocumentosDAO {
	private DatabaseGateway database = null;
	EmpleadoDAO empleadoDAO = null;
	HorarioDAO horarioDAO = null;
	HerramientasResultSet herramientasRS = null;
		
	/**
	 * Contruye esta instancia de clase
	 */
	public DocumentosDAO(){
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
	public DocumentosDAO(DatabaseGateway database){	
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
	
	
	
	public int insertRegistroDocumentoEmpleadosHorariosDTO(DocumentoHorarioDTO doc){
		int res = -1;
		if(doc != null){
			if(database.openDatabase()){
				String queryInsertRegistroArchivo = "INSERT INTO tb_documentos ("
						+ "nombre_doc"
						+ ", ruta_doc"
						+ ", fecha_validez_doc"
						+ ", fecha_carga_doc"
						+ ", hora_carga_doc"
						+ ", usuario_inserecion_doc"
						+ ", tamano_bytes_doc"
						+ ", extension_doc"
						+ ", status_doc"
						+ ") VALUES (";
				queryInsertRegistroArchivo += " '" + doc.getNombreDocumento() + "', '" + doc.getRutaDocumento() + "', '" + doc.getFechaValidezDocumento() 
				+ "', '" + doc.getFechaCargaDocumento() + "', '" + doc.getHoraCargaDocumento() + "', " 
				+ doc.getUsuarioDocumento() + ", " + doc.getTamanoDocumento() + ", '" + doc.getExtDocumento() + "', " + doc.getStatusDocumento() + ");" ;
				try {
					System.out.println("query registro archivo " + queryInsertRegistroArchivo);
					res = database.executeNonQuery(queryInsertRegistroArchivo);
					if(res > 0){
						String queryLastId = "SELECT LAST_INSERT_ID() AS ultimo_id;";
						ResultSet rs = database.executeQuery(queryLastId);
						if(rs != null){
							while (rs.next()) {
								res = rs.getInt("ultimo_id");								
							}
						}
					}
				} catch (SQLException e) {
					//						principal.getErrores().add("El empleado fulano de tal no ha sido encontrado! favor de revisar los datos ");
					e.printStackTrace();
				}
				if(database.closeDatabase()){
					System.out.println("Conexion cerrada en " + this.getClass().getSimpleName());
				}else{
					System.out.println("Conexion NO cerrada en " + this.getClass().getSimpleName());
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("Documento vacio o nulo");
		}
		return res;
	}

	public ArchivoDTO selectArchivoDTO(int idArchivo){
		ArchivoDTO archivoDTO = null;
		if(idArchivo > 0){			
			String tabla = "tb_documentos";
			String query = "SELECT * FROM " + tabla ;
			query += " WHERE";
			query += " " + tabla + ".id_documento = " + idArchivo;
			if(database.openDatabase()){
				ResultSet rs; 

				try {
					System.out.println(query);
					rs = database.executeQuery(query);
					System.out.println("1");
					while (rs.next()) {
						archivoDTO = herramientasRS.inicializaArchivoDTO(rs, tabla);
						System.out.println("2");

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
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("idArchivo = 0 selectArchivoDTO");
		}
		
		return archivoDTO;
	}
	
	public int insertRegistroArchivoDTO(ArchivoDTO doc){
		int res = -1;
		if(doc != null){
			if(database.openDatabase()){
				String queryInsertRegistroArchivo = "INSERT INTO tb_documentos ("
						+ "nombre_doc"
						+ ", ruta_doc"
						+ ", fk_id_documento_tipo"
						+ ", fecha_validez_doc"						
						+ ", fecha_carga_doc"
						+ ", hora_carga_doc"
						+ ", usuario_insercion_doc"
						+ ", tamano_bytes_doc"
						+ ", extension_doc"
						+ ", status_doc"
						+ ") VALUES (";
				queryInsertRegistroArchivo += " '" + doc.getNombreDocumento() + "', '" + doc.getRutaDocumento() + "', " + doc.getIdDocumentoTipo() + ", '" + doc.getFechaValidezDocumento() 
				+ "', '" + doc.getFechaCargaDocumento() + "', '" + doc.getHoraCargaDocumento() + "', " 
				+ doc.getUsuarioDocumento() + ", " + doc.getTamanoDocumento() + ", '" + doc.getExtDocumento() + "', " + doc.getStatusDocumento() + ");" ;
				try {
					System.out.println("query registro archivo " + queryInsertRegistroArchivo);
					res = database.executeNonQuery(queryInsertRegistroArchivo);
					if(res > 0){
						String queryLastId = "SELECT LAST_INSERT_ID() AS ultimo_id;";
						ResultSet rs = database.executeQuery(queryLastId);
						if(rs != null){
							while (rs.next()) {
								res = rs.getInt("ultimo_id");								
							}
						}
					}
				} catch (SQLException e) {
					//						principal.getErrores().add("El empleado fulano de tal no ha sido encontrado! favor de revisar los datos ");
					e.printStackTrace();
				}
				if(database.closeDatabase()){
					System.out.println("Conexion cerrada en " + this.getClass().getSimpleName());
				}else{
					System.out.println("Conexion NO cerrada en " + this.getClass().getSimpleName());
				}
			}else{
				System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
			}
		}else{
			System.out.println("Documento vacio o nulo");
		}
		return res;
	}
	
	public Vector<DocumentoHorarioDTO> selectDocumentosHorarios(){
		Vector<DocumentoHorarioDTO> docs = null;
		if(database.openDatabase()){
			System.out.println("Conexion abierta en " + this.getClass().getSimpleName());
			String query = "SELECT * FROM tb_documentos";
			ResultSet rs; 
			try {
//				System.out.println(query);
				rs = database.executeQuery(query);
				docs = new Vector<DocumentoHorarioDTO>();
				while (rs.next()) {
					DocumentoHorarioDTO doc = herramientasRS.inicializaDocumentoHorarioDTO(rs);
					if(doc != null){
						docs.add(doc);
					}else{
						System.out.println("empleadoHorario == NULL");
					}
					
				}//TERMINA CICLO WHILE
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
		return docs;
	}
	
	
	
	
	
}
