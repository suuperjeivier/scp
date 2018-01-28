package dto.listados.items;

import dto.listados.TipoEstatusDTO;
import dto.user.UserSimpleDTO;
import herramientas.FechaHoraDTO;

public class ItemDTO {
	private TipoItemDTO tipoItem;
	private int idItem;
	private String skuItem;
	private String nombreItem;
	private String noSerieItem;
	private String placasItem;
	private String categoriaItem;
	private ModeloItemDTO modeloItem;
	private String colorItem;
	private String tamanoItem;
	private MarcaItemDTO marcaItem;
	private String formaItem;
	private LineaItemDTO lineaItem;
	private boolean itemAsignadoItem;
	private int asignacionItem;
	private String string1Item;	
	private FechaHoraDTO fechaHoraActualizacion;
	private FechaHoraDTO fechaHoraCreacion;
	private UserSimpleDTO usuarioCreacion;
	private UserSimpleDTO usuarioActualizacion;
	private UserSimpleDTO usuarioRecepcion;
	private String comentariosItem;
	private TipoEstatusDTO situacionItemDTO;
	private TipoEstatusDTO ubicacionItemDTO;
	private int idContabilidadRecursoItem;
	private FechaHoraDTO fechaHoraRecepcionItem;
	private String contratoItem;
	private String proyectoItem;
	private TipoEstatusDTO statusItem;
	
	public ItemDTO(){
		setTipoItem(new TipoItemDTO());
		setSituacionItemDTO(new TipoEstatusDTO());
		setUbicacionItemDTO(new TipoEstatusDTO());
		setStatusItem(new TipoEstatusDTO());
		setFechaHoraActualizacion(new FechaHoraDTO());
		setFechaHoraCreacion(new FechaHoraDTO());
		setFechaHoraRecepcionItem(new FechaHoraDTO());
		setUsuarioCreacion(new UserSimpleDTO());
		setUsuarioActualizacion(new UserSimpleDTO());
		setUsuarioRecepcion(new UserSimpleDTO());
		setModeloItem(new ModeloItemDTO());
		setLineaItem(new LineaItemDTO());
		setMarcaItem(new MarcaItemDTO());
	}
	
	
	/**
	 * @return the idItem
	 */
	public int getIdItem() {
		return idItem;
	}
	/**
	 * @param idItem the idItem to set
	 */
	public void setIdItem(int idItem) {
		this.idItem = idItem;
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
	 * @return the skuItem
	 */
	public String getSkuItem() {
		return skuItem;
	}
	/**
	 * @param skuItem the skuItem to set
	 */
	public void setSkuItem(String skuItem) {
		this.skuItem = skuItem;
	}
	/**
	 * @return the nombreItem
	 */
	public String getNombreItem() {
		return nombreItem;
	}
	/**
	 * @param nombreItem the nombreItem to set
	 */
	public void setNombreItem(String nombreItem) {
		this.nombreItem = nombreItem;
	}
	/**
	 * @return the noSerieItem
	 */
	public String getNoSerieItem() {
		return noSerieItem;
	}
	/**
	 * @param noSerieItem the noSerieItem to set
	 */
	public void setNoSerieItem(String noSerieItem) {
		this.noSerieItem = noSerieItem;
	}
	/**
	 * @return the placasItem
	 */
	public String getPlacasItem() {
		return placasItem;
	}
	/**
	 * @param placasItem the placasItem to set
	 */
	public void setPlacasItem(String placasItem) {
		this.placasItem = placasItem;
	}
	/**
	 * @return the categoriaItem
	 */
	public String getCategoriaItem() {
		return categoriaItem;
	}
	/**
	 * @param categoriaItem the categoriaItem to set
	 */
	public void setCategoriaItem(String categoriaItem) {
		this.categoriaItem = categoriaItem;
	}	
	/**
	 * @return the colorItem
	 */
	public String getColorItem() {
		return colorItem;
	}
	/**
	 * @param colorItem the colorItem to set
	 */
	public void setColorItem(String colorItem) {
		this.colorItem = colorItem;
	}
	/**
	 * @return the tamanoItem
	 */
	public String getTamanoItem() {
		return tamanoItem;
	}
	/**
	 * @param tamanoItem the tamanoItem to set
	 */
	public void setTamanoItem(String tamanoItem) {
		this.tamanoItem = tamanoItem;
	}
	/**
	 * @return the marcaItem
	 */
	public MarcaItemDTO getMarcaItem() {
		return marcaItem;
	}
	/**
	 * @param marcaItem the marcaItem to set
	 */
	public void setMarcaItem(MarcaItemDTO marcaItem) {
		this.marcaItem = marcaItem;
	}
	/**
	 * @return the formaItem
	 */
	public String getFormaItem() {
		return formaItem;
	}
	/**
	 * @param formaItem the formaItem to set
	 */
	public void setFormaItem(String formaItem) {
		this.formaItem = formaItem;
	}
	/**
	 * @return the lineaItem
	 */
	public LineaItemDTO getLineaItem() {
		return lineaItem;
	}
	/**
	 * @param lineaItem the lineaItem to set
	 */
	public void setLineaItem(LineaItemDTO lineaItem) {
		this.lineaItem = lineaItem;
	}
	/**
	 * @return the itemAsignado
	 */
	public boolean isItemAsignadoItem() {
		return itemAsignadoItem;
	}
	/**
	 * @param itemAsignado the itemAsignado to set
	 */
	public void setItemAsignadoItem(boolean itemAsignadoItem) {
		this.itemAsignadoItem = itemAsignadoItem;
	}
	/**
	 * @return the asignacionItem
	 */
	public int getAsignacionItem() {
		return asignacionItem;
	}
	/**
	 * @param asignacionItem the asignacionItem to set
	 */
	public void setAsignacionItem(int asignacionItem) {
		this.asignacionItem = asignacionItem;
	}
	/**
	 * @return the string1
	 */
	public String getString1Item() {
		return string1Item;
	}
	/**
	 * @param string1 the string1 to set
	 */
	public void setString1Item(String string1Item) {
		this.string1Item = string1Item;
	}

	/**
	 * @return the comentariosItem
	 */
	public String getComentariosItem() {
		return comentariosItem;
	}
	/**
	 * @param comentariosItem the comentariosItem to set
	 */
	public void setComentariosItem(String comentariosItem) {
		this.comentariosItem = comentariosItem;
	}
	/**
	 * @return the situacionItemDTO
	 */
	public TipoEstatusDTO getSituacionItemDTO() {
		return situacionItemDTO;
	}
	/**
	 * @param situacionItemDTO the situacionItemDTO to set
	 */
	public void setSituacionItemDTO(TipoEstatusDTO situacionItemDTO) {
		this.situacionItemDTO = situacionItemDTO;
	}
	/**
	 * @return the ubicacionItemDTO
	 */
	public TipoEstatusDTO getUbicacionItemDTO() {
		return ubicacionItemDTO;
	}
	/**
	 * @param ubicacionItemDTO the ubicacionItemDTO to set
	 */
	public void setUbicacionItemDTO(TipoEstatusDTO ubicacionItemDTO) {
		this.ubicacionItemDTO = ubicacionItemDTO;
	}
	/**
	 * @return the idContabilidadRecurso
	 */
	public int getIdContabilidadRecursoItem() {
		return idContabilidadRecursoItem;
	}
	/**
	 * @param idContabilidadRecurso the idContabilidadRecurso to set
	 */
	public void setIdContabilidadRecursoItem(int idContabilidadRecursoItem) {
		this.idContabilidadRecursoItem = idContabilidadRecursoItem;
	}	
	/**
	 * @return the contratoItem
	 */
	public String getContratoItem() {
		return contratoItem;
	}
	/**
	 * @param contratoItem the contratoItem to set
	 */
	public void setContratoItem(String contratoItem) {
		this.contratoItem = contratoItem;
	}
	/**
	 * @return the proyectoItem
	 */
	public String getProyectoItem() {
		return proyectoItem;
	}
	/**
	 * @param proyectoItem the proyectoItem to set
	 */
	public void setProyectoItem(String proyectoItem) {
		this.proyectoItem = proyectoItem;
	}
	/**
	 * @return the statusItem
	 */
	public TipoEstatusDTO getStatusItem() {
		return statusItem;
	}
	/**
	 * @param statusItem the statusItem to set
	 */
	public void setStatusItem(TipoEstatusDTO statusItem) {
		this.statusItem = statusItem;
	}


	/**
	 * @return the fechaHoraActualizacion
	 */
	public FechaHoraDTO getFechaHoraActualizacion() {
		return fechaHoraActualizacion;
	}


	/**
	 * @param fechaHoraActualizacion the fechaHoraActualizacion to set
	 */
	public void setFechaHoraActualizacion(FechaHoraDTO fechaHoraActualizacion) {
		this.fechaHoraActualizacion = fechaHoraActualizacion;
	}


	/**
	 * @return the fechaHoraCreacion
	 */
	public FechaHoraDTO getFechaHoraCreacion() {
		return fechaHoraCreacion;
	}


	/**
	 * @param fechaHoraCreacion the fechaHoraCreacion to set
	 */
	public void setFechaHoraCreacion(FechaHoraDTO fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
	}


	/**
	 * @return the usuarioCreacion
	 */
	public UserSimpleDTO getUsuarioCreacion() {
		return usuarioCreacion;
	}


	/**
	 * @param usuarioCreacion the usuarioCreacion to set
	 */
	public void setUsuarioCreacion(UserSimpleDTO usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}


	/**
	 * @return the usuarioActualizacion
	 */
	public UserSimpleDTO getUsuarioActualizacion() {
		return usuarioActualizacion;
	}


	/**
	 * @param usuarioActualizacion the usuarioActualizacion to set
	 */
	public void setUsuarioActualizacion(UserSimpleDTO usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}


	/**
	 * @return the usuarioRecepcion
	 */
	public UserSimpleDTO getUsuarioRecepcion() {
		return usuarioRecepcion;
	}


	/**
	 * @param usuarioRecepcion the usuarioRecepcion to set
	 */
	public void setUsuarioRecepcion(UserSimpleDTO usuarioRecepcion) {
		this.usuarioRecepcion = usuarioRecepcion;
	}


	/**
	 * @return the fechaHoraRecepcionItem
	 */
	public FechaHoraDTO getFechaHoraRecepcionItem() {
		return fechaHoraRecepcionItem;
	}


	/**
	 * @param fechaHoraRecepcionItem the fechaHoraRecepcionItem to set
	 */
	public void setFechaHoraRecepcionItem(FechaHoraDTO fechaHoraRecepcionItem) {
		this.fechaHoraRecepcionItem = fechaHoraRecepcionItem;
	}


	/**
	 * @return the modeloItem
	 */
	public ModeloItemDTO getModeloItem() {
		return modeloItem;
	}


	/**
	 * @param modeloItem the modeloItem to set
	 */
	public void setModeloItem(ModeloItemDTO modeloItem) {
		this.modeloItem = modeloItem;
	}
	
}
