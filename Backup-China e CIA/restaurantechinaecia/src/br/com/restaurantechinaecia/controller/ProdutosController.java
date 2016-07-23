package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import br.com.restaurantechinaecia.banco_de_dados.*;
import br.com.restaurantechinaecia.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ProdutosController implements Initializable{

	@FXML
	TableView<ProdutoModel> tblProdutos;
	
	@FXML
	TableColumn<ProdutoModel, String> clnCodigo, clnProduto, clnPreco;
	
	@FXML
	Button btnAdicionar, btnAtualizar, btnRemover;
	
	@FXML
	TextField txtPrecoProduto, txtNProduto, txtVlrPeso, txtQtdMin, txtQtdMax;
	
	@FXML
	ComboBox<Categoria> cboCate;
        
	@FXML
	Pane pane;
        
	@FXML
	RadioButton radioSim, radioNao;
	
	BdControl db;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db= new BdControl();
		btnAdicionar.setOnAction(l-> AdicionarProduto());
		btnAtualizar.setOnAction(l-> AtualizarProduto());
		btnRemover.setOnAction(l-> RemoverProduto());
		txtPrecoProduto.setPromptText("10.00 (Separar real de centavo com ponto (.))");
	
		clnCodigo.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("id"));
		clnProduto.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("nome"));
		clnPreco.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("valor"));
		
                ToggleGroup group = new ToggleGroup();
                
                radioSim.setToggleGroup(group);
                radioNao.setToggleGroup(group);
                
                pane.setVisible(false);
                group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                    @Override
                    public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                        if(radioSim.isSelected()){					
                            pane.setVisible(true);
                        }else if (radioNao.isSelected()){
                            pane.setVisible(false);  
                        }
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
			cboCate.getItems().addAll(lstCategoria);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AtualizarTabela();
	}
	
	private void RemoverProduto() {
		// TODO Auto-generated method stub
		ProdutoModel p = tblProdutos.getSelectionModel().getSelectedItem();
		if(p!=null){
			String sql = "DELETE FROM produto where cod_produto ="+p.getId();
			db.DadosSemRetorno(sql);
		}else{
			JOptionPane.showMessageDialog(null, "SELECIONE O PRODUTO A SER REMOVIDO!!");
		}
		AtualizarTabela();
	}

	private void AtualizarTabela() {
		// TODO Auto-generated method stub
		ArrayList<ProdutoModel> lstProduto = new ArrayList<>();
		
		String sql = "SELECT * FROM produto;";
		ResultSet rs = db.DadosComRetorno(sql);
		try {
			while(rs.next()){
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
		tblProdutos.setItems(data);
	}
	private void AdicionarProduto() {
		// TODO Auto-generated method stub
		ProdutoModel produto = new ProdutoModel();
		if(!txtNProduto.getText().isEmpty() && !txtPrecoProduto.getText().isEmpty() 
				&& cboCate.getSelectionModel().getSelectedItem()!= null
                       /* && !txtQtdMin.getText().isEmpty() && !txtQtdMax.getText().isEmpty()*/){
			
			produto.setNome(txtNProduto.getText().toUpperCase());
			produto.setValor(Double.parseDouble(txtPrecoProduto.getText().replace(",", ".")));
			produto.setCategoria(cboCate.getSelectionModel().getSelectedItem());
			String sql ="INSERT INTO produto(nome, valor, cod_categoria) values"
					+ "('"+produto.getNome()+"', "+produto.getValor()+", "+produto.getCategoria().getCod_categoria()+")";
			db.DadosSemRetorno(sql);
			txtNProduto.setText("");
			txtPrecoProduto.setText("");
			AtualizarTabela();
		}else{
			JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS!!");
		}
	}
	private void AtualizarProduto() {
		// TODO Auto-generated method stub
		ProdutoModel p = tblProdutos.getSelectionModel().getSelectedItem();
		if(p!= null){
			if(!txtNProduto.getText().isEmpty() && !txtPrecoProduto.getText().isEmpty()
					&& cboCate.getSelectionModel().getSelectedItem()!=null){
				ProdutoModel produtoNovo = new ProdutoModel();
				
				produtoNovo.setNome(txtNProduto.getText().toUpperCase());
				produtoNovo.setValor(Double.parseDouble(txtPrecoProduto.getText().replace(",", ".")));
				produtoNovo.setCategoria(cboCate.getSelectionModel().getSelectedItem());
				produtoNovo.setId(p.getId());
				
				String sql ="UPDATE produto SET nome='"+produtoNovo.getNome()+"', valor= "+produtoNovo.getValor()+","
						+ " cod_categoria = "+produtoNovo.getCategoria().getCod_categoria()
							+ " where cod_produto ="+produtoNovo.getId();
				System.out.println(sql);
			}else{
				JOptionPane.showMessageDialog(null, "PREECHA TODOS OS CAMPOS!!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "SELECIONE O PRODUTO A SER MODIFICADO!!");
		}
	}

}
