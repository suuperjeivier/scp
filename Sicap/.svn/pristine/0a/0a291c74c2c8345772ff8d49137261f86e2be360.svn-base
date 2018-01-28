package dto.user.perfil;

import dto.listados.TipoEstatusDTO;

public class PerfilDTO {
	private int idPerfil;
	private String description;
	private int status;
	private TipoEstatusDTO estatusPerfilDTO;
	
	/**
	 * CONSTRUCTOR
	 */
	public PerfilDTO(){
		this.setIdPerfil(-1);
		if(this.getEstatusDTO() == null){
			this.setEstatusDTO(new TipoEstatusDTO());
		}
	}
	
	public int getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String descripccion) {
		this.description = descripccion;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public TipoEstatusDTO getEstatusDTO() {
		return estatusPerfilDTO;
	}
	public void setEstatusDTO(TipoEstatusDTO estatusDTO) {
		this.estatusPerfilDTO = estatusDTO;
	}


}
