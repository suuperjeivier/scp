package dto.ordenes;

import herramientas.FechaDTO;

public class VehiculoDTO {
	private int vehiculoId;
	private String nombre;
	private String tipo;
	private String marca;
	private String linea;
	private String modelo;
	private String placas;
	private String inventario;
	private String numPolizaSeguro;
	private FechaDTO fechaVencimientoSeguro;
	private char activo;
	
	public VehiculoDTO(){
		this.setFechaVencimientoSeguro(new FechaDTO());
	}
	
	/**
	 * @return the vehiculoId
	 */
	public int getVehiculoId() {
		return vehiculoId;
	}
	/**
	 * @param vehiculoId the vehiculoId to set
	 */
	public void setVehiculoId(int vehiculoId) {
		this.vehiculoId = vehiculoId;
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
	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * @return the linea
	 */
	public String getLinea() {
		return linea;
	}
	/**
	 * @param linea the linea to set
	 */
	public void setLinea(String linea) {
		this.linea = linea;
	}
	/**
	 * @return the modelo
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * @param modelo the modelo to set
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * @return the placas
	 */
	public String getPlacas() {
		return placas;
	}
	/**
	 * @param placas the placas to set
	 */
	public void setPlacas(String placas) {
		this.placas = placas;
	}
	/**
	 * @return the inventario
	 */
	public String getInventario() {
		return inventario;
	}
	/**
	 * @param inventario the inventario to set
	 */
	public void setInventario(String inventario) {
		this.inventario = inventario;
	}
	/**
	 * @return the activo
	 */
	public char getActivo() {
		return activo;
	}
	/**
	 * @param activo the activo to set
	 */
	public void setActivo(char activo) {
		this.activo = activo;
	}
	/**
	 * @return the numPolizaSeguro
	 */
	public String getNumPolizaSeguro() {
		return numPolizaSeguro;
	}
	/**
	 * @param numPolizaSeguro the numPolizaSeguro to set
	 */
	public void setNumPolizaSeguro(String numPolizaSeguro) {
		this.numPolizaSeguro = numPolizaSeguro;
	}
	/**
	 * @return the fechaVencimientoSeguro
	 */
	public FechaDTO getFechaVencimientoSeguro() {
		return fechaVencimientoSeguro;
	}
	/**
	 * @param fechaVencimientoSeguro the fechaVencimientoSeguro to set
	 */
	public void setFechaVencimientoSeguro(FechaDTO fechaVencimientoSeguro) {
		this.fechaVencimientoSeguro = fechaVencimientoSeguro;
	}
}
