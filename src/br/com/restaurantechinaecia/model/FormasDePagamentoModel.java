package br.com.restaurantechinaecia.model;

public class FormasDePagamentoModel {
	
	private Double dinheiro, credito, debito, voucher, visa_vale;

	public Double getDinheiro() {
		return dinheiro;
	}
	public void setDinheiro(Double dinheiroR) {
		dinheiro = dinheiro +dinheiroR;
	}
	public Double getCredito() {
		return credito;
	}
	public void setCredito(Double creditoR) {
		credito = credito + creditoR;
	}
	public Double getDebito() {
		return debito;
	}
	public void setDebito(Double debitoR) {
		debito = debito + debitoR;
	}
	public Double getVoucher() {
		return voucher;
	}
	public void setVoucher(Double voucherR) {
		voucher = voucher + voucherR;
	}
	public Double getVisa_vale() {
		return visa_vale;
	}
	public void setVisa_vale(Double visa_valeR) {
		visa_vale = visa_vale + visa_valeR;
	}
}
