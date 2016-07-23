package br.com.restaurantechinaecia.model;

public class Categoria {

	private int cod_categoria;
	private String nome;
	
	@Override
	public String toString(){
		
		return getNome();
	}
	public int getCod_categoria() {
		return cod_categoria;
	}
	public void setCod_categoria(int cod_categoria) {
		this.cod_categoria = cod_categoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
