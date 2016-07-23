package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import br.com.restaurantechinaecia.banco_de_dados.*;
import br.com.restaurantechinaecia.model.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProdutosVendidosDiaController implements Initializable{

	@FXML
	TableView<ProdutoModel> tableView;
	
	@FXML
	TableColumn<ProdutoModel, String> clnProduto, clnCodigo, clnValor, clnQtd;
	
        BdControl db;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
                db = new BdControl();
		clnProduto.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("nome"));
		clnCodigo.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("id"));
		clnValor.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("valor"));
		clnQtd.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("qtd"));
		AtualizarTabela();
	}
	public void AtualizarTabela(){
            //ArrayList<ArrayList<Produto>> lstProduto = _produtosRepositorio.ObterProdutos();
            ArrayList<ProdutoModel> lstProdutoMod = new ArrayList<>();

            String sql = "select pv.cod_produto, p.nome, p.valor, sum(pv.cod_produto) as soma "
            +"from produto_vendido as pv "
            +"inner join produto as p "
            +"on pv.cod_produto = p.cod_produto "
            +"where fechado is null";

            ResultSet rs = db.DadosComRetorno(sql);
            try {
                    while(rs.next()){
                            ProdutoModel produtoModel = new ProdutoModel();
                            produtoModel.setNome(rs.getString("nome"));
                            produtoModel.setValor(rs.getDouble("valor"));
                            produtoModel.setId(rs.getInt("cod_produto"));
                            produtoModel.setQtd(rs.getInt("soma"));
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
