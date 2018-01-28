package dto.ordenes;

public class OrdenRadioDTO {

	private OrdenDTO ordenDTO;
	private RadioDTO radioDTO;
	private boolean activo;
	
	public OrdenRadioDTO(){
		setOrdenDTO(new OrdenDTO());
		setRadioDTO(new RadioDTO());
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
	 * @return the radioDTO
	 */
	public RadioDTO getRadioDTO() {
		return radioDTO;
	}

	/**
	 * @param radioDTO the radioDTO to set
	 */
	public void setRadioDTO(RadioDTO radioDTO) {
		this.radioDTO = radioDTO;
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
