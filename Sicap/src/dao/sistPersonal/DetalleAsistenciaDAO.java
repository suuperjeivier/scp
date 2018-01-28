package dao.sistPersonal;

import dao.DatabaseGateway;
import herramientas.herrramientasrs.HerramientasResultSet;

public class DetalleAsistenciaDAO {
	private DatabaseGateway database;
	private HerramientasResultSet herramientasRS;
	
	/**
	 * Construye la clase sin parametros
	 */
	public DetalleAsistenciaDAO(){
		if(this.database == null){
			this.database = new DatabaseGateway();
		}else{
			System.out.println("database != null en " + this.getClass().getSimpleName() );
		}
		InitializeComponents();
	}

	/**
	 * Constrye la clase con la instancia de base de datos por parametro para reutilizar el objeto
	 */
	public DetalleAsistenciaDAO(DatabaseGateway database){
		if(this.database == null){
			if(database != null){
				this.database = database;
			}else{
				this.database = new DatabaseGateway();
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
		if (herramientasRS == null){
			herramientasRS = new HerramientasResultSet();
		}else{
			System.out.println("herramientasRS != null en " + this.getClass().getSimpleName() );
		}
	}
}