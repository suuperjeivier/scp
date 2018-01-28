package dto.listados;

public class GradoPeriodoEscolarDTO {

	private GradoEscolarDTO gradoDTO;
	private PeriodoEscolarDTO periodoDTO;
	
	public GradoPeriodoEscolarDTO(){
		if(this.getGradoDTO() == null){
			this.setGradoDTO(new GradoEscolarDTO());
		}
		if(this.getPeriodoDTO() == null){
			this.setPeriodoDTO(new PeriodoEscolarDTO());
		}
	}

	public GradoEscolarDTO getGradoDTO() {
		return gradoDTO;
	}

	public void setGradoDTO(GradoEscolarDTO gradoDTO) {
		this.gradoDTO = gradoDTO;
	}

	public PeriodoEscolarDTO getPeriodoDTO() {
		return periodoDTO;
	}

	public void setPeriodoDTO(PeriodoEscolarDTO periodoDTO) {
		this.periodoDTO = periodoDTO;
	}
	
	
}
