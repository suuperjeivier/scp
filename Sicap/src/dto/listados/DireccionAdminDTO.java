package dto.listados;

public class DireccionAdminDTO {
	private String nombreDireccion;
	private String cveDireccion;
	private CorporacionDTO corporacion;
	
	public String getNombreDireccion() {
		return nombreDireccion;
	}
	
	public void setNombreDireccion(String nombreDireccion) {
		this.nombreDireccion = nombreDireccion;
	}

	public String getCveDireccion() {
		return cveDireccion;
	}

	public void setCveDireccion(String cveDireccion) {
		this.cveDireccion = cveDireccion;
	}

	public CorporacionDTO getCorporacion() {
		return corporacion;
	}

	public void setCorporacion(CorporacionDTO corporacion) {
		this.corporacion = corporacion;
	}

}
