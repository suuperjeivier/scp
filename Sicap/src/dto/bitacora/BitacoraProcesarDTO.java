package dto.bitacora;

import dto.documentos.DocumentoHorarioDTO;
import dto.empleado.EmpleadoHorarioDTO;

public class BitacoraProcesarDTO extends BitacoraDTO {
	private EmpleadoHorarioDTO empleadoHorario;
	private DocumentoHorarioDTO documentoHorario;
	
	public BitacoraProcesarDTO(){
		super();
	}

	public EmpleadoHorarioDTO getEmpleadoHorario() {
		return empleadoHorario;
	}

	public void setEmpleadoHorario(EmpleadoHorarioDTO empleadoHorario) {
		this.empleadoHorario = empleadoHorario;
	}

	public DocumentoHorarioDTO getDocumentoHorario() {
		return documentoHorario;
	}

	public void setDocumentoHorario(DocumentoHorarioDTO documentoHorario) {
		this.documentoHorario = documentoHorario;
	}
	
	
}
