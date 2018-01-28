package dto.listados;

public class GradoEscolarDTO {
	private int idGradoEscolar;
	private String nombreGradoAcademico;
	private PeriodoEscolarDTO periodoEscolar;
	
	public int getIdGradoEscolar() {
		return idGradoEscolar;
	}
	
	public void setIdGradoEscolar(int idGradoEscolar) {
		this.idGradoEscolar = idGradoEscolar;
	}

	public String getNombreGradoAcademico() {
		return nombreGradoAcademico;
	}

	public void setNombreGradoAcademico(String nombreGradoAcademico) {
		this.nombreGradoAcademico = nombreGradoAcademico;
	}

	public PeriodoEscolarDTO getPeriodoEscolar() {
		return periodoEscolar;
	}

	public void setPeriodoEscolar(PeriodoEscolarDTO periodoEscolar) {
		this.periodoEscolar = periodoEscolar;
	}
	

}
