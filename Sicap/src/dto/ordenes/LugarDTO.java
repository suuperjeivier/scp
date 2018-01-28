package dto.ordenes;

public class LugarDTO {
	
	private int lugarId;
	private String nombre;
	private String ubicacion;
	private String tipo;
	/**
	 * @return the lugarId
	 */
	public int getLugarId() {
		return lugarId;
	}
	/**
	 * @param lugarId the lugarId to set
	 */
	public void setLugarId(int lugarId) {
		this.lugarId = lugarId;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the ubicacion
	 */
	public String getUbicacion() {
		return ubicacion;
	}
	/**
	 * @param ubicacion the ubicacion to set
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
