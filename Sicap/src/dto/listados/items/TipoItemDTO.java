package dto.listados.items;

public class TipoItemDTO {
	private int idTipoItem;
	private String nombreTipoItem;
	private String nombreTipoItemSingular;
	private int status;
	/**
	 * @return the idTipoItem
	 */
	public int getIdTipoItem() {
		return idTipoItem;
	}
	/**
	 * @param idTipoItem the idTipoItem to set
	 */
	public void setIdTipoItem(int idTipoItem) {
		this.idTipoItem = idTipoItem;
	}
	/**
	 * @return the nombreTipoItem
	 */
	public String getNombreTipoItem() {
		return nombreTipoItem;
	}
	/**
	 * @param nombreTipoItem the nombreTipoItem to set
	 */
	public void setNombreTipoItem(String nombreTipoItem) {
		this.nombreTipoItem = nombreTipoItem;
	}
	/**
	 * @return the nombreTipoItemsSingular
	 */
	public String getNombreTipoItemSingular() {
		return nombreTipoItemSingular;
	}
	/**
	 * @param nombreTipoItemsSingular the nombreTipoItemsSingular to set
	 */
	public void setNombreTipoItemSingular(String nombreTipoItemSingular) {
		this.nombreTipoItemSingular = nombreTipoItemSingular;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
}
