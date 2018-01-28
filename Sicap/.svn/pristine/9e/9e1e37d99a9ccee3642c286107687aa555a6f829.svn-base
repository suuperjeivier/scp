package dto.empleado;

import java.sql.Blob;
import java.time.LocalDate;

import dto.listados.CalleDTO;
import dto.listados.ColoniaDTO;
import dto.listados.CorporacionDTO;
import dto.listados.EntidadFederativaDTO;
import dto.listados.EstadoCivilDTO;
import dto.listados.GeneroDTO;
import dto.listados.GradoPeriodoEscolarDTO;
import dto.listados.GrupoSanguineoDTO;
import dto.listados.MunicipioDTO;
import dto.listados.PeriodoEscolarDTO;
import dto.listados.TipoEstatusDTO;

public class EmpleadoDTO {
	
	private int idEmpleado;
	private int skuEmpleado;	
	private String cuipEmpleado;
	private String nombreEmpleado;
	private String apPaternoEmpleado;
	private String apMaternoEmpleado;
	private String apellidosEmpleado;
	private String nombreCompletoEmpleado;
	private GeneroDTO generoEmpleadoDTO;
	private EstadoCivilDTO edoCivilEmpladoDTO;
	private LocalDate fechaNacimientoEmpleado;
	private String fechaNacimientoEmpleadoString;
	private GrupoSanguineoDTO grupoSanguineoEmpleadoDTO;
	private int codigoPostalDomicilioEmpleado;
	private EntidadFederativaDTO entidadFederativaDomicilioEmpleadoDTO;
	private MunicipioDTO municipioDomicilioEmpleadoDTO;
	private ColoniaDTO coloniaDomicilioEmpleadoDTO;	
	private CalleDTO calleDomicilioEmpleadoDTO;
	private String noExtDomicilioEmpleado;
	private String noIntDomicilioEmpleado;
	private String telFijoEmpleado;
	private String telMovilEmpleado;
	private String correoElectronicoEmpleado;
	private String curpEmpleado;
	private String rfcEmpleado;
	private EspecialidadDTO especialidadDTO;
	private byte[] documentoComprobanteInfoEmpleado;
	private String tipoComprobanteInfoEmpleado;
	private String nombreComprobanteInfoEmpleado;
	private LocalDate fechaAltaEmpleado;
	private String fechaAltaEmpleadoString;
	private LocalDate fechaBajaEmpleado;
	private String fechaBajaEmpleadoString;
	private byte[] fotoEmpleado;
	private Blob fotoEmpleadoBD;
	private String fotoEmpleadoBase64;
	private byte[] firmaEmpleado;
	private NivelAcademicoDTO nivelAcademicoEmpleadoDTO;
	private TipoEstatusDTO carreraTruncaEmpleadoDTO;
	private GradoPeriodoEscolarDTO gradoPeridoEscolarEmpleadoDTO;
	private PeriodoEscolarDTO peridoEscolarEmpleadoDTO;
	private EscolaridadDTO escolaridadDTO;
	private CorporacionDTO corporacionEmpleadoDTO;
	private TipoEstatusDTO estatusEmpleadoDTO;
	
	/**
	 * CONSTRUCTOR
	 */
	public EmpleadoDTO(){
		this.setIdEmpleado(-1);
		if(this.getGeneroEmpleadoDTO() == null){
			this.setGeneroEmpleadoDTO(new GeneroDTO());
		}
		if(this.getEdoCivilEmpladoDTO() == null){
			this.setEdoCivilEmpladoDTO(new EstadoCivilDTO());
		}
		if(this.getGrupoSanguineoEmpleadoDTO() == null){
			this.setGrupoSanguineoEmpleadoDTO(new GrupoSanguineoDTO());
		}
		if(this.getEntidadFederativaDomicilioEmpleadoDTO() == null){
			this.setEntidadFederativaDomicilioEmpleadoDTO(new EntidadFederativaDTO());
		}
		if(this.getMunicipioDomicilioEmpleadoDTO() == null){
			this.setMunicipioDomicilioEmpleadoDTO(new MunicipioDTO()); 
		}
		if(this.getColoniaDomicilioEmpleadoDTO() == null){
			this.setColoniaDomicilioEmpleadoDTO(new ColoniaDTO());
		}
		if(this.getCalleDomicilioEmpleadoDTO() == null){
			this.setCalleDomicilioEmpleadoDTO(new CalleDTO());
		}
		if(this.getEspecialidadDTO() == null){
			this.setEspecialidadDTO(new EspecialidadDTO());
		}
		if(this.getNivelAcademicoEmpleadoDTO() == null){
			this.setNivelAcademicoEmpleadoDTO(new NivelAcademicoDTO());
		}
		if(this.getGradoPeridoEscolarEmpleadoDTO() == null){
			this.setGradoPeridoEscolarEmpleadoDTO(new GradoPeriodoEscolarDTO());
		}
		if(this.getPeridoEscolarEmpleadoDTO() == null){
			this.setPeridoEscolarEmpleadoDTO(new PeriodoEscolarDTO());
		}
		if(this.getEscolaridadDTO() == null){
			this.setEscolaridadDTO(new EscolaridadDTO());
		}
		if(this.getCorporacionEmpleadoDTO() == null){
			this.setCorporacionEmpleadoDTO(new CorporacionDTO());
		}
		if(this.getEstatusDTO() == null){
			this.setEstatusDTO(new TipoEstatusDTO());
		}
		if (this.getCarreraTruncaEmpleado() == null){
			this.setCarreraTruncaEmpleado(new TipoEstatusDTO());
		}
	}
	
	
	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getSkuEmpleado() {
		return skuEmpleado;
	}

	public void setSkuEmpleado(int skuEmpleado) {
		this.skuEmpleado = skuEmpleado;
	}

	public String getCuipEmpleado() {
		return cuipEmpleado;
	}

	public void setCuipEmpleado(String cuipEmpleado) {
		this.cuipEmpleado = cuipEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getApPaternoEmpleado() {
		return apPaternoEmpleado;
	}

	public void setApPaternoEmpleado(String apPaternoEmpleado) {
		this.apPaternoEmpleado = apPaternoEmpleado;
	}

	public String getApMaternoEmpleado() {
		return apMaternoEmpleado;
	}

	public void setApMaternoEmpleado(String apMaternoEmpleado) {
		this.apMaternoEmpleado = apMaternoEmpleado;
	}		

	public LocalDate getFechaNacimientoEmpleado() {
		return fechaNacimientoEmpleado;
	}

	public void setFechaNacimientoEmpleado(LocalDate fechaNacimientoEmpleado) {
		this.fechaNacimientoEmpleado = fechaNacimientoEmpleado;
	}	

	public String getTelFijoEmpleado() {
		return telFijoEmpleado;
	}

	public void setTelFijoEmpleado(String telFijoEmpleado) {
		this.telFijoEmpleado = telFijoEmpleado;
	}

	public String getTelMovilEmpleado() {
		return telMovilEmpleado;
	}

	public void setTelMovilEmpleado(String telMovilEmpleado) {
		this.telMovilEmpleado = telMovilEmpleado;
	}

	public String getCorreoElectronicoEmpleado() {
		return correoElectronicoEmpleado;
	}

	public void setCorreoElectronicoEmpleado(String correoElectronicoEmpleado) {
		this.correoElectronicoEmpleado = correoElectronicoEmpleado;
	}

	public String getCurpEmpleado() {
		return curpEmpleado;
	}

	public void setCurpEmpleado(String curpEmpleado) {
		this.curpEmpleado = curpEmpleado;
	}

	public String getRfcEmpleado() {
		return rfcEmpleado;
	}

	public void setRfcEmpleado(String rfcEmpleado) {
		this.rfcEmpleado = rfcEmpleado;
	}

	public byte[] getDocumentoComprobanteInfoEmpleado() {
		return documentoComprobanteInfoEmpleado;
	}

	public void setDocumentoComprobanteInfoEmpleado(byte[] documentoComprobanteInfoEmpleado) {
		this.documentoComprobanteInfoEmpleado = documentoComprobanteInfoEmpleado;
	}

	public String getTipoComprobanteInfoEmpleado() {
		return tipoComprobanteInfoEmpleado;
	}

	public void setTipoComprobanteInfoEmpleado(String tipoComprobanteInfoEmpleado) {
		this.tipoComprobanteInfoEmpleado = tipoComprobanteInfoEmpleado;
	}

	public String getNombreComprobanteInfoEmpleado() {
		return nombreComprobanteInfoEmpleado;
	}

	public void setNombreComprobanteInfoEmpleado(String nombreComprobanteInfoEmpleado) {
		this.nombreComprobanteInfoEmpleado = nombreComprobanteInfoEmpleado;
	}

	public LocalDate getFechaAltaEmpleado() {
		return fechaAltaEmpleado;
	}

	public void setFechaAltaEmpleado(LocalDate fechaAltaEmpleado) {
		this.fechaAltaEmpleado = fechaAltaEmpleado;
	}

	public LocalDate getFechaBajaEmpleado() {
		return fechaBajaEmpleado;
	}

	public void setFechaBajaEmpleado(LocalDate fechaBajaEmpleado) {
		this.fechaBajaEmpleado = fechaBajaEmpleado;
	}

	public byte[] getFotoEmpleado() {
		return fotoEmpleado;
	}

	public void setFotoEmpleado(byte[] fotoEmpleado) {
		this.fotoEmpleado = fotoEmpleado;
	}

	public byte[] getFirmaEmpleado() {
		return firmaEmpleado;
	}

	public void setFirmaEmpleado(byte[] firmaEmpleado) {
		this.firmaEmpleado = firmaEmpleado;
	}
	
	public EspecialidadDTO getEspecialidadDTO() {
		return especialidadDTO;
	}

	public void setEspecialidadDTO(EspecialidadDTO especialidadDTO) {
		this.especialidadDTO = especialidadDTO;
	}

	public EscolaridadDTO getEscolaridadDTO() {
		return escolaridadDTO;
	}

	public void setEscolaridadDTO(EscolaridadDTO escolaridadDTO) {
		this.escolaridadDTO = escolaridadDTO;
	}

	public TipoEstatusDTO getEstatusDTO() {
		return estatusEmpleadoDTO;
	}

	public void setEstatusDTO(TipoEstatusDTO estatusDTO) {
		this.estatusEmpleadoDTO = estatusDTO;
	}

	public String getNombreCompletoEmpleado() {
		return nombreCompletoEmpleado;
	}

	public void setNombreCompletoEmpleado(String nombreCompletoEmpleado) {
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
	}

	public Blob getFotoEmpleadoBD() {
		return fotoEmpleadoBD;
	}

	public void setFotoEmpleadoBD(Blob fotoEmpleadoBD) {
		this.fotoEmpleadoBD = fotoEmpleadoBD;
	}

	public String getFotoEmpleadoBase64() {
		return fotoEmpleadoBase64;
	}

	public void setFotoEmpleadoBase64(String fotoEmpleadoBase64) {
		this.fotoEmpleadoBase64 = fotoEmpleadoBase64;
	}	

	public GeneroDTO getGeneroEmpleadoDTO() {
		return generoEmpleadoDTO;
	}

	public void setGeneroEmpleadoDTO(GeneroDTO generoEmpleadoDTO) {
		this.generoEmpleadoDTO = generoEmpleadoDTO;
	}

	public EstadoCivilDTO getEdoCivilEmpladoDTO() {
		return edoCivilEmpladoDTO;
	}

	public void setEdoCivilEmpladoDTO(EstadoCivilDTO edoCivilEmpladoDTO) {
		this.edoCivilEmpladoDTO = edoCivilEmpladoDTO;
	}

	public GrupoSanguineoDTO getGrupoSanguineoEmpleadoDTO() {
		return grupoSanguineoEmpleadoDTO;
	}

	public void setGrupoSanguineoEmpleadoDTO(GrupoSanguineoDTO grupoSanguineoEmpleadoDTO) {
		this.grupoSanguineoEmpleadoDTO = grupoSanguineoEmpleadoDTO;
	}
	
	public NivelAcademicoDTO getNivelAcademicoEmpleadoDTO() {
		return nivelAcademicoEmpleadoDTO;
	}

	public void setNivelAcademicoEmpleadoDTO(NivelAcademicoDTO nivelAcademicoEmpleadoDTO) {
		this.nivelAcademicoEmpleadoDTO = nivelAcademicoEmpleadoDTO;
	}

	public GradoPeriodoEscolarDTO getGradoPeridoEscolarEmpleadoDTO() {
		return gradoPeridoEscolarEmpleadoDTO;
	}

	public void setGradoPeridoEscolarEmpleadoDTO(GradoPeriodoEscolarDTO gradoPeridoEscolarEmpleadoDTO) {
		this.gradoPeridoEscolarEmpleadoDTO = gradoPeridoEscolarEmpleadoDTO;
	}

	public PeriodoEscolarDTO getPeridoEscolarEmpleadoDTO() {
		return peridoEscolarEmpleadoDTO;
	}

	public void setPeridoEscolarEmpleadoDTO(PeriodoEscolarDTO peridoEscolarEmpleadoDTO) {
		this.peridoEscolarEmpleadoDTO = peridoEscolarEmpleadoDTO;
	}

	public int getCodigoPostalDomicilioEmpleado() {
		return codigoPostalDomicilioEmpleado;
	}

	public void setCodigoPostalDomicilioEmpleado(int codigoPostalDomicilioEmpleado) {
		this.codigoPostalDomicilioEmpleado = codigoPostalDomicilioEmpleado;
	}

	public EntidadFederativaDTO getEntidadFederativaDomicilioEmpleadoDTO() {
		return entidadFederativaDomicilioEmpleadoDTO;
	}

	public void setEntidadFederativaDomicilioEmpleadoDTO(EntidadFederativaDTO entidadFederativaDomicilioEmpleadoDTO) {
		this.entidadFederativaDomicilioEmpleadoDTO = entidadFederativaDomicilioEmpleadoDTO;
	}

	public MunicipioDTO getMunicipioDomicilioEmpleadoDTO() {
		return municipioDomicilioEmpleadoDTO;
	}

	public void setMunicipioDomicilioEmpleadoDTO(MunicipioDTO municipioDomicilioEmpleadoDTO) {
		this.municipioDomicilioEmpleadoDTO = municipioDomicilioEmpleadoDTO;
	}

	public ColoniaDTO getColoniaDomicilioEmpleadoDTO() {
		return coloniaDomicilioEmpleadoDTO;
	}

	public void setColoniaDomicilioEmpleadoDTO(ColoniaDTO coloniaDomicilioEmpleadoDTO) {
		this.coloniaDomicilioEmpleadoDTO = coloniaDomicilioEmpleadoDTO;
	}

	public CalleDTO getCalleDomicilioEmpleadoDTO() {
		return calleDomicilioEmpleadoDTO;
	}

	public void setCalleDomicilioEmpleadoDTO(CalleDTO calleDomicilioEmpleadoDTO) {
		this.calleDomicilioEmpleadoDTO = calleDomicilioEmpleadoDTO;
	}

	public String getNoExtDomicilioEmpleado() {
		return noExtDomicilioEmpleado;
	}

	public void setNoExtDomicilioEmpleado(String noExtDomicilioEmpleado) {
		this.noExtDomicilioEmpleado = noExtDomicilioEmpleado;
	}

	public String getNoIntDomicilioEmpleado() {
		return noIntDomicilioEmpleado;
	}

	public void setNoIntDomicilioEmpleado(String noIntDomicilioEmpleado) {
		this.noIntDomicilioEmpleado = noIntDomicilioEmpleado;
	}

	public CorporacionDTO getCorporacionEmpleadoDTO() {
		return corporacionEmpleadoDTO;
	}

	public void setCorporacionEmpleadoDTO(CorporacionDTO corporacionEmpleadoDTO) {
		this.corporacionEmpleadoDTO = corporacionEmpleadoDTO;
	}


	public TipoEstatusDTO getCarreraTruncaEmpleado() {
		return carreraTruncaEmpleadoDTO;
	}


	public void setCarreraTruncaEmpleado(TipoEstatusDTO i) {
		this.carreraTruncaEmpleadoDTO = i;
	}


	public String getFechaNacimientoEmpleadoString() {
		return fechaNacimientoEmpleadoString;
	}


	public void setFechaNacimientoEmpleadoString(String fechaNacimientoEmpleadoString) {
		this.fechaNacimientoEmpleadoString = fechaNacimientoEmpleadoString;
	}


	public String getFechaAltaEmpleadoString() {
		return fechaAltaEmpleadoString;
	}


	public void setFechaAltaEmpleadoString(String fechaAltaEmpleadoString) {
		this.fechaAltaEmpleadoString = fechaAltaEmpleadoString;
	}


	public String getFechaBajaEmpleadoString() {
		return fechaBajaEmpleadoString;
	}


	public void setFechaBajaEmpleadoString(String fechaBajaEmpleadoString) {
		this.fechaBajaEmpleadoString = fechaBajaEmpleadoString;
	}


	/**
	 * @return the apellidosEmpleado
	 */
	public String getApellidosEmpleado() {
		return apellidosEmpleado;
	}


	/**
	 * @param apellidosEmpleado the apellidosEmpleado to set
	 */
	public void setApellidosEmpleado(String apellidosEmpleado) {
		this.apellidosEmpleado = apellidosEmpleado;
	}
	
}
