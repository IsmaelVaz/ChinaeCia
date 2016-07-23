package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import br.com.restaurantechinaecia.banco_de_dados.BdControl;
import br.com.restaurantechinaecia.model.*;
import br.com.restaurantechinaecia.view.MainChinaCia;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ContaClienteMostrarController implements Initializable {

	@FXML
	TableView<ProdutoModel> tableView;
	
	@FXML
	TableColumn<ProdutoModel, String> clnId, clnProduto, clnValor, clnData;
	
	@FXML
	Button btnPagamento, btnNao, btnSim, btnOk;
	
	@FXML
	RadioButton radioTudo, radioParte;
	
	@FXML
	TextField txtValor, txtValorTotal;
	
	@FXML 
	Pane paneParte, paneRadio, paneManterCliente;
	
	MainChinaCia main;
	int cliente;
	BdControl bd;
	double valor;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
			bd= new BdControl();
		
		paneParte.setVisible(false);
		
		ToggleGroup group =new ToggleGroup();
		radioParte.setToggleGroup(group);
		radioTudo.setToggleGroup(group);
		
		String sql="SELECT cp.cod_produto, cp.cod_cliente, SUM(p.valor) AS total "
				+ "FROM cliente_produto AS cp "
				+ "INNER JOIN produto AS p "
				+ "ON (cp.cod_produto = p.cod_produto) "
				+ "WHERE cp.cod_cliente = "+cliente+";";
		
		ResultSet rs = bd.DadosComRetorno(sql);
		valor = 0;
		try {
			while(rs.next()){
				valor = rs.getDouble("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtValorTotal.setText("R$ "+valor);
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if(radioTudo.isSelected()){					
					ADDProdutoVendido();
					main.CarregarPagamento(valor);
				}else if (radioParte.isSelected()){
					paneParte.setVisible(true);
					txtValor.setVisible(true);
				}
			}
		});	

		clnId.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("id"));
		clnProduto.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("nome"));
		clnValor.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("valor"));
		clnData.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("data"));
		
		AtualizarTabela();
		btnOk.setOnAction(l-> GerarPagamento());
		btnPagamento.setOnAction(l-> paneRadio.setVisible(true));
	}

	public ContaClienteMostrarController(int cliente, MainChinaCia main) {
		// TODO Auto-generated constructor stub
		this.cliente=cliente;
		this.main=main;
	}
	public void ADDProdutoVendido(){
		java.util.Date dd = new java.util.Date();
		Date data = new Date(dd.getTime());
		String hora = String.format("%s:%s:%s", dd.getHours(), dd.getMinutes(), dd.getMinutes());
		
		String sql="SELECT cp.cod_produto "
				+ "from cliente_produto AS cp "
				+ "WHERE cp.cod_cliente="+cliente+" and cp.cod_produto<>0;";
		
		ResultSet rs = bd.DadosComRetorno(sql);
		
		try {
			while(rs.next()){
				sql="INSERT INTO produto_vendido(cod_produto, data) "
						+ "values ("+rs.getInt("cod_produto")+", '"+data+" "+hora+"');";
				
				bd.DadosSemRetorno(sql);
			}
			sql="DELETE FROM cliente_produto WHERE cod_cliente ="+cliente+" and cod_produto<>0;";
			bd.DadosSemRetorno(sql);
			sql ="UPDATE cliente_produto set data ='"+data+" "+hora+"' where cod_cliente="+cliente;
			
			bd.DadosSemRetorno(sql);
			AtualizarTabela();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void GerarPagamento(){
		double valorPagar = 0;
		java.util.Date dd = new java.util.Date();
		Date data = new Date(dd.getTime());
		String hora = String.format("%s:%s:%s", dd.getHours(), dd.getMinutes(), dd.getMinutes());
		if(radioParte.isSelected()){
			if(!txtValor.getText().isEmpty()){
				String valorString = txtValorTotal.getText().replace("R$ ", "");
				double valor = Double.parseDouble(valorString.replace(",", "."));	
				valorPagar = Double.parseDouble(txtValor.getText().replace(",", "."));
				double valorSobrado = valor-valorPagar;
				
				String sql ="SELECT nome, cod_produto FROM produto;";
				ResultSet rs = bd.DadosComRetorno(sql);
				boolean verificar = false;
				try {
					while(rs.next()){
						if(rs.getString("nome").equals(""+cliente)){
							verificar = true;
							sql="UPDATE produto SET valor="+valorSobrado+" WHERE nome = '"+cliente+"';";
							bd.DadosSemRetorno(sql);
							
							ADDProdutoVendido();
							
							sql="INSERT INTO cliente_produto (cod_produto, cod_cliente, data)"
									+ "VALUES ("+rs.getInt("cod_produto")+", "+cliente+",'"+data+" "+hora+"')";
							bd.DadosSemRetorno(sql);
							
						}
					}
					if(!verificar){
						sql="INSERT INTO produto (nome, valor) "
								+ "VALUES ('"+cliente+"', "+valorSobrado+");";
						bd.DadosSemRetorno(sql);
						
						ADDProdutoVendido();
						sql="INSERT INTO cliente_produto (cod_produto, cod_cliente, data)"
								+ "VALUES ("+cliente+", "+cliente+",'"+data+" "+hora+"')";
						bd.DadosSemRetorno(sql);
						AtualizarTabela();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				main.CarregarPagamento(valorSobrado);
			}
		}
		
	}

	public void AtualizarTabela(){
		ArrayList<ProdutoModel> lstProduto = new ArrayList<>();
		String sql="SELECT p.cod_produto, p.nome, p.valor, cp.data "
				+ "FROM cliente_produto AS cp "
				+ "INNER JOIN produto AS p "
				+ "ON (cp.cod_produto = p.cod_produto) "
				+ "WHERE cp.cod_cliente="+cliente+" and cp.cod_produto<>0 ORDER BY cp.data;";
		ResultSet rs = bd.DadosComRetorno(sql);
		
		try {
			while(rs.next()){
				ProdutoModel produto = new ProdutoModel();
				produto.setId(rs.getInt("cod_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setValor(rs.getDouble("valor"));
				produto.setData(rs.getString("data"));
				lstProduto.add(produto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<ProdutoModel> data = FXCollections.observableArrayList(lstProduto);
		tableView.setItems(data);
	}
}
