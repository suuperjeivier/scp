package dto.listados.items;

public class MarcaItemDTO {
	
	private int idMarcaItem;
	private int skuMarcaItem;
	private String nombreMarcaItem;
	private TipoItemDTO tipoItem;
	/**
	 * @return the idMarcaItem
	 */
	public int getIdMarcaItem() {
		return idMarcaItem;
	}
	/**
	 * @param idMarcaItem the idMarcaItem to set
	 */
	public void setIdMarcaItem(int idMarcaItem) {
		this.idMarcaItem = idMarcaItem;
	}
	/**
	 * @return the skuMarcaItem
	 */
	public int getSkuMarcaItem() {
		return skuMarcaItem;
	}
	/**
	 * @param skuMarcaItem the skuMarcaItem to set
	 */
	public void setSkuMarcaItem(int skuMarcaItem) {
		this.skuMarcaItem = skuMarcaItem;
	}
	/**
	 * @return the nombreMarcaItem
	 */
	public String getNombreMarcaItem() {
		return nombreMarcaItem;
	}
	/**
	 * @param nombreMarcaItem the nombreMarcaItem to set
	 */
	public void setNombreMarcaItem(String nombreMarcaItem) {
		this.nombreMarcaItem = nombreMarcaItem;
	}
	/**
	 * @return the tipoItem
	 */
	public TipoItemDTO getTipoItem() {
		return tipoItem;
	}
	/**
	 * @param tipoItem the tipoItem to set
	 */
	public void setTipoItem(TipoItemDTO tipoItem) {
		this.tipoItem = tipoItem;
	}
	

}
