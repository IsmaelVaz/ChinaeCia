package br.com.restaurantechinaecia.model;


public class FechamentoDiaModel {
	
	private String data;
	private Double fundo;//clnFundo,
	private Double credito;//clnCredito,
	private Double debito;//clnDebito,
	private Double voucher;//clnVoucher,
	private Double visaVale;//clnVisaVale,
	private Double saida;//;clnSaida,
	private Double despesa;//clnDespesa,
	private Double total;//clnTotal,
	private Double lucro;//clnLucro;
	
	
	public Double getFundo() {
		return fundo;
	}
	public void setFundo(Double fundo) {
		this.fundo = fundo;
	}
	public Double getCredito() {
		return credito;
	}
	public void setCredito(Double credito) {
		this.credito = credito;
	}
	public Double getDebito() {
		return debito;
	}
	public void setDebito(Double debito) {
		this.debito = debito;
	}
	public Double getVoucher() {
		return voucher;
	}
	public void setVoucher(Double voucher) {
		this.voucher = voucher;
	}
	public Double getVisaVale() {
		return visaVale;
	}
	public void setVisaVale(Double visaVale) {
		this.visaVale = visaVale;
	}
	public Double getSaida() {
		return saida;
	}
	public void setSaida(Double saida) {
		this.saida = saida;
	}
	public Double getDespesa() {
		return despesa;
	}
	public void setDespesa(Double despesa) {
		this.despesa = despesa;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getLucro() {
		return lucro;
	}
	public void setLucro(Double lucro) {
		this.lucro = lucro;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
