package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class DatabaseGateway5_Sist_Per {	
	private Connection con;
	private String dbServerHostname;
	private String dbServerPort;
	private String dbName;
	private String driverUriPrefix;
	private String driverName;
	private String bdUserName;
	protected String bdPassword;
	private boolean sqlServer;
	
	public DatabaseGateway5_Sist_Per(){
		this.setDriverUriPrefix("jdbc:sqlserver://");
		this.setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		this.setDbServerHostname("172.16.21.228");//local 10.30.1.31//db_serv172.16.21.198
		this.setDbServerPort("1433");
		this.setDbName("Sistema_Personal");
		this.setBdUserName("reportes");//yhernandeza//appuser
		this.setBdPassword("reportes");//yhernandeza//c4tabasco
		this.setSqlServer(true);
		
	}
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public String getDbServerHostname() {
		return dbServerHostname;
	}

	public void setDbServerHostname(String dbServerHostname) {
		this.dbServerHostname = dbServerHostname;
	}

	public String getDbServerPort() {
		return dbServerPort;
	}

	public void setDbServerPort(String dbServerPort) {
		this.dbServerPort = dbServerPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDriverUriPrefix() {
		return driverUriPrefix;
	}

	public void setDriverUriPrefix(String driverUriPrefix) {
		this.driverUriPrefix = driverUriPrefix;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getBdUserName() {
		return bdUserName;
	}

	public void setBdUserName(String bdUserName) {
		this.bdUserName = bdUserName;
	}

	protected String getBdPassword() {
		return bdPassword;
	}

	protected void setBdPassword(String bdPassword) {
		this.bdPassword = bdPassword;
	}
	
	/**
	 * Crea una nueva conexion a la BD [Javier] 20170316
	 * @return boolean
	 */
	public boolean openDatabase(){
		boolean opened = false;
		setCon(getConnection());
		if(getCon() != null){
			try {
				if(getCon().isClosed()){
					opened = false;
				}else{
					opened = true;
				}
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
		else{
			opened = false;
		}
		return opened;
	}
	
	/**
	 * Cierra la conexion de esta instacia a la BD [Javier] 20170316
	 * @return boolean
	 */
	public boolean closeDatabase(){
		boolean closed = false;
		setCon(getConnection());
		if(getCon() != null){
			try {
				if(getCon().isClosed()){
					closed = true;
				}else{
					getCon().close();
					closed = true;
				}
			} catch (SQLException e) {				
				e.printStackTrace();
				closed = false;
			}
		}
		else{
			closed = true;
		}
		return closed;
	}
	
	/**
	 * Obtiene una nueva conexion al servidor de la BD a travez del driver de conexion [Javier] 20170316
	 * @return Connection
	 */
	public Connection getConnection(){
		String jdbcUrl = null;
		if(isSqlServer()){
			/**
			 * --->SQL SERVER<---
			 */		
			jdbcUrl = getDriverUriPrefix()+getDbServerHostname()+":"+getDbServerPort()+";databaseName="+ getDbName() + ";user="+getBdUserName()+";password="+getBdPassword()+";";
		}else{
			/**
			 * --->MySQL SERVER<---
			 */
			jdbcUrl = getDriverUriPrefix()+getDbServerHostname()+":"+getDbServerPort() + "/" + getDbName() + "?useSSL=false";		
		}
		try {
			Class.forName (getDriverName()).newInstance ();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
		if(getCon() != null){
			setCon(null);
		}
		try {
			if(isSqlServer()){
				/**
				 * --->SQL SERVER<---
				 */	
				setCon(DriverManager.getConnection(jdbcUrl));
			}else{
				/**
				 * --->MySQL SERVER<---
				 */
				setCon(DriverManager.getConnection(jdbcUrl, getBdUserName(), getBdPassword()));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getCon();
	}
	
	/**
	 * Ejecuta el query para realizar la operacion de (SELECT) en la BD [Javier] 20170316
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String query) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		if(query != null && !query.isEmpty()){			
			stmt = getCon().createStatement();
	        rs = stmt.executeQuery(query);	
		}
		return rs;
	}
	
	/**
	 * Executa el query para realizar la operacion de (INSERT, UPDATE, DELETE, DROP) en la BD [Javier] 20170316
	 * @param query
	 * @return int si se inserto la cantidad de filas afectadas por la operacion, si no 0 o menos (-1)
	 * @throws SQLException
	 */
	public int executeNonQuery(String query) throws SQLException{		
		int res = 0;
		if(query != null && !query.equals("") && query.length() > 0){		
			Statement stmt = null;
			stmt = getCon().createStatement();
	        res = stmt.executeUpdate(query);	
		}
		return res;
	}

	/**
	 * @return the sqlServer
	 */
	public boolean isSqlServer() {
		return sqlServer;
	}

	/**
	 * @param sqlServer the sqlServer to set
	 */
	public void setSqlServer(boolean sqlServer) {
		this.sqlServer = sqlServer;
	}

	
}//Termina clase