package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import br.com.restaurantechinaecia.banco_de_dados.BdControl;
import br.com.restaurantechinaecia.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PagamentoController implements Initializable{

	@FXML
	TextField txtValorTroco, txtValorTotal, txtTroco;
	
	@FXML
	ComboBox<Cartao> comboCartao;
	
	@FXML
	Button btnOk, btnGerarPagamento;
	
	@FXML
	Pane paneTroco;
	
	BdControl db;
	double valorProduto;
	Stage secondStage;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		db = new BdControl();
		
		txtValorTotal.setEditable(false);
		txtTroco.setEditable(false);
		btnGerarPagamento.setOnAction(l-> GerarPagamento());
		btnOk.setOnAction(l-> BtnOk());

		String sql ="SELECT * FROM tipo_pagamento";
		ResultSet rs = db.DadosComRetorno(sql);
		ArrayList<Cartao> lstCartao = new ArrayList<>();
		try {
			while(rs.next()){
				Cartao c = new Cartao();
				c.setId(rs.getInt("cod_tipopagamento"));
				c.setNome(rs.getString("nome"));
				c.setTipo(rs.getInt("tipo"));
				lstCartao.add(c);
			}
			comboCartao.getItems().addAll(lstCartao);
			comboCartao.setPromptText("TIPO DE PAGAMENTO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtValorTotal.setText("R$ "+valorProduto);
	}
	
	public PagamentoController(double valorProduto, Stage secondStage) {
		this.valorProduto=valorProduto;
		this.secondStage=secondStage;
	}
	public void GerarPagamento(){
		try{
			if(comboCartao.getSelectionModel().getSelectedItem().getTipo()==1){
				int tipoPagemento = comboCartao.getSelectionModel().getSelectedItem().getId();
				if(!txtValorTroco.getText().isEmpty()){
					double valorParaTroco = Double.parseDouble(txtValorTroco.getText().replace(",", "."));
					double valorTroco = valorParaTroco-valorProduto;
					
					txtTroco.setText("R$ "+valorTroco);
				}else{
					txtTroco.setText("VALOR PAGO!!");
				}
				String sql="INSERT INTO pagamento(cod_tipopagamento, valor, data) "
						+ "VALUES ("+tipoPagemento+", "+valorProduto+", now())";
				db.DadosSemRetorno(sql);
				
			}else if(comboCartao.getSelectionModel().getSelectedItem().getTipo()==0){
				int tipoPagemento = comboCartao.getSelectionModel().getSelectedItem().getId();
				String sql="INSERT INTO pagamento(cod_tipopagamento, valor, data) "
						+ "VALUES ("+tipoPagemento+", "+valorProduto+", now())";
				db.DadosSemRetorno(sql);
				txtTroco.setText("VALOR PAGO!!");
			}
		}catch (NullPointerException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "SELECIONE O TIPO DE PAGAMENTO!!");
			
		}
	}
	private void BtnOk(){
		secondStage.close();
	}
}