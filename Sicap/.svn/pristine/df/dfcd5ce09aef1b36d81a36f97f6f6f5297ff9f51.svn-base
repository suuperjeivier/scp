package dto.ordenes;

import java.io.InputStream;
import java.sql.Blob;

import herramientas.FechaHoraDTO;

public class DocumentoDTO {
	
	private int documentoId;
	private OrdenDTO ordenDTO;
	private Blob archivo;
	private InputStream archivoIS;
	private byte[] archivoEnByteArray;
	private FechaHoraDTO fechaHoraCargaDTO;
	private String nombreArchivo;
	private String descripcion;
	private boolean activo;
	
	public DocumentoDTO(){
		setOrdenDTO(new OrdenDTO());
		setFechaHoraCargaDTO(new FechaHoraDTO());
	}
	
	/**
	 * @return the documentoId
	 */
	public int getDocumentoId() {
		return documentoId;
	}
	/**
	 * @param documentoId the documentoId to set
	 */
	public void setDocumentoId(int documentoId) {
		this.documentoId = documentoId;
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
	 * @return the fechaHoraCargaDTO
	 */
	public FechaHoraDTO getFechaHoraCargaDTO() {
		return fechaHoraCargaDTO;
	}
	/**
	 * @param fechaHoraCargaDTO the fechaHoraCargaDTO to set
	 */
	public void setFechaHoraCargaDTO(FechaHoraDTO fechaHoraCargaDTO) {
		this.fechaHoraCargaDTO = fechaHoraCargaDTO;
	}
	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	/**
	 * @param nombreArchivo the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	/**
	 * @return the archivo
	 */
	public Blob getArchivo() {
		return archivo;
	}
	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(Blob archivo) {
		this.archivo = archivo;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the archivoIS
	 */
	public InputStream getArchivoIS() {
		return archivoIS;
	}

	/**
	 * @param archivoIS the archivoIS to set
	 */
	public void setArchivoIS(InputStream archivoIS) {
		this.archivoIS = archivoIS;
	}

	/**
	 * @return the archivoEnByteArray
	 */
	public byte[] getArchivoEnByteArray() {
		return archivoEnByteArray;
	}

	/**
	 * @param archivoEnByteArray the archivoEnByteArray to set
	 */
	public void setArchivoEnByteArray(byte[] archivoEnByteArray) {
		this.archivoEnByteArray = archivoEnByteArray;
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
