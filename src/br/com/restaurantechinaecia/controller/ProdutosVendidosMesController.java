package br.com.restaurantechinaecia.controller;

import br.com.restaurantechinaecia.banco_de_dados.BdControl;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.com.restaurantechinaecia.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProdutosVendidosMesController implements Initializable{

	@FXML
	TableView<ProdutoModel> tableView;
	
	@FXML
	TableColumn<ProdutoModel, String> clnID, clnNome, clnValor, clnQtd;
	
	@FXML
	Label lbltotal; 
	
	
        BdControl db;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		double valorTotal = 0;
		
                 db = new BdControl();
		
		
                String sql = "select sum(p.valor) as valorTotal "
                +"from produto_vendido as pv "
                +"inner join produto as p "
                +"on pv.cod_produto = p.cod_produto ";
               
                ResultSet rs = db.DadosComRetorno(sql);
                try {
                        while(rs.next()){
                            valorTotal= rs.getFloat("valorTotal");
                        }
                        
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
		lbltotal.setText("R$ "+valorTotal);
		clnID.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("id"));
		clnNome.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("nome"));
		clnValor.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("valor"));
		clnQtd.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("qtd"));
		AtualizarTabela();
	}
	public void AtualizarTabela(){
		ArrayList<ProdutoModel> lstProdutoMod = new ArrayList<>();
                
                
		String sql = "select pv.cod_produto, p.nome, p.valor "
            +"from produto_vendido as pv "
            +"inner join produto as p "
            +"on pv.cod_produto = p.cod_produto ";
            ResultSet rs = db.DadosComRetorno(sql);
            try {
                    while(rs.next()){
                            ProdutoModel produtoModel = new ProdutoModel();
                            produtoModel.setNome(rs.getString("nome"));
                            produtoModel.setValor(rs.getDouble("valor"));
                            produtoModel.setId(rs.getInt("cod_produto"));
                            lstProdutoMod.add(produtoModel);
                    }
                    ObservableList<ProdutoModel> data = FXCollections.observableArrayList(lstProdutoMod);
                    tableView.setItems(data);
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
	}
}
