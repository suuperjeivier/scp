package dto.ordenes;

import java.sql.Blob;

public class ImagenDTO {
	
	private int imagenId;
	private OrdenDTO ordenDTO;
	private LugarDTO lugarDTO;
	private Blob archivo;
	private String descripcion;
	
	public ImagenDTO(){
		setOrdenDTO(new OrdenDTO());
		setLugarDTO(new LugarDTO());
	}
	
	/**
	 * @return the imagenId
	 */
	public int getImagenId() {
		return imagenId;
	}
	/**
	 * @param imagenId the imagenId to set
	 */
	public void setImagenId(int imagenId) {
		this.imagenId = imagenId;
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
		
}
