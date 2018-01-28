package dto.listados;

import dto.empleado.EmpleadoDTO;

public class AreaEmpleadoDTO {

	private AreaDTO areaEmpleado;
	private EmpleadoDTO empleadoArea;
	private String extTelArea;
	private String marcadoRapidoEmpleado;
	private int rolEmpleado;
	private CorporacionDTO corporacionEmpleado;
	
	public AreaDTO getAreaEmpleado() {
		return areaEmpleado;
	}
	
	public void setAreaEmpleado(AreaDTO areaEmpleado) {
		this.areaEmpleado = areaEmpleado;
	}

	public EmpleadoDTO getEmpleadoArea() {
		return empleadoArea;
	}

	public void setEmpleadoArea(EmpleadoDTO empleadoArea) {
		this.empleadoArea = empleadoArea;
	}

	public String getExtTelArea() {
		return extTelArea;
	}

	public void setExtTelArea(String extTelArea) {
		this.extTelArea = extTelArea;
	}

	public String getMarcadoRapidoEmpleado() {
		return marcadoRapidoEmpleado;
	}

	public void setMarcadoRapidoEmpleado(String marcadoRapidoEmpleado) {
		this.marcadoRapidoEmpleado = marcadoRapidoEmpleado;
	}

	public int getRolEmpleado() {
		return rolEmpleado;
	}

	public void setRolEmpleado(int rolEmpleado) {
		this.rolEmpleado = rolEmpleado;
	}

	public CorporacionDTO getCorporacionEmpleado() {
		return corporacionEmpleado;
	}

	public void setCorporacionEmpleado(CorporacionDTO corporacionEmpleado) {
		this.corporacionEmpleado = corporacionEmpleado;
	}
	
	
	
}