package herramientas;

import java.sql.Date;

public class FechaHoraDTO {
	private FechaDTO fecha;
	private HoraDTO hora;
	private Date fechaHoraSql;
	
	public FechaHoraDTO(){
		this.setFecha(new FechaDTO());
		this.setHora(new HoraDTO());
	}

	/**
	 * @return the fecha
	 */
	public FechaDTO getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(FechaDTO fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the hora
	 */
	public HoraDTO getHora() {
		return hora;
	}

	/**
	 * @param hora the hora to set
	 */
	public void setHora(HoraDTO hora) {
		this.hora = hora;
	}

	/**
	 * @return the fechaHoraSql
	 */
	public Date getFechaHoraSql() {
		return fechaHoraSql;
	}

	/**
	 * @param fechaHoraSql the fechaHoraSql to set
	 */
	public void setFechaHoraSql(Date fechaHoraSql) {
		this.fechaHoraSql = fechaHoraSql;
	}
	
	
}
