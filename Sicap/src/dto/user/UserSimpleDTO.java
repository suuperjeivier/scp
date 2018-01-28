package dto.user;

import dto.user.perfil.PerfilDTO;
import herramientas.ImagenDTO;

public class UserSimpleDTO {
	private int idUser;
	private String login;
	private int fkIdUserConfig;
	private String pswd;
	private String name;
	private String email;
	private int sessionTimeOut;
	private char active;
	private ImagenDTO userAvatar;
	private String activationCode;
	private char privAdmin;
	private PerfilDTO userProfile;
	private int fkIdEmpleado;
	private String pswdTxt;
	
	public UserSimpleDTO(){
		this.setUserAvatar(new ImagenDTO());
		this.setUserProfile(new PerfilDTO());
	}
	
	/**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}
	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the fkIdUserConfig
	 */
	public int getFkIdUserConfig() {
		return fkIdUserConfig;
	}
	/**
	 * @param fkIdUserConfig the fkIdUserConfig to set
	 */
	public void setFkIdUserConfig(int fkIdUserConfig) {
		this.fkIdUserConfig = fkIdUserConfig;
	}
	/**
	 * @return the pswd
	 */
	public String getPswd() {
		return pswd;
	}
	/**
	 * @param pswd the pswd to set
	 */
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	/**
	 * @return the userAvatar
	 */
	/*public Blob getUserAvatar() {
		return userAvatar;
	}/
	/**
	 * @param blob the userAvatar to set
	 */
	/*public void setUserAvatar(java.sql.Blob blob) {
		this.userAvatar = blob;
	}*/
	/**
	 * @return the activationCode
	 */
	public String getActivationCode() {
		return activationCode;
	}
	/**
	 * @param activationCode the activationCode to set
	 */
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	/**
	 * @return the privAdmin
	 */
	public char getPrivAdmin() {
		return privAdmin;
	}
	/**
	 * @param privAdmin the privAdmin to set
	 */
	public void setPrivAdmin(char privAdmin) {
		this.privAdmin = privAdmin;
	}
	/**
	 * @return the fkIdEmpleado
	 */
	public int getFkIdEmpleado() {
		return fkIdEmpleado;
	}
	/**
	 * @param fkIdEmpleado the fkIdEmpleado to set
	 */
	public void setFkIdEmpleado(int fkIdEmpleado) {
		this.fkIdEmpleado = fkIdEmpleado;
	}
	/**
	 * @return the userAvatar
	 */
	public ImagenDTO getUserAvatar() {
		return userAvatar;
	}
	/**
	 * @param userAvatar the userAvatar to set
	 */
	public void setUserAvatar(ImagenDTO userAvatar) {
		this.userAvatar = userAvatar;
	}
	/**
	 * @return the userProfile
	 */
	public PerfilDTO getUserProfile() {
		return userProfile;
	}
	/**
	 * @param userProfile the userProfile to set
	 */
	public void setUserProfile(PerfilDTO userProfile) {
		this.userProfile = userProfile;
	}

	/**
	 * @return the pswdTxt
	 */
	public String getPswdTxt() {
		return pswdTxt;
	}

	/**
	 * @param pswdTxt the pswdTxt to set
	 */
	public void setPswdTxt(String pswdTxt) {
		this.pswdTxt = pswdTxt;
	}
	
}
