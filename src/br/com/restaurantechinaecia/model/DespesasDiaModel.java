package br.com.restaurantechinaecia.model;

public class DespesasDiaModel {
	private int cod_despesa;
	private String nome;
	private Double valor;
	private String saida;
	
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String isSaida() {
		return saida;
	}
	public void setSaida(String saida) {
		this.saida = saida;
	}
	public int getCod_despesa() {
		return cod_despesa;
	}
	public void setCod_despesa(int cod_despesa) {
		this.cod_despesa = cod_despesa;
	}
}
