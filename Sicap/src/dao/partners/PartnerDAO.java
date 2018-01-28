package dao.partners;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import dao.DatabaseGateway;
import dto.partner.PartnerDTO;
import herramientas.herrramientasrs.HerramientasResultSet;

public class PartnerDAO {
	private DatabaseGateway database;
	private HerramientasResultSet herramientasResultSet;
	
	/**
	 * 
	 */
	public PartnerDAO(){
		if(this.getDatabase() == null){
			this.setDatabase(new DatabaseGateway());
		}			
		if(getHerramientasResultSet() == null){
			setHerramientasResultSet(new HerramientasResultSet());
		}
	}
	
	public Vector<PartnerDTO> selectPartnersPorTipo(int tipoPartner){
		Vector<PartnerDTO> partners = null;		
		if(getDatabase().openDatabase()){
			partners = new Vector<PartnerDTO>();
			ResultSet rs = null;
			String query = "SELECT * FROM tb_partners WHERE fk_id_tipo_partner = " + tipoPartner;
			try {
				rs = getDatabase().executeQuery(query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if(rs != null){
				try {
					while (rs.next()) {		
						PartnerDTO partner = new PartnerDTO();
						partner = getHerramientasResultSet().inicializaPartnerSimple(rs);
						partners.add(partner);
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				System.out.println("rs == nullo");
			}
			if(getDatabase().closeDatabase()){
				System.out.println("conexion cerrada en " + this.getClass().getSimpleName());
			}else{
				System.out.println("conexion no cerrada en " + this.getClass().getSimpleName());
			}	
		}else{
			System.out.println("conexion no abierta en " + this.getClass().getSimpleName());
		}
		
		return partners;
		
	}

	/**
	 * @return the herramientasResultSet
	 */
	public HerramientasResultSet getHerramientasResultSet() {
		return herramientasResultSet;
	}

	/**
	 * @param herramientasResultSet the herramientasResultSet to set
	 */
	public void setHerramientasResultSet(HerramientasResultSet herramientasResultSet) {
		this.herramientasResultSet = herramientasResultSet;
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
