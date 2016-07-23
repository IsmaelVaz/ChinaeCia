package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.util.ResourceBundle;
import br.com.restaurantechinaecia.view.MainChinaCia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class BorderADMController implements Initializable{

	@FXML
	MenuItem itemMesas, itemTodosProdutos,itemDespesaDia, itemProdutosVDia, itemFechaDia, itemFechaMes,
	itemProdutosVMes, itemDespesaMes,itemContaUsuario, itemContaCliente, itemRelatorioFecha, itemLoginEntrar,itemConfig;
	
	MainChinaCia main;
	public BorderADMController(MainChinaCia main) {
		// TODO Auto-generated constructor stub
		this.main=main;
	}
//comentario inicial
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		itemMesas.setOnAction(l-> CarregarMesa());
		itemTodosProdutos.setOnAction(l-> CarregarProdutos());
		itemDespesaDia.setOnAction(l-> CarregarDespesasDia());
		itemProdutosVDia.setOnAction(l-> CarregarProdutosDia());
		itemFechaDia.setOnAction(l-> CarregarFechamentoDia());
		itemProdutosVMes.setOnAction(l-> CarregarProdutosVendidosMes());
		itemDespesaMes.setOnAction(l-> CarregarDespesasMes());
		itemContaUsuario.setOnAction(l-> CarregarUsuarios());
		itemContaCliente.setOnAction(l-> CarregarClientes());
		itemFechaMes.setOnAction(l-> CarregarFechamentoMes());
		itemRelatorioFecha.setOnAction(l-> CarregarRelatorioLucro());
		itemLoginEntrar.setOnAction(l-> CarregarEntrar());
                itemConfig.setOnAction(l-> CarregarConfig());
	}
	public void CarregarRelatorioLucro(){
		main.CarregarRelatorioLucro();
	}
	private void CarregarFechamentoMes() {
		main.CarregarFechamentoMes();
	}
	public void CarregarClientes(){
		main.CarregarClientes();
	}
	public void CarregarUsuarios(){
		main.CarregarUsuarios();
	}
	public void CarregarDespesasMes(){
		main.CarregarDespesasMes();
	}
	private void CarregarProdutosVendidosMes(){
		main.CarregarProdutosVendidosMes();
	}
	private void CarregarFechamentoDia() {
		main.CarregarFechamentoDia();
	}
	public void CarregarMesa(){
		main.CarregarMesas();
	}
	public void CarregarProdutos(){
		main.CarregarProdutos();
	}
	public void CarregarDespesasDia(){
		main.CarregarDespesaDia();
	}
	public void CarregarProdutosDia(){
		main.CarregarProdutosDia();
	}
	public void CarregarEntrar(){
		main.TelaLogin();
	}
        public void CarregarConfig(){
                main.CarregarConfig();
        }
}
