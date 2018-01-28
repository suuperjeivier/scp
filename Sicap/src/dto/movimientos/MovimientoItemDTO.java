package dto.movimientos;

import dto.listados.items.ItemDTO;

public class MovimientoItemDTO {
	
	private int idMovimientoItem;
	private MovimientoDTO fkIdMovimiento;
	private ItemDTO fkIdItem;	
	private String skuStringMovimientoItem;
	private String string1;
	private String string2;
	private String comentarios;
	private boolean boolean1;
	private boolean boolean2;
	private boolean boolean3;
	private boolean boolean4;
	private boolean boolean5;
	private boolean boolean6;
	private boolean boolean7;
	private boolean boolean8;
	private boolean boolean9;
	private boolean boolean10;
	private boolean boolean11;
	private boolean boolean12;
	private boolean statusMovimientoItem;
	
	public MovimientoItemDTO(){
		this.setFkIdMovimiento(new MovimientoDTO());
		this.setFkIdItem(new ItemDTO());
	}
	
	public int getIdMovimientoItem() {
		return idMovimientoItem;
	}
	
	public void setIdMovimientoItem(int idMovimientoItem) {
		this.idMovimientoItem = idMovimientoItem;
	}
	
	public MovimientoDTO getFkIdMovimiento() {
		return fkIdMovimiento;
	}
	
	public void setFkIdMovimiento(MovimientoDTO fkIdMovimiento) {
		this.fkIdMovimiento = fkIdMovimiento;
	}
	
	public ItemDTO getFkIdItem() {
		return fkIdItem;
	}
	
	public void setFkIdItem(ItemDTO fkIdItem) {
		this.fkIdItem = fkIdItem;
	}	
	
	public String getSkuStringMovimientoItem() {
		return skuStringMovimientoItem;
	}
	
	public void setSkuStringMovimientoItem(String skuStringMovimientoItem) {
		this.skuStringMovimientoItem = skuStringMovimientoItem;
	}
	
	public String getString1() {
		return string1;
	}
	
	public void setString1(String string1) {
		this.string1 = string1;
	}
	
	public String getString2() {
		return string2;
	}
	
	public void setString2(String string2) {
		this.string2 = string2;
	}
	
	public String getComentarios() {
		return comentarios;
	}
	
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	
	
	
	public boolean getStatusMovimientoItem() {
		return statusMovimientoItem;
	}
	
	public void setStatusMovimientoItem(boolean statusMovimientoItem) {
		this.statusMovimientoItem = statusMovimientoItem;
	}

	/**
	 * @return the boolean1
	 */
	public boolean isBoolean1() {
		return boolean1;
	}

	/**
	 * @param boolean1 the boolean1 to set
	 */
	public void setBoolean1(boolean boolean1) {
		this.boolean1 = boolean1;
	}

	/**
	 * @return the boolean2
	 */
	public boolean isBoolean2() {
		return boolean2;
	}

	/**
	 * @param boolean2 the boolean2 to set
	 */
	public void setBoolean2(boolean boolean2) {
		this.boolean2 = boolean2;
	}

	/**
	 * @return the boolean3
	 */
	public boolean isBoolean3() {
		return boolean3;
	}

	/**
	 * @param boolean3 the boolean3 to set
	 */
	public void setBoolean3(boolean boolean3) {
		this.boolean3 = boolean3;
	}

	/**
	 * @return the boolean4
	 */
	public boolean isBoolean4() {
		return boolean4;
	}

	/**
	 * @param boolean4 the boolean4 to set
	 */
	public void setBoolean4(boolean boolean4) {
		this.boolean4 = boolean4;
	}

	/**
	 * @return the boolean5
	 */
	public boolean isBoolean5() {
		return boolean5;
	}

	/**
	 * @param boolean5 the boolean5 to set
	 */
	public void setBoolean5(boolean boolean5) {
		this.boolean5 = boolean5;
	}

	/**
	 * @return the boolean6
	 */
	public boolean isBoolean6() {
		return boolean6;
	}

	/**
	 * @param boolean6 the boolean6 to set
	 */
	public void setBoolean6(boolean boolean6) {
		this.boolean6 = boolean6;
	}

	/**
	 * @return the boolean7
	 */
	public boolean isBoolean7() {
		return boolean7;
	}

	/**
	 * @param boolean7 the boolean7 to set
	 */
	public void setBoolean7(boolean boolean7) {
		this.boolean7 = boolean7;
	}

	/**
	 * @return the boolean8
	 */
	public boolean isBoolean8() {
		return boolean8;
	}

	/**
	 * @param boolean8 the boolean8 to set
	 */
	public void setBoolean8(boolean boolean8) {
		this.boolean8 = boolean8;
	}

	/**
	 * @return the boolean9
	 */
	public boolean isBoolean9() {
		return boolean9;
	}

	/**
	 * @param boolean9 the boolean9 to set
	 */
	public void setBoolean9(boolean boolean9) {
		this.boolean9 = boolean9;
	}

	/**
	 * @return the boolean10
	 */
	public boolean isBoolean10() {
		return boolean10;
	}

	/**
	 * @param boolean10 the boolean10 to set
	 */
	public void setBoolean10(boolean boolean10) {
		this.boolean10 = boolean10;
	}

	/**
	 * @return the boolean11
	 */
	public boolean isBoolean11() {
		return boolean11;
	}

	/**
	 * @param boolean11 the boolean11 to set
	 */
	public void setBoolean11(boolean boolean11) {
		this.boolean11 = boolean11;
	}

	/**
	 * @return the boolean12
	 */
	public boolean isBoolean12() {
		return boolean12;
	}

	/**
	 * @param boolean12 the boolean12 to set
	 */
	public void setBoolean12(boolean boolean12) {
		this.boolean12 = boolean12;
	}

	
}
