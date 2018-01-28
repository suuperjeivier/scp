package dto.ordenes;

public class OrdenLugarDTO {

	private OrdenDTO ordenDTO;
	private LugarDTO lugarDTO;
	private String actividad;
	private boolean activo;
	
	public OrdenLugarDTO(){
		setLugarDTO(new LugarDTO());
		setOrdenDTO(new OrdenDTO());
	}
	
	/**
	 * @return the ordenDTO
	 */
	public OrdenDTO getOrdenDTO() {
		return ordenDTO;
	}
	/**
	 * @param ordenDTO the ordenDTO to set
	 */
	public void setOrdenDTO(OrdenDTO ordenDTO) {
		this.ordenDTO = ordenDTO;
	}
	/**
	 * @return the lugarDTO
	 */
	public LugarDTO getLugarDTO() {
		return lugarDTO;
	}
	/**
	 * @param lugarDTO the lugarDTO to set
	 */
	public void setLugarDTO(LugarDTO lugarDTO) {
		this.lugarDTO = lugarDTO;
	}

	/**
	 * @return the actividad
	 */
	public String getActividad() {
		return actividad;
	}

	/**
	 * @param actividad the actividad to set
	 */
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * @param activo the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	
	
}
