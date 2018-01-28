package dto.listados;

public class AreaDTO {
	
	private int idArea;
	private String nombreArea;
	private DireccionAdminDTO direccionAdmin;
	private String cveArea;
	private String descripcionArea;
	private String extTelArea;
	
	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	
	public String getNombreArea() {
		return nombreArea;
	}
	
	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public DireccionAdminDTO getDireccionAdmin() {
		return direccionAdmin;
	}

	public void setDireccionAdmin(DireccionAdminDTO direccionAdmin) {
		this.direccionAdmin = direccionAdmin;
	}

	public String getCveArea() {
		return cveArea;
	}

	public void setCveArea(String cveArea) {
		this.cveArea = cveArea;
	}

	public String getDescripcionArea() {
		return descripcionArea;
	}

	public void setDescripcionArea(String descripcionArea) {
		this.descripcionArea = descripcionArea;
	}

	public String getExtTelArea() {
		return extTelArea;
	}

	public void setExtTelArea(String extTelArea) {
		this.extTelArea = extTelArea;
	}

	

}
