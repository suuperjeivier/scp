package dto.horario;

import java.time.LocalTime;

import dto.listados.TipoEstatusDTO;
import dto.user.UserSimpleDTO;
import herramientas.FechaHoraDTO;
import herramientas.HoraDTO;

public class HorarioDTO {
	
	private int idHorario;
	private String claveHorario;
	private String nombreHorario;
	private String prefix;
	private int skuHorario;
	private HoraDTO horaEntrada;
	private LocalTime horaSalida;
	private String horaSalidaString;
	private LocalTime horaRetardo;
	private String horaRetardoString;
	private LocalTime horaEntrada2;
	private String horaEntrada2String;
	private LocalTime horaSalida2;
	private String horaSalida2String;
	private LocalTime horaRetardo2;
	private String horaRetardo2String;
	private boolean horarioQuebrado;
	private TipoEstatusDTO horarioQuebradoEstatusDTO;
	private TipoEstatusDTO tipoEstatusDTO;
	private ConfiguracionHorarioDTO configuracionHorarioDTO;
	private boolean horarioNoLaboral;
	private boolean horarioDeDiaSiguiente;
	private FechaHoraDTO fechaHoraCreacion;
	private FechaHoraDTO fechaHoraActualizacion;
	private String colorHexadecimal;
	private UserSimpleDTO usuarioCreacion;
	private UserSimpleDTO usuarioActualizacion;
	
	public HorarioDTO(){
		this.setIdHorario(-1);
		if(this.getHoraEntrada() == null){
			this.setHoraEntrada(new HoraDTO());
		}
		if(this.getTipoEstatusDTO() == null){
			this.setTipoEstatusDTO(new TipoEstatusDTO());
		}
		if(this.getHorarioQuebradoEstatusDTO() == null){
			this.setHorarioQuebradoEstatusDTO(new TipoEstatusDTO());
		}
		if(this.getConfiguracionHorarioDTO() == null){
			this.setConfiguracionHorarioDTO(new ConfiguracionHorarioDTO());
		}
		this.setFechaHoraCreacion(new FechaHoraDTO());
		this.setFechaHoraActualizacion(new FechaHoraDTO());
		this.setUsuarioCreacion(new UserSimpleDTO());
		this.setUsuarioActualizacion(new UserSimpleDTO());
	}

	public int getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public String getClaveHorario() {
		return claveHorario;
	}

	public void setClaveHorario(String claveHorario) {
		this.claveHorario = claveHorario;
	}

	public HoraDTO getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(HoraDTO horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public LocalTime getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
	}

	public LocalTime getHoraRetardo() {
		return horaRetardo;
	}

	public void setHoraRetardo(LocalTime horaRetardo) {
		this.horaRetardo = horaRetardo;
	}

	public LocalTime getHoraEntrada2() {
		return horaEntrada2;
	}

	public void setHoraEntrada2(LocalTime horaEntrada2) {
		this.horaEntrada2 = horaEntrada2;
	}

	public LocalTime getHoraSalida2() {
		return horaSalida2;
	}

	public void setHoraSalida2(LocalTime horaSalida2) {
		this.horaSalida2 = horaSalida2;
	}

	public LocalTime getHoraRetardo2() {
		return horaRetardo2;
	}

	public void setHoraRetardo2(LocalTime horaRetardo2) {
		this.horaRetardo2 = horaRetardo2;
	}

	public String getNombreHorario() {
		return nombreHorario;
	}

	public void setNombreHorario(String nombreHorario) {
		this.nombreHorario = nombreHorario;
	}

	public boolean isHorarioNoLaboral() {
		return horarioNoLaboral;
	}

	public void setHorarioNoLaboral(boolean horarioNoLaboral) {
		this.horarioNoLaboral = horarioNoLaboral;
	}

	public boolean isHorarioQuebrado() {
		return horarioQuebrado;
	}

	public void setHorarioQuebrado(boolean horarioQuebrado) {
		this.horarioQuebrado = horarioQuebrado;
	}

	public ConfiguracionHorarioDTO getConfiguracionHorarioDTO() {
		return configuracionHorarioDTO;
	}

	public void setConfiguracionHorarioDTO(ConfiguracionHorarioDTO configuracionHorarioDTO) {
		this.configuracionHorarioDTO = configuracionHorarioDTO;
	}

	public boolean isHorarioDeDiaSiguiente() {
		return horarioDeDiaSiguiente;
	}

	public void setHorarioDeDiaSiguiente(boolean horarioDeDiaSiguiente) {
		this.horarioDeDiaSiguiente = horarioDeDiaSiguiente;
	}

	public TipoEstatusDTO getHorarioQuebradoEstatusDTO() {
		return horarioQuebradoEstatusDTO;
	}

	public void setHorarioQuebradoEstatusDTO(TipoEstatusDTO horarioQuebradoEstatusDTO) {
		this.horarioQuebradoEstatusDTO = horarioQuebradoEstatusDTO;
	}

	public String getHoraSalidaString() {
		return horaSalidaString;
	}

	public void setHoraSalidaString(String horaSalidaString) {
		this.horaSalidaString = horaSalidaString;
	}

	public String getHoraRetardoString() {
		return horaRetardoString;
	}

	public void setHoraRetardoString(String horaRetardoString) {
		this.horaRetardoString = horaRetardoString;
	}

	public String getHoraEntrada2String() {
		return horaEntrada2String;
	}

	public void setHoraEntrada2String(String horaEntrada2String) {
		this.horaEntrada2String = horaEntrada2String;
	}

	public String getHoraSalida2String() {
		return horaSalida2String;
	}

	public void setHoraSalida2String(String horaSalida2String) {
		this.horaSalida2String = horaSalida2String;
	}

	public String getHoraRetardo2String() {
		return horaRetardo2String;
	}

	public void setHoraRetardo2String(String horaRetardo2String) {
		this.horaRetardo2String = horaRetardo2String;
	}

	public TipoEstatusDTO getTipoEstatusDTO() {
		return tipoEstatusDTO;
	}

	public void setTipoEstatusDTO(TipoEstatusDTO tipoEstatusDTO) {
		this.tipoEstatusDTO = tipoEstatusDTO;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the skuHorario
	 */
	public int getSkuHorario() {
		return skuHorario;
	}

	/**
	 * @param skuHorario the skuHorario to set
	 */
	public void setSkuHorario(int skuHorario) {
		this.skuHorario = skuHorario;
	}

	/**
	 * @return the colorHexadecimal
	 */
	public String getColorHexadecimal() {
		return colorHexadecimal;
	}

	/**
	 * @param colorHexadecimal the colorHexadecimal to set
	 */
	public void setColorHexadecimal(String colorHexadecimal) {
		this.colorHexadecimal = colorHexadecimal;
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


	
	


}
