package br.com.restaurantechinaecia.model;

public class Empresa {
	private String nome;
	private int cod_empresa;
	private String cnpj;
	
	@Override
	public String toString(){
		
		return getNome();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCod_empresa() {
		return cod_empresa;
	}
	public void setCod_empresa(int cod_empresa) {
		this.cod_empresa = cod_empresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
