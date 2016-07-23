package br.com.restaurantechinaecia.model;

public class Cartao {

	private int id;
	private String nome;
	private int tipo;
	
	@Override
	public String toString(){
		
		return getNome();
	}
	public Cartao(){
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
