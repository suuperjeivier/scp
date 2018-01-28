package herramientas;

import java.time.LocalTime;

public class HoraDTO {
	private LocalTime horaLT;
	private String horaString;
	
	public HoraDTO(){		
		this.setHoraString("00:00:00");
	}
	
	public LocalTime getHoraLT() {
		return horaLT;
	}
	public void setHoraLT(LocalTime horaLT) {
		this.horaLT = horaLT;
	}
	/**
	 * @return the horaString
	 */
	public String getHoraString() {
		return horaString;
	}
	/**
	 * @param horaString the horaString to set
	 */
	public void setHoraString(String horaString) {
		this.horaString = horaString;
	}
	

}
