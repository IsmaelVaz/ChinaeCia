package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.restaurantechinaecia.banco_de_dados.BdControl;
import br.com.restaurantechinaecia.helper.Helper;
import br.com.restaurantechinaecia.model.ContaClienteModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ContaClienteAdicionarController implements Initializable{

	@FXML
	TextField txtCpf, txtNome, txtTel, txtRg;
	
	@FXML
	Button btnAdicionar, btnFechar;
	
	@FXML
	Pane pane;
	
	Stage secondStage;
	BdControl bd ;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		bd = new BdControl();
		btnAdicionar.setOnAction(l-> Adicionar());
		btnFechar.setOnAction(l-> Fechar());
		pane.setVisible(false);
	}
	public ContaClienteAdicionarController(Stage secondStage) {
		// TODO Auto-generated constructor stub
		this.secondStage=secondStage;
	}
	public void Fechar(){
		secondStage.close();
	}
	public void Adicionar(){
		ContaClienteModel cliente = new ContaClienteModel();
		if(!txtNome.getText().isEmpty() 
				&& !txtCpf.getText().isEmpty() && !txtTel.getText().isEmpty()){
			if(Helper.ValidarCpf(txtCpf.getText())){
				cliente.setNome(txtNome.getText().toUpperCase());
				cliente.setCpf(txtCpf.getText());
				cliente.setRg(txtRg.getText());
				cliente.setTelefone(txtTel.getText());
				
				java.util.Date dd = new java.util.Date();
				Date data = new Date(dd.getTime());
				String hora = String.format("%s:%s:%s", dd.getHours(), dd.getMinutes(), dd.getMinutes());
				String sql;
				sql ="INSERT INTO cliente (nome, telefone, cpf, rg) values"
						+ "('"+cliente.getNome()+"','"+cliente.getTelefone()+"',"
								+ " '"+cliente.getCpf()+"', '"+cliente.getRg()+"');";
				
				bd.DadosSemRetorno(sql);
				/*sql="SELECT MAX(cod_cliente) as cod_cliente from cliente;";
				ResultSet rs = bd.DadosComRetorno(sql);
				int cod_cliente=0;
				try {
					while(rs.next()){
						cod_cliente= rs.getInt("cod_cliente");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				sql="INSERT INTO cliente_produto (cod_cliente, cod_produto, data) values "
						+ "("+cod_cliente+", 0, '"+data+" "+hora+"');";
				bd.DadosSemRetorno(sql);*/
				secondStage.close();
			}else{
				JOptionPane.showMessageDialog(null, "O CPF NãO é VALIDO");
			}
		}else{
			JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS");
		}
	}
	
}
