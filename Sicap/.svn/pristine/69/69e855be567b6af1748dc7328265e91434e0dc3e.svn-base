package dto.empleado;

import java.time.LocalDate;
import java.util.Vector;

import dto.horario.HorarioDTO;
import dto.logs.ErroresDTO;

public class EmpleadoHorarioDTO {
	
	private int idEmpleadoHorario;
	private EmpleadoDTO empleado;
	private LocalDate fechaValidez;
	private int anioValidez;
	private int mesValidez;
	private HorarioDTO horario;
	private Vector<HorarioDTO> horarios;
	private Vector<ErroresDTO> errores;
	
	public EmpleadoHorarioDTO(){
		if(this.getHorarios() == null){
			this.setHorarios(new Vector<HorarioDTO>());
		}
	}

	public EmpleadoDTO getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoDTO empleado) {
		this.empleado = empleado;
	}

	public HorarioDTO getHorario() {
		return horario;
	}

	public void setHorario(HorarioDTO horario) {
		this.horario = horario;
	}

	public Vector<HorarioDTO> getHorarios() {
		return horarios;
	}

	public void setHorarios(Vector<HorarioDTO> horarios) {
		this.horarios = horarios;
	}

	public Vector<ErroresDTO> getErrores() {
		return errores;
	}

	public void setErrores(Vector<ErroresDTO> errores) {
		this.errores = errores;
	}

	public int getIdEmpleadoHorario() {
		return idEmpleadoHorario;
	}

	public void setIdEmpleadoHorario(int idEmpleadoHorario) {
		this.idEmpleadoHorario = idEmpleadoHorario;
	}

	/**
	 * @return the fechaValidez
	 */
	public LocalDate getFechaValidez() {
		return fechaValidez;
	}

	/**
	 * @param fechaValidez the fechaValidez to set
	 */
	public void setFechaValidez(LocalDate fechaValidez) {
		this.fechaValidez = fechaValidez;
	}

	/**
	 * @return the anioValidez
	 */
	public int getAnioValidez() {
		return anioValidez;
	}

	/**
	 * @param anoValidez the anioValidez to set
	 */
	public void setAnioValidez(int anioValidez) {
		this.anioValidez = anioValidez;
	}

	/**
	 * @return the mesValidez
	 */
	public int getMesValidez() {
		return mesValidez;
	}

	/**
	 * @param mesValidez the mesValidez to set
	 */
	public void setMesValidez(int mesValidez) {
		this.mesValidez = mesValidez;
	}
	
}
