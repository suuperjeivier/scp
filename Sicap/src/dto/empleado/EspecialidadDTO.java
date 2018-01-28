package dto.empleado;

public class EspecialidadDTO {
	
	private int idEspecialidad;
	private String nombreEspecialidad;
	private NivelAcademicoDTO nivelAcademicoDTO;
	
	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	public String getNombreEspecialidad() {
		return nombreEspecialidad;
	}
	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}
	public NivelAcademicoDTO getNivelAcademicoDTO() {
		return nivelAcademicoDTO;
	}
	public void setNivelAcademicoDTO(NivelAcademicoDTO nivelAcademicoDTO) {
		this.nivelAcademicoDTO = nivelAcademicoDTO;
	}

}
