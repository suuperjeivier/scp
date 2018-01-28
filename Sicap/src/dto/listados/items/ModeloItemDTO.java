package dto.listados.items;

public class ModeloItemDTO {

	private int idModeloItem;
	private int skuModeloItem;
	private String nombreModeloItem;
	private TipoItemDTO tipoItem;
	private int statusModelo;
	
	public ModeloItemDTO(){
		this.setTipoItem(new TipoItemDTO());
	}
	
	
	/**
	 * @return the idModeloItem
	 */
	public int getIdModeloItem() {
		return idModeloItem;
	}
	/**
	 * @param idModeloItem the idModeloItem to set
	 */
	public void setIdModeloItem(int idModeloItem) {
		this.idModeloItem = idModeloItem;
	}
	/**
	 * @return the skuModeloItem
	 */
	public int getSkuModeloItem() {
		return skuModeloItem;
	}
	/**
	 * @param skuModeloItem the skuModeloItem to set
	 */
	public void setSkuModeloItem(int skuModeloItem) {
		this.skuModeloItem = skuModeloItem;
	}
	/**
	 * @return the nombreModeloItem
	 */
	public String getNombreModeloItem() {
		return nombreModeloItem;
	}
	/**
	 * @param nombreModeloItem the nombreModeloItem to set
	 */
	public void setNombreModeloItem(String nombreModeloItem) {
		this.nombreModeloItem = nombreModeloItem;
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


	/**
	 * @return the statusModelo
	 */
	public int getStatusModelo() {
		return statusModelo;
	}


	/**
	 * @param statusModelo the statusModelo to set
	 */
	public void setStatusModelo(int statusModelo) {
		this.statusModelo = statusModelo;
	}
	
	
}
