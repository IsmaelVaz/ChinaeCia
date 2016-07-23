package br.com.restaurantechinaecia.model;

public class ContaClienteModel {

	private int cod_cliente;
	private String cpf;
	private String nome;
	private String rg;
	private String telefone;
	private String data;
	private double ultimoValor;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getUltimoValor() {
		return ultimoValor;
	}
	public void setUltimoValor(double ultimoValor) {
		this.ultimoValor = ultimoValor;
	}
	public int getCod_cliente() {
		return cod_cliente;
	}
	public void setCod_cliente(int cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
}
