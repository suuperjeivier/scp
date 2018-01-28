package dto.partner;

import java.sql.Blob;
import java.time.LocalDate;

import dto.DomicilioDTO;
import dto.empleado.EscolaridadDTO;
import dto.empleado.EspecialidadDTO;
import dto.empleado.NivelAcademicoDTO;
import dto.listados.CorporacionDTO;
import dto.listados.EstadoCivilDTO;
import dto.listados.GeneroDTO;
import dto.listados.GradoPeriodoEscolarDTO;
import dto.listados.GrupoSanguineoDTO;
import dto.listados.PeriodoEscolarDTO;
import dto.listados.TipoEstatusDTO;

public class PartnerDTO {

	private int idPartner;
	private int tipoPartner;
	private int skuPartner;	
	private String skuStringPartner;
	private String cuipPartner;
	private String nombrePartner;
	private String apPaternoPartner;
	private String apMaternoPartner;
	private String apellidosPartner;
	private String nombreCompletoPartner;
	private GeneroDTO generoPartnerDTO;
	private EstadoCivilDTO edoCivilPartnerDTO;
	private LocalDate fechaNacimientoPartner;
	private String fechaNacimientoPartnerString;
	private GrupoSanguineoDTO grupoSanguineoPartnerDTO;
	private DomicilioDTO domicilioPartnerDTO;
	private String telFijoPartner;
	private String telMovilPartner;
	private String correoElectronicoPartner;
	private String curpPartner;
	private String rfcPartner;
	private EspecialidadDTO especialidadDTO;
	private byte[] documentoComprobanteInfoPartner;
	private String tipoComprobanteInfoPartner;
	private String nombreComprobanteInfoPartner;
	private LocalDate fechaAltaPartner;
	private String fechaAltaStringPartner;
	private LocalDate fechaBajaPartner;
	private String fechaBajaStringPartner;
	private byte[] fotoPartner;
	private Blob fotoPartnerBD;
	private String fotoPartnerBase64;
	private byte[] firmaPartner;
	private NivelAcademicoDTO nivelAcademicoPartnerDTO;
	private TipoEstatusDTO carreraTruncaPartnerDTO;
	private GradoPeriodoEscolarDTO gradoPeridoEscolarPartnerDTO;
	private PeriodoEscolarDTO peridoEscolarPartnerDTO;
	private EscolaridadDTO escolaridadPartnerDTO;
	private CorporacionDTO corporacionPartnerDTO;
	private TipoEstatusDTO estatusPartnerDTO;

	/**
	 * CONSTRUCTOR
	 */
	 public PartnerDTO(){
		 this.setIdPartner(-1);
		 if(this.getGeneroPartnerDTO() == null){
			 this.setGeneroPartnerDTO(new GeneroDTO());
		 }
		 if(this.getEdoCivilPartnerDTO() == null){
			 this.setEdoCivilPartnerDTO(new EstadoCivilDTO());
		 }
		 if(this.getGrupoSanguineoPartnerDTO() == null){
			 this.setGrupoSanguineoPartnerDTO(new GrupoSanguineoDTO());
		 }		
		 if(this.getDomicilioPartnerDTO() == null){
			 this.setDomicilioPartnerDTO(new DomicilioDTO());
		 }
		 if(this.getEspecialidadDTO() == null){
			 this.setEspecialidadDTO(new EspecialidadDTO());
		 }
		 if(this.getNivelAcademicoPartnerDTO() == null){
			 this.setNivelAcademicoPartnerDTO(new NivelAcademicoDTO());
		 }
		 if(this.getGradoPeridoEscolarPartnerDTO() == null){
			 this.setGradoPeridoEscolarPartnerDTO(new GradoPeriodoEscolarDTO());
		 }
		 if(this.getPeridoEscolarPartnerDTO() == null){
			 this.setPeridoEscolarPartnerDTO(new PeriodoEscolarDTO());
		 }
		 if(this.getEscolaridadPartnerDTO() == null){
			 this.setEscolaridadPartnerDTO(new EscolaridadDTO());
		 }
		 if(this.getCorporacionPartnerDTO() == null){
			 this.setCorporacionPartnerDTO(new CorporacionDTO());
		 }
		 if(this.getEstatusPartnerDTO() == null){
			 this.setEstatusPartnerDTO(new TipoEstatusDTO());
		 }
		 if (this.getCarreraTruncaPartnerDTO() == null){
			 this.setCarreraTruncaPartnerDTO(new TipoEstatusDTO());
		 }
	 }

	/**
	 * @return the idPartner
	 */
	public int getIdPartner() {
		return idPartner;
	}

	/**
	 * @param idPartner the idPartner to set
	 */
	public void setIdPartner(int idPartner) {
		this.idPartner = idPartner;
	}

	/**
	 * @return the skuPartner
	 */
	public int getSkuPartner() {
		return skuPartner;
	}

	/**
	 * @param skuPartner the skuPartner to set
	 */
	public void setSkuPartner(int skuPartner) {
		this.skuPartner = skuPartner;
	}

	/**
	 * @return the cuipPartner
	 */
	public String getCuipPartner() {
		return cuipPartner;
	}

	/**
	 * @param cuipPartner the cuipPartner to set
	 */
	public void setCuipPartner(String cuipPartner) {
		this.cuipPartner = cuipPartner;
	}

	/**
	 * @return the nombrePartner
	 */
	public String getNombrePartner() {
		return nombrePartner;
	}

	/**
	 * @param nombrePartner the nombrePartner to set
	 */
	public void setNombrePartner(String nombrePartner) {
		this.nombrePartner = nombrePartner;
	}

	/**
	 * @return the apPaternoPartner
	 */
	public String getApPaternoPartner() {
		return apPaternoPartner;
	}

	/**
	 * @param apPaternoPartner the apPaternoPartner to set
	 */
	public void setApPaternoPartner(String apPaternoPartner) {
		this.apPaternoPartner = apPaternoPartner;
	}

	/**
	 * @return the apMaternoPartner
	 */
	public String getApMaternoPartner() {
		return apMaternoPartner;
	}

	/**
	 * @param apMaternoPartner the apMaternoPartner to set
	 */
	public void setApMaternoPartner(String apMaternoPartner) {
		this.apMaternoPartner = apMaternoPartner;
	}

	/**
	 * @return the apellidosPartner
	 */
	public String getApellidosPartner() {
		return apellidosPartner;
	}

	/**
	 * @param apellidosPartner the apellidosPartner to set
	 */
	public void setApellidosPartner(String apellidosPartner) {
		this.apellidosPartner = apellidosPartner;
	}

	/**
	 * @return the nombreCompletoPartner
	 */
	public String getNombreCompletoPartner() {
		return nombreCompletoPartner;
	}

	/**
	 * @param nombreCompletoPartner the nombreCompletoPartner to set
	 */
	public void setNombreCompletoPartner(String nombreCompletoPartner) {
		this.nombreCompletoPartner = nombreCompletoPartner;
	}

	/**
	 * @return the generoPartnerDTO
	 */
	public GeneroDTO getGeneroPartnerDTO() {
		return generoPartnerDTO;
	}

	/**
	 * @param generoPartnerDTO the generoPartnerDTO to set
	 */
	public void setGeneroPartnerDTO(GeneroDTO generoPartnerDTO) {
		this.generoPartnerDTO = generoPartnerDTO;
	}

	/**
	 * @return the edoCivilPartnerDTO
	 */
	public EstadoCivilDTO getEdoCivilPartnerDTO() {
		return edoCivilPartnerDTO;
	}

	/**
	 * @param edoCivilPartnerDTO the edoCivilPartnerDTO to set
	 */
	public void setEdoCivilPartnerDTO(EstadoCivilDTO edoCivilPartnerDTO) {
		this.edoCivilPartnerDTO = edoCivilPartnerDTO;
	}

	/**
	 * @return the fechaNacimientoPartner
	 */
	public LocalDate getFechaNacimientoPartner() {
		return fechaNacimientoPartner;
	}

	/**
	 * @param fechaNacimientoPartner the fechaNacimientoPartner to set
	 */
	public void setFechaNacimientoPartner(LocalDate fechaNacimientoPartner) {
		this.fechaNacimientoPartner = fechaNacimientoPartner;
	}

	/**
	 * @return the fechaNacimientoPartnerString
	 */
	public String getFechaNacimientoPartnerString() {
		return fechaNacimientoPartnerString;
	}

	/**
	 * @param fechaNacimientoPartnerString the fechaNacimientoPartnerString to set
	 */
	public void setFechaNacimientoPartnerString(String fechaNacimientoPartnerString) {
		this.fechaNacimientoPartnerString = fechaNacimientoPartnerString;
	}

	/**
	 * @return the grupoSanguineoPartnerDTO
	 */
	public GrupoSanguineoDTO getGrupoSanguineoPartnerDTO() {
		return grupoSanguineoPartnerDTO;
	}

	/**
	 * @param grupoSanguineoPartnerDTO the grupoSanguineoPartnerDTO to set
	 */
	public void setGrupoSanguineoPartnerDTO(GrupoSanguineoDTO grupoSanguineoPartnerDTO) {
		this.grupoSanguineoPartnerDTO = grupoSanguineoPartnerDTO;
	}

	/**
	 * @return the domicilioPartnerDTO
	 */
	public DomicilioDTO getDomicilioPartnerDTO() {
		return domicilioPartnerDTO;
	}

	/**
	 * @param domicilioPartnerDTO the domicilioPartnerDTO to set
	 */
	public void setDomicilioPartnerDTO(DomicilioDTO domicilioPartnerDTO) {
		this.domicilioPartnerDTO = domicilioPartnerDTO;
	}

	/**
	 * @return the telFijoPartner
	 */
	public String getTelFijoPartner() {
		return telFijoPartner;
	}

	/**
	 * @param telFijoPartner the telFijoPartner to set
	 */
	public void setTelFijoPartner(String telFijoPartner) {
		this.telFijoPartner = telFijoPartner;
	}

	/**
	 * @return the telMovilPartner
	 */
	public String getTelMovilPartner() {
		return telMovilPartner;
	}

	/**
	 * @param telMovilPartner the telMovilPartner to set
	 */
	public void setTelMovilPartner(String telMovilPartner) {
		this.telMovilPartner = telMovilPartner;
	}

	/**
	 * @return the correoElectronicoPartner
	 */
	public String getCorreoElectronicoPartner() {
		return correoElectronicoPartner;
	}

	/**
	 * @param correoElectronicoPartner the correoElectronicoPartner to set
	 */
	public void setCorreoElectronicoPartner(String correoElectronicoPartner) {
		this.correoElectronicoPartner = correoElectronicoPartner;
	}

	/**
	 * @return the curpPartner
	 */
	public String getCurpPartner() {
		return curpPartner;
	}

	/**
	 * @param curpPartner the curpPartner to set
	 */
	public void setCurpPartner(String curpPartner) {
		this.curpPartner = curpPartner;
	}

	/**
	 * @return the rfcPartner
	 */
	public String getRfcPartner() {
		return rfcPartner;
	}

	/**
	 * @param rfcPartner the rfcPartner to set
	 */
	public void setRfcPartner(String rfcPartner) {
		this.rfcPartner = rfcPartner;
	}

	/**
	 * @return the especialidadDTO
	 */
	public EspecialidadDTO getEspecialidadDTO() {
		return especialidadDTO;
	}

	/**
	 * @param especialidadDTO the especialidadDTO to set
	 */
	public void setEspecialidadDTO(EspecialidadDTO especialidadDTO) {
		this.especialidadDTO = especialidadDTO;
	}

	/**
	 * @return the documentoComprobanteInfoPartner
	 */
	public byte[] getDocumentoComprobanteInfoPartner() {
		return documentoComprobanteInfoPartner;
	}

	/**
	 * @param documentoComprobanteInfoPartner the documentoComprobanteInfoPartner to set
	 */
	public void setDocumentoComprobanteInfoPartner(byte[] documentoComprobanteInfoPartner) {
		this.documentoComprobanteInfoPartner = documentoComprobanteInfoPartner;
	}

	/**
	 * @return the tipoComprobanteInfoPartner
	 */
	public String getTipoComprobanteInfoPartner() {
		return tipoComprobanteInfoPartner;
	}

	/**
	 * @param tipoComprobanteInfoPartner the tipoComprobanteInfoPartner to set
	 */
	public void setTipoComprobanteInfoPartner(String tipoComprobanteInfoPartner) {
		this.tipoComprobanteInfoPartner = tipoComprobanteInfoPartner;
	}

	/**
	 * @return the nombreComprobanteInfoPartner
	 */
	public String getNombreComprobanteInfoPartner() {
		return nombreComprobanteInfoPartner;
	}

	/**
	 * @param nombreComprobanteInfoPartner the nombreComprobanteInfoPartner to set
	 */
	public void setNombreComprobanteInfoPartner(String nombreComprobanteInfoPartner) {
		this.nombreComprobanteInfoPartner = nombreComprobanteInfoPartner;
	}

	/**
	 * @return the fechaAltaPartner
	 */
	public LocalDate getFechaAltaPartner() {
		return fechaAltaPartner;
	}

	/**
	 * @param fechaAltaPartner the fechaAltaPartner to set
	 */
	public void setFechaAltaPartner(LocalDate fechaAltaPartner) {
		this.fechaAltaPartner = fechaAltaPartner;
	}

	/**
	 * @return the fechaAltaStringPartner
	 */
	public String getFechaAltaStringPartner() {
		return fechaAltaStringPartner;
	}

	/**
	 * @param fechaAltaStringPartner the fechaAltaStringPartner to set
	 */
	public void setFechaAltaStringPartner(String fechaAltaStringPartner) {
		this.fechaAltaStringPartner = fechaAltaStringPartner;
	}

	/**
	 * @return the fechaBajaPartner
	 */
	public LocalDate getFechaBajaPartner() {
		return fechaBajaPartner;
	}

	/**
	 * @param fechaBajaPartner the fechaBajaPartner to set
	 */
	public void setFechaBajaPartner(LocalDate fechaBajaPartner) {
		this.fechaBajaPartner = fechaBajaPartner;
	}

	/**
	 * @return the fechaBajaStringPartner
	 */
	public String getFechaBajaStringPartner() {
		return fechaBajaStringPartner;
	}

	/**
	 * @param fechaBajaStringPartner the fechaBajaStringPartner to set
	 */
	public void setFechaBajaStringPartner(String fechaBajaStringPartner) {
		this.fechaBajaStringPartner = fechaBajaStringPartner;
	}

	/**
	 * @return the fotoPartner
	 */
	public byte[] getFotoPartner() {
		return fotoPartner;
	}

	/**
	 * @param fotoPartner the fotoPartner to set
	 */
	public void setFotoPartner(byte[] fotoPartner) {
		this.fotoPartner = fotoPartner;
	}

	/**
	 * @return the fotoPartnerBD
	 */
	public Blob getFotoPartnerBD() {
		return fotoPartnerBD;
	}

	/**
	 * @param fotoPartnerBD the fotoPartnerBD to set
	 */
	public void setFotoPartnerBD(Blob fotoPartnerBD) {
		this.fotoPartnerBD = fotoPartnerBD;
	}

	/**
	 * @return the fotoPartnerBase64
	 */
	public String getFotoPartnerBase64() {
		return fotoPartnerBase64;
	}

	/**
	 * @param fotoPartnerBase64 the fotoPartnerBase64 to set
	 */
	public void setFotoPartnerBase64(String fotoPartnerBase64) {
		this.fotoPartnerBase64 = fotoPartnerBase64;
	}

	/**
	 * @return the firmaPartner
	 */
	public byte[] getFirmaPartner() {
		return firmaPartner;
	}

	/**
	 * @param firmaPartner the firmaPartner to set
	 */
	public void setFirmaPartner(byte[] firmaPartner) {
		this.firmaPartner = firmaPartner;
	}

	/**
	 * @return the nivelAcademicoPartnerDTO
	 */
	public NivelAcademicoDTO getNivelAcademicoPartnerDTO() {
		return nivelAcademicoPartnerDTO;
	}

	/**
	 * @param nivelAcademicoPartnerDTO the nivelAcademicoPartnerDTO to set
	 */
	public void setNivelAcademicoPartnerDTO(NivelAcademicoDTO nivelAcademicoPartnerDTO) {
		this.nivelAcademicoPartnerDTO = nivelAcademicoPartnerDTO;
	}

	/**
	 * @return the carreraTruncaPartnerDTO
	 */
	public TipoEstatusDTO getCarreraTruncaPartnerDTO() {
		return carreraTruncaPartnerDTO;
	}

	/**
	 * @param carreraTruncaPartnerDTO the carreraTruncaPartnerDTO to set
	 */
	public void setCarreraTruncaPartnerDTO(TipoEstatusDTO carreraTruncaPartnerDTO) {
		this.carreraTruncaPartnerDTO = carreraTruncaPartnerDTO;
	}

	/**
	 * @return the gradoPeridoEscolarPartnerDTO
	 */
	public GradoPeriodoEscolarDTO getGradoPeridoEscolarPartnerDTO() {
		return gradoPeridoEscolarPartnerDTO;
	}

	/**
	 * @param gradoPeridoEscolarPartnerDTO the gradoPeridoEscolarPartnerDTO to set
	 */
	public void setGradoPeridoEscolarPartnerDTO(GradoPeriodoEscolarDTO gradoPeridoEscolarPartnerDTO) {
		this.gradoPeridoEscolarPartnerDTO = gradoPeridoEscolarPartnerDTO;
	}

	/**
	 * @return the peridoEscolarPartnerDTO
	 */
	public PeriodoEscolarDTO getPeridoEscolarPartnerDTO() {
		return peridoEscolarPartnerDTO;
	}

	/**
	 * @param peridoEscolarPartnerDTO the peridoEscolarPartnerDTO to set
	 */
	public void setPeridoEscolarPartnerDTO(PeriodoEscolarDTO peridoEscolarPartnerDTO) {
		this.peridoEscolarPartnerDTO = peridoEscolarPartnerDTO;
	}

	/**
	 * @return the escolaridadPartnerDTO
	 */
	public EscolaridadDTO getEscolaridadPartnerDTO() {
		return escolaridadPartnerDTO;
	}

	/**
	 * @param escolaridadPartnerDTO the escolaridadPartnerDTO to set
	 */
	public void setEscolaridadPartnerDTO(EscolaridadDTO escolaridadPartnerDTO) {
		this.escolaridadPartnerDTO = escolaridadPartnerDTO;
	}

	/**
	 * @return the corporacionPartnerDTO
	 */
	public CorporacionDTO getCorporacionPartnerDTO() {
		return corporacionPartnerDTO;
	}

	/**
	 * @param corporacionPartnerDTO the corporacionPartnerDTO to set
	 */
	public void setCorporacionPartnerDTO(CorporacionDTO corporacionPartnerDTO) {
		this.corporacionPartnerDTO = corporacionPartnerDTO;
	}

	/**
	 * @return the estatusPartnerDTO
	 */
	public TipoEstatusDTO getEstatusPartnerDTO() {
		return estatusPartnerDTO;
	}

	/**
	 * @param estatusPartnerDTO the estatusPartnerDTO to set
	 */
	public void setEstatusPartnerDTO(TipoEstatusDTO estatusPartnerDTO) {
		this.estatusPartnerDTO = estatusPartnerDTO;
	}

	/**
	 * @return the tipoPartner
	 */
	public int getTipoPartner() {
		return tipoPartner;
	}

	/**
	 * @param tipoPartner the tipoPartner to set
	 */
	public void setTipoPartner(int tipoPartner) {
		this.tipoPartner = tipoPartner;
	}

	/**
	 * @return the skuStringPartner
	 */
	public String getSkuStringPartner() {
		return skuStringPartner;
	}

	/**
	 * @param skuStringPartner the skuStringPartner to set
	 */
	public void setSkuStringPartner(String skuStringPartner) {
		this.skuStringPartner = skuStringPartner;
	}


	
}
