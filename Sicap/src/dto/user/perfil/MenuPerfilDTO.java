package dto.user.perfil;

import dto.listados.TipoEstatusDTO;
import dto.user.menu.submenu.SubMenuDTO;

public class MenuPerfilDTO {
	
	private int idMenuPerfil;
	private int fkPerfil;
	private PerfilDTO fkPerfilDTO;
	private int fkSubmenu;
	private SubMenuDTO fkSubmenuDTO;
	private int createMenuPerfil;
	private TipoEstatusDTO createMenuPerfilDTO;
	private int updateMenuPerfil;
	private TipoEstatusDTO updateMenuPerfilDTO;
	private int deleteMenuPerfil;
	private TipoEstatusDTO deleteMenuPerfilDTO;
	private double indexMenuPerfil;
	
	
	/**
	 * CONSTRUCTOR
	 */
	public MenuPerfilDTO(){
		this.setIdMenuPerfil(-1);
		if(this.getFkPerfilDTO() == null){
			this.setFkPerfilDTO(new PerfilDTO());
		}
		if(this.getFkSubmenuDTO() == null){
			this.setFkSubmenuDTO(new SubMenuDTO());
		}
		if(this.getCreateMenuPerfilDTO() == null){
			this.setCreateMenuPerfilDTO(new TipoEstatusDTO());
		}
		if(this.getUpdateMenuPerfilDTO() == null){
			this.setUpdateMenuPerfilDTO(new TipoEstatusDTO());
		}
		if(this.getDeleteMenuPerfilDTO() == null){
			this.setDeleteMenuPerfilDTO(new TipoEstatusDTO());
		}
	}
	
	
	public int getIdMenuPerfil() {
		return idMenuPerfil;
	}
	
	public void setIdMenuPerfil(int idMenuPerfil) {
		this.idMenuPerfil = idMenuPerfil;
	}
	
	public int getFkPerfil() {
		return fkPerfil;
	}


	public void setFkPerfil(int fkPerfil) {
		this.fkPerfil = fkPerfil;
	}


	public PerfilDTO getFkPerfilDTO() {
		return fkPerfilDTO;
	}

	public void setFkPerfilDTO(PerfilDTO fkPerfilDTO) {
		this.fkPerfilDTO = fkPerfilDTO;
	}

	public int getFkSubmenu() {
		return fkSubmenu;
	}


	public void setFkSubmenu(int fkSubmenu) {
		this.fkSubmenu = fkSubmenu;
	}

	public SubMenuDTO getFkSubmenuDTO() {
		return fkSubmenuDTO;
	}


	public void setFkSubmenuDTO(SubMenuDTO fkSubmenuDTO) {
		this.fkSubmenuDTO = fkSubmenuDTO;
	}

	public int getCreateMenuPerfil() {
		return createMenuPerfil;
	}

	public void setCreateMenuPerfil(int createMenuPerfil) {
		this.createMenuPerfil = createMenuPerfil;
	}

	public int getUpdateMenuPerfil() {
		return updateMenuPerfil;
	}

	public void setUpdateMenuPerfil(int updateMenuPerfil) {
		this.updateMenuPerfil = updateMenuPerfil;
	}

	public int getDeleteMenuPerfil() {
		return deleteMenuPerfil;
	}

	public void setDeleteMenuPerfil(int deleteMenuPerfil) {
		this.deleteMenuPerfil = deleteMenuPerfil;
	}

	public double getIndexMenuPerfil() {
		return indexMenuPerfil;
	}

	public void setIndexMenuPerfil(double indexMenuPerfil) {
		this.indexMenuPerfil = indexMenuPerfil;
	}


	public TipoEstatusDTO getCreateMenuPerfilDTO() {
		return createMenuPerfilDTO;
	}


	public void setCreateMenuPerfilDTO(TipoEstatusDTO createMenuPerfilDTO) {
		this.createMenuPerfilDTO = createMenuPerfilDTO;
	}


	public TipoEstatusDTO getUpdateMenuPerfilDTO() {
		return updateMenuPerfilDTO;
	}


	public void setUpdateMenuPerfilDTO(TipoEstatusDTO updateMenuPerfilDTO) {
		this.updateMenuPerfilDTO = updateMenuPerfilDTO;
	}


	public TipoEstatusDTO getDeleteMenuPerfilDTO() {
		return deleteMenuPerfilDTO;
	}


	public void setDeleteMenuPerfilDTO(TipoEstatusDTO deleteMenuPerfilDTO) {
		this.deleteMenuPerfilDTO = deleteMenuPerfilDTO;
	}
	
}
