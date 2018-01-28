package dto.user.menu.submenu;

import dto.listados.TipoEstatusDTO;

public class SubMenuDTO {
	private int idAction;
	private int fkApp;	
	private String nombreMenu;
	private String nombreAction;
	private String urlAction;
	private String iconoAction;
	private int isHeader;
	private TipoEstatusDTO isHeaderSubmenuDTO;
	private int statusAction;
	private TipoEstatusDTO estatusSubmenuDTO;
	
	/**
	 * CONSTRUCTOR
	 */
	public SubMenuDTO(){
		this.setIdAction(-1);
		if(this.getEstatusSubmenuDTO() == null){
			this.setEstatusSubmenuDTO(new TipoEstatusDTO());
		}
		if(this.getIsHeaderSubmenuDTO() == null){
			this.setIsHeaderSubmenuDTO(new TipoEstatusDTO());
		}
	}
	
	public int getIdAction() {
		return idAction;
	}
	public void setIdAction(int idAction) {
		this.idAction = idAction;
	}
	public int getFkApp() {
		return fkApp;
	}
	public void setFkApp(int fkApp) {
		this.fkApp = fkApp;
	}
	public String getNombreAction() {
		return nombreAction;
	}
	public void setNombreAction(String nombreAction) {
		this.nombreAction = nombreAction;
	}
	public String getUrlAction() {
		return urlAction;
	}
	public void setUrlAction(String urlAction) {
		this.urlAction = urlAction;
	}
	public int getStatusAction() {
		return statusAction;
	}
	public void setStatusAction(int statusAction) {
		this.statusAction = statusAction;
	}
	public int getIsHeader() {
		return isHeader;
	}
	public void setIsHeader(int isHeader) {
		this.isHeader = isHeader;
	}
	public String getIconoAction() {
		return iconoAction;
	}
	public void setIconoAction(String iconoAction) {
		this.iconoAction = iconoAction;
	}
	public String getNombreMenu() {
		return nombreMenu;
	}
	public void setNombreMenu(String nombreMenu) {
		this.nombreMenu = nombreMenu;
	}
	public TipoEstatusDTO getEstatusSubmenuDTO() {
		return estatusSubmenuDTO;
	}
	public void setEstatusSubmenuDTO(TipoEstatusDTO estatusSubmenuDTO) {
		this.estatusSubmenuDTO = estatusSubmenuDTO;
	}

	public TipoEstatusDTO getIsHeaderSubmenuDTO() {
		return isHeaderSubmenuDTO;
	}

	public void setIsHeaderSubmenuDTO(TipoEstatusDTO isHeaderSubmenuDTO) {
		this.isHeaderSubmenuDTO = isHeaderSubmenuDTO;
	}

}
