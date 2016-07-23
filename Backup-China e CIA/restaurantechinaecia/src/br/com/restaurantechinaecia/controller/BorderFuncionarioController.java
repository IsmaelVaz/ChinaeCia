package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.util.ResourceBundle;
import br.com.restaurantechinaecia.view.MainChinaCia;
import javafx.fxml.*;
import javafx.scene.control.MenuItem;

public class BorderFuncionarioController implements Initializable{
	@FXML 
	MenuItem itemCodigos, itemDespesasDia, itemLoginEntrar, itemCliente;

	MainChinaCia main;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		itemCodigos.setOnAction(l-> CarregarMesa());
		itemDespesasDia.setOnAction(l->CarregarDespesasDia());
		itemLoginEntrar.setOnAction(l-> LoginEntar());
		itemCliente.setOnAction(l-> CarregarCliente());
	}
	public BorderFuncionarioController(MainChinaCia main) {
		// TODO Auto-generated constructor stub
		this.main=main;
	}
	public void CarregarMesa(){
		main.CarregarMesas();
	}
	public void CarregarDespesasDia(){
		main.CarregarDespesaDia();
	}
	public void LoginEntar(){
		main.TelaLogin();
	}
	public void CarregarCliente(){
		main.CarregarClientes();
	}
	public void LoginSair(){
		main.TelaLogin();
	}
}
