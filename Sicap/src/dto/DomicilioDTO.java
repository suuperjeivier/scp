package dto;

import dto.listados.CalleDTO;
import dto.listados.ColoniaDTO;
import dto.listados.EntidadFederativaDTO;
import dto.listados.MunicipioDTO;

public class DomicilioDTO {
	private int codigoPostalDomicilioPartner;
	private EntidadFederativaDTO entidadFederativaDomicilioPartnerDTO;
	private MunicipioDTO municipioDomicilioPartnerDTO;
	private ColoniaDTO coloniaDomicilioPartnerDTO;	
	private CalleDTO calleDomicilioPartnerDTO;
	private String noExtDomicilioPartner;
	private String noIntDomicilioPartner;
	private String referenciasDomicilioPartner;
	
	public DomicilioDTO(){
		setEntidadFederativaDomicilioPartnerDTO(new EntidadFederativaDTO());
		setCalleDomicilioPartnerDTO(new CalleDTO());
		setMunicipioDomicilioPartnerDTO(new MunicipioDTO());
		setColoniaDomicilioPartnerDTO(new ColoniaDTO());		
	}
	
	/**
	 * @return the codigoPostalDomicilioPartner
	 */
	public int getCodigoPostalDomicilioPartner() {
		return codigoPostalDomicilioPartner;
	}
	/**
	 * @param codigoPostalDomicilioPartner the codigoPostalDomicilioPartner to set
	 */
	public void setCodigoPostalDomicilioPartner(int codigoPostalDomicilioPartner) {
		this.codigoPostalDomicilioPartner = codigoPostalDomicilioPartner;
	}
	/**
	 * @return the entidadFederativaDomicilioPartnerDTO
	 */
	public EntidadFederativaDTO getEntidadFederativaDomicilioPartnerDTO() {
		return entidadFederativaDomicilioPartnerDTO;
	}
	/**
	 * @param entidadFederativaDomicilioPartnerDTO the entidadFederativaDomicilioPartnerDTO to set
	 */
	public void setEntidadFederativaDomicilioPartnerDTO(EntidadFederativaDTO entidadFederativaDomicilioPartnerDTO) {
		this.entidadFederativaDomicilioPartnerDTO = entidadFederativaDomicilioPartnerDTO;
	}
	/**
	 * @return the municipioDomicilioPartnerDTO
	 */
	public MunicipioDTO getMunicipioDomicilioPartnerDTO() {
		return municipioDomicilioPartnerDTO;
	}
	/**
	 * @param municipioDomicilioPartnerDTO the municipioDomicilioPartnerDTO to set
	 */
	public void setMunicipioDomicilioPartnerDTO(MunicipioDTO municipioDomicilioPartnerDTO) {
		this.municipioDomicilioPartnerDTO = municipioDomicilioPartnerDTO;
	}
	/**
	 * @return the coloniaDomicilioPartnerDTO
	 */
	public ColoniaDTO getColoniaDomicilioPartnerDTO() {
		return coloniaDomicilioPartnerDTO;
	}
	/**
	 * @param coloniaDomicilioPartnerDTO the coloniaDomicilioPartnerDTO to set
	 */
	public void setColoniaDomicilioPartnerDTO(ColoniaDTO coloniaDomicilioPartnerDTO) {
		this.coloniaDomicilioPartnerDTO = coloniaDomicilioPartnerDTO;
	}
	/**
	 * @return the calleDomicilioPartnerDTO
	 */
	public CalleDTO getCalleDomicilioPartnerDTO() {
		return calleDomicilioPartnerDTO;
	}
	/**
	 * @param calleDomicilioPartnerDTO the calleDomicilioPartnerDTO to set
	 */
	public void setCalleDomicilioPartnerDTO(CalleDTO calleDomicilioPartnerDTO) {
		this.calleDomicilioPartnerDTO = calleDomicilioPartnerDTO;
	}
	/**
	 * @return the noExtDomicilioPartner
	 */
	public String getNoExtDomicilioPartner() {
		return noExtDomicilioPartner;
	}
	/**
	 * @param noExtDomicilioPartner the noExtDomicilioPartner to set
	 */
	public void setNoExtDomicilioPartner(String noExtDomicilioPartner) {
		this.noExtDomicilioPartner = noExtDomicilioPartner;
	}
	/**
	 * @return the noIntDomicilioPartner
	 */
	public String getNoIntDomicilioPartner() {
		return noIntDomicilioPartner;
	}
	/**
	 * @param noIntDomicilioPartner the noIntDomicilioPartner to set
	 */
	public void setNoIntDomicilioPartner(String noIntDomicilioPartner) {
		this.noIntDomicilioPartner = noIntDomicilioPartner;
	}

	/**
	 * @return the referenciasDomicilioPartner
	 */
	public String getReferenciasDomicilioPartner() {
		return referenciasDomicilioPartner;
	}

	/**
	 * @param referenciasDomicilioPartner the referenciasDomicilioPartner to set
	 */
	public void setReferenciasDomicilioPartner(String referenciasDomicilioPartner) {
		this.referenciasDomicilioPartner = referenciasDomicilioPartner;
	}
}
