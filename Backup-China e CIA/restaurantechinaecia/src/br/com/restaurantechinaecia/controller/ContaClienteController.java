package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import br.com.restaurantechinaecia.banco_de_dados.BdControl;
import br.com.restaurantechinaecia.helper.Data;
import br.com.restaurantechinaecia.model.*;
import br.com.restaurantechinaecia.view.MainChinaCia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;

public class ContaClienteController implements Initializable{

	@FXML
	TableView<ContaClienteModel> tableView;
	
	@FXML
	TableColumn<ContaClienteModel, String> clnCpf, clnNome, clnTel, clnValorTotal, clnUltimoDia, clnRg;
	
	@FXML
	TextField txtPreco, txtQtd;
	
	@FXML
	ComboBox<ProdutoModel> cboProduto ;
	
	@FXML
	ComboBox<Categoria> cboTipoProduto;
	
	@FXML
	Button btnAdicionar, btnCadastrarNovo, btnMostrar, btnAtualizar, btnExcluir;
	
	MainChinaCia main;
	BdControl db;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db= new BdControl();
		
		cboTipoProduto.setPromptText("SELECIONE...");
		cboProduto.setVisible(false);
		ConfigurarBotoes();  
		
		String sql = "SELECT * FROM categoria";
		List<Categoria> lstCategoria = new ArrayList<>();
		ResultSet rs = db.DadosComRetorno(sql);
		try {
			while(rs.next()){
				Categoria categoria = new Categoria();
				categoria.setCod_categoria(rs.getInt("cod_categoria"));
				categoria.setNome(rs.getString("nome"));
				lstCategoria.add(categoria);
			}
			cboTipoProduto.getItems().addAll(lstCategoria);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AtribuiColunas();
		
		AtualizarTabela();
	}
	private void AtribuiColunas() {
		clnNome.setCellValueFactory(new PropertyValueFactory<ContaClienteModel, String>("nome"));
		clnTel.setCellValueFactory(new PropertyValueFactory<ContaClienteModel, String>("telefone"));
		clnValorTotal.setCellValueFactory(new PropertyValueFactory<ContaClienteModel, String>("ultimoValor"));
		clnUltimoDia.setCellValueFactory(new PropertyValueFactory<ContaClienteModel, String>("data"));
		clnCpf.setCellValueFactory(new PropertyValueFactory<ContaClienteModel, String>("cpf"));
		clnRg.setCellValueFactory(new PropertyValueFactory<ContaClienteModel, String>("rg"));
	}
	@FXML
	private void handleComboBoxAction() {
		  Categoria categoria = cboTipoProduto.getSelectionModel().getSelectedItem();
		  String sql="SELECT cod_produto, nome from produto where cod_categoria ="+categoria.getCod_categoria();
		  
		  ArrayList<ProdutoModel> lstProduto = new ArrayList<>();
		  ResultSet rs = db.DadosComRetorno(sql);
		  
		  try {
			while(rs.next()){
				  ProdutoModel produto = new ProdutoModel();
				  produto.setId(rs.getInt("cod_produto"));
				  produto.setNome(rs.getString("nome"));
				  lstProduto.add(produto);
			  }
			cboProduto.getItems().clear();
			cboProduto.setPromptText("SELECIONE...");
			cboProduto.setVisible(true);
			cboProduto.getItems().addAll(lstProduto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ContaClienteController(MainChinaCia main) {
		// TODO Auto-generated constructor stub
		this.main=main;
	}
	public ContaClienteController() {
		// TODO Auto-generated constructor stub
	}
	public void ExcluirCliente(){
		ContaClienteModel cliente = tableView.getSelectionModel().getSelectedItem();
		String sql;
		sql = "DELETE FROM cliente_produto where cod_cliente = "+cliente.getCod_cliente();
		db.DadosSemRetorno(sql);
		sql = "DELETE FROM cliente where cod_cliente ="+cliente.getCod_cliente();
		db.DadosSemRetorno(sql);
		AtualizarTabela();
	}
	public void ConfigurarBotoes(){
		btnExcluir.setOnAction(l-> ExcluirCliente());
		btnAdicionar.setOnAction(l-> Adicionar());
		btnCadastrarNovo.setOnAction(l-> CadastrarNovoCliente());
		btnMostrar.setOnAction(l-> MostrarCliente());
		btnAtualizar.setOnAction(l-> AtualizarTabela());
		tableView.setOnKeyPressed(k->Key(k));
		btnAdicionar.setOnKeyPressed(k->Key(k));
		btnCadastrarNovo.setOnKeyPressed(k->Key(k));
		btnMostrar.setOnKeyPressed(k->Key(k));
		btnAtualizar.setOnKeyPressed(k->Key(k));
		cboTipoProduto.setOnKeyPressed(k->Key(k));
		cboProduto.setOnKeyPressed(k->Key(k));
		txtPreco.setOnKeyPressed(k->Key(k));
		txtQtd.setOnKeyPressed(k->Key(k));
	}
	public void Key(KeyEvent k){
		final KeyCombination f2 = new KeyCodeCombination(KeyCode.F2);
		final KeyCombination f3 = new KeyCodeCombination(KeyCode.F3);
		final KeyCombination f4 = new KeyCodeCombination(KeyCode.F4);
		final KeyCombination f5 = new KeyCodeCombination(KeyCode.F5);
		if (f2.match(k)) {
			Adicionar();
		}else if(f3.match(k)){
			CadastrarNovoCliente();
		}else if(f4.match(k)){
			MostrarCliente();
		}else if(f5.match(k)){
			AtualizarTabela();
		}
	}
	public void Adicionar(){
		ContaClienteModel cliente = tableView.getSelectionModel().getSelectedItem();
		
		if(cliente != null){
			if(cboProduto.getSelectionModel().getSelectedItem()!= null 
					&& cboTipoProduto.getSelectionModel().getSelectedItem()!= null ){
				if(!txtPreco.getText().isEmpty()){
					
				}else{
					int cod_produto= cboProduto.getSelectionModel().getSelectedItem().getId();
					int qtdade = Integer.parseInt(txtQtd.getText());
					
					for(int i = 0; i<qtdade; i++){
                                            String sql ="INSERT INTO cliente_produto (cod_cliente, cod_produto, data) values "
                                                   + "("+cliente.getCod_cliente()+", "+cod_produto+", '"+Data.RetornaDataHora()+"')";
                                            db.DadosSemRetorno(sql);
					}
					
					AtualizarTabela();
				}
			}else{
				JOptionPane.showMessageDialog(null, "SELECIONE O PRODUTO");
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "SELECIONE UM CLIENTE");
		}
		
		/*cliente.getLstProdutoC().add(produto);
		AtualizarTabela();*/
	}
	public void CadastrarNovoCliente(){
		main.CarregarContaClienteAdicionar();
	}
	public void MostrarCliente(){
		ContaClienteModel cliente = tableView.getSelectionModel().getSelectedItem();
		if(cliente != null){
			main.CarregarContaClienteMostrar(cliente.getCod_cliente());
		}else{
			JOptionPane.showMessageDialog(null, "SELECIONE UM CLIENTE");
		}
	}
	public void AtualizarTabela(){
		ArrayList<ContaClienteModel> lstClientes = new ArrayList<>();
		
		String sql ="select c.*,"
				+ " max(cp.data) as ultima_data,"
				+ " sum(p.valor) as valor_total"
				+ " from cliente as c"
				+ " left join cliente_produto as cp"
					+ " on (c.cod_cliente=cp.cod_cliente)"
				+ " left join produto as p "
					+ "on (cp.cod_produto=p.cod_produto) group by c.nome;";
		ResultSet rs =db.DadosComRetorno(sql);
		try {
			while(rs.next()){
				ContaClienteModel cliente= new ContaClienteModel();
				cliente.setCod_cliente(rs.getInt("cod_cliente"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				cliente.setRg(rs.getString("rg"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setUltimoValor(rs.getDouble("valor_total"));
				cliente.setData(rs.getString("ultima_data"));
				lstClientes.add(cliente);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<ContaClienteModel> data = FXCollections.observableArrayList(lstClientes);
		tableView.setItems(data);
	}
}
