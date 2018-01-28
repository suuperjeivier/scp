package dao.listados;

import java.sql.SQLException;

import dao.DatabaseGateway;
import dto.listados.items.ModeloItemDTO;
import herramientas.herrramientasrs.HerramientasResultSet;

public class ListadosDAOInsertUpdate {
	
		private DatabaseGateway database = null;
		private HerramientasResultSet herramientasRS = null;
		
		/**
		 * Construye la clase sin paramentros
		 */
		public ListadosDAOInsertUpdate(){
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
		public ListadosDAOInsertUpdate(DatabaseGateway database){
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
		
		
		public int insertModeloItemDTO(ModeloItemDTO modelo){	
			int res = 0;
			if(getDatabase().openDatabase()){
				System.out.println("conexion abierta en " + this.getClass().getSimpleName());				
				String tabla = "tb_items_tipos_modelos";
				String query= "INSERT INTO "+tabla;
				query += "("
							+ "sku_modelo"
							+ ", nombre_modelo"
							+ ", fk_id_tipo_item"
							+ ", status"
						+ ")"
					+ "VALUES("
							+ modelo.getSkuModeloItem()
							+ ",'" + modelo.getNombreModeloItem() + "'"
							+ ", " + modelo.getTipoItem().getIdTipoItem()
							+ ", " + modelo.getStatusModelo()
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
	

}
