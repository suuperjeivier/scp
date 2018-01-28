package dto.user.menu;

import java.util.Vector;

import dto.listados.TipoEstatusDTO;
import dto.user.menu.submenu.SubMenuDTO;

public class MenuDTO {
	
	private int idApp;	
	private String urlApp;
	private String nombreApp;
	private String descApp;
	private int statusApp;
	private String iconoApp;
	private double indexApp;
	private Vector<SubMenuDTO> subMenu;
	private TipoEstatusDTO estatusMenuDTO;
	
	/**
	 * CONSTRUCTOR
	 */
	public MenuDTO(){
		this.setIdApp(-1);
		if(this.getEstatusMenuDTO() == null){
			this.setEstatusMenuDTO(new TipoEstatusDTO());
		}
	}
	
	
	public String getNombreApp() {
		return nombreApp;
	}
	public void setNombreApp(String nombreApp) {
		this.nombreApp = nombreApp;
	}
	public String getDescApp() {
		return descApp;
	}
	public void setDescApp(String descApp) {
		this.descApp = descApp;
	}
	public int getIdApp() {
		return idApp;
	}
	public void setIdApp(int idApp) {
		this.idApp = idApp;
	}
	public String getUrlApp() {
		return urlApp;
	}
	public void setUrlApp(String urlApp) {
		this.urlApp = urlApp;
	}
	public Vector<SubMenuDTO> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(Vector<SubMenuDTO> subMenu) {
		this.subMenu = subMenu;
	}
	public int getStatusApp() {
		return statusApp;
	}
	public void setStatusApp(int statusApp) {
		this.statusApp = statusApp;
	}
	public String getIconoApp() {
		return iconoApp;
	}
	public void setIconoApp(String iconoApp) {
		this.iconoApp = iconoApp;
	}
	public double getIndexApp() {
		return indexApp;
	}
	public void setIndexApp(double indexApp) {
		this.indexApp = indexApp;
	}
	public TipoEstatusDTO getEstatusMenuDTO() {
		return estatusMenuDTO;
	}
	public void setEstatusMenuDTO(TipoEstatusDTO estatusMenuDTO) {
		this.estatusMenuDTO = estatusMenuDTO;
	}
	

}
