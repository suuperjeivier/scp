package dto.ordenes;

public class TecnicoDTO {

	private int tecnicoId;
	private String iniciales;
	private String titulo;
	private String nombre;
	private String puesto;
	private DepartamentoDTO departamentoDTO;
	private String mando;
	private String licencia;
	
	public TecnicoDTO(){
		setDepartamentoDTO(new DepartamentoDTO());
	}
	
	/**
	 * @return the tecnicoId
	 */
	public int getTecnicoId() {
		return tecnicoId;
	}
	/**
	 * @param tecnicoId the tecnicoId to set
	 */
	public void setTecnicoId(int tecnicoId) {
		this.tecnicoId = tecnicoId;
	}
	/**
	 * @return the iniciales
	 */
	public String getIniciales() {
		return iniciales;
	}
	/**
	 * @param iniciales the iniciales to set
	 */
	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	 * @return the puesto
	 */
	public String getPuesto() {
		return puesto;
	}
	/**
	 * @param puesto the puesto to set
	 */
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	/**
	 * @return the departamentoDTO
	 */
	public DepartamentoDTO getDepartamentoDTO() {
		return departamentoDTO;
	}
	/**
	 * @param departamentoDTO the departamentoDTO to set
	 */
	public void setDepartamentoDTO(DepartamentoDTO departamentoDTO) {
		this.departamentoDTO = departamentoDTO;
	}
	/**
	 * @return the mando
	 */
	public String getMando() {
		return mando;
	}
	/**
	 * @param mando the mando to set
	 */
	public void setMando(String mando) {
		this.mando = mando;
	}
	/**
	 * @return the licencia
	 */
	public String getLicencia() {
		return licencia;
	}
	/**
	 * @param licencia the licencia to set
	 */
	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}
	
	
	
}
