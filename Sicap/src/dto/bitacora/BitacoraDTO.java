package dto.bitacora;

import dto.BiometricDeviceDTO;
import dto.empleado.EmpleadoDTO;
import dto.listados.TipoEstatusDTO;
import dto.user.UserDTO;
import herramientas.FechaDTO;
import herramientas.HoraDTO;

public class BitacoraDTO {

	private int id;
	private EmpleadoDTO empleado;
	private HoraDTO horaRegistro;
	private FechaDTO fechaRegistro;
	private TipoEstatusDTO tipoRegistro;	
	private String comentarioRegistro;
	private TipoEstatusDTO statusRegistro;	
	private FechaDTO fechaInsercionRegistro;
	private HoraDTO horaInsercionRegistro;
	private FechaDTO fechaActualizacionRegistro;
	private HoraDTO horaActualizacionRegistro;
	private UserDTO usuarioInsercion;
	private UserDTO usuarioActualizacion;
	private BiometricDeviceDTO device;
	private boolean faltaJustificada;
	
	
	public BitacoraDTO(){
		if(this.getEmpleado() == null){
			this.setEmpleado(new EmpleadoDTO());
		}
		if(this.getHoraRegistro() == null){
			this.setHoraRegistro(new HoraDTO());
		}		
		if(this.getFechaRegistro() == null){
			this.setFechaRegistro(new FechaDTO());
		}
		if(this.getTipoRegistro() == null){
			this.setTipoRegistro(new TipoEstatusDTO());
		}
		if(this.getStatusRegistro() == null){
			this.setStatusRegistro(new TipoEstatusDTO());
		}
		if(this.getFechaInsercionRegistro() == null){
			this.setFechaInsercionRegistro(new FechaDTO());
		}
		if(this.getHoraInsercionRegistro() == null){
			this.setHoraInsercionRegistro(new HoraDTO());
		}
		if(this.getFechaActualizacionRegistro() == null){
			this.setFechaActualizacionRegistro(new FechaDTO());
		}
		if(this.getHoraActualizacionRegistro() == null){
			this.setHoraActualizacionRegistro(new HoraDTO());
		}		
		if(this.getUsuarioInsercion() == null){
			this.setUsuarioInsercion(new UserDTO());
		}
		if(this.getUsuarioActualizacion() == null){
			this.setUsuarioActualizacion(new UserDTO());
		}
		if(this.getDevice() == null){
			this.setDevice(new BiometricDeviceDTO());
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmpleadoDTO getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoDTO empleado) {
		this.empleado = empleado;
	}

	public FechaDTO getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(FechaDTO fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * @return the horaRegistro
	 */
	public HoraDTO getHoraRegistro() {
		return horaRegistro;
	}

	/**
	 * @param horaRegistro the horaRegistro to set
	 */
	public void setHoraRegistro(HoraDTO horaRegistro) {
		this.horaRegistro = horaRegistro;
	}

	/**
	 * @return the tipoRegistro
	 */
	public TipoEstatusDTO getTipoRegistro() {
		return tipoRegistro;
	}

	/**
	 * @param tipoRegistro the tipoRegistro to set
	 */
	public void setTipoRegistro(TipoEstatusDTO tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	/**
	 * @return the statusRegistro
	 */
	public TipoEstatusDTO getStatusRegistro() {
		return statusRegistro;
	}

	/**
	 * @param statusRegistro the statusRegistro to set
	 */
	public void setStatusRegistro(TipoEstatusDTO statusRegistro) {
		this.statusRegistro = statusRegistro;
	}

	/**
	 * @return the faltaJustificada
	 */
	public boolean isFaltaJustificada() {
		return faltaJustificada;
	}

	/**
	 * @param faltaJustificada the faltaJustificada to set
	 */
	public void setFaltaJustificada(boolean faltaJustificada) {
		this.faltaJustificada = faltaJustificada;
	}

	/**
	 * @return the comentarioRegistro
	 */
	public String getComentarioRegistro() {
		return comentarioRegistro;
	}

	/**
	 * @param comentarioRegistro the comentarioRegistro to set
	 */
	public void setComentarioRegistro(String comentarioRegistro) {
		this.comentarioRegistro = comentarioRegistro;
	}

	/**
	 * @return the fechaInsercionRegistro
	 */
	public FechaDTO getFechaInsercionRegistro() {
		return fechaInsercionRegistro;
	}

	/**
	 * @param fechaInsercionRegistro the fechaInsercionRegistro to set
	 */
	public void setFechaInsercionRegistro(FechaDTO fechaInsercionRegistro) {
		this.fechaInsercionRegistro = fechaInsercionRegistro;
	}

	/**
	 * @return the horaInsercionRegistro
	 */
	public HoraDTO getHoraInsercionRegistro() {
		return horaInsercionRegistro;
	}

	/**
	 * @param horaInsercionRegistro the horaInsercionRegistro to set
	 */
	public void setHoraInsercionRegistro(HoraDTO horaInsercionRegistro) {
		this.horaInsercionRegistro = horaInsercionRegistro;
	}

	/**
	 * @return the fechaActualizacionRegistro
	 */
	public FechaDTO getFechaActualizacionRegistro() {
		return fechaActualizacionRegistro;
	}

	/**
	 * @param fechaActualizacionRegistro the fechaActualizacionRegistro to set
	 */
	public void setFechaActualizacionRegistro(FechaDTO fechaActualizacionRegistro) {
		this.fechaActualizacionRegistro = fechaActualizacionRegistro;
	}

	/**
	 * @return the horaActualizacionRegistro
	 */
	public HoraDTO getHoraActualizacionRegistro() {
		return horaActualizacionRegistro;
	}

	/**
	 * @param horaActualizacionRegistro the horaActualizacionRegistro to set
	 */
	public void setHoraActualizacionRegistro(HoraDTO horaActualizacionRegistro) {
		this.horaActualizacionRegistro = horaActualizacionRegistro;
	}
		

	/**
	 * @return the usuarioInsercion
	 */
	public UserDTO getUsuarioInsercion() {
		return usuarioInsercion;
	}

	/**
	 * @param usuarioInsercion the usuarioInsercion to set
	 */
	public void setUsuarioInsercion(UserDTO usuarioInsercion) {
		this.usuarioInsercion = usuarioInsercion;
	}

	/**
	 * @return the usuarioActualizacion
	 */
	public UserDTO getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	/**
	 * @param usuarioActualizacion the usuarioActualizacion to set
	 */
	public void setUsuarioActualizacion(UserDTO usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	/**
	 * @return the device
	 */
	public BiometricDeviceDTO getDevice() {
		return device;
	}

	/**
	 * @param device the device to set
	 */
	public void setDevice(BiometricDeviceDTO device) {
		this.device = device;
	}

	
	
}
