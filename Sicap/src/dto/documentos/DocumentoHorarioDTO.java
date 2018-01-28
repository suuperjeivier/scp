package dto.documentos;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

public class DocumentoHorarioDTO {
	private int idDocumentoHorarioEmpleados;
	private String nombreDocumento;
	private String rutaDocumento;
	private LocalDate fechaCargaDocumento;
	private LocalDate fechaValidezDocumento;
	private int usuarioDocumento;
	private LocalTime horaCargaDocumento;
	private long tamanoDocumento;
	private String extDocumento;
	private int statusDocumento;
	private File documento;
	
	
	/**
	 * @return the idDocumentoHorarioEmpleados
	 */
	public int getIdDocumentoHorarioEmpleados() {
		return idDocumentoHorarioEmpleados;
	}
	/**
	 * @param idDocumentoHorarioEmpleados the idDocumentoHorarioEmpleados to set
	 */
	public void setIdDocumentoHorarioEmpleados(int idDocumentoHorarioEmpleados) {
		this.idDocumentoHorarioEmpleados = idDocumentoHorarioEmpleados;
	}
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	public String getRutaDocumento() {
		return rutaDocumento;
	}
	public void setRutaDocumento(String rutaDocumento) {
		this.rutaDocumento = rutaDocumento;
	}
	public LocalDate getFechaCargaDocumento() {
		return fechaCargaDocumento;
	}
	public void setFechaCargaDocumento(LocalDate fechaCargaDocumento) {
		this.fechaCargaDocumento = fechaCargaDocumento;
	}
	
	public int getUsuarioDocumento() {
		return usuarioDocumento;
	}
	public void setUsuarioDocumento(int usuarioDocumento) {
		this.usuarioDocumento = usuarioDocumento;
	}
	public LocalTime getHoraCargaDocumento() {
		return horaCargaDocumento;
	}
	public void setHoraCargaDocumento(LocalTime horaCargaDocumento) {
		this.horaCargaDocumento = horaCargaDocumento;
	}
	public long getTamanoDocumento() {
		return tamanoDocumento;
	}
	public void setTamanoDocumento(long tamanoDocumento) {
		this.tamanoDocumento = tamanoDocumento;
	}
	public String getExtDocumento() {
		return extDocumento;
	}
	public void setExtDocumento(String extDocumento) {
		this.extDocumento = extDocumento;
	}
	public int getStatusDocumento() {
		return statusDocumento;
	}
	public void setStatusDocumento(int statusDocumento) {
		this.statusDocumento = statusDocumento;
	}
	public File getDocumento() {
		return documento;
	}
	public void setDocumento(File documento) {
		this.documento = documento;
	}
	public LocalDate getFechaValidezDocumento() {
		return fechaValidezDocumento;
	}
	public void setFechaValidezDocumento(LocalDate fechaValidezDocumento) {
		this.fechaValidezDocumento = fechaValidezDocumento;
	}
	
}
