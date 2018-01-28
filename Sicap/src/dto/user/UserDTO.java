package dto.user;

import java.util.Vector;

import org.apache.tomcat.jni.User;

import dto.empleado.EmpleadoDTO;
import dto.user.menu.MenuDTO;
import dto.user.perfil.PerfilDTO;
import herramientas.ImagenDTO;

public class UserDTO extends EmpleadoDTO{
	
	private int userId;
	private String userName;
	private String password;
	private String userEmail;
	private PerfilDTO perfil;
	private UserConfigDTO userConfigDTO;
	private ImagenDTO imgAvatarDTO;
	private int sessionTimeOut;
	private char active;
	private Vector <MenuDTO> menu;
	private User userJNI;
	private String provName;
	
	public UserDTO(){		
		if(this.getPerfil() == null){
			this.setPerfil(new PerfilDTO());
		}
		if(this.getUserConfigDTO() == null){
			this.setUserConfigDTO(new UserConfigDTO());
		}
		if(this.getImgAvatarDTO() == null){
			this.setImgAvatarDTO(new ImagenDTO());
		}
		if(this.getUserJNI() == null){
			this.setUserJNI(new User());
		}
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public PerfilDTO getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilDTO perfil) {
		this.perfil = perfil;
	}
	public Vector <MenuDTO> getMenu() {
		return menu;
	}
	public void setMenu(Vector <MenuDTO> menu) {
		this.menu = menu;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public UserConfigDTO getUserConfigDTO() {
		return userConfigDTO;
	}

	public void setUserConfigDTO(UserConfigDTO userConfigDTO) {
		this.userConfigDTO = userConfigDTO;
	}
	
	public ImagenDTO getImgAvatarDTO() {
		return imgAvatarDTO;
	}

	public void setImgAvatarDTO(ImagenDTO imgAvatarDTO) {
		this.imgAvatarDTO = imgAvatarDTO;
	}

	/**
	 * @return the userJNI
	 */
	public User getUserJNI() {
		return userJNI;
	}

	/**
	 * @param userJNI the userJNI to set
	 */
	public void setUserJNI(User userJNI) {
		this.userJNI = userJNI;
	}

	/**
	 * @return the sessionTimeOut
	 */
	public int getSessionTimeOut() {
		return sessionTimeOut;
	}

	/**
	 * @param sessionTimeOut the sessionTimeOut to set
	 */
	public void setSessionTimeOut(int sessionTimeOut) {
		this.sessionTimeOut = sessionTimeOut;
	}

	/**
	 * @return the provName
	 */
	public String getProvName() {
		return provName;
	}

	/**
	 * @param provName the provName to set
	 */
	public void setProvName(String provName) {
		this.provName = provName;
	}

	/**
	 * @return the active
	 */
	public char getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(char active) {
		this.active = active;
	}

}
