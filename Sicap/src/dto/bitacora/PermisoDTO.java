package dto.bitacora;

import java.time.LocalDate;
import java.time.LocalTime;

import dto.empleado.EmpleadoDTO;
import dto.listados.TipoEstatusDTO;
import dto.user.UserDTO;
import herramientas.ArchivoDTO;
import herramientas.FechaDTO;
import herramientas.HoraDTO;

public class PermisoDTO {
	private int idPermiso;
	private EmpleadoDTO empledoDTO;
	private BitacoraDTO bitacoraDTO;
	private String nombrePermiso;
	private String descripcionPermiso;
	private FechaDTO fechaDePermiso;
	private FechaDTO fechaAPermiso;
	private HoraDTO horaDePermiso;
	private HoraDTO horaAPermiso;
	private TipoEstatusDTO motivoDTO;
	private LocalDate fechaCreacionPermiso;
	private LocalTime horaCreacionPermiso;
	private FechaDTO fechaActualizacion;
	private HoraDTO horaActualizacion;
	private UserDTO usuarioCreacionDTO;
	private UserDTO usuarioActualizacionDTO;
	private LocalDate fechaEjecucionPermiso;
	private LocalTime horaEjecucionPermiso;
	private UserDTO usuarioEjecucionDTO;
	private TipoEstatusDTO estatusDTO;
	private ArchivoDTO archivoTarjeta;
	
	public PermisoDTO(){
		if(this.getEmpledoDTO() == null){
			this.setEmpledoDTO(new EmpleadoDTO());
		}
		if(this.getBitacoraDTO() == null){
			this.setBitacoraDTO(new BitacoraDTO());
		}
		if(this.getUsuarioCreacionDTO() == null){
			this.setUsuarioCreacionDTO(new UserDTO());
		}
		if(this.getUsuarioEjecucionDTO() == null){
			this.setUsuarioEjecucionDTO(new UserDTO());
		}
		if(this.getMotivoDTO() == null){
			this.setMotivoDTO(new TipoEstatusDTO());
		}
		if(this.getEstatusDTO() == null){
			this.setEstatusDTO(new TipoEstatusDTO());
		}
		if(this.getFechaDePermiso() == null){
			this.setFechaDePermiso(new FechaDTO());
		}
		if(this.getFechaAPermiso() == null){
			this.setFechaAPermiso(new FechaDTO());
		}
		if(this.getHoraDePermiso() == null){
			this.setHoraDePermiso(new HoraDTO());
		}
		if(this.getHoraAPermiso() == null){
			this.setHoraAPermiso(new HoraDTO());
		}
		if(this.getFechaActualizacion() == null){
			this.setFechaActualizacion(new FechaDTO());
		}
		if(this.getHoraActualizacion() == null){
			this.setHoraActualizacion(new HoraDTO());
		}
		if(this.getUsuarioActualizacionDTO() == null){
			this.setUsuarioActualizacionDTO(new UserDTO());
		}
		if(this.getArchivoTarjeta() == null){
			this.setArchivoTarjeta(new ArchivoDTO());
		}
	}
	
	public int getIdPermiso() {
		return idPermiso;
	}
	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}
	public EmpleadoDTO getEmpledoDTO() {
		return empledoDTO;
	}
	public void setEmpledoDTO(EmpleadoDTO empledoDTO) {
		this.empledoDTO = empledoDTO;
	}
	public BitacoraDTO getBitacoraDTO() {
		return bitacoraDTO;
	}
	public void setBitacoraDTO(BitacoraDTO bitacoraDTO) {
		this.bitacoraDTO = bitacoraDTO;
	}
	public String getNombrePermiso() {
		return nombrePermiso;
	}
	public void setNombrePermiso(String nombrePermiso) {
		this.nombrePermiso = nombrePermiso;
	}
	public String getDescripcionPermiso() {
		return descripcionPermiso;
	}
	public void setDescripcionPermiso(String descripcionPermiso) {
		this.descripcionPermiso = descripcionPermiso;
	}
	
	public HoraDTO getHoraAPermiso() {
		return horaAPermiso;
	}
	public void setHoraAPermiso(HoraDTO horaAPermiso) {
		this.horaAPermiso = horaAPermiso;
	}
	
	public LocalDate getFechaCreacionPermiso() {
		return fechaCreacionPermiso;
	}
	public void setFechaCreacionPermiso(LocalDate fechaCreacionPermiso) {
		this.fechaCreacionPermiso = fechaCreacionPermiso;
	}
	public LocalTime getHoraCreacionPermiso() {
		return horaCreacionPermiso;
	}
	public void setHoraCreacionPermiso(LocalTime horaCreacionPermiso) {
		this.horaCreacionPermiso = horaCreacionPermiso;
	}
	public UserDTO getUsuarioCreacionDTO() {
		return usuarioCreacionDTO;
	}
	public void setUsuarioCreacionDTO(UserDTO usuarioCreacionDTO) {
		this.usuarioCreacionDTO = usuarioCreacionDTO;
	}
	public LocalDate getFechaEjecucionPermiso() {
		return fechaEjecucionPermiso;
	}
	public void setFechaEjecucionPermiso(LocalDate fechaEjecucionPermiso) {
		this.fechaEjecucionPermiso = fechaEjecucionPermiso;
	}
	public LocalTime getHoraEjecucionPermiso() {
		return horaEjecucionPermiso;
	}
	public void setHoraEjecucionPermiso(LocalTime horaEjecucionPermiso) {
		this.horaEjecucionPermiso = horaEjecucionPermiso;
	}
	public UserDTO getUsuarioEjecucionDTO() {
		return usuarioEjecucionDTO;
	}
	public void setUsuarioEjecucionDTO(UserDTO usuarioEjecucionDTO) {
		this.usuarioEjecucionDTO = usuarioEjecucionDTO;
	}
	public TipoEstatusDTO getEstatusDTO() {
		return estatusDTO;
	}
	public void setEstatusDTO(TipoEstatusDTO estatusDTO) {
		this.estatusDTO = estatusDTO;
	}

	public FechaDTO getFechaDePermiso() {
		return fechaDePermiso;
	}

	public void setFechaDePermiso(FechaDTO fechaDePermiso) {
		this.fechaDePermiso = fechaDePermiso;
	}

	public FechaDTO getFechaAPermiso() {
		return fechaAPermiso;
	}

	public void setFechaAPermiso(FechaDTO fechaAPermiso) {
		this.fechaAPermiso = fechaAPermiso;
	}

	public TipoEstatusDTO getMotivoDTO() {
		return motivoDTO;
	}

	public void setMotivoDTO(TipoEstatusDTO tipoEstatusDTO) {
		this.motivoDTO = tipoEstatusDTO;
	}

	/**
	 * @return the horaDePermiso
	 */
	public HoraDTO getHoraDePermiso() {
		return horaDePermiso;
	}

	/**
	 * @param horaDePermiso the horaDePermiso to set
	 */
	public void setHoraDePermiso(HoraDTO horaDePermiso) {
		this.horaDePermiso = horaDePermiso;
	}

	/**
	 * @return the fechaActualizacion
	 */
	public FechaDTO getFechaActualizacion() {
		return fechaActualizacion;
	}

	/**
	 * @param fechaActualizacion the fechaActualizacion to set
	 */
	public void setFechaActualizacion(FechaDTO fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	/**
	 * @return the horaActualizacion
	 */
	public HoraDTO getHoraActualizacion() {
		return horaActualizacion;
	}

	/**
	 * @param horaActualizacion the horaActualizacion to set
	 */
	public void setHoraActualizacion(HoraDTO horaActualizacion) {
		this.horaActualizacion = horaActualizacion;
	}

	/**
	 * @return the usuarioActualizacionDTO
	 */
	public UserDTO getUsuarioActualizacionDTO() {
		return usuarioActualizacionDTO;
	}

	/**
	 * @param usuarioActualizacionDTO the usuarioActualizacionDTO to set
	 */
	public void setUsuarioActualizacionDTO(UserDTO usuarioActualizacionDTO) {
		this.usuarioActualizacionDTO = usuarioActualizacionDTO;
	}

	/**
	 * @return the archivoTarjeta
	 */
	public ArchivoDTO getArchivoTarjeta() {
		return archivoTarjeta;
	}

	/**
	 * @param archivoTarjeta the archivoTarjeta to set
	 */
	public void setArchivoTarjeta(ArchivoDTO archivoTarjeta) {
		this.archivoTarjeta = archivoTarjeta;
	}

}
