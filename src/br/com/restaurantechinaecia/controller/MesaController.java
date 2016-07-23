package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import br.com.restaurantechinaecia.banco_de_dados.*;
import br.com.restaurantechinaecia.helper.Helper;
import br.com.restaurantechinaecia.model.*;
import br.com.restaurantechinaecia.view.MainChinaCia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable; 
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;


public class MesaController implements Initializable {
	@FXML
	TableView<ProdutoModel> tblMesaDetalhe;
	
	@FXML
	TableView<ComandaModel> tblTodasMesas;
	
	@FXML
	TableColumn<ProdutoModel, String> clnMDProduto, clnMDPreco, clnMDId;
	
	@FXML
	TableColumn<ComandaModel, String> clnTMCodigo, clnTMPreco, clnTMDisponivel;
	
	@FXML
	Button btnPagar, btnApagarItem, btnAdicionarProduto, btnJuntar,  btnApagarComanda;
	
	@FXML
	TextField txtPreco, txtQtd, txtJuntar;
	
	@FXML
	Label lblMesa, lblValorTotal;
	
	@FXML
	ComboBox<Categoria> cboTipo;
	
	@FXML
	ComboBox<ProdutoModel> cboProduto;
	
	BdControl db;
	MainChinaCia main;	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new BdControl();
                
		tblTodasMesas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
		    	int mesa = newSelection.getId();
		    	String sql = "SELECT mp.cod_mesa, SUM(p.valor) as valor "
		    			+"FROM mesa_produto AS mp "
		    			+"INNER JOIN produto AS p "
		    			+"on (mp.cod_produto=p.cod_produto) "
		    			+"INNER JOIN mesa AS m "
		    			+"ON (mp.cod_mesa = m.cod_mesa) where mp.cod_mesa="+mesa+";";
				
				ResultSet rs = db.DadosComRetorno(sql);
				double valorTotal = 0;
				try {
					while(rs.next())
					{
						valorTotal= rs.getDouble("valor");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	AtualizaTabelaProdutos(mesa);
		    	lblMesa.setText("COMANDA: "+newSelection.getId());
		    	lblValorTotal.setText("VALOR TOTAL: "+valorTotal);
		    }
		});
		
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
			cboTipo.getItems().addAll(lstCategoria);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConfigurarBotoes();
		
		clnTMDisponivel.setCellValueFactory(new PropertyValueFactory<ComandaModel, String>("disponivel"));
		clnTMCodigo.setCellValueFactory(new PropertyValueFactory<ComandaModel, String>("id"));
		clnTMPreco.setCellValueFactory(new PropertyValueFactory<ComandaModel, String>("valor"));
		AtualizaTabelaTodas();
		
	}
	@FXML
	private void handleComboBoxAction() {
		  Categoria categoria = cboTipo.getSelectionModel().getSelectedItem();
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
	private void ConfigurarBotoes() {
		// TODO Auto-generated method stub
		btnAdicionarProduto.setOnAction(l-> AdicionarProduto());
		btnPagar.setOnAction(l-> FecharMesa());
		btnApagarItem.setOnAction(l-> RemoverProduto());
		btnJuntar.setOnAction(l-> Juntar());
		btnApagarComanda.setOnAction(l-> ExcluirComanda());
		
		/********************** KEY PRESSED **********************/
		btnAdicionarProduto.setOnKeyPressed(k->Key(k));
		btnPagar.setOnKeyPressed(k->Key(k));
		btnApagarItem.setOnKeyPressed(k->Key(k));
		btnJuntar.setOnKeyPressed(k->Key(k));
		btnApagarComanda.setOnKeyPressed(k->Key(k));
		txtPreco.setOnKeyPressed(k->Key(k));
		txtQtd.setOnKeyPressed(k->Key(k));
		txtJuntar.setOnKeyPressed(k->Key(k));
		tblMesaDetalhe.setOnKeyPressed(k->Key(k));
		tblTodasMesas.setOnKeyPressed(k->Key(k));
	}
	public void Key(KeyEvent k){
		final KeyCombination enter = new KeyCodeCombination(KeyCode.ENTER);
		final KeyCombination f3 = new KeyCodeCombination(KeyCode.F3);
		final KeyCombination f4 = new KeyCodeCombination(KeyCode.F4);
		if (enter.match(k)) {
			AdicionarProduto();
		}else if(f3.match(k)){
			RemoverProduto();
		}else if(f4.match(k)){
			//FecharMesa();
		}
	}
	public MesaController(MainChinaCia main) {
		// TODO Auto-generated constructor stub
		this.main=main;
	}
	public MesaController() {
		// TODO Auto-generated constructor stub
	}
	public void RemoverProduto() {
		ProdutoModel produtoSelected = tblMesaDetalhe.getSelectionModel().getSelectedItem();
		ComandaModel comandaSelected = tblTodasMesas.getSelectionModel().getSelectedItem();
		
		if(produtoSelected != null && comandaSelected != null){
			int cod_produto = produtoSelected.getId();
			int cod_mesa= comandaSelected.getId();
			String sql="delete FROM mesa_produto where cod_mesa="+cod_mesa+" and cod_produto="+cod_produto
					+" ORDER BY cod_produto ASC "
					+"LIMIT 1;";
			db.DadosSemRetorno(sql);
			AtualizaTabelaTodas();
			AtualizaTabelaProdutos(cod_mesa);
		}else{
			JOptionPane.showMessageDialog(null, "SELECIONE UMA COMANDA E UM PRODUTO DESSA COMANDA!!");
		}
	}
	public void FecharMesa(){
		ComandaModel comandaSelected = tblTodasMesas.getSelectionModel().getSelectedItem();
		
		if(comandaSelected!=null){
			int comanda = comandaSelected.getId();
			String sql="select mp.cod_mesa, sum(p.valor) as total"
			+" from mesa_produto as mp"
			+" inner join produto as p"
			+" on (mp.cod_produto=p.cod_produto)"
			+" where cod_mesa ="+comanda
			+" group by cod_mesa;";
			
			double total=0;
			ResultSet rs = db.DadosComRetorno(sql);
			
			try {
				while(rs.next()){
					total=rs.getDouble("total");
				}
				main.CarregarPagamento(total);
				ADDProdutoVendido(comandaSelected.getId());
				ExcluirComanda();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			JOptionPane.showMessageDialog(null, "SELECIONE UMA COMANDA!!");
		}
	}
	public void ADDProdutoVendido(int comanda){
		java.util.Date dd = new java.util.Date();
		Date data = new Date(dd.getTime());
		String hora = String.format("%s:%s:%s", dd.getHours(), dd.getMinutes(), dd.getMinutes());
		
		String sql="SELECT mp.cod_produto "
				+ "from mesa_produto AS mp "
				+ "WHERE mp.cod_mesa="+comanda+" and mp.cod_produto<>0;";
		
		ResultSet rs = db.DadosComRetorno(sql);
		
		try {
			while(rs.next()){
				sql="INSERT INTO produto_vendido(cod_produto, data) "
						+ "values ("+rs.getInt("cod_produto")+", '"+data+" "+hora+"');";
				
				db.DadosSemRetorno(sql);
			}
			sql="DELETE FROM mesa_produto WHERE cod_mesa ="+comanda+" and cod_produto<>0;";
			db.DadosSemRetorno(sql);
			sql ="UPDATE mesa set situacao = 'DISPONIVEL' where cod_mesa="+comanda;
			
			db.DadosSemRetorno(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Juntar(){
		ComandaModel mesa = tblTodasMesas.getSelectionModel().getSelectedItem();
		
		if(mesa!= null){
			if(!txtJuntar.getText().isEmpty() &&
					Helper.VerificarDigito(txtJuntar.getText())){
				int velhaComanda = mesa.getId();
				int novaComanda = Integer.parseInt(txtJuntar.getText());
				
				String sql="UPDATE mesa_produto set cod_mesa ="+novaComanda+" where cod_mesa="+velhaComanda+""
						+ " and cod_produto<>0";
				db.DadosSemRetorno(sql);
				
				sql="UPDATE mesa set situacao ='DISPONIVEL' where cod_mesa="+velhaComanda;
				db.DadosSemRetorno(sql);
				
				sql="UPDATE mesa set situacao ='INDISPONIVEL' where cod_mesa="+novaComanda;
				db.DadosSemRetorno(sql);
				
				txtJuntar.setText("");
				AtualizaTabelaTodas();
				
			}else{
				JOptionPane.showMessageDialog(null, "DIGITE O NÚMERO DA OUTRA COMANDA!!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "SELECIONE UMA COMANDA!!");
		}
	}
	public void ExcluirComanda(){
		ComandaModel mesaSelecionada = tblTodasMesas.getSelectionModel().getSelectedItem();

		if(mesaSelecionada!= null){
			
			String sql = "DELETE FROM mesa_produto WHERE cod_mesa ="+mesaSelecionada.getId()+" and cod_produto<>0";
			db.DadosSemRetorno(sql);
			
			sql="UPDATE mesa SET situacao ='DISPONIVEL' WHERE cod_mesa="+mesaSelecionada.getId()+";";
			db.DadosSemRetorno(sql);
			
			AtualizaTabelaTodas();
			AtualizaTabelaProdutos(mesaSelecionada.getId());
		}
	}
	public void AdicionarProduto(){
		ComandaModel mesaSelecionada = tblTodasMesas.getSelectionModel().getSelectedItem();
		
		if(mesaSelecionada!= null){
			if(cboTipo.getSelectionModel().getSelectedItem()!=null
					&& cboProduto.getSelectionModel().getSelectedItem()!= null){
				int qtdade = Integer.parseInt(txtQtd.getText());
				
				for(int i=0; i<qtdade; i++){
					String sql = "INSERT INTO mesa_produto (cod_mesa, cod_produto) "
							+ "VALUES ("+mesaSelecionada.getId()+", "
									+ ""+cboProduto.getSelectionModel().getSelectedItem().getId()+");";
					db.DadosSemRetorno(sql);
				}
				String sql="UPDATE mesa SET situacao ='INDISPONIVEL' WHERE cod_mesa="+mesaSelecionada.getId()+";";
				db.DadosSemRetorno(sql);
				AtualizaTabelaTodas();
				AtualizaTabelaProdutos(mesaSelecionada.getId());
			}else{
				JOptionPane.showMessageDialog(null, "SELECIONE UM PRODUTO");
			}
		}else{
			JOptionPane.showMessageDialog(null, "SELECIONE UMA COMANDA");
		}
	}
	public void AtualizaTabelaTodas(){
		ArrayList<ComandaModel> lstMesa = new ArrayList<>();

		String sql ="SELECT m.situacao, m.cod_mesa, SUM(p.valor) AS valor "
				+"FROM mesa_produto AS mp "
				+"RIGHT JOIN mesa AS m ON (mp.cod_mesa=m.cod_mesa) "
				+"LEFT JOIN produto AS p ON (mp.cod_produto = p.cod_produto) GROUP BY cod_mesa;";
		
		ResultSet rs = db.DadosComRetorno(sql);
		try {
			while(rs.next())
			{
				ComandaModel mesa = new ComandaModel();
				mesa.setDisponivel(rs.getString("situacao"));
				mesa.setId(rs.getInt("cod_mesa"));
				mesa.setValor(rs.getDouble("valor"));
				lstMesa.add(mesa);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<ComandaModel> data = FXCollections.observableList(lstMesa);
		tblTodasMesas.setItems(data);
	}
	public void AtualizaTabelaProdutos(int mesa){
		clnMDProduto.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("nome"));
		clnMDPreco.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("valor"));
		clnMDId.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("id"));

		ArrayList<ProdutoModel> lstProduto = new ArrayList<>();
		
		String sql = "SELECT mp.cod_mesa, p.* "
    			+"FROM mesa_produto AS mp "
    			+"INNER JOIN produto AS p "
    			+"on (mp.cod_produto=p.cod_produto) "
    			+"INNER JOIN mesa AS m "
    			+"ON (mp.cod_mesa = m.cod_mesa) where mp.cod_mesa="+mesa+" "
    			+ "and mp.cod_produto<>0;";
		
		ResultSet rs = db.DadosComRetorno(sql);
		
		try {
			while(rs.next())
			{
				ProdutoModel produto = new ProdutoModel();
				produto.setId(rs.getInt("cod_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setValor(rs.getDouble("valor"));
				lstProduto.add(produto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<ProdutoModel> data = FXCollections.observableList(lstProduto);
		tblMesaDetalhe.setItems(data);
	}
}